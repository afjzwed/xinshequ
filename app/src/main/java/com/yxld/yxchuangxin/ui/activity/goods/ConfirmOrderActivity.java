package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.contain.PayContain;
import com.yxld.yxchuangxin.entity.CxwyMallAdd;
import com.yxld.yxchuangxin.entity.IsNight;
import com.yxld.yxchuangxin.entity.OrderRemainDianZiQuanEntity;
import com.yxld.yxchuangxin.entity.SureOrderEntity;
import com.yxld.yxchuangxin.entity.goods.BaseEntityAll;
import com.yxld.yxchuangxin.entity.goods.DiZiJuanGuiZei;
import com.yxld.yxchuangxin.entity.goods.ShopDianZiJuan;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerConfirmOrderComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.ConfirmOrderContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.ConfirmOrderModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.ConfirmOrderPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.ConfirmOrderAdapter;
import com.yxld.yxchuangxin.view.ConfirmOrderSelectView;
import com.yxld.yxchuangxin.view.OrderAddressView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yxld.yxchuangxin.ui.activity.goods.OrderListActivity.IS_ZITI;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/22 11:07:51
 */

public class ConfirmOrderActivity extends BaseActivity implements ConfirmOrderContract.View {
    public static final int CODE_REQUEST_ADDRESS = 0x000010;
    public static final String ENTER_TYPE = "enter_type";//从购物车界面 1  商品详情界面2 进来
    public static final int CODE_REQUEST_ADDRESS_DELETE = 0x000011;
    @Inject
    ConfirmOrderPresenter mPresenter;
    @BindView(R.id.recycler_confirm_order)
    RecyclerView recyclerConfirmOrder;
    @BindView(R.id.btn_confirm_order_to_balance)
    Button btnConfirmOrderToBalance;
    @BindView(R.id.tv_confirm_order_price)
    TextView tvConfirmOrderPrice;
    @BindView(R.id.tv_confirm_order_send_address_and_name)
    TextView tvConfirmOrderSendAddressAndName;
    @BindView(R.id.address_confirm_order)
    OrderAddressView addressConfirmOrder;
    @BindView(R.id.select_view_product_prices)
    ConfirmOrderSelectView selectViewProductPrices;
    @BindView(R.id.select_view_freight)
    ConfirmOrderSelectView selectViewFreight;
    @BindView(R.id.select_view_use_coupons)
    ConfirmOrderSelectView selectViewUseCoupons;
    @BindView(R.id.select_view_remain_coupons)
    ConfirmOrderSelectView selectViewRemainCoupons;
    @BindView(R.id.edit_order_bei_zhu)
    EditText editOrderBeiZhu;
    @BindView(R.id.tv_yunfei_desc)
    TextView tvYunfeiDesc;
    @BindView(R.id.select_view_mendian)
    ConfirmOrderSelectView SelectViewMendian;
    @BindView(R.id.tv_zuiduo)
    TextView mTvZuiduo;
    @BindView(R.id.radiobtn1)
    RadioButton mRadiobtn1;
    @BindView(R.id.radiobtn2)
    RadioButton mRadiobtn2;
    @BindView(R.id.radiogroup)
    RadioGroup mRadiogroup;
    private ConfirmOrderAdapter mAdapter;
    private Map<String, String> mRequestParams;

    /**
     * 总价  商品的总价
     */
    private double mTotalPrice;
    /**
     * 配送费 -1表示没加载到运费
     */
    private double mYunFei = -1;
    /**
     * 配送费+总价
     */
    private double mTotalAddYf;
    /**
     * 实付款 最后付款价格 配送费+总价-可以使用的电子券
     */
    private double mRealPay;

    /**
     * 使用的电子券数量
     */
    private int mUseDianZiQuanCount;
    /**
     * 总共可以使用的电子券数量 电子券余额
     */
    private int mTotalDianZiQuan;

    /**
     * 最多优惠的电子券数量
     */
    private int mRemainDianZiQuan;


    /**
     * 配送方式：1商城配送，2自提
     */

    private int mPeisongFangshi = 1;

    /**
     * 配送方式：门店是否支持 2为自提，1为商城配送，3是自提+配送
     */

    private int peisongMethod = 1;
    //private int voucherRuleMinPrice;
    /**
     * 电子券实体类
     *
     * @param savedInstanceState
     */
    private ShopDianZiJuan mShopDianZiJuan;

