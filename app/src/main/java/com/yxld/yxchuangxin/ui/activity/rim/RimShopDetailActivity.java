package com.yxld.yxchuangxin.ui.activity.rim;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.CxwyBusinessInfo;
import com.yxld.yxchuangxin.entity.RimShopDetailBean;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimShopDetailComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimShopDetailContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimShopDetailModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimShopDetailPresenter;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.rimshopdetail
 * @Description: 欣周边 商家首页
 * @date 2017/12/15 12:04:35
 */

public class RimShopDetailActivity extends BaseActivity implements RimShopDetailContract.View {

    @Inject
    RimShopDetailPresenter mPresenter;

    @BindView(R.id.iv_shop_logo)
    ImageView ivShopLogo;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.rb_shop_level)
    RatingBar rbShopLevel;
    @BindView(R.id.tv_shop_level)
    TextView tvShopLevel;
    @BindView(R.id.tv_shop_sales)
    TextView tvShopSales;
    @BindView(R.id.atrl_call_shop)
    AutoRelativeLayout atrlCallShop;
    @BindView(R.id.tv_discount1)
    TextView tvDiscount1;
    @BindView(R.id.tv_discount2)
    TextView tvDiscount2;
    @BindView(R.id.autoll_discount)
    AutoLinearLayout autollDiscount;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;
    @BindView(R.id.autorl_comment_num)
    AutoRelativeLayout autorlCommentNum;
    @BindView(R.id.iv_comment_head)
    ImageView ivCommentHead;
    @BindView(R.id.tv_comment_name)
    TextView tvCommentName;
    @BindView(R.id.tv_comment_time)
    TextView tvCommentTime;
    @BindView(R.id.rb_commnet_praise)
    RatingBar rbCommnetPraise;
    @BindView(R.id.tv_comment_content)
    TextView tvCommentContent;
    @BindView(R.id.autoll_comment_detail)
    AutoLinearLayout autollCommentDetail;
    @BindView(R.id.autorl_flow_process)
    AutoRelativeLayout autorlFlowProcess;
    @BindView(R.id.autorl_shop_description)
    AutoRelativeLayout autorlShopDescription;
    @BindView(R.id.tv_shop_description)
    TextView tvShopDescription;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;

    private RimShopDetailBean.DataBean.BusinessBean business;//商家数据
    private String businessNumber;//店铺id
    private double score;//店铺星级
    private String phoneNum;//商家电话
    private String logo;//商家logo
    private int appraiseNum;//商家评价总数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_rim_shopdetail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CxwyBusinessInfo.DataBean.BusinessBean business1 = (CxwyBusinessInfo.DataBean
                .BusinessBean) getIntent().getSerializableExtra("business");
        businessNumber = business1.getBusinessNumber();

        score = getIntent().getDoubleExtra("score", 5);
        rbShopLevel.setRating((float) score);
        tvShopLevel.setText(score + "分");

//        KLog.i("score"+(float) score);
    }

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("businessNumber", businessNumber);
        map.put("uuId", Contains.uuid);
        mPresenter.getRimShopDetail(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerRimShopDetailComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .rimShopDetailModule(new RimShopDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RimShopDetailContract.RimShopDetailContractPresenter presenter) {
        mPresenter = (RimShopDetailPresenter) presenter;
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
    public void setData(RimShopDetailBean data) {
        business = data.getData().getBusiness();
        logo = business.getBusinessLogo();
        appraiseNum = data.getData().getAppraiseNum();
//        Log.e("wh", "score " + score + " BusinessBean " + business.toString());
        Glide.with(this)
                .load(API.PIC + logo)
//                .placeholder(R.drawable.default_recommed_icon)
//                .error(R.drawable.default_recommed_icon)
                .crossFade()//渐入渐出
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(ivShopLogo);
        phoneNum = business.getBusinessPhone();
        tvShopName.setText(business.getBusinessName());
        tvShopSales.setText("已售" + data.getData().getOrderNum() + "单");

        String content = data.getData().getContent();

        tvDiscount.setText(content);

        tvDiscount1.setText("");
        tvDiscount2.setText("");
        if (null == data.getData().getPingjia() || data.getData().getPingjia().size() == 0) {
            tvCommentNum.setText("(" + "0" + ")");
            autollCommentDetail.setVisibility(View.GONE);
        } else {
            RimShopDetailBean.DataBean.PingjiaBean pingjiaBean = data.getData().getPingjia().get(0);
            tvCommentNum.setText("(" + appraiseNum + ")");
            autollCommentDetail.setVisibility(View.VISIBLE);
            tvCommentName.setText(pingjiaBean.getOrderUserName());
            tvCommentTime.setText(pingjiaBean.getOrderEvaluateEvaTime());
            tvCommentContent.setText(pingjiaBean.getOrderEvaluateEvaContent());
            rbCommnetPraise.setRating(pingjiaBean.getOrderEvaluateEvaLevel());
        }
        tvShopDescription.setText(business.getBusinessDetails());


    }

    @Override
    public void setError() {

    }

    @Override
    public void setEmptyData(RimShopDetailBean data) {
        ToastUtil.show(this, data.msg);

    }

    @OnClick({R.id.atrl_call_shop, R.id.autorl_comment_num, R.id.autorl_flow_process, R.id
            .autorl_shop_description})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.atrl_call_shop://联系商家
                new AlertView.Builder().setContext(this)
                        .setTitle("是否拨打电话？")
                        .setCancelText("取消")
                        .setOthers(new String[]{"确定"})
                        .setStyle(AlertView.Style.ActionSheet)
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(Object o, int position) {
                                if (position == 0) {
                                    mPresenter.makeCall(phoneNum);
                                }
                            }
                        })
                        .build().setCancelable(true).show();
                break;
            case R.id.autorl_comment_num://评价
                mPresenter.startRimShopCommentListActivity(businessNumber, score, logo,
                        appraiseNum);
                break;
            case R.id.autorl_flow_process://服务流程
//                mPresenter.startRimFlowProcessActivity();
                break;
            case R.id.autorl_shop_description://商家简介
//                mPresenter.startWebViewActivity();
                break;
        }
    }
}