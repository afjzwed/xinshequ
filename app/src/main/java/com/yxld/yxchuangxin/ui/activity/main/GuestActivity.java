package com.yxld.yxchuangxin.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.login.LoginActivity;
import com.yxld.yxchuangxin.ui.activity.main.component.DaggerGuestComponent;
import com.yxld.yxchuangxin.ui.activity.main.contract.GuestContract;
import com.yxld.yxchuangxin.ui.activity.main.module.GuestModule;
import com.yxld.yxchuangxin.ui.activity.main.presenter.GuestPresenter;
import com.yxld.yxchuangxin.view.BezierBannerDot;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: $description
 * @date 2017/06/30 10:41:13
 */

public class GuestActivity extends BaseActivity implements GuestContract.View {

    @Inject
    GuestPresenter mPresenter;
    @BindView(R.id.vp_guide)
    ViewPager vpGuide;
    @BindView(R.id.btn_guest_start)
    Button btnGuestStart;
    @BindView(R.id.tiaoguo)
    Button btTiaoguo;
    @BindView(R.id.bd)
    BezierBannerDot bd;
    private int mPointDis;

    private ArrayList<ImageView> imagelist;
    private int[] mImageIds = new int[]{R.mipmap.xsq_ydy, R.mipmap.xsq_ydy2, R.mipmap.xsq_ydy3, R
            .mipmap.xsq_ydy4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_guest);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setTitle("");
        toolbar.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        imagelist = new ArrayList<ImageView>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView view = new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);
            imagelist.add(view);

            ImageView point = new ImageView(this);
            point.setImageResource(R.drawable.shape_point_gray);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                params.leftMargin = UIUtils.dip2px(7);
            }
            point.setLayoutParams(params);
//            llContainer.addView(point);
        }
        vpGuide.setAdapter(new mViewPagerAdapter());
        bd.attachToViewpager(vpGuide);
        vpGuide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    btnGuestStart.setVisibility(View.VISIBLE);
                    btTiaoguo.setVisibility(View.INVISIBLE);
                }
                if (position == 2) {
                    btnGuestStart.setVisibility(View.INVISIBLE);
                    btTiaoguo.setVisibility(View.VISIBLE);
                }
                if (position == 1) {
                    btnGuestStart.setVisibility(View.INVISIBLE);
                    btTiaoguo.setVisibility(View.VISIBLE);
                }
                if (position == 0) {
                    btnGuestStart.setVisibility(View.INVISIBLE);
                    btTiaoguo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //计算小红点的偏移量
//                int leftmargin = (int) (mPointDis*(positionOffset + position));
//                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint.getLayoutParams();
//                params.leftMargin = leftmargin;
//                ivRedPoint.setLayoutParams(params);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // TODO Auto-generated method stub

            }
        });
//        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//
//            @Override
//            public void onGlobalLayout() {
//                ivRedPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                mPointDis = llContainer.getChildAt(1).getLeft() - llContainer.getChildAt(0).getLeft();
//
//            }
//        });
    }

    @Override
    protected void setupActivityComponent() {
        DaggerGuestComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .guestModule(new GuestModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(GuestContract.GuestContractPresenter presenter) {
        mPresenter = (GuestPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick({R.id.btn_guest_start, R.id.tiaoguo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_guest_start:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("flag", "other");
                startActivity(intent);
                break;
            case R.id.tiaoguo:
                Intent intent1 = new Intent(this, LoginActivity.class);
                intent1.putExtra("flag", "other");
                startActivity(intent1);
                break;
        }
    }

    public class mViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imagelist.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = imagelist.get(position);
            container.addView(view);
            return view;
        }

    }

}