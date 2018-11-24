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

import com.orhanobut.logger.Logger;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.YwhInfo;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerThirdComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.ThirdContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.ThirdModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.ThirdPresenter;
import com.zhy.autolayout.AutoLinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 09:53:49
 */

public class ThirdFragment extends BaseYwhFragment implements ThirdContract.View {
    public static final String EVEBUS_MSG = "ThirdFragment";
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        initIncludeView(view);
        Log.e("wh", "ThirdFragment");
        EventBus.getDefault().register(this);
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
        } else if (ywhInfo.getData().getFlow().getPhaseState() == 1) {
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
            tvStatus.setText("筹备组工作阶段-进行中");
            imgStep.setImageResource(R.mipmap.ic_ywh_start4);
            tvStep.setText(ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitname());
            if (ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitstate() == -1) {
                //审核失败
                tvTjcy.setText("查看");
                tvDetails.setText(Html.fromHtml("<font color=\"#ff9e04\">" + ywhInfo.getData().getFlow()
                        .getProprietorAduitVo().getAduitStateContext() + "可在" + "</font>" + "<font color=\"#ff9e04\">" + ywhInfo.getData().getFlow()
                        .getProprietorAduitVo().getAduitTime() + "</font>" +
                        "之前重新申请！否则将无法参与业主大会投票。"));
                tvShzt.setVisibility(View.VISIBLE);
                tvShzt.setText(ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitStateContext());
            } else if (ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitstate() == 3) {
                //审核中
                tvTjcy.setText("查看");
                tvDetails.setText("您的认证信息审核中！");
                tvShzt.setVisibility(View.VISIBLE);
                tvShzt.setText(ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitStateContext());
            } else if (ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitstate() == 2) {
                //审核成功
                tvTjcy.setText("查看");
                tvDetails.setText("恭喜您已成功领取票权！");
                tvShzt.setVisibility(View.VISIBLE);
                tvShzt.setText(ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitStateContext());
            } else if (ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitstate() == 4) {
                //未提交资料
                tvTjcy.setText("立即领取票权");
                tvDetails.setText(Html.fromHtml("请在" + "<font color=\"#ff9e04\">" + ywhInfo.getData().getFlow()
                        .getProprietorAduitVo().getAduitTime() + "</font>" +
                        "之前完成实名认证以领取票权！每个业主仅能领取一张票权，未领取票权的业主将无法参与业主大会投票。"));
                tvShzt.setVisibility(View.GONE);
            }
            //点击跳转实名票权认证或票权认证状态页
            llTjcy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitstate() == 4) {
                        startActivity(PqrzActivity.class);
                    } else {
                        startActivity(PqrzResultActivity.class);
                    }
                }
            });

            if (ywhInfo.getData().getFlow().getGongshi() == null) {
                llStatus3.setVisibility(View.GONE);
            } else {
                llStatus3.setVisibility(View.VISIBLE);
                imgStep1.setImageResource(R.mipmap.ic_ywh_start5);
                tvStep1.setText("关于业主大会筹备文件公示的通知");
                tvDetails1.setText(ywhInfo.getData().getFlow().getGongshi().getTitle());
                tvTjcy1.setText("查看业主大会筹备文件");
                llTjcy1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) ywhInfo.getData().getFlow().getConfirmPeople());//人员列表
                        bundle.putParcelableArrayList("ywh_member_list", (ArrayList<? extends Parcelable>) ywhInfo.getData().getFlow().getFiles());//附件列表
                        bundle.putInt("isYjfk", 0);
                        bundle.putInt("ywh_position", 2);
                        bundle.putParcelable("ywh_gongshi", ywhInfo.getData().getFlow().getGongshi());//公示
                        startActivity(CheckNoticeActivity.class, bundle);//成员名单公示
                    }
                });
            }
        } else if (ywhInfo.getData().getFlow().getPhaseState() == 2) {
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_00b404));
            tvStatus.setText("筹备组工作阶段-已完成");
            imgStep.setImageResource(R.mipmap.ic_ywh_start4);
            tvStep.setText(ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitname());
            if (ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitstate() == -1) {
                //审核失败
                tvTjcy.setText("查看");
                tvDetails.setText(Html.fromHtml("<font color=\"#ff9e04\">" + ywhInfo.getData().getFlow()
                        .getProprietorAduitVo().getAduitStateContext() + "可在" + "</font>" + "<font color=\"#ff9e04\">" + ywhInfo.getData().getFlow()
                        .getProprietorAduitVo().getAduitTime() + "</font>" +
                        "之前重新申请！否则将无法参与业主大会投票。"));
                tvShzt.setVisibility(View.VISIBLE);
                tvShzt.setText(ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitStateContext());
            } else if (ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitstate() == 3) {
                //审核中
                tvTjcy.setText("查看");
                tvDetails.setText("您的认证信息审核中！");
                tvShzt.setVisibility(View.VISIBLE);
                tvShzt.setText(ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitStateContext());
            } else if (ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitstate() == 2) {
                //审核成功
                tvTjcy.setText("查看");
                tvDetails.setText("恭喜您已成功领取票权！");
                tvShzt.setVisibility(View.VISIBLE);
                tvShzt.setText(ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitStateContext());
            } else if (ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitstate() == 4) {
                //未提交资料
                tvTjcy.setText("立即领取票权");
                tvDetails.setText(Html.fromHtml("请在" + "<font color=\"#ff9e04\">" + ywhInfo.getData().getFlow()
                        .getProprietorAduitVo().getAduitTime() + "</font>" +
                        "之前完成实名认证以领取票权！每个业主仅能领取一张票权，未领取票权的业主将无法参与业主大会投票。"));
                tvShzt.setVisibility(View.GONE);
            }
            //点击跳转实名票权认证或票权认证状态页
            llTjcy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ywhInfo.getData().getFlow().getProprietorAduitVo().getAduitstate() == 4) {
                        startActivity(PqrzActivity.class);
                    } else {
                        startActivity(PqrzResultActivity.class);
                    }
                }
            });
            if (ywhInfo.getData().getFlow().getGongshi() == null) {
                llStatus3.setVisibility(View.GONE);
            } else {
                llStatus3.setVisibility(View.VISIBLE);
            imgStep1.setImageResource(R.mipmap.ic_ywh_start5);
            tvStep1.setText("业主大会及业主委员会相关筹备文件公示通知");
            tvDetails1.setText(ywhInfo.getData().getFlow().getGongshi().getTitle());
            tvTjcy1.setText("业主大会及业主委员会相关筹备文件公示");
            llTjcy1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    startActivity(CheckNoticeActivity.class);
                    Intent intent = new Intent(getActivity(), CheckNoticeActivity.class);//查看通知
                    intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) ywhInfo.getData()
                            .getFlow().getConfirmPeople());//人员列表
                    intent.putExtra("ywh_gongshi", ywhInfo.getData().getFlow().getGongshi());//公示
                    intent.putParcelableArrayListExtra("ywh_member_list", (ArrayList<? extends Parcelable>) ywhInfo
                            .getData().getFlow().getFiles());
                    intent.putExtra("ywh_position", 2);
                    intent.putExtra("isYjfk", 1);
                    startActivity(intent);
                }
            });
            }
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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefresh(String s) {
        Logger.e("ThirdFrament evebusmsg 刷新状态");
        if (s.equals(EVEBUS_MSG)) {
            initData();
        }
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