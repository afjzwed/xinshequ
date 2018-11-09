package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerSixthComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.SixthContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.SixthModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.SixthPresenter;
import com.zhy.autolayout.AutoLinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 15:56:44
 */

public class SixthFragment extends BaseFragment implements SixthContract.View {

    @Inject
    SixthPresenter mPresenter;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.tv_content_head)
    TextView tvContentHead;
    @BindView(R.id.tv_content_foot)
    TextView tvContentFoot;
    @BindView(R.id.autoll_data0)
    AutoLinearLayout autollData0;
    @BindView(R.id.tv_click_name1)
    TextView tvClickName1;
    @BindView(R.id.tv_click_name2)
    TextView tvClickName2;
    @BindView(R.id.tv_click_name3)
    TextView tvClickName3;
    @BindView(R.id.autoll_data1)
    AutoLinearLayout autollData1;
    @BindView(R.id.autoll_data2)
    AutoLinearLayout autollData2;

    private int status = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ywh_sixth, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();

        initData();
        return view;
    }

    private void initData() {
        mPresenter.getSixthData();
    }

    @Override
    public void setSixthData() {
        switch (status) {
            case 0:
                ivNoData.setVisibility(View.VISIBLE);
                autollData0.setVisibility(View.GONE);
                autollData1.setVisibility(View.GONE);
                autollData2.setVisibility(View.GONE);
                tvStatus.setText("备案阶段-进行中");
                tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
                break;
            case 1:
                ivNoData.setVisibility(View.GONE);
                autollData0.setVisibility(View.VISIBLE);
                autollData1.setVisibility(View.VISIBLE);
                autollData2.setVisibility(View.VISIBLE);
                tvStatus.setText("备案阶段-已完成");
                tvStatus.setTextColor(getResources().getColor(R.color.color_00b404));
                break;
        }
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerSixthComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .sixthModule(new SixthModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(SixthContract.SixthContractPresenter presenter) {
        mPresenter = (SixthPresenter) presenter;
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

    @OnClick({R.id.tv_status, R.id.tv_click_name1, R.id.tv_click_name2, R.id.tv_click_name3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_status:
                if (status == 0) {
                    status = 1;
                } else {
                    status = 0;
                }
                setSixthData();
                break;
            case R.id.tv_click_name1:
                Toast.makeText(getActivity(), "点击1", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), YwhMemberShowActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_click_name2:
                Toast.makeText(getActivity(), "点击2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_click_name3:
                Toast.makeText(getActivity(), "点击3", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}