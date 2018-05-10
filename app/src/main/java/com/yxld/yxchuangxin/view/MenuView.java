package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.nineoldandroids.view.ViewHelper;
import com.yxld.yxchuangxin.R;

/**
 * 作者：Android on 2017/10/16
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class MenuView extends RelativeLayout implements View.OnClickListener {

    private ImageView menu;
    private ImageView menu1;
    private ImageView menu2;
    private ImageView menu3;
    private double mTension = 50;
    private double mFriction = 7;
    private int fromDeration = 200;
    private int endDeration = 0;
    private BaseSpringSystem mSpringSystem;
    private OnMenuItemClickListener onMenuItemClickListener;

    private static MenuView instance;
    public boolean isShow = false;
    private TextView countMenu;
    private TextView countCart;

    public MenuView(Context context) {
        super(context);
        init(context);
    }

    public static MenuView getInstance() {
        if (instance != null) {
            return instance;
        } else {
            return null;
        }
    }

    public MenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        mSpringSystem = SpringSystem.create();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.menu_view, this);
        menu = (ImageView) view.findViewById(R.id.menu);
        menu1 = (ImageView) view.findViewById(R.id.menu1);
        menu2 = (ImageView) view.findViewById(R.id.menu2);
        menu3 = (ImageView) view.findViewById(R.id.menu3);
        countMenu = (TextView) view.findViewById(R.id.count_menu);
        countCart = (TextView) view.findViewById(R.id.count_cart);
        menu.setOnClickListener(this);
        menu1.setOnClickListener(this);
        menu2.setOnClickListener(this);
        menu3.setOnClickListener(this);
        instance = this;
        close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu:
                if (isShow) {
                    close();
                } else {
                    duangDeTeXiao(menu);
                    isShow = true;
                    setClickable(true);
                    menu1.setVisibility(VISIBLE);
                    menu2.setVisibility(VISIBLE);
                    menu3.setVisibility(VISIBLE);
                    countMenu.setVisibility(GONE);
                    animateViewDirectionX(menu1, fromDeration, endDeration, mTension, mFriction);
                    animateViewDirectionXY(menu2, fromDeration, endDeration, mTension, mFriction);
                    animateViewDirectionY(menu3, fromDeration, endDeration, mTension, mFriction);
                    mHandler.sendEmptyMessageDelayed(1, 150);
                    if (countCart.getText().equals("0")) {
                        countCart.setVisibility(GONE);
                    } else {
                        animateViewDirectionXY(countCart, fromDeration, endDeration, mTension, mFriction);
                    }
                }
                break;
            case R.id.menu1:
            case R.id.menu2:
            case R.id.menu3:
                if (onMenuItemClickListener != null) {
                    onMenuItemClickListener.onMenuItemClick(v);
                }
                break;
            default:
                break;
        }
    }

    private void duangDeTeXiao(View view) {
        Spring spring = mSpringSystem
                .createSpring()
                .setCurrentValue(2)
                .setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(mTension, mFriction))
                .addListener(new SimpleSpringListener() {
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        float value = (float) spring.getCurrentValue();
                        float scale = 1f - (value / 2);
                        ViewHelper.setScaleX(view, scale);
                        ViewHelper.setScaleY(view, scale);
                    }
                })
                .setEndValue(0);
    }

    private void duangDeTeXiaoCart(View view) {
        Spring spring = mSpringSystem
                .createSpring()
                .setCurrentValue(2, false)
                .setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(mTension, mFriction))
                .addListener(new SimpleSpringListener() {
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        float value = (float) spring.getCurrentValue();
                        float scale = 1f - (value / 2);
                        ViewHelper.setScaleX(view, scale);
                        ViewHelper.setScaleY(view, scale);
                    }
                })
                .setEndValue(2);
    }

    public void close() {
        duangDeTeXiao(menu);
        isShow = false;
        countCart.setVisibility(INVISIBLE);
        animateViewDirectionX(menu1, endDeration, fromDeration, mTension, mFriction);
        animateViewDirectionXY(menu2, endDeration, fromDeration, mTension, mFriction);
        animateViewDirectionY(menu3, endDeration, fromDeration, mTension, mFriction);
        mHandler.sendEmptyMessageDelayed(0, 150);
        if (countCart.getText().equals("0")) {
            countMenu.setVisibility(INVISIBLE);
        } else {
            animateViewDirectionXY(countCart, endDeration, fromDeration, mTension, mFriction);
        }
        setClickable(false);
    }

    public void updateCount(int count) {
        if (count >= 100) {
            countCart.setText("99+");
            countMenu.setText("99+");
        } else {
            countCart.setText(count + "");
            countMenu.setText(count + "");
        }
        if (count == 0) {
            countMenu.setVisibility(INVISIBLE);
            countCart.setVisibility(INVISIBLE);
            return;
        }
        countMenu.setVisibility(VISIBLE);
        duangDeTeXiaoCart(countMenu);
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    menu1.setVisibility(GONE);
                    menu2.setVisibility(GONE);
                    menu3.setVisibility(GONE);
                    if (countMenu.getText().equals("0")) {
                        countMenu.setVisibility(INVISIBLE);
                        return;
                    }
                    countMenu.setVisibility(VISIBLE);
                    duangDeTeXiaoCart(countMenu);
                    break;
                case 1:
                    if (countCart.getText().equals("0")) {
                        countCart.setVisibility(INVISIBLE);
                        return;
                    }
                    countCart.setVisibility(VISIBLE);
                    duangDeTeXiao(countCart);
                    break;
                case 2:
                    duangDeTeXiao(countCart);
                    break;
            }
        }
    };

    /**
     * 弹簧动画
     *
     * @param v        动画View
     * @param fromX
     * @param toX
     * @param tension  拉力系数
     * @param friction 摩擦力系数
     */
    private void animateViewDirectionX(final View v, float fromX, float toX, double tension, double friction) {
        Spring spring = mSpringSystem.createSpring();
        spring.setCurrentValue(fromX);
        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(tension, friction));
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
//                v.setTranslationY((float) spring.getCurrentValue());
                v.setTranslationX((float) spring.getCurrentValue());
            }
        });
        spring.setEndValue(toX);
    }

    private void animateViewDirectionY(final View v, float fromY, float toY, double tension, double friction) {
        Spring spring = mSpringSystem.createSpring();
        spring.setCurrentValue(fromY);
        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(tension, friction));
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                v.setTranslationY((float) spring.getCurrentValue());
//                v.setTranslationX((float) spring.getCurrentValue());
            }
        });
        spring.setEndValue(toY);
    }

    private void animateViewDirectionXY(final View v, float fromY, float toY, double tension, double friction) {
        Spring spring = mSpringSystem.createSpring();
        spring.setCurrentValue(fromY);
        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(tension, friction));
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                v.setTranslationY((float) spring.getCurrentValue());
                v.setTranslationX((float) spring.getCurrentValue());
            }
        });
        spring.setEndValue(toY);
    }

    public TextView getCountMenu() {
        return countMenu;
    }

    public interface OnMenuItemClickListener {
        void onMenuItemClick(View v);
    }

    public void setMenuItemOnclickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }


}
