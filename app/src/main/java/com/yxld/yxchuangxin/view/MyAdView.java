package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.contain.Contains;

import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/7/13
 * @descprition:
 */

public class MyAdView extends ViewPager {
    //选中颜色
    private int mSelectedColor;
    //未选中颜色
    private int mUnSelectedColor;
    //背景圆初始半径
    private float mNomarlRadius = 20;
    //圆点之间距离
    private float mDistance = 80;

    //起始圆初始半径
    private float mRadius = 30;

    public MyAdView(Context context) {
        this(context,null);
    }

    public MyAdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray=getContext().obtainStyledAttributes(attrs, R.styleable.BezierBannerDot);
        mSelectedColor=typedArray.getColor(R.styleable.BezierBannerDot_selectedColor,0xFFFFFFFF);
        mUnSelectedColor=typedArray.getColor(R.styleable.BezierBannerDot_unSelectedColor,0xFFAAAAAA);
        mRadius=typedArray.getDimension(R.styleable.BezierBannerDot_selectedRaduis,mRadius);
        mNomarlRadius=typedArray.getDimension(R.styleable.BezierBannerDot_unSelectedRaduis,mNomarlRadius);
        mDistance =typedArray.getDimension(R.styleable.BezierBannerDot_spacing, mDistance);
        typedArray.recycle();
    }

    public void startCircle(List<String> urls){
//        BezierBannerDot dots = new BezierBannerDot(getContext());
//        dots.setAttrs(mSelectedColor,mUnSelectedColor, mRadius,mNomarlRadius,mDistance);
//        MyAdViewAdapter adapter = new MyAdViewAdapter(urls);
//        setAdapter(adapter);
//        dots.attachToViewpager(this);
    }


    public static class MyAdViewAdapter  extends PagerAdapter{
        private List<String> imgUrls;
        private Context context;
        private LayoutInflater inflater;
        public MyAdViewAdapter(List<String> imgUrls, Context context) {
            this.imgUrls = imgUrls;
            this.context = context;
            this.inflater = LayoutInflater.from(this.context);
        }

        @Override
        public int getCount() {
            return imgUrls == null?0:imgUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = inflater.inflate(R.layout.item_ad,null);
            ImageView iv = (ImageView) view.findViewById(R.id.iv_ad);
//            Glide.with(context)
//                    .load(imgUrls.get(position))
//                    .error(R.mipmap.main_banner1)
//                    .into(iv);
            iv.setImageResource(R.mipmap.main_banner1);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

    }
}
