package com.yxld.yxchuangxin.data.api;


import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.base.BaseEntityService;
import com.yxld.yxchuangxin.entity.APPCamera;
import com.yxld.yxchuangxin.entity.Accredit;
import com.yxld.yxchuangxin.entity.AccreditDetail;
import com.yxld.yxchuangxin.entity.AppCameraList;
import com.yxld.yxchuangxin.entity.AppJiaofei;
import com.yxld.yxchuangxin.entity.AppYezhuFangwu;
import com.yxld.yxchuangxin.entity.BaoJingEntity;
import com.yxld.yxchuangxin.entity.BaseBack;
import com.yxld.yxchuangxin.entity.BaseBack1;
import com.yxld.yxchuangxin.entity.BaseBack2;
import com.yxld.yxchuangxin.entity.BuCheFang;
import com.yxld.yxchuangxin.entity.CarJiaofeiRecord;
import com.yxld.yxchuangxin.entity.CarList;
import com.yxld.yxchuangxin.entity.CxwyAppVersion;
import com.yxld.yxchuangxin.entity.CxwyBaoxiu;
import com.yxld.yxchuangxin.entity.CxwyBusiness;
import com.yxld.yxchuangxin.entity.CxwyBusinessInfo;
import com.yxld.yxchuangxin.entity.CxwyClassifyInfo;
import com.yxld.yxchuangxin.entity.CxwyCommon;
import com.yxld.yxchuangxin.entity.CxwyCommonToken;
import com.yxld.yxchuangxin.entity.CxwyComplain;
import com.yxld.yxchuangxin.entity.CxwyDianziquan;
import com.yxld.yxchuangxin.entity.CxwyMallAdd;
import com.yxld.yxchuangxin.entity.CxwyMallCart;
import com.yxld.yxchuangxin.entity.CxwyMallComment;
import com.yxld.yxchuangxin.entity.CxwyMallOrder;
import com.yxld.yxchuangxin.entity.CxwyMallPezhi;
import com.yxld.yxchuangxin.entity.CxwyMallProduct;
import com.yxld.yxchuangxin.entity.CxwyMessage;
import com.yxld.yxchuangxin.entity.CxwyOrderInfo;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;
import com.yxld.yxchuangxin.entity.DoorInfo;
import com.yxld.yxchuangxin.entity.FangquEntity;
import com.yxld.yxchuangxin.entity.GoodsKind;
import com.yxld.yxchuangxin.entity.HostEntiti;
import com.yxld.yxchuangxin.entity.IsNight;
import com.yxld.yxchuangxin.entity.JiaofeiMingxi;
import com.yxld.yxchuangxin.entity.LiuCheng;
import com.yxld.yxchuangxin.entity.LocalAd;
import com.yxld.yxchuangxin.entity.LockCar;
import com.yxld.yxchuangxin.entity.LoginEntity;
import com.yxld.yxchuangxin.entity.LoginPhoneEntity;
import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.entity.MenJinShareMemberBean;
import com.yxld.yxchuangxin.entity.MsgAndSuccess;
import com.yxld.yxchuangxin.entity.MyAllComment;
import com.yxld.yxchuangxin.entity.Onlymsg;
import com.yxld.yxchuangxin.entity.OpenDoorCode;
import com.yxld.yxchuangxin.entity.OpinionSurveyEntity;
import com.yxld.yxchuangxin.entity.OrderComplainEntity;
import com.yxld.yxchuangxin.entity.OrderDetailEntity;
import com.yxld.yxchuangxin.entity.OrderRemainDianZiQuanEntity;
import com.yxld.yxchuangxin.entity.PaianYezhuJiashu;
import com.yxld.yxchuangxin.entity.PaianYijiaJiashu;
import com.yxld.yxchuangxin.entity.PrePay;
import com.yxld.yxchuangxin.entity.PushOrder;
import com.yxld.yxchuangxin.entity.QiniuToken;
import com.yxld.yxchuangxin.entity.RimActivityDiscount;
import com.yxld.yxchuangxin.entity.RimCommentListBean;
import com.yxld.yxchuangxin.entity.RimDeleteOrderBean;
import com.yxld.yxchuangxin.entity.RimOrderCommentBean;
import com.yxld.yxchuangxin.entity.RimShopDetailBean;
import com.yxld.yxchuangxin.entity.RoomRent;
import com.yxld.yxchuangxin.entity.SJComplain;
import com.yxld.yxchuangxin.entity.SJOrder;
import com.yxld.yxchuangxin.entity.SJOrderStatus;
import com.yxld.yxchuangxin.entity.ShopCarList;
import com.yxld.yxchuangxin.entity.ShopList;
import com.yxld.yxchuangxin.entity.StateOrderNum;
import com.yxld.yxchuangxin.entity.WyFwApp;
import com.yxld.yxchuangxin.entity.XMsxt;
import com.yxld.yxchuangxin.entity.XuFeiBean;
import com.yxld.yxchuangxin.entity.XuFeiOrder;
import com.yxld.yxchuangxin.entity.YezhuDainZhiQuan;
import com.yxld.yxchuangxin.entity.YwhCurrentflow;
import com.yxld.yxchuangxin.entity.YwhFkyj;
import com.yxld.yxchuangxin.entity.YwhHouse;
import com.yxld.yxchuangxin.entity.YwhInfo;
import com.yxld.yxchuangxin.entity.YwhTj;
import com.yxld.yxchuangxin.entity.camera.ShareFamily;
import com.yxld.yxchuangxin.entity.camera.Shared;
import com.yxld.yxchuangxin.entity.goods.BaseEntityAll;
import com.yxld.yxchuangxin.entity.goods.DiZiJuanGuiZei;
import com.yxld.yxchuangxin.entity.goods.MallNewOrder;
import com.yxld.yxchuangxin.entity.goods.MallNewProduct1;
import com.yxld.yxchuangxin.entity.goods.MallOrderSuggest;
import com.yxld.yxchuangxin.entity.goods.ShopCart;
import com.yxld.yxchuangxin.entity.goods.ShopDianZiJuan;
import com.yxld.yxchuangxin.entity.goods.ShopNewList;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import static com.yxld.yxchuangxin.data.api.API.*;

