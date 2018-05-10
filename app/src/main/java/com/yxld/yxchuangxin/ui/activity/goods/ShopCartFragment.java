package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.CxwyMallCart;
import com.yxld.yxchuangxin.entity.SureOrderEntity;
import com.yxld.yxchuangxin.entity.goods.ShopCart;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerShopCartComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.ShopCartContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.ShopCartModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.ShopCartPresenter;
import com.yxld.yxchuangxin.ui.adapter.goods.ShopCartListAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.refactor.library.SmoothCheckBox;

import static com.yxld.yxchuangxin.ui.activity.goods.ConfirmOrderActivity.ENTER_TYPE;

/**
 * @author yuan
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: 购物车
 * @date 2017/06/14
 */

public class ShopCartFragment extends MyBaseFragment implements ShopCartContract.View, SwipeRefreshLayout.OnRefreshListener {
    public static final String KEY_IN_TYPE = "key_in_type";
    public static final int IN_TYPE_MAIN = 0x000001; //主页的购物车
    public static final int IN_TYPE_OTHER = 0x000002; //商品列表页和详情页进来的
    @Inject
    ShopCartPresenter mPresenter;
    @Inject
    ShopCartListAdapter mShopCartListAdapter;
    @BindView(R.id.bt_cart_order_ok)
    Button btCartOrderOk;
    @BindView(R.id.tv_cart_total_money)
    TextView tvCartTotalMoney;
    @BindView(R.id.recycerCartProducts)
    RecyclerView recycerCartProducts;
    @BindView(R.id.swipeLayouts)
    SwipeRefreshLayout swipeLayouts;
    @BindView(R.id.radio_all_checked)
    SmoothCheckBox radioAllChecked;
    @BindView(R.id.tv_cart_zongjia)
    TextView tvCartZongjia;
    private BroadcastReceiver mBroadcastReceiver;
    private int mInType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_cart, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if (bundle == null) {
            mInType = IN_TYPE_MAIN;
        } else {
            mInType = bundle.getInt(KEY_IN_TYPE, IN_TYPE_MAIN);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycerCartProducts.setLayoutManager(layoutManager);
        recycerCartProducts.setAdapter(mShopCartListAdapter);
        setRecyclerBottomDivider();
        setEvent();
        registerBroadcast();
    }


    @Override
    public void fetchData() {
        swipeLayouts.post(new Runnable() {
            @Override
            public void run() {
                loadCartProductsFromServer();
            }
        });
    }

