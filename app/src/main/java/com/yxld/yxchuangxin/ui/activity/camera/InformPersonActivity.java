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
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.HostEntiti;
import com.yxld.yxchuangxin.entity.PaianYezhuJiashu;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerInformPersonComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.InformPersonContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.InformPersonModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.InformPersonPresenter;
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
 * @Description: $description
 * @date 2017/09/19 11:17:32
 */

public class InformPersonActivity extends BaseActivity implements InformPersonContract.View {

    @Inject
    InformPersonPresenter mPresenter;
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
    @BindView(R.id.cacanl)
    TextView cacanl;
    private WheelView wheelView;
    private ArrayList<String> personList;                     //所有的家属
    private NumericWheelAdapter xiangmuAdapter;
    private ArrayList<String> phoneList = new ArrayList<>();   // 显示的
    private HostEntiti.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_inform_person);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        dataBean = getIntent().getParcelableExtra("databean");
        phoneList.add("                       ");
        phoneList.add("                       ");
        phoneList.add("                       ");
        phoneList.add("                       ");
        Map<String, String> map2 = new HashMap<>();
        map2.put("uuid", Contains.uuid);
        map2.put("zhuji.zhujiXiangmuId", dataBean.getZhujiXiangmuId());
        map2.put("zhuji.zhujiLoudong", dataBean.getZhujiLoudong());
        map2.put("zhuji.zhujiDanyuan", dataBean.getZhujiDanyuan());
        map2.put("zhuji.zhujiFanghao", dataBean.getZhujiFanghao());
        mPresenter.findYezhuJiashu(map2, dataBean.getZhujiMac());
    }

    @Override
    protected void setupActivityComponent() {
        DaggerInformPersonComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .informPersonModule(new InformPersonModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(InformPersonContract.InformPersonContractPresenter presenter) {
        mPresenter = (InformPersonPresenter) presenter;
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
    public void setPerson(PaianYezhuJiashu person) {
        personList = new ArrayList<>();
        for (int i = 0; i < person.getData().size(); i++) {
            personList.add(person.getData().get(i).getYezhuName() + "    " + person.getData().get(i).getYezhuShouji());
        }
        personList.add("                       ");
        xiangmuAdapter = new NumericWheelAdapter(InformPersonActivity.this, 0, personList.size() - 1, "", personList);
        xiangmuAdapter.setTextSize(15);
    }

    @Override
    public void setYijiaJiashu(ArrayList<String> paianYijiaJiashu) {
        for (int i = 0; i <paianYijiaJiashu.size(); i++) {
            switch (i) {
                case 0:
                    tvObject1.setText(paianYijiaJiashu.get(0));
                    phoneList.set(0, paianYijiaJiashu.get(0));
                    break;
                case 1:
                    tvObject2.setText(paianYijiaJiashu.get(1));
                    phoneList.set(1, paianYijiaJiashu.get(1));
                    break;
                case 2:
                    tvObject3.setText(paianYijiaJiashu.get(2));
                    phoneList.set(2, paianYijiaJiashu.get(2));
                    break;
                case 3:
                    tvObject4.setText(paianYijiaJiashu.get(3));
                    phoneList.set(3, paianYijiaJiashu.get(3));
                    break;
            }
        }
    }

    @OnClick({R.id.tv_object1, R.id.tv_object2, R.id.tv_object3, R.id.tv_object4, R.id.confirm, R.id.cacanl})
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
            case R.id.cacanl:
                finish();
                break;
        }
    }

    private void saveJiashu() {
        Map<String, String> map = new HashMap<>();
        map.put("mac", dataBean.getZhujiMac());
        map.put("uuid", Contains.uuid);
        String phone = "";
        for (int i = 0; i < phoneList.size(); i++) {
            if (phoneList.get(i).trim().equals("")) {
                phone += phoneList.get(i).trim() + ",";
            } else {
                phone += (phoneList.get(i).substring(phoneList.get(i).length() - 11, phoneList.get(i).length()) + ",");
            }
        }

        map.put("shoujiHaoma",phone.substring(0, phone.length() - 1));
        KLog.i(map);
        mPresenter.saveNumber(map);
    }

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
                    tvObject1.setText(personList.get(wheelView.getCurrentItem()));
                }
                if (flag == 2) {
                    if (!personList.get(wheelView.getCurrentItem()).equals("                       ") && phoneList.contains(personList.get(wheelView.getCurrentItem()))) {
                        ToastUtil.displayShortToast("不能添加相同的家属");
                        return;
                    }
                    phoneList.set(1, personList.get(wheelView.getCurrentItem()));
                    tvObject2.setText(personList.get(wheelView.getCurrentItem()));
                }
                if (flag == 3) {
                    if (!personList.get(wheelView.getCurrentItem()).equals("                       ") && phoneList.contains(personList.get(wheelView.getCurrentItem()))) {
                        ToastUtil.displayShortToast("不能添加相同的家属");
                        return;
                    }
                    phoneList.set(2, personList.get(wheelView.getCurrentItem()));
                    tvObject3.setText(personList.get(wheelView.getCurrentItem()));
                }
                if (flag == 4) {
                    if (!personList.get(wheelView.getCurrentItem()).equals("                       ") && phoneList.contains(personList.get(wheelView.getCurrentItem()))) {
                        ToastUtil.displayShortToast("不能添加相同的家属");
                        return;
                    }
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