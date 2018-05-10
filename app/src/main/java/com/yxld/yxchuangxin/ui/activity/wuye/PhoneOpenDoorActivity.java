package com.yxld.yxchuangxin.ui.activity.wuye;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.PopWindowUtil;
import com.yxld.yxchuangxin.Utils.ScreenUtil;
import com.yxld.yxchuangxin.Utils.YouMengShareUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.ShareInfo;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerPhoneOpenDoorComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.PhoneOpenDoorContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.PhoneOpenDoorModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.PhoneOpenDoorPresenter;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.zhy.autolayout.AutoRelativeLayout;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/06
 */

public class PhoneOpenDoorActivity extends BaseActivity implements PhoneOpenDoorContract.View, PopWindowUtil.OnSubmitClickListener {

    @Inject
    PhoneOpenDoorPresenter mPresenter;
    @BindView(R.id.iv_erweima)
    ImageView ivErweima;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.rl_erweima)
    AutoRelativeLayout rlErweima;
    @BindView(R.id.time)
    TextView Time;

    private ShareInfo shareInfo = new ShareInfo();
    private String time;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_phoneopendoor);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        ScreenUtil.setBrightness(this, 255);
        time = getIntent().getStringExtra("time");
        address = getIntent().getStringExtra("address");
        shareInfo.setTitle("手机开门二维码");
        shareInfo.setShareCon(address);
        String code = getIntent().getStringExtra("code");
        if (!getIntent().getStringExtra("code").equals("")) {
            //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
            Bitmap qrCodeBitmap = CodeUtils.createImage(getIntent().getStringExtra("code"), 450, 450, BitmapFactory.decodeResource(getResources(), R.mipmap.login_icon_bg));
            ivErweima.setImageBitmap(qrCodeBitmap);
            shareInfo.setBitmap(qrCodeBitmap);
            String shareUrl = API.IP_PRODUCT + "/qr_code.html?timr=" + getIntent().getStringExtra("time") + "&code=" + getIntent().getStringExtra("code");
            shareInfo.setImgUrl(shareUrl);
            Timestamp dateStr = new Timestamp(Long.parseLong(time));

            try {
                dateStr = Timestamp.valueOf(dateStr.toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Log.d("geek", "getOpenDoor: sdf" + sdf.format(dateStr));
                Time.setText("有效期至：" + sdf.format(dateStr));
            } catch (Exception e) {
                e.printStackTrace();
                Time.setText("有效期至：" + dateStr);
            }

            try{
                code = URLEncoder.encode(code,"UTF-8").toString();
            }catch (Exception e){
                e.printStackTrace();
            }
            String qqurl = API.IP_PRODUCT+"/qr_code.html?timr="+time+"&code="+code;
            shareInfo.setQQImgUrl(qqurl);

            shareInfo.setImgUrl(API.IP_PRODUCT+"/qr_code.html?timr="+time+"&code="+code);
        } else {
            Toast.makeText(this, "生成二维码失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerPhoneOpenDoorComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .phoneOpenDoorModule(new PhoneOpenDoorModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(PhoneOpenDoorContract.PhoneOpenDoorContractPresenter presenter) {
        mPresenter = (PhoneOpenDoorPresenter) presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                YouMengShareUtil mengShareUtil = new YouMengShareUtil(this);
                mengShareUtil.addCustomPlatforms(shareInfo);
//                PopWindowUtil popWindowUtil = new PopWindowUtil();
//                popWindowUtil.showSharePopWindow(this, ivErweima);
//                popWindowUtil.setOnSubmitClickListener(this);
                break;
            case android.R.id.home:
                finish();
                System.gc();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (CustomPopWindow.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onSubmitClick(View v, String time) {
        switch (v.getId()) {
            case R.id.share_weixin:
                break;
            case R.id.share_qq:
                break;
            case R.id.share_msg:
                break;
        }
    }
}