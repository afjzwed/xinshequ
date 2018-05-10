package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.Question;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerSatisficingComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.SatisficingContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.SatisficingModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.SatisficingPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.SatisficingAdapter;
import com.yxld.yxchuangxin.view.NoScrollViewPager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/20 10:07:43
 */

public class SatisficingActivity extends BaseActivity implements SatisficingContract.View {

    @Inject
    SatisficingPresenter mPresenter;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    @BindView(R.id.bt_next)
    Button btNext;
    private SatisficingAdapter adapter;
    private Map<String, String> map;
    private LinkedHashMap<String, String> checkMap = new LinkedHashMap<>();

    private String question = "[";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_satisficing);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        mPresenter.getQuestion();
        map = new HashMap<>();
        map.put("userId", Contains.user.getYezhuId() + "");
        map.put("xiangmuId", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoupanId() + "");
        map.put("tableId", "1");
    }

    @Override
    protected void setupActivityComponent() {
        DaggerSatisficingComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .satisficingModule(new SatisficingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(SatisficingContract.SatisficingContractPresenter presenter) {
        mPresenter = (SatisficingPresenter) presenter;
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
    public void setData(List<Question.DataBean> list) {
        adapter = new SatisficingAdapter(this, list);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onSuccess() {
        ToastUtil.show(this, "提交成功，感谢您的参与！");
        finish();
    }

    @OnClick(R.id.bt_next)
    public void onViewClicked() {
        int answerId = adapter.getCheckdItem();
        if (answerId == -1) {
            ToastUtil.show(this, "您还没有选择答案");
            return;
        }
        if (viewPager.getCurrentItem() == adapter.getCount() - 1) {
            checkMap.put((viewPager.getCurrentItem() + 1) + "", answerId + "");
            String json = map2Json(checkMap);
            KLog.i(json);
            map.put("question", json);
            Map<String, String> map1 = new HashMap();
            map1.put("abc", map2String(map));
            KLog.i(map1);
            mPresenter.saveMaYiDu(map2String(map));
            return;
        }
        checkMap.put((viewPager.getCurrentItem() + 1) + "", answerId + "");
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        if (viewPager.getCurrentItem() == adapter.getCount() - 1) {
            btNext.setText("提交");
        }
    }

    private String map2Json(LinkedHashMap<String, String> map) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            json.append("{id:" + entry.getKey() + ",value:" + entry.getValue() + "},");
        }
        json.deleteCharAt(json.length() - 1);
        json.append("]");
        return json.toString();
    }

    private String map2String(Map map) {
        StringBuilder json = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        json.append("{");
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            json.append("" + entry.getKey() + ":" + entry.getValue() + ",");
//            if (entry.getKey().equals("question")) {
//                json.append("" + entry.getKey() + ":" + entry.getValue() + ",");
//                KLog.i(entry.getValue());
//            } else {
//                json.append("" + entry.getKey() + ":\"" + entry.getValue() + "\",");
//            }
        }
        json.deleteCharAt(json.length() - 1);
        json.append("}");
        return json.toString();
    }
}