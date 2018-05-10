package com.yxld.yxchuangxin.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.yxld.yxchuangxin.view.datepicker.NumericWheelAdapter;
import com.yxld.yxchuangxin.view.datepicker.OnWheelChangedListener;
import com.yxld.yxchuangxin.view.datepicker.WheelView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.Calendar;

import static com.yxld.yxchuangxin.Utils.TimeUtil.en2Zh;

/**
 * 作者：hu on 2017/6/19
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class TimePickUtil {
    OnSubmitClickListener onSubmitClickListener;
    private DateNumericAdapter dayAdapter;
    private DateNumericAdapter hourAdapter;

    public void showDatePickerPop(Activity activity, View showView) {
        View view = LayoutInflater.from(activity).inflate(R.layout.picker_date, null);
        AutoLinearLayout ll_content = (AutoLinearLayout) view.findViewById(R.id.ll_content);
        ll_content.setAnimation(AnimationUtils.loadAnimation(activity, R.anim.pop_manage_product_in));
        TextView submit = (TextView) view.findViewById(R.id.submit);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow.onBackPressed();
            }
        });
        final WheelView hour = (WheelView) view.findViewById(R.id.hour);
        final WheelView day = (WheelView) view.findViewById(R.id.day);
        final ArrayList<String> dateList = TimeUtil.getDate();
        final ArrayList<String> dayList = new ArrayList<>();
        Calendar c7 = Calendar.getInstance();
        for (int i = 0; i < dateList.size(); i++) {
            c7.setTimeInMillis(Long.parseLong(dateList.get(i)));
            String date =c7.get(Calendar.MONTH) + 1 + "月" + String.format("%02d", c7.get(Calendar.DAY_OF_MONTH)) + "日(星期"+en2Zh(c7.get(Calendar.DAY_OF_WEEK))+")";
            dayList.add(date);
        }
        ArrayList<String> timeList = TimeUtil.get24(1);
        dayAdapter = new DateNumericAdapter(activity, 1, dayList.size(), 0, dayList);
        hourAdapter = new DateNumericAdapter(activity, 1, timeList.size(), 0, timeList);
        day.setViewAdapter(dayAdapter);
        hour.setViewAdapter(hourAdapter);
        day.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                if (newValue == 0) {
                    // 今天。。。
                    ArrayList<String> timeList = TimeUtil.get24(1);
                    hourAdapter = new DateNumericAdapter(activity, 1, timeList.size(), 0, timeList);
                    hour.setViewAdapter(hourAdapter);
                    hour.setCurrentItem(0);
                } else {
                    ArrayList<String> timeList = TimeUtil.get24(0);
                    hourAdapter = new DateNumericAdapter(activity, 1, timeList.size(), 0, timeList);
                    hour.setViewAdapter(hourAdapter);
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSubmitClickListener != null) {
                   String time1= TimeUtil.timesTamp2Year1(Long.parseLong(dateList.get(day.getCurrentItem())))+"  " + hourAdapter.getItemText(hour.getCurrentItem()).toString();
                    String time = dayAdapter.getItemText(day.getCurrentItem()).toString() + "  " + hourAdapter.getItemText(hour.getCurrentItem()).toString();
                    onSubmitClickListener.onSubmitClick(time1);
                    CustomPopWindow.onBackPressed();
                }
            }
        });
        new CustomPopWindow.PopupWindowBuilder(activity)
                .setClippingEnable(false)
                .setFocusable(true)
                .setView(view)
                .setContenView(ll_content)
                .size(UIUtils.getDisplayWidth(activity), UIUtils.getDisplayHeigh(activity))
                .create()
                .showAtLocation(showView, Gravity.CENTER, 0, 0);
    }

    /**
     * Adapter for numeric wheels. Highlights the current value.
     */
    private class DateNumericAdapter extends NumericWheelAdapter {
        @SuppressWarnings("unused")
        int currentItem;
        @SuppressWarnings("unused")
        int currentValue;

        private ArrayList<String> list;

        /**
         * Constructor
         */
        public DateNumericAdapter(Context context, int minValue, int maxValue, int current, ArrayList<String> list) {
            super(context, minValue, maxValue, "0");
            this.currentValue = current;
            setTextSize(18);
            this.list = list;
        }

        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            view.setTypeface(Typeface.SANS_SERIF);
        }

        public CharSequence getItemText(int index) {
            currentItem = index;
            return list.get(index);
//            return super.getItemText(index);
        }
    }

    public void setOnSubmitClickListener(OnSubmitClickListener onSubmitClickListener) {
        this.onSubmitClickListener = onSubmitClickListener;
    }

    public interface OnSubmitClickListener {
        void onSubmitClick(String time);
    }
}
