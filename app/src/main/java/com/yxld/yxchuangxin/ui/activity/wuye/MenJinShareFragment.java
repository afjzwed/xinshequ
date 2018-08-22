package com.yxld.yxchuangxin.ui.activity.wuye;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.socks.library.KLog;
import com.yanzhenjie.permission.PermissionListener;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.GsonHelper;
import com.yxld.yxchuangxin.Utils.PermissionUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.Utils.YouMengShareUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.AppYezhuFangwu;
import com.yxld.yxchuangxin.entity.BaseBack2;
import com.yxld.yxchuangxin.entity.DoorInfo;
import com.yxld.yxchuangxin.entity.ShareInfo;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerMenJinShareComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenJinShareContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.MenJinShareModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.MenJinSharePresenter;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.yxld.yxchuangxin.view.datepicker.NumericWheelAdapter;
import com.yxld.yxchuangxin.view.datepicker.WheelView;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2018/05/26 13:36:35
 */

public class MenJinShareFragment extends BaseFragment implements MenJinShareContract.View {

    @Inject
    MenJinSharePresenter mPresenter;
    @BindView(R.id.tv_new_erweima)
    TextView tvNewErweima;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_door)
    TextView mTvDoor;
    @BindView(R.id.tv_car_number_take)
    TextView mTvCarNumber;

    private AppYezhuFangwu house = new AppYezhuFangwu();
    public String address;
    private String mac;

    private WheelView wheelView;
    private NumericWheelAdapter xiangmuAdapter;
    private ArrayList<String> xiangmuList;
    private List<DoorInfo.DoorInfoBean> mDoorInfoBeen;
    ShareInfo shareInfo = new ShareInfo();
    private String tempPassword;//要分享的零时密码
    private String shareCon = "";
    private OptionsPickerView mOptionsPickerView;
    private List<String> options1Items = new ArrayList<>();
    private ArrayList<String> options2Items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menjin_share, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        StringUitl.setInputName(etName);
        initCityData();
        initShare();
        initDataFromLocal();
        return view;
    }

    private void initCityData() {
        String wuyeString;
        AssetManager assetManager = AppConfig.getInstance().getAssets();
        InputStream is = null;
        try {
            is = assetManager.open("carNumberCities.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer stringBuffer = new StringBuffer();
            wuyeString = null;
            while ((wuyeString = br.readLine()) != null) {
                stringBuffer.append(wuyeString);
            }
            wuyeString = stringBuffer.toString();
            KLog.e("-----------------" + wuyeString);
            options1Items = GsonHelper.fromJsonArray(wuyeString, String.class);
            for (int i = 'A'; i <= 'Z'; i++) {
                options2Items.add((char) i + "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //    @Override
//    public void onPause() {
//        super.onPause();
//    KLog.e("onPause-----------------");
//        ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow
// (getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
//        inputmanger.hideSoftInputFromWindow(etNumber.getWindowToken(), 0);
//    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        KLog.e("setUserVisibleHint-----------------" + isVisibleToUser);
        if (isVisibleToUser) {
            // 相当于onResume()方法
        } else {
            //切换fragment 隐藏软键盘
            if (etName!=null) {
                InputMethodManager imm = (InputMethodManager) etName.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(etName.getApplicationWindowToken(), 0);
                }
            }

        }
    }

    private void initShare() {
        shareInfo.setTitle("门禁临时密码");
        shareInfo.setBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.login_icon_bg));
        shareInfo.setImgUrl("http://www.iot.xin");
        shareInfo.setQQImgUrl("http://www.iot.xin");
    }


    @OnClick({R.id.tv_new_erweima, R.id.contact, R.id.relativelayout4, R.id.tv_car_number_take})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_new_erweima:
                //分享按钮
                if (StringUitl.isNotEmpty1(getActivity(), etName.getText().toString(), "请输入分享人姓名")) {
                    if (StringUitl.isNotEmpty1(getActivity(), etNumber.getText().toString(), "请输入分享人电话")) {
                        if (!StringUitl.isMobileNum(etNumber.getText().toString())) {
//                            ToastUtil.show(getActivity(), "请输入正确的手机号码");
                            Toast toast = Toast.makeText(getActivity(), "请输入正确的手机号码", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            return;
                        }
                        if (StringUitl.isNotEmpty1(getActivity(), mac, "请选择分享门禁")) {

                            Map<String, String> map = new HashMap<>();
                            map.put("uuid", Contains.uuid);
                            map.put("chepai", "1212");
                            map.put("mac", mac);
                            map.put("phone", etNumber.getText().toString());
                            map.put("openname", etName.getText().toString());
                            mPresenter.getDoorMima(map);
                        }
                    }
                }
                break;
            //选择门禁列表按钮
            case R.id.relativelayout4:
                mPresenter.getDoorList();
                break;
            //imag获取电话铺
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
            //获取车牌
            case R.id.tv_car_number_take:
                showCarNumber();
            default:
                break;
        }
    }

    private void showCarNumber() {
        mOptionsPickerView = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvCarNumber.setText(options1Items.get(options1) + options2Items.get(options2));
            }
        })
                // .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
