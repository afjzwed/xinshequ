package com.yxld.yxchuangxin.application;

import android.app.ActivityManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.support.multidex.MultiDex;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.p2p.core.P2PSpecial.P2PSpecial;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.socks.library.KLog;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.videogo.openapi.EZOpenSDK;
import com.yxld.yxchuangxin.BuildConfig;
import com.yxld.yxchuangxin.Utils.AppActivityManager;
import com.yxld.yxchuangxin.Utils.swipeback.ActivityLifecycleHelper;
import com.yxld.yxchuangxin.db.green.DaoMaster;
import com.yxld.yxchuangxin.db.green.DaoSession;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wwx
 * @ClassName: AppConfig
 * @Description: Application 对象
 * @date 2015�?1�?6�?下午1:36:10
 */
public class AppConfig extends Application {
    PendingIntent restartIntent;
    /**
     * 配置文件�?
     */
    public String SP_FILE_NAME = "ChuangXinConfig";
    /**
     * 配置文件工具
     */
    private SharedPreferences sp = null;
    public static String ipAddress = "http://192.168.8.22:8080/wygl";

    /**
     * 运用list来保存每�?��activity
     */
    public static ImageLoader mImageLoader;
    /** 七牛*/
//	public static UploadManager uploadManager = new UploadManager();


    /**
     * 为了实现每次使用该类时不创建新的对象而创建的静�?对象
     */
    public static AppConfig instance;

    // 用于存放倒计时时间
    public static Map<String, Long> map;
    public static AppConfig app;
    public static LinkedHashMap<String, String> jilu;

    //摄像头
    public final static String APPID = "3b5766f0a536880c6a75e5a3965d81fa";
    public final static String APPToken = "14a19defd28f919d6d5bda9856bb1a93983a8441e57a9c990c319669ffda78a1";
    public final static String APPVersion = "03.94.01.03";
    private AppComponent mAppComponent;


    //公共安防
    public static String AppKey = "13edae3069574e6bad0c49474b9344da";

    public static EZOpenSDK getOpenSDK() {
        return EZOpenSDK.getInstance();
    }

    //滑动返回
    public ActivityLifecycleHelper mActivityLifecycleHelper;

    public AppActivityManager mAppActivityManager;

    public AppConfig() {

    }

    public static synchronized AppConfig getInstance() {
        if (null == instance) {
            instance = new AppConfig();
        }
        return instance;
    }

    // user your appid the key.
    private static final String APP_ID = "2882303761517529181";
    // user your appid the key.
    private static final String APP_KEY = "5791752929181";

    // 此TAG在adb logcat中检索自己所需要的信息， 只需在命令行终端输入 adb logcat | grep
    // com.xiaomi.mipushdemo
    public static final String TAG = "com.yxld.yxchuangxin";

//	//在自己的Application中添加如下代码
//	public static RefWatcher getRefWatcher(Context context) {
//		AppConfig application = (AppConfig) context
//				.getApplicationContext();
//		return application.refWatcher;
//	}
//
//	//在自己的Application中添加如下代码
//	private  RefWatcher refWatcher;
static {
    //设置全局的Header构建器
    SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
        @Override
        public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
         //   layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
            return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        }
    });
    //设置全局的Footer构建器
    SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
        @Override
        public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        }
    });
}
    @Override
    public void onCreate() {
        super.onCreate();
        setupApplicationComponent();
        app = this;
        initP2P(app);
        instance = this;
//		FIR.init(this);
        // 创建加载图片
//		Contains.loadingImg = LoadingImg.LoadingImgs(getApplicationContext());
//
//		Fresco.initialize(this);
        // 初始化配置文�?
        sp = getSharedPreferences(SP_FILE_NAME, MODE_PRIVATE);

        File cacheDir = new File(this.getCacheDir(), "volley");
        // TODO: 2017/11/3 bugly的key要改
//        CrashReport.initCrashReport(this, "b272dac831", BuildConfig.LOG_DEBUG);
        CrashReport.initCrashReport(this, "86d37d22a0", true);
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
        //初始化log打印
        KLog.init(BuildConfig.LOG_DEBUG);
      //  KLog.i("geek", "onCreate: jpush id " + JPushInterface.getRegistrationID(this));
        KLog.i("----------------------------------------应用初始化---------------------------------");
        //在自己的Application中添加如下代码
//		refWatcher = LeakCanary.install(this);

//		// 以下用来捕获程序崩溃异常
//		Intent intent = new Intent();
//		// 参数1：包名，参数2：程序入口的activity
//		intent.setClassName("com.yxld.yxchuangxin", "com.yxld.yxchuangxin.activity.login.WelcomeActivity");
//		intent.setClass(this,WelcomeActivity.class);
//		restartIntent = PendingIntent.getActivity(getApplicationContext(), 0,
//				intent,0);
//		Thread.setDefaultUncaughtExceptionHandler(restartHandler); // 程序崩溃时触发线程

        //滑动返回
        registerActivityLifecycleCallbacks(ActivityLifecycleHelper.build());

        //initCommon();
        mAppActivityManager = new AppActivityManager(this);
//		LeakCanary.install(this);

        /**
         * 以下是FunSDK初始化
         */
        //FunSupport.getInstance().init(this);

        //友盟统计
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数3:Push推送业务的secret
         */
        UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE,"");
//        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

        // 初始化阿里云推送
        initCloudChannel(this);
    }
    static DaoSession mDaoSessin;
    public static DaoSession getGreenDaoSession() {
        if (mDaoSessin == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(instance, "user-db", null);
            DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSessin = daoMaster.newSession();
        }
        return mDaoSessin;
    }


    public ActivityLifecycleHelper getActivityLifecycleHelper() {
        return mActivityLifecycleHelper;
    }

    protected void setupApplicationComponent() {
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .aPIModule(new APIModule(this))
//                .databaseModule(new DatabaseModule(this))
                .build();
        mAppComponent.inject(this);
    }

    public AppComponent getApplicationComponent() {
        return mAppComponent;
    }


    public void exit() {
//		if (mListActivity != null) {
//			for (Activity activity : mListActivity) {
//				if (activity != null && !activity.isFinishing())
//					activity.finish();
//			}
//		}
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    public SharedPreferences getSp() {
        return sp;
    }

    public void setSp(SharedPreferences sp) {
        this.sp = sp;
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 分割 Dex 支持
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void initCommon() {
        /**
         * sdk日志开关，正式发布需要去掉
         */
//			EZOpenSDK.showSDKLog(true);

        /**
         * 设置是否支持P2P取流,详见api
         */
        EZOpenSDK.enableP2P(false);

        /**
         * APP_KEY请替换成自己申请的
         */
        EZOpenSDK.initLib(this, AppKey, "");
    }

    /**
     * 初始化技威
     * 居家安防摄像头
     * @param app
     */
    public void initP2P(AppConfig app) {
        P2PSpecial.getInstance().init(app, APPID, APPToken, APPVersion);
    }

    /**
     * 初始化云推送通道
     *
     * @param applicationContext
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                PushServiceFactory.getCloudPushService().onAppStart();
                KLog.i("geek", "阿里云推送初始化成功" + PushServiceFactory.getCloudPushService().getDeviceId());
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
            }
        });
    }

}
