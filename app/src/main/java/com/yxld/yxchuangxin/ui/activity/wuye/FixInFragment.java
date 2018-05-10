package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyBaoxiu;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerFixInComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FixInContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.FixInModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.FixInPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.FixiListAdapter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/15
 */

public class FixInFragment extends BaseFragment implements FixInContract.View {

    @Inject
    FixInPresenter mPresenter;
    @Inject
    FixiListAdapter fixiListAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String repairId;
    private View notDataView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fixin, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        repairId = mBundle.getString("repairId");
        notDataView = getActivity().getLayoutInflater().inflate(R.layout.layout_empty_data, (ViewGroup) recyclerView.getParent(), false);
        fixiListAdapter.setEmptyView(notDataView);
        recyclerView.setAdapter(fixiListAdapter);
        Map<String, String> parm = new HashMap<String, String>();
        if (Contains.appYezhuFangwus != null && Contains.appYezhuFangwus.size() > 0) {
            parm.put("loupan", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoupanId() + "");
            parm.put("loudong", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoudong());
            parm.put("danyuan", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwDanyuan());
            parm.put("fanghao", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwFanghao());
            if (repairId == null) {
                repairId = "";
            }
            parm.put("status", repairId);
        }
        parm.put("uuid", Contains.uuid);
        if (repairId.equals("")) {
            mPresenter.getRepairAllList(parm);
        } else {
            mPresenter.getRepairOtherList(parm);
        }
        return view;
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerFixInComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .fixInModule(new FixInModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FixInContract.FixInContractPresenter presenter) {
        mPresenter = (FixInPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void onDestroyView() {
//        fixiListAdapter.removeEmptyView();
        mPresenter.unsubscribe();
        super.onDestroyView();
    }

    @Override
    public void setAdapter(CxwyBaoxiu baoxiu) {
        fixiListAdapter.setNewData(baoxiu.getRows());
    }

}