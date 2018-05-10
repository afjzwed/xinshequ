package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.RoomRent;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerRoomRentComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.RoomRentContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.RoomRentModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.RoomRentPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.RoomRentAdapter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description 房屋出租
 * @date 2017/06/16
 */

public class RoomRentActivity extends BaseActivity implements RoomRentContract.View {

    @Inject
    RoomRentPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Inject
    RoomRentAdapter roomRentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_room_rent);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        mPresenter.getRentList();
        recyclerView.setAdapter(roomRentAdapter);
        roomRentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("fangwuid", roomRentAdapter.getData().get(i).getFwId() + "");
                switch (view.getId()) {
                    case R.id.bt_cancal_rent:
                        //取消出租
                        map.put("zhuangtai", "0");
                        break;
                    case R.id.bt_rent_status:
                        if (roomRentAdapter.getData().get(i).getFwIsChuzu() == 0) {
                            //未出租
                            map.put("zhuangtai", "1");
                        } else {
                            //已出租
                            return;
                        }
                        break;
                }
                mPresenter.updateRentStatus(map);
            }
        });
    }

    @Override
    protected void setupActivityComponent() {
        DaggerRoomRentComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .roomRentModule(new RoomRentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RoomRentContract.RoomRentContractPresenter presenter) {
        mPresenter = (RoomRentPresenter) presenter;
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
    public void setRentList(RoomRent roomRent) {
        roomRentAdapter.setNewData(roomRent.getYezhu());
    }
}