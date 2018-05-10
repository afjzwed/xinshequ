package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
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
        if(Contains.appYezhuFangwus.get(Contains.curFangwu).getFwyzType() > 1) {
            ivAddMember.setVisibility(View.GONE);
        }
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
                ConfirmDialog.showDialog(LiveMemberActivity.this, "提示", "成员删除之后将无法恢复", new ConfirmDialog.OnConfirmListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onConfirm() {
                        mPresenter.deletLiveMember(position);
                    }
                });
            }
        });
    }

    @Override
    public void deletMember(int position) {
        if (position == -1) {
        } else {
            liveMemberAdapter.remove(position);
        }
    }

    @OnClick(R.id.iv_add_member)
    public void onViewClicked() {
        startActivity(AddLiveMemberActivity.class);
        overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }
    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}