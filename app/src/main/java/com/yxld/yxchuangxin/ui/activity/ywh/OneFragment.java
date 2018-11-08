package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerOneComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.OneContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.OneModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.OnePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 08:54:54
 */

public class OneFragment extends BaseFragment implements OneContract.View {

    @Inject
    OnePresenter mPresenter;

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_one, null);
       ButterKnife.bind(this, view);
       Bundle mBundle = getArguments();
       return view;
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

}