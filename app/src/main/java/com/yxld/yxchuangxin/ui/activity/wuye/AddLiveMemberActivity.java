package com.yxld.yxchuangxin.ui.activity.wuye;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.socks.library.KLog;
import com.yanzhenjie.permission.PermissionListener;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.PermissionUtil;
import com.yxld.yxchuangxin.Utils.PopWindowUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerAddLiveMemberComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.AddLiveMemberContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.AddLiveMemberModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.AddLiveMemberPresenter;
import com.yxld.yxchuangxin.view.AddMemberView;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.zhy.autolayout.AutoLinearLayout;

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
 * @Description: $description 添加入住成员
 * @date 2017/06/07
 */

public class AddLiveMemberActivity extends BaseActivity implements AddLiveMemberContract.View {

    @Inject
    AddLiveMemberPresenter mPresenter;
    @BindView(R.id.chengyuan_leixing)
    AddMemberView chengyuanLeixing;
    @BindView(R.id.chengyuan_xingbie)
    AddMemberView chengyuanXingbie;
    @BindView(R.id.cehngyuan_xingming)
    AddMemberView cehngyuanXingming;
    @BindView(R.id.chengyuan_haoma)
    AddMemberView chengyuanHaoma;
    @BindView(R.id.cehngyaun_shenfenzheng)
    AddMemberView cehngyaunShenfenzheng;
    @BindView(R.id.save)
    TextView save;

    //性别，0男， 1女
    private String curSex = "0";
    //居住类型1 家人 2 租客
    private String curGuanxi = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_addlivemember);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        chengyuanHaoma.setRightImgOnClickListener(new AddMemberView.RightImgOnClickListener() {
            @Override
            public void OnRightClick() {
                PermissionUtil.permission(AddLiveMemberActivity.this, 0x000503, new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        if (requestCode == 0x000503) {
                            Intent intent = new Intent(Intent.ACTION_PICK,
                                    android.provider.ContactsContract.Contacts.CONTENT_URI);
                            startActivityForResult(intent, 1);
                        }
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        ToastUtil.show(AddLiveMemberActivity.this, "请提供读取联系人的权限");
                    }
                }, Manifest.permission.READ_CONTACTS);

            }
        });
        StringUitl.setInputName(cehngyuanXingming.getEditTextView());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerAddLiveMemberComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .addLiveMemberModule(new AddLiveMemberModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(AddLiveMemberContract.AddLiveMemberContractPresenter presenter) {
        mPresenter = (AddLiveMemberPresenter) presenter;
    }

    @Override
    public void onBackPressed() {
        if (CustomPopWindow.onBackPressed()) {
            return;
        }
        finish();
        overridePendingTransition(0, R.anim.activity_translate_out_1);
    }

    @OnClick({R.id.chengyuan_leixing, R.id.chengyuan_xingbie, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chengyuan_leixing:
                showLeixing(chengyuanLeixing);
                break;
            case R.id.chengyuan_xingbie:
                showXingbie(chengyuanXingbie);
                break;
            case R.id.save:
                if (StringUitl.isNotEmpty(AddLiveMemberActivity.this, chengyuanLeixing.getEdittext(), "请选择成员类型")) {
                    if (StringUitl.isNotEmpty(AddLiveMemberActivity.this, chengyuanXingbie.getEdittext(), "请选择成员性别")) {
                        if (StringUitl.isNotEmpty(AddLiveMemberActivity.this, cehngyuanXingming.getEdittext(), "请输入成员姓名")) {
                            if (StringUitl.isNotEmpty(AddLiveMemberActivity.this, chengyuanHaoma.getEdittext(), "请输入成员电话")) {
                                if (!StringUitl.isMobileNum(chengyuanHaoma.getEdittext())) {
                                    ToastUtil.show(AddLiveMemberActivity.this, "请输入正确手机号码");
                                    return;
                                }
                                if (chengyuanLeixing.getEdittext().equals("家属") || (chengyuanLeixing.getEdittext().equals("租客") && (StringUitl.isIDCard15(cehngyaunShenfenzheng.getEdittext()) || StringUitl.isIDCard18(cehngyaunShenfenzheng.getEdittext())))) {
                                    //添加成员的请求
                                    Map<String, String> map = new HashMap<String, String>();
                                    map.put("yezhuId", Contains.user.getYezhuId() + "");
                                    map.put("fwyzType", curGuanxi);
                                    map.put("yezhuCardNum", cehngyaunShenfenzheng.getEdittext());
                                    map.put("yezhuName", cehngyuanXingming.getEdittext());
                                    map.put("yezhuSex", curSex);
                                    map.put("yezhuShouji", chengyuanHaoma.getEdittext());
                                    map.put("fwyzFw", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwId() + "");
                                    map.put("yezhuGzdw", Contains.user.getYezhuGzdw());
                                    map.put("xiaoquId", Contains.user.getYezhuXmId().toString());
                                    map.put("uuid", Contains.uuid);
                                    KLog.i(map);
                                    progressDialog.show();
                                    mPresenter.addChengyuan(map);
                                } else {
                                    ToastUtil.show(AddLiveMemberActivity.this, "身份证不合法");
                                    return;
                                }
                            }
                        }
                    }
                }
                break;
        }
    }

    /**
     * 选怎成员类型的popwindow
     *
     * @param v
     */
    private void showLeixing(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_add_chengyuan_leixing, null);
        AutoLinearLayout llErWeiMa = (AutoLinearLayout) view.findViewById(R.id.ll_erweima);
        TextView tv_jiashu = (TextView) view.findViewById(R.id.tv_jiashu);
        tv_jiashu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chengyuanLeixing.setEdittext("家属");
                cehngyaunShenfenzheng.setVisibility(View.GONE);
                curGuanxi = "1";
                CustomPopWindow.onBackPressed();
            }
        });
        TextView tv_zuke = (TextView) view.findViewById(R.id.tv_zuke);
        tv_zuke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chengyuanLeixing.setEdittext("租客");
                cehngyaunShenfenzheng.setVisibility(View.VISIBLE);
                curGuanxi = "2";
                CustomPopWindow.onBackPressed();
            }
        });
        view.findViewById(R.id.cancal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow.onBackPressed();
            }
        });
        PopWindowUtil.showFullScreenPopWindow(this, v, view, llErWeiMa);
    }

    /**
     * 选择成员性别的popwindow
     *
     * @param v
     */
    private void showXingbie(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_add_chengyuan_xingbie, null);
        AutoLinearLayout llErWeiMa = (AutoLinearLayout) view.findViewById(R.id.ll_erweima);
        TextView tv_nan = (TextView) view.findViewById(R.id.tv_nan);
        tv_nan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chengyuanXingbie.setEdittext("男");
                curSex = "0";
                CustomPopWindow.onBackPressed();
            }
        });
        TextView tv_nv = (TextView) view.findViewById(R.id.tv_nv);
        tv_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chengyuanXingbie.setEdittext("女");
                curSex = "1";
                CustomPopWindow.onBackPressed();
            }
        });
        view.findViewById(R.id.cancal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow.onBackPressed();
            }
        });
        PopWindowUtil.showFullScreenPopWindow(this, v, view, llErWeiMa);
    }

    @Override
    public void setContactNumber(String name, String number) {
        cehngyuanXingming.setEdittext(name);
        chengyuanHaoma.setEdittext(number);
    }

    @Override
    public void onAddBack(String msg) {
        ToastUtil.show(this, msg);
        progressDialog.hide();
    }
    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}