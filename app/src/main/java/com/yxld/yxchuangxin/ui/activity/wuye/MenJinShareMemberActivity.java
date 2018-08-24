package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.AppYezhuFangwu;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerMenJinShareMemberComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenJinShareMemberContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MenJinShareMemberModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MenJinShareMemberPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.MenJinShareAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2018/06/11 14:53:04
 */

public class MenJinShareMemberActivity extends BaseActivity implements MenJinShareMemberContract.View {

    @Inject
    MenJinShareMemberPresenter mPresenter;
    @Inject
    MenJinShareAdapter liveMemberAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout mSmartRefresh;
    TextView mTextView;
    LinearLayout mEmptyLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_menjin_share);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mEmptyLayout = (LinearLayout) findViewById(R.id.empty_layout);
        mTextView = (TextView) findViewById(R.id.tv_empty_text);
        mTextView.setText("暂无数据");
        mRecyclerView.setAdapter(liveMemberAdapter);
        mSmartRefresh.setRefreshHeader(new ClassicsHeader(this));
        mSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
                initData();
            }
        });
    }

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("fwyzFw", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwId() + "");
        map.put("fwyzId", Contains.appYezhuFangwus.get(Contains.curFangwu).getYezhuId() + "");
        map.put("uuid", Contains.uuid + "");
        mPresenter.getAllLiveMember(map);




    }
    @Override
    public void getMembered() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("gongsiId", getIntent().getStringExtra("gsid"));
        map1.put("xiangmuId", getIntent().getStringExtra("xmid"));
        map1.put("loudongId", getIntent().getStringExtra("ldid")==null?"0":getIntent().getStringExtra("ldid"));
        map1.put("danyuanId", getIntent().getStringExtra("dyid")==null?"0":getIntent().getStringExtra("dyid"));
        map1.put("fanghao", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwFanghao() + "");
        mPresenter.getDoorShareMember(map1);
    }
    @Override
    protected void setupActivityComponent() {
        DaggerMenJinShareMemberComponent.builder().appComponent(((AppConfig) getApplication())
                .getApplicationComponent()).menJinShareMemberModule(new MenJinShareMemberModule(this)).build().inject
                (this);
    }

    @Override
    public void setPresenter(MenJinShareMemberContract.MenJinShareMemberContractPresenter presenter) {
        mPresenter = (MenJinShareMemberPresenter) presenter;
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
    public void setMember(List<AppYezhuFangwu> data) {
        if (data == null || data.size() == 0) {
            mEmptyLayout.setVisibility(View.VISIBLE);
            return;
        }
        mEmptyLayout.setVisibility(View.GONE);
        liveMemberAdapter.setNewData(data);
    }



    @OnClick(R.id.bt_commit)
    public void onViewClicked() {
        if (mEmptyLayout.getVisibility()==View.VISIBLE){
            return;
        }
        Map<String, String> map2 = new HashMap<>();
        map2.put("gongsiId", getIntent().getStringExtra("gsid"));
        map2.put("xiangmuId", getIntent().getStringExtra("xmid"));
        map2.put("loudongId", getIntent().getStringExtra("ldid")==null?"0":getIntent().getStringExtra("ldid"));
        map2.put("danyuanId", getIntent().getStringExtra("dyid")==null?"0":getIntent().getStringExtra("dyid"));
        String fwFanghao = Contains.appYezhuFangwus.get(Contains.curFangwu).getFwFanghao();
        String newfwFanghao = fwFanghao.replaceFirst("^0*", "");
        map2.put("fanghao", newfwFanghao);
        int j=0;
        for (int i = 0; i < liveMemberAdapter.getData().size(); i++) {
            if (liveMemberAdapter.getData().get(i).isMenjinSave()) {
                map2.put("yezhuIds[" + j + "]", liveMemberAdapter.getData().get(i).getYezhuId() + "");
                map2.put("dianhuas[" + j + "]", liveMemberAdapter.getData().get(i).getFwLoudong());
                j++;
            }
        }
        mPresenter.saveDoorShareMember(map2);
    }
    @Override
    public void saveSuccess(BaseEntity baseEntity) {
        if (baseEntity.status==0){
            ToastUtil.showShort("设置呼叫成功");
            finish();
        }else {
            onError(baseEntity.getMsg(),baseEntity.status);
        }
    }


}