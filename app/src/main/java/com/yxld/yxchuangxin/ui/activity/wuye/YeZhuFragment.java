package com.yxld.yxchuangxin.ui.activity.wuye;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
}