/**
 * Created by hu on 2017/5/16.
 */

public interface HttpApi {
    //******首页******//
    @GET(URL_GET_MAIN_LUNBO)
    Observable<CxwyMallPezhi> getBanner(@QueryMap Map<String, RequestBody> params);

    @GET(URL_GET_NEWMAINTONGZHI)
    Observable<BaseEntity> getAction(@QueryMap Map<String, RequestBody> params);
    //******首页******//

    /***********************账户相关*************************/
    //更改密码
    @GET(URL_GET_ALL_UPDATE_PWD)
    Observable<BaseEntity> getPassUpdate(@QueryMap Map<String, RequestBody> params);

    //退出登录
    @GET(URL_GET_ALL_OUTLOGIN)
    Observable<BaseEntity> getLoginOut(@QueryMap Map<String, RequestBody> params);

    //判断是否存在手机号码
    @GET(URL_GET_EXIST_SHOUJI)
    Observable<BaseEntity> getExistShouji(@QueryMap Map<String, RequestBody> params);

    //找回密码
    @GET(URL_GET_FIND_PWD)
    Observable<BaseEntity> getFindPwd(@QueryMap Map<String, RequestBody> params);

    //获取app版本信息
    @GET(URL_APP_GETVERSION)
    Observable<CxwyAppVersion> getAppVersionInfo(@QueryMap Map<String, RequestBody> params);
    /************************账户相关************************/

    /*********综合服务***********/
    //获取通知
    @POST(URL_FINDTONGZHI)
    @FormUrlEncoded
    Observable<CxwyMessage> getMessage(@FieldMap Map<String, RequestBody> params);

    //获取活动
    @POST(URL_HUODONG)
    @FormUrlEncoded
    Observable<CxwyMessage> getActivity(@FieldMap Map<String, RequestBody> params);

    //保存投诉和建议
    @POST(URL_SAVETOUSU)
    @FormUrlEncoded
    Observable<BaseEntity> saveTousuAndJianYi(@FieldMap Map<String, RequestBody> params);

    //获取投诉建议列表
    @GET(URL_FINDTOUSULIST)
    Observable<CxwyComplain> getTousuAndJianYiList(@QueryMap Map<String, RequestBody> params);

    //保存满意度调查
    @FormUrlEncoded
    @POST(URL_MANYIDU)
    Observable<MsgAndSuccess> saveManYiDu(@Field("abc") String params);

