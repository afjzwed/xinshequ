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
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerThirdComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.ThirdContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.ThirdModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.ThirdPresenter;
import com.zhy.autolayout.AutoLinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 09:53:49
 */

public class ThirdFragment extends BaseFragment implements ThirdContract.View {

    @Inject
    ThirdPresenter mPresenter;
    @BindView(R.id.tv_status) TextView tvStatus;
    @BindView(R.id.ll_status1) AutoLinearLayout llStatus1;
     AutoLinearLayout ll_ywh;
     AutoLinearLayout ll_ywh2;
    @BindView(R.id.ll_status2) AutoLinearLayout llStatus2;
    @BindView(R.id.ll_status3) AutoLinearLayout llStatus3;
    TextView tvDetails;
    TextView tvStep;
    TextView tvShzt;
    ImageView imgStep;
    TextView tvTjcy;
    AutoLinearLayout llTjcy;
    AutoLinearLayout llTjcy1;
    TextView tvDetails1;
    TextView tvStep1;
    TextView tvShzt1;
    ImageView imgStep1;
    TextView tvTjcy1;
    private int type = 0;//模拟页面状态


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        initIncludeView(view);
        initStatusView(type);
        Log.e("wh", "ThirdFragment");
        return view;
    }

    private void initIncludeView(View view) {
        ll_ywh = view.findViewById(R.id.ll_ywh);
        llTjcy = ll_ywh.findViewById(R.id.ll_tjcy);
        tvDetails = ll_ywh.findViewById(R.id.tv_details);
        tvStep = ll_ywh.findViewById(R.id.tv_step);
        tvShzt = ll_ywh.findViewById(R.id.tv_shzt);
        imgStep = ll_ywh.findViewById(R.id.img_step);
        tvTjcy = ll_ywh.findViewById(R.id.tv_tjcy);
        ll_ywh2 = view.findViewById(R.id.ll_ywh2);
        tvDetails1 = ll_ywh2.findViewById(R.id.tv_details);
        tvStep1 = ll_ywh2.findViewById(R.id.tv_step);
        tvShzt1 = ll_ywh2.findViewById(R.id.tv_shzt);
        imgStep1 = ll_ywh2.findViewById(R.id.img_step);
        tvTjcy1 = ll_ywh2.findViewById(R.id.tv_tjcy);
        llTjcy1 = ll_ywh2.findViewById(R.id.ll_tjcy);

    }

    private void initStatusView(int type) {
        if (type == 0) {
            llStatus1.setVisibility(View.VISIBLE);
            llStatus2.setVisibility(View.GONE);
            llStatus3.setVisibility(View.GONE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_ff9e04));
            tvStatus.setText("筹备组工作阶段-未开始");
        } else if (type == 1) {
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            llStatus3.setVisibility(View.GONE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
            tvStatus.setText("筹备组工作阶段-进行中");
            imgStep.setImageResource(R.mipmap.ic_ywh_start4);
            tvStep.setText("请及时领取票权");
            tvDetails.setText("请在2018-9-12之前完成实名认证以领取票权！每个业主仅能领取一张票权，未领取票权的业主将无法参与业主大会投票。");
            tvTjcy.setText("立即领取票权");
            llTjcy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(PqrzActivity.class);
                }
            });
            tvShzt.setVisibility(View.VISIBLE);
            tvShzt.setText("暂未提交资料");
        } else if (type == 2) {
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            llStatus3.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
            tvStatus.setText("筹备组工作阶段-进行中");
            imgStep.setImageResource(R.mipmap.ic_ywh_start4);
            tvStep.setText("请及时领取票权");
            tvDetails.setText("审核失败的审核失败的审核失败的审核失败的审核失败的");
            tvTjcy.setText("查看");
            tvShzt.setVisibility(View.VISIBLE);
            tvShzt.setText("审核失败");
            llTjcy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(PqrzResultActivity.class);
                }
            });
            imgStep1.setImageResource(R.mipmap.ic_ywh_start5);
            tvStep1.setText("关于业主大会筹备文件公示的通知");
            tvDetails1.setText("审核失败的审核失败的审核失败的审核失败的审核失败的");
            tvTjcy1.setText("查看业主大会筹备文件");
            llTjcy1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(CheckNoticeActivity.class);
                }
            });
        } else {
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            llStatus3.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_00b404));
            tvStatus.setText("筹备组工作阶段-已完成");
            imgStep.setImageResource(R.mipmap.ic_ywh_start4);
            tvStep.setText("筹备工作已开始，请及时领取票权");
            tvDetails.setText("恭喜您成功领取票权");
            tvTjcy.setText("查看");
            tvShzt.setVisibility(View.VISIBLE);
            tvShzt.setText("审核成功");
            llTjcy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(PqrzResultActivity.class);
                }
            });
            imgStep1.setImageResource(R.mipmap.ic_ywh_start5);
            tvStep1.setText("业主大会及业主委员会相关筹备文件公示通知");
            tvDetails1.setText("审核失败的审核失败的审核失败的审核失败的审核失败的");
            tvTjcy1.setText("业主大会及业主委员会相关筹备文件公示");
            llTjcy1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(CheckNoticeActivity.class);
                }
            });
        }
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerThirdComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .thirdModule(new ThirdModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(ThirdContract.ThirdContractPresenter presenter) {
        mPresenter = (ThirdPresenter) presenter;
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
    @OnClick(R.id.tv_status)
    public void onViewClicked() {
        if (type == 0) {
            type = 1;
        } else if (type == 1) {
            type = 2;
        } else if (type==2){
            type = 3;
        }else {
            type = 0;
        }
        initStatusView(type);
    }
}