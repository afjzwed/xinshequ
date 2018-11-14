package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.YwhTj;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerTuiJianListComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.TuiJianListContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.TuiJianListModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.TuiJianListPresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.YwhTuiJianAdapter;
import com.yxld.yxchuangxin.view.YwhTjDialog;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 10:54:14
 */

public class TuiJianListActivity extends BaseActivity implements TuiJianListContract.View {

    @Inject
    TuiJianListPresenter mPresenter;
    @BindView(R.id.rv) RecyclerView rv;
    @BindView(R.id.et_search) EditText etSearch;
    private YwhTuiJianAdapter ywhTuiJianAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_tuijian_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("筹备组人员推荐");
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_2d97ff));
        initListener();
        initRv();
//        testDialog();
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) { // 当按了搜索之后关闭软键盘
                    ((InputMethodManager) etSearch.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(TuiJianListActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    initData();
                }
                return false;

            }
        });
    }

    private void testDialog(YwhTj.DataBean.ResultsBean resultsBean) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
        YwhTjDialog ywhTjDialog = new YwhTjDialog(this);
        ywhTjDialog.setCancelable(false);
        ywhTjDialog.setConfirmListener(new YwhTjDialog.OnConfirmListener() {
            @Override
            public void onCancel() {
                ywhTjDialog.dismiss();
            }

            @Override
            public void onConfirm() {

                if (TextUtils.isEmpty(ywhTjDialog.getEditText().getText().toString().trim())) {
                    ToastUtil.showShort("请填写推荐理由");
                    return;
                }
                ywhTjDialog.dismiss();
                Map<String, String> map = new HashMap<String, String>();
                map.put("uuid", Contains.uuid);
                map.put("fwid", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwId() + "");
                map.put("tjid", resultsBean.getId() + "");
                map.put("reason", ywhTjDialog.getEditText().getText().toString().trim());
                mPresenter.comitLy(map);
            }
        });
        ywhTjDialog.show();
        ywhTjDialog.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                if (focused) { //dialog弹出软键盘
                    ywhTjDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                }
            }
        });
        ywhTjDialog.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 200) {
                    ywhTjDialog.getTvCount().setText(s.length() + "/200");
                } else {
                    ToastUtil.showShort("限制输入200字符");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        ywhTuiJianAdapter = new YwhTuiJianAdapter();
        rv.setAdapter(ywhTuiJianAdapter);
        ywhTuiJianAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                YwhTj.DataBean.ResultsBean resultsBean = (YwhTj.DataBean.ResultsBean) baseQuickAdapter.getData().get(i);
                if (view.getId() == R.id.tv_tj) {
                    testDialog(resultsBean);
                }
            }
        });
    }

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("yezhuName", etSearch.getText().toString().trim());
        mPresenter.getTjcbz(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerTuiJianListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .tuiJianListModule(new TuiJianListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(TuiJianListContract.TuiJianListContractPresenter presenter) {
        mPresenter = (TuiJianListPresenter) presenter;
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
    public void getTjcbzSuccess(YwhTj baseEntity) {
        if (baseEntity.getCode()==200){
            ywhTuiJianAdapter.setNewData(baseEntity.getData().getResults());
        }else {
            onError(baseEntity.msg);
        }
    }

    @Override
    public void commitLySuccess(BaseEntity baseEntity) {
        ToastUtil.showShort(baseEntity.getMsg());

    }

    @OnClick(R.id.tv_cancle)
    public void onViewClicked() {
        //取消按钮
        etSearch.setText("");
    }
}