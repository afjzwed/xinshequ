package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerTwoComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.TwoContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.TwoModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.TwoPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 09:44:57
 */

public class TwoFragment extends BaseFragment implements TwoContract.View {

    @Inject
    TwoPresenter mPresenter;

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_two, null);
       ButterKnife.bind(this, view);
       Bundle mBundle = getArguments();
       return view;
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

}