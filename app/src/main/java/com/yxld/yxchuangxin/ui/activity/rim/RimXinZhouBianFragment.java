package com.yxld.yxchuangxin.ui.activity.rim;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimXinZhouBianComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimXinZhouBianContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimXinZhouBianModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimXinZhouBianPresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.CNKFixedPagerAdapter;
import com.yxld.yxchuangxin.view.ChildViewPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: $欣周边 -欣周边主页
 * @date 2017/06/16
 */

public class RimXinZhouBianFragment extends BaseFragment implements RimXinZhouBianContract.View {

    @Inject
    RimXinZhouBianPresenter mPresenter;

    @BindView(R.id.xinzhushou_tabLayout)
    TabLayout xinzhushouTabLayout;
    @BindView(R.id.xinzhushou_viewPager)
    ChildViewPager xinzhushouViewPager;
    @BindView(R.id.fhhn)
    ImageView fhhn;
    @BindView(R.id.jrtt)
    ImageView jrtt;
    @BindView(R.id.hw)
    ImageView hw;

    Unbinder unbinder;

    private String[] titles = new String[]{"到家", "车主服务", "支付", "生活助手", "娱乐", "出行/旅行"};
    private List<Fragment> fragments;
    private CNKFixedPagerAdapter mPagerAdater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rim_xinzhushou_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        selectView();
        KLog.i("RimXinZhouBianFragment");
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupFragmentComponent() {
       DaggerRimXinZhouBianComponent
               .builder()
               .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
               .rimXinZhouBianModule(new RimXinZhouBianModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(RimXinZhouBianContract.RimXinZhouBianContractPresenter presenter) {
        mPresenter = (RimXinZhouBianPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void initView() {
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            ItemServiceFragment oneFragment = new ItemServiceFragment();
            Bundle args = new Bundle();
            args.putInt("arg", i);
            oneFragment.setArguments(args);
            fragments.add(oneFragment);
        }
        //创建Fragment的 ViewPager 自定义适配器
        mPagerAdater = new CNKFixedPagerAdapter(getChildFragmentManager());
        //设置显示的标题
        mPagerAdater.setTitles(titles);
        //设置需要进行滑动的页面Fragment
        mPagerAdater.setFragments(fragments);
        xinzhushouViewPager.setOffscreenPageLimit(6);
        xinzhushouViewPager.setAdapter(mPagerAdater);
        xinzhushouTabLayout.setupWithViewPager(xinzhushouViewPager);
        //设置TabLayout模式 -该使用Tab数量比较多的情况
        xinzhushouTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void selectView() {
        int position = getActivity().getIntent().getIntExtra("tag", 0);
        xinzhushouViewPager.setCurrentItem(position);
    }

    @Override
    public void onDestroyView() {
        KLog.i("RimXinZhouBianFragment");
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fhhn, R.id.jrtt, R.id.hw})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), // context
                WebViewActivity.class);// 跳转的activity\
        switch (view.getId()) {
            case R.id.fhhn:
                Bundle fhhn = new Bundle();
                fhhn.putString("name", "凤凰湖南");
                fhhn.putString("address", "http://hunan.ifeng.com");
                intent.putExtras(fhhn);
                startActivity(intent);
                break;
            case R.id.jrtt:
                Bundle jrtt = new Bundle();
                jrtt.putString("name", "今日头条");
                jrtt.putString("address", "http://www.toutiao.com/");
                intent.putExtras(jrtt);
                startActivity(intent);

//                PayContain.requestPayModule = PayContain.MODULE_MALL_ORDER;
//                PayContain.weixinPayChecked = PayContain.PAY_FAIL;
//                Bundle bundle = new Bundle();
//                bundle.putString("orderId", "test1");
//                bundle.putString("orderMoney", "0.01");
//                bundle.putString("orderShop", "测试商品");
//                bundle.putString("orderDetails", "测试测试测试测试");
//                bundle.putString("orderBianhao", "sctest1");
//                startActivity(PayWaySelectActivity.class,bundle);
                break;
            case R.id.hw:
                Bundle hw = new Bundle();
                hw.putString("name", "红网");
                hw.putString("address", "http://www.rednet.cn/index.html");
                intent.putExtras(hw);
                startActivity(intent);
                break;
        }
    }
}