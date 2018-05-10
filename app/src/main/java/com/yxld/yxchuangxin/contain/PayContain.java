package com.yxld.yxchuangxin.contain;

/**
 * 作者：wwx on 2017/6/28 0028 10:17
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：支付常量
 */

public class PayContain {
    //	/** Pay Callback Server URL **/
    // 微信开放平台审核通过的应用APPID
//    public static final String WX_APP_ID = "wx474645d31f239239";
    public static final String WX_APP_ID = "wx5b6670d212378919";
    // 微信支付分配的商户号
//    public static final String WX_MCH_ID = "1353654702";
    public static final String WX_MCH_ID = "1488728872";

//    public static final String WX_APP_SECRET = "71a35538363ef0b77417db4b54bafba6";
    public static final String WX_APP_SECRET = "23329cd69121b72f2ef08a7ce2e24473";

    /** 微信支付结果 1 成功 -1 失败*/
    public static Integer weixinPayResult = null;
    /** 支付功能点 */
    public static Integer requestPayModule = null;

    public static Integer yinLianPay = null;

    public static Integer payResult = null;

    public static final int PAY_SUCCESS = 1;
    public static final int PAY_FAIL = -1;

    public static final int WEI_XIN_CHECKED = 1;

    public static final int MODULE_MALL_ORDER = 1;
    public static final int MODULE_CL_ORDER = 2;
    public static final int MODULE_WY_ORDER = 3;

    public static final int YIN_LIAN_PAY_CHECKED = 2;

}
