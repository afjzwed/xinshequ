package com.yxld.yxchuangxin.ui.activity.wuye;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.PopWindowUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.base.ViewPagerActivity;
import com.yxld.yxchuangxin.ui.activity.index.util.Bimp;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerFixComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FixContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.FixModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.FixPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.GridAdapter;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.zhy.autolayout.AutoLinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/15
 */

public class FixActivity extends BaseActivity implements FixContract.View, CheckBox.OnCheckedChangeListener {

    @Inject
    FixPresenter mPresenter;
    @Inject
    GridLayoutManager gridLayoutManager;
    @Inject
    GridAdapter gridAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static Bitmap bimap;
    @BindView(R.id.cb_private)
    CheckBox cbPrivate;
    @BindView(R.id.cb_public)
    CheckBox cbPublic;
    @BindView(R.id.cb_smart_fix)
    CheckBox cbSmartFix;
    @BindView(R.id.cb_big_fix)
    CheckBox cbBigFix;
    @BindView(R.id.et_location)
    EditText etLocation;
    @BindView(R.id.et_detail)
    EditText etDetail;
    @BindView(R.id.bt_commit)
    Button btCommit;
    @BindView(R.id.tv_fix_list)
    TextView tvFixList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_fix);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cbPrivate.setOnCheckedChangeListener(this);
        cbPublic.setOnCheckedChangeListener(this);
        cbSmartFix.setOnCheckedChangeListener(this);
        cbBigFix.setOnCheckedChangeListener(this);
        StringUitl.setEditTextInhibitInputSpeOnlyChnese50(etDetail);
        StringUitl.setInputName(etLocation);
    }

    @Override
    protected void initData() {
        mPresenter.init();
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(gridAdapter);
        gridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (Bimp.tempSelectBitmap.get(position).isSelected()) {
                    Intent intent = new Intent(FixActivity.this, ViewPagerActivity.class);
                    intent.putExtra("item", position);
                    intent.putExtra("url", "");
                    intent.putExtra("url1", "0");
                    startActivity(intent);
                } else {
                    showImgPickPop(recyclerView, position);
                }
            }
        });
        gridAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bimp.tempSelectBitmap.get(position).setSelected(false);
                gridAdapter.notifyDataSetChanged();
            }
        });
    }

    private void showImgPickPop(View v, int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_pick_image, null);
        Button item_popupwindows_camera = (Button) view.findViewById(R.id.item_popupwindows_camera);
        Button item_popupwindows_Photo = (Button) view.findViewById(R.id.item_popupwindows_Photo);
        Button item_popupwindows_cancel = (Button) view.findViewById(R.id.item_popupwindows_cancel);
        item_popupwindows_camera.setOnClickListener(v12 -> {
            mPresenter.fromCameraUpLoad(position);
            CustomPopWindow.onBackPressed();
        });
        item_popupwindows_Photo.setOnClickListener(v13 -> {
            mPresenter.fromAlbumUpLoad(position);
            CustomPopWindow.onBackPressed();
        });
        item_popupwindows_cancel.setOnClickListener(v1 -> CustomPopWindow.onBackPressed());
        AutoLinearLayout ll_popup = (AutoLinearLayout) view.findViewById(R.id.ll_popup);

        PopWindowUtil.showFullScreenPopWindow(this, v, view, ll_popup);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFixComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .fixModule(new FixModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FixContract.FixContractPresenter presenter) {
        mPresenter = (FixPresenter) presenter;
    }

    @Override
    public void onBackPressed() {
        if (CustomPopWindow.onBackPressed()) {
            return;
        }
        super.onBackPressed();
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
    public void onPickImgBack() {
        gridAdapter.notifyDataSetChanged();
    }

    @Override
    public void onUpLoadSuccess() {
        etDetail.setText("");
        etLocation.setText("");
        Bimp.tempSelectBitmap.clear();
        ToastUtil.show(this, "提交成功，我们会尽快处理");
        closeProgressDialog();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_private:
                if (isChecked) {
                    cbPublic.setChecked(false);
                    cbSmartFix.setVisibility(View.GONE);
                    cbBigFix.setVisibility(View.GONE);
                } else {
                    cbPublic.setChecked(true);
                    cbSmartFix.setVisibility(View.VISIBLE);
                    cbBigFix.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.cb_public:
                if (isChecked) {
                    cbPrivate.setChecked(false);
                    cbSmartFix.setVisibility(View.VISIBLE);
                    cbBigFix.setVisibility(View.VISIBLE);
                } else {
                    cbPrivate.setChecked(true);
                    cbSmartFix.setVisibility(View.GONE);
                    cbBigFix.setVisibility(View.GONE);
                }
                break;
            case R.id.cb_smart_fix:
                if (isChecked) {
                    Contains.repairQuyu="1";
                    cbBigFix.setChecked(false);
                } else {
                    cbBigFix.setChecked(true);
                }
                break;
            case R.id.cb_big_fix:
                if (isChecked) {
                    Contains.repairQuyu="2";
                    cbSmartFix.setChecked(false);
                } else {
                    cbSmartFix.setChecked(true);
                }
                break;
            default:break;
        }
    }

    @OnClick({R.id.bt_commit, R.id.tv_fix_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_commit:
                int imgCount = 0;
                if (!cbPrivate.isChecked() && !cbPublic.isChecked()) {
                    ToastUtil.showShort("请选择部位");
                    return;
                }
                if (cbPublic.isChecked()) {
                    if (!cbSmartFix.isChecked() && !cbBigFix.isChecked()) {
                        ToastUtil.showShort("请选择维修大小");
                        return;
                    }
                }
                if (StringUitl.isNotEmpty(FixActivity.this, etLocation, "请输入维修地点")) {
                    Contains.repairAddressStr = etLocation.getText().toString();
                    if (StringUitl.isNotEmpty(FixActivity.this, etDetail, "请输入维修详情")) {
                        Contains.repairContextStr = etDetail.getText().toString();
                        for (int i = 0; i < 3; i++) {
                            if (Bimp.tempSelectBitmap.get(i).isSelected()) {
                                imgCount++;
                            }
                        }
                        if (imgCount != 0) {
                            //上传逻辑
                            Bimp.imgCount = imgCount;
                            mPresenter.upLoadImg();
                        } else {
                            mPresenter.saveOrUpdateTalk();
                        }
                    }
                }
                break;
            case R.id.tv_fix_list:
                startActivity(FixListActivity.class);
                break;
            default:break;
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}