    //保存商城投诉和建议
    @FormUrlEncoded
    @POST(URL_MALL_TOUSUJIANYI_SAVA)
    Observable<BaseEntity> saveMallTousuAndJianYi(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_GET_MANYIDUTIAOCHAEXIST)
    Observable<BaseEntity> getManYiDuTiaoChaExist(@FieldMap Map<String, RequestBody> params);

    @GET(URL_GET_QINIU_TOKEN)
    Observable<QiniuToken> getQiniuToken(@QueryMap Map<String, RequestBody> params);

    //提交报修
    @FormUrlEncoded
    @POST(URL_GET_ALL_PRIVATE_SUBMIT)
    Observable<BaseEntity> getRepairPSubmit(@FieldMap Map<String, RequestBody> params);

    //获取所有的报修
    @FormUrlEncoded
    @POST(URL_REPAIR_ALL)
    Observable<CxwyBaoxiu> getRepairAllList(@FieldMap Map<String, RequestBody> params);

    //获取其它报修列表
    @FormUrlEncoded
    @POST(URL_REPAIR_OTHER)
    Observable<CxwyBaoxiu> getRepairOtherList(@FieldMap Map<String, RequestBody> params);

    //获取房屋信息
    @GET(URL_HOUSE)
    Observable<WyFwApp> getHouse(@QueryMap Map<String, RequestBody> params);

    //获取物业缴费
    @FormUrlEncoded
    @POST(URL_WUYE_ADD)
    Observable<AppJiaofei> getWuyePay(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_CHUZU_CHUZUXIANSHI)
    Observable<RoomRent> getRentList(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_CHUZU_UPDATEZHUANG)
    Observable<BaseEntity> updateRentStatus(@FieldMap Map<String, RequestBody> params);

    //业主获取授权放行列表
    @FormUrlEncoded
    @POST(URL_GET_SHOUQUAN_LIST_YEZHU)
    Observable<Accredit> getAccreditListProprietor(@FieldMap Map<String, RequestBody> params);

    //租客获取授权放行列表
    @FormUrlEncoded
    @POST(URL_GET_SHOUQUAN_LIST_ZUKE)
    Observable<Accredit> getAccreditListRent(@FieldMap Map<String, RequestBody> params);

    //租户授权申请提交
    @FormUrlEncoded
    @POST(URL_SHOUQUAN_CXWY_TIJIAO)
    Observable<MsgAndSuccess> comfirmAccredit(@FieldMap Map<String, RequestBody> params);

    //获取授权放行详情
    @FormUrlEncoded
    @POST(URL_GET_SHOUQUAN_DETAIL)
    Observable<AccreditDetail> getAccreditDetail(@FieldMap Map<String, RequestBody> params);

    //获取授权放行详情
    @FormUrlEncoded
    @POST(URL_SHOUQUAN_COMFIRM)
    Observable<BaseEntity> passAccredit(@FieldMap Map<String, RequestBody> params);

    //租户授权申请提交
    @FormUrlEncoded
    @POST(URL_TOUSUJIANYI_SAVA)
    Observable<Onlymsg> mallComplain(@FieldMap Map<String, RequestBody> params);

    //获取用户所有的缴费记录
    @FormUrlEncoded
    @POST(URL_ALL_PAYMENT_RECORDS)
    Observable<BaseEntity> getPaymentList(@FieldMap Map<String, RequestBody> params);

    //获取用户所有的缴费记录
    @FormUrlEncoded
    @POST(URL_DETAIL)
    Observable<JiaofeiMingxi> getJiaofei(@FieldMap Map<String, RequestBody> params);

    //获取用户所有的欠费记录
    @FormUrlEncoded
    @POST(URL_ALL_QIANFEI_RECORDS)
    Observable<BaseEntity> getArrearageList(@FieldMap Map<String, RequestBody> params);


    //车辆解锁
    @FormUrlEncoded
    @POST(URL_CAR_JIESUO)
    Observable<LockCar> carLockControl(@FieldMap Map<String, RequestBody> params);

    //车辆缴费
    @FormUrlEncoded
    @POST(URL_CAR_RECHARGE)
    Observable<BaseBack> carAffordMoney(@FieldMap Map<String, String> params);

    //车辆缴费
    @FormUrlEncoded
    @POST(URL_PRE_CHELAINGJIAOFEI)
    Observable<PrePay> carPreChongzhi(@FieldMap Map<String, String> params);

