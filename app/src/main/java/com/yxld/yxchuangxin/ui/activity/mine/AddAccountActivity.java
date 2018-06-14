package com.yxld.yxchuangxin.ui.activity.mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.VersionUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.db.DbRimUtil;
import com.yxld.yxchuangxin.db.green.UserInfo;
import com.yxld.yxchuangxin.db.green.UserInfoDao;
import com.yxld.yxchuangxin.entity.CxwyMallAdd;
import com.yxld.yxchuangxin.entity.LoginEntity;
import com.yxld.yxchuangxin.entity.LoginPhoneEntity;
import com.yxld.yxchuangxin.ui.activity.main.HomeActivity;
import com.yxld.yxchuangxin.ui.activity.mine.component.DaggerAddAccountComponent;
import com.yxld.yxchuangxin.ui.activity.mine.contract.AddAccountContract;
import com.yxld.yxchuangxin.ui.activity.mine.module.AddAccountModule;
import com.yxld.yxchuangxin.ui.activity.mine.presenter.AddAccountPresenter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tyrantgit.explosionfield.ExplosionField;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static com.yxld.yxchuangxin.R.id.login_xiaoqu;
import static com.yxld.yxchuangxin.contain.Contains.user;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: $description
 * @date 2017/10/11 09:49:50
 */

public class AddAccountActivity extends BaseActivity implements AddAccountContract.View {

    @Inject
    AddAccountPresenter mPresenter;
    @BindView(R.id.login_tel)
    EditText loginTel;
    @BindView(login_xiaoqu)
    MaterialSpinner loginXiaoqu;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.iv_show_password)
    CheckBox ivShowPassword;
    @BindView(R.id.content)
    AutoLinearLayout content;
    @BindView(R.id.loginSubmit)
    Button loginSubmit;
    private ExplosionField mExplosionField;
    private String lastshoujihao = "";

    private String userNameValue, passwordValue;

    private boolean canCheck = false;
    private int clickCount = 0;
    private String louPan = "";
    private UserInfoDao userInfoDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_add_account);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mExplosionField = ExplosionField.attach2Window(this);
