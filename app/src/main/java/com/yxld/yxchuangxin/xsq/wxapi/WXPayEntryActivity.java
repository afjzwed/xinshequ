package com.yxld.yxchuangxin.xsq.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.contain.PayContain;

import org.greenrobot.eventbus.EventBus;

/**
 * @author wwx
 * @ClassName: WXPayEntryActivity
 * @Description: 获取微信支付结果并返回
 * @date 2016年3月8日 上午12:02:23
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = WXPayEntryActivity.class.getSimpleName();

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("geek", "onCreate:WXPayEntryActivity ");
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, PayContain.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.d("geek", "onReq");
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.d("geek", "onPayFinish, errCode = " + baseResp.errCode);
        Log.d("geek", "onPayFinish, errStr = " + baseResp.errStr);
        Log.d("geek", "onPayFinish, transaction = " + baseResp.transaction);
        Log.d("geek", "onPayFinish, openId = " + baseResp.openId);
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
                 if(baseResp.errCode==0){
                     ToastUtil.showShort("支付成功");
                     PayContain.payResult = PayContain.PAY_SUCCESS;
                 }else {
                     ToastUtil.showShort("支付失败,请重试！");
                     PayContain.payResult = PayContain.PAY_FAIL;
                 }
                 finish();
        }
    }

}
