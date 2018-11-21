package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
import com.yxld.yxchuangxin.entity.HouxuanRenBean;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerHouxuanListComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.HouxuanListContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.HouxuanListModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.HouxuanListPresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.YwhHouxuanAdapter;
import com.yxld.yxchuangxin.view.YwhTjDialog;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/14 09:53:34
 */

public class HouxuanListActivity extends BaseActivity implements HouxuanListContract.View {

    @Inject
    HouxuanListPresenter mPresenter;

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.et_search)
    EditText etSearch;
    private YwhHouxuanAdapter ywhHouxuanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_houxuanlist);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setBackgroundColor(getResources().getColor(R.color.color_2d97ff));
        initListener();
        initRv();

        //隐藏输入法
//        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromInputMethod(etSearch.getWindowToken(),0);
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
                            .hideSoftInputFromWindow(HouxuanListActivity.this.getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);

                    initData();
                }
                return false;
            }
        });
    }

    private void testDialog(HouxuanRenBean.DataBean resultsBean) {
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
                    ToastUtil.displayShortToast("请填写推荐理由");
                    return;
                }
                ywhTjDialog.dismiss();
                Map<String, String> map = new HashMap<String, String>();
                map.put("uuid", Contains.uuid);
                map.put("tjfwbh", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwId() + "");
                map.put("btjid", resultsBean.getId() + "");
                map.put("btjfwbh", resultsBean.getFwId() + "");
                Log.e("wh", "reason " + ywhTjDialog.getEditText().getText().toString().trim());
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
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        ywhHouxuanAdapter = new YwhHouxuanAdapter();
        rv.setAdapter(ywhHouxuanAdapter);
        ywhHouxuanAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                HouxuanRenBean.DataBean resultsBean = (HouxuanRenBean.DataBean) baseQuickAdapter.getData().get(i);
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
        map.put("name", etSearch.getText().toString().trim());
        mPresenter.getTjcbz(map);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerHouxuanListComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .houxuanListModule(new HouxuanListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(HouxuanListContract.HouxuanListContractPresenter presenter) {
        mPresenter = (HouxuanListPresenter) presenter;
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
    public void getTjcbzSuccess(HouxuanRenBean baseEntity) {
        if (baseEntity.getCode() == 200) {
            ywhHouxuanAdapter.setNewData(baseEntity.getData());
        } else {
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