package com.yxld.yxchuangxin.ui.activity.goods;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyMallSale;
import com.yxld.yxchuangxin.entity.goods.MallNewOrder;
import com.yxld.yxchuangxin.entity.goods.MallNewOrderDetails;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerCommentAndShowOrderComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.CommentAndShowOrderContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.CommentAndShowOrderModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.CommentAndShowOrderPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.CommentAndShowOrderAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: 评价晒单
 * @date 2017/06/28 20:14:36
 */

public class CommentAndShowOrderActivity extends BaseActivity implements CommentAndShowOrderContract.View {
    public static final String KEY_ORDER_ID = "key_order_id";
    public static final String KEY_PRODUCTS = "key_products";

    @Inject
    CommentAndShowOrderPresenter mPresenter;
    @BindView(R.id.btn_add_comment)
    Button btnAddComment;
    @BindView(R.id.recycerView)
    RecyclerView recycerView;


    private CommentAndShowOrderAdapter mAdapter;
    private String mOrderId;
    private List<MallNewOrderDetails> mProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_comment_show_order);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_ff9934));
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        mOrderId = bundle.getString(KEY_ORDER_ID);
        mProducts = bundle.getParcelableArrayList(KEY_PRODUCTS);

        LinearLayoutManager manager = new LinearLayoutManager(CommentAndShowOrderActivity.this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new CommentAndShowOrderAdapter(mProducts);
        recycerView.setLayoutManager(manager);
        recycerView.setAdapter(mAdapter);

        if (mAdapter.getFooterLayoutCount() == 0) {
            mAdapter.setFooterView(UIUtils.getRecyclerBottomView(CommentAndShowOrderActivity.this, 26));
        }
    }


    @Override
    protected void setupActivityComponent() {
        DaggerCommentAndShowOrderComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .commentAndShowOrderModule(new CommentAndShowOrderModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(CommentAndShowOrderContract.CommentAndShowOrderContractPresenter presenter) {
        mPresenter = (CommentAndShowOrderPresenter) presenter;
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
    public void onCommentResponse(BaseEntity entity) {
        closeProgressDialog();
        if (entity.status == 1) {
            ToastUtil.show(CommentAndShowOrderActivity.this, "评论成功");
            setResult(OrderListPartitionFragment.CODE_REQUEST_COMMENT);
            finish();
        } else {
            onError(entity.MSG, entity.status);
        }
    }

    @Override
    public void onCommentFailed() {
        closeProgressDialog();
        ToastUtil.show(CommentAndShowOrderActivity.this, getResources().getString(R.string.load_failed));
    }

    @OnClick(R.id.btn_add_comment)
    public void onViewClicked() {
        if (hasCommentEmpty()) {
            ToastUtil.show(CommentAndShowOrderActivity.this, "还有商品未评价");
            return;
        }
        if (hasEmptyRate()) {
            ToastUtil.show(CommentAndShowOrderActivity.this, "评价等级不能为0颗星");
            return;
        }
        if (hasLsTen()) {
            ToastUtil.show(CommentAndShowOrderActivity.this, "请输入大于10个字的评价");
            return;
        }
        showProgressDialog();
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        params.put("bianhao", mOrderId);
        for (int i = 0, size = mProducts.size(); i < size; i++) {
            MallNewOrderDetails sale = mProducts.get(i);
            params.put("pingjias[" + i + "].shangpinMing", sale.getShangpinMing());
            params.put("pingjias[" + i + "].pingjiaNeirong", sale.getComment());
            params.put("pingjias[" + i + "].pingjiaDengji", (int) sale.getRatingNum() + "");
            params.put("pingjias[" + i + "].shangpinId", sale.getShangpinId() + "");
        }

        mPresenter.addComment(params);
    }

    private boolean hasLsTen() {
        for (MallNewOrderDetails sale : mProducts) {
            if (sale.getComment().length() <= 1) {
                return true;
            }
        }
        return false;
    }

    private boolean hasEmptyRate() {
        for (MallNewOrderDetails sale : mProducts) {
            if (sale.getRatingNum() == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCommentEmpty() {
        for (MallNewOrderDetails sale : mProducts) {
            if (TextUtils.isEmpty(sale.getComment())) {
                return true;
            }
        }
        return false;
    }
}