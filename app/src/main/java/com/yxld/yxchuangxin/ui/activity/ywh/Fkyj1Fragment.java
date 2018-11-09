package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerFkyj1Component;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.Fkyj1Contract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.Fkyj1Module;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.Fkyj1Presenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/09 13:26:28
 */

public class Fkyj1Fragment extends BaseFragment implements Fkyj1Contract.View {

    @Inject
    Fkyj1Presenter mPresenter;

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_fkyj1, null);
       ButterKnife.bind(this, view);
       Bundle mBundle = getArguments();
       return view;
   }
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        KLog.e("setUserVisibleHint-----------------" + isVisibleToUser);
//        if (isVisibleToUser) {
//            // 相当于onResume()方法
//        } else {
//            //切换fragment 隐藏软键盘
//            if (etName!=null) {
//                InputMethodManager imm = (InputMethodManager) etName.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//
//                if (imm.isActive()) {
//                    imm.hideSoftInputFromWindow(etName.getApplicationWindowToken(), 0);
//                }
//            }
//
//        }
//    }

    @Override
    protected void setupFragmentComponent() {
       DaggerFkyj1Component
               .builder()
               .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
               .fkyj1Module(new Fkyj1Module(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(Fkyj1Contract.Fkyj1ContractPresenter presenter) {
        mPresenter = (Fkyj1Presenter) presenter;
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