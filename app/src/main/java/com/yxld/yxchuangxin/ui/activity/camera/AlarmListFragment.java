package com.yxld.yxchuangxin.ui.activity.camera;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.HostEntiti;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerAlarmListComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.AlarmListContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.AlarmListModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.AlarmListPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.AlarmListAdapter;
import com.yxld.yxchuangxin.ui.adapter.camera.CheckChangeListener;

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
 * @date 2017/09/04 15:09:38
 */

public class AlarmListFragment extends BaseFragment implements AlarmListContract.View, CheckChangeListener {

    @Inject
    AlarmListPresenter mPresenter;

    @Inject
    AlarmListAdapter alarmListAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.iv_dianhua)
    ImageView ivDianhua;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_list, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        alarmListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
//                if (alarmListAdapter.getData().get(i).getZhujiBuchefangZhuangtai() == null) {
//                    return;
//                }
                Map<String, String> map = new HashMap<>();
                map.put("mac", alarmListAdapter.getData().get(i).getZhujiMac());
                map.put("uuid", Contains.uuid);
                map.put("timestamp", Calendar.getInstance().getTimeInMillis() + "");
                switch (view.getId()) {
                    case R.id.tv_chebufang:
                        Intent intent = new Intent(getActivity(), TimeCheBuFangActivity.class);
                        intent.putExtra("mac", alarmListAdapter.getData().get(i).getZhujiMac());
                        startActivity(intent);
                        break;
                    case R.id.button11:
                        if (alarmListAdapter.getData().get(i).getZhujiBuchefangZhuangtai().equals("0")) {
                            return;
                        }
                        map.put("type", "0");
                        mPresenter.buCheFang(map);
                        break;
                    case R.id.button12:
                        if (alarmListAdapter.getData().get(i).getZhujiBuchefangZhuangtai().equals("1")) {
                            return;
                        }
                        map.put("type", "1");
                        mPresenter.buCheFang(map);
                        break;
                    case R.id.button13:
                        if (alarmListAdapter.getData().get(i).getZhujiBuchefangZhuangtai().equals("2")) {
                            return;
                        }
                        map.put("type", "2");
                        mPresenter.buCheFang(map);
                        break;
                    case R.id.iv_avater:
                        Intent intent1 = new Intent(getActivity(), FangquListActivity.class);
                        intent1.putExtra("mac", alarmListAdapter.getData().get(i).getZhujiMac());
                        startActivity(intent1);
                        break;
                    case R.id.lishibaojing:
                        Intent intent2 = new Intent(getActivity(), AllBaoJingActivity.class);
                        intent2.putExtra("mac", alarmListAdapter.getData().get(i).getZhujiMac());
                        startActivity(intent2);
                        break;
                    case R.id.tv_tongzhi:
                        Intent intent3 = new Intent(getActivity(), InformPersonActivity.class);
                        intent3.putExtra("databean", alarmListAdapter.getData().get(i));
                        startActivity(intent3);
                        break;
                    case R.id.xufei:
                        Intent intent4 = new Intent(getActivity(), AlarmPayActivity.class);
                        intent4.putExtra("mac", alarmListAdapter.getData().get(i).getZhujiMac());
                        intent4.putExtra("time", alarmListAdapter.getData().get(i).getZhujiJiezhiTime());
                        intent4.putExtra("zhujileixi", alarmListAdapter.getData().get(i).getZhujiYonghuLeixin() == 1 ? "租赁用户" : "购机用户");
                        //  startActivity(intent4);
                        startActivityForResult(intent4, 0);
                        break;
//                    case R.id.button21:          //开启
//                        map.put("tk", "1");
//                        map.put("type", "0");
//                        mPresenter.mingDi(map);
//                        //开启鸣笛
//                        break;
//                    case R.id.button22:          //关闭
//                        map.put("tk", "0");
//                        map.put("type", "0");
//                        mPresenter.mingDi(map);
//                        //关闭鸣笛
//                        break;
                    default:
                        break;
                }
            }
        });
        alarmListAdapter.setCheckChangeListener(this);
        recyclerView.setAdapter(alarmListAdapter);
        mPresenter.getHost();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getHost();
            }
        });

        return view;
    }

    /**
     * 支付完成后的回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == 0) {
                mPresenter.getHost();
            }
        }
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerAlarmListComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .alarmListModule(new AlarmListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(AlarmListContract.AlarmListContractPresenter presenter) {
        mPresenter = (AlarmListPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        refreshLayout.setRefreshing(false);
        progressDialog.hide();
    }

    @Override
    public void setAdapter(List<HostEntiti.DataBean> list) {
        refreshLayout.setRefreshing(false);
        alarmListAdapter.setNewData(list);
        for (HostEntiti.DataBean databean : list) {
            if (databean.getZhujiBaojingZhuangtai().equals("1")) {
                ivDianhua.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCheckChange(RadioGroup radioGroup, @IdRes int checkedId, String mac) {
        KLog.i("点击改变的回调");
        Map<String, String> map = new HashMap<>();
        map.put("mac", mac);
        map.put("uuid", Contains.uuid);
        switch (checkedId) {
            case R.id.button11:     //0撤防，1布防，2留守布防
                map.put("type", "0");
                mPresenter.buCheFang(map);
                break;
            case R.id.button12:
                map.put("type", "1");
                mPresenter.buCheFang(map);
                break;
            case R.id.button13:
                map.put("type", "2");
                mPresenter.buCheFang(map);
                break;
//            case R.id.button21:
//                break;
//            case R.id.button22:
//                break;
            default:
                break;
        }
    }

    @OnClick(R.id.iv_dianhua)
    public void onViewClicked() {
        showDianHua();
    }

    private void showDianHua() {
        AlertDialog alerDialog = new AlertDialog.Builder(getActivity()).create();
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_phone, null, false);
        TextView wuye = (TextView) view.findViewById(R.id.wuye);
        TextView baojing = (TextView) view.findViewById(R.id.baojing);
        wuye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone(Contains.appYezhuFangwus.get(Contains.curFangwu).getXiangmuTelphone());
                alerDialog.cancel();
            }
        });
        baojing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone("110");
                alerDialog.cancel();
            }
        });
        alerDialog.setView(view);
        alerDialog.show();
    }

    private void callPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        startActivity(intent);
    }
}
