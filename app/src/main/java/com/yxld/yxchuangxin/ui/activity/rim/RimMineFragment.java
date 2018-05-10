package com.yxld.yxchuangxin.ui.activity.rim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimMineComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimMineContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimMineModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimMinePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: 欣助手 个人中心界面
 * @date 2017/06/16
 */

public class RimMineFragment extends BaseFragment implements RimMineContract.View {

    @Inject
    RimMinePresenter mPresenter;
    @BindView(R.id.mine_scrollView)
    PullToZoomScrollViewEx mineScrollView;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rim_activty_mine_layout, null);
        unbinder = ButterKnife.bind(this, view);
        loadViewForCode();
        initView();
        return view;
    }

    @Override
    public void loadViewForCode() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_content_view, null, false);
        mineScrollView.setHeaderView(headView);
        mineScrollView.setZoomView(zoomView);
        mineScrollView.setScrollContentView(contentView);
    }

    @Override
    public void initView() {
        mineScrollView.getPullRootView().findViewById(R.id.rela_tongzhi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RimTongzhiListActivity.class);
            }
        });

        mineScrollView.getPullRootView().findViewById(R.id.rela_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("geek", "onClick -->c");
                startActivity(RimCommentListActivity.class);
            }
        });

        mineScrollView.getPullRootView().findViewById(R.id.rela_tousu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RimComplainListActivity.class);
            }
        });

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        mineScrollView.setHeaderLayoutParams(localObject);
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerRimMineComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .rimMineModule(new RimMineModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RimMineContract.RimMineContractPresenter presenter) {
        mPresenter = (RimMinePresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}