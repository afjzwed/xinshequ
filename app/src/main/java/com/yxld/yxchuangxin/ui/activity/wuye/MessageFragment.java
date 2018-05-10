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
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerMessageComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MessageContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MessageModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MessagePresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.MessageAdapter;

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

public class MessageFragment extends BaseFragment implements MessageContract.View {

    @Inject
    MessagePresenter mPresenter;

    @Inject
    MessageAdapter messageAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        messageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(getActivity(), MessageActivityDetailActivity.class);
                intent.putExtra("flag", "message");
                Bundle bundle = new Bundle();
                bundle.putSerializable("entity", messageAdapter.getData().get(i));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(messageAdapter);
        if (Contains.appYezhuFangwus.size() > 0) {
            Map<String, String> map = new HashMap<>();
            map.put("luopan", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoupanId() + "");
            mPresenter.getMessage(map);
        }
        return view;
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerMessageComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .messageModule(new MessageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(MessageContract.MessageContractPresenter presenter) {
        mPresenter = (MessagePresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setMessage(CxwyMessage message) {
        messageAdapter.setNewData(message.getRows());
    }

    @Override
    public void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}