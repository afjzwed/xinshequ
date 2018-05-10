package com.yxld.yxchuangxin.view.datepicker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;

import java.util.Calendar;


@SuppressLint("SimpleDateFormat")
public class DatePicker {

	private Dialog dialog_time;
	private Context context;
//	private boolean timeChanged = false;
//	private boolean timeScrolled = false;
	StringBuffer buffer;
	private String age;
	private int mCurYear;
	private int mCurMonth;
	
	private String[] dateType;
	private TextView submit;
	private TextView cancel;
	private DateNumericAdapter monthAdapter, dayAdapter, yearAdapter;
	private int mCurDay;
	private int style;
	public DatePicker(Context context){
		this.context = context;
		style = R.style.Dialog_Fullscreen;
	} 
	public DatePicker(Context context, int style){
		this.context = context;
		this.style = style;
	} 
	/**
	 * 选择日期对话框
	 */
//	public void selectDateDialog(final TextView tv_time,String defaultTime) {
//		View view = newDataDialog(R.layout.picker_date);
//
//		 submit = (TextView) view.findViewById(R.id.submit);
//		 cancel = (TextView) view.findViewById(R.id.cancel);
//		final WheelView year = (WheelView) view.findViewById(R.id.year);
//		final WheelView month = (WheelView) view.findViewById(R.id.month);
//		final WheelView day = (WheelView) view.findViewById(R.id.day);
//
//		try {
//			String time = tv_time.getText().toString().trim();
//			if(TextUtils.hasEmptyItem(time)){
//				if(TextUtils.hasEmptyItem(defaultTime)){
//				Date date = new Date();
//				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//				 this.age = format.format(date);
//				}else{
//					this.age = defaultTime;
//				}
//			}else{
//				this.age = defaultTime;
//			}
//		} catch (Exception e) {
//			this.age = "1980-01-01";
//		}
//
//		submit.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				tv_time.setText("" + age);
//				dialog_time.dismiss();
//			}
//		});
//		cancel.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				dialog_time.dismiss();
//			}
//		});
//		Calendar calendar = Calendar.getInstance();
//		OnWheelChangedListener listener = new OnWheelChangedListener() {
//			public void onChanged(WheelView wheel, int oldValue, int newValue) {
//				updateDays(year, month, day);
//
//			}
//
//		};
//		int curYear = calendar.get(Calendar.YEAR);
//		if (age != null && age.contains("-")) {
//			String str[] = age.split("-");
//			mCurYear = 100 - (curYear - Integer.parseInt(str[0]));
//			mCurMonth = Integer.parseInt(str[1]) - 1;
//			mCurDay = Integer.parseInt(str[2]) - 1;
//        }
//		dateType = context.getResources().getStringArray(R.array.date);
//		monthAdapter = new DateNumericAdapter(context, 1, 12, 5);
//		monthAdapter.setTextType(dateType[1]);
//		month.setViewAdapter(monthAdapter);
//		month.setCurrentItem(mCurMonth);
//		month.addChangingListener(listener);
//		// year
//
//		yearAdapter = new DateNumericAdapter(context, curYear - 100, curYear + 100, 100 - 20);
//		yearAdapter.setTextType(dateType[0]);
//		year.setViewAdapter(yearAdapter);
//		year.setCurrentItem(mCurYear);
//		year.addChangingListener(listener);
//		// day
//
//		updateDays(year, month, day);
//		day.setCurrentItem(mCurDay);
//		updateDays(year, month, day);
//		day.addChangingListener(listener);
//
//		dialog_time.show();
//	}
	
	

	@SuppressWarnings("deprecation")
	private View newDataDialog(int layoutID) {
		buffer = new StringBuffer();
		dialog_time = new Dialog(context, style);
		dialog_time.requestWindowFeature(Window.FEATURE_NO_TITLE);
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(layoutID, null);
		int screenWidth =((Activity)context).getWindowManager().getDefaultDisplay().getWidth();

		Window window = dialog_time.getWindow();
		// 重新设置
		WindowManager.LayoutParams lp = window.getAttributes();
		window.setWindowAnimations(R.style.addresspickerstyle); // 添加动画
		window.setGravity(Gravity.BOTTOM);
		// window.setWindowAnimations(R.style.mystyle); // 添加动画
		lp.x = 0; // 新位置X坐标
		lp.y = 0; // 新位置Y坐标
		// lp.width = screenWidth;
		// window.setAttributes(lp);
		view.setMinimumWidth(screenWidth - 0);
		dialog_time.setContentView(view, lp);
		return view;
	}
	/**
	 * 年月日 的更新操作
	 * @param year
	 * @param month
	 * @param day
	 */
	public void updateDays(WheelView year, WheelView month, WheelView day) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
		calendar.set(Calendar.MONTH, month.getCurrentItem());

		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		dayAdapter = new DateNumericAdapter(context, 1, maxDays, calendar.get(Calendar.DAY_OF_MONTH) - 1);
		dayAdapter.setTextType(dateType[2]);
		day.setViewAdapter(dayAdapter);
		int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
		day.setCurrentItem(curDay - 1, true);
		int years = calendar.get(Calendar.YEAR) - 100;
		String monthss = "";
		String dayss = "";
		if(month.getCurrentItem()+1<=9){
			monthss = "0"+(month.getCurrentItem()+1);
		}else{
			monthss = ""+(month.getCurrentItem()+1);
		}
		if(day.getCurrentItem()+1<=9){
			dayss = "0"+(day.getCurrentItem()+1);
		}else{
			dayss = ""+(day.getCurrentItem()+1);
		}
		age = years + "-" + monthss + "-" + dayss;
	}

	/**
	 * Adapter for numeric wheels. Highlights the current value.
	 */
	private class DateNumericAdapter extends NumericWheelAdapter {
		@SuppressWarnings("unused")
		int currentItem;
		@SuppressWarnings("unused")
		int currentValue;

		/**
		 * Constructor
		 */
		public DateNumericAdapter(Context context, int minValue, int maxValue, int current) {
			super(context, minValue, maxValue);
			this.currentValue = current;
			setTextSize(24);
		}

		protected void configureTextView(TextView view) {
			super.configureTextView(view);
			view.setTypeface(Typeface.SANS_SERIF);
		}

		public CharSequence getItemText(int index) {
			currentItem = index;
			return super.getItemText(index);
		}

	}
	
}