    //获取车辆列表
    @GET(URL_CAR_LIST)
    Observable<CarList> getCarList(@QueryMap Map<String, RequestBody> params);

    //获取车辆缴费记录
    @FormUrlEncoded
    @POST(URL_CAR_RELIST)
    Observable<CarJiaofeiRecord> getCarMoneyRecord(@FieldMap Map<String, RequestBody> params);

    /*********综合服务***********/
    /********************/
    @GET(URL_delete_chengyuan)
    Observable<BaseEntity> deletLiveMember(@QueryMap Map<String, RequestBody> params);

    /********************/
    @FormUrlEncoded
    @POST(URL_GET_ALL_LOGIN)
    Observable<LoginEntity> Login(@FieldMap Map<String, RequestBody> params);

    @GET(URL_GET_LOGIN_PHONE)
    Observable<LoginPhoneEntity> getloginPlot(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_GET_YEZHUOPENCODE)
    Observable<OpenDoorCode> getQRCode(@FieldMap Map<String, RequestBody> params);

    @GET(URL_GET_MENJINLIST)
    Observable<DoorInfo> getDoorList(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_POST_MENJINMIMA)
    Observable<BaseBack2> getDoorMima(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_POST_MENJIN_SHAREMEMBER)
    Observable<MenJinShareMemberBean> getDoorShareMember(@FieldMap Map<String, RequestBody> params);
    @FormUrlEncoded
    @POST(URL_POST_MENJIN_SAVE)
    Observable<BaseEntity> saveDoorShareMember(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_GET_FANGKEOPENCODE)
    Observable<OpenDoorCode> getVisitorQRCode(@FieldMap Map<String, RequestBody> params);

    @GET(URL_findall_chengyuan)
    Observable<AppYezhuFangwu> getAllLiveMember(@QueryMap Map<String, RequestBody> params);

    //公共安防的api
    @FormUrlEncoded
    @POST(URL_COMMON_Token)
    Observable<CxwyCommonToken> getCommonToken(@FieldMap Map<String, RequestBody> params);

    //公共安防的api
//    @FormUrlEncoded
    @GET(URL_COMMON)
    Observable<CxwyCommon> getCommonHide(@QueryMap Map<String, RequestBody> params);

    //添加入住成员
    @FormUrlEncoded
    @POST(URL_add_chengyuan)
    Observable<BaseBack1> addChengyuan(@FieldMap Map<String, RequestBody> params);


    //欣周边
    @GET(URL_BUSINESS_LIST)
    Observable<CxwyBusiness> getBusinessList(@QueryMap Map<String, RequestBody> params);

    //获取商家信息
    @GET(URL_BUSINESS_INFO)
    Observable<CxwyBusinessInfo> getBusinessInfo(@QueryMap Map<String, RequestBody> params);

    //获取商品分类信息
    @GET(URL_CLASSIFYINFO)
    Observable<CxwyClassifyInfo> getClassifyinfo(@QueryMap Map<String, RequestBody> params);

    //获取最终商品信息
    @GET(URL_PRODUCTINFO)
    Observable<CxwyProductInfo> getProductinfo(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_APPOINTMENT)
    Observable<PushOrder> addOrder(@FieldMap Map<String, RequestBody> params);

    @GET(URL_GET_ALL_ORDER_COMPLAIN)
    Observable<SJComplain> getRimComplain(@QueryMap Map<String, RequestBody> params);


    @GET(URL_GET_ALL_ORDER_LIST)
    Observable<SJOrder> getRimComment(@QueryMap Map<String, RequestBody> params);

    @GET(URL_ORDERINFO)
    Observable<CxwyOrderInfo> getOrderInfo(@QueryMap Map<String, RequestBody> params);

    @GET(URL_GET_ALL_COMPLAIN_DETAIL)
    Observable<SJComplain> getRimComplainDetail(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_RIM_ORDER_CANCEL)
    Observable<BaseEntityService> rimCancelOrder(@FieldMap Map<String, RequestBody> params);


    //注意：通知接口暂没有，正式需更换路径
    @GET(URL_GET_ALL_ORDER_LIST)
    Observable<SJOrder> getRimTongzhi(@QueryMap Map<String, RequestBody> params);

