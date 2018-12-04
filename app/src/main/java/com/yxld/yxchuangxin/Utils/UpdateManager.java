package com.yxld.yxchuangxin.Utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.ui.activity.main.SplashActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 更新管理器
 */
@SuppressLint({"InflateParams", "HandlerLeak", "SdCardPath"})
public class UpdateManager {

    private Context mContext;

    // 提示语
    private String updateMsg = "有最新的软件包哦，亲快下载吧~";

    // 返回的安装包url
    private String apkUrl = "";

    private Dialog noticeDialog;

    private Dialog downloadDialog;
    /* 下载包安装路径 */
    private static final String savePath = "/sdcard/updatedemo/";

    private static final String saveFileName = savePath
            + "UpdateDemoRelease.apk";

    /* 进度条与通知ui刷新的handler和msg常量 */
    private ProgressBar mProgress;

    private TextView curProgress, totalProgress, cancel;

    private static final int DOWN_UPDATE = 1;

    private static final int DOWN_OVER = 2;

    private int progress;

    private Thread downLoadThread;

    private boolean interceptFlag = false;
    private Handler mhander;

    private OnYiHouOnClickListener onYiHouOnClickListener;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE:
                    mProgress.setProgress(progress);
                    Log.d("geek", "progress" + progress);
                    curProgress.setText(progress + "%");
                    totalProgress.setText(progress + "/100");
                    break;
                case DOWN_OVER:
                    installApk();
                    break;
                default:
                    break;
            }
        }
    };

    public UpdateManager(Context context, String apkUrl) {
        this.mContext = context;
        this.apkUrl = apkUrl;
    }

    public UpdateManager(Context context, String apkUrl, Handler handler) {
        this.mContext = context;
        this.apkUrl = apkUrl;
        this.mhander = handler;
        Log.d("geek", "apkUrl" + apkUrl);
    }

    // 外部接口让主Activity调用
    public void checkUpdateInfo(String versionCode, String versionMsg, Integer versionIsCompulsory) {
        showNoticeDialog(versionCode, versionMsg, versionIsCompulsory);
    }

    private void showNoticeDialog(String versionCode, String versionMsg, Integer versionIsCompulsory) {
        Builder builder = new Builder(mContext);
        builder.setTitle("版本更新" + versionCode);
        builder.setMessage(updateMsg + "\n" + "版本号:" + versionCode + "\n" + "版本描述:" + versionMsg);
        builder.setCancelable(false);
        //强制更新
        if (versionIsCompulsory == 1) {
            builder.setPositiveButton("立即更新", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
//					showDownloadDialog(true);
                    // TODO: 2018/12/4 不显示下载进度条，直接跳应用宝下载链接
                    intit_getClick();
                }
            });
        } else {
            builder.setPositiveButton("立即更新", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
//					showDownloadDialog(false);
                    // TODO: 2018/12/4 不显示下载进度条，直接跳应用宝下载链接
                    intit_getClick();
                }
            });
            builder.setNegativeButton("以后再说", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    if (onYiHouOnClickListener != null) {
//                        onYiHouOnClickListener.onYihouClick();
//                    }
                    dialog.dismiss();
                    if (mhander != null) {
                        mhander.sendEmptyMessage(SplashActivity.LOCATION_FINISH);
                    }
                }
            });
        }
        noticeDialog = builder.create();
        noticeDialog.show();
    }

    private void showDownloadDialog(boolean type) {
        Builder builder = new Builder(mContext);
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.progress);
        curProgress = (TextView) v.findViewById(R.id.curProgress);
        totalProgress = (TextView) v.findViewById(R.id.totalProgress);
        cancel = (TextView) v.findViewById(R.id.cancel);
        builder.setView(v);
        builder.setCancelable(false);
        if (type) {
            cancel.setVisibility(View.GONE);
        } else {
            cancel.setVisibility(View.VISIBLE);
        }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (downloadDialog != null) {
                    downloadDialog.dismiss();
                }
                interceptFlag = true;
                if (mhander != null) {
                    mhander.sendEmptyMessage(SplashActivity.LOCATION_FINISH);
                }
            }
        });
        downloadDialog = builder.create();
        downloadDialog.setCancelable(false);
        downloadDialog.show();

        downloadApk();
    }

    //    检查版本更新，跳转到腾讯应用宝进行下载
    private void intit_getClick() {
//		if (isAvilible(mContext, "com.tencent.android.qqdownloader")) {// 市场存在
//			Log.e("wh", "跳转下载");
////			launchAppDetail(AppConfig.getInstance(), "com.yxld.yxchuangxin.xsq", "com.tencent.android
/// .qqdownloader");
//		} else {
//			Uri uri = Uri.parse("http://a.app.qq.com/o/simple.jsp?pkgname=com.yxld.yxchuangxin.xsq");
//			Intent it = new Intent(Intent.ACTION_VIEW, uri);
//			mContext.startActivity(it);
//		}

//		Log.e("wh", "跳转下载");
        Uri uri = Uri.parse("http://a.app.qq.com/o/simple.jsp?pkgname=com.yxld.yxchuangxin.xsq");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(it);
//		SplashActivity activity = (SplashActivity) mContext;
//		activity.startActivityForResult(it, 66);
    }

    // 判断市场是否存在的方法
    public static boolean isAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        List<String> pName = new ArrayList<String>();// 用于存储所有已安装程序的包名
// 从pinfo中将包名字逐一取出，压入pName list中
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);// 判断pName中是否有目标程序的包名，有TRUE，没有FALSE
    }

    /**
     * 启动到app详情界面
     *
     * @param appPkg    App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public static void launchAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) {
                return;
            }
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(apkUrl);

                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();

                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                String apkFile = saveFileName;
                File ApkFile = new File(apkFile);
                FileOutputStream fos = new FileOutputStream(ApkFile);

                int count = 0;
                byte buf[] = new byte[1024];

                do {
                    int numread = is.read(buf);
                    count += numread;
                    progress = (int) (((float) count / length) * 100);
                    // 更新进度
                    mHandler.sendEmptyMessage(DOWN_UPDATE);
                    if (numread <= 0) {
                        // 下载完成通知安装
                        mHandler.sendEmptyMessage(DOWN_OVER);
                        break;
                    }
                    fos.write(buf, 0, numread);
                } while (!interceptFlag);// 点击取消就停止下载.

                fos.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    /**
     * 下载apk
     */

    private void downloadApk() {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    /**
     * 安装apk
     */
    private void installApk() {
        File apkfile = new File(saveFileName);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
                "application/vnd.android.package-archive");
        mContext.startActivity(i);

    }

    public interface OnYiHouOnClickListener {
        void onYihouClick();
    }


    public void setOnYiHouOnClickListener(OnYiHouOnClickListener onYiHouOnClickListener) {
        this.onYiHouOnClickListener = onYiHouOnClickListener;
    }
}
