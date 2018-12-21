package com.yxld.yxchuangxin.ui.activity.wuye;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yhy.gvp.utils.ToastUtils;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.TimeUtil;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.entity.DoorInfo;
import com.yxld.yxchuangxin.entity.EvenBusMsg;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerMenJinListComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenJinListContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MenJinListModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MenJinListPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.MenJinListAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2018/05/26 13:35:55
 */

public class MenJinListFragment extends BaseFragment implements MenJinListContract.View {

    @Inject
    MenJinListPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_empty_text)
    TextView mTvEmptyText;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout mSmartRefresh;
    TextView mTextView;
    LinearLayout mEmptyLayout;
    private List<DoorInfo.DoorInfoBean> mList;
    private MenJinListAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menjin_list, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        mEmptyLayout = (LinearLayout) view.findViewById(R.id.empty_layout);
        mTextView = (TextView) view.findViewById(R.id.tv_empty_text);
        initLoacView();
        initData();
        return view;
    }

    private void initData() {
        mPresenter.getDoorList();
    }

    private void initLoacView() {
        mList = new ArrayList<>();
        mAdapter = new MenJinListAdapter(mList);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (view.getId() == R.id.tv_open) {
                    if (TimeUtil.isFastClick()) {
                        EvenBusMsg evenBusMsg = new EvenBusMsg("HomeService", mList.get(i).getMac(), "opendoor");
                        EventBus.getDefault().post(evenBusMsg);
                    } else {
                        ToastUtil.showShort("点击频率过快");
                    }
                } else if (view.getId() == R.id.img_share) {
                    Intent intent = new Intent(getActivity(), MenJinShareMemberActivity.class);
                    intent.putExtra("gsid", mList.get(i).getGongsi_id()+"");
                    intent.putExtra("xmid", mList.get(i).getXiangmu_id()+"");
                    intent.putExtra("dyid", mList.get(i).getDanyuan_id());
                    intent.putExtra("ldid", mList.get(i).getLoudong_id());
                    startActivity(intent);
                }
            }
        });
        mTextView.setText("暂无数据");
        mSmartRefresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        mSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
                initData();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    protected void setupFragmentComponent() {
        DaggerMenJinListComponent.builder().appComponent(((AppConfig) getActivity().getApplication())
                .getApplicationComponent()).menJinListModule(new MenJinListModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(MenJinListContract.MenJinListContractPresenter presenter) {
        mPresenter = (MenJinListPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

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
    public void setDoorList(DoorInfo baseEntity) {
        if ("0".equals(baseEntity.getCode())) {
            if (baseEntity.getData().size() > 0) {
                mList.clear();
                mList.addAll(baseEntity.getData());
                mAdapter.setNewData(mList);
                mEmptyLayout.setVisibility(View.GONE);
            }
        } else {
            onError(baseEntity.getMsg());
            mEmptyLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}