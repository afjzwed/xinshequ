package com.yxld.yxchuangxin.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.Utils.Network;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.db.DBUtil;
import com.yxld.yxchuangxin.ui.activity.login.LoginActivity;
import com.yxld.yxchuangxin.view.ProgressDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BaseFragment 
 * @Description: 基础Fragment�?
 * @author wwx
 * @date 2015�?1�?6�?下午1:38:32 
 */
@SuppressLint("ShowToast")
public abstract class BaseFragment extends Fragment {
	protected boolean isVisible;
	/**
	 * 在这里实现Fragment数据的缓加载.
	 * @param isVisibleToUser
	 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if(getUserVisibleHint()) {
			isVisible = true;
		} else {
			isVisible = false;
		}
	}

	/** 服务器请求成功 */
	protected static final int STATUS_CODE_OK = 0;
	/** 服务器请求成功--欣周边 */
	protected static final int STATUS_CODE_OK_SERVICE = 1;
	
	/**
	 * 数据库操作类
	 */
	protected DBUtil dbUtil;

	/**
	 * 进度条加载
	 */
	protected ProgressDialog progressDialog;
	
	/**
	 * 根布局
	 */
	protected View rootView;
	/**
	 * 数据加载失败后显示的布局
	 */
	protected View ly_loading_failed;
	/**
	 * 没有数据时显示的布局
	 */
	protected View ly_empty_data;
	/**
	 * 页码
	 */
	protected int pageCode = 0;

	public SharedPreferences sp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//当该变量为空，说明内存被回收 ，清空业主房屋等信息，重新进入欢迎界面
		if (Contains.isKill == null|| "".equals(Contains.isKill)) {
//			Logger.d("BaseFragment onCreate isKill为空");
			return;
		}

//		//在自己的应用初始Activity中加入如下两行代码
//		RefWatcher refWatcher = AppConfig.getRefWatcher(getActivity());
//		refWatcher.watch(this);
		if (savedInstanceState != null) {
			//取出保存在savedInstanceState中
//			Logger.d("BaseActivity onRestoreInstanceState() savedInstanceState != null");
			CxUtil.getLogindata(savedInstanceState);
		}
//		AppConfig.getInstance().addFragment(this);
		
		if (dbUtil == null) {
			dbUtil = new DBUtil(getActivity());
		}

