package com.yxld.yxchuangxin.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.AppYezhuFangwu;
import com.yxld.yxchuangxin.entity.CxwyMallPezhi;
import com.yxld.yxchuangxin.entity.CxwyYezhu;
import com.yxld.yxchuangxin.entity.adapter.Wuye;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CxUtil {

	/** 保存当前选择小区*/
	private static String SAVEXIAOQUID = "SAVEXIAOQUID";
	/** 保存当前登录用户信息*/
	private static String SAVEYONGHU = "SAVEYONGHU";
	/** 保存当前登录业主信息*/
	private static String SAVEYEZHU = "SAVEYEZHU";

	/**
	 * 抖一抖动画
	 */
	public static void actionAndAction(View iv, Context context) {
		Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.action_and_action);
		iv.startAnimation(animation);
	}

	// 用户是否已经登陆
		public static boolean isLoginOk() {
            return !(Contains.user == null || Contains.user.getYezhuShouji() == null
                    || Contains.user.getYezhuPwd() == null);
        }

		// 购物车是否为空
		public static boolean cartIsNull() {
            return !(Contains.CartList != null && Contains.CartList.size() != 0);
        }

		/**
		 * ListView 加载动画
		 */
		public static LayoutAnimationController getListAnim() {
			AnimationSet set = new AnimationSet(true);
			Animation animation = new AlphaAnimation(0.0f, 1.0f);
			animation.setDuration(300);
			set.addAnimation(animation);

			animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
					Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
					1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
			animation.setDuration(500);
			set.addAnimation(animation);
			LayoutAnimationController controller = new LayoutAnimationController(
					set, 0.5f);
			return controller;
		}

		public static String getVersion(Context mContext) {
			try {
				PackageManager manager = mContext.getPackageManager();
				PackageInfo info = manager.getPackageInfo(
						mContext.getPackageName(), 0);
				String version = info.versionName;
				KLog.d("geek","version"+version);
				return version;
			} catch (Exception e) {
				e.printStackTrace();
				return mContext.getString(R.string.can_not_find_version_name);
			}
		}

	/**
	 * 设置数据至内存中
	 * @param outState
     */
	public static void setLoginData(Bundle outState){
		outState.putInt(SAVEXIAOQUID, Contains.curSelectXiaoQuId);
		outState.putSerializable(SAVEYONGHU,Contains.user);
		outState.putSerializable(SAVEYEZHU,(ArrayList)Contains.appYezhuFangwus);
	}

	/**
	 * 取出保存在内存中数据
	 * @param savedInstanceState
     */
	public static void getLogindata(Bundle savedInstanceState){
		Contains.curSelectXiaoQuId = savedInstanceState.getInt(SAVEXIAOQUID);
		Contains.user = (CxwyYezhu) savedInstanceState.getSerializable(SAVEYONGHU);
		Contains.appYezhuFangwus = (List<AppYezhuFangwu>) savedInstanceState.getSerializable(SAVEYEZHU);
		if(Contains.user == null || Contains.appYezhuFangwus == null){
			return;
		}
		if (Contains.appYezhuFangwus!=null && Contains.appYezhuFangwus.size() >0 ){
			Contains.curSelectXiaoQuName = Contains.appYezhuFangwus.get(0).getXiangmuLoupan();
			Contains.curSelectXiaoQuId = Contains.appYezhuFangwus.get(0).getFwLoupanId();

			//设置默认地址项目
			if(Contains.user.getYezhuName() != null && !"".equals(Contains.user.getYezhuName() )){
				Contains.defuleAddress.setAddName(Contains.user.getYezhuName());
			}else{
				Contains.defuleAddress.setAddName(Contains.user.getYezhuShouji());
			}
			Contains.defuleAddress.setAddTel(Contains.user.getYezhuShouji());
			Contains.defuleAddress.setAddAdd(Contains.appYezhuFangwus.get(0).getXiangmuLoupan()+
					Contains.appYezhuFangwus.get(0).getFwLoudong()+"栋"+
					Contains.appYezhuFangwus.get(0).getFwDanyuan()+"单元"+
					Contains.appYezhuFangwus.get(0).getFwFanghao());
		}
	}

	/**
	 * 退出登录
	 */
	public static void clearData(SharedPreferences sp){
		if(Contains.user != null){
			String alias = Contains.uuid;
//			JPushInterface.setAlias(AppConfig.app, "", new TagAliasCallback() {
//				@Override
//				public void gotResult(int i, String s, Set<String> set) {
//					Log.d("geek", "JPushInterface clearData  setAlias  gotResult: "+i);
//				}
//			});
//
//			JPushInterface.setTags(AppConfig.app, new HashSet<String>(), new TagAliasCallback() {
//				@Override
//				public void gotResult(int i, String s, Set<String> set) {
//					Log.d("geek", "gotResult: set clearData = "+set.toString());
//					Log.d("geek", "JPushInterface setTags clearData gotResult: "+i);
//				}
//			});
//                //alipush
			PushServiceFactory.getCloudPushService().removeAlias(null, new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.d("geek", "alipush clearData  setAlias  gotResult: "+s);
                }

                @Override
                public void onFailed(String s, String s1) {

                }
            });