    /**
     * 所有的商品详情拼接成的字符串
     */
    private String mProductDetails;
    /**
     * 所有的商品名字拼接而成的字符串
     */
    private String mProductNames;
    /**
     * 是否是夜间
     */
    private boolean mIsNight;
    /**
     * 购物车进来 1  商品详情进来2
     */
    private String enterType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(Color.parseColor("#ff9934"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StringUitl.setEditTextInhibitInputSpeOnlyChnese50(editOrderBeiZhu);
    }

    @Override
    protected void initData() {
        enterType = getIntent().getStringExtra(ENTER_TYPE);
        mAdapter = new ConfirmOrderAdapter(Contains.sureOrderList);
        LinearLayoutManager manager = new LinearLayoutManager(ConfirmOrderActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerConfirmOrder.setLayoutManager(manager);
        recyclerConfirmOrder.setAdapter(mAdapter);
        initAddress();
        initViewData();
        initEvent();
        mTotalDianZiQuan = 0;
        mRemainDianZiQuan = 0;
        loadDianZiQuanFromServer();
    }

    private void initAddress() {
        Contains.confirmOrderAddress = Contains.defuleAddress;
    }

    private boolean isDZJon;

    private void initEvent() {


        selectViewProductPrices.setPrice(mTotalPrice);
        selectViewUseCoupons.setOnUseDianZiQuanListener(new ConfirmOrderSelectView.OnUseDianZiQuanListener() {
            @Override
            public void onUserDianZiQuanListener(SwitchCompat view, boolean isChecked) {
                if (isChecked) {
                    if (mTotalDianZiQuan <= 0) {
                        view.setChecked(false);
                        ToastUtil.show(ConfirmOrderActivity.this, "当前没有电子券");
                        return;
                    }
//                    else if (mTotalPrice < voucherRuleMinPrice) {
//
//                        view.setChecked(false);
//                        ToastUtil.show(ConfirmOrderActivity.this, "不满足使用电子券");
//                        return;
//                    }
                    isDZJon = true;
                    //加载电子券
                    Map<String, String> params = new HashMap<>();
                    params.put("money", mTotalPrice + "");
                    params.put("peisongfei", mYunFei + "");
                    params.put("uuid", Contains.uuid);
                    mPresenter.loadOrderRemainDianZiQuan(params);


                } else {
                    isDZJon = false;
                    mUseDianZiQuanCount = 0;
                    selectViewRemainCoupons.setDianZiQuanUesCount(mUseDianZiQuanCount);
                    setRealPay();
                    selectViewRemainCoupons.setVisibility(View.GONE);
                    mTvZuiduo.setVisibility(View.GONE);
                }
            }
        });
        mRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                boolean isMendianziti = false;
                if (mIsNight) {
                    mRadiobtn1.setChecked(true);
                    ToastUtil.show(ConfirmOrderActivity.this, "夜间支付不支持自提");
                    setRealPay();
                    return;
                }
                if (checkedId == mRadiobtn1.getId()) {
                    if (peisongMethod == 2) {
                        ToastUtil.show(ConfirmOrderActivity.this, "不支持商城配送");
                        mRadiobtn2.setChecked(true);
                        return;
                    }
                    //当前是商城配送
                    mPeisongFangshi = 1;
                    isMendianziti = false;

                } else if (checkedId == mRadiobtn2.getId()) {
                    //当前是门店自提
                    if (peisongMethod == 1) {
                        ToastUtil.show(ConfirmOrderActivity.this, "不支持门店自提");
                        mRadiobtn1.setChecked(true);
                        return;
                    }
                    mPeisongFangshi = 2;
                    isMendianziti = true;
                }
                if (mShopDianZiJuan != null) {
                    JiSuanYunfei(mShopDianZiJuan, isMendianziti);
                }
                if (isDZJon) {
                    Map<String, String> params = new HashMap<>();
                    params.put("money", mTotalPrice + "");
                    params.put("peisongfei", mYunFei + "");
                    params.put("uuid", Contains.uuid);
                    mPresenter.loadOrderRemainDianZiQuan(params);

                } else {
                    setRealPay();
                }
            }
        });
        SelectViewMendian.setOnUseDianZiQuanListener(new ConfirmOrderSelectView.OnUseDianZiQuanListener() {
            @Override
            public void onUserDianZiQuanListener(SwitchCompat view, boolean isChecked) {
                if (mIsNight) {
                    view.setChecked(false);
                    ToastUtil.show(ConfirmOrderActivity.this, "夜间支付不支持自提");
                    setRealPay();
                    return;
                }
                if (isChecked) {
                    if (peisongMethod == 1) {
                        ToastUtil.show(ConfirmOrderActivity.this, "不支持门店自提");
                        view.setChecked(false);
                        return;
                    }
                    mPeisongFangshi = 2;
                    SelectViewMendian.setuDesc("门店自提");
                } else {
                    if (peisongMethod == 2) {
                        ToastUtil.show(ConfirmOrderActivity.this, "不支持商城配送");
                        view.setChecked(true);
                        return;
                    }
                    mPeisongFangshi = 1;
                    SelectViewMendian.setuDesc("商城配送");
                }
                if (mShopDianZiJuan != null) {
                    JiSuanYunfei(mShopDianZiJuan, isChecked);
                }
                if (isDZJon) {
                    Map<String, String> params = new HashMap<>();
                    params.put("money", mTotalPrice + "");
                    params.put("peisongfei", mYunFei + "");
                    params.put("uuid", Contains.uuid);
                    mPresenter.loadOrderRemainDianZiQuan(params);

                } else {
                    setRealPay();
                }

            }
        });


