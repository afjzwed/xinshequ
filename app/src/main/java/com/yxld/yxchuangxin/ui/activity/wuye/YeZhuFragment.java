package com.yxld.yxchuangxin.ui.activity.wuye;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hubcloud.adhubsdk.NativeAd;
import com.hubcloud.adhubsdk.NativeAdListener;
import com.hubcloud.adhubsdk.NativeAdResponse;
import com.hubcloud.adhubsdk.internal.nativead.NativeAdEventListener;
import com.hubcloud.adhubsdk.internal.nativead.NativeAdUtil;
import com.hubcloud.adhubsdk.internal.network.ServerResponse;
import com.socks.library.KLog;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.AppYezhuFangwu;
import com.yxld.yxchuangxin.entity.OpenDoorCode;
import com.yxld.yxchuangxin.entity.ShareInfo;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerYeZhuComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.YeZhuContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.YeZhuModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.YeZhuPresenter;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/06
 */

public class YeZhuFragment extends BaseFragment implements YeZhuContract.View {

    @Inject
    YeZhuPresenter mPresenter;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.rl_qrcode)
    AutoRelativeLayout rlQrcode;
    @BindView(R.id.tv_shuaxin)
    TextView tvShuaxin;
    @BindView(R.id.image)
    ImageView mImageView;
    @BindView(R.id.tv_guanggao)
    TextView tvGuanggao;
    private String address;

    private ShareInfo shareInfo = new ShareInfo();

    private AppYezhuFangwu house = new AppYezhuFangwu();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yezhu, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerYeZhuComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .yeZhuModule(new YeZhuModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(YeZhuContract.YeZhuContractPresenter presenter) {
        mPresenter = (YeZhuPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {
        fetchAd(getActivity());
        KLog.i("开始获取开门信息");
        shareInfo.setTitle("业主开门二维码");
        shareInfo.setShareCon(address);
        List<AppYezhuFangwu> list = Contains.appYezhuFangwus;
        if(list != null && list.size() != 0){
            house = list.get(Contains.curFangwu);
            KLog.i(house.toString());
            address = house.getXiangmuLoupan()+""+house.getFwLoudong()+"栋"+house.getFwDanyuan()+"单元" +house.getFwFanghao();
            if(house != null && Contains.user != null && Contains.user.getYezhuShouji() != null
                    && house.getFwLoupanId() != 0 && house.getFwLoudong() != null
                    && house.getFwDanyuan() != null){

                String bname = "";
                if(Contains.user.getYezhuName() == null || "".equals(Contains.user.getYezhuName())){
                    bname = Contains.user.getYezhuShouji();
                }else{
                    bname = Contains.user.getYezhuName();
                }
                Map<String, String> maps = new HashMap<String, String>();
                maps.put("uuid", Contains.uuid);
                maps.put("bName", bname);
                maps.put("bPhone", Contains.user.getYezhuShouji());
                maps.put("bRole", String.valueOf(house.getFwyzType()));
                maps.put("building", String.valueOf(house.getFwLoupanId()));
                maps.put("buildingHouse", house.getFwLoudong());
                maps.put("buildingUnit", house.getFwDanyuan());
                mPresenter.getQRCodeInfo(maps);
                KLog.i("开始获取开门信息");
            }else{
                ToastUtil.show(getActivity(),"业主信息不完善");
            }
        }
    }

    @Override
    public void creatQRcode(OpenDoorCode openDoorCode) {
        progressDialog.hide();
        if (!openDoorCode.getCode().equals("")) {
            //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
            rlQrcode.setVisibility(View.VISIBLE);
            Bitmap qrCodeBitmap = CodeUtils.createImage(openDoorCode.getCode(), 450, 450, BitmapFactory.decodeResource(getResources(), R.mipmap.login_icon_bg));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            qrCodeBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] bytes=baos.toByteArray();
            Glide.with(getActivity())
                    .load(bytes)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.mipmap.dly_logo)
                    .into(ivQrcode);
            shareInfo.setBitmap(qrCodeBitmap);
            String shareUrl = API.IP_PRODUCT + "/qr_code.html?timr=" + openDoorCode.getTime() + "&code=" + openDoorCode.getCode();
            shareInfo.setImgUrl(shareUrl);
        } else {
            Toast.makeText(getActivity(), "生成二维码失败,请重试", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void onDestroyView() {
        mPresenter.unsubscribe();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    @OnClick(R.id.tv_shuaxin)
    public void onViewClicked() {
        initDataFromLocal();
    }

    /**
     * 获取广告
     *
     * @param activity
     */
    public void fetchAd(final Activity activity) {
        final NativeAd nativeAd = new NativeAd(activity, "5429", new NativeAdListener() {

            @Override
            public void onAdFailed(int errorcode) {
               // Toast.makeText(activity, "onAdFailedToLoad reason: " + errorcode, Toast.LENGTH_LONG).show();
                KLog.e( "onAdFailedToLoad reason: " + errorcode);
            }

            @Override
            public void onAdLoaded(NativeAdResponse response) {
                KLog.e("onAdLoaded");
                // 一个广告只允许展现一次，多次展现、点击只会计入一次
                // demo仅简单地显示一条。可将返回的多条广告保存起来备用。
                KLog.e(response.toString());
               updateView(response);
                //返回设置的广告的多个图片的URL，SDK并未处理加载urls里面的图片，需要集成者自己去加载展示
                ArrayList<String> imageUrls = response.getImageUrls();

                //返回设置的广告的多个视频流的URL，SDK并未处理加载urls里面的视频，需要集成者自己去加载展示
                ArrayList<String> vedioUrls = response.getVedioUrls();
                //返回设置的广告的多个文本信息
                ArrayList<String> texts = response.getTexts();
                Log.e("wh", imageUrls.toString()+"---"+vedioUrls.toString()+"---"+texts.toString());

                //根据广告法新规定，必须加入广告标识和广告来源。此处返回广告字样及广告来源标识图片。需要开发者分别放置于广告左下和右下角
                //sdk内部提供了NativeAdUtil.addADLogo（View v，NativeAdResponse response）方法，可以将一个view加上logo并返回一个加入了logo的FrameLayout替代原本无logo的view;
                //注意若传入此方法的view之前已经有父view，调用了此方法之后原来的view会从父view中移除，须将方法返回的framelayout加入之前view的父view之中。
                //若此方法不满足要求，请开发者自己实现加入logo及广告字样。具体请参考本样例及样例效果
                // ServerResponse.AdLogoInfo结构
                // public static class AdLogoInfo {
                //    public static int TYPE_PIC = 0;
                //    public static int TYPE_TEXT = 1;
                //    String adurl;
                //    int type = 0;
                //    }
                //其中属性type为广告表示的类型共2种：图片和文字，如果type==TYPE_PIC则属性adurl是图片的url
                //如果是type==TYPE_TEXT则属性adurl是文字字符串

                //广告字样
                ServerResponse.AdLogoInfo adUrl = response.getAdUrl();
                //广告来源标识
                ServerResponse.AdLogoInfo adLogoInfo = response.getlogoUrl();
            }
        });
        //此方法已过时，请不要使用。SDK不再为接入者下载资源图片
        nativeAd.shouldLoadIcon(true);
        //此方法已过时，请不要使用。SDK不再为接入者下载资源图片
        //nativeAd.shouldLoadImage(true);
        nativeAd.loadAd();
    }
    private void updateView(NativeAdResponse response) {
        tvGuanggao.setVisibility(View.VISIBLE);
        Glide.with(getActivity()).load(Uri.parse(response.getImageUrls().get(0))).into(mImageView);
        NativeAdUtil.registerTracking(response,mImageView, new NativeAdEventListener() {
            @Override
            public void onAdWasClicked() {
                Toast.makeText(getActivity(), "onAdWasClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdWillLeaveApplication() {
                Toast.makeText(getActivity(), "onAdWillLeaveApplication", Toast.LENGTH_SHORT).show();
            }
        });

    }
}