    @GET(URL_GET_ALL_ORDER_LIST)
    Observable<SJOrder> getRimOrderList(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_GET_ADD_ORDER_COMPLAIN)
    Observable<BaseEntityService> addRimComplain(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_GET_ALL_ORDER_COMMENT)
    Observable<BaseEntityService> addRimComment(@FieldMap Map<String, RequestBody> params);

    @GET(URL_GET_ALL_CONFIRM_ORDER)
    Observable<BaseEntityService> requestConfirOrder(@QueryMap Map<String, RequestBody> params);

    @GET(URL_GET_ALL_ORDER_DYNAMIC)
    Observable<SJOrderStatus> getRimOrderState(@QueryMap Map<String, RequestBody> params);

    @GET(URL_GET_BUSINESS_FIRSTINFO)
    Observable<RimShopDetailBean> getRimShopDetail(@QueryMap Map<String, RequestBody> params);

    @GET(URL_GET_RIM_SHOP_COMMENTLIST)
    Observable<RimCommentListBean> getRimShopCommentList(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_GET_RIM_ACTIVITY_DISCOUNT)
    Observable<RimActivityDiscount> getRimActivityDiscount(@FieldMap Map<String, RequestBody> params);

    @GET(URL_DELETE_RIMORDER)
    Observable<RimDeleteOrderBean> deleteRimOrder(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_ORDER_BUYCHECK)
    Observable<BaseEntityService> getOrderBayCheck(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_ADD_SHOPCAR)
    Observable<BaseEntity> addShopCar(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_SHOP_CAR_LIST)
    Observable<ShopCarList> getShopCarList(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_DELETE_SHOP_CAR)
    Observable<BaseEntity> deleteShopCar(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_UPDATA_SHOP_CAR)
    Observable<BaseEntity> updataShopCar(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_DETAILS_TO_CAR)
    Observable<BaseEntity> detailsToCar(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_UPDATA_ORDER)
    Observable<BaseEntity> updataOrder(@FieldMap Map<String, RequestBody> params);

    @GET(URL_RIM_ORDER_COMMENT)
    Observable<RimOrderCommentBean> getRimOrderComment(@QueryMap Map<String, RequestBody> params);

    /****************************居家安防********************************/
    @FormUrlEncoded
    @POST(URL_GET_CAMERA)
    Observable<APPCamera> getCameraLogin(@FieldMap Map<String, RequestBody> params);

    @GET(URL_GET_CAMERA_All)
    Observable<AppCameraList> getAllCamera(@QueryMap Map<String, RequestBody> params);

    @GET(url_get_jiashu)
    Observable<ShareFamily> getCameraShareFamily(@QueryMap Map<String, RequestBody> params);

    @GET(url_get_fenxiang_duixiang)
    Observable<Shared> getShared(@QueryMap Map<String, RequestBody> params);

    @GET(url_camera_add_fenxiang)
    Observable<BaseEntity> addCameraShare(@QueryMap Map<String, RequestBody> params);

    @GET(URL_GET_CAMERA_UPDATE)
    Observable<BaseEntity> getCameraUpdate(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_GET_CAMERA_ADD)
    Observable<BaseEntity> getCameraAdd(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_GET_CAMERA_DEL)
    Observable<BaseEntity> deletCamera(@FieldMap Map<String, RequestBody> params);
    /****************************居家安防********************************/


    /**
     * 欣商城
     **/
    @GET(API.URL_GET_SC_LUNBO_TUBIAO)
    Observable<CxwyMallPezhi> getNoramlMenus(@Query("uuid") String uuid, @Query("xmid") int xmid);

    @FormUrlEncoded
    @POST(API.URL_INDEX_GOODS_LIST)
    Observable<ShopList> getMiaoShaProducts(@FieldMap Map<String, RequestBody> params);

    @GET(API.URL_GET_ALL_SHOPLIST_BY_KEY)
    Observable<ShopList> getGoodsListBySearchKey(@QueryMap Map<String, RequestBody> params);

    @GET(API.URL_ADD_INFO_TO_CART)
    Observable<ShopList> add2ShopCart(@QueryMap Map<String, RequestBody> params);

    @GET(API.URL_GET_INFO__CART_FROM_USERID)
    Observable<CxwyMallCart> getShopCart(@QueryMap Map<String, RequestBody> params);

