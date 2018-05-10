package com.yxld.yxchuangxin.ui.activity.wuye;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.yanzhenjie.permission.PermissionListener;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.PermissionUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.AppYezhuFangwu;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerLaiFangComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.LaiFangContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.LaiFangModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.LaiFangPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/06
 */

public class LaiFangFragment extends BaseFragment implements LaiFangContract.View {

    @Inject
    LaiFangPresenter mPresenter;
    @BindView(R.id.tv_new_erweima)
    TextView tvNewErweima;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    private AppYezhuFangwu house = new AppYezhuFangwu();
    public String address;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laifang, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        StringUitl.setInputName(etName);
        initDataFromLocal();
        return view;
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerLaiFangComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .laiFangModule(new LaiFangModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(LaiFangContract.LaiFangContractPresenter presenter) {
        mPresenter = (LaiFangPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {
        List<AppYezhuFangwu> list = Contains.appYezhuFangwus;
        if (list != null && list.size() != 0) {
            house = list.get(Contains.curFangwu);
            address = house.getXiangmuLoupan() + "" + house.getFwLoudong() + "栋" + house.getFwDanyuan() + "单元" + house.getFwFanghao();
            tvAddress.setText(address);
        }
    }

    @Override
    public void onDestroyView() {
        mPresenter.unsubscribe();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    @OnClick({R.id.tv_new_erweima, R.id.contact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_new_erweima:
                if (StringUitl.isNotEmpty(getActivity(), etName.getText().toString(), "请输入成员姓名")) {
                    if (StringUitl.isNotEmpty(getActivity(), etNumber.getText().toString(), "请输入成员电话")) {
                        if (!StringUitl.isMobileNum(etNumber.getText().toString())) {
                            ToastUtil.show(getActivity(), "请输入正确手机号码");
                            return;
                        }
                        //第一次进入弹出加载
                        if (house != null && Contains.user != null && Contains.user.getYezhuShouji() != null
                                && house.getFwLoupanId() != 0 && house.getFwLoudong() != null
                                && house.getFwDanyuan() != null) {
                            String nameyz = "";
                            if (Contains.user.getYezhuName() == null || "".equals(Contains.user.getYezhuName())) {
                                nameyz = Contains.user.getYezhuShouji();
                            } else {
                                nameyz = Contains.user.getYezhuName();
                            }
                            Map<String, String> maps = new HashMap<String, String>();
                            maps.put("bName", etName.getText().toString());
                            maps.put("bPhone", etNumber.getText().toString());
                            maps.put("bRole", "3");
                            maps.put("name", nameyz);
                            maps.put("phone", Contains.user.getYezhuShouji());
                            maps.put("role", String.valueOf(house.getFwyzType()));
                            maps.put("building", String.valueOf(house.getFwLoupanId()));
                            maps.put("buildingHouse", house.getFwLoudong());
                            maps.put("buildingUnit", house.getFwDanyuan());
                            maps.put("uuid", Contains.uuid);
                            house.toString();
                            mPresenter.getQrCode(maps);
                            progressDialog.show();
                        }
                    }
                }
                break;
            case R.id.contact:
                PermissionUtil.permission(getContext(), 0x000505, new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        if (requestCode == 0x000505) {
                            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                            startActivityForResult(intent, 1);
                        }
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        ToastUtil.show(getContext(), "请提供读取联系人的权限");
                    }
                }, Manifest.permission.READ_CONTACTS);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setOnResult(String name, String number) {
        etName.setText(name);
        etNumber.setText(number);
    }

    @Override
    public void onQrBack() {
        progressDialog.hide();
    }

}