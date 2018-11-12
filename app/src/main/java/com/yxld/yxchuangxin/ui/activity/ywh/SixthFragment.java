package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerSixthComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.SixthContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.SixthModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.SixthPresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.YwhAccessoryAdapter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 15:56:44
 */

public class SixthFragment extends BaseFragment implements SixthContract.View {

    @Inject
    SixthPresenter mPresenter;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.ll_no_data)
    AutoLinearLayout llNoData;
    @BindView(R.id.tv_content_head)
    TextView tvContentHead;
    @BindView(R.id.tv_content_foot)
    TextView tvContentFoot;
    @BindView(R.id.autoll_data0)
    AutoLinearLayout autollData0;
    @BindView(R.id.tv_click_name1)
    TextView tvClickName1;
    @BindView(R.id.autoll_data1)
    AutoLinearLayout autollData1;
    @BindView(R.id.autoll_data2)
    AutoLinearLayout autollData2;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private int status = 0;
    private YwhAccessoryAdapter ywhAccessoryAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ywh_sixth, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);
        ywhAccessoryAdapter = new YwhAccessoryAdapter();
        ywhAccessoryAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
//                ywhAccessoryAdapter.getData().get(position);
                Toast.makeText(getActivity(), "点击" + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(ywhAccessoryAdapter);//绑定适配器

        initData();
        return view;
    }

    private void initData() {
        Log.e("wh", "SixthFragment");

//        mPresenter.getSixthData();
        setSixthData(null);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("1");
        }
        ywhAccessoryAdapter.setNewData(list);
    }

    @Override
    public void setSixthData(BaseEntity baseEntity) {
        switch (status) {
            case 0:
                ivNoData.setVisibility(View.VISIBLE);
                llNoData.setVisibility(View.GONE);
                autollData0.setVisibility(View.GONE);
                autollData1.setVisibility(View.GONE);
                autollData2.setVisibility(View.GONE);
                tvStatus.setText("备案阶段-未开始");
                tvStatus.setTextColor(getResources().getColor(R.color.color_ff9e04));
                break;
            case 1:
                ivNoData.setVisibility(View.GONE);
                llNoData.setVisibility(View.VISIBLE);
                autollData0.setVisibility(View.GONE);
                autollData1.setVisibility(View.GONE);
                autollData2.setVisibility(View.GONE);
                tvStatus.setText("备案阶段-进行中");
                tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
                break;
            case 2:
                ivNoData.setVisibility(View.GONE);
                llNoData.setVisibility(View.GONE);
                autollData0.setVisibility(View.VISIBLE);
                autollData1.setVisibility(View.VISIBLE);
                autollData2.setVisibility(View.VISIBLE);
                tvStatus.setText("备案阶段-已完成");
                tvStatus.setTextColor(getResources().getColor(R.color.color_00b404));
                break;
        }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.tv_status, R.id.tv_click_name1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_status:
                if (status == 0) {
                    status = 1;
                } else if (status == 1) {
                    status = 2;
                } else {
                    status = 0;
                }
                setSixthData(null);
                break;
            case R.id.tv_click_name1:
                Toast.makeText(getActivity(), "点击1", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), YwhMemberShowActivity.class);
                startActivity(intent);
                break;
//            case R.id.tv_click_name2:
//                Toast.makeText(getActivity(), "点击2", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.tv_click_name3:
//                Toast.makeText(getActivity(), "点击3", Toast.LENGTH_SHORT).show();
//                break;
        }
    }
}