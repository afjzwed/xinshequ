package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerFkyj2Component;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.Fkyj2Contract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.Fkyj2Module;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.Fkyj2Presenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.FkyjListAdapter;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/09 13:42:26
 */

public class Fkyj2Fragment extends BaseFragment implements Fkyj2Contract.View {

    @Inject
    Fkyj2Presenter mPresenter;
    @BindView(R.id.rv) RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fkyj2, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        initRv();
        return view;
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new FkyjListAdapter(Arrays.asList("","","")));
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerFkyj2Component
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .fkyj2Module(new Fkyj2Module(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(Fkyj2Contract.Fkyj2ContractPresenter presenter) {
        mPresenter = (Fkyj2Presenter) presenter;
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