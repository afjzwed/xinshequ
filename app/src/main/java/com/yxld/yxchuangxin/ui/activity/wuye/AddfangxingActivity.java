package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.PopWindowUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.AddFangxing;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerAddfangxingComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.AddfangxingContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.AddfangxingModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.AddfangxingPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.AddFangxingAdapter;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
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
 * @Description: $description
 * @date 2017/06/13
 */

public class AddfangxingActivity extends BaseActivity implements AddfangxingContract.View {

    @Inject
    AddfangxingPresenter mPresenter;

    @Inject
    AddFangxingAdapter addFangxingAdapter;

    @BindView(R.id.add_fangxing)
    Button addFangxing;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.recycerView)
    RecyclerView recycerView;
    private View notDataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_addfangxing);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        notDataView = getLayoutInflater().inflate(R.layout.layout_empty_fangxing, (ViewGroup) recycerView.getParent(), false);
    }

    @Override
    protected void initData() {
        addFangxingAdapter.setEmptyView(notDataView);
        addFangxingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                showAddPopReSet(recycerView, i);
            }
        });
        recycerView.setAdapter(addFangxingAdapter);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerAddfangxingComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .addfangxingModule(new AddfangxingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(AddfangxingContract.AddfangxingContractPresenter presenter) {
        mPresenter = (AddfangxingPresenter) presenter;
    }


    List<AddFangxing> list = new ArrayList<>();
    int count = 1;

    private void showAddPop(View v) {
        //重置count
        count = 1;
        View view = LayoutInflater.from(this).inflate(R.layout.pop_add_fangxing, null);
        AutoLinearLayout ll = (AutoLinearLayout) view.findViewById(R.id.content);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });
        EditText etName = (EditText) view.findViewById(R.id.et_name);
        EditText etRemark = (EditText) view.findViewById(R.id.et_remark);
        TextView tvCancal = (TextView) view.findViewById(R.id.tv_cacanl);
        AutoRelativeLayout ivJian = (AutoRelativeLayout) view.findViewById(R.id.iv_jian);
        AutoRelativeLayout ivJia = (AutoRelativeLayout) view.findViewById(R.id.iv_jia);
        TextView tvCount = (TextView) view.findViewById(R.id.tv_count);
        StringUitl.setInputName(etName);
        StringUitl.setEditTextInhibitInputSpeOnlyChnese50(etRemark);
        tvCancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow.onBackPressed();
            }
        });
        TextView tvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUitl.isNotEmpty(AddfangxingActivity.this, etName.getText().toString(), "请输入物品名称")) {
                    if (StringUitl.isNotEmpty(AddfangxingActivity.this, etRemark.getText().toString(), "请输入备注")) {
                        AddFangxing item = new AddFangxing();
                        item.setName(etName.getText().toString());
                        item.setCount(tvCount.getText().toString());
                        item.setReMark(etRemark.getText().toString());
                        list.add(item);
                        addFangxingAdapter.setNewData(list);
                        CustomPopWindow.onBackPressed();
                    }
                }
            }
        });
        ivJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 1) {
                    ToastUtil.show(AddfangxingActivity.this, "最少一件物品哦");
                } else {
                    count = count - 1;
                    tvCount.setText(count + "");
                }
            }
        });
        ivJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count + 1;
                tvCount.setText(count + "");
            }
        });
        PopWindowUtil.showAddFangxingPop(this, v, view);
    }
    private void showAddPopReSet(View v, int position) {
        //重置count
        count = Integer.parseInt(addFangxingAdapter.getData().get(position).getCount());
        View view = LayoutInflater.from(this).inflate(R.layout.pop_add_fangxing, null);
        AutoLinearLayout ll = (AutoLinearLayout) view.findViewById(R.id.content);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });
        EditText etName = (EditText) view.findViewById(R.id.et_name);
        EditText etRemark = (EditText) view.findViewById(R.id.et_remark);
        StringUitl.setInputName(etName);
        StringUitl.setEditTextInhibitInputSpeOnlyChnese50(etRemark);
        TextView tvCancal = (TextView) view.findViewById(R.id.tv_cacanl);
        AutoRelativeLayout ivJian = (AutoRelativeLayout) view.findViewById(R.id.iv_jian);
        AutoRelativeLayout ivJia = (AutoRelativeLayout) view.findViewById(R.id.iv_jia);
        TextView tvCount = (TextView) view.findViewById(R.id.tv_count);
        etName.setText(addFangxingAdapter.getData().get(position).getName());
        etRemark.setText(addFangxingAdapter.getData().get(position).getReMark());
        tvCount.setText(count + "");
        tvCancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow.onBackPressed();
            }
        });
        TextView tvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUitl.isNotEmpty(AddfangxingActivity.this, etName.getText().toString(), "请输入物品名称")) {
                    if (StringUitl.isNotEmpty(AddfangxingActivity.this, etRemark.getText().toString(), "请输入备注")) {
                        AddFangxing item = new AddFangxing();
                        item.setName(etName.getText().toString());
                        item.setCount(tvCount.getText().toString());
                        item.setReMark(etRemark.getText().toString());
                        list.set(position, item);
                        addFangxingAdapter.notifyDataSetChanged();
                        CustomPopWindow.onBackPressed();
                    }
                }
            }
        });
        ivJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    ToastUtil.show(AddfangxingActivity.this, "最少一件物品哦");
                } else {
                    count = count - 1;
                    tvCount.setText(count + "");
                }
            }
        });
        ivJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count + 1;
                tvCount.setText(count + "");
            }
        });
        PopWindowUtil.showAddFangxingPop(this, v, view);
    }

    @OnClick({R.id.add_fangxing, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_fangxing:
                showAddPop(addFangxing);
                break;
            case R.id.submit:
                if (addFangxingAdapter.getData().size() == 0) {
                    ToastUtil.show(this, "您还没有选择物品");
                    return;
                }
                //提交
                //yezhuId  xiangmuId  fwId  obj（物品信息数组）
                Map<String, String> map = new HashMap<>();
                map.put("yezhuId", Contains.appYezhuFangwus.get(Contains.curFangwu).getYezhuId() + "");
                map.put("xiangmuId", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoupanId() + "");
                map.put("fwId", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwId() + "");
                ArrayList<Map<String, String>> list = new ArrayList();
                for (int i = 0; i < addFangxingAdapter.getData().size(); i++) {
                    map.put("list[" + i + "].proName", addFangxingAdapter.getData().get(i).getName());
                    map.put("list[" + i + "].number", addFangxingAdapter.getData().get(i).getCount());
                    map.put("list[" + i + "].content", addFangxingAdapter.getData().get(i).getReMark());
                }
                mPresenter.comfirmAccredit(map);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (CustomPopWindow.onBackPressed()) {
            return;
        }
        super.onBackPressed();
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
    public void onComfirmSuccess() {
        ToastUtil.show(this, "提交成功");
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}