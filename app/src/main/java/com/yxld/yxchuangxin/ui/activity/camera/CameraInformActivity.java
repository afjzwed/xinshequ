package com.yxld.yxchuangxin.ui.activity.camera;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.camera.ShareFamily;
import com.yxld.yxchuangxin.entity.camera.Shared;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerCameraInformComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.CameraInformContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.CameraInformModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.CameraInformPresenter;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.yxld.yxchuangxin.view.datepicker.NumericWheelAdapter;
import com.yxld.yxchuangxin.view.datepicker.WheelView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description 通知对象的设定
 * @date 2017/10/18 09:18:09
 */

public class CameraInformActivity extends BaseActivity implements CameraInformContract.View {

    @Inject
    CameraInformPresenter mPresenter;
    @BindView(R.id.tv_object1)
    TextView tvObject1;
    @BindView(R.id.check_fangquleixing)
    AutoLinearLayout checkFangquleixing;
    @BindView(R.id.tv_object2)
    TextView tvObject2;
    @BindView(R.id.tv_object3)
    TextView tvObject3;
    @BindView(R.id.tv_object4)
    TextView tvObject4;
    @BindView(R.id.confirm)
    TextView confirm;

    private ArrayList<String> phoneList = new ArrayList<>();   // 显示的
    private ArrayList<String> personList;                     //所有的家属
    private ArrayList<String> allIdList = new ArrayList<>();   //所有的id的集合
    private ArrayList<String> idList = new ArrayList<>();                          //记录业主id的集合
    private WheelView wheelView;
    private NumericWheelAdapter xiangmuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_camera_inform);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<>();
        //uuid,loudong,danyuan,fanghao
        map.put("uuid", Contains.uuid);
        map.put("loudong", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoudong());
        map.put("danyuan", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwDanyuan());
        map.put("fanghao", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwFanghao());
        mPresenter.getJiashu(map, getIntent().getStringExtra("deviceId"));

        //uuid,sb_ipc_id
