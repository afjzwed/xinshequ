package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerOneComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.OneContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.OneModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.OnePresenter;
import com.zhy.autolayout.AutoLinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 08:54:54
 */

public class OneFragment extends BaseFragment implements OneContract.View {

    @Inject
    OnePresenter mPresenter;
    @BindView(R.id.tv_status) TextView tvStatus;
    @BindView(R.id.ll_status1) AutoLinearLayout llStatus1;
    @BindView(R.id.ll_status2) AutoLinearLayout llStatus2;
    @BindView(R.id.tv_tjcy_content) TextView tvTjcyContent;
    @BindView(R.id.tv_details) TextView tvDetails;
    private int type = 0;//模拟页面状态

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        initStatusView(type);
        Log.e("wh", "OneFragment");
        return view;
    }

    private void initStatusView(int type) {
        if (type == 0) {
            llStatus1.setVisibility(View.VISIBLE);
            llStatus2.setVisibility(View.GONE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_ff9e04));
            tvStatus.setText("开始成立阶段-未开始");
            tvTjcyContent.setText("当前小区暂不具备成立业委会条件");
        } else if (type == 1) {
            llStatus1.setVisibility(View.VISIBLE);
            llStatus2.setVisibility(View.GONE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_ff9e04));
            tvStatus.setText("开始成立阶段-未开始");
            tvTjcyContent.setText("当前小区已具备成立业委会条件\n您可以向街道申请成立业委会");
        } else if (type == 2) {
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_00b404));
            tvStatus.setText("开始成立阶段-已完成");
            tvDetails.setText("请在2018-9-12之前完成筹备组成员推荐程序");
        }
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerOneComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .oneModule(new OneModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(OneContract.OneContractPresenter presenter) {
        mPresenter = (OnePresenter) presenter;
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

    @OnClick({R.id.tv_tijian, R.id.ll_tjcy, R.id.tv_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_tijian:

                startActivity(YwhRequestActivity.class);
                break;
            case R.id.ll_tjcy:
                startActivity(TuiJianListActivity.class);
                break;
            case R.id.tv_status:
                if (type == 0) {
                    type = 1;
                } else if (type == 1) {
                    type = 2;
                } else {
                    type = 0;
                }
                initStatusView(type);
                break;
        }
    }
}