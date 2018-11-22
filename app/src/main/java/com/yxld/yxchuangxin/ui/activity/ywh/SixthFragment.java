package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
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
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.YwhCurrentflow;
import com.yxld.yxchuangxin.entity.YwhInfo;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerSixthComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.SixthContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.SixthModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.SixthPresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.YwhAccessoryAdapter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class SixthFragment extends BaseYwhFragment implements SixthContract.View {

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

    private YwhAccessoryAdapter ywhAccessoryAdapter;
    private int status = 0;//当前状态
    private int skip = 0;//控制页面跳转
    private YwhInfo ywhInfo;//业委会信息

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
                YwhCurrentflow.DataBean.FlowBean.FilesBean filesBean = ywhAccessoryAdapter.getData().get(position);
                if (filesBean != null) {
                    Intent intent = new Intent(getActivity(), YwhWebViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("address", filesBean.getUrl());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
//                Toast.makeText(getActivity(), "点击" + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(ywhAccessoryAdapter);//绑定适配器
        recyclerView.setFocusable(false);
        Log.e("wh", "SixthFragment");
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
        initData();
    }

    private void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("type", "6");
        mPresenter.getSixthData(map);
    }

    @Override
    public void setSixthData(YwhInfo baseEntity) {
        if (baseEntity.isSuccess()) {
            ywhInfo = baseEntity;
            status = ywhInfo.getData().getFlow().getPhaseState();
            initStatusView();
        } else {
            onError(baseEntity.msg);
            status = -1;
            initStatusView();
        }
    }

    private void initStatusView() {
        switch (status) {
            case -1:
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
                //从beianInfo和files和beianPeoples里取
                ivNoData.setVisibility(View.GONE);
                llNoData.setVisibility(View.GONE);
                autollData0.setVisibility(View.VISIBLE);
                autollData1.setVisibility(View.VISIBLE);
                autollData2.setVisibility(View.VISIBLE);
                if (null!=ywhInfo.getData().getFlow().getBeianInfo()) {
                    tvContentHead.setText("备案名称：" + ywhInfo.getData().getFlow().getBeianInfo().getBeianmingc());
                    tvContentFoot.setText("备案时间：" + ywhInfo.getData().getFlow().getBeianInfo().getBeianTime());
                }
                tvStatus.setText("备案阶段-已完成");
                tvStatus.setTextColor(getResources().getColor(R.color.color_00b404));

                List<YwhCurrentflow.DataBean.FlowBean.FilesBean> list = (List<YwhCurrentflow.DataBean.FlowBean
                        .FilesBean>) ywhInfo.getData().getFlow().getFiles();
                if (list != null && list.size() > 0) {
                    ywhAccessoryAdapter.setNewData(list);
                } else {
                    autollData2.setVisibility(View.GONE);
                }
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

    @OnClick({R.id.tv_click_name1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_click_name1:
//                Toast.makeText(getActivity(), "点击1", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), YwhBeianMemberActivity.class);//这里传备案人员名单列表beianPeoples
//                intent.putExtra("isYjfk", 1);
//                startActivity(intent);
                if (ywhInfo.getData().getFlow().getBeianPeoples() != null && ywhInfo.getData().getFlow()
                        .getBeianPeoples().size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("ywh_beian_list", (ArrayList<? extends Parcelable>) ywhInfo.getData()
                            .getFlow().getBeianPeoples());
                    startActivity(YwhBeianMemberActivity.class, bundle);//成员名单公示
                } else {
                    Toast.makeText(getActivity(), "没有成员名单", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}