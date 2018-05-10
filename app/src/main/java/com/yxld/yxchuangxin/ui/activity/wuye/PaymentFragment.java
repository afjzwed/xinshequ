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
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerPaymentComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.PaymentContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.PaymentModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.PaymentPresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description  缴费明细列表
 * @date 2017/07/01 13:45:55
 */

public class PaymentFragment extends BaseFragment implements PaymentContract.View {

    @Inject
    PaymentPresenter mPresenter;
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
        DaggerPaymentComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .paymentModule(new PaymentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(PaymentContract.PaymentContractPresenter presenter) {
        mPresenter = (PaymentPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {
//        Map<String, String> parm = new HashMap<String, String>();
//        parm.put("i", "1");
//        parm.put("loupan", Contains.appYezhuFangwus.get(0).getXiangmuLoupan());
//        parm.put("loudong", Contains.appYezhuFangwus.get(0).getFwLoudong());
//        parm.put("danyuan", Contains.appYezhuFangwus.get(0).getFwDanyuan());
//        parm.put("fanghao", Contains.appYezhuFangwus.get(0).getFwFanghao() + "");
//        mPresenter.getList(parm);
        Map<String, String> map = new HashMap<String, String>();
        map.put("fwId", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwId() + "");
        map.put("uuid", Contains.uuid);
        mPresenter.getJiaofei(map);
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
    public void onDestroyView() {
        mPresenter.unsubscribe();
        super.onDestroyView();
    }
}