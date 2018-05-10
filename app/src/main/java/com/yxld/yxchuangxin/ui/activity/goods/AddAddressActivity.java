package com.yxld.yxchuangxin.ui.activity.goods;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.yanzhenjie.permission.PermissionListener;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.PermissionUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyMallAdd;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerAddAddressComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.AddAddressContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.AddAddressModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.AddAddressPresenter;
import com.yxld.yxchuangxin.view.AddMemberView;
import com.yxld.yxchuangxin.view.CustomPopWindow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/22 17:36:39
 */

public class AddAddressActivity extends BaseActivity implements AddAddressContract.View {
    public static final String KEY_TITLE = "key_title";
    private static final int CODE_REQUEST_CONTACT = 0x000400;
    private static final int CODE_PERMISSION_CONTACT = 0x000500;
    @Inject
    AddAddressPresenter mPresenter;
    @BindView(R.id.chengyuan_leixing)
    AddMemberView chengyuanXiaoQu;
    @BindView(R.id.cehngyuan_xingming)
    AddMemberView cehngyuanName;
    @BindView(R.id.chengyuan_haoma)
    AddMemberView chengyuanPhone;
    @BindView(R.id.cehngyaun_shenfenzheng)
    AddMemberView cehngyaunAddress;
    @BindView(R.id.save)
    TextView save;

    private int mInType;
    private CxwyMallAdd mAddressEntity;
    private int mEditAddressPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_ff9934));
        Bundle bundle = getIntent().getExtras();
        String title = "新增地址";
        if(bundle != null){
            title = bundle.getString(KEY_TITLE);
        }
        toolbar.setTitle(title);
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        mInType = bundle.getInt(AddressManageActivity.KEY_IN_TYPE);
        if (mInType == AddressManageActivity.CODE_REQUEST_EDIT) {
            mAddressEntity = (CxwyMallAdd) bundle.getSerializable(AddressManageActivity.KEY_ENTITY);
            mEditAddressPosition = bundle.getInt(AddressManageActivity.KEY_POSITION);
        }
        if (mAddressEntity != null) {
            cehngyuanName.setEdittext(mAddressEntity.getAddName());
            chengyuanPhone.setEdittext(mAddressEntity.getAddTel());
            cehngyaunAddress.setEdittext(mAddressEntity.getAddAdd());
        }

        chengyuanXiaoQu.setEdittext(Contains.curSelectXiaoQuName);
        chengyuanPhone.setRightImgOnClickListener(new AddMemberView.RightImgOnClickListener() {
            @Override
            public void OnRightClick() {
                PermissionUtil.permission(AddAddressActivity.this, CODE_PERMISSION_CONTACT, new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        Intent intent = new Intent(Intent.ACTION_PICK,
                                android.provider.ContactsContract.Contacts.CONTENT_URI);
                        startActivityForResult(intent, CODE_REQUEST_CONTACT);
                    }
                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        ToastUtil.show(AddAddressActivity.this,"请提供读取联系人的权限");
                    }
                }, Manifest.permission.READ_CONTACTS);
            }
        });
    }

    @Override
    protected void setupActivityComponent() {
        DaggerAddAddressComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .addAddressModule(new AddAddressModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(AddAddressContract.AddAddressContractPresenter presenter) {
        mPresenter = (AddAddressPresenter) presenter;
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
    public void onAddAddressSucceed(BaseEntity entity) {
        if (entity.status == STATUS_CODE_OK) {
            ToastUtil.show(AddAddressActivity.this, "保存成功");
            if (mInType == AddressManageActivity.CODE_REQUEST_ADD) {
                setResult(AddressManageActivity.CODE_REQUEST_ADD);
                finish();
            } else {
                mAddressEntity.setAddAdd(cehngyaunAddress.getEdittext());
                mAddressEntity.setAddName(cehngyuanName.getEdittext());
                mAddressEntity.setAddTel(chengyuanPhone.getEdittext());
                mAddressEntity.setAddVillage(chengyuanXiaoQu.getEdittext());
                Intent intent = new Intent();
                intent.putExtra(AddressManageActivity.KEY_POSITION, mEditAddressPosition);
                intent.putExtra(AddressManageActivity.KEY_ENTITY, mAddressEntity);
                setResult(AddressManageActivity.CODE_REQUEST_EDIT, intent);
                finish();
            }

        } else {
            onError(entity.MSG, entity.status);
        }
    }

    @Override
    public void onAddAddressFailed() {
        ToastUtil.show(AddAddressActivity.this, getResources().getString(R.string.load_failed));
    }

    @Override
    public void onBackPressed() {
        if (CustomPopWindow.onBackPressed()) {
            return;
        }
        finish();
        overridePendingTransition(0, R.anim.activity_translate_out_1);
    }

    @OnClick(R.id.save)
    public void onViewClicked() {
        if (hasEmpty()) {
            ToastUtil.show(AddAddressActivity.this, "还有未填写的选项");
            return;
        }
        if (!StringUitl.isTelNum(chengyuanPhone.getEdittext())) {
            ToastUtil.show(AddAddressActivity.this, "联系电话格式有误");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("add.addName", cehngyuanName.getEdittext());
        params.put("add.addTel", chengyuanPhone.getEdittext());
        params.put("add.addVillage", chengyuanXiaoQu.getEdittext());
        params.put("add.addAdd", cehngyaunAddress.getEdittext());
        params.put("uuid", Contains.uuid);

        if (mAddressEntity != null) {
            params.put("add.addStatus", mAddressEntity.getAddStatus() + "");
            params.put("add.addId", mAddressEntity.getAddId() + "");
            mPresenter.updateAddress(params);
        } else {
            params.put("add.addStatus", "1");
            mPresenter.addAddress(params);
        }


    }

    private boolean hasEmpty() {
        return StringUitl.hasEmptyItem(chengyuanXiaoQu.getEdittext(), chengyuanPhone.getEdittext(),
                cehngyuanName.getEdittext(), cehngyaunAddress.getEdittext());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_REQUEST_CONTACT && resultCode == RESULT_OK) {
            List<String> list = UIUtils.getPhoneAndName(data, AddAddressActivity.this);
            if(list.size()!=2){
                ToastUtil.show(AddAddressActivity.this,"获取联系人失败");
            }else {
                cehngyuanName.setEdittext(list.get(0));
                chengyuanPhone.setEdittext(list.get(1));
            }
        }
    }
}