package com.yxld.yxchuangxin.ui.activity.common;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyCommon;
import com.yxld.yxchuangxin.ui.activity.common.component.DaggerAisleComponent;
import com.yxld.yxchuangxin.ui.activity.common.contract.AisleContract;
import com.yxld.yxchuangxin.ui.activity.common.module.AisleModule;
import com.yxld.yxchuangxin.ui.activity.common.presenter.AislePresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.AisleAdapter;
import com.yxld.yxchuangxin.ui.adapter.wuye.SpacesItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.commom
 * @Description: $description
 * @date 2017/06/08
 */

public class AisleActivity extends BaseActivity implements AisleContract.View {

    @Inject
    AislePresenter mPresenter;

    @Inject
    AisleAdapter aisleAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_aisle);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("appKey", AppConfig.AppKey);
        map.put("appSecret", "8b2ee852dadd753b8138494800b4afe7");
        mPresenter.getCommonToken(map);

        Map<String,String> map1=new HashMap<>();
        map1.put("uuid", Contains.uuid);
        mPresenter.getCommon(map1);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerAisleComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .aisleModule(new AisleModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(AisleContract.AisleContractPresenter presenter) {
        mPresenter = (AislePresenter) presenter;
    }


    @Override
    public void setAdapter(List<CxwyCommon.DataBean.CvoListBean> data) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(AisleActivity.this, 2));
        recyclerView.addItemDecoration(new SpacesItemDecoration(UIUtils.getDisplayWidth(AisleActivity.this) / 1080 * 20));
        aisleAdapter.setNewData(data);
        recyclerView.setAdapter(aisleAdapter);
        aisleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.aisleAdapterOnItemClick(position);
            }
        });
        if (data == null || data.size() == 0) {
            ToastUtil.show(this, "数据为空");
            return;
        }
    }
}