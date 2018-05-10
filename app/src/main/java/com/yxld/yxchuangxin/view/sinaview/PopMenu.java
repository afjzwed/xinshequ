package com.yxld.yxchuangxin.view.sinaview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.yxld.yxchuangxin.view.backboard.Actor;
import com.yxld.yxchuangxin.view.backboard.MotionProperty;
import com.yxld.yxchuangxin.view.backboard.imitator.Imitator;

import java.util.ArrayList;
import java.util.List;

/**
 * 弹出菜单
 * Created by HanHailong on 16/2/17.
 */
public class PopMenu {


    /**
     * 默认的列数为4个
     */
    private static final int DEFAULT_COLUMN_COUNT = 4;

    /**
     * 动画时间
     */
    private static final int DEFAULT_DURATION = 300;

    /**
     * 拉力系数
     */
    private static final int DEFAULT_TENSION = 50;
    /**
     * 摩擦力系数
     */
    private static final int DEFAULT_FRICTION = 7;

    /**
     * item水平之间的间距
     */
    private static final int DEFAULT_HORIZONTAL_PADDING = 20;
    /**
     * item竖直之间的间距
     */
    private static final int DEFAULT_VERTICAL_PADDING = 71;

    private Activity mActivity;
    private int mColumnCount;
    private List<PopMenuItem> mMenuItems = new ArrayList<>();
    private FrameLayout mAnimateLayout;
    private GridLayout mGridLayout;
//    private ImageView mCloseIv;
    private int mDuration;
    private double mTension;
    private double mFriction;
    private int mHorizontalPadding;
    private int mVerticalPadding;
    private PopMenuItemListener mPopMenuItemListener;

    private int mScreenWidth;
    private int mScreenHeight;

    public static boolean isShowing = false;
    private static PopMenu instance;

    private SpringSystem mSpringSystem;
    private int mPosition = -1;

    {
        mSpringSystem = SpringSystem.create();
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int position;
            switch (msg.what) {
                case 0:
                    //显示
                    View view = (View) msg.obj;
                    position = msg.arg1;
                    view.setVisibility(View.VISIBLE);
                    switch (position) {
                        case 0:
                            animateViewDirection(view, mScreenHeight, 0, mTension, mFriction, position);
                            break;
                        case 1:
                            animateViewDirection(view, mScreenHeight, 0, mTension, mFriction, position);
                            break;
                        case 2:
                            animateViewDirection(view, mScreenHeight, 0, mTension, mFriction, position);
                            break;
                    }

                    break;
                case 1:
                    //隐藏
                    View view1 = (View) msg.obj;
                    position = msg.arg1;
                    animateViewDirection(view1, 0, mScreenHeight, mTension, mFriction, position);
                    break;
                case 2:
                    ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
                    decorView.removeView(mAnimateLayout);
                    isShowing = false;
                    mPopMenuItemListener.onhideOver(PopMenu.this, mPosition);
                    break;
                default:
                    break;
            }
        }
    };
    private PopMenu(Builder builder) {
        this.mActivity = builder.activity;
        this.mMenuItems.clear();
        this.mMenuItems.addAll(builder.itemList);

        this.mColumnCount = builder.columnCount;
        this.mDuration = builder.duration;
        this.mTension = builder.tension;
        this.mFriction = builder.friction;
        this.mHorizontalPadding = builder.horizontalPadding;
        this.mVerticalPadding = builder.verticalPadding;
        this.mPopMenuItemListener = builder.popMenuItemListener;

        mScreenWidth = mActivity.getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = mActivity.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 显示菜单
     */
    public void show() {
        buildAnimateGridLayout();

        if (mAnimateLayout.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) mAnimateLayout.getParent();
            viewGroup.removeView(mAnimateLayout);
        }

        ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
        decorView.addView(mAnimateLayout);

        //执行显示动画
        showSubMenus(mGridLayout);

        isShowing = true;
    }

    /**
     * 隐藏菜单
     */
    public void hide() {
        //先执行消失的动画
        mCloseIvAnimation(false);
        if (isShowing && mGridLayout != null) {
            hideSubMenus(mGridLayout, new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
                    decorView.removeView(mAnimateLayout);
                }
            });
            isShowing = false;
            instance = null;
        }
    }

    public static boolean onBackPressed() {
        if (isShowing && instance != null) {
            instance.hide();
            return true;
        }
        return false;
    }

    public void mCloseIvAnimation(boolean show) {
        ObjectAnimator rot;
        ObjectAnimator alpha;
//        if (show) {
//            rot = ObjectAnimator.ofFloat(mCloseIv, "rotation", 0, 90);
//            alpha = ObjectAnimator.ofFloat(mCloseIv, "alpha", 0.0f,1.0f);
//        } else {
//            rot = ObjectAnimator.ofFloat(mCloseIv, "rotation", 90, 0);
//            alpha = ObjectAnimator.ofFloat(mCloseIv, "alpha", 1.0f,0.0f);
//        }
//        AnimatorSet animSet = new AnimatorSet();
//        animSet.play(alpha).with(rot);
//        animSet.setDuration(250);
//        animSet.start();
//        ObjectAnimator.ofFloat(mCloseIv, "rotation", 0.5f, 0.5f);
    }

    /**
     * 构建动画布局
     */
    private void buildAnimateGridLayout() {
        mAnimateLayout = new FrameLayout(mActivity);

        mGridLayout = new GridLayout(mActivity);
        mGridLayout.setColumnCount(mColumnCount);
        mGridLayout.setBackgroundColor(Color.parseColor("#00000000"));

        int hPadding = dp2px(mActivity, mHorizontalPadding);
        int vPadding = dp2px(mActivity, mVerticalPadding);
        int itemWidth = (mScreenWidth - (mColumnCount + 1) * hPadding) / mColumnCount;

        int rowCount = mMenuItems.size() % mColumnCount == 0 ? mMenuItems.size() / mColumnCount :
                mMenuItems.size() / mColumnCount + 1;

        int topMargin = (mScreenHeight - (itemWidth + vPadding) * rowCount + vPadding) / 2 + 200;

        for (int i = 0; i < mMenuItems.size(); i++) {
            final int position = i;
            PopSubView subView = new PopSubView(mActivity);
            PopMenuItem menuItem = mMenuItems.get(i);
            subView.setPopMenuItem(menuItem);
            subView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPopMenuItemListener != null) {
                        mPopMenuItemListener.onItemClick(PopMenu.this, position);
                        mPosition = position;
                    }
                    hide();
                }
            });

            GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
            lp.width = itemWidth;
            lp.leftMargin = hPadding;
            if (i / mColumnCount == 0) {
                lp.topMargin = topMargin;
            } else {
                lp.topMargin = vPadding;
            }
            new Actor.Builder(SpringSystem.create(), subView)
                    .addTranslateMotion(Imitator.TRACK_DELTA, Imitator.FOLLOW_EXACT, MotionProperty.X)
                    .addTranslateMotion(Imitator.TRACK_DELTA, Imitator.FOLLOW_EXACT, MotionProperty.Y)
                    .build();
            subView.setVisibility(View.INVISIBLE);
            mGridLayout.addView(subView, lp);
