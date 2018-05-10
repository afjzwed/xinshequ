package com.yxld.yxchuangxin.ui.activity.goods;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyMallAdd;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerAddressManageComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.AddressManageContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.AddressManageModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.AddressManagePresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.ReceiveAddressAdapter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/22 17:20:34
 */

public class AddressManageActivity extends BaseActivity implements AddressManageContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    public static final int CODE_REQUEST_ADD = 0x000300;
    public static final int CODE_REQUEST_EDIT = 0x000301;
    public static final int IN_TYPE_ODRDER_CONFIRM = 0x0003002;
    public static final int IN_TYPE_MINE = 0x000313;

    public static final String KEY_IN_TYPE = "key_in_type";
    public static final String KEY_ENTITY = "key_entity";
    public static final String KEY_POSITION = "key_position";
    @Inject
    AddressManagePresenter mPresenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.add_address)
    ImageView addAddress;
    @BindView(R.id.rl_add)
    AutoLinearLayout rlAdd;
    @BindView(R.id.swipeLayouts)
    SwipeRefreshLayout swipeLayouts;

    private List<CxwyMallAdd> mAddressEntities;
    private ReceiveAddressAdapter mAddressAdapter;
    private int mInType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_address_manage);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_ff9934));
        mInType = getIntent().getIntExtra(KEY_IN_TYPE, IN_TYPE_MINE);
    }


    @Override
    protected void initData() {
        mAddressEntities = new ArrayList<>();
        mAddressAdapter = new ReceiveAddressAdapter(mAddressEntities);
        recyclerView.setAdapter(mAddressAdapter);
        UIUtils.configSwipeRefreshLayoutColors(swipeLayouts);

        mAddressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CxwyMallAdd entity = mAddressEntities.get(i);
                //修改默认地址
                if (view.getTag().equals("Default")) {
                    if (entity.getAddStatus() == 0) {
                        return;
                    }
                    CxwyMallAdd defaultAddress = getCurrentDefaultAddress();
                    if (defaultAddress != null) {
                        //将现在的默认地址改为非默认地址
                        mPresenter.updateAddress(defaultAddress, 1, i);
                    }
                    //将选中的非默认地址改为默认地址
                    mPresenter.updateAddress(entity, 0, i);
                //删除
                } else if ("Delete".equals(view.getTag())) {
                    deleteAddress(entity.getAddId() + "", i);
                //编辑
                } else if ("Edit".equals(view.getTag())) {
                    addOrEditAddress(CODE_REQUEST_EDIT, entity, i);
                } else if ("Root".equals(view.getTag()) && mInType == IN_TYPE_ODRDER_CONFIRM) {
                    Intent intent = new Intent();
                    KLog.i("更改当前的收货地址");
                    Contains.confirmOrderAddress = entity;
                    setResult(ConfirmOrderActivity.CODE_REQUEST_ADDRESS, intent);
                    finish();
                }
            }
        });

        mAddressAdapter.setOnLoadMoreListener(this, recyclerView);
        swipeLayouts.setOnRefreshListener(this);
        swipeLayouts.post(new Runnable() {
            @Override
            public void run() {
                mPresenter.getAddressDataFromServer();
            }
        });

    }

    @Override
    protected void setupActivityComponent() {
        DaggerAddressManageComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .addressManageModule(new AddressManageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(AddressManageContract.AddressManageContractPresenter presenter) {
        mPresenter = (AddressManagePresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
//        progressDialog.show();
        swipeLayouts.setRefreshing(true);
    }

    @Override
    public void closeProgressDialog() {
//        progressDialog.hide();
        swipeLayouts.setRefreshing(false);
    }

    @Override
    public void onAddressDataBacked(CxwyMallAdd addresses) {
        if (addresses.status == STATUS_CODE_OK) {
            mAddressEntities.clear();
            mAddressEntities.addAll(addresses.getAddList());
            mAddressAdapter.notifyDataSetChanged();
        } else {
            onError(addresses.MSG, addresses.status);
        }
    }


    @Override
    public void onAddressChangedSucceed(BaseEntity entity, int status, int position) {
        if (entity.status == STATUS_CODE_OK) {
            if (status == 1) {
                KLog.i("geek", "change address 2 not default");
            } else if (status == 0) {
                //修改本地的默认收货地址
                KLog.i("geek", "change address 2 default succeed");
                mAddressAdapter.OnOtherDefaultClicked(position);
            } else {
                ToastUtil.show(AddressManageActivity.this, "修改失败");
            }
        } else {
            onError(entity.getMSG(), entity.getStatus());
        }

    }

    @Override
    public void onDeleteAddressSucceed(BaseEntity entity, int position) {
        if (entity.status == STATUS_CODE_OK) {
            CxwyMallAdd mallAdd = mAddressEntities.get(position);
            if (mallAdd.getAddStatus() == 0) {
                Contains.defuleAddress = null;
            }
            if(Contains.confirmOrderAddress != null && mallAdd.equals(Contains.confirmOrderAddress)){
                Contains.confirmOrderAddress = null;
            }
            mAddressEntities.remove(position);
            mAddressAdapter.notifyDataSetChanged();
        } else {
            onError(entity.MSG, entity.status);
        }
    }

    @Override
    public void onLoadDataFailed() {
        ToastUtil.show(AddressManageActivity.this, getResources().getString(R.string.load_failed));
    }

    @OnClick(R.id.add_address)
    public void onViewClicked() {
        addOrEditAddress(CODE_REQUEST_ADD, null, -1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_REQUEST_ADD && resultCode == CODE_REQUEST_ADD) {
            mPresenter.getAddressDataFromServer();
        } else if (requestCode == CODE_REQUEST_EDIT && resultCode == CODE_REQUEST_EDIT) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                mPresenter.getAddressDataFromServer();
                return;
            }
            CxwyMallAdd add = (CxwyMallAdd) bundle.getSerializable(KEY_ENTITY);
            if (add == null) {
                mPresenter.getAddressDataFromServer();
                return;
            }
            //编辑的时候,如果是编辑默认地址,就修改本地的默认地址
//            if (add.getAddStatus() == 0) {
//                Contains.defuleAddress = add;
//                Contains.confirmOrderAddress = Contains.defuleAddress;
//            }
            if (Contains.confirmOrderAddress != null && Contains.confirmOrderAddress.getAddId().equals(add.getAddId())) {
                KLog.i("需要改变收货地址");
                Contains.confirmOrderAddress = add;
            }
            int position = bundle.getInt(KEY_POSITION);
            CxwyMallAdd old = mAddressEntities.get(position);
            old.setAddName(add.getAddName());
            old.setAddTel(add.getAddTel());
            old.setAddAdd(add.getAddAdd());
            add = null;
            mAddressAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getAddressDataFromServer();
    }

    @Override
    public void onLoadMoreRequested() {
        if (mAddressEntities.size() > 0) {
            mAddressAdapter.loadMoreEnd(false);
        }
    }

    private void deleteAddress(String addressId, int pos) {

        AlertDialog.Builder builder = new AlertDialog.Builder(AddressManageActivity.this);
        builder.setTitle("确定删除该地址");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.deleteAddress(addressId, pos);
            }
        });
        builder.show();
    }

    private void addOrEditAddress(int requestCode, CxwyMallAdd entity, int pos) {
        Intent intent = new Intent(AddressManageActivity.this, AddAddressActivity.class);
        if (requestCode == CODE_REQUEST_ADD) {
            intent.putExtra(KEY_IN_TYPE, CODE_REQUEST_ADD);
            intent.putExtra(AddAddressActivity.KEY_TITLE,"新增地址");
        } else {
            intent.putExtra(KEY_IN_TYPE, CODE_REQUEST_EDIT);
            intent.putExtra(KEY_POSITION, pos);
            intent.putExtra(KEY_ENTITY, entity);
            intent.putExtra(AddAddressActivity.KEY_TITLE,"编辑地址");
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
    }

    public CxwyMallAdd getCurrentDefaultAddress() {
        for (CxwyMallAdd add : mAddressEntities) {
            if (add.getAddStatus() == 0) {
                return add;
            }
        }
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
        mPresenter = null;
        mAddressEntities.clear();
        mAddressEntities = null;
        mAddressAdapter = null;
    }
}