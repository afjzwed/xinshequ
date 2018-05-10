package com.yxld.yxchuangxin.Utils;

import android.app.Activity;
import android.content.Intent;

import com.socks.library.KLog;
import com.unionpay.UPPayAssistEx;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.view.ConfirmDialog;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author xlei
 * @Date 2018/1/16.
 */

public class YinLianPayUtil {

    public static final int PLUGIN_VALID = 0;
    public static final int PLUGIN_NOT_INSTALLED = -1;
    public static final int PLUGIN_NEED_UPGRADE = 2;

    public void getTN(String dingdanBianhao, String price, HttpUtils.CallBack callBack) {
        String url = API.IP_PRODUCT + "/pay/unionpay_pay_info.mvc?orderId=" + dingdanBianhao + "&txnAmt=" + price;
        KLog.i(url);
        HttpUtils.doGetAsyn(url, callBack);
    }

    public void doStartUnionPayPlugin(Activity activity, String tn, String mode) {
        // mMode参数解释：
        // 0 - 启动银联正式环境
        // 1 - 连接银联测试环境
        KLog.i("activity" + activity.toString());
        int ret = UPPayAssistEx.startPay(activity, null, null, tn, mode);
        if (ret == PLUGIN_NEED_UPGRADE || ret == PLUGIN_NOT_INSTALLED) {
            // 需要重新安装控件
            KLog.i(" plugin not found or need upgrade!!!");
            ConfirmDialog.showDialog(activity, "欣提示", "完成购买需要安装银联支付控件，是否安装？", new ConfirmDialog.OnConfirmListener() {
                @Override
                public void onCancel() {

                }

                @Override
                public void onConfirm() {
                    UPPayAssistEx.installUPPayPlugin(activity);
                }
            });
        }
        KLog.i("ret" + ret);
    }

    public void yinLianCallBack(Intent data,YinLianPayBack payBack) {
        /*************************************************
         * 步骤3：处理银联手机支付控件返回的支付结果
         ************************************************/
        if (data == null) {
            return;
        }

        String msg = "";
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        if (StringUitl.isEmpty(str))return;
        if (str.equalsIgnoreCase("success")) {

            // 如果想对结果数据验签，可使用下面这段代码，但建议不验签，直接去商户后台查询交易结果
            // result_data结构见c）result_data参数说明
            if (data.hasExtra("result_data")) {
                String result = data.getExtras().getString("result_data");
                try {
                    JSONObject resultJson = new JSONObject(result);
                    String sign = resultJson.getString("sign");
                    String dataOrg = resultJson.getString("data");
                    // 此处的verify建议送去商户后台做验签
                    // 如要放在手机端验，则代码必须支持更新证书
                    boolean ret = verify(dataOrg, sign, "01");
                    if (ret) {
                        // 验签成功，显示支付结果
                        msg = "支付成功！";
                    } else {
                        // 验签失败
                        msg = "支付失败！";
                    }
                } catch (JSONException e) {
                }
            }
            // 结果result_data为成功时，去商户后台查询一下再展示成功
            msg = "支付成功！";
            payBack.onSuccess(msg);
        } else if (str.equalsIgnoreCase("fail")) {
            msg = "支付失败！";
            payBack.onFail(msg);
        } else if (str.equalsIgnoreCase("cancel")) {
            msg = "用户取消了支付";
            payBack.onCanel(msg);
        }
        KLog.i(msg + "----------------");

    }

    private boolean verify(String msg, String sign64, String mode) {
        // 此处的verify，商户需送去商户后台做验签
        return true;

    }

    public interface YinLianPayBack {
        void onSuccess(String msg);

        void onFail(String msg);

        void onCanel(String msg);
    }
}