        selectViewRemainCoupons.setOnDianZiQuanCountChanged(new ConfirmOrderSelectView.OnDianZiQuanCountChanged()

        {
            @Override
            public void onDianZiQuanMinus() {
                // if (mUseDianZiQuanCount == 0 || (mRealPay - 1) < 0) {
                if (mUseDianZiQuanCount == 0) {
                    ToastUtil.show(ConfirmOrderActivity.this, getResources().getString(R.string.cannot_minus_cart));
                    return;
                }
                mUseDianZiQuanCount--;
                selectViewRemainCoupons.setDianZiQuanUesCount(mUseDianZiQuanCount);
                setRealPay();
            }

            @Override
            public void onDianZiQuanPlus() {
                if (mUseDianZiQuanCount >= mRemainDianZiQuan) {
                    // ToastUtil.show(ConfirmOrderActivity.this, "没有剩余的电子券了，客官");
                    ToastUtil.show(ConfirmOrderActivity.this, "抱歉,当前订单限制使用" + mRemainDianZiQuan + "张");
                    return;
                }

                if (mUseDianZiQuanCount >= mTotalDianZiQuan) {
                    ToastUtil.show(ConfirmOrderActivity.this, "抱歉,当前订单限制使用" + mTotalDianZiQuan + "张");
                    return;
                }
                mUseDianZiQuanCount++;
                selectViewRemainCoupons.setDianZiQuanUesCount(mUseDianZiQuanCount);
                setRealPay();
            }

            @Override
            public void onDianZiQuanInput(String num) {
                if (TextUtils.isEmpty(num)) {
                    return;
                }

                if (!num.matches("\\d+")) {
                    ToastUtil.show(ConfirmOrderActivity.this, "请输入正确的格式");
                    selectViewRemainCoupons.setDianZiQuanUesCount(0);
                    return;
                }

                int count = Integer.parseInt(num);
                if (count > mRemainDianZiQuan) {
                    ToastUtil.show(ConfirmOrderActivity.this, "没有剩余的电子券了，客官");
                    selectViewRemainCoupons.setDianZiQuanUesCount(0);
                    return;
                }

                if (count > mTotalDianZiQuan) {
                    ToastUtil.show(ConfirmOrderActivity.this, "抱歉,当前订单限制使用" + mTotalDianZiQuan + "张");
                    selectViewRemainCoupons.setDianZiQuanUesCount(0);
                    return;
                }
                mUseDianZiQuanCount = count;
                setRealPay();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Contains.confirmOrderAddress != null) {
            setAddress(Contains.confirmOrderAddress);
        } else {
            addressConfirmOrder.setAddress(null, null, null);
        }
    }

    private void setAddress(CxwyMallAdd add) {
        if (add != null) {
            addressConfirmOrder.setAddress(add.getAddName(), add.getAddTel(), add.getAddAdd());
            KLog.i("修改后的地址为:" + add.getAddAdd());
            tvConfirmOrderSendAddressAndName.setText("送至: " + add.getAddAdd() + " " + add.getAddName() + " 收");
        } else {
            addressConfirmOrder.setAddress(null, null, null);
        }
    }

    private void initViewData() {
        StringBuilder sbGoodsDetails = new StringBuilder();
        StringBuilder sbGoodsNames = new StringBuilder();
        mRequestParams = new HashMap<>(16);
        double totalPrice = 0;
        for (int i = 0, size = Contains.sureOrderList.size(); i < size; i++) {
            SureOrderEntity entity = Contains.sureOrderList.get(i);

            //是否大件
            mRequestParams.put("carLists[" + i + "].cartIsDajian", entity.getIsDajianGoods());
            //是否夜间
            mRequestParams.put("carLists[" + i + "].cartIsYejian", entity.getIsYejian() + "");
            //商品数量
            mRequestParams.put("carLists[" + i + "].cartNum", entity.getGoodsNum() + "");
            //商品id
            mRequestParams.put("carLists[" + i + "].cartSpbianhao", entity.getGoodsId());
            mRequestParams.put("carLists[" + i + "].cartSpmingcheng", entity.getGoodsShop());
            mRequestParams.put("carLists[" + i + "].cartSpdanjia", entity.getGoodsRmb());

            double onePrice = Double.parseDouble(entity.getGoodsRmb());
            int num = Integer.parseInt(entity.getGoodsNum());
            totalPrice += (onePrice * num);

            sbGoodsDetails.append(entity.getGoodsDetails());

        }
        if (Contains.sureOrderList.size() > 1) {
            mProductNames = (Contains.sureOrderList.get(0).getGoodsShop() + "...等");
        } else {
            mProductNames = (Contains.sureOrderList.get(0).getGoodsShop());
        }
        mProductDetails = sbGoodsDetails.toString();
        mTotalPrice = totalPrice;
    }

    @Override
    protected void setupActivityComponent() {
        DaggerConfirmOrderComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .confirmOrderModule(new ConfirmOrderModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(ConfirmOrderContract.ConfirmOrderContractPresenter presenter) {
        mPresenter = (ConfirmOrderPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }


    @OnClick({R.id.address_confirm_order, R.id.btn_confirm_order_to_balance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address_confirm_order:
                Intent intent = new Intent(ConfirmOrderActivity.this, AddressManageActivity.class);
                intent.putExtra(AddressManageActivity.KEY_IN_TYPE, AddressManageActivity.IN_TYPE_ODRDER_CONFIRM);
                startActivityForResult(intent, CODE_REQUEST_ADDRESS);

                break;
            case R.id.btn_confirm_order_to_balance:
                if (checkValid()) {
                    addOneOrder();
                }
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_REQUEST_ADDRESS) {
            setAddress(Contains.confirmOrderAddress);
        }
    }

    /**
     * 添加订单
     */
    private void addOneOrder() {
        mRequestParams.put("shouhuorenMing", Contains.confirmOrderAddress.getAddName());
        mRequestParams.put("shouhuoDizhi", Contains.confirmOrderAddress.getAddAdd());
        mRequestParams.put("shouhuoDianhua", Contains.confirmOrderAddress.getAddTel());
        mRequestParams.put("zongjine", mTotalPrice + "");
        mRequestParams.put("dianziquan", mUseDianZiQuanCount + "");
        mRequestParams.put("beizhu", editOrderBeiZhu.getText().toString() + "");
        mRequestParams.put("peisongFangshi", mPeisongFangshi + "");
        mRequestParams.put("uuid", Contains.uuid);
        mRequestParams.put("type", enterType);
        mRequestParams.put("peisongfei", mYunFei + "");
        showProgressDialog();
        mPresenter.addMallOrder(mRequestParams);
    }

    /**
     * 检测金额是否合法
     *
     * @return
     */
    private boolean checkValid() {

        if (mTotalPrice < 0 || mRealPay < 0) {
            ToastUtil.show(ConfirmOrderActivity.this, "金额不能为负数");
            return false;
        }
        if (Contains.confirmOrderAddress == null) {
            ToastUtil.show(ConfirmOrderActivity.this, "收货地址不能为空");
            return false;
        }
        if (!TextUtils.isEmpty(editOrderBeiZhu.getText().toString()) && editOrderBeiZhu.getText().toString().length() > 45) {
            ToastUtil.show(ConfirmOrderActivity.this, "请输入45个字以内的备注");
            return false;
        }

        if (mUseDianZiQuanCount > mRemainDianZiQuan
                || mUseDianZiQuanCount > mTotalDianZiQuan
                || TextUtils.isEmpty(selectViewRemainCoupons.getDianZiQuanUseCount())) {
            ToastUtil.show(ConfirmOrderActivity.this, "电子券使用失败,请重新输入电子券数量");
            return false;
        }

        return true;
    }

    private void loadDianZiQuanFromServer() {
        showProgressDialog();
        mPresenter.loadDianZiQuan();
        mPresenter.isNight();
    }

    private String calculateRealPay() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        if (mYunFei == -1) {
            mRealPay = mTotalPrice - mUseDianZiQuanCount;
        } else {
            mRealPay = mTotalPrice + mYunFei - mUseDianZiQuanCount;
        }
        if (mRealPay <= 0) {
            return "0.01";
        }
        return decimalFormat.format(mRealPay);
    }

    private String TotalAddYunfei() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        if (mYunFei == -1) {
            mTotalAddYf = mTotalPrice;
        } else {
            mTotalAddYf = mTotalPrice + mYunFei;
        }
        return decimalFormat.format(mTotalAddYf);
    }

    private void setRealPay() {
        tvConfirmOrderPrice.setText("¥ " + calculateRealPay());
    }

    @Override
    public void onLoadDianZiQuanSucceed(ShopDianZiJuan dzq) {
        closeProgressDialog();
        if (dzq.getStatus() == 1) {
            peisongMethod = dzq.getRows().getPeisongfeiRule().getPeisongMethod();
            if (peisongMethod == 1) {
                mRadiobtn1.setChecked(true);
            } else if (peisongMethod == 2) {
                mRadiobtn2.setChecked(true);
            } else {
                mRadiobtn1.setChecked(true);
            }
            mTotalDianZiQuan = dzq.getRows().getBalance();
//            mRemainDianZiQuan = (int) dzq.getRows().getVoucherRule().getVoucherRuleUsePrice();
//            voucherRuleMinPrice = (int) dzq.getRows().getVoucherRule().getVoucherRuleMinPrice();
//            mRemainDianZiQuan = (int) Math.floor(mTotalPrice / voucherRuleMinPrice) * mRemainDianZiQuan;
//            KLog.i("当前总价：" + mTotalPrice + "满足多多少使用" + voucherRuleMinPrice + "可以使用的电子卷" + mRemainDianZiQuan);
            mShopDianZiJuan = dzq;
            JiSuanYunfei(dzq, mRadiobtn2.isChecked());
            setRealPay();
        } else {
            onError(dzq.MSG, dzq.getStatus());
        }
    }

    @Override
    public void onLoadOrderRemainDianZiQuanSucceed(DiZiJuanGuiZei entity) {
        if (entity.getStatus() == 1) {
            tvYunfeiDesc.setText(psfText + "\n电子券规则：" + entity.getRows().getExplain());
            mRemainDianZiQuan = entity.getRows().getUseTicket();
            selectViewRemainCoupons.setVisibility(View.VISIBLE);
            selectViewRemainCoupons.setDianZiQuanShengYuDesc(mTotalDianZiQuan);
            mTvZuiduo.setVisibility(View.VISIBLE);
            mTvZuiduo.setText("最多使用" + mRemainDianZiQuan + "张");
            //设置默认使用最大的值
            mUseDianZiQuanCount = mRemainDianZiQuan;
            selectViewRemainCoupons.setDianZiQuanUesCount(mUseDianZiQuanCount);
            if (mUseDianZiQuanCount > mRemainDianZiQuan) {
                mUseDianZiQuanCount = mRemainDianZiQuan;
                selectViewRemainCoupons.setDianZiQuanUesCount(mUseDianZiQuanCount);
            }
            setRealPay();
        } else {
            selectViewUseCoupons.setSwitchChecked(false);
            onError(entity.MSG, entity.status);
        }
    }

    private String psfText;

    private void JiSuanYunfei(ShopDianZiJuan dzq, boolean isMendianZiti) {
        double freeYunfei = dzq.getRows().getPeisongfeiRule().getFreePrice();
        double mBigGoodsYunFei = dzq.getRows().getPeisongfeiRule().getMaxPeisongPrice();
        double mNormalGoodsYunFei = dzq.getRows().getPeisongfeiRule().getMinPeisongPrice();
        boolean flag = false;
        for (int i = 0, size = Contains.sureOrderList.size(); i < size; i++) {
            SureOrderEntity entity = Contains.sureOrderList.get(i);
            if ("1".equals(entity.getIsDajianGoods())) {
                flag = true;
                break;
            }
        }
        if (flag) {
            mYunFei = mBigGoodsYunFei;
        } else if (!flag && mTotalPrice >= freeYunfei) {
            mYunFei = 0;
        } else {
            mYunFei = mNormalGoodsYunFei;
        }
        if (isMendianZiti) {
            mYunFei = 0;
        }

        selectViewFreight.setPrice(mYunFei);
        psfText = String.format(getResources().getString(R.string.confirm_order_explain)
                , new Object[]{freeYunfei + "", mNormalGoodsYunFei + "", mBigGoodsYunFei + ""});
        tvYunfeiDesc.setText(psfText);
    }


    @Override
    public void onAddOrderSuccess(OrderRemainDianZiQuanEntity entity) {
        closeProgressDialog();
        if (entity.getStatus() == 1) {
            //发送广播更新购物车
            //  sendBroadcast(new Intent(getResources().getString(R.string.add_shop_cart)));
            //跳转
            if (mYunFei == -1) {
                ToastUtil.show(ConfirmOrderActivity.this, "加载配送费失败请重新加载");
                return;
            }
            String rows = entity.getRows();
            //   mPresenter.getOrder(rows);
            Intent intent = new Intent(ConfirmOrderActivity.this, PayWaySelectActivity.class);
            intent.putExtra("orderId", rows);
            intent.putExtra("orderMoney", calculateRealPay());
            intent.putExtra("orderShop", mProductNames);
            intent.putExtra("orderBianhao", rows);
            intent.putExtra("orderDetails", mProductDetails);
            intent.putExtra(PayWaySelectActivity.KEY_IN_TYPE, PayWaySelectActivity.IN_TYPE_CONFROM_ORDER);
            PayContain.requestPayModule = PayContain.MODULE_MALL_ORDER;
            startActivity(intent);
            //设置是否自提还是商城配送 来确定跳转界面 配送方式：1商城配送，2自提 */
            IS_ZITI = mPeisongFangshi;
            //关闭购物车页面
            AppConfig.getInstance().mAppActivityManager.finishActivity(SecondShopCartActivity.class);
            //关闭分类菜单界面
            AppConfig.getInstance().mAppActivityManager.finishActivity(GoodsFenLeiActivity.class);
            //关闭分类菜单界面
            AppConfig.getInstance().mAppActivityManager.finishActivity(GoodDetailActivity.class);
            finish();
        } else {
            onError(entity.MSG, entity.status);
        }
    }

    /**
     * 这个借口没掉
     *
     * @param baseEntityAll
     */
    @Override
    public void onLoadOrderSuccess(BaseEntityAll baseEntityAll) {
        if (baseEntityAll.getStatus() == 1) {
            Intent intent = new Intent(ConfirmOrderActivity.this, PayWaySelectActivity.class);
            intent.putExtra("orderId", baseEntityAll.getRows());
            intent.putExtra("orderMoney", mRealPay + "");
            intent.putExtra("orderShop", mProductNames);
            intent.putExtra("orderBianhao", baseEntityAll.getRows());
            intent.putExtra("orderDetails", mProductDetails);
            intent.putExtra(PayWaySelectActivity.KEY_IN_TYPE, PayWaySelectActivity.IN_TYPE_CONFROM_ORDER);
            //设置是否自提还是商城配送 来确定跳转界面 配送方式：1商城配送，2自提 */
            IS_ZITI = mPeisongFangshi;
            PayContain.requestPayModule = PayContain.MODULE_MALL_ORDER;
            startActivity(intent);
            //关闭购物车页面
            AppConfig.getInstance().mAppActivityManager.finishActivity(SecondShopCartActivity.class);
            //关闭分类菜单界面
            AppConfig.getInstance().mAppActivityManager.finishActivity(GoodsFenLeiActivity.class);
            //关闭分类菜单界面
            AppConfig.getInstance().mAppActivityManager.finishActivity(GoodDetailActivity.class);
            finish();
        } else {
            onError(baseEntityAll.getMsg(), baseEntityAll.getStatus());
        }
    }

    @Override
    public void onLoadDataFailed(String msg) {
        closeProgressDialog();
        ToastUtil.show(ConfirmOrderActivity.this, msg);
    }

    @Override
    public void onLoadDataFailed() {
        onLoadDataFailed(getResources().getString(R.string.loading_failed_1));
    }

    @Override
    public void setIsNight(IsNight isNight) {
        if (isNight.getStatus() == 1) {
            if ("夜间".equals(isNight.getRows())) {
                mIsNight = true;
            } else {
                mIsNight = false;
            }
        } else {
            onError(isNight.getMsg(), isNight.getStatus());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
        mPresenter = null;
        Contains.confirmOrderAddress = null;
        mAdapter = null;
        mRequestParams.clear();
        mRequestParams = null;
    }
}