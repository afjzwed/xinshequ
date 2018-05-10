package com.yxld.yxchuangxin.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.Utils.DoubleClickExitHelper;
import com.yxld.yxchuangxin.Utils.JPushUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyMallAdd;
import com.yxld.yxchuangxin.entity.CxwyYezhu;
import com.yxld.yxchuangxin.entity.EventBusEntity;
import com.yxld.yxchuangxin.entity.MainToMarket;
import com.yxld.yxchuangxin.entity.TabDataBean;
import com.yxld.yxchuangxin.ui.activity.goods.MallFragment;
import com.yxld.yxchuangxin.ui.activity.login.LoginActivity;
import com.yxld.yxchuangxin.ui.activity.rim.RimFragment;
import com.yxld.yxchuangxin.view.MenuView;
import com.zhy.autolayout.AutoFrameLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.realtabcontent)
    AutoFrameLayout realtabcontent;
    @BindView(android.R.id.tabcontent)
    AutoFrameLayout tabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;
//    private Intent main;
//    private Intent wuye;
//    private Intent zhoubian;
//    private Intent shangcheng;
    private ArrayList<TabDataBean> tabDataList = new ArrayList<>(4);

    private static final String ACTION_CHANGETAB = "com.yxld.yxchuangxin.ui.activity.main.HomeActivity.action.changetab";//(没有实例则创建实例)changeTabIndex
    private static final String EXTRA_TABINDEX = "com.yxld.yxchuangxin.ui.activity.main.HomeActivity.extra.tabindex";

    private DoubleClickExitHelper doulebClickAction;
    private LayoutInflater mInflater;

    public static void startActionChangeTab(Context context, int tabIndex) {
        Intent changeTabIntent = new Intent(context, HomeActivity.class);
        changeTabIntent.setAction(ACTION_CHANGETAB);
        changeTabIntent.putExtra(EXTRA_TABINDEX, tabIndex);
        context.startActivity(changeTabIntent);
    }

    private void handleActionChangeTab(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (action != null) {
                switch (action) {
                    case ACTION_CHANGETAB:
                        int tabIndex = intent.getIntExtra(EXTRA_TABINDEX, 0);
                        tabhost.setCurrentTab(tabIndex);
                        break;

                    default:
                        break;
                }
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("uuid", Contains.uuid);
        outState.putString("curSelectXiaoQuName", Contains.curSelectXiaoQuName);
        outState.putInt("curSelectXiaoQuId", Contains.curSelectXiaoQuId);
        outState.putSerializable("user", Contains.user);
        outState.putSerializable("defuleAddress", Contains.defuleAddress);
        outState.putParcelableArrayList("appYezhuFangwus", (ArrayList<? extends Parcelable>) Contains.appYezhuFangwus);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Contains.uuid = savedInstanceState.getString("uuid");
            Contains.curSelectXiaoQuName = savedInstanceState.getString("curSelectXiaoQuName");
            Contains.curSelectXiaoQuId = savedInstanceState.getInt("curSelectXiaoQuId");
            Contains.user = (CxwyYezhu) savedInstanceState.getSerializable("user");
            Contains.defuleAddress = (CxwyMallAdd) savedInstanceState.getSerializable("defuleAddress");
            Contains.appYezhuFangwus = savedInstanceState.getParcelableArrayList("appYezhuFangwus");
        }
        super.onRestoreInstanceState(savedInstanceState);
    }


    /**
     * 在mainactivity中发过来的消息
     *
     * @param mainToMarket
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainToMarket(MainToMarket.Main2Market mainToMarket) {
        tabhost.setCurrentTab(3);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOutLogin(String outlogin) {
        if ("退出登录".equals(outlogin)) {
            CxUtil.clearData(sp);
            PushServiceFactory.getCloudPushService().removeAlias(null, new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    KLog.i("阿里云推送设置移除成功" +  "removeAlias success"+s);
                }

                @Override
                public void onFailed(String s, String s1) {

                }
            });
            PushServiceFactory.getCloudPushService().unbindAccount(new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    KLog.i("阿里云推送解除绑定账号" +  "removeAlias success"+s);
                }

                @Override
                public void onFailed(String s, String s1) {

                }
            });
            startActivity(LoginActivity.class);
        }
        if (outlogin.equals("reLogin")) {
            //极光
            initPush();
            //阿里
            initALPush();
            handleActionChangeTab(getIntent());
            doulebClickAction = new DoubleClickExitHelper();
//            initTabHost();
        }
    }

    /**
     * 在@link HttpAPIWrapper 中发过来的消息
     *
     * @param entity
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginTimeOut(EventBusEntity.Entity entity) {
        KLog.i("收到eventbus消息");
        if (entity == EventBusEntity.Entity.loginTimeOut) {
//            ToastUtil.show(this, "登录失效，请重新登录");
            Intent intent = new Intent(this, SplashActivity.class);
//            intent.putExtra("flag", "other");
            CxUtil.clearData(sp);
            startActivity(intent);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar.setVisibility(View.GONE);
        setContentView(R.layout.activity_home);
        AppConfig.getInstance().mAppActivityManager.finishAllActivityWithoutThis();
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        mInflater = LayoutInflater.from(this);
        //极光
        initPush();
        //阿里
        initALPush();
        handleActionChangeTab(getIntent());
        doulebClickAction = new DoubleClickExitHelper();
        initTabHost();

        //友盟统计
        //当用户使用自有账号登录时，可以这样统计：统计UUID
        MobclickAgent.onProfileSignIn(Contains.uuid);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setupActivityComponent() {

    }

    private void initTabHost() {
        //初始化fTabHost, 第三个参数为内容容器
        tabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        /*初始化数据源*/
        TabDataBean tabHome = new TabDataBean(R.string.main_main, R.drawable.tab_main_selector, MainFragment.class);
        TabDataBean tabHot = new TabDataBean(R.string.main_wuye, R.drawable.tab_wuye_selector, WuyeFragment.class);
        TabDataBean tabCategory = new TabDataBean(R.string.main_zhoubian, R.drawable.tab_rim_selector, RimFragment.class);
        TabDataBean tabCart = new TabDataBean(R.string.main_shangcheng, R.drawable.tab_market_selector, MallFragment.class);
        tabDataList.add(tabHome);
        tabDataList.add(tabHot);
        tabDataList.add(tabCategory);
        tabDataList.add(tabCart);
        //添加底部菜单项-tabSpec
        for (TabDataBean bean : tabDataList) {
            TabHost.TabSpec tabSpec = tabhost.newTabSpec(getString(bean.getTabName()));
            //给菜单项添加内容，indicator,其中indicator需要的参数View即为菜单项的布局
            tabSpec.setIndicator(getIndicatorView(bean));
            //第二参数就是该菜单项对应的页面内容
            tabhost.addTab(tabSpec, bean.getContent(), null);
        }
    }

    private View getIndicatorView(TabDataBean bean) {
        View view = mInflater.inflate(R.layout.tabhost_tabspec_normal_layout, null);
        ImageView iconTab = (ImageView) view.findViewById(R.id.iv_tab_icon);
        TextView tvTab = (TextView) view.findViewById(R.id.tv_tab_label);
        iconTab.setImageResource(bean.getTabIcon());
        tvTab.setText(bean.getTabName());
        return view;
    }

    private android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            String alias = Contains.user.getYezhuShouji();
            JPushInterface.setAlias(HomeActivity.this, alias, tagAliasCallback);
        }
    };

    private TagAliasCallback tagAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> set) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    KLog.i("极光设置别名成功" + logs);
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 30s.";
                    KLog.i("极光设置别名失败" + logs);
                    if (JPushUtil.isConnected(getApplicationContext())) {
                        mHandler.sendEmptyMessageDelayed(0, 1000 * 30);
                    } else {
                        KLog.i("No network");
                    }
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    KLog.i("极光设置别名失败,错误码为:" + logs);
            }
        }
    };
    private void initALPush(){
        PushServiceFactory.getCloudPushService().addAlias(StringUitl.getDeviceId(this), new CommonCallback() {
            @Override
            public void onSuccess(String s) {

                KLog.i("阿里云推送设置添加别名成功" +StringUitl.getDeviceId(HomeActivity.this));
            }

            @Override
            public void onFailed(String s, String s1) {

            }
        });
        PushServiceFactory.getCloudPushService().listAliases(new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                KLog.i("阿里云查询别名成功" +s);
            }

            @Override
            public void onFailed(String s, String s1) {

            }
        });
        PushServiceFactory.getCloudPushService().bindAccount(Contains.user.getYezhuShouji(), new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                KLog.i("阿里云推送设置绑定账号成功" +Contains.user.getYezhuShouji());
            }

            @Override
            public void onFailed(String s, String s1) {

            }
        });

    }
    private void initPush() {
        String alias = Contains.user.getYezhuShouji();
        //极光推送 设置别名和标签
//        JPushInterface.setAlias(HomeActivity.this, alias, tagAliasCallback);
//        Set<String> tags = new ArraySet<>();
//        tags.add(StringUitl.getDeviceId(this));
//        Log.d("geek", "initContentView: tags=" + StringUitl.getDeviceId(this));
//        JPushInterface.setTags(HomeActivity.this, tags, new TagAliasCallback() {
//            @Override
//            public void gotResult(int i, String s, Set<String> set) {
//                Log.d("geek", "gotResult: set = " + set.toString());
//                Log.d("geek", "JPushInterface setTags gotResult: " + i);
//            }
//        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleActionChangeTab(getIntent());
    }

    private TabHost.TabSpec buildTabSpec(int index, String tag, Intent intent) {
        if (tabhost != null) {
            View view;
            view = View.inflate(this, R.layout.tabhost_tabspec_normal_layout, null);
            ImageView iv_tab_icon = (ImageView) view.findViewById(R.id.iv_tab_icon);
            TextView tv_tab_label = (TextView) view.findViewById(R.id.tv_tab_label);
            if (index == 0) {
                iv_tab_icon.setImageResource(R.drawable.tab_main_selector);
                tv_tab_label.setText("首页");
            } else if (index == 1) {
                iv_tab_icon.setImageResource(R.drawable.tab_wuye_selector);
                tv_tab_label.setText("欣物业");
            } else if (index == 2) {
                iv_tab_icon.setImageResource(R.drawable.tab_rim_selector);
                tv_tab_label.setText("欣周边");
            } else if (index == 3) {
                iv_tab_icon.setImageResource(R.drawable.tab_market_selector);
                tv_tab_label.setText("欣商城");
            }
            return tabhost.newTabSpec(tag).setIndicator(view)
                    .setContent(intent);
        }
        return null;
    }

    private boolean onBack() {
        return doulebClickAction.onBackKeyDown();
    }

    // 用下面这个函数拦截子Activity的返回操作
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getRepeatCount() == 0) {
            if (MenuView.getInstance() != null && MenuView.getInstance().isShow) {
                MenuView.getInstance().close();
                return true;
            }
            return onBack();
        }
        return super.dispatchKeyEvent(event);//此处默认是false
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);//统计时长
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);//统计时长
    }
}
