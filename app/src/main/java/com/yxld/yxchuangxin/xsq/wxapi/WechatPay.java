package com.yxld.yxchuangxin.xsq.wxapi;

import android.app.Activity;
import android.util.Log;

import com.socks.library.KLog;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yxld.yxchuangxin.Utils.HttpUtils;
import com.yxld.yxchuangxin.contain.PayContain;
import com.yxld.yxchuangxin.data.api.API;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

public class WechatPay {
	/**
	 * 生成订单的方法
/**
 * @author wwx
 * @ClassName: WechatPay
 * @Description: 请求服务器获取预订单
 * @date 2016年3月8日 上午11:02:23
 */
	public static String createOrder(String tradeNo, String totalFee, String subject,String payType) {
		Log.d("geek", "createOrder: 'createOrder"+subject);
		String result = "";
		//http://www..../WechatPayServer/UnifiedOrderServlet?trade_no=" + tradeNo + "&total_fee=" + totalFee + "&subject=" + subject
//		String URL_PREPAY = Contains.URL_PAY_CALLBACK + "/UnifiedOrderServlet";
		String IP_PRODUCT = API.IP_PRODUCT;
//		String IP_PRODUCT = "http://120.24.77.39/wygl";
		//// TODO: 2018/1/15 修改微信支付的地址
	//	String URL_PREPAY = IP_PRODUCT + "/UnifiedOrderServlet.servlet";
		String URL_PREPAY = IP_PRODUCT + "/pay/unifyOrder_wechatpay.mvc";
		try {
			subject = URLEncoder.encode(subject, "UTF-8");
			payType = URLEncoder.encode(payType, "UTF-8");
			String url = URL_PREPAY + "?trade_no=" + tradeNo + "&total_fee=" + totalFee + "&subject=" + subject +"&payType="+payType;
			KLog.i("createOrder: 'url="+url);
			result = HttpUtils.doGet(url);
			KLog.i("微信创建订单 result="+result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String createOrder(String tradeNo, String totalFee, String subject,String payType, String url) {
		Log.d("geek", "createOrder: 'createOrder"+subject);
		String result = "";
		//http://www..../WechatPayServer/UnifiedOrderServlet?trade_no=" + tradeNo + "&total_fee=" + totalFee + "&subject=" + subject
//		String URL_PREPAY = Contains.URL_PAY_CALLBACK + "/UnifiedOrderServlet";
		String IP_PRODUCT = "http://www.hnchxwl.com/wygl";
		String URL_PREPAY = IP_PRODUCT + "/UnifiedOrderServlet.servlet";
		try {
			subject = URLEncoder.encode(subject, "UTF-8");
			payType = URLEncoder.encode(payType, "UTF-8");
//			String url = URL_PREPAY + "?trade_no=" + tradeNo + "&total_fee=" + totalFee + "&subject=" + subject +"&payType="+payType;
			Log.d("geek", "createOrder: 'url="+url);
			result = HttpUtils.doGet(url);
			Log.d("geek","微信创建订单 result="+result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 支付的方法
	 *
	 * @param activity
	 * @param result 服务器生成订单返回的json字符串
	 *
	 */
	public static void pay(Activity activity, String result) {
		IWXAPI api = WXAPIFactory.createWXAPI(activity, PayContain.WX_APP_ID); // 将该app注册到微信
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(result);
			PayReq payReq = new PayReq();
//			payReq.appId = Contains.WX_APP_ID;
//			payReq.partnerId = Contains.WX_MCH_ID;
//			payReq.prepayId = jsonObject.getString("prepay_id");
//			payReq.nonceStr = jsonObject.getString("nonce_str");
//			payReq.timeStamp = jsonObject.getString("timestamp");
//			payReq.packageValue = jsonObject.getString("package");
//			payReq.sign = jsonObject.getString("sign");
//			payReq.appId = PayContain.WX_APP_ID;
//			payReq.partnerId = PayContain.WX_MCH_ID;
			payReq.appId = PayContain.WX_APP_ID;
			payReq.partnerId = PayContain.WX_MCH_ID;
			payReq.prepayId = jsonObject.getString("prepayid");
			payReq.nonceStr = jsonObject.getString("noncestr");
			payReq.timeStamp = jsonObject.getString("timestamp");
			payReq.packageValue = jsonObject.getString("package");
			payReq.sign = jsonObject.getString("sign");
			Log.d("geek", "pay: payReq="+payReq.toString());
			api.sendReq(payReq);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
