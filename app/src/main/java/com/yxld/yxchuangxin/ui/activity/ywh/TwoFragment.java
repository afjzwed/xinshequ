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

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.YwhInfo;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerTwoComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.TwoContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.TwoModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.TwoPresenter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
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
 * @date 2018/11/08 09:44:57
 */

public class TwoFragment extends BaseYwhFragment implements TwoContract.View {

    @Inject
    TwoPresenter mPresenter;
    @BindView(R.id.tv_status) TextView tvStatus;
    @BindView(R.id.ll_status1) AutoLinearLayout llStatus1;
    @BindView(R.id.ll_status2) AutoLinearLayout llStatus2;
    @BindView(R.id.tv_details) TextView tvDetails;
    @BindView(R.id.tv_step) TextView tvStep;
    @BindView(R.id.img_step) ImageView imgStep;
    @BindView(R.id.tv_tjcy) TextView tvTjcy;
    private int type;
    private YwhInfo ywhInfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        Log.e("wh", "TwoFragment");
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
        map.put("type", "2");
        mPresenter.getData(map);
    }

    private void initStatusView(YwhInfo ywhInfo) {

        if (ywhInfo.getData().getFlow().getPhaseState() == -1) {
            llStatus1.setVisibility(View.VISIBLE);
            llStatus2.setVisibility(View.GONE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_ff9e04));
            tvStatus.setText("筹备组成立阶段-未开始");
        } else if (ywhInfo.getData().getFlow().getPhaseState() == 1 && ywhInfo.getData().getFlow().getGongshi().getGongshiType() == 7) {
            type = 1;
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
            tvStatus.setText("筹备组成立阶段-进行中");
            tvStep.setText("已启动推荐组成员推荐程序");
            tvDetails.setVisibility(View.VISIBLE);
            tvDetails.setText(Html.fromHtml("请在" + "<font color=\"#ff9e04\">" + ywhInfo.getData().getFlow().getGongshi().getEndtime() + "</font>" +
                    "之前完成筹备组成员推荐"));
            tvTjcy.setText("推荐筹备组成员");
        } else if (ywhInfo.getData().getFlow().getPhaseState() == 1 && ywhInfo.getData().getFlow().getGongshi().getGongshiType() == 1) {
            type = 2;
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
            tvStatus.setText("筹备组成立阶段-进行中");
            imgStep.setImageResource(R.mipmap.ic_ywh_start2);
            tvStep.setText("筹备组成员公示");
            tvDetails.setVisibility(View.VISIBLE);
            tvDetails.setText(ywhInfo.getData().getFlow().getGongshi().getTitle());
            tvTjcy.setText("查看筹备组成员公示信息");
        } else if (ywhInfo.getData().getFlow().getPhaseState() == 2) {
            type = 3;
            llStatus1.setVisibility(View.GONE);
            llStatus2.setVisibility(View.VISIBLE);
            tvStatus.setTextColor(getResources().getColor(R.color.color_00b404));
            tvStatus.setText("筹备组成立阶段-已完成");
            imgStep.setImageResource(R.mipmap.ic_ywh_start3);
            tvStep.setText("筹备组成员");
            tvDetails.setVisibility(View.GONE);
            tvTjcy.setText("查看筹备组成员名单");
        }
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerTwoComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .twoModule(new TwoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(TwoContract.TwoContractPresenter presenter) {
        mPresenter = (TwoPresenter) presenter;
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
            ywhInfo = baseEntity;
            initStatusView(baseEntity);
        } else {
            onError(baseEntity.msg);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    @OnClick({R.id.tv_status, R.id.ll_tjcy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_status:
                break;
            case R.id.ll_tjcy:
                if (type == 1) {
                    startActivity(TuiJianListActivity.class);//推荐成员
                } else if (type == 2) {
                    Intent intent = new Intent(getActivity(),CheckNoticeActivity.class);//查看通知
                    intent.putExtra("ywh_position", 1);
                    startActivity(intent);
//                    startActivity(CheckNoticeActivity.class);//查看通知
                } else if (type == 3) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) ywhInfo.getData().getFlow().getConfirmPeople());
                    bundle.putInt("isYjfk",1);
                    startActivity(CymdActivity.class,bundle);//成员名单公示
                }
                break;
        }
    }
}