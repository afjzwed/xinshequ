package com.yxld.yxchuangxin.ui.activity.mine;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.CxUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.db.DbRimUtil;
import com.yxld.yxchuangxin.db.green.UserInfo;
import com.yxld.yxchuangxin.db.green.UserInfoDao;
import com.yxld.yxchuangxin.entity.CxwyMallAdd;
import com.yxld.yxchuangxin.entity.LoginEntity;
import com.yxld.yxchuangxin.ui.activity.login.LoginActivity;
import com.yxld.yxchuangxin.ui.activity.mine.component.DaggerMultiAccountComponent;
import com.yxld.yxchuangxin.ui.activity.mine.contract.MultiAccountContract;
import com.yxld.yxchuangxin.ui.activity.mine.module.MultiAccountModule;
import com.yxld.yxchuangxin.ui.activity.mine.presenter.MultiAccountPresenter;
import com.yxld.yxchuangxin.ui.adapter.mine.MultiAccountAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.mine
 * @Description: $description
 * @date 2017/10/11 09:31:27
 */

public class MultiAccountActivity extends BaseActivity implements MultiAccountContract.View {

    @Inject
    MultiAccountPresenter mPresenter;
    @Inject
    MultiAccountAdapter multiAccountAdapter;
    UserInfoDao userInfoDao;
    @BindView(R.id.add_account)
    Button addAccount;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_multi_account);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        userInfoDao = AppConfig.getGreenDaoSession().getUserInfoDao();
        List<UserInfo> users = userInfoDao.loadAll();
        if (users == null || users.size() == 0) {  //Contains.appYezhuFangwus.get(Contains.curFangwu).getXiangmuLoupan()
            UserInfo userInfo = new UserInfo(null, sp.getString("NAME", ""), true, sp.getString("loupan", Contains.appYezhuFangwus.get(Contains.curFangwu).getXiangmuLoupan()), sp.getString("PASSWORD", ""), sp.getInt("xiangmuId", 0));
            userInfoDao.insert(userInfo);
        }
//        KLog.i("数据库中的数据数量为：" + users.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(multiAccountAdapter);
        multiAccountAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (!multiAccountAdapter.getData().get(i).getIsLogin()) {
                    mPresenter.switchAccount(multiAccountAdapter.getData().get(i));
                }
            }
        });

        multiAccountAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                showDeletAccountDialog(multiAccountAdapter.getData().get(i));
                return true;
            }
        });
    }

    private void showDeletAccountDialog(UserInfo userInfo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setIcon(R.mipmap.logo)
                .setMessage("删除该条登录信息？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {// 积极
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userInfoDao.delete(userInfo);
                        multiAccountAdapter.setNewData(userInfoDao.loadAll());
                        if (userInfo.getPhoneNumber().equals(sp.getString("NAME", "")) && (userInfo.getXiangmuId() == sp.getInt("xiangmuId", 0))){
                            CxUtil.clearData(sp);
                            startActivity(LoginActivity.class);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {// 消极
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void setupActivityComponent() {
        DaggerMultiAccountComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .multiAccountModule(new MultiAccountModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(MultiAccountContract.MultiAccountContractPresenter presenter) {
        mPresenter = (MultiAccountPresenter) presenter;
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
    public void loginSuccess(UserInfo userInfo) {
        if (!sp.getString("NAME", "").equals(userInfo.getPhoneNumber()) || sp.getInt("xiangmuId", 0) !=userInfo.getXiangmuId()) {
            // TODO: 2017/12/27   如果账号不等于当前的账号或者项目  清空外埠商城的购物车
            DbRimUtil.getInstans().deleteAll();
            KLog.i("清空外埠商城购物车成功");
        }
        KLog.i("登录的小区为：" + userInfo.getLouPan());
        multiAccountAdapter.setNewData(AppConfig.getGreenDaoSession().getUserInfoDao().loadAll());
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("NAME", userInfo.getPhoneNumber());
        editor.putString("PASSWORD", userInfo.getPassword());
        editor.putInt("xiangmuId", userInfo.getXiangmuId());
        editor.putString("loupan", userInfo.getLouPan());
        editor.commit();
        sp.edit().putBoolean("ISCHECK", true).commit();
        EventBus.getDefault().post("reLogin");
    }

    @Override
    public void loginSuccessPre(LoginEntity info) {
        KLog.i(info.getMSG());
        if (info.getMSG().equals("登录成功")) {
            KLog.i("登陆成功， 修改uuid");
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
        }
    }

    @OnClick(R.id.add_account)
    public void onViewClicked() {
        startActivity(AddAccountActivity.class);
        overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
    }
}