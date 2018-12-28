package com.yxld.yxchuangxin.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.Utils.Network;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.Utils.swipeback.SwipeBackHelper;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.db.DBUtil;
import com.yxld.yxchuangxin.ui.activity.login.LoginActivity;
import com.yxld.yxchuangxin.view.ProgressDialog;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//import com.orhanobut.logger.Logger;

/**
 * @author wwx
 * @ClassName: BaseActivity
 * @Description: 基础Activity�?
 * @date 2015�?1�?6�?下午1:37:53
 */
public abstract class BaseActivity extends AppCompatActivity implements ActivityDelegate, SwipeBackHelper.SlideBackManager{
    /**
     * 服务器请求成功 --欣商城 --欣物业
     */
    public static final int STATUS_CODE_OK = 0;

    /**
     * 服务器请求成功--欣周边
     */
    protected static final int STATUS_CODE_OK_SERVICE = 1;

    /**
     * 数据库操作类
     */
    protected DBUtil dbUtil;


    /**
     * 标题栏
     */
    protected TextView tv_title, rightTv;

    protected ImageView returnWrap;

    public RelativeLayout headerWarp;

    public AutoRelativeLayout rootLayout;

    public Toolbar toolbar;
    public TextView tvMenu;

    /**
     * 数据加载失败时显示的视图
     */
    protected View ly_loading_failed;

    /**
     * 公共的加载进度弹窗
     */
    public ProgressDialog progressDialog;
    /**
     * 没有数据时显示的布局
     */
    protected View ly_empty_data;
    /**
     * 页码
     */
    protected int pageCode = 0;

    public AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;


    public static List<String> logList = new CopyOnWriteArrayList<String>();

    public SharedPreferences sp;
    public boolean needFront = false;   //toolBar 是否需要显示在最上层的标识

    public SwipeBackHelper mSwipeBackHelper;
//    private View view_tongzhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //当该变量为空，说明内存被回收 ，清空业主房屋等信息，重新进入欢迎界面
        if (Contains.isKill == null || "".equals(Contains.isKill)) {
//			Logger.d("BaseActivity onCreate isKill为空");
            CxUtil.clearData(AppConfig.app.getSp());
//			startActivity(WelcomeActivity.class);
            return;
        }
//		Logger.d("Contains.isKill ="+Contains.isKill);

        if (savedInstanceState != null) {
            //取出保存在savedInstanceState中
//			Logger.d("BaseActivity onRestoreInstanceState() savedInstanceState != null");
            CxUtil.getLogindata(savedInstanceState);
        }
        // 这句很关键，注意是调用父类的方法
        super.setContentView(R.layout.activity_base);
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        initToolbar();

        setupActivityComponent();

//		//在自己的应用初始Activity中加入如下两行代码
//		RefWatcher refWatcher = AppConfig.getRefWatcher(this);
//		refWatcher.watch(this);

        progressDialog = new ProgressDialog(this);
//		progressDialog.setMyCancelListener(this);

