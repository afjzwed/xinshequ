package com.yxld.yxchuangxin.ui.activity.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.entity.adapter.Wuye;
import com.yxld.yxchuangxin.ui.activity.main.component.DaggerWuyeComponent;
import com.yxld.yxchuangxin.ui.activity.main.contract.WuyeContract;
import com.yxld.yxchuangxin.ui.activity.main.module.WuyeModule;
import com.yxld.yxchuangxin.ui.activity.main.presenter.WuyePresenter;
import com.yxld.yxchuangxin.ui.adapter.main.WuyeAdapter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: $description
 * @date 2017/06/05
 */

public class WuyeFragment extends BaseFragment implements WuyeContract.View {

    @Inject
    WuyePresenter mPresenter;

    @Inject
    WuyeAdapter wuyeAdapter;

    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    @BindView(R.id.toolbarBusiness)
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_wuye, null);
        ButterKnife.bind(this, view);
        toolbar.setTitle("欣物业");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        AutoLinearLayout.LayoutParams lp = new AutoLinearLayout.LayoutParams(UIUtils.getDisplayWidth(getActivity()), (int)(UIUtils.getStatusBarHeight(getActivity()) * 3));
        toolbar.setLayoutParams(lp);
        toolbar.setPadding(0, (int) (UIUtils.getStatusBarHeight(getActivity()) * 0.8), 0, 0);
        toolbar.setTitleMarginTop((int) (UIUtils.getStatusBarHeight(getActivity()) *0.55));
//        TypedArray actionbarSizeTypedArray = getActivity().obtainStyledAttributes(new int[] {
//                android.R.attr.actionBarSize
//        });
//        float h = actionbarSizeTypedArray.getDimension(0, 0);
//        AutoLinearLayout.LayoutParams lp = new AutoLinearLayout.LayoutParams(UIUtils.getDisplayWidth(getActivity()), (int)h + UIUtils.getStatusBarHeight(getActivity()));
//        toolbar.setLayoutParams(lp);
//        toolbar.setPadding(0, UIUtils.getStatusBarHeight(getActivity()), 0, 0);
        Bundle mBundle = getArguments();
        recycerView.setAdapter(wuyeAdapter);
        return view;
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerWuyeComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .wuyeModule(new WuyeModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initDataFromLocal() {

    }


//    @Override
//    protected void initView() {
//        setContentView(R.layout.activity_wuye);
//        ButterKnife.bind(this);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//    }

    @Override
    public void onResume() {
        super.onResume();
    }

//    @Override
//    protected void setupActivityComponent() {
//        DaggerWuyeComponent
//                .builder()
//                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
//                .wuyeModule(new WuyeModule(this))
//                .build()
//                .inject(this);
//    }

    @Override
    public void setPresenter(WuyeContract.WuyeContractPresenter presenter) {
        mPresenter = (WuyePresenter) presenter;
    }

    @Override
    public void setAdapter(List<Wuye.DataBean> dataBean) {
        recycerView.setAdapter(new WuyeAdapter(dataBean));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}