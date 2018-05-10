package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.goods.MallNewOrderDetails;
import com.yxld.yxchuangxin.ui.activity.base.ViewPagerActivity;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerGoodsSaleComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.GoodsSaleContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.GoodsSaleModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.GoodsSalePresenter;
import com.yxld.yxchuangxin.ui.activity.index.util.Bimp;
import com.yxld.yxchuangxin.ui.adapter.goods.GoodsSaleListAdapter;
import com.yxld.yxchuangxin.ui.adapter.wuye.GridAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.refactor.library.SmoothCheckBox;

import static com.yxld.yxchuangxin.ui.activity.goods.OrderListPartitionFragment.CODE_REQUEST_SHOUHOU;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/10/23 10:36:05
 */

public class GoodsSaleActivity extends BaseActivity implements GoodsSaleContract.View, View.OnTouchListener {

    @Inject
    GoodsSalePresenter mPresenter;
    @Inject
    GridAdapter mGridAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_leixing)
    TextView mTvLeixing;
    @BindView(R.id.et_reason)
    EditText mEtReason;
    @BindView(R.id.recycler_view_image)
    RecyclerView mRecyclerViewImage;
    @BindView(R.id.chc_quanxuan)
    SmoothCheckBox mChcQuanxuan;
    @BindView(R.id.btn_commit)
    Button mBtnCommit;
    private String orderId;
    private List<MallNewOrderDetails> mMallNewOrderDetailsList;

    private GoodsSaleListAdapter mGoodsSaleListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_goods_sale);
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(Color.parseColor("#ff9934"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        mPresenter.init();
        Intent intent = getIntent();
        String orderId = intent.getStringExtra("orderId");
        mMallNewOrderDetailsList = intent.getParcelableArrayListExtra("list");
        mGoodsSaleListAdapter = new GoodsSaleListAdapter(this, mMallNewOrderDetailsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mGoodsSaleListAdapter);
        mRecyclerViewImage.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerViewImage.setAdapter(mGridAdapter);
        setEvent();

    }

    private void setEvent() {
        mEtReason.setOnTouchListener(this);
        mChcQuanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoodsSaleListAdapter.setAllChecked(!mChcQuanxuan.isChecked());
                mGoodsSaleListAdapter.notifyDataSetChanged();
            }
        });
        mGridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (Bimp.tempShouhouSelectBitmap.get(position).isSelected()) {
                    Intent intent = new Intent(GoodsSaleActivity.this, ViewPagerActivity.class);
                    intent.putExtra("item", position);
                    intent.putExtra("url", "");
                    intent.putExtra("url1", "3");
                    startActivity(intent);
                } else {
                    showImgPickPop(position);
                }
            }
        });
        mGridAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bimp.tempShouhouSelectBitmap.get(position).setSelected(false);
                mGridAdapter.notifyDataSetChanged();
            }
        });

    }

    /**
     * 售后类型弹窗选项
     * 售后类型：1.换货 2.退款 3.其他
     */
    private int SHOUHOULEIXING = -1;

    private void showLeiXingWindow() {
        new AlertView("售后类型", null, "取消", null,
                new String[]{"换货", "退款", "其他"},
                this, AlertView.Style.ActionSheet, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                AlertView alertView = (AlertView) o;
                switch (position) {
                    case 0:
                        mTvLeixing.setText("换货");
                        SHOUHOULEIXING = 1;
                        break;
                    case 1:
                        mTvLeixing.setText("退款");
                        SHOUHOULEIXING = 2;
                        break;
                    case 2:
                        mTvLeixing.setText("其他");
                        SHOUHOULEIXING = 3;
                        break;
                    default:
                        alertView.dismiss();
                        break;
                }
            }
        }).show();
    }

    /**
     * 照片弹窗选项
     */
    private void showImgPickPop(int id) {
        new AlertView.Builder().setContext(this)
                .setStyle(AlertView.Style.ActionSheet)
                .setTitle("上传文件")
                .setMessage(null)
                .setCancelText("取消")
                .setOthers(new String[]{"拍照", "从相册中选择"})
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        switch (position) {
                            case 0:
                                mPresenter.fromCameraUpLoad(id);
                                break;
                            case 1:
                                mPresenter.fromAlbumUpLoad(id);
                                break;
                            default:
                                break;
                        }
                    }
                })
                .build()
                .setCancelable(false)
                .show();
    }

    @Override
    protected void setupActivityComponent() {
        DaggerGoodsSaleComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .goodsSaleModule(new GoodsSaleModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(GoodsSaleContract.GoodsSaleContractPresenter presenter) {
        mPresenter = (GoodsSalePresenter) presenter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 表示条目按钮有一个没选 设置全选按钮为false
     */
    @Override
    public void onOneCheckBoxNotChecked() {
        if (mChcQuanxuan.isChecked()) {
            mChcQuanxuan.setChecked(false, true);
        }
    }

    /**
     * 条目按钮都选了 设置全选按钮为true
     *
     * @param allChecked
     */
    @Override
    public void onAllChecked(boolean allChecked) {
        mChcQuanxuan.setChecked(allChecked, true);
    }

    /**
     * 选完照片更新
     */
    @Override
    public void onPickImgBack() {
        mGridAdapter.notifyDataSetChanged();
    }

    /**
     * 上传照片成功
     *
     * @param uploadImgUrl
     */
    @Override
    public void onUpLoadSuccess(String uploadImgUrl) {
        Map<String, String> map = new HashMap<>(16);
        map.put("uuid", Contains.uuid);
        // 订单详情id  1,2,3
        map.put("OrderDetailId", OrderDetailId);
        //售后类型：1.换货 2.退款 3.其他
        map.put("shouhouLeixing", SHOUHOULEIXING + "");
        //售后图片
        String uploadImgUrl1 = "";
        if (uploadImgUrl.contains(",")) {
            uploadImgUrl1 = uploadImgUrl.substring(0, uploadImgUrl.length() - 1);
        }
        KLog.i(uploadImgUrl + "去除逗号uploadImgUrl" + uploadImgUrl1);
        map.put("shouhouTupian", uploadImgUrl1);
        //售后原因
        map.put("shouhouYuanyin", mEtReason.getText().toString().trim());

        mPresenter.postShouhou(map);
    }

    /**
     * 提交数据到服务器成功
     */
    @Override
    public void postShouhouSuccess() {
        Bimp.tempShouhouSelectBitmap.clear();
        ToastUtil.show(this, "提交成功，我们会尽快处理");
        setResult(CODE_REQUEST_SHOUHOU);
        finish();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }


    @OnClick({R.id.tv_leixing, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_leixing:
                showLeiXingWindow();
                break;
            case R.id.btn_commit:
                //// TODO: 2017/10/24  还有订单详情id没确定
                OrderDetailId = getId();
                if ("".equals(OrderDetailId)) {
                    ToastUtil.show(GoodsSaleActivity.this, "请选择商品");
                    return;
                }
                KLog.i("OrderDetailId--------------" + OrderDetailId);
                if (SHOUHOULEIXING == -1) {
                    ToastUtil.show(GoodsSaleActivity.this, "请选择售后类型");
                    return;
                }
                if (mEtReason.getText() == null || "".equals(mEtReason.getText().toString())) {
                    ToastUtil.show(GoodsSaleActivity.this, "请输入售后原因");
                    return;
                }
                if (mEtReason.getText().toString().length() > 200) {
                    ToastUtil.show(GoodsSaleActivity.this, "最多输入两百字");
                    return;
                }
                int imgCount = 0;
                for (int i = 0; i < 3; i++) {
                    if (Bimp.tempShouhouSelectBitmap.get(i).isSelected()) {
                        imgCount++;
                    }
                }
                if (imgCount == 0) {
                    ToastUtil.show(GoodsSaleActivity.this, "请添加照片");
                    return;
                }
                //上传逻辑
                Bimp.imgCount = imgCount;
                mPresenter.upLoadImg();

                break;
            default:
                break;
        }
    }

    /**
     * 获取商品id；
     * //格式1，2，3
     */
    private String OrderDetailId = "";

    /**
     * 去除拼接的id中末尾的逗号
     *
     * @return
     */
    private String getId() {
        String str = "";
        List<MallNewOrderDetails> data = mGoodsSaleListAdapter.getData();
        for (MallNewOrderDetails details : data) {
            if (details.isChecked()) {
                str = str + details.getId() + ",";
            }
        }
        if (str.contains(",")) {
            return str.substring(0, str.length() - 1);
        } else {
            return "";
        }
    }

    /**
     * 解决edittext和scollview 的滑动冲突
     *
     * @param view
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
        if ((view.getId() == R.id.et_reason && canVerticalScroll(mEtReason))) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return false;
    }

    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动   false：不可以滚动
     */
    private boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if (scrollDifference == 0) {
            return false;
        }

        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }
}