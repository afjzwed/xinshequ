package com.yxld.yxchuangxin.ui.activity.ywh;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;

import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.YwhCurrentflow;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerYeWeiHuiComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YeWeiHuiContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YeWeiHuiModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YeWeiHuiPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: 我的业委会主页面
 * @date 2018/11/07 11:49:57
 */

public class YeWeiHuiActivity extends BaseActivity implements YeWeiHuiContract.View {

    @Inject
    YeWeiHuiPresenter mPresenter;
    @BindView(R.id.tab_indicator)
    ScrollIndicatorView tabIndicator;
    @BindView(R.id.tab_viewPager)
    ViewPager tabViewPager;

    private IndicatorViewPager indicatorViewPager;
    private MyAdapter myAdapter;

    private int currrentPosition = 0;//当前阶段
    private int currentPhaseStatus = -1;//当前阶段状态(-1:未开始,1:进行中,2已完成)

    private int[] status = new int[6];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_yeweihui);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//                toolbar.setTitle("改变标题");
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_2d97ff));

        //设置指示器线的颜色
        tabIndicator.setScrollBar(new ColorBar(this, Color.parseColor("#ffffff"), 6));
        //设置指示器文字的颜色
//        tabIndicator.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.RED, Color.GRAY));
        //懒加载界面和防止重新创建界面
//        tabViewPager.setOffscreenPageLimit(4);

        indicatorViewPager = new IndicatorViewPager(tabIndicator, tabViewPager);

        // 禁止viewpager的滑动事件
//        indicatorViewPager.setPageCanScroll(false);
        //设置viewpager保留界面不重新加载的页面数量
//        tabViewPager.setOffscreenPageLimit(6);
        tabIndicator.setSplitAuto(true);

    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    public void setData(YwhCurrentflow ywhCurrentflow) {

//        Log.e("whlog", baseEntity.toString());"开始成立", "成立筹备组", "筹备组工作", "候选人确认", "业主大会", "备案阶段"
        if (null != ywhCurrentflow && ywhCurrentflow.getCode() == 200) {
            YwhCurrentflow.DataBean.CurrentFlowBean currentFlow = ywhCurrentflow.getData().getCurrentFlow();
            if (null != currentFlow && !TextUtils.isEmpty(currentFlow.getCurrentPhaseName())) {

                switch (currentFlow.getCurrentPhaseName()) {
                    case "开始成立":
                        currrentPosition = 0;
                        status[0]=ywhCurrentflow.getData().getFlows().get(0).getPhaseState();
                        break;
                    case "成立筹备组":
                        currrentPosition = 1;
                        status[1]=ywhCurrentflow.getData().getFlows().get(1).getPhaseState();
                        break;
                    case "筹备组工作":
                        currrentPosition = 2;
                        status[2]=ywhCurrentflow.getData().getFlows().get(2).getPhaseState();
                        break;
                    case "候选人确认":
                        currrentPosition = 3;
                        status[3]=ywhCurrentflow.getData().getFlows().get(3).getPhaseState();
                        break;
                    case "业主大会":
                        currrentPosition = 4;
                        status[4]=ywhCurrentflow.getData().getFlows().get(4).getPhaseState();
                        break;
                    case "备案阶段":
                        currrentPosition = 5;
                        status[5]=ywhCurrentflow.getData().getFlows().get(5).getPhaseState();
                        break;
                }
                currentPhaseStatus = currentFlow.getCurrentPhaseStatus();
                myAdapter = new MyAdapter(getSupportFragmentManager(),ywhCurrentflow);
                indicatorViewPager.setAdapter(myAdapter);
                indicatorViewPager.setCurrentItem(currrentPosition,false);
            }
        }
//        myAdapter.notifyDataSetChanged();

    }

    public int getCurrentPosition() {
        return currrentPosition;
    }

    public int getCurrentPhaseStatus(int currentFlow) {
        return status[currentFlow];
    }

    @Override
    protected void setupActivityComponent() {
        DaggerYeWeiHuiComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .yeWeiHuiModule(new YeWeiHuiModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(YeWeiHuiContract.YeWeiHuiContractPresenter presenter) {
        mPresenter = (YeWeiHuiPresenter) presenter;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ywh_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ywh_help:
                startActivity(YwhMessageGuideActivity.class);//业委会信息指导
                break;
            case android.R.id.home:
                finish();
                System.gc();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        private String[] tabNames = {"开始成立", "成立筹备组", "筹备组工作", "候选人确认", "业主大会", "备案阶段"};
        private LayoutInflater inflater;
       private YwhCurrentflow currentflow;

        public MyAdapter(FragmentManager fragmentManager, YwhCurrentflow ywhCurrentflow) {
            super(fragmentManager);
            inflater = LayoutInflater.from(getApplicationContext());
            currentflow = ywhCurrentflow;
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            // TODO: 2018/11/6 这里定制
//            if (convertView == null) {
//                convertView = inflater.inflate(R.layout.yeh_tab, container, false);
//            }
//            convertView = inflater.inflate(R.layout.yeh_tab, container, false);
            View view = View.inflate(getApplicationContext(), R.layout.yeh_tab, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_selector);
            TextView textView = (TextView) view.findViewById(R.id.tv_selector);
            if (position < currrentPosition) {
                imageView.setImageResource(R.drawable.ywtab_1_selector);
            } else if (position == currrentPosition) {
                imageView.setImageResource(R.drawable.ywtab_3_selector);
            } else if (position > currrentPosition) {
                imageView.setImageResource(R.drawable.ywtab_2_selector);
            }
            textView.setText(tabNames[position]);
            return view;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            //测试
            if (position == 0) {
                return new OneFragment();
            } else if (position == 1) {
                return new TwoFragment();
            } else if (position == 2) {
                return new ThirdFragment();
            } else if (position == 3) {
                return new FourthFragment();
            } else if (position == 4) {
                return new FivethFragment();
            } else {
                return new SixthFragment();
            }
        }
    }

}