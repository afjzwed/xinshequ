package com.yxld.yxchuangxin.ui.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.login.LoginActivity;
import com.yxld.yxchuangxin.ui.activity.mine.component.DaggerResetPasswordComponent;
import com.yxld.yxchuangxin.ui.activity.mine.contract.ResetPasswordContract;
import com.yxld.yxchuangxin.ui.activity.mine.module.ResetPasswordModule;
import com.yxld.yxchuangxin.ui.activity.mine.presenter.ResetPasswordPresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: $description
 * @date 2017/06/23 11:03:59
 */

public class ResetPasswordActivity extends BaseActivity implements ResetPasswordContract.View {
    public static final String KEY_IN_TYPE = "key_in_type";
    public static final int IN_TYPE_MALL = 0x000002;
    public static final int IN_TYPE_DEFAULT = 0x000001;
    @Inject
    ResetPasswordPresenter mPresenter;
    @BindView(R.id.old_password)
    EditText oldPassword;
    @BindView(R.id.new_password)
    EditText newPassword;
    @BindView(R.id.re_new_password)
    EditText reNewPassword;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.iv_bg)
    ImageView ivBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            int inType = bundle.getInt(KEY_IN_TYPE, IN_TYPE_DEFAULT);
            if (inType == IN_TYPE_MALL) {
                toolbar.setBackgroundColor(getResources().getColor(R.color.color_ff9934));
                ivBg.setBackgroundResource(R.mipmap.xzdz_bj02);
                submit.setBackgroundResource(R.drawable.btn_cart_order_ok);
            }
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerResetPasswordComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .resetPasswordModule(new ResetPasswordModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(ResetPasswordContract.ResetPasswordContractPresenter presenter) {
        mPresenter = (ResetPasswordPresenter) presenter;
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
    public void onUpdatePasswordSuccess() {
        Map<String, String> map = new HashMap<>();
        map.put("shouji", Contains.user.getYezhuShouji());
        mPresenter.getLoginOut(map);
    }

    @Override
    public void loginOut() {
        CxUtil.clearData(sp);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("flag", "other");
        startActivity(intent);
        ToastUtil.show(this, getString(R.string.password_fix_success));
    }

    @OnClick(R.id.submit)
    public void onViewClicked() {
        String old_pwd = oldPassword.getText().toString();
        String new_pwd = newPassword.getText().toString();
        String repeat_pwd = reNewPassword.getText().toString();
        String shouji = Contains.user.getYezhuShouji();
        if (Contains.user == null || "".equals(Contains.user.getYezhuPwd()) || !Contains.user.getYezhuPwd().equalsIgnoreCase(StringUitl.getMD5(old_pwd))) {
            ToastUtil.show(this, getString(R.string.original_password_error));
            return;
        }
        if (new_pwd == null || new_pwd.length() < 6 || new_pwd.length() > 16) {
            ToastUtil.show(this, getString(R.string.password_rule));
            return;
        }
        if (new_pwd != null && !"".equals(new_pwd) && new_pwd.equals(repeat_pwd)) {
            if (StringUitl.getMD5(old_pwd).equals(StringUitl.getMD5(new_pwd))) {
                ToastUtil.show(this, getString(R.string.two_password_identical));
                return;
            }
            Map<String, String> map = new HashMap<>();
            //?shouji=%1$s&pwd=%2$s&newPassWord=%3$s&uuid=%4$s
            map.put("shouji", shouji);
            map.put("pwd", StringUitl.getMD5(old_pwd));
            map.put("newPassWord", StringUitl.getMD5(new_pwd));
            map.put("uuid", Contains.uuid);
            mPresenter.getUpdatePwd(map);
        } else {
            Toast.makeText(ResetPasswordActivity.this, getString(R.string.two_password_atypism),
                    Toast.LENGTH_SHORT).show();
        }
    }
}