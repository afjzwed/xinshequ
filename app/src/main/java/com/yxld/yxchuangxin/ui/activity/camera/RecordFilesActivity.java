package com.yxld.yxchuangxin.ui.activity.camera;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.p2p.core.P2PHandler;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.OnLoadMoreListener;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.RecordFile;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerRecordFilesComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.RecordFilesContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.RecordFilesModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.RecordFilesPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.RecordFileAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/06/21 10:22:29
 */

public class RecordFilesActivity extends BaseActivity implements RecordFilesContract.View {

    @Inject
    RecordFilesPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private String deviceId;
    private String devicePwd;
    Date startDate = new Date(0);
    public static final String RECORDFILES = "com.yoosee.RECORDFILES";
    private RecordFileAdapter recordFileAdapter;
    private ArrayList<RecordFile> items=new ArrayList<>();
    private boolean firstLoad = true;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_recordfiles);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        deviceId = bundle.getString("deviceId");
        devicePwd = bundle.getString("devicePwd");
    }

    @Override
    protected void initData() {
        progressDialog.show();
        recordFileAdapter = new RecordFileAdapter(items);
        Date endDate = new Date(System.currentTimeMillis());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //加载更多
//        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                //获取上一次获取的数据中最后一位
//                RecordFile file = (RecordFile) items.get(OnLoadMoreListener.itemCount - 1);
//                String lastTime = file.getName().substring(6, 22);
//                lastTime = lastTime.replace("_", " ");
//                Date nextEndTime = null;
//                try {
//                    nextEndTime = sdf.parse(lastTime);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                //因获取到数据是按照时间从近到远顺序排序
//                //所以加载更多时startDate不用变,改变nextEndTime即可
//                P2PHandler.getInstance().getRecordFiles(deviceId, devicePwd, startDate, nextEndTime);
//            }
//        });
        regFilter();
        //获取录像列表
        P2PHandler.getInstance().getRecordFiles(deviceId, devicePwd, startDate, endDate);
        recyclerView.setAdapter(recordFileAdapter);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerRecordFilesComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .recordFilesModule(new RecordFilesModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RecordFilesContract.RecordFilesContractPresenter presenter) {
        mPresenter = (RecordFilesPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(RECORDFILES)) {
                //获取到的录像文件名字
                // 该数组中的文件名按照录像时间从近到远开始排序 且 每次获取到的nams的长度不大于64
                KLog.i(intent);
                String[] names = (String[]) intent.getCharSequenceArrayExtra("recordList");
                KLog.i("names大小：" +names.length);
                byte option = intent.getByteExtra("option1", (byte) -1);
                if (option == 82) {
                    progressDialog.hide();
                    ToastUtil.show(RecordFilesActivity.this, "存储卡不存在!!!");
                }if (names.length > 0) {
                    updateAdapter(names);
                }
            }
        }
    };

    private void updateAdapter(String[] names) {
        //因加载更多时，之前的最后一个文件和加载后第一个文件重复，故仅第一次加载时加载第一个文件
        if (firstLoad) {
            items.add(new RecordFile(count, names[0]));
            firstLoad = false;
        }
        for (int i = 1; i < names.length; i++) {
            items.add(new RecordFile(++count, names[i]));
        }
        recordFileAdapter.setNewData(items);
        recyclerView.addOnItemTouchListener(ClickListener);
        progressDialog.hide();
    }

    //item点击事件
    private OnItemChildClickListener ClickListener=new OnItemChildClickListener() {
        @Override
        public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            RecordFile recordFiles= items.get(position);
            Intent intent = new Intent(RecordFilesActivity.this, PlayBackActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("fileList",items);
            bundle.putSerializable("file", recordFiles);
            intent.putExtra("recordFile", bundle);
            intent.putExtra("deviceId", deviceId);
            intent.putExtra("position", position);
            intent.putExtra("devicePwd", devicePwd);
            startActivity(intent);
        }
    };

    private void regFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(RECORDFILES);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}