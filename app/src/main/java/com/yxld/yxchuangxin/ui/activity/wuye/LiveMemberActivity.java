package com.yxld.yxchuangxin.ui.activity.wuye;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.AppYezhuFangwu;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerLiveMemberComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.LiveMemberContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.LiveMemberModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.LiveMemberPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.LiveMemberAdapter;
import com.yxld.yxchuangxin.view.ConfirmDialog;
import com.yxld.yxchuangxin.view.NoUpFaceDialog;
import com.yxld.yxchuangxin.view.UploadFaceDialog;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/07
 */

public class LiveMemberActivity extends BaseActivity implements LiveMemberContract.View {

    @Inject
    LiveMemberPresenter mPresenter;

    @Inject
    LiveMemberAdapter liveMemberAdapter;
    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    @BindView(R.id.iv_add_member)
    ImageView ivAddMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_live_member);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recycerView.setAdapter(liveMemberAdapter);
        //如果不是业主或者家属，不能添加入住成员
        if (Contains.appYezhuFangwus.get(Contains.curFangwu).getFwyzType() > 1) {
            ivAddMember.setVisibility(View.GONE);
        }

        mPresenter.init();
    }

    @Override
    protected void initData() {  //?fwyzFw=%1$s&fwyzId=%2$s&uuid=%3$s
        Map<String, String> map = new HashMap<>();
        map.put("fwyzFw", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwId() + "");
        map.put("fwyzId", Contains.appYezhuFangwus.get(Contains.curFangwu).getYezhuId() + "");
        map.put("uuid", Contains.uuid + "");
        mPresenter.getAllLiveMember(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerLiveMemberComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .liveMemberModule(new LiveMemberModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(LiveMemberContract.LiveMemberContractPresenter presenter) {
        mPresenter = (LiveMemberPresenter) presenter;
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
    public void setMember(AppYezhuFangwu data) {
        liveMemberAdapter.setNewData(data.getRows());
        liveMemberAdapter.notifyDataSetChanged();
        liveMemberAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                if (view.getId() == R.id.iv_delet) {
                    ConfirmDialog.showDialog(LiveMemberActivity.this, "提示", "成员删除之后将无法恢复", new ConfirmDialog
                            .OnConfirmListener() {
                        @Override
                        public void onCancel() {

                        }

                        @Override
                        public void onConfirm() {
                            mPresenter.deletLiveMember(position);
                        }
                    });
                } else if (view.getId() == R.id.iv_avater) {
                    if (Contains.user.getYezhuType() == null || Contains.user.getYezhuType() != 0) {
                        showNoupFaceDialog();
                    } else {
                        String faceurl = "";
                        showUploadFaceDialog(faceurl);
                    }
                }
            }
        });
    }

    private UploadFaceDialog dialog;

    /**
     * 显示上传弹框
     * @param faceurl
     */
    private void showUploadFaceDialog(String faceurl) {
        dialog = new UploadFaceDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//代码中取消标题栏
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        dialog.setCancelable(false);
        dialog.show();
        dialog.getBt_album().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.showShort("从相册");
                mPresenter.fromAlbumUpLoad();
                dialog.dismiss();

            }
        });
        dialog.getBt_camera().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.showShort("拍摄");
                mPresenter.fromCameraUpLoad();
                dialog.dismiss();
            }
        });
    }

    /**
     * 显示无权限弹框
     */
    private void showNoupFaceDialog() {
        NoUpFaceDialog dialog = new NoUpFaceDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//代码中取消标题栏
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
//        dialog.setCancelable(false);
    }

    @Override
    public void deletMember(int position) {
        if (position == -1) {
        } else {
            liveMemberAdapter.remove(position);
        }
    }

    @Override
    public void onUploadOVer(String url) {
        Map<String, String> map = new HashMap<>();
        map.put("upFaceUrl", url);
        // TODO: 2018/12/13 改参数改地址
        mPresenter.upFace(map);
    }

    @Override
    public void onUpFaceBack() {
        ToastUtil.show(this, "上传人脸成功");
        closeProgressDialog();
        initData();
    }

    @OnClick(R.id.iv_add_member)
    public void onViewClicked() {
        startActivity(AddLiveMemberActivity.class);
        overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}