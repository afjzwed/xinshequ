package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerGoodsFenLeiComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.GoodsFenLeiContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.GoodsFenLeiModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.GoodsFenLeiPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.GoodFenLeiAdapter;
import com.zhy.autolayout.AutoFrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/10/19 08:58:40
 */

public class GoodsFenLeiActivity extends BaseActivity implements GoodsFenLeiContract.View {

    @Inject
    GoodsFenLeiPresenter mPresenter;
    @BindView(R.id.tab_order_list)
    TabLayout mTabLayout;
    @BindView(R.id.vp_order_list_container)
    ViewPager mVpOrderListContainer;
    @BindView(R.id.autoFrameLayout)
    AutoFrameLayout mAutoFrameLayout;
    private ArrayList<String> mTabList;
    private ArrayList<String> mTabList2;
    private List<Fragment> mFragmentList;
    private List<Fragment> mFragmentList2;
    private GoodFenLeiAdapter mGoodFenLeiAdapter;
    private ArrayList<String> mFenLei2IdList;
    private String FenleiId;
    private ArrayList<String> mFenlei1IdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_goods_fenlei);
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(Color.parseColor("#ff9934"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initTab();
    }

    @Override
    protected void initData() {

    }

    private void initTab() {
        mFragmentList = new ArrayList<>();
        mFragmentList2 = new ArrayList<>();
        mTabList = new ArrayList<>();
        mTabList2 = new ArrayList<>();
        mFenLei2IdList = new ArrayList<>();
        Intent intent = getIntent();
        FenleiId = intent.getStringExtra(MallFragment.TO_FEILEI_TYPE);
        mFenlei1IdList = intent.getStringArrayListExtra("listid");
        ArrayList<String> mFenlei1ming = intent.getStringArrayListExtra("listname");
        if ("10000".equals(FenleiId)) {
            //表示查询全部
            mAutoFrameLayout.setVisibility(View.VISIBLE);
            mTabLayout.setVisibility(View.VISIBLE);
            for (int i = 0; i < mFenlei1IdList.size(); i++) {
                GoodFenLeiFragment fragment = GoodFenLeiFragment.newInstance(mFenlei1IdList.get(i), "");
                mFragmentList.add(fragment);
                mTabList.add(mFenlei1ming.get(i));
            }
            if (mFragmentList.size() > 4) {
                mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            }
            mGoodFenLeiAdapter = new GoodFenLeiAdapter(getSupportFragmentManager(), mFragmentList, mTabList);
            mVpOrderListContainer.setAdapter(mGoodFenLeiAdapter);
            mVpOrderListContainer.setOffscreenPageLimit(mFragmentList.size());
            mTabLayout.setupWithViewPager(mVpOrderListContainer);
        } else {
            //表示一级菜单进来 查询二级菜单
            Map<String, String> map = new HashMap<>();
            map.put("uuid", Contains.uuid);
            map.put("fenlei1", FenleiId);
            mPresenter.getFenLei2(map);
        }
    }

    @Override
    public void loadFenLei2Success(MallClassify mallClassify) {
        if (mallClassify.getStatus() == 1) {
            mAutoFrameLayout.setVisibility(View.VISIBLE);
            mTabLayout.setVisibility(View.VISIBLE);
            mTabList2.add("全部");
            GoodFenLeiFragment qfragment = GoodFenLeiFragment.newInstance(FenleiId, "");
            mFragmentList2.add(qfragment);
            for (MallClassify.RowsBean bean : mallClassify.getRows()) {
                mFenLei2IdList.add(bean.getId() + "");
                mTabList2.add(bean.getFenleiMing());
                GoodFenLeiFragment fragment = GoodFenLeiFragment.newInstance(FenleiId, bean.getId() + "");
                mFragmentList2.add(fragment);
            }
            if (mFragmentList2.size() > 4) {
                mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            } else {
                mTabLayout.setTabMode(TabLayout.MODE_FIXED);
            }
            mGoodFenLeiAdapter = new GoodFenLeiAdapter(getSupportFragmentManager(), mFragmentList2, mTabList2);
            mVpOrderListContainer.setAdapter(mGoodFenLeiAdapter);
            mVpOrderListContainer.setOffscreenPageLimit(mFragmentList2.size());
            mTabLayout.setupWithViewPager(mVpOrderListContainer);
        } else {
          onError(mallClassify.getMSG(),mallClassify.getStatus());
        }

    }

    @Override
    protected void setupActivityComponent() {
        DaggerGoodsFenLeiComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .goodsFenLeiModule(new GoodsFenLeiModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(GoodsFenLeiContract.GoodsFenLeiContractPresenter presenter) {
        mPresenter = (GoodsFenLeiPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }


    @OnClick(R.id.layout_goods_list_shop_cart)
    public void onViewClicked() {
        startActivity(SecondShopCartActivity.class);
    }


}