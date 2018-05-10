package com.yxld.yxchuangxin.ui.activity.wuye;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.Accredit;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerFangxingComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FangxingContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.FangxingModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.FangxingPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.FangxingAdapter;

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
 * @date 2017/06/13
 */

public class FangxingActivity extends BaseActivity implements FangxingContract.View {

    @Inject
    FangxingPresenter mPresenter;
    @Inject
    FangxingAdapter fangxingAdapter;
    @BindView(R.id.new_fangxing)
    Button newFangxing;
    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    View notDataView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_fangxing);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        notDataView = getLayoutInflater().inflate(R.layout.layout_empty_fangxing, (ViewGroup) recycerView.getParent(), false);
        if (isYezhu()) {
            TextView tv_content = (TextView) notDataView.findViewById(R.id.tv_content);
            tv_content.setText("您还没有要放行的物品哦");
        }
        fangxingAdapter.setEmptyView(notDataView);
        recycerView.setAdapter(fangxingAdapter);
        fangxingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(FangxingActivity.this, FangxingDetaillActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", fangxingAdapter.getData().get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("userid", Contains.user.getYezhuId() + "");
        map.put("fwid", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwId() + "");
        if (isYezhu()) {
            newFangxing.setVisibility(View.GONE);
            mPresenter.getAccreditListProprietor(map);
        } else {
            mPresenter.getAccreditListRent(map);
        }
    }

    private boolean isYezhu() {
        if (Contains.user == null || Contains.appYezhuFangwus.size() == 0 || Contains.appYezhuFangwus.get(0) == null || Contains.appYezhuFangwus.get(0).getFwLoupanId() == 0
                || "".equals(Contains.appYezhuFangwus.get(0).getFwLoupanId())) {
            Toast.makeText(this, "需要在后台去配置您的业主信息", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            //getFwyzType >1 就不是家人。权限低
            if (Contains.appYezhuFangwus.get(Contains.curFangwu).getFwyzType() > 1) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    protected void onRestart() {
        initData();
        super.onRestart();
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFangxingComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .fangxingModule(new FangxingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FangxingContract.FangxingContractPresenter presenter) {
        mPresenter = (FangxingPresenter) presenter;
    }

    @OnClick({R.id.new_fangxing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.new_fangxing:
                startActivity(AddfangxingActivity.class);
                break;
        }
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        refreshLayout.setRefreshing(false);
        progressDialog.hide();
    }

    @Override
    public void setAdapter(Accredit accredit) {
        refreshLayout.setRefreshing(false);
        fangxingAdapter.setNewData(accredit.getData());
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}