//            PushServiceFactory.getCloudPushService().unbindTag(3, new String[]{}, alias, new CommonCallback() {
//                @Override
//                public void onSuccess(String s) {
//                    Log.d("geek", "gotResult: set clearData = "+s);
//                    Log.d("geek", "alipush setTags clearData gotResult: "+s);
//                }
//
//                @Override
//                public void onFailed(String s, String s1) {
//
//                }
//            });
		}
		Contains.shopCartNum=0;
		Contains.shopCartList.clear();
		Contains.curSelectXiaoQuName = "";
		Contains.curSelectXiaoQuId = 0;
		Contains.curFangwu = 0;
		Contains.user = null;
		Contains.appYezhuFangwus.clear();
		Contains.CartList.clear();
		Contains.defuleAddress = null;
		Contains.curCommData.clear();
		Contains.sureOrderList.clear();
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("NAME", "");
		editor.putString("PASSWORD", "");
		editor.putBoolean("ISCHECK", false);
		editor.putInt("xiangmuId", 0);
		editor.commit();

	}

	/***
	 * 获取微信是否安装
	 * @param context
	 * @return
	 */
	public static boolean isWeixinAvilible(Context context) {
		final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
		if (pinfo != null) {
			for (int i = 0; i < pinfo.size(); i++) {
				String pn = pinfo.get(i).packageName;
				if (pn.equals("com.tencent.mm")) {
					return true;
				}
			}
		}

		return false;
	}

	public static List<CxwyMallPezhi> loadMallHomeNormalMenuConfig(Context context){
		StringBuilder sb = new StringBuilder();
		BufferedReader reader =null;
		InputStream inStream= null;
		try {
			inStream = context.getAssets().open("mall_home_menu.json");
			reader = new BufferedReader(new InputStreamReader(inStream));
			String line = "";
			while ((line = reader.readLine())!=null){
				sb.append(line.trim());
			}

			return handlerMallHomeNormalMenu(sb.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(inStream!=null){
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private static List<CxwyMallPezhi> handlerMallHomeNormalMenu(String string) {
		List<Wuye.DataBean.ListBean> beans = new Gson().fromJson(string,new TypeToken<List<Wuye.DataBean.ListBean>>(){}.getType());
		List<CxwyMallPezhi> list = new ArrayList<>();
		for (Wuye.DataBean.ListBean bean : beans){
			CxwyMallPezhi pezhi = new CxwyMallPezhi();
			pezhi.setMallPeizhiValue(bean.getIcon());
			pezhi.setMallPeizhiBeiyong(bean.getName());
			list.add(pezhi);
		}
		return list;
	}
}
