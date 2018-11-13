package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.YwhInfo;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerThirdComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.ThirdContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.ThirdModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.ThirdPresenter;
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
 * @date 2018/11/08 09:53:49
 */

public class ThirdFragment extends BaseYwhFragment implements ThirdContract.View {

    @Inject
    ThirdPresenter mPresenter;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.ll_status1)
    AutoLinearLayout llStatus1;
    AutoLinearLayout ll_ywh;
    AutoLinearLayout ll_ywh2;
    @BindView(R.id.ll_status2)
    AutoLinearLayout llStatus2;
    @BindView(R.id.ll_status3)
    AutoLinearLayout llStatus3;
    TextView tvDetails;
    TextView tvStep;
    TextView tvShzt;
    ImageView imgStep;
    TextView tvTjcy;
    AutoLinearLayout llTjcy;
    AutoLinearLayout llTjcy1;
    TextView tvDetails1;
    TextView tvStep1;
    TextView tvShzt1;
    ImageView imgStep1;
    TextView tvTjcy1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        initIncludeView(view);
        Log.e("wh", "ThirdFragment");
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
        if (!isViewCreated || !isUIVisible||isload ) {
            return;
        }
        isload = true;
//        Log.e("wh", "OneFragment 加载数据");
        initData();
    }

    private void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("type", "3");
        mPresenter.getData(map);
    }

    @Override
    public void getDataSuccess(YwhInfo baseEntity) {
        if (baseEntity.isSuccess()) {
            initStatusView(baseEntity);
        } else {
            onError(baseEntity.msg);
        }
    }
    private void initIncludeView(View view) {
        ll_ywh = view.findViewById(R.id.ll_ywh);
        llTjcy = ll_ywh.findViewById(R.id.ll_tjcy);
        tvDetails = ll_ywh.findViewById(R.id.tv_details);
        tvStep = ll_ywh.findViewById(R.id.tv_step);
        tvShzt = ll_ywh.findViewById(R.id.tv_shzt);
        imgStep = ll_ywh.findViewById(R.id.img_step);
        tvTjcy = ll_ywh.findViewById(R.id.tv_tjcy);
        ll_ywh2 = view.findViewById(R.id.ll_ywh2);
        tvDetails1 = ll_ywh2.findViewById(R.id.tv_details);
        tvStep1 = ll_ywh2.findViewById(R.id.tv_step);
        tvShzt1 = ll_ywh2.findViewById(R.id.tv_shzt);
        imgStep1 = ll_ywh2.findViewById(R.id.img_step);
        tvTjcy1 = ll_ywh2.findViewById(R.id.tv_tjcy);
        llTjcy1 = ll_ywh2.findViewById(R.id.ll_tjcy);

    }

    private void initStatusView(YwhInfo ywhInfo) {
        if (ywhInfo.getData().getFlow().getPhaseState() == -1) {
            llStatus1.setVisibility(View.VISIBLE);
            llStatus2.setVisibility(View.GONE);
            llStatus3.setVisibility(View.GONE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_ff9e04));
            tvStatus.setText("筹备组工作阶段-未开始");
        } else if (ywhInfo.getData().getFlow().getPhaseState() == 1 && ywhInfo.getData().getFlow().getGongshi()==null) {
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            llStatus3.setVisibility(View.GONE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
            tvStatus.setText("筹备组工作阶段-进行中");
            imgStep.setImageResource(R.mipmap.ic_ywh_start4);
            tvStep.setText("请及时领取票权");
            tvDetails.setText(Html.fromHtml("请在" + "<font color=\"#ff9e04\">" + ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitTime() + "</font>" +
                    "之前完成实名认证以领取票权！每个业主仅能领取一张票权，未领取票权的业主将无法参与业主大会投票。"));
            tvTjcy.setText("立即领取票权");
            llTjcy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(PqrzActivity.class);
                }
            });
            tvShzt.setVisibility(View.VISIBLE);
            tvShzt.setText(ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitStateContext());
        } else if (ywhInfo.getData().getFlow().getPhaseState() == 1 && ywhInfo.getData().getFlow().getGongshi()!=null) {
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            llStatus3.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
            tvStatus.setText("筹备组工作阶段-进行中");
            imgStep.setImageResource(R.mipmap.ic_ywh_start4);
            tvStep.setText("请及时领取票权");
            tvDetails.setText(ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitOpinion());
            tvTjcy.setText("查看");
            tvShzt.setVisibility(View.VISIBLE);
            tvShzt.setText(ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitStateContext());
            llTjcy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(PqrzResultActivity.class);
                }
            });
            imgStep1.setImageResource(R.mipmap.ic_ywh_start5);
            tvStep1.setText("关于业主大会筹备文件公示的通知");
            tvDetails1.setText(ywhInfo.getData().getFlow().getGongshi().getTitle());
            tvTjcy1.setText("查看业主大会筹备文件");
            llTjcy1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CheckNoticeActivity.class);//查看通知
                    intent.putExtra("ywh_position", 2);
                    startActivity(intent);
                }
            });
        } else if (ywhInfo.getData().getFlow().getPhaseState() == 2) {
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            llStatus3.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_00b404));
            tvStatus.setText("筹备组工作阶段-已完成");
            imgStep.setImageResource(R.mipmap.ic_ywh_start4);
            tvStep.setText("筹备工作已开始，请及时领取票权");
            tvDetails.setText(ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitOpinion());
            tvTjcy.setText("查看");
            tvShzt.setVisibility(View.VISIBLE);
            tvShzt.setText(ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitStateContext());
            llTjcy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(PqrzResultActivity.class);
                }
            });
            imgStep1.setImageResource(R.mipmap.ic_ywh_start5);
            tvStep1.setText("业主大会及业主委员会相关筹备文件公示通知");
            tvDetails1.setText(ywhInfo.getData().getFlow().getGongshi().getTitle());
            tvTjcy1.setText("业主大会及业主委员会相关筹备文件公示");
            llTjcy1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    startActivity(CheckNoticeActivity.class);
                    Intent intent = new Intent(getActivity(), CheckNoticeActivity.class);//查看通知
                    intent.putExtra("ywh_position", 2);
                    intent.putExtra("isYjfk", 1);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerThirdComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .thirdModule(new ThirdModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(ThirdContract.ThirdContractPresenter presenter) {
        mPresenter = (ThirdPresenter) presenter;
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