//            if (SpUtil.getString(mActivity, ConstantValue.CHECKSTATE, "").equals("1")) {
//                subView.setVisibility(View.GONE);
//            }
        }

        mAnimateLayout.addView(mGridLayout);

//        mCloseIv = new ImageView(mActivity);
//        mCloseIv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//        mCloseIv.setImageResource(R.drawable.zt_close);
//        mCloseIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPosition = -1;
//                hide();
//            }
//        });
//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
//        layoutParams.bottomMargin = dp2px(mActivity, 25);
//        mAnimateLayout.addView(mCloseIv, layoutParams);
//        mAnimateLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPosition = -1;
//                hide();
//            }
//        });
    }

    /**
     * show sub menus with animates
     *
     * @param viewGroup
     */
    private void showSubMenus(final ViewGroup viewGroup) {
        if (viewGroup == null) return;
        final int childCount = viewGroup.getChildCount();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < childCount; i++) {
                    View view = viewGroup.getChildAt(i);
                    Message msg = new Message();
                    msg.what = 0;
                    msg.obj = view;
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 处理具体的逻辑
            }

        }).start();
        mCloseIvAnimation(true);
    }

    /**
     * hide sub menus with animates
     *
     * @param viewGroup
     * @param listener
     */
    private void hideSubMenus(final ViewGroup viewGroup, final AnimatorListenerAdapter listener) {
        if (viewGroup == null) return;
        final int childCount = viewGroup.getChildCount();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = childCount - 1; i >= 0; i--) {
                    View view = viewGroup.getChildAt(i);
                    Message msg = new Message();
                    msg.what = 1;
                    msg.arg1 = i;
                    msg.obj = view;
                    handler.sendMessage(msg);
                    if (i == 0) {
                        handler.sendEmptyMessageDelayed(2, 130);
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 处理具体的逻辑
            }

        }).start();

//        for (int i = childCount - 1; i >= 0; i--) {
//            View view = viewGroup.getChildAt(i);
//            view.animate().translationY(mScreenHeight).setDuration(mDuration).setListener(listener).start();
//        }
    }

    /**
     * 弹簧动画
     *
     * @param v        动画View
     * @param fromY
     * @param toY
     * @param tension  拉力系数
     * @param friction 摩擦力系数
     */
    private void animateViewDirection(final View v, float fromY, float toY, double tension, double friction, int position) {
        Spring spring = mSpringSystem.createSpring();
        spring.setCurrentValue(fromY);
        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(tension, friction));
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
//                v.setTranslationY((float) spring.getCurrentValue());
                v.setTranslationX((float) spring.getCurrentValue());
            }
        });
        spring.setEndValue(toY);
    }

    public static class Builder {

        private Activity activity;
        private int columnCount = DEFAULT_COLUMN_COUNT;
        private List<PopMenuItem> itemList = new ArrayList<>();
        private int duration = DEFAULT_DURATION;
        private double tension = DEFAULT_TENSION;
        private double friction = DEFAULT_FRICTION;
        private int horizontalPadding = DEFAULT_HORIZONTAL_PADDING;
        private int verticalPadding = DEFAULT_VERTICAL_PADDING;
        private PopMenuItemListener popMenuItemListener;

        public Builder attachToActivity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public Builder columnCount(int count) {
            this.columnCount = count;
            return this;
        }

        public Builder addMenuItem(PopMenuItem menuItem) {
            this.itemList.add(menuItem);
            return this;
        }

        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder tension(double tension) {
            this.tension = tension;
            return this;
        }

        public Builder friction(double friction) {
            this.friction = friction;
            return this;
        }

        public Builder horizontalPadding(int padding) {
            this.horizontalPadding = padding;
            return this;
        }

        public Builder verticalPadding(int padding) {
            this.verticalPadding = padding;
            return this;
        }

        public Builder setOnItemClickListener(PopMenuItemListener listener) {
            this.popMenuItemListener = listener;
            return this;
        }

        public PopMenu build() {
            final PopMenu popMenu = new PopMenu(this);
            if (instance == null) {
                instance = popMenu;
            }
            return popMenu;
        }
    }

    /**
     * dp 2 px
     *
     * @param context
     * @param dpVal
     * @return
     */
    protected int dp2px(Context context, int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
