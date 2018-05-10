package com.yxld.yxchuangxin.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;
import com.yxld.yxchuangxin.ui.adapter.rim.RimGoodDetailAdapter;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * @author xlei
 * @Date 2017/12/27.
 */

public class RimSpDetailDialog extends BaseDialog {
    AutoRelativeLayout changeImg;
    private NoScrollViewPager noScrollViewPager;
    TextView goodDescription;
    static CxwyProductInfo.DataBean pro;
    TextView cuttentPrice;
    TextView originalPrice;
    TextView proName;
    Context mContext;
    ImageButton rightArrows;
    ImageButton leftArrows;
    ImageButton addGoods;
    private addListener mAddListener;

    public RimSpDetailDialog(Context context, CxwyProductInfo.DataBean pro1) {
        super(context);
        mContext = context;
        pro = pro1;
    }

//    public static void showDialog(Context context, CxwyProductInfo.DataBean pro1 ,addListener addListener) {
//        pro = pro1;
//        RimSpDetailDialog dialog = new RimSpDetailDialog(context);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.setAddListener(addListener);
//        dialog.show();
//    }

    public void setAddListener(addListener addListener) {
        mAddListener = addListener;
    }

    @Override
    public void initView() {
        setContentView(R.layout.dialog_good_detail);
        noScrollViewPager = (NoScrollViewPager) findViewById(R.id.nsvp);
        changeImg = (AutoRelativeLayout) findViewById(R.id.autorl_change_img);
        leftArrows = (ImageButton) findViewById(R.id.ib_left_arrows);
        rightArrows = (ImageButton) findViewById(R.id.ib_right_arrows);
        goodDescription = (TextView) findViewById(R.id.tv_good_description);
        goodDescription.setMovementMethod(ScrollingMovementMethod.getInstance() );
        cuttentPrice = (TextView) findViewById(R.id.tv_current_price);
        originalPrice = (TextView) findViewById(R.id.tv_original_price);
        proName = (TextView) findViewById(R.id.tv_name);
        addGoods = (ImageButton) findViewById(R.id.ib_add_goods);
    }

    @Override
    public void initListener() {
        leftArrows.setOnClickListener(this);
        rightArrows.setOnClickListener(this);
        addGoods.setOnClickListener(this);
    }

    @Override
    public void initData() {
        String[] split = pro.getProductImage().split(",");
        //判断此商品或服务是否有多图，是否显示切换按钮
        if (null != split && split.length > 1) {
            changeImg.setVisibility(View.VISIBLE);
        } else {
            changeImg.setVisibility(View.GONE);
        }
        RimGoodDetailAdapter adapter = new RimGoodDetailAdapter((Activity) mContext, split);
        noScrollViewPager.setAdapter(adapter);
        proName.setText(pro.getProductName());

      goodDescription.setText(pro.getProductDetails());
        cuttentPrice.setText("¥ " + pro.getProductPreferentialPrice() + " / ");
        originalPrice.setText("¥ " + pro.getProductPrice());
        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ib_left_arrows:
                if (noScrollViewPager.getCurrentItem() > -1) {
                    noScrollViewPager.setCurrentItem(noScrollViewPager.getCurrentItem() - 1);
                }
                break;
            case R.id.ib_right_arrows:
                if (noScrollViewPager.getCurrentItem() < 5) {
                    noScrollViewPager.setCurrentItem(noScrollViewPager.getCurrentItem() + 1);
                }
                break;
            case R.id.ib_add_goods:
                if (mAddListener != null) {
                    mAddListener.add(v, pro.getProductImage().split(",")[0]);
                }
                break;
            default:
                break;

        }

    }

    public interface addListener {
        void add(View view, String url);
    }
}
