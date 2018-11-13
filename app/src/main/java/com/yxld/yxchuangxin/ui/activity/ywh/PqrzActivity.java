package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.qiniu.android.http.ResponseInfo;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ImgUtil;
import com.yxld.yxchuangxin.Utils.QiniuUploadUtil;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.YwhHouse;
import com.yxld.yxchuangxin.ui.activity.index.util.FileUtils;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerPqrzComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.PqrzContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.PqrzModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.PqrzPresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.PqrzAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/09 08:55:03
 */

public class PqrzActivity extends BaseActivity implements PqrzContract.View {

    @Inject
    PqrzPresenter mPresenter;
    @BindView(R.id.rv) RecyclerView rv;
    private PqrzAdapter adapter;
    private List<byte[]> imgDataList = new ArrayList<>();
    private String zmPath;
    private String fmPath;
    private int type; //1身份证正面拍照2身份证反面面拍照3房产证4手持房产证
    private int positon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_pqrz);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initRv();
        showAlerView(1);
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PqrzAdapter();
        rv.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                positon = i;
                switch (view.getId()) {
                    case R.id.img_fcz:
                        type = 3;
                        ImgUtil.openCamera(PqrzActivity.this);
                        break;
                    case R.id.img_tx:
                        type = 4;
                        ImgUtil.openAlbum(PqrzActivity.this);

                        break;
                }
            }
        });
    }

    View viewFoot;
    private View footLayout() {
        if (viewFoot==null){
            viewFoot= getLayoutInflater().inflate(R.layout.layout_foot_pqrz, (ViewGroup) rv.getParent(), false);
            TextView textView=viewFoot.findViewById(R.id.tv_commit);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickCommit();
                }
            });
        }
        return viewFoot;
    }


    ImageView imgz;
    ImageView imgf;
    View viewHead;

    private View headLayout() {
        if (viewHead == null) {
            viewHead = getLayoutInflater().inflate(R.layout.layout_head_pqrz, (ViewGroup) rv.getParent(), false);
            imgz = viewHead.findViewById(R.id.img_sfzz);
            imgf = viewHead.findViewById(R.id.img_sfzf);
            imgz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type = 1;
                    ImgUtil.openCamera(PqrzActivity.this);
                }
            });
            imgf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type = 2;
                    ImgUtil.openAlbum(PqrzActivity.this);
                }
            });
        }
        return viewHead;
    }

    @Override
    protected void initData() {
        mPresenter.getHouse();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //正确返回
        if (resultCode == RESULT_OK) {
//            Bitmap bitmap = null;
            switch (requestCode) {
                case ImgUtil.TAKE_PHOTO://相机返回
                    ImgUtil.CropPhoto(this, ImgUtil.imageUri);
                    break;
                case ImgUtil.CHOOSE_PHOTO://相册返回
                    try {
                        if (data != null) {
                            ImgUtil.CropPhoto(this, data.getData());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case ImgUtil.PHOTO_CROP://裁剪返回
                    Bitmap bitmap = ImgUtil.decodeUriAsBitmap(this, ImgUtil.imageCropUri);
                    switch (type) {
                        case 1:
                            //身份证正面
                            zmPath = FileUtils.saveBitmap(bitmap, String.valueOf(System.currentTimeMillis()));
                            Glide.with(this)
                                    .load(zmPath)
                                    .skipMemoryCache(true)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .into(imgz);
                            break;
                        case 2:
                            //身份证反面
                            fmPath = FileUtils.saveBitmap(bitmap, String.valueOf(System.currentTimeMillis()));
                            Glide.with(this)
                                    .load(fmPath)
                                    .skipMemoryCache(true)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .into(imgf);
                            break;
                        case 3:
                            //房产证
                            YwhHouse.DataBean dataBean = adapter.getData().get(positon);
                            dataBean.setDeedImage(FileUtils.saveBitmap(bitmap, String.valueOf(System.currentTimeMillis())));
                            adapter.notifyDataSetChanged();

                            break;
                        case 4:
                            //手持房产证和人头像
                            YwhHouse.DataBean dataBean1 = adapter.getData().get(positon);
                            dataBean1.setPaperWork(FileUtils.saveBitmap(bitmap, String.valueOf(System.currentTimeMillis())));
                            adapter.notifyDataSetChanged();
                            break;
                    }

                    break;
            }
        } else {
        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerPqrzComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .pqrzModule(new PqrzModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(PqrzContract.PqrzContractPresenter presenter) {
        mPresenter = (PqrzPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void uploadimg(String token) {

    }

    @Override
    public void getHoustSuccess(YwhHouse baseEntity) {
        if (baseEntity.getCode() == 200) {
            adapter.addHeaderView(headLayout());
            adapter.setNewData(baseEntity.getData());
            adapter.addFooterView(footLayout());
        }
    }

    @Override
    public void commitSuccess(BaseEntity baseEntity) {
        if (baseEntity.getMsg().equals("操作成功")) {
            EventBus.getDefault().post(ThirdFragment.EVEBUS_MSG);
            finish();
        } else {
            onError(baseEntity.getMsg());
        }
    }


    /**
     * 点击提交按钮
     */
    private void clickCommit() {
        if (TextUtils.isEmpty(zmPath) || TextUtils.isEmpty(fmPath)) {
            ToastUtil.showShort("照片信息不完整");
            return;
        }
        for (int i = 0; i < adapter.getData().size(); i++) {
            if (TextUtils.isEmpty(adapter.getData().get(i).getDeedImage()) || TextUtils.isEmpty(adapter.getData().get(i).getDeedImage())) {
                ToastUtil.showShort("照片信息不完整");
                return;
            }
        }
        List<String> list = new ArrayList<>();
        list.add(zmPath);
        list.add(fmPath);
        for (int i = 0; i < adapter.getData().size(); i++) {
            list.add(adapter.getData().get(i).getDeedImage());
            list.add(adapter.getData().get(i).getPaperWork());
        }
        showProgressDialog();
        QiniuUploadUtil.uploadPics(list, new QiniuUploadUtil.UploadCallback() {
            @Override
            public void sucess(String url) {

            }

            @Override
            public void sucess(List<String> url) {
                Logger.e("七牛上传图片成功" + url.toString());

                Map<String, String> map = new HashMap<>();
                map.put("uuid", Contains.uuid);
                map.put("cardFront", url.get(0));
                map.put("cardReverse", url.get(1));
                for (int i = 0; i < adapter.getData().size(); i++) {
                    map.put("houses[" + i + "].deedImage", url.get(i * 2 + 2));
                    map.put("houses[" + i + "].paperWork", url.get(i * 2 + 3));
                    map.put("houses[" + i + "].id", adapter.getData().get(i).getId() + "");
                }
                mPresenter.commit(map);
            }

            @Override
            public void fail(String key, ResponseInfo info) {
                closeProgressDialog();

            }
        });

    }

    /**
     * 选择拍照或图片
     */
    private void showAlerView(int lx) {
        new AlertView("上传头像", null, "取消", null, new String[]{"拍照", "从相册中选择"}, this, AlertView.Style.ActionSheet, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                Logger.e("点击了第" + position + "个");
            }
        }).show();

    }
}