package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerFourthComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.FourthContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.FourthModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.FourthPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 11:20:19
 */

public class FourthFragment extends BaseFragment implements FourthContract.View {

    @Inject
    FourthPresenter mPresenter;

    private int status = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ywh_fourth, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();

        initLoacView();
        initData();
        return view;
    }

    private void initData() {
        mPresenter.getFourthData();
    }

    private void initLoacView() {

    }

    @Override
    public void setFourthData() {
        //获取数据
        switch (status) {
            case 0:

                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerFourthComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .fourthModule(new FourthModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FourthContract.FourthContractPresenter presenter) {
        mPresenter = (FourthPresenter) presenter;
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
}