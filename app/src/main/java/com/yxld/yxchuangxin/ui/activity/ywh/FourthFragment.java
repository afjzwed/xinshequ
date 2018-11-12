package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.os.Bundle;
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
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerFourthComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.FourthContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.FourthModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.FourthPresenter;
import com.zhy.autolayout.AutoLinearLayout;

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

public class FourthFragment extends BaseFragment implements FourthContract.View {

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

    private int status = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ywh_fourth, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();

        initLoacView();
        initData();
        return view;
    }

    private void initData() {

        Log.e("wh", "FourthFragment");
        status = 2;
//        mPresenter.getFourthData();
        setFourthData(null);
    }

    private void initLoacView() {

    }

    @Override
    public void setFourthData(BaseEntity baseEntity) {

        Log.e("wh", "FourthFragment" + status);
        //获取数据
        switch (status) {
            case 0:
                autollData.setVisibility(View.GONE);
                ivNoData.setVisibility(View.VISIBLE);
                tvStatus.setText("候选人确定阶段-未开始");
                tvStatus.setTextColor(getResources().getColor(R.color.color_ff9e04));
                break;
            case 1:
                autollData.setVisibility(View.VISIBLE);
                ivNoData.setVisibility(View.GONE);
                tvStatus.setText("候选人确定阶段-进行中");
                tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
                tvContentHead.setText(Html.fromHtml("请在" + "<font color=\"#ff9e04\">" + "2018-9-12" + "</font>" +
                        "之前完成推荐程序"));
                tvContentHead.setVisibility(View.VISIBLE);
                tvName.setText("推荐候选人成员");
                ivImg.setImageResource(R.mipmap.ic_ywh_start);
                break;
            case 2:
                autollData.setVisibility(View.VISIBLE);
                ivNoData.setVisibility(View.GONE);
                ivImg.setImageResource(R.mipmap.ic_ywh_vote);
                tvTitle.setText("候选人名单已公示");
                tvContentHead.setText("通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容通知内容");
                tvContentHead.setVisibility(View.VISIBLE);
                tvName.setText("查看候选人名单公示");
                break;
            case 3:
                autollData.setVisibility(View.VISIBLE);
                ivNoData.setVisibility(View.GONE);
                tvStatus.setText("候选人确定阶段-已完成");
                tvStatus.setTextColor(getResources().getColor(R.color.color_00b404));
                tvTitle.setText("候选人名单");
                tvName.setText("查看候选人名单");
                ivImg.setImageResource(R.mipmap.ic_ywh_start3);
                tvContentHead.setVisibility(View.GONE);
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

    @OnClick({R.id.tv_status, R.id.tv_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                Toast.makeText(getActivity(), "点击", Toast.LENGTH_SHORT).show();
                Intent intent ;
                switch (status) {
                    case 1:
                        intent = new Intent(getActivity(), RecommendMemberActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
//                        intent = new Intent(getActivity(), CheckNoticeActivity.class);
//                        startActivity(intent);
                         intent = new Intent(getActivity(),CheckNoticeActivity.class);//查看通知
                        intent.putExtra("ywh_position", 3);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getActivity(), CymdActivity.class);
                        startActivity(intent);
                        break;
                }
                break;
            case R.id.tv_status:
                if (status == 0) {
                    status = 1;
                } else if (status == 1) {
                    status = 2;
                } else if (status == 2) {
                    status = 3;
                } else {
                    status = 0;
                }
                setFourthData(null);
                break;
        }
    }
}