//        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back_arrow));
        ivShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    loginPwd.setInputType(TYPE_CLASS_TEXT);
                    loginPwd.setSelection(loginPwd.getText().length());
                } else {
                    loginPwd.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
                    loginPwd.setSelection(loginPwd.getText().length());
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.activity_translate_out_1);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerAddAccountComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .addAccountModule(new AddAccountModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(AddAccountContract.AddAccountContractPresenter presenter) {
        mPresenter = (AddAccountPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick({login_xiaoqu, R.id.loginSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case login_xiaoqu:
                checkRepetition(loginTel.getText().toString());
                break;
            case R.id.loginSubmit:
                attemptLogin();
                break;
        }
    }

    public void checkRepetition(String loginTel) {
        if (StringUitl.isNotEmpty(AddAccountActivity.this, loginTel, getString(R.string.please_enter_password))) {
            if (lastshoujihao.equals(loginTel)) {

            } else {
                Map<String, String> params = new HashMap<String, String>();
                params.put("shouji", loginTel);
                mPresenter.loginPlot(params);
            }
        }
    }

    private void attemptLogin() {
        userNameValue = loginTel.getText().toString();
        passwordValue = loginPwd.getText().toString();
        if (StringUitl.isNotEmpty(AddAccountActivity.this, userNameValue, getString(R.string.please_enter_phone_number)) && StringUitl.isNotEmpty(AddAccountActivity.this, passwordValue, getString(R.string.please_enter_password))) {
            if (xiangmuId == -1) {
                ToastUtil.show(this, "请选择所属项目");
                return;
            }
            loginSubmit.setClickable(false);
            int len = userNameValue.length();
            int len1 = passwordValue.length();
            String mac = StringUitl.getDeviceId(this);
            if (len1 >= 6 && len == 11) {
                progressDialog.show();
                Map<String, String> params = new HashMap<String, String>();
                params.put("shouji", userNameValue);
                params.put("pwd", StringUitl.getMD5(passwordValue));
                params.put("macAddr", mac);
                params.put("id", xiangmuId + "");
                params.put("app_version", VersionUtil.getAppVersionName(this));
                params.put("mobile_type", "A");
                params.put("mobile_brand", StringUitl.getPhoneBrand() + StringUitl.getPhoneModel());
                params.put("mobile_version", StringUitl.getPhoneSysVersion());
                mPresenter.login(params);
                loginSubmit.setClickable(true);
            } else {
                Toast.makeText(AddAccountActivity.this, "请确定账号密码格式是否正确", Toast.LENGTH_SHORT).show();
                loginSubmit.setClickable(true);
            }
        }
    }

    int xiangmuId = -1;

    @Override
    public void setLoginPhone(LoginPhoneEntity loginPhone) {
        if (loginPhone.getStatus() == -1) {
            ToastUtil.show(this, getString(R.string.not_project));
            return;
        }
        List<LoginPhoneEntity> list;
        for (int i = 0; i < loginPhone.getRows().size(); i++) {
            if (loginPhone.getRows().get(i) == null) {
                loginPhone.getRows().remove(i);
            }
        }
        list = loginPhone.getRows();
        final int[] ids = new int[list.size()];
        final String[] name = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                ids[i] = list.get(i).getXiangmuId();
                name[i] = list.get(i).getXiangmuLoupan();
            }
        }
        lastshoujihao = loginTel.getText().toString();
        loginXiaoqu.setItems(name);
        xiangmuId = ids[0];
        louPan = name[0];
        loginXiaoqu.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                xiangmuId = ids[position];
                louPan = name[position];
            }
        });
    }

    @Override
    public void login(LoginEntity info) {
        progressDialog.hide();
        if (info.getStatus() != STATUS_CODE_OK) {
            onError(info.getMSG());
            return;
        }
        if (info.getMSG().equals("登录成功")) {
            if (!sp.getString("NAME", "").equals(userNameValue) || sp.getInt("xiangmuId", 0) != xiangmuId) {
                // TODO: 2017/12/27   如果账号不等于当前的账号或者项目  清空外埠商城的购物车
                DbRimUtil.getInstans().deleteAll();
                KLog.i("清空外埠商城购物车成功");
            }
            mExplosionField.explode(loginSubmit);
            loginSubmit.setOnClickListener(null);
            Contains.uuid = info.getUuid();
            Contains.yeZhuVo=info.getYezhuVo();
            user = info.getUser();
            if (info.getHouse() != null && info.getHouse().size() > 0) {
                Contains.appYezhuFangwus = info.getHouse();
                Contains.curSelectXiaoQuName = info.getHouse().get(0).getXiangmuLoupan();
                Contains.curSelectXiaoQuId = info.getHouse().get(0).getFwLoupanId();

                Contains.defuleAddress = new CxwyMallAdd();
                //设置默认地址项目
                if (user.getYezhuName() != null && !"".equals(user.getYezhuName())) {
                    Contains.defuleAddress.setAddName(user.getYezhuName());
                } else {
                    Contains.defuleAddress.setAddName(user.getYezhuShouji());
                }
                Contains.defuleAddress.setAddTel(user.getYezhuShouji());
                Contains.defuleAddress.setAddAdd(info.getHouse().get(0).getXiangmuLoupan() +
                        info.getHouse().get(0).getFwLoudong() + "栋" +
                        info.getHouse().get(0).getFwDanyuan() + "单元" +
                        info.getHouse().get(0).getFwFanghao());
            }

            if (info.getAddr() != null) {
                Contains.defuleAddress = info.getAddr();
            }
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("NAME", userNameValue);
            editor.putString("PASSWORD", passwordValue);
            editor.putInt("xiangmuId", xiangmuId);
            editor.putString("loupan", louPan);
            editor.commit();
            sp.edit().putBoolean("ISCHECK", true).commit();
            saveUserInfo();
            new Thread() {
                public void run() {
                    try {
                        sleep(1200);
                        Intent login = new Intent(AddAccountActivity.this, HomeActivity.class);
                        startActivity(login);
                        overridePendingTransition(R.anim.main_activity_in, R.anim.splash_activity_out);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else {
            Toast.makeText(AddAccountActivity.this, "你输入的手机号或者密码错误",
                    Toast.LENGTH_LONG).show();
            loginSubmit.setClickable(true);
        }
    }

    private void saveUserInfo() {
        KLog.i("开始保存到数据库。。。。。。。。。。。。。。");
        userInfoDao = AppConfig.getGreenDaoSession().getUserInfoDao();
        List<UserInfo> users = userInfoDao.loadAll();
        if (users != null && users.size() != 0) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getPhoneNumber().equals(userNameValue) && users.get(i).getXiangmuId() == xiangmuId) {
                    for (int j = 0; j < users.size(); j++) {
                        if (j == i) {
                            UserInfo user = users.get(j);
                            user.setIsLogin(true);
                            userInfoDao.update(user);
                        } else {
                            UserInfo user = users.get(j);
                            user.setIsLogin(false);
                            userInfoDao.update(user);
                        }
                    }
                    return;
                }
            }
        }
        List<UserInfo> users1 = userInfoDao.loadAll();
        if (users1 != null && users1.size() != 0) {
            for (int i = 0; i < users1.size(); i++) {
                UserInfo user = users1.get(i);
                user.setIsLogin(false);
                userInfoDao.update(user);
            }
        }
        UserInfo userInfo = new UserInfo(null, userNameValue, true, louPan, passwordValue, xiangmuId);
        userInfoDao.insert(userInfo);
    };

}