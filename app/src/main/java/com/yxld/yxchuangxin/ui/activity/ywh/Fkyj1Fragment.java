package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerFkyj1Component;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.Fkyj1Contract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.Fkyj1Module;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.Fkyj1Presenter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/09 13:26:28
 */

public class Fkyj1Fragment extends BaseFragment implements Fkyj1Contract.View {

    @Inject
    Fkyj1Presenter mPresenter;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fkyj1, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();


        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = etContent.getText().toString();
                tvCount.setText(string.length() + "/200");
            }
        });
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // 相当于onResume()方法
        } else {
            //切换fragment 隐藏软键盘
            if (etContent != null) {
                InputMethodManager imm = (InputMethodManager) etContent.getContext().getSystemService(Context
                        .INPUT_METHOD_SERVICE);

                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(etContent.getApplicationWindowToken(), 0);
                }
            }
        }
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerFkyj1Component
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .fkyj1Module(new Fkyj1Module(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(Fkyj1Contract.Fkyj1ContractPresenter presenter) {
        mPresenter = (Fkyj1Presenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

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
    public void setData() {
        etContent.setText("");
        Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void setError(String s) {
        ToastUtil.showShort(s);
    }

    @Override
    public void commitFkyjSuccess(BaseEntity baseEntity) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.tv_commit)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etContent.getText().toString().trim())) {
            Toast.makeText(getActivity(), "内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        FkyjActivity fkyjActivity = (FkyjActivity) getActivity();
        //判断调哪个接口
        /*switch (fkyjActivity.getPosition()) {
            case 1: {//成立筹备组
                Map<String, String> map1 = new HashMap<>();
                map1.put("uuid", Contains.uuid);
                map1.put("fwid", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwId() + "");
                map1.put("reason", etContent.getText().toString().trim());
                mPresenter.commitFkyj2(map1);
                break;
            }
            case 2: {//筹备组工作

                break;
            }
            case 3: {//候选人确认
//                LinkedHashMap<String, String> map2 = new LinkedHashMap<>();
//                map2.put("reason", etContent.getText().toString().trim());
//                mPresenter.conmitFkyjInfo2(Contains.appYezhuFangwus.get(Contains.curFangwu).getFwId()+"", Contains.uuid,
//                        map2);//候选人确认的意见反馈接口
                break;
            }
            case 4: {//业主大会
                LinkedHashMap<String, String> map = new LinkedHashMap<>();
                map.put("uuid", Contains.uuid);
                map.put("gongshiId", 1 + "");//公示ID
                map.put("expect", Contains.appYezhuFangwus.get(Contains.curFangwu).getXiangmuLoupan());//如果没有就传项目名
                map.put("building", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoudong());//楼栋
                map.put("unit", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwDanyuan());//单元
                map.put("room_number", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwFanghao().toString());//房号
                map.put("resultdesc", etContent.getText().toString().trim());
                mPresenter.conmitFkyjInfo1(map);//业主大会的意见反馈接口
                break;
            }
        }*/
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("gongshiId", fkyjActivity.getGongshiId() + "");//公示ID
        map.put("expect", Contains.appYezhuFangwus.get(Contains.curFangwu).getXiangmuLoupan());//如果没有就传项目名
        map.put("building", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoudong());//楼栋
        map.put("unit", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwDanyuan());//单元
        map.put("roomNumber", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwFanghao().toString());//房号
        map.put("resultdesc", etContent.getText().toString().trim());
        map.put("resultType", fkyjActivity.getPosition() + "");
        mPresenter.conmitFkyjInfo1(map);//业主大会的意见反馈接口

    }
}