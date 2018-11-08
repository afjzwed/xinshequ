package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerFivethComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.FivethContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.FivethModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.FivethPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 14:11:35
 */

public class FivethFragment extends BaseFragment implements FivethContract.View {

    @Inject
    FivethPresenter mPresenter;

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_ywh_fiveth, null);
       ButterKnife.bind(this, view);
       Bundle mBundle = getArguments();
       return view;
   }


    @Override
    protected void setupFragmentComponent() {
       DaggerFivethComponent
               .builder()
               .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
               .fivethModule(new FivethModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(FivethContract.FivethContractPresenter presenter) {
        mPresenter = (FivethPresenter) presenter;
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

}