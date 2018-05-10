package com.yxld.yxchuangxin.ui.activity.camera;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.FangquEntity;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerFangquListComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.FangquListContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.FangquListModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.FangquListPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.FangQuListAdapter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/09/07 01:27:17
 */

public class FangquListActivity extends BaseActivity implements FangquListContract.View {

    @Inject
    FangquListPresenter mPresenter;
    @Inject
    FangQuListAdapter fangQuListAdapter;
    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_fangqu_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        LinearLayoutManager ll = new LinearLayoutManager(this);
        recycerView.setLayoutManager(ll);
        recycerView.setAdapter(fangQuListAdapter);
        fangQuListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(FangquListActivity.this, ModifyFangquActivity.class);
                intent.putExtra("databean", fangQuListAdapter.getData().get(i));
                startActivityForResult(intent, 0);
            }
        });
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("mac", getIntent().getStringExtra("mac"));
        mPresenter.getFangquList(map);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        refreshLayout.setRefreshing(true);
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("mac", getIntent().getStringExtra("mac"));
        mPresenter.getFangquList(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFangquListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .fangquListModule(new FangquListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FangquListContract.FangquListContractPresenter presenter) {
        mPresenter = (FangquListPresenter) presenter;
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
    public void setAdapter(FangquEntity fangquEntity) {
        refreshLayout.setRefreshing(false);
        fangQuListAdapter.setNewData(fangquEntity.getData());
    }

    private void showModifyView(int i) {
//        View view = LayoutInflater.from(this).inflate(R.layout.pop_add_modify_fangqu, null);
//        AutoLinearLayout ll = (AutoLinearLayout) view.findViewById(R.id.content);
//        ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                return;
//            }
//        });
//        PopWindowUtil.showPopWindow(this, recycerView, view, ll);
    }

}