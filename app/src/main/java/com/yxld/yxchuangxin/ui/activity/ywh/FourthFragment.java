package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.YwhInfo;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerFourthComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.FourthContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.FourthModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.FourthPresenter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 11:20:19
 */

public class FourthFragment extends BaseYwhFragment implements FourthContract.View {

    @Inject
    FourthPresenter mPresenter;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content_head)
    TextView tvContentHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.autoll_data)
    AutoLinearLayout autollData;

    private int status = 0;//当前状态
    private int skip = 0;//控制页面跳转
    private YwhInfo ywhInfo;//业委会信息

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ywh_fourth, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        Log.e("wh", "FourthFragment");
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
        map.put("type", "4");
        mPresenter.getFourthData(map);
    }

    @Override
    public void setFourthData(YwhInfo baseEntity) {
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
        //获取数据
        switch (status) {
            case -1:
                autollData.setVisibility(View.GONE);
                ivNoData.setVisibility(View.VISIBLE);
                tvStatus.setText("候选人确定阶段-未开始");
                tvStatus.setTextColor(getResources().getColor(R.color.color_ff9e04));
                skip = 0;
                break;
            case 1:
                tvStatus.setText("候选人确定阶段-进行中");
                tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
                if (ywhInfo.getData().getFlow().getGongshi().getGongshiType() == 6) {
                    autollData.setVisibility(View.VISIBLE);
                    ivNoData.setVisibility(View.GONE);
                    tvContentHead.setText(Html.fromHtml("请在" + "<font color=\"#ff9e04\">" + ywhInfo.getData().getFlow
                            ().getGongshi().getEndtime() + "</font>" +
                            "之前完成推荐程序"));
                    tvContentHead.setVisibility(View.VISIBLE);
                    tvName.setText("推荐候选人成员");
                    ivImg.setImageResource(R.mipmap.ic_ywh_start);
                    skip = 1;
                } else if (ywhInfo.getData().getFlow().getGongshi().getGongshiType() == 3) {
                    //tvContentHead内容为YwhCurrentflow.DataBean.FlowBean.GongshiBeantitle
                    autollData.setVisibility(View.VISIBLE);
                    ivNoData.setVisibility(View.GONE);
                    ivImg.setImageResource(R.mipmap.ic_ywh_vote);
                    tvTitle.setText("候选人名单已公示");
                    tvContentHead.setText(ywhInfo.getData().getFlow().getGongshi().getTitle() + "");
                    tvContentHead.setVisibility(View.VISIBLE);
                    tvName.setText("查看候选人名单公示");
                    skip = 2;
                }
                break;
            case 2:
                autollData.setVisibility(View.VISIBLE);
                ivNoData.setVisibility(View.GONE);
                tvStatus.setText("候选人确定阶段-已完成");
                tvStatus.setTextColor(getResources().getColor(R.color.color_00b404));
                tvTitle.setText("候选人名单");
                tvName.setText("查看候选人名单");
                ivImg.setImageResource(R.mipmap.ic_ywh_start3);
                tvContentHead.setVisibility(View.GONE);
                skip = 3;
                break;
        }
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerFourthComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .fourthModule(new FourthModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FourthContract.FourthContractPresenter presenter) {
        mPresenter = (FourthPresenter) presenter;
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

    @OnClick({R.id.tv_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
//                Toast.makeText(getActivity(), "点击", Toast.LENGTH_SHORT).show();
                Intent intent;
                switch (skip) {
                    case 1:
                        //公示类型 6启动候选人推荐
                        if (null != ywhInfo.getData().getFlow().getGongshi()) {
                            intent = new Intent(getActivity(), RecommendMemberActivity.class);//传YwhCurrentflow.DataBean
                            // .FlowBean.GongshiBean
                            intent.putExtra("ywh_gongshi", ywhInfo.getData().getFlow().getGongshi());
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "没有公示", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case 2:
                        //公示类型 3:候选人名单公示
//                        intent = new Intent(getActivity(), CheckNoticeActivity.class);//查看通知 //传YwhCurrentflow
                        // .DataBean.FlowBean.GongshiBean 和 confirmPeople集合
//                        intent.putExtra("ywh_gongshi", ywhInfo.getData().getFlow().getGongshi());
//                        intent.putExtra("ywh_position", 3);
//                        startActivity(intent);
                        if (null != ywhInfo.getData().getFlow().getConfirmPeople() && ywhInfo.getData().getFlow()
                                .getConfirmPeople().size() > 0) {
                            Bundle bundle1 = new Bundle();
                            bundle1.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) ywhInfo.getData()
                                    .getFlow().getConfirmPeople());//人员列表
                            bundle1.putInt("ywh_position", 3);
                            bundle1.putParcelable("ywh_gongshi", ywhInfo.getData().getFlow().getGongshi());//公示
//                        bundle1.putParcelableArrayList("ywh_member_list", (ArrayList<? extends Parcelable>) ywhInfo
// .getData().getFlow().getFiles());//附件列表不传
                            bundle1.putInt("isYjfk", 0);
                            startActivity(CheckNoticeActivity.class, bundle1);//成员名单公示
                        } else {
                            Toast.makeText(getActivity(), "没有人员列表", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
//                        intent = new Intent(getActivity(), CymdActivity.class);//传 confirmPeople集合
                        if (ywhInfo.getData().getFlow().getConfirmPeople() != null && ywhInfo.getData()
                                .getFlow().getConfirmPeople().size() > 0) {
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) ywhInfo.getData()
                                    .getFlow().getConfirmPeople());
                            bundle.putInt("isYjfk", 1);
                            startActivity(CymdActivity.class, bundle);//成员名单公示
                        } else {
                            Toast.makeText(getActivity(), "没有成员列表", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                break;
        }
    }
}