    @GET(API.URL_UPDATE_INFO__CART_FROM_ID)
    Observable<BaseEntity> changeCartCount(@QueryMap Map<String, RequestBody> params);

    @GET(API.URL_DELETE_INFO__CART_FROM_ID)
    Observable<BaseEntity> deleteProductsFromCart(@QueryMap Map<String, RequestBody> params);

    @GET(API.USER_GET_YEZHU_DIANZIQUAN)
    Observable<YezhuDainZhiQuan> getYeZhuDianZiQuan(@QueryMap Map<String, RequestBody> params);

    @GET(USR_GET_YEZHU_DIANZHIQUAN)
    Observable<OrderRemainDianZiQuanEntity> getOrderRemainDianZiQuan(@QueryMap Map<String,
            RequestBody> params);


    @GET(API.URL_GET_ADDRESS_LIST_FROM_USER)
    Observable<CxwyMallAdd> getAddressList(@QueryMap Map<String, RequestBody> params);

    @GET(API.URL_UPDATE_ADDRESS)
    Observable<CxwyMallAdd> updateAddress(@QueryMap Map<String, RequestBody> params);

    @GET(API.URL_ADD_ADDRESS)
    Observable<BaseEntity> addAddress(@QueryMap Map<String, RequestBody> params);

    @GET(API.URL_DELETE_ADDRESS_FROM_ID)
    Observable<BaseEntity> deleteAddress(@QueryMap Map<String, RequestBody> params);

    @GET(API.URL_ADD_ORDER)
    Observable<CxwyMallAdd> addMallOrder(@QueryMap Map<String, RequestBody> params);

    @GET(API.URL_GET_ORDER_LIST_FROM_USER)
    Observable<CxwyMallOrder> getOrderList(@QueryMap Map<String, RequestBody> params);

    @GET(API.URL_GET_ORDER_DESTAIL_FROM_ID)
    Observable<OrderDetailEntity> getOrderDetail(@QueryMap Map<String, RequestBody> params);

    @GET(API.URL_UPDATE_ORDER_STATE_FROM_ID)
    Observable<BaseEntity> updateOrderStatus(@QueryMap Map<String, RequestBody> params);

    @GET(API.URL_TUIKUAN_ORDER_STATE_FROM_ID)
    Observable<BaseEntity> updateOrderStatusForTuiKuan(@QueryMap Map<String, RequestBody> params);

