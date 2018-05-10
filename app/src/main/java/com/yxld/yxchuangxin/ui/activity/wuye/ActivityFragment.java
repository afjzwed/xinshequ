package com.yxld.yxchuangxin.ui.activity.wuye;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyMessage;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerActivityComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.ActivityContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.ActivityModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.ActivityPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.ActivityAdapter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/14
 */

public class ActivityFragment extends BaseFragment implements ActivityContract.View {

    @Inject
    ActivityPresenter mPresenter;

    @Inject
    ActivityAdapter activityAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        activityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(getActivity(), MessageActivityDetailActivity.class);
                intent.putExtra("flag", "activity");
                Bundle bundle = new Bundle();
                bundle.putSerializable("entity", activityAdapter.getData().get(i));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(activityAdapter);
        Map<String, String> map = new HashMap<>();
        map.put("luopan", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoupanId() + "");
        mPresenter.getActivity(map);
        return view;
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerActivityComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(ActivityContract.ActivityContractPresenter presenter) {
        mPresenter = (ActivityPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {
    }

    @Override
    public void setActivity(CxwyMessage message) {
        activityAdapter.setNewData(message.getRows());
    }

    @Override
    public void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}