//                .isCenterLabel(false)//是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setTitleText("车牌号")
                .build();
        mOptionsPickerView.setNPicker(options1Items, options2Items, null);
        mOptionsPickerView.show();


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
    public void setDoorMima(BaseBack2 baseEntity) {
        if ("0".equals(baseEntity.getCode())) {
            if (StringUitl.isNoEmpty(baseEntity.getData())) {
                YouMengShareUtil mengShareUtil = new YouMengShareUtil(getActivity());
                shareInfo.setShareCon(shareCon + baseEntity.getData());
                mengShareUtil.addCustomPlatforms(shareInfo);
            }
        } else {
            onError(baseEntity.getMsg());
        }
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
    protected void initDataFromLocal() {

        xiangmuList = new ArrayList<>();
        mDoorInfoBeen = new ArrayList<>();
        List<AppYezhuFangwu> list = Contains.appYezhuFangwus;
        if (list != null && list.size() != 0) {
            house = list.get(Contains.curFangwu);
//            address = house.getXiangmuLoupan() + "" + house.getFwLoudong() + "栋" + house.getFwDanyuan() + "单元" +
//                    house.getFwFanghao();
            address = house.getXiangmuLoupan();//8.22改为小区名，不拼接
            tvAddress.setText(address);
        }
    }

    private void showWheelView(View showView, final int flag) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(showView.getWindowToken(), 0);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.picker_xiangmu, null);
        AutoLinearLayout ll_content = (AutoLinearLayout) view.findViewById(R.id.ll_content);
        ll_content.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.pop_manage_product_in));
        TextView submit = (TextView) ll_content.findViewById(R.id.submit);
        TextView tv_title = (TextView) ll_content.findViewById(R.id.tv_title);
        wheelView = (WheelView) ll_content.findViewById(R.id.fangqu);
        wheelView.setViewAdapter(xiangmuAdapter);
        switch (flag) {
            case 0:
                tv_title.setText("请选择门禁");
                break;
            default:
                break;
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow.onBackPressed();
                onWheelViewOnconfirm(flag);
            }
        });

        new CustomPopWindow.PopupWindowBuilder(getActivity()).setClippingEnable(false).setFocusable(true).setView
                (view).setContenView(ll_content).size(UIUtils.getDisplayWidth(getActivity()), UIUtils.getDisplayHeigh
                (getActivity())).create().showAtLocation(showView, Gravity.CENTER, 0, 0);
    }

    private void onWheelViewOnconfirm(int flag) {
        //         1为 选择项目， 2 为选择类型
        int position = wheelView.getCurrentItem();
        switch (flag) {
            case 0:
                mTvDoor.setText(mDoorInfoBeen.get(position).getName());
                mac = mDoorInfoBeen.get(position).getMac();
                shareCon = mDoorInfoBeen.get(position).getName() + ("0".equals(mDoorInfoBeen.get(position).getType())
                        ? "大门" : "单元门") + "临时密码";
                break;
            default:
                break;
        }
    }

    @Override
    public void setDoorList(DoorInfo baseEntity) {
        if ("0".equals(baseEntity.getCode())) {
            if (baseEntity.getData().size() > 0) {
                mDoorInfoBeen.clear();
                xiangmuList.clear();
                mDoorInfoBeen.addAll(baseEntity.getData());
                for (int i = 0; i < mDoorInfoBeen.size(); i++) {
                    xiangmuList.add(i, mDoorInfoBeen.get(i).getName());
                }
                xiangmuAdapter = new NumericWheelAdapter(getActivity(), 0, xiangmuList.size() - 1, "", xiangmuList);
            }
            showWheelView(tvAddress, 0);
        } else {
            onError(baseEntity.getMsg());
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    protected void setupFragmentComponent() {
        DaggerMenJinShareComponent.builder().appComponent(((AppConfig) getActivity().getApplication())
                .getApplicationComponent()).menJinShareModule(new MenJinShareModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(MenJinShareContract.MenJinShareContractPresenter presenter) {
        mPresenter = (MenJinSharePresenter) presenter;
    }

}