package com.yxld.yxchuangxin.ui.activity.rim;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimComplainAddComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimComplainAddContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimComplainAddModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimComplainAddPresenter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: 欣周边 添加投诉
 * @date 2017/06/17
 */

public class RimComplainAddActivity extends BaseActivity implements RimComplainAddContract.View {

    @Inject
    RimComplainAddPresenter mPresenter;
    @BindView(R.id.tv_complaintType)
    EditText tvComplaintType;
    @BindView(R.id.linear_type)
    AutoLinearLayout linearType;
    @BindView(R.id.tv_complaintcontent)
    EditText tvComplaintcontent;
    @BindView(R.id.submit)
    Button submit;


    private View parentView;
    private PopupWindow pop = null;
    private AutoLinearLayout ll_popup ;
    private String compaintType;

    private String orderNumber;
    private String orderBusinessNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        parentView = getLayoutInflater().inflate(R.layout.rim_complaint_add,
                null);
        setContentView(parentView);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        initPopWindow();
        orderNumber = getIntent().getStringExtra("orderNumber");
        orderBusinessNumber = getIntent().getStringExtra("complainBusinessNumber");


        StringUitl.forbidEmoji(tvComplaintcontent,70,this);//限制输入字数和表情

//        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5_]");
//        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
//                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
//
//        InputFilter lengthFilter = new InputFilter.LengthFilter(70);
//        tvComplaintcontent.setFilters(new InputFilter[]{
//                new InputFilter() {
//                    @Override
//                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                        Matcher emojiMatcher = emoji.matcher(source);
//                        if (emojiMatcher.find()) {
//                            Toast.makeText(RimComplainAddActivity.this, "不支持输入表情", Toast.LENGTH_SHORT).show();
//                            return "";
//                        }
//                        return null;
//                    }
//                },lengthFilter
//        });
    }

    @Override
    protected void setupActivityComponent() {
        DaggerRimComplainAddComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .rimComplainAddModule(new RimComplainAddModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RimComplainAddContract.RimComplainAddContractPresenter presenter) {
        mPresenter = (RimComplainAddPresenter) presenter;
    }

    @OnClick({R.id.linear_type, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_type:
                ll_popup.startAnimation(AnimationUtils.loadAnimation(
                        RimComplainAddActivity.this,
                        R.anim.activity_translate_in));
//                Log.d("geek", "onViewClicked: pop"+pop);
//                Log.d("geek", "onViewClicked: parentView"+parentView);
                pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.submit:
                Map map = new HashMap();
                map.put("uuId", Contains.uuid);
                map.put("orderNumber",orderNumber);
                map.put("complainBusinessNumber",orderBusinessNumber);
                map.put("complainContent",tvComplaintcontent.getText().toString().trim());
                map.put("type",compaintType);
                if (TextUtils.isEmpty(compaintType)) {
                    ToastUtil.showShort("请选择投诉类型");
                    return;
                }
                if (TextUtils.isEmpty(tvComplaintcontent.getText().toString())) {
                    ToastUtil.showShort("请输入投诉内容");
                    return;
                }
//                Log.e("wh", "map " + map.toString());只能限制可以输入的内容为汉字,英文，数字
                mPresenter.addComplanData(map);
                break;
        }
    }

    @Override
    public void initPopWindow() {
        pop = new PopupWindow(this);
        View view = getLayoutInflater().inflate(R.layout.rim_complain_item_popupwindows,
                null);
        ll_popup = (AutoLinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        TextView goods = (TextView) view.findViewById(R.id.goods);
        TextView service = (TextView) view.findViewById(R.id.service);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        // 商品
        goods.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
                tvComplaintType.setText("商品");
                compaintType= "1";
            }
        });
        // 服务
        service.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
                tvComplaintType.setText("服务");
                compaintType = "2";
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
    }

    @Override
    public void requestSuccess() {
        ToastUtil.show(this, "投诉成功");
        finish();
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
    public void onError() {
        ToastUtil.show(this, "投诉失败");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rim_tslist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.tusu:
                startActivity(RimComplainListActivity.class);
                break;
            default:
                break;
        }
        return true;
    }
}