package com.yxld.yxchuangxin.ui.activity.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.Utils.OsUtil;
import com.yxld.yxchuangxin.Utils.SpUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.VersionUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.ContainValue;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.db.DbRimUtil;
import com.yxld.yxchuangxin.db.green.UserInfo;
import com.yxld.yxchuangxin.db.green.UserInfoDao;
import com.yxld.yxchuangxin.entity.CxwyMallAdd;
import com.yxld.yxchuangxin.entity.LoginEntity;
import com.yxld.yxchuangxin.entity.LoginPhoneEntity;
import com.yxld.yxchuangxin.ui.activity.login.component.DaggerLoginComponent;
import com.yxld.yxchuangxin.ui.activity.login.contract.LoginContract;
import com.yxld.yxchuangxin.ui.activity.login.module.LoginModule;
import com.yxld.yxchuangxin.ui.activity.login.presenter.LoginPresenter;
import com.yxld.yxchuangxin.ui.activity.main.HomeActivity;
import com.yxld.yxchuangxin.ui.activity.mine.FindPasswordActivity;
import com.yxld.yxchuangxin.ui.activity.rim.WebViewActivity;
import com.yxld.yxchuangxin.view.AutoLinkStyleTextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.refactor.library.SmoothCheckBox;
import tyrantgit.explosionfield.ExplosionField;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static com.yxld.yxchuangxin.R.id.login_pwd;
import static com.yxld.yxchuangxin.R.id.login_tel;
import static com.yxld.yxchuangxin.R.id.login_xiaoqu;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.login
 * @Description: $description
 * @date 2017/05/23
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginPresenter mPresenter;
    @BindView(login_tel)
    EditText loginTel;
    @BindView(login_xiaoqu)
    MaterialSpinner loginXiaoqu;
    @BindView(login_pwd)
    EditText loginPwd;
    @BindView(R.id.txt_findPwd)
    TextView txtFindPwd;
    @BindView(R.id.loginSubmit)
    Button loginSubmit;
    @BindView(R.id.ptxi)
    AutoLinkStyleTextView ptxi;
    @BindView(R.id.checkbox)
    SmoothCheckBox checkbox;
    @BindView(R.id.iv_show_password)
    CheckBox ivShowPassword;
    @BindView(R.id.remember_password)
    SmoothCheckBox rememberPassword;
    @BindView(R.id.environment)
    View environment;
    @BindView(R.id.check_environment)
    SmoothCheckBox checkEnvironment;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.enter_environment)
    SmoothCheckBox enterEnvironment;
    private ExplosionField mExplosionField;
    private String lastshoujihao = "";

    private String userNameValue, passwordValue;

    private boolean canCheck = false;
    private int clickCount = 0;
    private String louPan;
    private UserInfoDao userInfoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        AppConfig.getInstance().mAppActivityManager.finishAllActivityWithoutThis();
        userNameValue = this.sp.getString("NAME", "");
        passwordValue = this.sp.getString("PASSWORD", "");
        xiangmuId = this.sp.getInt("xiangmuId", 0);
        String mac = StringUitl.getDeviceId(this);
        loginTel.setText(userNameValue);
        loginPwd.setText(passwordValue);
        if (mPresenter.isWeixinAvilible(this)) {
            showHasOldDialog();
            return;
        }
        if (!userNameValue.equals("") && !passwordValue.equals("") && netWorkIsAvailable()) {
            loginSubmit.performClick();
        }
    }

    private void showHasOldDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("存在老版本").setIcon(R.drawable.ic_launcher)
                .setMessage("请先卸载老版本的app!!!")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {// 积极
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getAppDetailSettingIntent(LoginActivity.this);
                        Toast.makeText(LoginActivity.this, "点击了确定", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {// 消极
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }


    //        以下代码可以跳转到应用详情，可以通过应用详情跳转到权限界面(6.0系统测试可用)
    private void getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", "com.yxld.yxchuangxin", null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", "com.yxld.yxchuangxin");
        }
        startActivity(localIntent);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.login_layout2);
        ButterKnife.bind(this);
        setImmersiveStatusBar();
        toolbar.setTitle("");
        toolbar.setBackground(getResources().getDrawable(R.color.color_00000000));
        checkbox.setChecked(true);
        rememberPassword.setChecked(true);
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
        txtFindPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FindPasswordActivity.class);
            }
        });
        ptxi.setOnClickCallBack(new AutoLinkStyleTextView.ClickCallBack() {
            @Override
            public void onClick(int position) {
                if (position == 0) {
                    Intent cz = new Intent();
                    cz.setClass(LoginActivity.this, // context
                            WebViewActivity.class);// 跳转的activity
                    Bundle cz1 = new Bundle();
                    cz1.putString("name", "平台协议");
                    cz1.putString("address", API.PINGTAI_XIEYI);
                    cz.putExtras(cz1);
                    startActivity(cz);
                }
            }
        });
        loginXiaoqu.getPopupWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_login_material_spinner));
        mExplosionField = ExplosionField.attach2Window(this);
        checkEnvironment.setChecked(SpUtil.getBoolean(AppConfig.getInstance(), ContainValue.ENVIRONMENT, true));
        checkEnvironment.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox smoothCheckBox, boolean b) {
                if (b) {
                    ToastUtil.show(LoginActivity.this, "选择线上环境");
                    enterEnvironment.setChecked(false);
//                    SpUtil.putBoolean(AppConfig.getInstance(), ContainValue.ENVIRONMENT, true);
                } else {
                    ToastUtil.show(LoginActivity.this, "选择测试环境");
                }
            }
        });
    }

    @Override
    protected void setupActivityComponent() {
        DaggerLoginComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(LoginContract.LoginContractPresenter presenter) {
        mPresenter = (LoginPresenter) presenter;
    }

    @OnClick({login_xiaoqu, R.id.txt_findPwd, R.id.loginSubmit, R.id.iv_logo, R.id.environment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case login_xiaoqu:
                checkRepetition(loginTel.getText().toString());
                break;
            case R.id.txt_findPwd:
                startActivity(FindPasswordActivity.class);
                break;
            case R.id.loginSubmit:
                attemptLogin();
                break;
            case R.id.iv_logo:
                environment.setVisibility(View.VISIBLE);
                break;
            case R.id.environment:
                if (clickCount == 0) {
                    fiveClickHandlere.sendEmptyMessageDelayed(0, 1000);
                }
                clickCount++;
                break;
        }
    }

    Handler fiveClickHandlere = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (clickCount >= 4) {
                checkEnvironment.setVisibility(View.VISIBLE);
                enterEnvironment.setVisibility(View.VISIBLE);
                enterEnvironment.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(SmoothCheckBox smoothCheckBox, boolean b) {
                        if (!checkEnvironment.isChecked()) {
                            SpUtil.putBoolean(LoginActivity.this, ContainValue.ENTERURL, b);
                            if (b) {
                                showEnterUrlDialog();
                            }
                        } else {
                            if (b) enterEnvironment.setChecked(false);
                        }
                    }
                });
            }
            clickCount = 0;
        }
    };

    private void showEnterUrlDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_enter_url, null);
        EditText editText = (EditText) view.findViewById(R.id.editText);
        if (!SpUtil.getString(LoginActivity.this, ContainValue.URL, "").equals("")) {
            editText.setText(SpUtil.getString(LoginActivity.this, ContainValue.URL, ""));
        } else {
            editText.setText(API.BASE_URL_DEVOLOP);
        }
        TextView confirm = (TextView) view.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString().trim();
                if (!content.equals("") && !content.substring(content.length() - 1, content.length()).equals("/")) {
                    ToastUtil.displayShortToast("输入的url不合法，请重新输入");
                    return;
                }
                SpUtil.putString(LoginActivity.this, ContainValue.URL, content);
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }

    /**
     * 设置沉浸式状态栏
     */
    protected void setImmersiveStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                || OsUtil.isMIUI()
                || OsUtil.isFlyme()) {
            setStatusBarFontIconDark(true);
        }
    }

    /**
     * 设置Android状态栏的字体颜色，状态栏为亮色的时候字体和图标是黑色，状态栏为暗色的时候字体和图标为白色
     *
     * @param dark 状态栏字体是否为深色
     */
    private void setStatusBarFontIconDark(boolean dark) {
        // 小米MIUI
        try {
            Window window = getWindow();
            Class clazz = getWindow().getClass();
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            if (dark) {    //状态栏亮色且黑色字体
                extraFlagField.invoke(window, darkModeFlag, darkModeFlag);
            } else {       //清除黑色字体
                extraFlagField.invoke(window, 0, darkModeFlag);
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        // 魅族FlymeUI
        try {
            Window window = getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (dark) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(lp, value);
            window.setAttributes(lp);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        // android6.0+系统
        // 这个设置和在xml的style文件中用这个<item name="android:windowLightStatusBar">true</item>属性是一样的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (dark) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (checkEnvironment.getVisibility() == View.VISIBLE) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("环境已经修改，点击确定生效");
            builder.setNegativeButton("取消", null);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (checkEnvironment.isChecked()) {
                        SpUtil.putBoolean(AppConfig.getInstance(), ContainValue.ENVIRONMENT, true);
                        KLog.i("选择了线上环境，置为true");
                    } else {
                        SpUtil.putBoolean(AppConfig.getInstance(), ContainValue.ENVIRONMENT, false);
                        KLog.i("选择了开发环境，置为false");
                    }
                    CxUtil.clearData(sp);
                    AppConfig.getInstance().mAppActivityManager.AppExit();
                }
            });
            builder.show();
        } else {
            super.onBackPressed();
        }
    }

    public void checkRepetition(String loginTel) {
        if (StringUitl.isNotEmpty(LoginActivity.this, loginTel, getString(R.string.please_enter_password))) {
            if (lastshoujihao.equals(loginTel)) {

            } else {
                Map<String, String> params = new HashMap<String, String>();
                params.put("shouji", loginTel);
                mPresenter.loginPlot(params);
            }
        }
    }

    private void attemptLogin() {
        userNameValue = loginTel.getText().toString().trim();
        passwordValue = loginPwd.getText().toString().trim();
        if (StringUitl.isNotEmpty(LoginActivity.this, userNameValue, getString(R.string.please_enter_phone_number)) && StringUitl.isNotEmpty(LoginActivity.this, passwordValue, getString(R.string.please_enter_password))) {
            if (xiangmuId == -1) {
                ToastUtil.show(this, "请选择所属项目");
                return;
            }
            if (!checkbox.isChecked()) {
                ToastUtil.show(this, "您没有同意欣物业《用户协议》");
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
                Toast.makeText(LoginActivity.this, "请确定账号密码格式是否正确", Toast.LENGTH_SHORT).show();
                loginSubmit.setClickable(true);
            }
        }
    }

    int xiangmuId = -1;

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

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
            Contains.user = info.getUser();
            if (info.getHouse() != null && info.getHouse().size() > 0) {
                Contains.appYezhuFangwus = info.getHouse();
                Contains.curSelectXiaoQuName = info.getHouse().get(0).getXiangmuLoupan();
                Contains.curSelectXiaoQuId = info.getHouse().get(0).getFwLoupanId();

                Contains.defuleAddress = new CxwyMallAdd();
                //设置默认地址项目
                if (Contains.user.getYezhuName() != null && !"".equals(Contains.user.getYezhuName())) {
                    Contains.defuleAddress.setAddName(Contains.user.getYezhuName());
                } else {
                    Contains.defuleAddress.setAddName(Contains.user.getYezhuShouji());
                }
                Contains.defuleAddress.setAddTel(Contains.user.getYezhuShouji());
                Contains.defuleAddress.setAddAdd(info.getHouse().get(0).getXiangmuLoupan() +
                        info.getHouse().get(0).getFwLoudong() + "栋" +
                        info.getHouse().get(0).getFwDanyuan() + "单元" +
                        info.getHouse().get(0).getFwFanghao());
            }

            if (info.getAddr() != null) {
                Contains.defuleAddress = info.getAddr();
            }
            if (rememberPassword.isChecked()) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("NAME", userNameValue);
                editor.putString("PASSWORD", passwordValue);
                editor.putInt("xiangmuId", xiangmuId);
                editor.putString("loupan", louPan);
                editor.commit();
                sp.edit().putBoolean("ISCHECK", true).commit();
                saveUserInfo();
            }
            new Thread() {
                public void run() {
                    try {
                        sleep(1000);
                        Intent login = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(login);
                        overridePendingTransition(R.anim.main_activity_in, R.anim.splash_activity_out);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else {
            Toast.makeText(LoginActivity.this, "你输入的手机号或者密码错误",
                    Toast.LENGTH_LONG).show();
            loginSubmit.setClickable(true);
        }
    }

    private void saveUserInfo() {
        KLog.i("开始保存到数据库。。。。。。。。。。。。。。");
        userInfoDao = AppConfig.getGreenDaoSession().getUserInfoDao();
        List<UserInfo> users = userInfoDao.loadAll();
        KLog.i("userslist:" + users.toString());
        if (users != null && users.size() != 0) {
            for (int i = 0; i < users.size(); i++) {//这里的循环次数为啥是users.size()，不是users.size()-1？？？？
                KLog.i("phone:" + users.get(i).getPhoneNumber() + "xianmuid" + users.get(i).getXiangmuId());
                if (users.get(i).getPhoneNumber().equals(userNameValue) && users.get(i).getXiangmuId() == xiangmuId) {
                    for (int j = 0; j < users.size(); j++) {
                        if (j == i) {
                            UserInfo user = users.get(j);
                            user.setPassword(passwordValue);
                            if (user.getLouPan() == null) {
                                user.setLouPan(Contains.appYezhuFangwus.get(Contains.curFangwu).getXiangmuLoupan());
                            }
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
        KLog.i("添加一条、、、、");
        KLog.i(users.size());
        KLog.i(userNameValue);
        KLog.i(Contains.appYezhuFangwus.get(Contains.curFangwu).getXiangmuLoupan());
        KLog.i(passwordValue);
        KLog.i(xiangmuId);
//        Log.e("wh", "userNameValue" + userNameValue + " louPan" + louPan + " passwordValue" +
//                passwordValue + " xiangmuId" + xiangmuId);
        //更新已有登录的账号 改变登录状态
        if (users != null && users.size() != 0) {
            for (int i = 0; i < users.size(); i++) {
                UserInfo user = users.get(i);
                user.setIsLogin(false);
                userInfoDao.update(user);
            }
        }
        try {
            UserInfo userInfo = new UserInfo(null, userNameValue, true, Contains.appYezhuFangwus.get(Contains.curFangwu).getXiangmuLoupan(), passwordValue, xiangmuId);
            userInfoDao.insert(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        KLog.i("userslist2:" + userInfoDao.loadAll().toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);//统计时长
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);//统计时长
    }

}