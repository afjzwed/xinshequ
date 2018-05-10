package com.yxld.yxchuangxin.ui.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.entity.LoginPhoneEntity;
import com.yxld.yxchuangxin.ui.activity.mine.component.DaggerFindPasswordComponent;
import com.yxld.yxchuangxin.ui.activity.mine.contract.FindPasswordContract;
import com.yxld.yxchuangxin.ui.activity.mine.module.FindPasswordModule;
import com.yxld.yxchuangxin.ui.activity.mine.presenter.FindPasswordPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yxld.yxchuangxin.R.id.new_password;
import static com.yxld.yxchuangxin.R.id.re_new_password;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: $description
 * @date 2017/06/23 14:14:05
 */

public class FindPasswordActivity extends BaseActivity implements FindPasswordContract.View {

    @Inject
    FindPasswordPresenter mPresenter;
    @BindView(R.id.shouji)
    EditText shouji;
    @BindView(R.id.login_xiaoqu)
    MaterialSpinner loginXiaoqu;
    @BindView(new_password)
    EditText newPassword;
    @BindView(re_new_password)
    EditText reNewPassword;
    @BindView(R.id.et_check_code)
    EditText etCheckCode;
    @BindView(R.id.tv_get_check_code)
    TextView tvGetCheckCode;
    @BindView(R.id.submit)
    Button submit;

    private String lastshoujihao = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_find_password);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerFindPasswordComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .findPasswordModule(new FindPasswordModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FindPasswordContract.FindPasswordContractPresenter presenter) {
        mPresenter = (FindPasswordPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick({R.id.login_xiaoqu, R.id.tv_get_check_code, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_xiaoqu:
                checkRepetition(shouji.getText().toString());
                break;
            case R.id.tv_get_check_code:
                if (checkCanGetCheckCode()) {
                    //获取验证码的逻辑
                    Map<String, String> map = new HashMap<>();
                    map.put("shouji", shouji.getText().toString());
                    mPresenter.getExistShouji(map, shouji.getText().toString());
                }
                break;
            case R.id.submit:
                if (checkCanGetCheckCode()) {
                    if (etCheckCode.getText().toString().length() == 4) {
                        //确认验证码
                        mPresenter.checkCheckCode(shouji.getText().toString(), etCheckCode.getText().toString());
                    }
                }
                break;
        }
    }

    private boolean checkCanGetCheckCode() {
        if(!StringUitl.isNotEmpty(this,shouji,getString(R.string.please_enter_phone_number))){
            return false;
        }
        if (xiangmuId == 0) {
            ToastUtil.show(this,getString(R.string.please_check_project));
            return false;
        }

        if(StringUitl.isNotEmpty(this,newPassword,getString(R.string.please_enter_password))){
            int len = newPassword.getText().toString().length();
            if (len >=6 && len <= 16) {
                if(newPassword.getText().toString().equals(reNewPassword.getText().toString())){
                    return true;
                }else{
                    Toast.makeText(this, R.string.two_password_atypism,Toast.LENGTH_LONG).show();
                    return false;
                }
            } else {
                ToastUtil.show(this,getString(R.string.password_rule));
                return false;
            }
        }else{
            return false;
        }
    }

    public void checkRepetition(String loginTel) {
        if (StringUitl.isNotEmpty(this,loginTel,getString(R.string.please_enter_phone_number))) {
            if (lastshoujihao.equals(loginTel)) {

            } else {
                Map<String, String> params = new HashMap<String, String>();
                params.put("shouji", loginTel);
                mPresenter.loginPlot(params);
            }
        }
    }

    int xiangmuId;
    @Override
    public void setLoginPhone(LoginPhoneEntity loginPhone) {
        if (loginPhone.getStatus() == -1) {
            ToastUtil.show(this, getString(R.string.not_project));
            return;
        }
        lastshoujihao = shouji.getText().toString();
        List<LoginPhoneEntity> list;
        list=loginPhone.getRows();
        final int[] ids=new int[list.size()];
        final String[] name=new String[list.size()];
        for (int i=0;i<list.size();i++){
            if(list.get(i) != null){
                ids[i]=list.get(i).getXiangmuId();
                name[i]=list.get(i).getXiangmuLoupan();
            }
        }
        loginXiaoqu.setItems(name);
        xiangmuId = ids[0];
        loginXiaoqu.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                xiangmuId = ids[position];
            }
        });
    }

    @Override
    public void setStartTime() {
        tvGetCheckCode.setClickable(false);
        tvGetCheckCode.setBackground(getResources().getDrawable(R.drawable.bg_check_code_999999));
        tvGetCheckCode.setTextColor(getResources().getColor(R.color.color_909090));
    }

    @Override
    public void setSecond(String text) {
        tvGetCheckCode.setText(text);
    }

    @Override
    public void setSecondOver() {
        tvGetCheckCode.setClickable(true);
        tvGetCheckCode.setBackground(getResources().getDrawable(R.drawable.bg_check_code_maincolor));
        tvGetCheckCode.setTextColor(getResources().getColor(R.color.main_color));
        tvGetCheckCode.setText(R.string.re_get_validate_code);
    }

    @Override
    public void setResetPassWordSuccess() {
        ToastUtil.show(this, getString(R.string.password_find_success));
        finish();
    }

    @Override
    public void checkCodeCheckSuccess() {
        //?shouji=%1$s&pwd=%2$s&id=%3$s
        Map<String, String> map = new HashMap<>();
        map.put("shouji", shouji.getText().toString());
        map.put("pwd", StringUitl.getMD5(reNewPassword.getText().toString()));
        map.put("id", xiangmuId + "");
        mPresenter.getReSetPassWord(map);
    }
}