    @Override
    public void onResume() {
        if (mInType == IN_TYPE_MAIN && Contains.hasCartChanged) {
            Contains.hasCartChanged = false;
            mShopCartListAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }

    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter(getContext().getResources().getString(R.string.add_shop_cart));
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                loadCartProductsFromServer();
            }
        };
        getContext().registerReceiver(mBroadcastReceiver, filter);
    }

    private void setRecyclerBottomDivider() {
        mShopCartListAdapter.setFooterView(UIUtils.getRecyclerBottomView(getContext()));
    }

    private void setEvent() {
        radioAllChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShopCartListAdapter.setAllChecked(!radioAllChecked.isChecked());
            }
        });


        mShopCartListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                int cartSpbianhao = mShopCartListAdapter.getData().get(i).getCartSpbianhao();
                int cartNum = mShopCartListAdapter.getData().get(i).getCartNum();
                switch ((String) view.getTag()) {
                    case "Detail-Title":
                        onProductTitleClick(cartSpbianhao);
                        break;
                    case "Plus":
                        onCartChangedCount(cartNum + 1, cartSpbianhao);
                        break;
                    case "Minus":
                        if (cartNum == 1) {
                            ToastUtil.show(getActivity(), "不能再少了");
                            return;
                        }
                        onCartChangedCount(cartNum - 1, cartSpbianhao);
                        break;
                    default:
                        break;
                }
            }
        });

        swipeLayouts.setOnRefreshListener(this);
        UIUtils.configSwipeRefreshLayoutColors(swipeLayouts);
    }

    private void onCartChangedCount(int num, int bianhao) {
        mPresenter.changeCartCount(num + "", bianhao + "");
    }

    private void onProductTitleClick(int bianhao) {
        Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
        intent.putExtra(GoodDetailActivity.KEY_PRODUCT_ID, bianhao + "");
        startActivity(intent);
        AppConfig.getInstance().mAppActivityManager.finishActivity(GoodDetailActivity.class);
    }

    private void loadCartProductsFromServer() {
        swipeLayouts.setRefreshing(true);
        radioAllChecked.setChecked(false);
        mPresenter.getShopCartProductsFromServer();
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerShopCartComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .shopCartModule(new ShopCartModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(ShopCartContract.ShopCartContractPresenter presenter) {
        mPresenter = (ShopCartPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getContext().unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        swipeLayouts.setRefreshing(false);
        mPresenter.unsubscribe();
        mPresenter = null;
        mShopCartListAdapter = null;
        KLog.i("Second", "Fragment Destroy");
    }

    @OnClick({R.id.bt_cart_order_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cart_order_ok:
                if ("结算".equals(btCartOrderOk.getText())) {
                    if (hasProductChecked()) {
                        if (!hasShiXiaoProductChecked()) {
                            toConformOrder();
                        }
                    } else {
                        Toast.makeText(getContext(), "没有可以结算的商品", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (hasProductChecked()) {
                    showDeletePop();}else{
                        Toast.makeText(getContext(), "没有可以删除的商品", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            default:
                break;
        }
    }

    private void toConformOrder() {
        if (hasRemain()) {
            assembleOrderList();
            Intent intent = new Intent(getContext(), ConfirmOrderActivity.class);
            intent.putExtra(ENTER_TYPE, "1");
            startActivity(intent);
            return;
        }
    }

    private boolean hasRemain() {
        for (ShopCart.ShapCartBean bean : mShopCartListAdapter.getData()) {
            if (bean.isChecked()) {
                if (bean.getCartNum() == 0) {
                    ToastUtil.show(getContext(), "商品缺货哦");
                    return false;
                }
            }
        }
        return true;
    }

    private void assembleOrderList() {
        Contains.sureOrderList.clear();
        for (ShopCart.ShapCartBean bean : mShopCartListAdapter.getData()) {
            if (bean.isChecked()) {
                SureOrderEntity entity = new SureOrderEntity(
                        bean.getCartSpbianhao() + "",
                        bean.getCartNum() + "",
                        bean.getCartAdminId() + "",
                        bean.getCartSpzhutu(),
                        bean.getCartSpdanjia() + "",
                        bean.getCartSpmingcheng(),
                        bean.getCartSpguige(),
                        bean.getCartIsDajian() + "",
                        bean.getCartIsYejian() + "");
                Contains.sureOrderList.add(entity);
            }
        }
    }

    private boolean hasProductChecked() {
        for (ShopCart.ShapCartBean bean : mShopCartListAdapter.getData()) {
            if (bean.isChecked()) {
                return true;
            }
        }
        return false;
    }

    private boolean hasShiXiaoProductChecked() {
        for (ShopCart.ShapCartBean bean : mShopCartListAdapter.getData()) {
            if (bean.isChecked()) {
                if (bean.getCartIsShangjia() == -1) {
                    ToastUtil.displayShortToast("所选商品存在已经失效的商品,请删除再重新下单");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void backShopCartProducts(CxwyMallCart products) {
        swipeLayouts.setRefreshing(false);
        if (products.getStatus() == BaseActivity.STATUS_CODE_OK) {

            Contains.cartTotalNum = products.getCart().size();
            Contains.CartList.clear();
            Contains.CartList.addAll(products.getCart());

            mShopCartListAdapter.notifyDataSetChanged();
            setCheckedTotalMoney();
            onOneCheckBoxNotChecked();
            if (products.getCart().size() == 0) {
                if (isResumed()) {
                    Toast.makeText(getActivity(), "空空如也", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Contains.cartTotalNum = 0;
            Contains.CartList.clear();
            Contains.cartTotalNum = products.getCart().size();
            onError(products.MSG, products.status);
        }


    }

    @Override
    public void onChangedCartCountSucceed(BaseEntity baseEntity, int pos, int count) {
        swipeLayouts.setRefreshing(false);
        if (baseEntity.status == STATUS_CODE_OK) {
            CxwyMallCart product = Contains.CartList.get(pos);
            product.setCartNum(String.valueOf(count));
            mShopCartListAdapter.notifyItemChanged(pos);
            setCheckedTotalMoney();
        } else {
            onError(baseEntity.MSG, baseEntity.status);
        }

    }

    @Override
    public void onDeleteCartSucceed() {
        loadCartProductsFromServer();
        tvCartTotalMoney.setText("");
    }

    @Override
    public void onLoadCartProductSuccess(ShopCart shopCart) {
        swipeLayouts.setRefreshing(false);
        if (shopCart.getStatus() == 1) {
            if (shopCart.getTotal() < 1) {
                ToastUtil.show(getActivity(), "你还未添加任何商品");
            }
            Contains.shopCartNum = shopCart.getTotal();
            Contains.shopCartList = shopCart.getRows();
            mShopCartListAdapter.setNewData(shopCart.getRows());
        } else {
//            onError(shopCart.getMSG(), shopCart.getStatus());
            onError(shopCart.MSG, shopCart.status);
        }

    }

    /**
     * 修改商品成功
     */
    @Override
    public void changeCartCountSuccess() {
        loadCartProductsFromServer();
    }

    @Override
    public void onLoadProductFailed() {
        swipeLayouts.setRefreshing(false);
//        onLoadProductFailed(getActivity().getString(R.string.loading_failed_1));
    }

    @Override
    public void onLoadProductFailed(String msg) {
        swipeLayouts.setRefreshing(false);
        ToastUtil.show(getContext(), msg);
    }


    @Override
    public void onOneCheckBoxNotChecked() {
        if (radioAllChecked.isChecked()) {
            radioAllChecked.setChecked(false, true);
        }

    }

    @Override
    public void onAllChecked(boolean allChecked) {
        radioAllChecked.setChecked(allChecked, true);
        setCheckedTotalMoney();
    }


    @Override
    public void onItemChecked() {
        setCheckedTotalMoney();
    }

    @Override
    public void onItemCancelChecked() {
        setCheckedTotalMoney();
    }

    @Override
    public void onItemPriceChanged() {
        setCheckedTotalMoney();
    }


    private void setCheckedTotalMoney() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        double sum = 0;
        for (ShopCart.ShapCartBean dataBean : mShopCartListAdapter.getData()) {
            if (!dataBean.isChecked()) {
                continue;
            }
            double money = dataBean.getCartSpdanjia() * (double) dataBean.getCartNum();
            sum += money;
        }
        tvCartTotalMoney.setText("¥ " + decimalFormat.format(sum));
    }

    private void deleteProductsFromCart() {
        List<Integer> cartIds = new ArrayList<>();
        for (ShopCart.ShapCartBean bean : mShopCartListAdapter.getData()) {
            if (bean.isChecked()) {
                cartIds.add(bean.getCartSpbianhao());
            }
        }
        mPresenter.deleteCart(cartIds);
    }

    @Override
    public void onRefresh() {
        loadCartProductsFromServer();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_bianji, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.bianji) {
            if ("编辑".equals(item.getTitle())) {
                item.setTitle("完成");
                // ToastUtil.show(getActivity(), "点击了编辑");
                btCartOrderOk.setText("删除");
                btCartOrderOk.setBackgroundColor(getResources().getColor(R.color.color_ff6966));
                tvCartTotalMoney.setVisibility(View.INVISIBLE);
                tvCartZongjia.setVisibility(View.INVISIBLE);

            } else {
                item.setTitle("编辑");
                // ToastUtil.show(getActivity(), "点击了完成");
                btCartOrderOk.setText("结算");
                btCartOrderOk.setBackgroundColor(getResources().getColor(R.color.color_ff9934));
                tvCartTotalMoney.setVisibility(View.VISIBLE);
                tvCartZongjia.setVisibility(View.VISIBLE);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDeletePop() {
        new AlertView("提示", "确定要删除这些商品吗", null, null, new String[]{"删除", "取消"}, getActivity(), AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                if (position == 0) {
                        deleteProductsFromCart();

                }
            }
        }).show();
    }
}