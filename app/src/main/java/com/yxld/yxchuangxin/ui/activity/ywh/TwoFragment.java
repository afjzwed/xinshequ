package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerTwoComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.TwoContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.TwoModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.TwoPresenter;
import com.zhy.autolayout.AutoLinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 09:44:57
 */

public class TwoFragment extends BaseFragment implements TwoContract.View {

    @Inject
    TwoPresenter mPresenter;
    @BindView(R.id.tv_status) TextView tvStatus;
    @BindView(R.id.ll_status1) AutoLinearLayout llStatus1;
    @BindView(R.id.ll_status2) AutoLinearLayout llStatus2;
    @BindView(R.id.tv_details) TextView tvDetails;
    @BindView(R.id.tv_step) TextView tvStep;
    @BindView(R.id.img_step) ImageView imgStep;
    @BindView(R.id.tv_tjcy) TextView tvTjcy;
    private int type = 0;//模拟页面状态


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        initStatusView(type);
        Log.e("wh", "TwoFragment");
        return view;
    }

    private void initStatusView(int type) {
        if (type == 0) {
            llStatus1.setVisibility(View.VISIBLE);
            llStatus2.setVisibility(View.GONE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_ff9e04));
            tvStatus.setText("筹备组成立阶段-未开始");
        } else if (type == 1) {
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
            tvStatus.setText("筹备组成立阶段-进行中");
            tvStep.setText("已启动推荐组成员推荐程序");
            tvDetails.setVisibility(View.VISIBLE);
            tvDetails.setText("请在2018-9-12之前完成筹备组成员推荐程序");
            tvTjcy.setText("推荐筹备组成员");
        } else if (type == 2) {
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
            tvStatus.setText("筹备组成立阶段-进行中");
            imgStep.setImageResource(R.mipmap.ic_ywh_start2);
            tvStep.setText("筹备组成员公示");
            tvDetails.setVisibility(View.VISIBLE);
            tvDetails.setText("读取通知读取通知读取通知读取通知读取通知读取通知读取通知读取通知读取通知");
            tvTjcy.setText("查看筹备组成员公示信息");
        } else {
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_00b404));
            tvStatus.setText("筹备组成立阶段-已完成");
            imgStep.setImageResource(R.mipmap.ic_ywh_start3);
            tvStep.setText("筹备组成员");
            tvDetails.setVisibility(View.GONE);
            tvTjcy.setText("查看筹备组成员名单");
        }
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerTwoComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .twoModule(new TwoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(TwoContract.TwoContractPresenter presenter) {
        mPresenter = (TwoPresenter) presenter;
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
        progressDialog.hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    @OnClick({R.id.tv_status, R.id.ll_tjcy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_status:
                if (type == 0) {
                    type = 1;
                } else if (type == 1) {
                    type = 2;
                } else if (type == 2) {
                    type = 3;
                } else {
                    type = 0;
                }
                initStatusView(type);
                break;
            case R.id.ll_tjcy:
                if (type == 0) {
                } else if (type == 1) {
                    startActivity(TuiJianListActivity.class);//推荐成员
                } else if (type == 2) {
                    startActivity(CheckNoticeActivity.class);//查看通知
                } else {
                    startActivity(CymdActivity.class);//成员名单公示
                }
                initStatusView(type);
                break;
        }
    }
}