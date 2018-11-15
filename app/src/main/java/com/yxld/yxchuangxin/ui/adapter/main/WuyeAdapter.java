package com.yxld.yxchuangxin.ui.adapter.main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.adapter.Wuye;
import com.yxld.yxchuangxin.ui.activity.camera.DeviceActivity;
import com.yxld.yxchuangxin.ui.activity.common.AisleActivity;
import com.yxld.yxchuangxin.ui.activity.main.WebviewActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.AboutOurActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.AccountActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.CarManageActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.ComplainActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.FangwuActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.FangxingActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.FixActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.LiveMemberActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.MessageActivityActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.RoomRentActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.UpdateActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.WebSatisficingActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.WuyeMoneyActivity;
import com.yxld.yxchuangxin.ui.activity.xiongmai.DeviceLoginActivity;
import com.yxld.yxchuangxin.ui.activity.xiongmai.lib.funsdk.support.FunSupport;
import com.yxld.yxchuangxin.ui.activity.ywh.YeWeiHuiActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.YwhWebViewActivity;
import com.yxld.yxchuangxin.view.GridDividerItemDecoration;

import java.util.List;

/**
 * 作者：hu on 2017/6/5
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class WuyeAdapter extends BaseQuickAdapter<Wuye.DataBean, BaseViewHolder> {

    HttpAPIWrapper httpAPIWrapper;

    public WuyeAdapter(List<Wuye.DataBean> data) {
        super(R.layout.item_listview, data);
    }

    public WuyeAdapter(List<Wuye.DataBean> data, HttpAPIWrapper httpAPIWrapper) {
        super(R.layout.item_listview, data);
        this.httpAPIWrapper = httpAPIWrapper;
    }

    @Override
    protected void convert(BaseViewHolder helper, Wuye.DataBean item) {
        helper.setText(R.id.textView, item.getName());
        RecyclerView recycerView = helper.getView(R.id.recycerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        recycerView.setLayoutManager(gridLayoutManager);
        Drawable divider = mContext.getResources().getDrawable(R.drawable.shape_divider);
        GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration(mContext,
                GridDividerItemDecoration.GRID_DIVIDER_VERTICAL);
        dividerItemDecoration.setVerticalDivider(divider);
        dividerItemDecoration.setHorizontalDivider(divider);
        recycerView.addItemDecoration(dividerItemDecoration);
       // recycerView.addItemDecoration(new GridDividerItemDecoration(10));
        WuyeAdapter1 wuyeAdapter1 = new WuyeAdapter1(item.getList());
        //缩放动画
//        wuyeAdapter1.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        if (item.getName().equals("我的物业")) {
            wuyeAdapter1.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (position) {
                        case 0:
                            mContext.startActivity(new Intent(mContext, MenJinActivity.class));
//                            mContext.startActivity(new Intent(mContext, MenJinNewActivity.class));
                            break;
                        case 1:
                            mContext.startActivity(new Intent(mContext, CarManageActivity.class));
                            break;
                        case 2:
                            //AppConfig.getInstance().initP2P(AppConfig.getInstance());
                            mContext.startActivity(new Intent(mContext, DeviceActivity.class));
                            break;
                        case 3:
                            AppConfig.getInstance().initCommon();
                            mContext.startActivity(new Intent(mContext, AisleActivity.class));
                            break;
                        case 4:
                            if (Contains.user == null || Contains.user.getYezhuCardNum() == null
                                    || Contains.user.getYezhuShouji() == null) {
                                ToastUtil.show(mContext, "请至物业完善业主身份证和手机号码信息再进行查询");
                                return;
                            } else {
                                mContext.startActivity(new Intent(mContext, RoomRentActivity
                                        .class));
                            }
                            break;
                        case 5:
                            mContext.startActivity(new Intent(mContext, WuyeMoneyActivity.class));
                            break;
                        case 6:
                            FunSupport.getInstance().init(AppConfig.getInstance());
                            mContext.startActivity(new Intent(mContext, DeviceLoginActivity.class));
                            break;
                    }
                }
            });
        }
        if (item.getName().equals("缴费服务")) {
            wuyeAdapter1.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (position) {
                        case 0:
                            mContext.startActivity(new Intent(mContext, WuyeMoneyActivity.class));
                            break;
                    }
                }
            });
        }
        if (item.getName().equals("综合服务")) {
            wuyeAdapter1.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (position) {
                        case 0:
                            mContext.startActivity(new Intent(mContext, MessageActivityActivity
                                    .class));
                            break;
                        case 1:
                            mContext.startActivity(new Intent(mContext, FixActivity.class));
                            break;
                        case 2:
                            mContext.startActivity(new Intent(mContext, FangxingActivity.class));
                            break;
                        case 3:
                            Intent intent = new Intent(mContext, WebviewActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("name", "邮包查寄");
                            bundle.putString("address", "http://m.kuaidihelp.com/");
                            intent.putExtras(bundle);
                            mContext.startActivity(intent);
                            break;
                        case 4:
                            mContext.startActivity(new Intent(mContext, ComplainActivity.class));
                            break;
                        case 5: {
                            Intent intent1 = new Intent(mContext, WebSatisficingActivity.class);
                            Bundle bundle1 = new Bundle();
                            bundle1.putString("name", "满意度调查");
//                            bundle1.putString("address", API.HTTPS+"wygl/manyidu.jsp?");
                            bundle1.putString("address", API.URL_SATISFICING);
                            intent1.putExtras(bundle1);
                            mContext.startActivity(intent1);
                          /*  if (Contains.user == null || Contains.appYezhuFangwus.size() == 0
                          || Contains.appYezhuFangwus.get(0) == null || Contains.appYezhuFangwus
                          .get(0).getFwLoupanId() == 0
                                    || "".equals(Contains.appYezhuFangwus.get(0).getFwLoupanId())) {
                                Toast.makeText(mContext, "需要在后台去配置您的业主信息", Toast.LENGTH_SHORT)
                                .show();
                            } else {
                                CompositeDisposable mCompositeDisposable = new
                                CompositeDisposable();
                                Map<String, String> map = new HashMap<>();
                                //?uuid=%1$s
                                map.put("uuid", Contains.uuid);
                                Disposable disposable = httpAPIWrapper.getManYiDuTiaoChaExist(map)
                                        .subscribe(new Consumer<BaseEntity>() {
                                            @Override
                                            public void accept(BaseEntity entity) throws Exception {
                                                //isSuccesse
                                                KLog.i("onSuccesse");
                                                if (entity.getMSG().equals("不好意思你已经答过了")) {
                                                    ToastUtil.show(mContext, entity.getMSG());
                                                } else {
                                                    mContext.startActivity(new Intent(mContext,
                                                    SatisficingActivity.class));
                                                }
                                            }
                                        }, new Consumer<Throwable>() {
                                            @Override
                                            public void accept(Throwable throwable) throws
                                            Exception {
                                                //onError
                                                KLog.i("onError");
                                                throwable.printStackTrace();
//                                                ToastUtil.show(mContext, mContext.getString(R
.string.loading_failed_1));
                                            }
                                        }, new Action() {
                                            @Override
                                            public void run() throws Exception {
                                                //onComplete
                                                KLog.i("onComplete");
                                            }
                                        });
                                mCompositeDisposable.add(disposable);
                            }*/
