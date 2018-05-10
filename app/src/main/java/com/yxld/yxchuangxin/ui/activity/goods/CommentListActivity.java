package com.yxld.yxchuangxin.ui.activity.goods;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerCommentListComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.CommentListContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.CommentListModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.CommentListPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.MarketFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/29 11:01:08
 */

public class CommentListActivity extends BaseActivity implements CommentListContract.View {

    @Inject
    CommentListPresenter mPresenter;
   /* @BindView(R.id.tab_order_list)
    TabLayout tabOrderList;*/
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private String mProductId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_comment_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_ff9934));


    }

    @Override
    protected void initData() {
        mProductId = getIntent().getExtras().getString(GoodDetailActivity.KEY_PRODUCT_ID);

        List<String> titles = new ArrayList<>();
        /*titles.add("好评");
        titles.add("中评");
        titles.add("差评");*/
        titles.add("");
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            CommentListPartitionFragment fragment = new CommentListPartitionFragment();
            Bundle bundle = new Bundle();
            bundle.putString(CommentListPartitionFragment.ARG_TYPE, i == 0 ? "5" : i == 1 ? "3" : "1");
            bundle.putString(CommentListPartitionFragment.ARG_PRODUCT_ID,mProductId);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        MarketFragmentAdapter adapter = new MarketFragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
//        tabOrderList.setupWithViewPager(viewPager);
//        viewPager.setOffscreenPageLimit(3);
        viewPager.setOffscreenPageLimit(1);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerCommentListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .commentListModule(new CommentListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(CommentListContract.CommentListContractPresenter presenter) {
        mPresenter = (CommentListPresenter) presenter;
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
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
        mPresenter = null;
//        tabOrderList = null;
        viewPager = null;
    }
}