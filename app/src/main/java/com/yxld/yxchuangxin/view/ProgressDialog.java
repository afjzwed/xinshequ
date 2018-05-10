package com.yxld.yxchuangxin.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;

/**
 * @ClassName: ProgressDialog 
 * @Description: 公用等待加载条 
 * @author wwx
 * @date 2016年3月14日 下午5:32:19 
 */
public class ProgressDialog {
	private Dialog dialog;
	private TextView tv_progress;

	/***
	 * 默认加载条
	 * 
	 * @param context
	 *            上下文
	 */
	public ProgressDialog(Context context) {
		initDialog(context, initView(context));
		// dialog.setCancelable(false); // 点击屏幕不消失
	}

	public ProgressDialog(Context context, boolean message) {
		initDialog(context, initView(context));
		dialog.setCancelable(message); // 点击屏幕不消失
	}

	public void setCancelable(boolean flag) {
		dialog.setCancelable(flag); // 点击屏幕不消失
	}

	/***
	 * 自定应加载条中的文字内容
	 * 
	 * @param context
	 *            上下文
	 * @param message
	 *            加载条中文字消息内容
	 */
	public ProgressDialog(Context context, String message) {
		View dialogView = initView(context);
	//	egg_duang = (ImageView) dialogView.findViewById(R.id.egg_duang);
		initDialog(context, dialogView);
	}
	public void setProgress(int progress){
		tv_progress.setVisibility(View.VISIBLE);
		tv_progress.setText(progress + "%");
	}
	/***
	 * 初始化自定义视图
	 * 
	 * @param context
	 *            上下文
	 * @return 视图
	 */
	public View initView(Context context) {
		View popView = LayoutInflater.from(context)
				.inflate(R.layout.dialog_progress, null);
	//	egg_duang = (ImageView) popView.findViewById(R.id.egg_duang);
	//	animationDrawable = (AnimationDrawable) egg_duang.getDrawable();
		tv_progress = (TextView) popView.findViewById(R.id.tv_progress);
		tv_progress.setText("加载中...");
		ProgressBar progress = (ProgressBar) popView.findViewById(R.id.progress);
		/** 对TextView设置不同状态时其文字颜色。 */
		if (Build.VERSION.SDK_INT >= 21) {
			progress.setIndeterminateTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.main_color)));
		}

		return popView;
	}


	/***
	 * 初始化Dialog
	 * 
	 * @param context
	 *            上下文
	 * @param view
	 *            布局视图
	 */
	public void initDialog(Context context, View view) {
		dialog = new Dialog(context, R.style.dialog);
		dialog.setContentView(view, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		dialog.setFeatureDrawableAlpha(Window.FEATURE_OPTIONS_PANEL, 20);
	}

//	private AnimationDrawable animationDrawable;
//	private ImageView egg_duang;

	/***
	 * 
	 * 显示等待加载条
	 */
	public void show() {
	//	animationDrawable.start();
		tv_progress.setVisibility(View.VISIBLE);
		if (dialog!=null&&!dialog.isShowing())
			dialog.show();
	}

	/***
	 * 隐藏等待加载条
	 */
	public void hide() {
	//                                                                                                               	animationDrawable.stop();
		tv_progress.setVisibility(View.GONE);
		if (dialog!=null&&dialog.isShowing()){
			dialog.dismiss();
		}
	}

	/***
	 * 加载条是否存在
	 * 
	 * @return
	 */
	public boolean isShowing() {
		return dialog.isShowing();
	}
	
	/**
	 * 取消监听
	 * @param listener
	 */
	public void setMyCancelListener(OnCancelListener listener){
		dialog.setOnCancelListener(listener);
	}
}
