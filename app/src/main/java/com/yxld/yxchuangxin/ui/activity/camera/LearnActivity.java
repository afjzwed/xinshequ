package com.yxld.yxchuangxin.ui.activity.camera;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.p2p.core.P2PHandler;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerLearnComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.LearnContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.LearnModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.LearnPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.LearnAdapter;
import com.yxld.yxchuangxin.yoosee.LearnEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/06/21 10:22:13
 */

public class LearnActivity extends BaseActivity implements LearnContract.View {

    @Inject
    LearnPresenter mPresenter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private String deviceId,devicePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_learn);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EventBus.getDefault().register(this);
        //设备账号密码获取请求摄像头防区
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        deviceId =bundle.getString("deviceId");
        devicePwd  = bundle.getString("devicePwd");
        P2PHandler.getInstance().getDefenceArea(deviceId, devicePwd);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerLearnComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .learnModule(new LearnModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(LearnContract.LearnContractPresenter presenter) {
        mPresenter = (LearnPresenter) presenter;
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    /**
     * 学习设备的返回
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void LearnEventBus(LearnEvent message) {
        switch (message.result) {
            case 0:
                Toast.makeText(this, "学习成功啦~", Toast.LENGTH_SHORT).show();
                break;
            case 24:
                Toast.makeText(this, "该通道已学", Toast.LENGTH_SHORT).show();
                break;
            case 26:
                Toast.makeText(this, "学码超时", Toast.LENGTH_SHORT).show();
                break;
            case 32:
                Toast.makeText(this, "此码已被学", Toast.LENGTH_SHORT).show();
                break;
            case 37:
                Toast.makeText(this, "无效的码值", Toast.LENGTH_SHORT).show();
                break;
        }

        Log.d("...", "helloEventBus: ");
        LearnAdapter learnAdapter = new LearnAdapter(mPresenter.generateData(message.data),deviceId,devicePwd);
        recyclerview.setAdapter(learnAdapter);
    }

}