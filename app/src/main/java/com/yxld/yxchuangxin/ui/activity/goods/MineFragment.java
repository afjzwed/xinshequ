package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.PopWindowUtil;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.StateOrderNum;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerMineComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MineContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.MineModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.MinePresenter;
import com.yxld.yxchuangxin.ui.activity.wuye.AccountActivity;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.yxld.yxchuangxin.view.MineOrderEventView;
import com.yxld.yxchuangxin.view.MineSettingView;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yuan
 * @Package .ui.activity.goods
 * @Description: $description
 * @date 2017/06/14
 */

public class MineFragment extends BaseFragment implements MineContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    MinePresenter mPresenter;
    @BindView(R.id.iv_mine_header)
    ImageView ivMineHeader;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.rl_mine_order_check_all)
    AutoRelativeLayout rlMineOrderCheckAll;
    @BindView(R.id.mine_setting_root)
    AutoLinearLayout mineSettingRoot;
    @BindView(R.id.mine_order_waiting_pay)
    MineOrderEventView mineOrderWaitingPay;
    @BindView(R.id.mine_order_waiting_send)
    MineOrderEventView mineOrderWaitingSend;
    @BindView(R.id.mine_order_waiting_receive)
    MineOrderEventView mineOrderWaitingReceive;
    @BindView(R.id.mine_order_waiting_comment)
    MineOrderEventView mineOrderWaitingComment;
    @BindView(R.id.mine_order_refund)
    MineOrderEventView mineOrderRefund;
    @BindView(R.id.swipeLayouts)
    SwipeRefreshLayout swipeLayouts;
    @BindView(R.id.header_and_name_container)
    AutoLinearLayout headerAndNameContainer;
    @BindView(R.id.title)
    TextView title;

    private BroadcastReceiver mBroadcastReceiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        UIUtils.configSwipeRefreshLayoutColors(swipeLayouts);
        swipeLayouts.setOnRefreshListener(this);
        initDataFromLocal();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mBroadcastReceiver == null) {
            IntentFilter filter = new IntentFilter(getResources().getString(R.string.update_mine));
            mBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    loadDataFromServer();
                }
            };
            getContext().registerReceiver(mBroadcastReceiver, filter);
        }

        swipeLayouts.post(new Runnable() {
            @Override
            public void run() {
                loadDataFromServer();
            }
        });
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerMineComponent.builder().appComponent(((AppConfig) getActivity().getApplication())
                .getApplicationComponent()).mineModule(new MineModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(MineContract.MineContractPresenter presenter) {
        mPresenter = (MinePresenter) presenter;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reLogin(String relogin) {
        if (relogin.equals("reLogin")) {
            KLog.i("我的收到了消息");
            loadDataFromServer();
        }
    }

    @Override
    protected void initDataFromLocal() {
        AutoLinearLayout.LayoutParams settingParams = new AutoLinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT);
        MineSettingView settingView = null;
        for (int i = 0; i < 4; i++) {
            settingView = new MineSettingView(getContext());
            switch (i) {
                case 0:
                    settingView.setText("电子券");
                    settingView.setIcon(R.mipmap.grzx_dzj01);
                    break;
                case 1:
//                    settingView.setText("地址管理");
//                    settingView.setIcon(R.mipmap.grzx_dzj02);
                    settingView.setText("我的评价");
                    settingView.setIcon(R.mipmap.grzx_dzj02);
                    break;
                case 2:
                    settingView.setText("商城建议");
                    settingView.setIcon(R.mipmap.grzx_dzj03);
                    break;
                case 3:
//                    settingView.setText("修改密码");
//                    settingView.setIcon(R.mipmap.grzx_dzj04);
//                    settingView.setGrayLineVisible(false);
                    settingView.setText("联系客服");
                    settingView.setIcon(R.mipmap.kefu);
                    settingView.setGrayLineVisible(false);
                    break;
                default:
                    break;
            }
            onSettingViewClick(settingView, i);
            settingView.setLayoutParams(settingParams);
            mineSettingRoot.addView(settingView);
        }

        tvMineName.setText(Contains.user.getYezhuName());

        title.setText("我的");
//        AutoLinearLayout.LayoutParams lp = (AutoLinearLayout.LayoutParams)title.getLayoutParams();
//        lp.setMargins(0, UIUtils.getStatusBarHeight(getActivity()), 0, 0);
//        title.setLayoutParams(lp);
        headerAndNameContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AccountActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getContext().unregisterReceiver(mBroadcastReceiver);
        mBroadcastReceiver = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        swipeLayouts.setRefreshing(false);
        mPresenter.unsubscribe();
        mPresenter = null;
    }

    @OnClick({R.id.mine_order_waiting_pay, R.id.mine_order_waiting_send, R.id.mine_order_waiting_receive, R.id
            .mine_order_waiting_comment, R.id.mine_order_refund, R.id.rl_mine_order_check_all})
    public void onViewClicked(View view) {
        int item = 0;
        switch (view.getId()) {
            case R.id.mine_order_waiting_pay:
                //待付款
                item = 1;
                break;
            case R.id.mine_order_waiting_send:
                //待发货
                item = 2;
                break;
            case R.id.mine_order_waiting_receive:
                //待收货
                item = 3;
                break;
            case R.id.mine_order_waiting_comment:
                //待评价
                item = 4;
                break;
            case R.id.mine_order_refund:
                //退款
                item = 5;
                break;
            case R.id.rl_mine_order_check_all:
                //查看详情
                break;
            default:
                break;
        }
        Intent intent = new Intent(getContext(), OrderListActivity.class);
        intent.putExtra(OrderListActivity.ITEM, item);
        startActivity(intent);
    }

    private void onSettingViewClick(MineSettingView view, int position) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        startActivity(TicketActivity.class);
                        break;
                    case 1:
//                        startActivity(AddressManageActivity.class);
                        startActivity(MyEvaluateActivity.class);
                        break;
                    case 2:
                        startActivity(MarketComplainListActivity.class);
                        break;
                    case 3:
//                        Intent intent = new Intent(getContext(), ResetPasswordActivity.class);
//                        intent.putExtra(ResetPasswordActivity.KEY_IN_TYPE, ResetPasswordActivity.IN_TYPE_MALL);
//                        startActivity(intent);
                        showServiceDialog();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void showServiceDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_link_service, null);
        AutoLinearLayout shangjia = (AutoLinearLayout) view.findViewById(R.id.shangjia);
        TextView shangjiaDianHua = (TextView) view.findViewById(R.id.shangjia_dianhua);
        TextView kefuDianHua = (TextView) view.findViewById(R.id.kefu_telephone);
        AutoLinearLayout kefu = (AutoLinearLayout) view.findViewById(R.id.kefu);
        String telePhone = Contains.appYezhuFangwus.get(Contains.curFangwu).getXiangmuTelphone();
        shangjiaDianHua.setText("客服电话" + telePhone);
        Button cancel = (Button) view.findViewById(R.id.cancel);
        kefu.setOnClickListener(v12 -> {
            CustomPopWindow.onBackPressed();
        });
        shangjia.setOnClickListener(v13 -> {
            callPhone(telePhone);
            CustomPopWindow.onBackPressed();
        });
        cancel.setOnClickListener(v -> CustomPopWindow.onBackPressed());
        AutoLinearLayout ll_popup = (AutoLinearLayout) view.findViewById(R.id.ll_popup);
        PopWindowUtil.showFullScreenPopWindow(getActivity(), headerAndNameContainer, view, ll_popup);
    }

    @Override
    public void onLoadOrderStatusSucceed(StateOrderNum orderStatus) {
        if (orderStatus.getStatus() == 1) {
            mineOrderWaitingPay.setDotNum(orderStatus.getDaiFukuan());
            mineOrderWaitingSend.setDotNum(orderStatus.getDaiFahuo());
            mineOrderWaitingReceive.setDotNum(orderStatus.getDaiShouhuo());
            mineOrderWaitingComment.setDotNum(orderStatus.getDaiPingjia());
            // mineOrderRefund.setDotNum(orderStatus.getTuiKuan());
        } else {
            onError(orderStatus.MSG, orderStatus.status);
        }
    }

    @Override
    public void onLoadOrderStatusFailed() {
        ToastUtil.show(getContext(), getResources().getString(R.string.load_failed));
    }

    @Override
    public void showProgressDialog() {
//        progressDialog.show();
        if (swipeLayouts != null) {
            swipeLayouts.setRefreshing(true);
        }
    }

    @Override
    public void closeProgressDialog() {
//        progressDialog.hide();
        if (swipeLayouts != null) {
            swipeLayouts.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        loadDataFromServer();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }


    private void loadDataFromServer() {
        mPresenter.loadOrderStatusFromServer();
    }

    private void callPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        startActivity(intent);
    }

}