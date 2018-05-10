package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerMarketComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MarketContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.MarketModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MarketPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.MarketFragmentAdapter;
import com.yxld.yxchuangxin.view.sinaview.PopMenu;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/12
 */

public class MarketFragment extends BaseFragment implements MarketContract.View, OnShopCartClicked {
    private static final String TAG = MarketFragment.class.getName();

    private PopMenu mPopMenu;
    @Inject
    MarketPresenter mPresenter;


//    @BindView(R.id.vp_market_container)
//    ViewPager vpMarketContainer;
//    @BindView(R.id.tab_titles)
//    AutoTabLayout tabTitles;

    @BindView(R.id.head_market_root)
    AutoRelativeLayout headMarketRoot;
    @BindView(R.id.market_search)
    AutoRelativeLayout marketSearch;
    @BindView(R.id.toolbarBusiness)
    Toolbar toolbar;
//    @BindView(R.id.ll_market_activity_root)
//    AutoLinearLayout llMarketActivityRoot;
//    @BindView(R.id.status_bar)
//    View statusBar;


    private List<Fragment> mFragments;
    private List<String> mTitles;
    private MarketFragmentAdapter fragmentAdapter;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            savedInstanceState.putParcelable("android:support:fragments", null);
        }
    }

    protected void initView() {
//        setContentView(R.layout.activity_market);
//        ButterKnife.bind(this);
//        toolbar.setPadding(0, UIUtils.getStatusBarHeight(getActivity()), 0, 0);
//        toolbar.setBackgroundColor(getResources().getColor(R.color.color_00000000));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setTitle(null);
        initBar();

//        toolbar.setVisibility(View.GONE);
//        headMarketRoot.bringToFront();
    }

    private void initBar() {
//        int statusBarHeight = UIUtils.getStatusBarHeight(UIUtils.getContext());
//        AutoLinearLayout.LayoutParams layoutParams = (AutoLinearLayout.LayoutParams) marketStatusBar.getLayoutParams();
//        layoutParams.height = statusBarHeight;
//        marketStatusBar.setLayoutParams(layoutParams);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.activity_market, null);
        ButterKnife.bind(this, view);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        TypedArray actionbarSizeTypedArray = getActivity().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        float h = actionbarSizeTypedArray.getDimension(0, 0);
        AutoRelativeLayout.LayoutParams lp = new AutoRelativeLayout.LayoutParams(UIUtils.getDisplayWidth(getActivity()), (int) (UIUtils.getStatusBarHeight(getActivity()) * 3));
        AutoLinearLayout.LayoutParams lp4 = new AutoLinearLayout.LayoutParams(UIUtils.getDisplayWidth(getActivity()), (int) (UIUtils.getStatusBarHeight(getActivity()) * 3));
//        AutoRelativeLayout.LayoutParams lp2 = new AutoRelativeLayout.LayoutParams(UIUtils.getDisplayWidth(getActivity()), (int) h + UIUtils.getStatusBarHeight(getActivity()));
        headMarketRoot.setVisibility(View.VISIBLE);
        headMarketRoot.setLayoutParams(lp4);
//        AutoRelativeLayout.LayoutParams lp3 = new AutoRelativeLayout.LayoutParams(UIUtils.getDisplayWidth(getActivity()), UIUtils.getStatusBarHeight(getActivity()));
//        statusBar.setLayoutParams(lp3);
//        headMarketRoot.setPadding(0, UIUtils.getStatusBarHeight(getActivity()), 0, 0);
        toolbar.setLayoutParams(lp);
        toolbar.setPadding(0, (int) (UIUtils.getStatusBarHeight(getActivity()) * 0.8), 0, 0);
        toolbar.setTitleMarginTop((int) (UIUtils.getStatusBarHeight(getActivity()) * 0.55));
//        toolbar.setLayoutParams(lp2);
//        toolbar.setPadding(0, UIUtils.getStatusBarHeight(getActivity()), 0, 0);
        Bundle mBundle = getArguments();
        initView();
        initData();
        return view;
    }

    protected void initData() {
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        fragmentAdapter = new MarketFragmentAdapter(getChildFragmentManager(), mFragments, mTitles);
//        vpMarketContainer.setOffscreenPageLimit(3);
//        vpMarketContainer.setAdapter(fragmentAdapter);
//        tabTitles.setupWithViewPager(vpMarketContainer);
        mPresenter.initAdapterData();

//        onViewPagerChangeEvent();
    }

    /**
     * 对ViewPager的滑动监听，改变头部的状态
     */
    private void onViewPagerChangeEvent() {
//        vpMarketContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                switch (position) {
//                    case 0:
//                        marketSearch.setVisibility(View.VISIBLE);
//                        toolbar.setVisibility(View.GONE);
//                        toolbar.setTitle(null);
//                        break;
//                    default:
//                        marketSearch.setVisibility(View.INVISIBLE);
//                        toolbar.setVisibility(View.VISIBLE);
//                        toolbar.setBackgroundColor(Color.parseColor("#ff9934"));
//                        toolbar.setTitle(position == 1 ? "购物车" : "欣商城");
//                        break;
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerMarketComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .marketModule(new MarketModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void setPresenter(MarketContract.MarketContractPresenter presenter) {
        mPresenter = (MarketPresenter) presenter;
    }


    @Override
    public void setAdapter(List<Fragment> fragments, List<String> titles) {
        mFragments.clear();
        mTitles.clear();
        mFragments.addAll(fragments);
        mTitles.addAll(titles);
        fragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onShopCartClick() {
//        vpMarketContainer.setCurrentItem(1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragments = null;
        mTitles = null;
        mPresenter.unsubscribe();
        mPresenter = null;
        fragmentAdapter = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @OnClick({R.id.market_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.market_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra(SearchActivity.KEY_IN_TYPE, SearchActivity.IN_TYPE_MAIN);
                startActivity(intent);
                break;

        }
    }

}