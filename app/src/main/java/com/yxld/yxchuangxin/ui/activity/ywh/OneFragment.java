package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.YwhInfo;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerOneComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.OneContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.OneModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.OnePresenter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 08:54:54
 */

public class OneFragment extends BaseYwhFragment implements OneContract.View {

    @Inject
    OnePresenter mPresenter;
    @BindView(R.id.tv_status) TextView tvStatus;
    @BindView(R.id.ll_status1) AutoLinearLayout llStatus1;
    @BindView(R.id.ll_status2) AutoLinearLayout llStatus2;
    @BindView(R.id.tv_tjcy_content) TextView tvTjcyContent;
    @BindView(R.id.tv_details) TextView tvDetails;
    @BindView(R.id.tv_tijian) TextView tvTijian;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        Log.e("wh", "OneFragment");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        initDataFromLocal();
    }

    private boolean isload;

    @Override
    protected void initDataFromLocal() {
        if (!isViewCreated || !isUIVisible || isload) {
            return;
        }
        isload = true;
//        Log.e("wh", "OneFragment 加载数据");
        initData();
    }

    private void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("type", "1");
        mPresenter.getData(map);
    }

    private void initStatusView(YwhInfo ywhInfo) {

        if (ywhInfo.getData().getFlow().getPhaseState() == -1 && ywhInfo.getData().getFlow().getIsChengli() == -1) {
            llStatus1.setVisibility(View.VISIBLE);
            llStatus2.setVisibility(View.GONE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_ff9e04));
            tvStatus.setText("开始成立阶段-未开始");
            tvTjcyContent.setText("当前小区暂不具备成立业委会条件");
        } else if (ywhInfo.getData().getFlow().getPhaseState() == -1 && ywhInfo.getData().getFlow().getIsChengli() == 1) {
            llStatus1.setVisibility(View.VISIBLE);
            llStatus2.setVisibility(View.GONE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_ff9e04));
            tvStatus.setText("开始成立阶段-未开始");
            tvTjcyContent.setText("当前小区已具备成立业委会条件\n您可以向街道申请成立业委会");
        } else if (ywhInfo.getData().getFlow().getPhaseState() == 1) {
            llStatus1.setVisibility(View.VISIBLE);
            tvTijian.setVisibility(View.GONE);
            tvTjcyContent.setVisibility(View.GONE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
            tvStatus.setText("开始成立阶段-进行中");
        } else if (ywhInfo.getData().getFlow().getPhaseState() == 2) {
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_00b404));
            tvStatus.setText("开始成立阶段-已完成");
            tvDetails.setText(Html.fromHtml("请在" + "<font color=\"#ff9e04\">" + ywhInfo.getData().getFlow().getGongshi().getEndtime() + "</font>" +
                    "之前完成筹备组成员推荐程序"));
        }
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
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void getDataSuccess(YwhInfo baseEntity) {
        if (baseEntity.isSuccess()) {
            initStatusView(baseEntity);
        } else {
            onError(baseEntity.msg);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @OnClick({R.id.tv_tijian, R.id.ll_tjcy, R.id.tv_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_tijian:
                startActivity(YwhRequestActivity.class);
                break;
            case R.id.ll_tjcy:
                startActivity(TuiJianListActivity.class);
                break;
            case R.id.tv_status:
                break;
        }
    }
}