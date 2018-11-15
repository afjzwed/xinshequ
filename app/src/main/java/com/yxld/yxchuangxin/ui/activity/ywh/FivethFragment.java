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
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.YwhCurrentflow;
import com.yxld.yxchuangxin.entity.YwhInfo;
import com.yxld.yxchuangxin.ui.activity.main.WebviewActivity;
import com.yxld.yxchuangxin.ui.activity.rim.WebViewActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.OpinionSurveyActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerFivethComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.FivethContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.FivethModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.FivethPresenter;
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
 * @date 2018/11/08 14:11:35
 */

public class FivethFragment extends BaseYwhFragment implements FivethContract.View {

    @Inject
    FivethPresenter mPresenter;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.tv_content_head)
    TextView tvContentHead;
    @BindView(R.id.autoll_data0)
    AutoLinearLayout autollData0;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time1)
    TextView tvTime1;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_click_name1)
    TextView tvClickName1;
    @BindView(R.id.tv_click_name2)
    TextView tvClickName2;
    @BindView(R.id.auto_click)
    AutoLinearLayout autoClick;
    @BindView(R.id.autoll_data1)
    AutoLinearLayout autollData1;
    @BindView(R.id.autoll_data2)
    AutoLinearLayout autollData2;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
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
        View view = inflater.inflate(R.layout.fragment_ywh_fiveth, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);
        ywhAccessoryAdapter = new YwhAccessoryAdapter();
        ywhAccessoryAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                YwhCurrentflow.DataBean.FlowBean.FilesBean filesBean = ywhAccessoryAdapter.getData().get(position);

