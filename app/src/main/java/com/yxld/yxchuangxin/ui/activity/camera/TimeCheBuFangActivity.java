package com.yxld.yxchuangxin.ui.activity.camera;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.BuCheFang;
import com.yxld.yxchuangxin.entity.FangquEntity;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerTimeCheBuFangComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.TimeCheBuFangContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.TimeCheBuFangModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.TimeCheBuFangPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.TimeCheBuFangAdapter;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.yxld.yxchuangxin.view.datepicker.NumericWheelAdapter;
import com.yxld.yxchuangxin.view.datepicker.WheelView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/09/05 17:39:46
 */

public class TimeCheBuFangActivity extends BaseActivity implements TimeCheBuFangContract.View {

    @Inject
    TimeCheBuFangPresenter mPresenter;
    @Inject
    TimeCheBuFangAdapter timeCheBuFangAdapter;
    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    @BindView(R.id.check_fangqu)
    TextView checkFangqu;
    private WheelView fangqu;
    private NumericWheelAdapter xiangmuAdapter;
    private ArrayList<String> fangquList;
    private ArrayList<String> shebeiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_time_che_bu_fang);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        recycerView.setLayoutManager(new LinearLayoutManager(this));
        timeCheBuFangAdapter.setOnItemChildClickListener((baseQuickAdapter, view, i) -> {
            switch (view.getId()) {
                case R.id.tv_cacanl:
                    TextView tvcacal = (TextView)view;
                    if (tvcacal.getText().toString().trim().equals("已撤销")) {
                        return;
                    }
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("uuid", Contains.uuid);
                    map.put("mac", getIntent().getStringExtra("mac"));
                    map.put("fangqu", timeCheBuFangAdapter.getData().get(i).getFangquhao() + "");
                    mPresenter.cacanlDingshiCheBuFang(map);
                    break;
            }
        });
        recycerView.setAdapter(timeCheBuFangAdapter);

        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("mac", getIntent().getStringExtra("mac"));
        mPresenter.getFangqu(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerTimeCheBuFangComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .timeCheBuFangModule(new TimeCheBuFangModule(this))
                .build()
                .inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_camera_device, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View tianjia = findViewById(R.id.add);
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(TimeCheBuFangActivity.this, AddCheBuFangActivity.class);
                intent.putExtra("mac", getIntent().getStringExtra("mac"));
                startActivityForResult(intent, 5);
                overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
                break;
            case android.R.id.home:
                finish();
                System.gc();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 5) {
            initData();
        }
    }

    @Override
    public void setPresenter(TimeCheBuFangContract.TimeCheBuFangContractPresenter presenter) {
        mPresenter = (TimeCheBuFangPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void setFangqu(List<FangquEntity.DataBean> list) {
        fangquList = new ArrayList<String>();
        shebeiList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                fangquList.add(list.get(i).getShebeiFangquBianhao());
                shebeiList.add(list.get(i).getShebeiName());
            }
        }
        Map<String, String> map1 = new HashMap<>();
        map1.put("mac", getIntent().getStringExtra("mac"));
        map1.put("uuid", Contains.uuid);
//        map1.put("number", list.get(0).getShebeiFangquBianhao());
        map1.put("number", "1");
        map1.put("timestamp", Calendar.getInstance().getTimeInMillis() + "");
        mPresenter.getTimingBufang(map1);
        xiangmuAdapter = new NumericWheelAdapter(TimeCheBuFangActivity.this, 0, shebeiList.size() - 1, "", shebeiList);
        xiangmuAdapter.setTextSize(15);
//        fangqu.setViewAdapter(xiangmuAdapter);
    }

    @Override
    public void setBuCheFangList(BuCheFang buCheFangList) {
        checkFangqu.setText(shebeiList.get(currentItem));
        timeCheBuFangAdapter.setNewData(buCheFangList.getList());
    }

    private int currentItem;
    @Override
    public void onCancalBuchefangChenggong() {
        initData();
    }

    private void showWheelView(View showView) {
        View view = LayoutInflater.from(this).inflate(R.layout.picker_fangqu, null);
        AutoLinearLayout ll_content = (AutoLinearLayout) view.findViewById(R.id.ll_content);
        ll_content.setAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_manage_product_in));
        TextView submit = (TextView) ll_content.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map1 = new HashMap<>();
                map1.put("mac", getIntent().getStringExtra("mac"));
                map1.put("uuid", Contains.uuid);
                map1.put("number", fangquList.get(fangqu.getCurrentItem()));
                currentItem = fangqu.getCurrentItem();
                map1.put("timestamp", Calendar.getInstance().getTimeInMillis() + "");
                KLog.i("防区号：" + xiangmuAdapter.getItem(fangqu.getCurrentItem()));
                mPresenter.getTimingBufang(map1);
                CustomPopWindow.onBackPressed();
            }
        });
        fangqu = (WheelView) ll_content.findViewById(R.id.fangqu);
        fangqu.setViewAdapter(xiangmuAdapter);
        new CustomPopWindow.PopupWindowBuilder(this)
                .setClippingEnable(false)
                .setFocusable(true)
                .setView(view)
                .setContenView(ll_content)
                .size(UIUtils.getDisplayWidth(this), UIUtils.getDisplayHeigh(this))
                .create()
                .showAtLocation(showView, Gravity.CENTER, 0, 0);
    }

    @OnClick(R.id.check_fangqu)
    public void onViewClicked() {
        showWheelView(checkFangqu);
    }
}