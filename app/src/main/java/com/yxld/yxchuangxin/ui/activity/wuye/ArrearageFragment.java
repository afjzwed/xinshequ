package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerArrearageComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.ArrearageContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.ArrearageModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.ArrearagePresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description  欠费明细列表
 * @date 2017/07/01 13:46:50
 */

public class ArrearageFragment extends BaseFragment implements ArrearageContract.View {

    @Inject
    ArrearagePresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        initDataFromLocal();
        return view;
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerArrearageComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .arrearageModule(new ArrearageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(ArrearageContract.ArrearageContractPresenter presenter) {
        mPresenter = (ArrearagePresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {
        Map<String, String> parm = new HashMap<String, String>();
//        parm.put("i", "2");
        parm.put("loupan", Contains.appYezhuFangwus.get(0).getXiangmuLoupan());
        parm.put("loudong", Contains.appYezhuFangwus.get(0).getFwLoudong());
        parm.put("danyuan", Contains.appYezhuFangwus.get(0).getFwDanyuan());
        parm.put("fanghao", Contains.appYezhuFangwus.get(0).getFwFanghao() + "");
//        mPresenter.getList(parm);
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
    public void setList() {

    }

    @Override
    public void setMoreList() {

    }
    @Override
    public void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}