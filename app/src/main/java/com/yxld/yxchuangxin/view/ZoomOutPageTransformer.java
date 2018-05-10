package com.yxld.yxchuangxin.view;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/7/5
 * @descprition:
 */

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.9f;
    private static final float MIN_ALPHA = 0.5f;

    private static final float defaultScale = 0.9f;

    @Override
    public void transformPage(View view, float position) {

        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
//            view.setAlpha(0);
            view.setScaleX(defaultScale);
            view.setScaleY(defaultScale);
        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
//            view.setAlpha(0);
            view.setScaleX(defaultScale);
            view.setScaleY(defaultScale);
        }
    }






//    private static final float MAX_SCALE = 1.2f;
//    private static final float MIN_SCALE = 1.0f;//0.85f
//
//    @Override
//    public void transformPage(View view, float position) {
//        //setScaleY只支持api11以上
//        if (position < -1){
//            view.setScaleX(MIN_SCALE);
//            view.setScaleY(MIN_SCALE);
//        } else if (position <= 1) //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
//        { // [-1,1]
////              Log.e("TAG", view + " , " + position + "");
//            float scaleFactor =  MIN_SCALE+(1-Math.abs(position))*(MAX_SCALE-MIN_SCALE);
//            view.setScaleX(scaleFactor);
//            //每次滑动后进行微小的移动目的是为了防止在三星的某些手机上出现两边的页面为显示的情况
//            if(position>0){
//                view.setTranslationX(-scaleFactor*2);
//            }else if(position<0){
//                view.setTranslationX(scaleFactor*2);
//            }
//            view.setScaleY(scaleFactor);
//
//        } else
//        { // (1,+Infinity]
//
//            view.setScaleX(MIN_SCALE);
//            view.setScaleY(MIN_SCALE);
//
//        }
//    }
}
