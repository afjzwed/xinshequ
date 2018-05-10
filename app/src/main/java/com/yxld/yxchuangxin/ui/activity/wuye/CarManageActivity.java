package com.yxld.yxchuangxin.ui.activity.wuye;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CarList;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerCarManageComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.CarManageContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.CarManageModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.CarManagePresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.CarManageAdapter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/08
 */

public class CarManageActivity extends BaseActivity implements CarManageContract.View {

    @Inject
    CarManagePresenter mPresenter;

    @Inject
    CarManageAdapter carManageAdapter;
    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private View notDataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_car_manage);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        notDataView = getLayoutInflater().inflate(R.layout.layout_empty_data, (ViewGroup) recycerView.getParent(), false);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Map<String, String> map = new HashMap<>();
                map.put("uuid", Contains.uuid + "");
                KLog.i(map);
                mPresenter.getCarList(map);
            }
        });
    }

    @Override
    protected void initData() {
        carManageAdapter.setEmptyView(notDataView);
        recycerView.setAdapter(carManageAdapter);
        carManageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (view.getId()) {
                    case R.id.bt_suoding:
                        //锁定
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("lpid", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoupanId() + "");
                        map.put("parkNo", carManageAdapter.getData().get(i).getClparkNo());
                        map.put("mediaNo", carManageAdapter.getData().get(i).getClmediaNo());
                        //0为已锁定， 1 为未锁定
                        if (carManageAdapter.getData().get(i).getType() == 1) {
                            //需要锁定
                            map.put("isLock", "0");
                            map.put("serialNo", "");
                        } else {
                            //需要解锁
                            map.put("isLock", "1");
                            map.put("serialNo", carManageAdapter.getData().get(i).getSerialNo() + "");
                        }
                        KLog.i(map);
                        mPresenter.lockControl(map);
                        break;
                    case R.id.bt_chongzhi:
                        Intent intent = new Intent(CarManageActivity.this, CarAddMoneyActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("car", carManageAdapter.getData().get(i));
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 0);
                        break;
                }
            }
        });
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid + "");
        KLog.i(map);
        mPresenter.getCarList(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerCarManageComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .carManageModule(new CarManageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(CarManageContract.CarManageContractPresenter presenter) {
        mPresenter = (CarManagePresenter) presenter;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_add_car, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.help:
//                startActivity(MenjinHelpActivity.class);
//                break;
//            case android.R.id.home:
//                finish();
//                System.gc();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

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
    public void setCarList(CarList carList) {
        if (carList.isSuccess()){
        carManageAdapter.setNewData(carList.getData());}
        else {
            ToastUtil.show(this,carList.getMsg());
        }
    }

    @Override
    public void onLocakBack() {
        initData();
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        initData();
        super.onActivityResult(requestCode, resultCode, data);
    }
}