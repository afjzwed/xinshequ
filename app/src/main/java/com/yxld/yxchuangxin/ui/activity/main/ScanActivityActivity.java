package com.yxld.yxchuangxin.ui.activity.main;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.socks.library.KLog;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ImageUtil;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.goods.ConfirmOrderCodeActivity;
import com.yxld.yxchuangxin.ui.activity.main.component.DaggerScanActivityComponent;
import com.yxld.yxchuangxin.ui.activity.main.contract.ScanActivityContract;
import com.yxld.yxchuangxin.ui.activity.main.module.ScanActivityModule;
import com.yxld.yxchuangxin.ui.activity.main.presenter.ScanActivityPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.main
 * @Description: $description
 * @date 2018/03/02 09:11:52
 */

public class ScanActivityActivity extends BaseActivity implements ScanActivityContract.View {

    @Inject
    ScanActivityPresenter mPresenter;
    @BindView(R.id.iv_shoudiantong)
    ImageView mIvShoudiantong;
    private CaptureFragment captureFragment;
    private int REQUEST_IMAGE = 160;//相册

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }

    @Override
    protected void initData() {

    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            KLog.i("扫码结果：" + result);
            //ToastUtil.showShort(result);
            if (!result.contains("pos")) {
                ToastUtil.showShort("无效的二维码");
//                //开启重新扫码
//                Message obtain = Message.obtain();
//                obtain.what = R.id.restart_preview;
//                captureFragment.getHandler().sendMessageDelayed(obtain, 1500);
                finish();
                return;
            }
            Intent resultIntent = new Intent(ScanActivityActivity.this, ConfirmOrderCodeActivity.class);
            resultIntent.putExtra(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            resultIntent.putExtra(CodeUtils.RESULT_STRING, result);
            startActivity(resultIntent);
            finish();
        }

        @Override
        public void onAnalyzeFailed() {
            KLog.i("解析失败");
            ToastUtil.showShort("解析二维码失败");
//            Intent resultIntent = new Intent();
//            Bundle bundle = new Bundle();
//            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
//            bundle.putString(CodeUtils.RESULT_STRING, "");
//            resultIntent.putExtras(bundle);
             finish();
        }
    };

    @Override
    protected void setupActivityComponent() {
        DaggerScanActivityComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .scanActivityModule(new ScanActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(ScanActivityContract.ScanActivityContractPresenter presenter) {
        mPresenter = (ScanActivityPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    private boolean isLight;

    /**
     * 手电筒开关点击事件
     */
    @OnClick(R.id.iv_shoudiantong)
    public void onViewClicked() {
        isLight = !isLight;
        if (isLight) {
            CodeUtils.isLightEnable(true);//开闪光灯
            mIvShoudiantong.setImageResource(R.mipmap.shoudiantong_on);

        } else {
            CodeUtils.isLightEnable(false);//关闪光灯
            mIvShoudiantong.setImageResource(R.mipmap.shoudiantong_off);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isLight) {
            CodeUtils.isLightEnable(false);//关闪光灯
        }
    }

    /**
     * 相册menu创建
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) //得到被点击的item的itemId
        {
            case R.id.xiangce:  //对应的ID就是在add方法中所设定的Id
                AndPermission.with(ScanActivityActivity.this)
                        .requestCode(101)
                        .permission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .callback(new PermissionListener() {
                            @Override
                            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(intent, REQUEST_IMAGE);
                            }

                            @Override
                            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                                ToastUtil.show(ScanActivityActivity.this, "没有权限!");
                            }
                        })
                        .start();

                break;
            case android.R.id.home:
                finish();
                System.gc();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_xiangce, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            //  Toast.makeText(ScanActivityActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                            KLog.i("文件扫码结果：" + result);
                            //ToastUtil.showShort(result);
                            if (!result.contains("pos")) {
                                ToastUtil.showShort("无效的二维码");
                                finish();
                                return;
                            }
                            Intent resultIntent = new Intent(ScanActivityActivity.this, ConfirmOrderCodeActivity.class);
                            resultIntent.putExtra(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
                            resultIntent.putExtra(CodeUtils.RESULT_STRING, result);
                            startActivity(resultIntent);
                            finish();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(ScanActivityActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}