		sp = this.getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		setupFragmentComponent();
	}

	public Activity getContainerActivity() {
		return getActivity();
	}
	
	/**
	 * @Title: netWorkIsAvailable
	 * @Description: 网络是否可用
	 * @return
	 * @return boolean
	 * @throws
	 */
	public boolean netWorkIsAvailable() {
        return Network.isAvailableByPing(getActivity());
	}

	protected abstract void setupFragmentComponent();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		initView(inflater, container);
		initDataFromLocal();
		return rootView;
	}

	/**
	 * 加载界面控件
	 */
	protected void initView(LayoutInflater inflater, ViewGroup container) {

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		progressDialog = new ProgressDialog(activity);
//		progressDialog.setMyCancelListener(this);
	}

	/**
	 * 获取网络数据
	 * 
	 * @return
	 */
	protected void initDataFromNet() {
		progressDialog.show();
	}

	/**
	 * 获取本地数据
	 * 
	 * @return
	 */
	protected abstract void initDataFromLocal();

	/**
	 * 启动Activity
	 * 
	 * @param <T>
	 * @param clazz
	 */
	protected <T> void startActivity(Class<T> clazz) {
		Intent intent = new Intent(getActivity(), clazz);
		try {
			startActivity(intent);
		} catch (Exception e) {
			Toast.makeText(getActivity(), "敬请期待！"+ clazz.getSimpleName()
					+ "未注册！", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 启动Activity
	 * 
	 * @param clazz
	 */
	protected <T> void startActivity(Class<T> clazz, Bundle bundle) {
		Intent intent = new Intent(getActivity(), clazz);
		intent.putExtras(bundle);
		try {
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getActivity(), "敬请期待！"+ clazz.getSimpleName()
					+ "未注册！", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 启动Activity
	 * 
	 * @param clazz
	 */
	protected <T> void startActivity4Result(Class<T> clazz, Bundle bundle,
                                            int requestCode) {
		Intent intent = new Intent(getActivity(), clazz);
		intent.putExtras(bundle);
		try {
			getActivity().startActivityForResult(intent, requestCode);
		} catch (Exception e) {
			Toast.makeText(getActivity(), "敬请期待！"+ clazz.getSimpleName()
					+ "未注册！", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 启动Activity
	 * 
	 * @param clazz
	 */
	protected <T> void startActivity4Result(Class<T> clazz, int requestCode) {
		Intent intent = new Intent(getActivity(), clazz);
		try {
			getActivity().startActivityForResult(intent, requestCode);
		} catch (Exception e) {
			Toast.makeText(getActivity(), "敬请期待！"+ clazz.getSimpleName()
					+ "未注册！", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 获取界面控件
	 * 
	 * @param resId
	 * @return
	 */
	protected View findView(int resId) {
		return rootView.findViewById(resId);
	}


	/**
	 * 获取文本框中的文字（已处理首尾空格）</br> 支持TextView所有子类如：EditText, CheckBox, RadioButton等
	 * 
	 * @param v
	 *            TextView
	 * @return String
	 */
	protected String getText(TextView v) {
		if (v == null) {
			return "";
		}
		return v.getText().toString().trim();
	}

	/**
	 * 检查list是否为空
	 * 
	 * @param list
	 * @return
	 */
	protected <T> boolean isEmptyList(List<T> list) {
        return list == null || list.isEmpty();
    }

	/**
	 * 退出时关闭数据库
	 */
	@Override
	public void onDestroy() {
		try {
			dbUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		super.onDestroy();
	}


	public void onMyResponse(BaseEntity info) {
		resetView();
		if (ly_loading_failed != null) {
			ly_loading_failed.setVisibility(View.GONE);
		}

		if (info.status != STATUS_CODE_OK) {
			if (pageCode != 0) {
				ToastUtil.show(getActivity(), info.MSG);
			}
			return;
		}
		if(info.status == STATUS_CODE_OK){
			isLoaded = true;
		}
		pageCode += 1;
	}

	public void onError(String errMsg) {
		if(!netWorkIsAvailable()){
			ToastUtil.show(getActivity(),"网络连接失败，请检查您的网络状态");
		}else{
			if(StringUitl.isNoEmpty(errMsg)){
				ToastUtil.show(getActivity(),errMsg);
			}
		}
		resetView();
		showErrorPage(true);
	}

	public void onError(String errMsg, int status) {
		if(!netWorkIsAvailable()){
			ToastUtil.show(getActivity(),"网络连接失败，请检查您的网络状态");
		}else if(status == 99 || status == -99){
			ToastUtil.show(getActivity(),"登录失效，请重新登录");
			//退出登录信息
			CxUtil.clearData(sp);
			try {
				getActivity().finish();
				AppConfig.getInstance().exit();
				Intent intent = new Intent(getActivity(), LoginActivity.class);
				intent.putExtra("flag", "other");
				startActivity(intent);
//				startActivity(LoginActivity.class);
			}catch (Exception e){
				e.printStackTrace();
			}
		}else{
			if(StringUitl.isNoEmpty(errMsg)){
				ToastUtil.show(getActivity(),errMsg);
			}
		}
		resetView();
		showErrorPage(true);
	}
//	@Override
//	public void onRefresh() {
//		Log.d("geek", "BaseFragment onRefresh()");
//		pageCode = 0;
//		initDataFromNet();
//	}
//
//	@Override
//	public void onLoadMore() {
//		Log.d("geek", "BaseFragment onLoadMore()");
//		initDataFromNet();
//	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		//保存至outState中
		if(Contains.user != null && (ArrayList)Contains.appYezhuFangwus != null){
//			Logger.d("BaseFragment onSaveInstanceState user="+Contains.user+",house="+(ArrayList)Contains.appYezhuFangwus);
			CxUtil.setLoginData(outState);
		}else{
//			Logger.d("BaseFragment onSaveInstanceState ");
		}
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
		try {
			//取出保存在savedInstanceState的值
			if (savedInstanceState != null) {
//				Logger.d("BaseFragment onRestoreInstanceState()");
				CxUtil.getLogindata(savedInstanceState);
			}
			super.onViewStateRestored(savedInstanceState);
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 */
	protected void resetView() {
		progressDialog.hide();
		showEmptyDataPage(false);
	}


	/** 是否已经加载过初始数据, 在页面初始数据加载成功之后请置为false */
	protected boolean isLoaded = false;

//	@Override
//	public void setUserVisibleHint(boolean isVisibleToUser) {
//		if(this != null){
//			super.setUserVisibleHint(isVisibleToUser);
//			if (isVisibleToUser) {
//				if(!isLoaded){
//				}
//			}
//		}
//	}
	

	/**
	 * 显示错误页面
	 * @param show
	 */
	public void showErrorPage(boolean show){
		if(ly_empty_data != null){
			ly_empty_data.setVisibility(View.GONE);
		}
		if (ly_loading_failed == null) {
			return;
		}
		if(show){
			ly_loading_failed.setVisibility(View.VISIBLE);
		}else{
			ly_loading_failed.setVisibility(View.GONE);
		}
		
	}
	
	/**
	 * 显示空内容页面
	 * @param show
	 */
	public void showEmptyDataPage(boolean show){
		if(ly_loading_failed != null){
			ly_loading_failed.setVisibility(View.GONE);
		}
		if (ly_empty_data == null) {
			return;
		}
		if(show){
			ly_empty_data.setVisibility(View.VISIBLE);
		}else{
			ly_empty_data.setVisibility(View.GONE);
		}
	}

}