    @GET(API.URL_GET_ORDER_KUNCUN_FROM_ID)
    Observable<BaseEntity> checkPayNow(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(API.URL_PRAISE_GOODS_FROM_USER)
    Observable<BaseEntity> addCommnent(@FieldMap Map<String, RequestBody> params);


    //    @GET("http://192.168.2.121:8080/wygl/mall/comment_findComment")
    @GET(API.URL_PRAISE_LIST_FROM_GOOD)
    Observable<CxwyMallComment> getComment(@QueryMap Map<String, RequestBody> params);

    @GET(API.USR_GET_YEZHU_DINGDAN_NUM)
    Observable<StateOrderNum> getMineOrderStatus(@QueryMap Map<String, RequestBody> params);
    //StateOrderNum

    @GET(API.URL_GETPRODUCT_BYGOODID)
    Observable<CxwyMallProduct> getProductDetail(@QueryMap Map<String, RequestBody> params);

    @GET(API.USR_GET_YEZHU_DIANZHIQUAN_JILU)
    Observable<CxwyDianziquan> getDianZiQuanList(@QueryMap Map<String, RequestBody> params);


    //CxwyComplain
    @GET(API.URL_DINGDANTOUSU_WEICHULI)
    Observable<OrderComplainEntity> getOrderComplainNotDeal(@QueryMap Map<String, RequestBody>
                                                                    params);

    @GET(API.URL_DINGDANTOUSU_CHULIZHONG)
    Observable<OrderComplainEntity> getOrderComplainDealing(@QueryMap Map<String, RequestBody>
                                                                    params);

    @GET(API.URL_DINGDANTOUSU_YICHULI)
    Observable<OrderComplainEntity> getOrderComplainDealed(@QueryMap Map<String, RequestBody>
                                                                   params);


    /**欣商城**/
    /****************************支付********************************/
    @GET(URL_UPDATE_ORDER_STATE_FROM_ID)
    Observable<BaseEntity> updateOrderByAlipySuccess(@QueryMap Map<String, RequestBody> params);

    /****************************支付********************************/


    @GET(URL_XIONGMAI_SXT)
    Observable<XMsxt> getXMsxt(@QueryMap Map<String, RequestBody> params);


    @GET(PAIAN_SEARCH_ALLHOST)
    Observable<HostEntiti> searchAllHost(@QueryMap Map<String, RequestBody> params);


    @GET(PAIAN_BUCHEFANG)
    Observable<BaseEntity> buCheFang(@QueryMap Map<String, RequestBody> params);

    @GET(PAIAN_FINDSHEBEILIST)
    Observable<FangquEntity> getFangqu(@QueryMap Map<String, RequestBody> params);

    @GET(PAIAN_SELECTDINGSHIBUCHEFANG)
    Observable<BuCheFang> selectTimingBufang(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(PAIAN_GETMONEY)
    Observable<XuFeiBean> getMoney(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(PAIAN_JIAOFEIORDER)
    Observable<XuFeiOrder> getJiaoFeiOrder(@FieldMap Map<String, RequestBody> params);

    @GET(PAIAN_ZONEINTERVA)
    Observable<BaseEntity> cacanlDingshiCheBuFang(@QueryMap Map<String, RequestBody> params);

    @GET(PAIAN_ALARMHISTORY)
    Observable<BaoJingEntity> getBaoJingList(@QueryMap Map<String, RequestBody> params);

    @GET(PAIAN_LEIXIN)
    Observable<BaseEntity> mingDi(@QueryMap Map<String, RequestBody> params);

    @GET(PAIAN_FIND_SHARE)
    Observable<PaianYijiaJiashu> findShare(@QueryMap Map<String, RequestBody> params);

    @GET(PAIAN_SAVE_SHARE)
    Observable<BaseEntity> saveShare(@QueryMap Map<String, RequestBody> params);


    @GET(PAIAN_UPDATE_FANGQU)
    Observable<BaseEntity> updateFangqu(@QueryMap Map<String, RequestBody> params);

    @GET(PAIAN_TIMINGDISARMTIMESETTING)
    Observable<BaseEntity> saveBufang(@QueryMap Map<String, RequestBody> params);

    @GET(PAIAN_FIND_YEZHU_JIASHU)
    Observable<PaianYezhuJiashu> findYezhuJiashu(@QueryMap Map<String, RequestBody> params);

    @GET(url_shangcheng_fenlei)
    Observable<MallClassify> getShangchengFenlei(@QueryMap Map<String, RequestBody> params);

    @GET(url_my_evaluate)
    Observable<MyAllComment> getMyEvaluate(@QueryMap Map<String, RequestBody> params);

    @GET(url_goods_kinds)
    Observable<GoodsKind> getGoodsKinds(@QueryMap Map<String, RequestBody> params);

    @GET(url_goods_list)
    Observable<ShopNewList> getGoodsList(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(url_mall_order)
    Observable<MallNewOrder> getMallOrder(@FieldMap Map<String, RequestBody> params);

    @GET(url_goods_info)
    Observable<MallNewProduct1> getGoodsInfo(@QueryMap Map<String, RequestBody> params);

    @GET(url_goods_appraise)
    Observable<MyAllComment> getGoodsAppraise(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(url_shangcheng_shouhou)
    Observable<BaseEntity> getShangchengShouhou(@FieldMap Map<String, RequestBody> params);

    @GET(url_get_shop_cart_list)
    Observable<ShopCart> getShopCart1(@QueryMap Map<String, RequestBody> params);

    @GET(url_isNight)
    Observable<IsNight> isNight(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(url_shop_cart_edit)
    Observable<ShopCart> getShopCartUpdate(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(url_shop_cart_delete)
    Observable<BaseEntity> getShopCartDelete(@FieldMap Map<String, RequestBody> params);

    @GET(url_goods_dianzijuan)
    Observable<ShopDianZiJuan> getShopDizijuan(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(url_goods_order_add)
    Observable<OrderRemainDianZiQuanEntity> jieSuanShopCart(@FieldMap Map<String, RequestBody>
                                                                    params);

    @FormUrlEncoded
    @POST(url_shop_cart_add)
    Observable<BaseEntity> addGood2ShopCart(@FieldMap Map<String, RequestBody> params);

    @GET(url_goods_order_cancel)
    Observable<BaseEntity> cancelOrder(@QueryMap Map<String, RequestBody> params);

    @GET(url_goods_order_hidden)
    Observable<BaseEntity> hiddenOrder(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(url_goods_order_suggest)
    Observable<BaseEntity> suggestOrder(@FieldMap Map<String, RequestBody> params);

    @GET(url_goods_order_count)
    Observable<StateOrderNum> getOrderCount(@QueryMap Map<String, RequestBody> params);

    @GET(url_goods_banner)
    Observable<ShopNewList> getGoodsBanner(@QueryMap Map<String, RequestBody> params);

    @GET(url_goods_order_suggest_list)
    Observable<MallOrderSuggest> getSuggestList(@QueryMap Map<String, RequestBody> params);

    @GET(url_goods_dianzijuan_list)
    Observable<CxwyDianziquan> getDianzijuanList(@QueryMap Map<String, RequestBody> params);

    @GET(url_mall_suggest_list)
    Observable<MallOrderSuggest> getMallSuggestList(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(url_mall_commit_suggest)
    Observable<BaseEntity> commitMallSuggest(@FieldMap Map<String, RequestBody> params);

    @GET(url_goods_get_order)
    Observable<BaseEntityAll> getGoodsOrder(@QueryMap Map<String, RequestBody> params);

    @GET(url_goods_is_xiajia)
    Observable<BaseEntityAll> getGoodsXiaJia(@QueryMap Map<String, RequestBody> params);

    @GET(url_get_dianzijuan_guizei)
    Observable<DiZiJuanGuiZei> getDiZiQuanGuiZei(@QueryMap Map<String, RequestBody> params);

    @GET(url_get_pos_order)
    Observable<MallNewOrder> getPosOrder(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(url_confirm_order)
    Observable<BaseEntityAll> confirmOrder(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_DONGSHENG_AD)
    Observable<LocalAd> getLocalAd(@FieldMap Map<String, RequestBody> params);
    @GET(URL_WEIXIU_LIUCHENG)
    Observable<LiuCheng> getWeiXiuLiucheng(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(url_ywh_lcxx)
    Observable<YwhInfo> getLcxx(@FieldMap Map<String, RequestBody> params);
    @FormUrlEncoded
    @POST(url_ywh_tjcbz)
    Observable<BaseEntity> getTjcbz(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(url_ywh_tjcbz_list)
    Observable<YwhTj> getTjcbzList(@FieldMap Map<String, RequestBody> params);
    @GET(url_ywh_gsmd)
    Observable<BaseEntity> getGsmd(@QueryMap Map<String, RequestBody> params);
    @FormUrlEncoded
    @POST(url_ywh_tjyj)
    Observable<BaseEntity> getTjyj(@FieldMap Map<String, RequestBody> params);
    @FormUrlEncoded
    @POST(url_ywh_lhlb)
    Observable<YwhFkyj> getLhlb(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(url_ywh_smrz)
    Observable<BaseEntity> getSmrz(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(url_ywh_smrz_change)
    Observable<BaseEntity> getSmrzCChange(@FieldMap Map<String, RequestBody> params);

    @GET(url_ywh_smrz_detail)
    Observable<YwhHouse> getSmrzDetail(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(url_ywh_house)
    Observable<YwhHouse> getHouseList(@FieldMap Map<String, RequestBody> params);
    /****************************业委会********************************/

    @FormUrlEncoded
    @POST(URL_YWH_CURRENTFLOW)
    Observable<YwhCurrentflow> getYwhCurrentflow(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_YWH_GETFKYJLIST)
    Observable<YwhFkyj> getFkyjList(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_YWH_COMMITFKYJ)
    Observable<BaseEntity> commitFkyj(@FieldMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_YWH_COMMITFKYJ2+"/"+"{fwbh}"+"/"+"{uuid}")
    Observable<BaseEntity> commitFkyj2(@Path("fwbh") String fwid,@Path("uuid") String uuid, @FieldMap Map<String, RequestBody> params);

    @GET(URL_YWH_GETMEMBERSHOWLIST)
    Observable<BaseEntity> getMemberShowList(@QueryMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST(URL_YWH_GETSURVEYLIST)
    Observable<OpinionSurveyEntity> getSurveyList(@FieldMap Map<String, RequestBody> params);

    /****************************业委会********************************/
}
