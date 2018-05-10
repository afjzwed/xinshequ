package com.yxld.yxchuangxin.Utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * 作者：hu on 2017/6/8
 * 邮箱：365941593@qq.com
 * 描述：
 */

/**
 * 公共的popwindow弹出类。所有的popwindow都可以封装在这个类里边
 */
public class PopWindowUtil {
    OnSubmitClickListener onSubmitClickListener;
    public static void  showFullScreenPopWindow(Activity activity, View showView, View backView, View contentView) {
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });
        contentView.setAnimation(AnimationUtils.loadAnimation(activity, R.anim.pop_manage_product_in));
        new CustomPopWindow.PopupWindowBuilder(activity)
                .setView(backView)
                .setClippingEnable(false)
                .setContenView(contentView)
                .setFocusable(false)
                .size(UIUtils.getDisplayWidth(activity), UIUtils.getDisplayHeigh(activity))
                .create()
                .showAtLocation(showView, Gravity.NO_GRAVITY, 0, 0);
    }

    public static void showAddFangxingPop(Activity activity, View showView, View contentView) {
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });
        new CustomPopWindow.PopupWindowBuilder(activity)
                .setClippingEnable(false)
                .setFocusable(true)
                .setView(contentView)
                .setContenView(contentView)
                .setAnimationStyle(android.R.style.Animation_Dialog)
                .size(UIUtils.getDisplayWidth(activity), UIUtils.getDisplayHeigh(activity))
                .create()
                .showAtLocation(showView, Gravity.CENTER, 0, 0);
    }

    /**
     *
     * @param activity  上下文
     * @param showView  从activity中传进来的view,用于让popWindow附着的
     * @param maskView  门板view，阴影
     * @param contentView 内容显示view，打开关闭会有类似输入法弹窗的效果
     */
    public static void showPopWindow(Activity activity, View showView, View maskView, View contentView) {
        contentView.setAnimation(AnimationUtils.loadAnimation(activity, R.anim.pop_manage_product_in));
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });
        new CustomPopWindow.PopupWindowBuilder(activity)
                .setView(maskView)
                .setClippingEnable(false)
                .setContenView(contentView)
                .setFocusable(false)
                .size(UIUtils.getDisplayWidth(activity), UIUtils.getDisplayHeigh(activity))
                .create()
                .showAtLocation(showView, Gravity.NO_GRAVITY, 0, 0);
    }

    /**
     * 显示选择投诉类型的popwindow
     * @param activity
     * @param showView
     */
    public void showComplainPop(Activity activity, View showView) {
        View maskView = LayoutInflater.from(activity).inflate(R.layout.pop_pick_tousu_content, null);
        AutoLinearLayout ll_popup = (AutoLinearLayout) maskView.findViewById(R.id.ll_popup);
        //对具体的view的事件的处理
        Button bt1 = (Button) maskView.findViewById(R.id.bt1);
        Button bt2 = (Button) maskView.findViewById(R.id.bt2);
        Button bt3 = (Button) maskView.findViewById(R.id.bt3);
        Button bt4 = (Button) maskView.findViewById(R.id.bt4);
        Button bt5 = (Button) maskView.findViewById(R.id.bt5);
        Button bt_cancal = (Button) maskView.findViewById(R.id.bt_cancal);
        bt_cancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow.onBackPressed();
            }
        });
        bt1.setOnClickListener(v -> {
            if (onSubmitClickListener != null) {
                onSubmitClickListener.onSubmitClick(v, bt1.getText().toString());
            }
        });
        bt2.setOnClickListener(v -> {
            if (onSubmitClickListener != null) {
                onSubmitClickListener.onSubmitClick(v, bt2.getText().toString());
            }
        });
        bt3.setOnClickListener(v -> {
            if (onSubmitClickListener != null) {
                onSubmitClickListener.onSubmitClick(v, bt3.getText().toString());
            }
        });
        bt4.setOnClickListener(v -> {
            if (onSubmitClickListener != null) {
                onSubmitClickListener.onSubmitClick(v, bt4.getText().toString());
            }
        });
        bt5.setOnClickListener(v -> {
            if (onSubmitClickListener != null) {
                onSubmitClickListener.onSubmitClick(v, bt5.getText().toString());
            }
        });
        showPopWindow(activity, showView, maskView, ll_popup);
    }
    /**
     * 显示选择投诉类型的popwindow
     * @param activity
     * @param showView
     */
    public void showMarketComplainPop(Activity activity, View showView) {
        View maskView = LayoutInflater.from(activity).inflate(R.layout.pop_pick_market_tousu_content, null);
        AutoLinearLayout ll_popup = (AutoLinearLayout) maskView.findViewById(R.id.ll_popup);
        //对具体的view的事件的处理
        Button bt1 = (Button) maskView.findViewById(R.id.bt1);
        Button bt2 = (Button) maskView.findViewById(R.id.bt2);
        Button bt_cancal = (Button) maskView.findViewById(R.id.bt_cancal);
        bt_cancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow.onBackPressed();
            }
        });
        bt1.setOnClickListener(v -> {
            if (onSubmitClickListener != null) {
                onSubmitClickListener.onSubmitClick(v, bt1.getText().toString());
            }
        });
        bt2.setOnClickListener(v -> {
            if (onSubmitClickListener != null) {
                onSubmitClickListener.onSubmitClick(v, bt2.getText().toString());
            }
        });
        showPopWindow(activity, showView, maskView, ll_popup);
    }

    public void showSharePopWindow(Activity activity, View showView) {
        View maskView = LayoutInflater.from(activity).inflate(R.layout.pop_share_erweima, null);
        AutoLinearLayout llShare = (AutoLinearLayout) maskView.findViewById(R.id.ll_share);
        AutoLinearLayout shareWeixin = (AutoLinearLayout) maskView.findViewById(R.id.share_weixin);
        AutoLinearLayout shareQq = (AutoLinearLayout) maskView.findViewById(R.id.share_qq);
        AutoLinearLayout shareMsg = (AutoLinearLayout) maskView.findViewById(R.id.share_msg);
        TextView tv_cancal = (TextView) maskView.findViewById(R.id.tv_cancal);
        tv_cancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow.onBackPressed();
            }
        });
        shareWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSubmitClickListener != null) {
                    onSubmitClickListener.onSubmitClick(v, "");
                }
            }
        });
        shareQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSubmitClickListener != null) {
                    onSubmitClickListener.onSubmitClick(v, "");
                }
            }
        });
        shareMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSubmitClickListener != null) {
                    onSubmitClickListener.onSubmitClick(v, "");
                }
            }
        });
        showPopWindow(activity, showView, maskView, llShare);
    }
    public void setOnSubmitClickListener(OnSubmitClickListener onSubmitClickListener) {
        this.onSubmitClickListener = onSubmitClickListener;
    }
    public interface OnSubmitClickListener {
        void onSubmitClick(View v, String time);
    }
}
