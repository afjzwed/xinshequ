package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.LiuCheng;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerFixLiuChengComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FixLiuChengContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.FixLiuChengModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.FixLiuChengPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.LiuChengAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2018/05/14 10:23:41
 */

public class FixLiuChengActivity extends BaseActivity implements FixLiuChengContract.View {

    public static final String BAOXIU_ID = "baoxiu_id";
    @Inject
    FixLiuChengPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private int baoxiuId;
    private List<LiuCheng> mLiuChengList;
    private LiuChengAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_fix_liucheng);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        mLiuChengList = new ArrayList<>();
        mAdapter = new LiuChengAdapter(mLiuChengList);
        recyclerView.setAdapter(mAdapter);

        baoxiuId = getIntent().getIntExtra(BAOXIU_ID, 0);
        if (baoxiuId != 0) {
            loadData();
        }

    }

    private void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("baoxiuId", baoxiuId + "");
        mPresenter.getData(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFixLiuChengComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .fixLiuChengModule(new FixLiuChengModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(FixLiuChengContract.FixLiuChengContractPresenter presenter) {
        mPresenter = (FixLiuChengPresenter) presenter;
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
    public void setData(LiuCheng liuCheng) {
        if (liuCheng.status == 1) {
            mAdapter.setNewData(liuCheng.getRows());
        } else {
            // onError(liuCheng.status);
        }
    }

}