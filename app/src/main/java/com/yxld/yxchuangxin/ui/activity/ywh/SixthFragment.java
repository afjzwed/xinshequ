package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerSixthComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.SixthContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.SixthModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.SixthPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 15:56:44
 */

public class SixthFragment extends BaseFragment implements SixthContract.View {

    @Inject
    SixthPresenter mPresenter;

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_ywh_sixth, null);
       ButterKnife.bind(this, view);
       Bundle mBundle = getArguments();
       return view;
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

}