//                Intent intent = new Intent(getActivity(), WebviewActivity.class);
//                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                Intent intent = new Intent(getActivity(), YwhWebViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("address", filesBean.getUrl());
                intent.putExtras(bundle);
                startActivity(intent);

                Toast.makeText(getActivity(), "点击" + position + " " + filesBean.getUrl(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(ywhAccessoryAdapter);//绑定适配器
        recyclerView.setFocusable(false);
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
        map.put("type", "5");
        mPresenter.getFivethData(map);
    }

    @Override
    public void setFivethData(YwhInfo baseEntity) {
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
                autollData0.setVisibility(View.GONE);
                autollData1.setVisibility(View.GONE);
                autoClick.setVisibility(View.GONE);
                autollData2.setVisibility(View.GONE);
                tvStatus.setText("业主大会阶段-未开始");
                tvStatus.setTextColor(getResources().getColor(R.color.color_ff9e04));
                skip = 0;
                break;
            case 1:
                if (ywhInfo.getData().getFlow().getGongshi() == null && ywhInfo.getData().getFlow().getVoteVo() !=
                        null) {//公示为null voteVo不为null
                    //根据voteVo类来判断投票方式
                    if (ywhInfo.getData().getFlow().getVoteVo().getVoteType() == 1) {//线上
                        ivNoData.setVisibility(View.GONE);
                        autollData0.setVisibility(View.GONE);
                        autollData1.setVisibility(View.VISIBLE);
                        autoClick.setVisibility(View.VISIBLE);
                        autollData2.setVisibility(View.GONE);
                        tvStatus.setText("业主大会阶段-进行中");
                        tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
                        ivImg.setImageResource(R.mipmap.ic_ywh_vote);
                        tvTitle.setText("线上投票");
                        tvTime1.setVisibility(View.VISIBLE);
                        tvTime1.setText(ywhInfo.getData().getFlow().getVoteVo().getStartTime() + "至" + ywhInfo
                                .getData().getFlow().getVoteVo().getEndTime());
                        tvContent.setVisibility(View.VISIBLE);
                        tvContent.setText(ywhInfo.getData().getFlow().getVoteVo().getContent());
                        line.setVisibility(View.GONE);
                        autoClick.setBackgroundColor(getResources().getColor(R.color.color_ffefea));
                        tvClickName1.setText("进入投票");
                        tvClickName1.setTextColor(getResources().getColor(R.color.color_ea3006));
                        if (ywhInfo.getData().getFlow().getVoteVo().getIsVote() == -1) {
                            tvClickName2.setText("未投票");
                        } else if (ywhInfo.getData().getFlow().getVoteVo().getIsVote() == 1) {
                            tvClickName2.setText("已投票");
                        }
                        tvClickName2.setVisibility(View.VISIBLE);
                        tvClickName2.setTextColor(getResources().getColor(R.color.color_ea3006));
                        ivArrow.setImageResource(R.mipmap.ic_jt_red);
                    } else if (ywhInfo.getData().getFlow().getVoteVo().getVoteType() == 2) {//线下
                        ivNoData.setVisibility(View.GONE);
                        autollData0.setVisibility(View.VISIBLE);
                        autollData1.setVisibility(View.GONE);
                        autoClick.setVisibility(View.GONE);
                        autollData2.setVisibility(View.GONE);
                        tvStatus.setText("业主大会阶段-进行中");
                        tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
                        ivImg.setImageResource(R.mipmap.ic_ywh_vote);
                        tvTitle.setText("线上投票");
                        tvTime1.setVisibility(View.VISIBLE);
                        tvTime1.setText("至");
                        tvContent.setVisibility(View.VISIBLE);
                        tvContent.setText("");
                        line.setVisibility(View.GONE);
                        autoClick.setBackgroundColor(getResources().getColor(R.color.color_ffefea));
                        tvClickName1.setText("进入投票");
                        tvClickName1.setTextColor(getResources().getColor(R.color.color_ea3006));
                        tvClickName2.setText("未投票");
                        tvClickName2.setVisibility(View.VISIBLE);
                        tvClickName2.setTextColor(getResources().getColor(R.color.color_ea3006));
                        ivArrow.setImageResource(R.mipmap.ic_jt_red);
                    }
                    skip = 1;
                } else if (ywhInfo.getData().getFlow().getGongshi() != null && ywhInfo.getData().getFlow().getGongshi
                        ().getGongshiType() == 4) {//公示不为null
                    ivNoData.setVisibility(View.GONE);
                    autollData0.setVisibility(View.GONE);
                    autollData1.setVisibility(View.VISIBLE);
                    autoClick.setVisibility(View.VISIBLE);
                    autollData2.setVisibility(View.GONE);
                    tvStatus.setText("业主大会阶段-进行中");
                    tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
                    ivImg.setImageResource(R.mipmap.ic_ywh_material);
                    tvTitle.setText("业主大会结果公示");
                    tvTime1.setText(ywhInfo.getData().getFlow().getGongshi().getStarttime() + "");//starttime
                    tvContent.setVisibility(View.VISIBLE);
                    tvContent.setText(ywhInfo.getData().getFlow().getGongshi().getTitle() + "");
                    autoClick.setBackgroundColor(getResources().getColor(R.color.white));
                    tvClickName1.setText("查看业主大会结果公示");
                    tvClickName1.setTextColor(getResources().getColor(R.color.color_2d97ff));
                    tvClickName2.setVisibility(View.GONE);
                    line.setVisibility(View.VISIBLE);
                    ivArrow.setImageResource(R.mipmap.ic_jt_blue);
                    skip = 2;
                }
                break;
            case 2:
                ivNoData.setVisibility(View.GONE);
                autollData0.setVisibility(View.GONE);
                autollData1.setVisibility(View.VISIBLE);
                autoClick.setVisibility(View.VISIBLE);
                autollData2.setVisibility(View.VISIBLE);
                tvStatus.setText("业主大会阶段-已完成");
                tvStatus.setTextColor(getResources().getColor(R.color.color_00b404));
                ivImg.setImageResource(R.mipmap.ic_ywh_start3);
                tvTitle.setText("业主委员会成员已确定");
                tvContent.setVisibility(View.GONE);
                tvClickName1.setText("查看业主委员会成员信息");
                List<YwhCurrentflow.DataBean.FlowBean.FilesBean> list = (List<YwhCurrentflow.DataBean.FlowBean
                        .FilesBean>) ywhInfo.getData().getFlow().getFiles();
                if (list != null && list.size() > 0) {
                    ywhAccessoryAdapter.setNewData(list);
                } else {
                    autollData2.setVisibility(View.GONE);
                }
                skip = 3;
                break;
        }
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerFivethComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .fivethModule(new FivethModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FivethContract.FivethContractPresenter presenter) {
        mPresenter = (FivethPresenter) presenter;
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

    @OnClick({R.id.tv_status, R.id.auto_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_status:
//                if (status == 0) {
//                    status = 1;
//                } else if (status == 1) {
//                    status = 2;
//                } else if (status == 2) {
//                    status = 3;
//                } else {
//                    status = 0;
//                }
//                setFivethData(null);
                break;
            case R.id.auto_click:
                Intent intent;
                switch (skip) {
                    case 1:
                        if (tvClickName2.getText().toString().equals("已投票")) {
                            Toast.makeText(getActivity(), "已投票", Toast.LENGTH_SHORT).show();
                        } else if (tvClickName2.getText().toString().equals("未投票")) {
                            intent = new Intent(getActivity(), WebviewActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("name", "投票");
                            bundle.putString("address", "http://192.168.8.130:8020/research/index.html?uid=" + Contains
                                    .uuid);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        break;
                    case 2:
//                        intent = new Intent(getActivity(), ResultShowActivity.class);//传公示 和 附件fileurl
//                        startActivity(intent);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("ywh_gongshi", ywhInfo.getData().getFlow().getGongshi());//公示
                        bundle.putParcelableArrayList("ywh_member_list", (ArrayList<? extends Parcelable>) ywhInfo.getData()
                                .getFlow().getFiles());
                        startActivity(ResultShowActivity.class, bundle);//成员名单公示
                        break;
                    case 3:
                        intent = new Intent(getActivity(), YwhMemberShowActivity.class);
                        intent.putExtra("isYjfk", 1);
                        startActivity(intent);
                        break;
                }
                break;
        }
    }
}