//        Map<String, String> map2 = new HashMap<>();
//        map2.put("uuid", Contains.uuid);
//        map2.put("sb_ipc_id", getIntent().getStringExtra("deviceId"));
//        mPresenter.getShared(map2);
        //给四个textview设定初始值
        phoneList.add("                       ");
        phoneList.add("                       ");
        phoneList.add("                       ");
        phoneList.add("                       ");
        //四个id设定初始值
        idList.add("");
        idList.add("");
        idList.add("");
        idList.add("");
    }

    @Override
    protected void setupActivityComponent() {
        DaggerCameraInformComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .cameraInformModule(new CameraInformModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(CameraInformContract.CameraInformContractPresenter presenter) {
        mPresenter = (CameraInformPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    /**
     * 所有的可以添加的家属的返回
     * @param shareFamily
     */
    @Override
    public void onAllJiashuBack(ShareFamily shareFamily) {
        personList = new ArrayList<>();
        for (int i = 0; i < shareFamily.getData().size(); i++) {
            if (shareFamily.getData().get(i).getYezhuId() == Contains.appYezhuFangwus.get(Contains.curFangwu).getYezhuId()) {
                continue;
            }
            personList.add(shareFamily.getData().get(i).getYezhuName() + "    " + shareFamily.getData().get(i).getYezhuShouji());
            allIdList.add(shareFamily.getData().get(i).getYezhuId() + "");
        }
        personList.add("                       ");
        allIdList.add("");
        xiangmuAdapter = new NumericWheelAdapter(this, 0, personList.size() - 1, "", personList);
        xiangmuAdapter.setTextSize(15);
    }

    @Override
    public void onSharedBack(Shared shared) {

    }

    /**
     * 添加家属的返回
     * @param entity
     */
    @Override
    public void onAddBack(BaseEntity entity) {
        if (entity.getMSG().equals("设置成功")) {
            ToastUtil.displayShortToast("修改成功");
            finish();
        } else {
            ToastUtil.displayShortToast("修改失败");
        }
    }

    /**
     *添加家属
     */
    private void saveJiashu() {
        //uuid, yezhuIds, sb_ipc_id
        Map<String, String> map = new HashMap<>();
        map.put("sb_ipc_id", getIntent().getStringExtra("deviceId"));
        map.put("uuid", Contains.uuid);
        String ids = "";
        for (int i = 0; i < idList.size(); i++) {
            if (idList.get(i).trim().equals("")) {
//                ids += idList.get(i).trim() + ",";
            } else {
                ids += (idList.get(i) + ",");
            }
        }
        if (ids.equals("")) {
            map.put("yezhuIds","");
        } else {
            map.put("yezhuIds",ids.substring(0, ids.length() - 1));
        }
        KLog.i(map);
        mPresenter.addShare(map);
    }

    /**
     * 设置已经添加的家属，
     * 把对应的家属id替换到idList里边去
     * @param list
     * @param idList
     */
    @Override
    public void setYijiaJiashu(ArrayList<String> list, ArrayList<String> idList) {
        for (int i = 0; i <list.size(); i++) {
            switch (i) {
                case 0:
                    tvObject1.setText(list.get(0));
                    phoneList.set(0, list.get(0));
                    this.idList.set(0, idList.get(0));
                    break;
                case 1:
                    tvObject2.setText(list.get(1));
                    phoneList.set(1, list.get(1));
                    this.idList.set(1, idList.get(1));
                    break;
                case 2:
                    tvObject3.setText(list.get(2));
                    phoneList.set(2, list.get(2));
                    this.idList.set(2, idList.get(2));
                    break;
                case 3:
                    tvObject4.setText(list.get(3));
                    phoneList.set(3, list.get(3));
                    this.idList.set(3, idList.get(3));
                    break;
            }
        }
    }

    @OnClick({R.id.tv_object1, R.id.tv_object2, R.id.tv_object3, R.id.tv_object4, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_object1:
                showWheelView(1);
                break;
            case R.id.tv_object2:
                showWheelView(2);
                break;
            case R.id.tv_object3:
                showWheelView(3);
                break;
            case R.id.tv_object4:
                showWheelView(4);
                break;
            case R.id.confirm:
                saveJiashu();
                break;
        }
    }

    /**
     * 显示选择可以添加的家属的选择框
     * @param flag
     */
    private void showWheelView(int flag) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(tvObject4.getWindowToken(), 0);
        View view = LayoutInflater.from(this).inflate(R.layout.picker_xiangmu, null);
        AutoLinearLayout ll_content = (AutoLinearLayout) view.findViewById(R.id.ll_content);
        ll_content.setAnimation(AnimationUtils.loadAnimation(this, R.anim.pop_manage_product_in));
        TextView submit = (TextView) ll_content.findViewById(R.id.submit);
        TextView tv_title = (TextView) ll_content.findViewById(R.id.tv_title);
        tv_title.setText("请选择通知对象");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow.onBackPressed();
                if (flag == 1) {
                    if (!personList.get(wheelView.getCurrentItem()).equals("                       ") && phoneList.contains(personList.get(wheelView.getCurrentItem()))) {
                        ToastUtil.displayShortToast("不能添加相同的家属");
                        return;
                    }
                    phoneList.set(0, personList.get(wheelView.getCurrentItem()));
                    idList.set(0, allIdList.get(wheelView.getCurrentItem()));
                    tvObject1.setText(personList.get(wheelView.getCurrentItem()));
                }
                if (flag == 2) {
                    if (!personList.get(wheelView.getCurrentItem()).equals("                       ") && phoneList.contains(personList.get(wheelView.getCurrentItem()))) {
                        ToastUtil.displayShortToast("不能添加相同的家属");
                        return;
                    }
                    idList.set(1, allIdList.get(wheelView.getCurrentItem()));
                    phoneList.set(1, personList.get(wheelView.getCurrentItem()));
                    tvObject2.setText(personList.get(wheelView.getCurrentItem()));
                }
                if (flag == 3) {
                    if (!personList.get(wheelView.getCurrentItem()).equals("                       ") && phoneList.contains(personList.get(wheelView.getCurrentItem()))) {
                        ToastUtil.displayShortToast("不能添加相同的家属");
                        return;
                    }
                    idList.set(2, allIdList.get(wheelView.getCurrentItem()));
                    phoneList.set(2, personList.get(wheelView.getCurrentItem()));
                    tvObject3.setText(personList.get(wheelView.getCurrentItem()));
                }
                if (flag == 4) {
                    if (!personList.get(wheelView.getCurrentItem()).equals("                       ") && phoneList.contains(personList.get(wheelView.getCurrentItem()))) {
                        ToastUtil.displayShortToast("不能添加相同的家属");
                        return;
                    }
                    idList.set(3, allIdList.get(wheelView.getCurrentItem()));
                    phoneList.set(3, personList.get(wheelView.getCurrentItem()));
                    tvObject4.setText(personList.get(wheelView.getCurrentItem()));
                }
            }
        });
        wheelView = (WheelView) ll_content.findViewById(R.id.fangqu);
        wheelView.setViewAdapter(xiangmuAdapter);
        new CustomPopWindow.PopupWindowBuilder(this)
                .setClippingEnable(false)
                .setFocusable(true)
                .setView(view)
                .setContenView(ll_content)
                .size(UIUtils.getDisplayWidth(this), UIUtils.getDisplayHeigh(this))
                .create()
                .showAtLocation(tvObject4, Gravity.CENTER, 0, 0);
    }
}