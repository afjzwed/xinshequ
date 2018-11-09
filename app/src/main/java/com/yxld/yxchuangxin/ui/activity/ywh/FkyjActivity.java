package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.DrawableBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerFkyjComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.FkyjContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.FkyjModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.FkyjPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/09 10:03:28
 */

public class FkyjActivity extends BaseActivity implements FkyjContract.View {

    @Inject
    FkyjPresenter mPresenter;
    @BindView(R.id.moretab_indicator) ScrollIndicatorView moretabIndicator;
    @BindView(R.id.moretab_viewPager) ViewPager moretabViewPager;
    private String[] title = new String[]{"提交反馈意见", "查看反馈意见"};
    private IndicatorViewPager indicatorViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_fkyj);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_2d97ff));
        toolbar.setTitle("意见反馈");
        moretabIndicator.setScrollBar(new DrawableBar(this, R.drawable.bg_btn_22_white, ScrollBar.Gravity.CENTENT_BACKGROUND) {
            @Override
            public int getHeight(int tabHeight) {
                return tabHeight;
            }

            @Override
            public int getWidth(int tabWidth) {
                return tabWidth;
            }
        });

        // 设置滚动监听
//        moretabIndicator.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.RED, Color.WHITE));
        // 设置滚动监听
//        moretabIndicator.setScrollBar(new DrawableBar(this,R.drawable.bg_btn_22_white, ScrollBar.Gravity.CENTENT_BACKGROUND));
        moretabIndicator.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.parseColor("#1e8afe"), Color.parseColor("#ffffff")));
        moretabViewPager.setOffscreenPageLimit(2);
        indicatorViewPager = new IndicatorViewPager(moretabIndicator, moretabViewPager);
        indicatorViewPager.setAdapter(new MyAdapter(this, getSupportFragmentManager()));

    }

    @Override
    protected void initData() {

    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        private Context context;

        public MyAdapter(Context context, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.context = context;
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_tab_yjfk, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(title[position]);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            if (position == 0) {
                return new Fkyj1Fragment();
            } else {
                return new Fkyj2Fragment();
            }
        }


    }

    @Override
    protected void setupActivityComponent() {
        DaggerFkyjComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .fkyjModule(new FkyjModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FkyjContract.FkyjContractPresenter presenter) {
        mPresenter = (FkyjPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

}