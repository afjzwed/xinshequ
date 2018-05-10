package com.yxld.yxchuangxin.Utils;


import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.API;


/**
 * 作者：wwx on 2017/6/28 0028 17:32
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述： 支付宝工具类
 */

public class AlipyUtil {




    /**
     * create the order info. 创建订单信息
     */
//    public String getOrderInfo(String dingdanBianhao, String subject, String body, String price) {
//        // 签约合作者身份ID
//        String orderInfo = "partner=" + "\"" + PARTNER + "\"";
//
//        // 签约卖家支付宝账号
//        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";
//
//        // 商户网站唯一订单号
//        orderInfo += "&out_trade_no=" + "\"" + dingdanBianhao + "\"";
//
//        // 商品名称
//        orderInfo += "&subject=" + "\"" + subject + "\"";
//
//        // 商品详情
//        orderInfo += "&body=" + "\"" + body + "\"";
//
//        // 商品金额
//        orderInfo += "&total_fee=" + "\"" + price + "\"";
//
//        // 服务器异步通知页面路径
//        orderInfo += "&notify_url=" + "\"" + API.IP_PRODUCT + "/notify_url.jsp" + "\"";
//        //http://222.240.1.133/wygl/mall/androidOrder_alipayUpdateOrder
//        // 服务接口名称， 固定值
//        orderInfo += "&service=\"mobile.securitypay.pay\"";
//
//        // 支付类型， 固定值
//        orderInfo += "&payment_type=\"1\"";
//
//        // 参数编码， 固定值
//        orderInfo += "&_input_charset=\"utf-8\"";
//
//        // 设置未付款交易的超时时间
//        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
//        // 取值范围：1m～15d。
//        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
//        // 该参数数值不接受小数点，如1.5h，可转换为90m。
//        orderInfo += "&it_b_pay=\"30m\"";
//
//        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
//        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
//
//        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
//        orderInfo += "&return_url=\"m.alipay.com\"";
//
//        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
//        // orderInfo += "&paymethod=\"expressGateway\"";
//
//        Log.d("geek", "订单信息 = " + orderInfo);
//        return orderInfo;
//    }

    /**
     * create the order info. 创建订单信息 请求服务器获取签名后的orderinfo
     */
    public void getOrderInfo(String dingdanBianhao, String subject, String body, String price, HttpUtils.CallBack callback) {

        //http://192.168.8.120:8080/wygl/pay/orderinfo_alipay.mvc?dingdanBianhao=12333sj76663&subject=%E5%B0%8F%E8%8A%B1&body=123&price=10
        String url = API.IP_PRODUCT + "/pay/orderinfo_alipay.mvc?dingdanBianhao=" + dingdanBianhao + "&subject=" + subject + "&body=" + body + "&price=" + price;
        KLog.i(url);
        try {
            HttpUtils.doGetAsyn(url, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    /**
//     * sign the order info. 对订单信息进行签名
//     *
//     * @param content 待签名订单信息
//     */
//    public String signs(String content) {
//        return sign(content, RSA_PRIVATE);
//    }
//
//    /***
//     * 签名
//     * @param content
//     * @param privateKey
//     * @return
//     */
//    public String sign(String content, String privateKey) {
//        try {
//            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
//                    Base64.decode(privateKey));
//            KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
//            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
//
//            java.security.Signature signature = java.security.Signature
//                    .getInstance(SIGN_ALGORITHMS);
//
//            signature.initSign(priKey);
//            signature.update(content.getBytes(DEFAULT_CHARSET));
//
//            byte[] signed = signature.sign();
//
//            return Base64.encode(signed);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    /**
//     * get the sign type we use. 获取签名方式
//     */
//    public String getSignType() {
//        return "sign_type=\"RSA\"";
//    }

}
