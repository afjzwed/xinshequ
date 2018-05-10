package com.yxld.yxchuangxin.ui.activity.base;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.index.util.Bimp;
import com.yxld.yxchuangxin.ui.activity.index.util.ImageItem;

import java.util.ArrayList;

import static com.yxld.yxchuangxin.data.api.API.PIC;

/**
 * Created by liuheng on 2015/8/19.
 */
public class ViewPagerActivity extends BaseActivity {

    private ViewPager mPager;

    private String[] imgsId = new String[]{};
    private ArrayList<ImageItem> img = new ArrayList<>();


    private int currentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        currentItem = getIntent().getIntExtra("item", 0);
        if (!getIntent().getStringExtra("url").equals("")) {
            imgsId = getIntent().getStringExtra("url").split(";");
        } else {
            if (getIntent().getStringExtra("url1").equals("0")) {
                for (ImageItem item : Bimp.tempSelectBitmap) {
                    if (item.isSelected()) {
                        img.add(item);
                    }
                }
            }
            if (getIntent().getStringExtra("url1").equals("1")) {
                for (ImageItem item : Bimp.tempTousuSelectBitmap) {
                    if (item.isSelected()) {
                        img.add(item);
                    }
                }
            }
            if (getIntent().getStringExtra("url1").equals("2")) {
                for (ImageItem item : Bimp.tempJianyiSelectBitmap) {
                    if (item.isSelected()) {
                        img.add(item);
                    }
                }
            }
            if (getIntent().getStringExtra("url1").equals("3")) {
                for (ImageItem item : Bimp.tempShouhouSelectBitmap) {
                    if (item.isSelected()) {
                        img.add(item);
                    }
                }
            }
        }

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        mPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                if (img.size() == 0) {
                    return imgsId.length;
                }
                return img.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(ViewPagerActivity.this);
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                if (img.size() == 0) {
                    Glide.with(ViewPagerActivity.this)
                            .load(PIC + imgsId[position])
                            .into(view);
                } else {
                    view.setImageBitmap(img.get(position).getBitmap());
                }
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        mPager.setCurrentItem(currentItem);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setupActivityComponent() {

    }
}