        dbUtil = new DBUtil(this);

//        locationClient = new AMapLocationClient(this);
//        locationOption = new AMapLocationClientOption();
////		// 设置定位模式为高精度模式
//        locationOption
//                .setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        locationOption.setOnceLocation(false);
//        initOption();


//		initTitle();

        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        AppConfig.getInstance().mAppActivityManager.addActivity(this);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化dagger2
     */
    protected abstract void setupActivityComponent();


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarBusiness);
        tvMenu = (TextView) findViewById(R.id.tv_menu);
        AutoRelativeLayout.LayoutParams lp = new AutoRelativeLayout.LayoutParams(UIUtils.getDisplayWidth(this), (int)(UIUtils.getStatusBarHeight(this) * 3));
        toolbar.setLayoutParams(lp);
        toolbar.setPadding(0, (int) (UIUtils.getStatusBarHeight(this) * 0.6), 0, 0);
        toolbar.setTitleMarginTop((int) (UIUtils.getStatusBarHeight(this) *0.55));
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    // 根据控件的选择，重新设置定位参数
    private void initOption() {
        // 设置是否需要显示地址信息
        locationOption.setNeedAddress(true);
        String strInterval = "10000";
        if (!TextUtils.isEmpty(strInterval)) {
            // 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
            locationOption.setInterval(Long.valueOf(strInterval));
        }
        // 设置定位参数
        locationClient.setLocationOption(locationOption);

    }

    /**
     * @return boolean
     * @throws
     * @Title: netWorkIsAvailable
     * @Description: 网络是否可用
     */
    public boolean netWorkIsAvailable() {
        return Network.isAvailableByPing(this);
    }

    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (AutoRelativeLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) return;
        if (needFront) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.color_00000000));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            rootLayout.addView(view, params);
            toolbar.bringToFront();
        } else {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.addRule(RelativeLayout.BELOW, R.id.toolbarBusiness);
            rootLayout.addView(view, params);
        }
        initToolbar();
    }

    /**
     * 初始化网络数据
     */
    protected void initDataFromNet() {
        progressDialog.show();
    }

    /**
     * 初始化界面头部
     */
    protected void initTitle() {
//		headerWarp = (RelativeLayout) findViewById(R.id.header);
//		tv_title = (TextView) findViewById(R.id.searchCate);
//		returnWrap = (ImageView) findViewById(R.id.returnWrap);
//		rightTv = (TextView) findViewById(R.id.rightTv);
//
//		ly_loading_failed = findViewById(R.id.ly_loading_failed);
//		ly_empty_data = findViewById(R.id.ly_empty_data);
//		xlistView = (XListView) findViewById(R.id.xListView);
//
//
//		if (ly_loading_failed != null) {
//			ly_loading_failed.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					initDataFromNet();
//				}
//			});
//		}
//		if (xlistView != null) {
//			xlistView.setXListViewListener(this);
//			xlistView.setOnItemClickListener(this);
//		}
    }

    /**
     * 设置页面标题文字
     *
     * @param title
     */
    public void setTitle(String title) {
        if (tv_title == null) {
            return;
        }
        if (TextUtils.isEmpty(title)) {
            title = "创欣";
            return;
        }
        tv_title.setText(title);
    }

    public void setToorBar(boolean isVisitiy) {
        if (toolbar != null) {
            if (isVisitiy) {
                toolbar.setVisibility(View.VISIBLE);
            } else {
                toolbar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        try {
            dbUtil.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 停止定位
        if (locationClient != null && locationClient.isStarted()) {
            locationClient.stopLocation();
        }
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }

        AppConfig.getInstance().mAppActivityManager.removeActivity(this);
        super.onDestroy();
    }


    @Override
    protected void onPause() {
        super.onPause();
//		Logger.d("BaseActivity onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
//		Logger.d("BaseActivity onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
//		Logger.d("BaseActivity onResume ");
    }

    /**
     * 启动Activity
     *
     * @param clazz
     */
    protected <T> void startActivity(Class<T> clazz) {
        Intent intent = new Intent(this, clazz);
        try {
            startActivity(intent);
        } catch (Exception e) {
            ToastUtil.show(this, "敬请期待！");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 启动Activity
     *
     * @param clazz
     * @param bundle
     */
    protected <T> void startActivity(Class<T> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        try {
            startActivity(intent);
        } catch (Exception e) {
            ToastUtil.show(this, "敬请期待！");
        }
    }

    public void onError(String errMsg) {
        if (!this.isFinishing()) {
            if (!netWorkIsAvailable()) {
                ToastUtil.show(this, "网络连接失败，请检查您的网络状态");
            } else {
                if (StringUitl.isNoEmpty(errMsg)) {
                    ToastUtil.show(this, errMsg);
                }
            }
            resetView();
            showErrorPage(true);
        }
    }

    /**
     * @param status
     * @param errMsg
     */
    public void onError(String errMsg, int status) {
        if (!this.isFinishing()) {
            if (!netWorkIsAvailable()) {
                ToastUtil.show(this, "网络连接失败，请检查您的网络状态");
            } else if (status == 99 || status == -99) {
                ToastUtil.show(this, "登录失效，请重新登录");
                //退出登录信息
                CxUtil.clearData(sp);
                finish();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("flag", "other");
                startActivity(intent);
            } else {
                if (StringUitl.isNoEmpty(errMsg)) {
                    ToastUtil.show(this, errMsg);
                }
            }
            resetView();
            showErrorPage(true);
        }
    }

    /**
     *
     */
    protected void resetView() {
        progressDialog.hide();
        showEmptyDataPage(false);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //将用户信息房屋信息及选择楼盘信息 保存至outState中
        if (Contains.user != null && (ArrayList) Contains.appYezhuFangwus != null) {
//			Logger.d("BaseActivity onSaveInstanceState user="+Contains.user+",house="+(ArrayList)Contains.appYezhuFangwus);
            CxUtil.setLoginData(outState);
        } else {
//			Logger.d("BaseActivity onSaveInstanceState ");
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        try {
//			Logger.d("BaseActivity onRestoreInstanceState()");
            if (savedInstanceState != null) {
                //取出保存在savedInstanceState中数据
//			   Logger.d("BaseActivity onRestoreInstanceState() savedInstanceState != null");
                CxUtil.getLogindata(savedInstanceState);
            }
            super.onRestoreInstanceState(savedInstanceState);
        } catch (Exception e) {
        }
    }


    /**
     * 显示错误页面
     *
     * @param show
     */
    public void showErrorPage(boolean show) {
        if (ly_empty_data != null) {
            ly_empty_data.setVisibility(View.GONE);
        }
        if (ly_loading_failed == null) {
            return;
        }
        if (show) {
            ly_loading_failed.setVisibility(View.VISIBLE);
        } else {
            ly_loading_failed.setVisibility(View.GONE);
        }

    }

    /**
     * 显示空内容页面
     *
     * @param show
     */
    public void showEmptyDataPage(boolean show) {
        if (ly_loading_failed != null) {
            ly_loading_failed.setVisibility(View.GONE);
        }
        if (ly_empty_data == null) {
            return;
        }
        if (show) {
            ly_empty_data.setVisibility(View.VISIBLE);
        } else {
            ly_empty_data.setVisibility(View.GONE);
        }
    }

    public void refreshLogInfo() {
        String AllLog = "";
        for (String log : logList) {
            AllLog = AllLog + log + "\n\n";
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(mSwipeBackHelper == null) {
            mSwipeBackHelper = new SwipeBackHelper(this);
        }
        return mSwipeBackHelper.processTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    @Override
    public void destoryContainer() {
        finish();
    }

    @Override
    public BaseActivity getContainerActivity() {
        return this;
    }

    @Override
    public boolean isContainerDead() {
        if (Build.VERSION.SDK_INT > 16) {
            return this.isDestroyed();
        } else {
            return this.isFinishing();
        }
    }

    @Override
    public Activity getSlideActivity() {
        return this;
    }

    @Override
    public boolean supportSlideBack() {
        return true;
    }

    @Override
    public boolean canBeSlideBack() {
        return true;
    }

    @Override
    public void finish() {
        if(mSwipeBackHelper != null) {
            mSwipeBackHelper.finishSwipeImmediately();
            mSwipeBackHelper = null;
        }
        super.finish();
    }

    @Override
    public void onBackPressed() {
        if(mSwipeBackHelper == null) {
            mSwipeBackHelper = new SwipeBackHelper(this);
        }
        if (mSwipeBackHelper.mIsSlideAnimPlaying) {
            return;
        }
        super.onBackPressed();
    }
}
