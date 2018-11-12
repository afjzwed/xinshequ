package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.orhanobut.logger.Logger;
import com.qiniu.android.http.ResponseInfo;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ImgUtil;
import com.yxld.yxchuangxin.Utils.QiniuUploadUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerPqrzComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.PqrzContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.PqrzModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.PqrzPresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.PqrzAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PqrzAdapter(Arrays.asList("", ""));
        adapter.addHeaderView(headLayout());
        adapter.addFooterView(footLayout());
        rv.setAdapter(adapter);
    }

    View viewFoot;
    private View footLayout() {
        if (viewFoot==null){
            viewFoot= getLayoutInflater().inflate(R.layout.layout_foot_pqrz, (ViewGroup) rv.getParent(), false);
            TextView textView=viewFoot.findViewById(R.id.tv_commit);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.getQnToken();
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
                    ImgUtil.openCamera(PqrzActivity.this);
                }
            });
            imgf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImgUtil.openAlbum(PqrzActivity.this);
                }
            });
        }
        return viewHead;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //正确返回
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ImgUtil.TAKE_PHOTO://相机返回
                    Log.e("返回相机", ImgUtil.imageUri.toString()+new File(ImgUtil.imageUri.toString()).length());
                    Glide.with(this)
                            .load(ImgUtil.getImgByteFromUri(this, ImgUtil.imageUri))
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(imgz);
                    imgDataList.add(ImgUtil.getImgByteFromUri(this, ImgUtil.imageUri));
                    break;
                case ImgUtil.CHOOSE_PHOTO://相册返回
                    try {
                        if (data != null) {
                            //相册返回
                            Uri uri = data.getData();
                            Glide.with(this)
                                    .load(uri)
                                    .skipMemoryCache(true)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .into(imgf);
                            imgDataList.add(ImgUtil.getImgByteFromUri(this, uri));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
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
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void uploadimg(String token) {
        QiniuUploadUtil.uploadPics1(imgDataList, token, new QiniuUploadUtil.UploadCallback() {
            @Override
            public void sucess(String url) {
            }

            @Override
            public void sucess(List<String> url) {
                if (url.size()>0){
                    Logger.e(url.get(0)+"----------");
                }
                Logger.e(url.toString()+"-----------");
            }

            @Override
            public void fail(String key, ResponseInfo info) {

            }
        });
    }

}