//                            mContext.startActivity(new Intent(mContext, SatisficingActivity
// .class));
                            break;
                        }
                    }
                }
            });
        }
        if (item.getName().equals("个人中心")) {
            wuyeAdapter1.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (position) {
                        case 0:
                            if (Contains.appYezhuFangwus != null && Contains.appYezhuFangwus.size
                                    () != 0) {
                                mContext.startActivity(new Intent(mContext, FangwuActivity.class));
                            } else {
                                Toast.makeText(mContext, "业主信息尚未完善", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 1:
                            if (Contains.appYezhuFangwus != null && Contains.appYezhuFangwus.size
                                    () != 0 && Contains.appYezhuFangwus.get(Contains.curFangwu)
                                    .getFwyzType() <= 1) {
                                mContext.startActivity(new Intent(mContext, LiveMemberActivity
                                        .class));
                            } else {
                                ToastUtil.show(mContext, "您没有权限查看");
                            }
                            break;
                        case 2:
                            mContext.startActivity(new Intent(mContext, AccountActivity.class));
                            break;
                        case 3:
                            mContext.startActivity(new Intent(mContext, UpdateActivity.class));
//                            mContext.startActivity(new Intent(mContext, AtestActivity.class));
                            break;
                        case 4:
                            mContext.startActivity(new Intent(mContext, AboutOurActivity.class));
                            break;
                    }
                }
            });
        }
        if (item.getName().equals("物业监管")) {
            wuyeAdapter1.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (position) {
                        case 0:
                            mContext.startActivity(new Intent(mContext, YeWeiHuiActivity.class));
                            break;
                        case 1:
//                            mContext.startActivity(new Intent(mContext, OpinionSurveyActivity.class));
                            mContext.startActivity(new Intent(mContext, YwhWebViewActivity.class));
                            break;
                    }
                }
            });
        }
        recycerView.setAdapter(wuyeAdapter1);

    }
}
