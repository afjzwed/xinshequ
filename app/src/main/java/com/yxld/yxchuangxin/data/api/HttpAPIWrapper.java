package com.yxld.yxchuangxin.data.api;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
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

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hu
 * @desc 对Request实体(不执行)在执行时所调度的线程，以及得到ResponseBody后根据retCode对Result进行进一步处理
 * @date 2017/5/31 16:56
 */
public class HttpAPIWrapper {

    private HttpApi mHttpAPI;

    @Inject
    public HttpAPIWrapper(HttpApi mHttpAPI) {
        this.mHttpAPI = mHttpAPI;
    }

    //*********首页******//
    public Observable<CxwyMallPezhi> getBanner(Map data) {
        return wrapper(mHttpAPI.getBanner(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> getAction(Map data) {
        return wrapper(mHttpAPI.getAction(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    //*********首页******//

    /****************************账户相关*****************************/
    public Observable<BaseEntity> getPassUpdate(Map data) {
        return wrapper(mHttpAPI.getPassUpdate(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> getLoginOut(Map data) {
        return wrapper(mHttpAPI.getLoginOut(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> getExistShouji(Map data) {
        return wrapper(mHttpAPI.getExistShouji(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> getFindPwd(Map data) {
        return wrapper(mHttpAPI.getFindPwd(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyAppVersion> getAppVersionInfo(Map data) {
        return wrapper(mHttpAPI.getAppVersionInfo(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    /****************************账户相关*****************************/
    /****************************物业相关*****************************/
    public Observable<BaseEntity> deletLiveMember(Map data) {
        return wrapper(mHttpAPI.deletLiveMember(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    /****************************物业相关*****************************/

    public Observable<LoginEntity> Login(Map data) {
        return wrapper(mHttpAPI.Login(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<LoginPhoneEntity> getloginPlot(Map data) {
        return wrapper(mHttpAPI.getloginPlot(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<OpenDoorCode> getQRCode(Map data) {
        return wrapper(mHttpAPI.getQRCode(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    public Observable<DoorInfo> getDoorList(Map data) {
        return wrapper(mHttpAPI.getDoorList(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    public Observable<BaseBack2> getDoorMima(Map data) {
        return wrapper(mHttpAPI.getDoorMima(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    public Observable<MenJinShareMemberBean> getDoorShareMember(Map data) {
        return wrapper(mHttpAPI.getDoorShareMember(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    public Observable<BaseEntity> saveDoorShareMember(Map data) {
        return wrapper(mHttpAPI.saveDoorShareMember(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    public Observable<OpenDoorCode> getVisitorQRCode(Map data) {
        return wrapper(mHttpAPI.getVisitorQRCode(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<AppYezhuFangwu> getAllLiveMember(Map data) {
        return wrapper(mHttpAPI.getAllLiveMember(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //公共安防的api
    public Observable<CxwyCommonToken> getCommonToken(Map data) {
        return wrapperObject(mHttpAPI.getCommonToken(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyCommon> getCommonHide(Map data) {
        return wrapper(mHttpAPI.getCommonHide(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //添加成员
    public Observable<BaseBack1> addChengyuan(Map data) {
        return wrapper(mHttpAPI.addChengyuan(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边
    public Observable<CxwyBusiness> getBusinessList(Map data) {
        return wrapper(mHttpAPI.getBusinessList(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyBusinessInfo> getBusinessInfo(Map data) {
        return wrapper(mHttpAPI.getBusinessInfo(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<RimShopDetailBean> getRimShopDetail(Map data) {
        return wrapper(mHttpAPI.getRimShopDetail(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<RimCommentListBean> getRimShopCommentList(Map data) {
        return wrapper(mHttpAPI.getRimShopCommentList(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<RimActivityDiscount> getRimActivityDiscount(Map data) {
        return wrapper(mHttpAPI.getRimActivityDiscount(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyClassifyInfo> getClassifyinfo(Map data) {
        return wrapper(mHttpAPI.getClassifyinfo(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyProductInfo> getProductinfo(Map data) {
        return wrapper(mHttpAPI.getProductinfo(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<PushOrder> addOrder(Map data) {
        return wrapper(mHttpAPI.addOrder(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -投诉列表
    public Observable<SJComplain> getRimComplain(Map data) {
        return wrapper(mHttpAPI.getRimComplain(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -评论列表
    public Observable<SJOrder> getRimComment(Map data) {
        return wrapper(mHttpAPI.getRimComment(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -通知列表
    public Observable<SJOrder> getRimTongzhi(Map data) {
        return wrapper(mHttpAPI.getRimTongzhi(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -订单列表
    public Observable<SJOrder> getRimOrderList(Map data) {

        return wrapper(mHttpAPI.getRimOrderList(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -添加投诉
    public Observable<BaseEntityService> addRimComplain(Map data) {

        return wrapper(mHttpAPI.addRimComplain(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -订单动态跟踪
    public Observable<SJOrderStatus> getRimOrderState(Map data) {

        return wrapper(mHttpAPI.getRimOrderState(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -添加评论
    public Observable<BaseEntityService> addRimComment(Map data) {

        return wrapper(mHttpAPI.addRimComment(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -确认订单
    public Observable<BaseEntityService> requestConfirOrder(Map data) {

        return wrapper(mHttpAPI.requestConfirOrder(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -获取订单详情
    public Observable<CxwyOrderInfo> getOrderInfo(Map data) {
        return wrapper(mHttpAPI.getOrderInfo(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -获取订单投诉详情
    public Observable<SJComplain> getRimComplainDetail(Map data) {
        return wrapper(mHttpAPI.getRimComplainDetail(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -取消订单
    public Observable<BaseEntityService> rimCancelOrder(Map data) {
        return wrapper(mHttpAPI.rimCancelOrder(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -删除订单
    public Observable<RimDeleteOrderBean> deleteRimOrder(Map data) {
        return wrapper(mHttpAPI.deleteRimOrder(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -查询商品库存
    public Observable<BaseEntityService> getOrderBayCheck(Map data) {
        return wrapper(mHttpAPI.getOrderBayCheck(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -添加商品到购物车
    public Observable<BaseEntity> addShopCar(Map data) {
        return wrapper(mHttpAPI.addShopCar(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -查询购物车列表
    public Observable<ShopCarList> getShopCarList(Map data) {
        return wrapper(mHttpAPI.getShopCarList(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    //欣周边 -删除购物车商品
    public Observable<BaseEntity> deleteShopCar(Map data) {
        return wrapper(mHttpAPI.deleteShopCar(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    //欣周边 -删除购物车商品
    public Observable<BaseEntity> updataShopCar(Map data) {
        return wrapper(mHttpAPI.updataShopCar(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    //欣周边 -删除购物车商品
    public Observable<BaseEntity> detailsToCar(Map data) {
        return wrapper(mHttpAPI.detailsToCar(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    //欣周边 -删除购物车商品
    public Observable<BaseEntity> updataOrder(Map data) {
        return wrapper(mHttpAPI.updataOrder(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    //欣周边 -单个订单的评价列表
    public Observable<RimOrderCommentBean> getRimOrderComment(Map data) {
        return wrapper(mHttpAPI.getRimOrderComment(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    /****************************居家安防**********************************/

    /***************************支付*************************************/
    //支付宝支付成功
    public Observable<BaseEntity> updateOrderByAlipySuccess(Map data) {
        return wrapper(mHttpAPI.updateOrderByAlipySuccess(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    /***************************支付*************************************/
    /**
     * 登录技威
     *
     * @param data
     * @return
     */
    public Observable<APPCamera> getCameraLogin(Map data) {
        return wrapper(mHttpAPI.getCameraLogin(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<AppCameraList> getAllCamera(Map data) {
        return wrapper(mHttpAPI.getAllCamera(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<ShareFamily> getCameraShareFamily(Map data) {
        return wrapper(mHttpAPI.getCameraShareFamily(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<Shared> getShared(Map data) {
        return wrapper(mHttpAPI.getShared(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> addCameraShare(Map data) {
        return wrapper(mHttpAPI.addCameraShare(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<AppCameraList> getCameraUpdate(Map data) {
        return wrapper(mHttpAPI.getCameraUpdate(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<AppCameraList> getCameraAdd(Map data) {
        return wrapper(mHttpAPI.getCameraAdd(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> deletCamera(Map data) {
        return wrapper(mHttpAPI.deletCamera(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }
    /****************************居家安防**********************************/
    /****************************综合服务**********************************/
    public Observable<CxwyMessage> getMessage(Map data) {
        return wrapper(mHttpAPI.getMessage(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyMessage> getActivity(Map data) {
        return wrapper(mHttpAPI.getActivity(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> saveTousuAndJianYi(Map data) {
        return wrapper(mHttpAPI.saveTousuAndJianYi(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyComplain> getTousuAndJianYiList(Map data) {
        return wrapper(mHttpAPI.getTousuAndJianYiList(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<MsgAndSuccess> saveManYiDu(String data) {
        return wrapperObject(mHttpAPI.saveManYiDu(data)).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> saveMallTousuAndJianYi(Map data) {
        return wrapper(mHttpAPI.saveMallTousuAndJianYi(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> getManYiDuTiaoChaExist(Map data) {
        return wrapper(mHttpAPI.getManYiDuTiaoChaExist(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<QiniuToken> getQiniuToken(Map data) {
        return wrapper(mHttpAPI.getQiniuToken(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> getRepairPSubmit(Map data) {
        return wrapper(mHttpAPI.getRepairPSubmit(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyBaoxiu> getRepairAllList(Map data) {
        return wrapper(mHttpAPI.getRepairAllList(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyBaoxiu> getRepairOtherList(Map data) {
        return wrapper(mHttpAPI.getRepairOtherList(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<WyFwApp> getHouse(Map data) {
        return wrapper(mHttpAPI.getHouse(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<AppJiaofei> getWuyePay(Map data) {
        return wrapper(mHttpAPI.getWuyePay(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<RoomRent> getRentList(Map data) {
        return wrapper(mHttpAPI.getRentList(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> updateRentStatus(Map data) {
        return wrapper(mHttpAPI.updateRentStatus(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<Accredit> getAccreditListProprietor(Map data) {
        return wrapperObject(mHttpAPI.getAccreditListProprietor(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<Accredit> getAccreditListRent(Map data) {
        return wrapperObject(mHttpAPI.getAccreditListRent(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<AccreditDetail> getAccreditDetail(Map data) {
        return wrapperObject(mHttpAPI.getAccreditDetail(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> passAccredit(Map data) {
        return wrapperObject(mHttpAPI.passAccredit(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<MsgAndSuccess> comfirmAccredit(Map data) {
        return wrapperObject(mHttpAPI.comfirmAccredit(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<Onlymsg> mallComplain(Map data) {
        return wrapperObject(mHttpAPI.mallComplain(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> getPaymentList(Map data) {
        return wrapperObject(mHttpAPI.getPaymentList(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<JiaofeiMingxi> getJiaofei(Map data) {
        return wrapperObject(mHttpAPI.getJiaofei(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> getArrearageList(Map data) {
        return wrapperObject(mHttpAPI.getArrearageList(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CarList> getCarList(Map data) {
        return wrapperObject(mHttpAPI.getCarList(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CarJiaofeiRecord> getCarMoneyRecord(Map data) {
        return wrapperObject(mHttpAPI.getCarMoneyRecord(addParams(data))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<LockCar> carLockControl(Map data) {
        return wrapperObject(mHttpAPI.carLockControl(data)).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseBack> carAffordMoney(Map data) {
        return wrapperObject(mHttpAPI.carAffordMoney(data)).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<PrePay> carPreChongzhi(Map data) {
        return wrapperObject(mHttpAPI.carPreChongzhi(data)).compose(SCHEDULERS_TRANSFORMER);
    }
    /****************************综合服务**********************************/


    /******************************欣商城*********************************************/
    public Observable<CxwyMallPezhi> getNoramlMenus(String uuid, int xmid) {
        return wrapper(mHttpAPI.getNoramlMenus(uuid, xmid)).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<ShopList> getMiaoShaProduct(Map<String, String> params) {
        return wrapper(mHttpAPI.getMiaoShaProducts(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<ShopList> getGoodsListBySearchKey(Map<String, String> params) {
        return wrapper(mHttpAPI.getGoodsListBySearchKey(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> add2ShopCart(Map<String, String> params) {
        return wrapper(mHttpAPI.add2ShopCart(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyMallCart> getShopCart(Map<String, String> params) {
        return wrapper(mHttpAPI.getShopCart(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> changeCartCount(Map<String, String> params) {
        return wrapper(mHttpAPI.changeCartCount(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> deleteProductFromCart(Map<String, String> params) {
        return wrapper(mHttpAPI.deleteProductsFromCart(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<YezhuDainZhiQuan> getYeZhuDianZiQuan(Map<String, String> params) {
        return wrapper(mHttpAPI.getYeZhuDianZiQuan(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<OrderRemainDianZiQuanEntity> getOrderRemainDianZiQuan(Map<String, String> params) {
        return wrapper(mHttpAPI.getOrderRemainDianZiQuan(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyMallAdd> getAddressList(Map<String, String> params) {
        return wrapper(mHttpAPI.getAddressList(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> addAddress(Map<String, String> params) {
        return wrapper(mHttpAPI.addAddress(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> deleteAddress(Map<String, String> params) {
        return wrapper(mHttpAPI.deleteAddress(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> updateAddress(Map<String, String> params) {
        return wrapper(mHttpAPI.updateAddress(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> addMallOrder(Map<String, String> params) {
        return wrapper(mHttpAPI.addMallOrder(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyMallOrder> getOrderList(Map<String, String> params) {
        return wrapper(mHttpAPI.getOrderList(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<OrderDetailEntity> getOrderDetail(Map<String, String> params) {
        return wrapper(mHttpAPI.getOrderDetail(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> updateOrderStatus(Map<String, String> params) {
        return wrapper(mHttpAPI.updateOrderStatus(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> updateOrderStatusForTuiKuan(Map<String, String> params) {
        return wrapper(mHttpAPI.updateOrderStatusForTuiKuan(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> checkPayNow(Map<String, String> params) {
        return wrapper(mHttpAPI.checkPayNow(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> addComment(Map<String, String> params) {
        return wrapper(mHttpAPI.addCommnent(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyMallComment> getComments(Map<String, String> params) {
        return wrapper(mHttpAPI.getComment(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyMallProduct> getProductDetail(Map<String, String> params) {
        return wrapper(mHttpAPI.getProductDetail(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }


    public Observable<StateOrderNum> getMineOrderStatus(Map<String, String> params) {
        return wrapper(mHttpAPI.getMineOrderStatus(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyDianziquan> getDianZiQuanList(Map<String, String> params) {
        return wrapper(mHttpAPI.getDianZiQuanList(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<OrderComplainEntity> getOrderComplainNotDeal(Map<String, String> params) {
        return wrapper(mHttpAPI.getOrderComplainNotDeal(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<OrderComplainEntity> getOrderComplainDealing(Map<String, String> params) {
        return wrapper(mHttpAPI.getOrderComplainDealing(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<OrderComplainEntity> getOrderComplainDealed(Map<String, String> params) {
        return wrapper(mHttpAPI.getOrderComplainDealed(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<XMsxt> getXMsxt(Map<String, String> params) {
        return wrapperObject(mHttpAPI.getXMsxt(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    /******************************欣商城*********************************************/

    //*********************************派安******************************************//
    public Observable<HostEntiti> searchAllHost(Map<String, String> params) {
        return wrapper(mHttpAPI.searchAllHost(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> buCheFang(Map<String, String> params) {
        return wrapper(mHttpAPI.buCheFang(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<FangquEntity> getFangqu(Map<String, String> params) {
        return wrapper(mHttpAPI.getFangqu(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> saveBufang(Map<String, String> params) {
        return wrapper(mHttpAPI.saveBufang(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BuCheFang> selectTimingBufang(Map<String, String> params) {
        return wrapper(mHttpAPI.selectTimingBufang(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<XuFeiBean> getMoney(Map<String, String> params) {
        return wrapper(mHttpAPI.getMoney(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<XuFeiOrder> getJiaoFeiOrder(Map<String, String> params) {
        return wrapperObject(mHttpAPI.getJiaoFeiOrder(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> cacanlDingshiCheBuFang(Map<String, String> params) {
        return wrapper(mHttpAPI.cacanlDingshiCheBuFang(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaoJingEntity> getBaoJingList(Map<String, String> params) {
        return wrapper(mHttpAPI.getBaoJingList(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> mingDi(Map<String, String> params) {
        return wrapper(mHttpAPI.mingDi(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<PaianYijiaJiashu> findShare(Map<String, String> params) {
        return wrapper(mHttpAPI.findShare(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> saveShare(Map<String, String> params) {
        return wrapper(mHttpAPI.saveShare(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> updateFangqu(Map<String, String> params) {
        return wrapper(mHttpAPI.updateFangqu(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<PaianYezhuJiashu> findYezhuJiashu(Map<String, String> params) {
        return wrapper(mHttpAPI.findYezhuJiashu(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }
    //*********************************派安******************************************//

    //*********************************商城改版******************************************//
    public Observable<MallClassify> getShangchengFenlei(Map<String, String> params) {
        return wrapper(mHttpAPI.getShangchengFenlei(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<MyAllComment> getMyEvaluate(Map<String, String> params) {
        return wrapper(mHttpAPI.getMyEvaluate(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<GoodsKind> getGoodsKinds(Map<String, String> params) {
        return wrapper(mHttpAPI.getGoodsKinds(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<ShopNewList> getGoodsList(Map<String, String> params) {
        return wrapper(mHttpAPI.getGoodsList(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<MallNewOrder> getMallOrder(Map<String, String> params) {
        return wrapper(mHttpAPI.getMallOrder(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<MallNewProduct1> getGoodsInfo(Map<String, String> params) {
        return wrapper(mHttpAPI.getGoodsInfo(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<MyAllComment> getGoodsAppraise(Map<String, String> params) {
        return wrapper(mHttpAPI.getGoodsAppraise(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> getShangchengShouhou(Map<String, String> params) {
        return wrapper(mHttpAPI.getShangchengShouhou(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<ShopCart> getShopCart1(Map<String, String> params) {
        return wrapper(mHttpAPI.getShopCart1(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<IsNight> isNight(Map<String, String> params) {
        return wrapper(mHttpAPI.isNight(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<ShopCart> getShopCartUpdate(Map<String, String> params) {
        return wrapper(mHttpAPI.getShopCartUpdate(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> getShopCartDeLelte(Map<String, String> params) {
        return wrapper(mHttpAPI.getShopCartDelete(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<ShopDianZiJuan> getShopDizijuan(Map<String, String> params) {
        return wrapper(mHttpAPI.getShopDizijuan(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<OrderRemainDianZiQuanEntity> jieSuanShopCart(Map<String, String> params) {
        return wrapper(mHttpAPI.jieSuanShopCart(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> addGood2ShopCart(Map<String, String> params) {
        return wrapper(mHttpAPI.addGood2ShopCart(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> cancelOrder(Map<String, String> params) {
        return wrapper(mHttpAPI.cancelOrder(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> hiddenOrder(Map<String, String> params) {
        return wrapper(mHttpAPI.hiddenOrder(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> suggestOrder(Map<String, String> params) {
        return wrapper(mHttpAPI.suggestOrder(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<StateOrderNum> getOrderCount(Map<String, String> params) {
        return wrapper(mHttpAPI.getOrderCount(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<ShopNewList> getGoodsBanner(Map<String, String> params) {
        return wrapper(mHttpAPI.getGoodsBanner(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<MallOrderSuggest> getSuggestList(Map<String, String> params) {
        return wrapper(mHttpAPI.getSuggestList(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<CxwyDianziquan> getDianzijuanList(Map<String, String> params) {
        return wrapper(mHttpAPI.getDianzijuanList(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<MallOrderSuggest> getMallSuggestList(Map<String, String> params) {
        return wrapper(mHttpAPI.getMallSuggestList(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntity> commitMallSuggest(Map<String, String> params) {
        return wrapper(mHttpAPI.commitMallSuggest(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntityAll> getGoodsOrder(Map<String, String> params) {
        return wrapper(mHttpAPI.getGoodsOrder(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<BaseEntityAll> getGoodsXiaJia(Map<String, String> params) {
        return wrapper(mHttpAPI.getGoodsXiaJia(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }

    public Observable<DiZiJuanGuiZei> getDiZiQuanGuiZei(Map<String, String> params) {
        return wrapper(mHttpAPI.getDiZiQuanGuiZei(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }
    public Observable<MallNewOrder> getPosOrder(Map<String, String> params) {
        return wrapper(mHttpAPI.getPosOrder(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }
    public Observable<BaseEntityAll> confirmOrder(Map<String, String> params) {
        return wrapper(mHttpAPI.confirmOrder(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }
    //*********************************商城改版******************************************//

    public Observable<LocalAd> getLocalAd(Map<String, String> params) {
        return wrapper(mHttpAPI.getLocalAd(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }
    public Observable<LiuCheng> getWeiXiuLiucheng(Map<String, String> params) {
        return wrapper(mHttpAPI.getWeiXiuLiucheng(addParams(params))).compose(SCHEDULERS_TRANSFORMER);
    }
    /**
     * 给任何Http的Observable加上通用的线程调度器
     */
    private static final ObservableTransformer SCHEDULERS_TRANSFORMER = new ObservableTransformer() {
        @Override
        public ObservableSource apply(@NonNull Observable upstream) {
            return upstream.subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    /**
     * 根据Http的response中关于状态码的描述，自定义生成本地的Exception
     *
     * @param resourceObservable
     * @param <T>
     * @return
     */
    private <T extends BaseEntity> Observable<T> wrapper(Observable<T> resourceObservable) {
        return resourceObservable
                .flatMap(new Function<T, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(@NonNull T baseResponse) throws Exception {
                        return Observable.create(
                                new ObservableOnSubscribe<T>() {
                                    @Override
                                    public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {

                                        if (baseResponse.getStatus() == 99 || baseResponse.getStatus() == -99) {
                                            //99为登录失效，需要重新登录
                                            KLog.i(baseResponse.getMSG());
                                            // TODO: 2017/11/13  防止拦截失效 在各个请求页面做失效处理
                                            e.onNext(baseResponse);
                                            //// TODO: 2017/11/13  这里是发完homeactivity的失效消息暂时不做处理 统一在页面处理
                                            // EventBus.getDefault().post(EventBusEntity.Entity.loginTimeOut);
                                        } else {
                                            e.onNext(baseResponse);
                                        }
                                    }
                                });
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable e) throws Exception {
//                        Log.e("william", e.toString());
                        e.printStackTrace();
                        String errorText = "";
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            KLog.i("服务器出错了");
                            errorText = "服务器出错了";
                        } else if (e instanceof UnknownHostException) {
                            KLog.i("请先打开网络,再重新登录");
                            errorText = "请先打开网络,再重新登录";
                        } else if (e instanceof SocketTimeoutException) {
                            KLog.i("请求超时");
                            errorText = "请求超时";
                        } else if (e instanceof ConnectException) {
                            KLog.i("连接失败");
                            errorText = "连接失败";
                        } else if (e instanceof HttpException) {
                            KLog.i("请求超时");
                            errorText = "请求超时";
                        } else {
                            KLog.i("请求失败");
                            errorText = "请求失败";
                        }
                        ToastUtil.displayShortToast(errorText);
                    }
                });
    }

    /**
     * 根据Http的response中关于状态码的描述，自定义生成本地的Exception
     *
     * @param resourceObservable
     * @param <T>
     * @return
     */
    private <T extends Object> Observable<T> wrapperObject(Observable<T> resourceObservable) {
        return resourceObservable
                .flatMap(new Function<T, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(@NonNull T baseResponse) throws Exception {
                        return Observable.create(
                                new ObservableOnSubscribe<T>() {
                                    @Override
                                    public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
                                        if (baseResponse == null) {

                                        } else {
                                            //// TODO: 2017/6/8 没有做错误处理，因为现在后台返回的结果格式都不一样，等后台统一了返回再做处理
                                            e.onNext(baseResponse);
                                        }
                                    }
                                });
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable e) throws Exception {
                        e.printStackTrace();
                        String errorText = "";
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            KLog.i("服务器出错了");
                            errorText = "服务器出错了";
                        } else if (e instanceof UnknownHostException) {
                            KLog.i("请先打开网络,再重新登录");
                            errorText = "请先打开网络,再重新登录";
                        } else if (e instanceof SocketTimeoutException) {
                            KLog.i("请求超时");
                            errorText = "请求超时";
                        } else if (e instanceof ConnectException) {
                            KLog.i("连接失败");
                            errorText = "连接失败";
                        } else if (e instanceof HttpException) {
                            KLog.i("请求超时");
                            errorText = "请求超时";
                        } else {
                            KLog.i("请求失败");
                            errorText = "请求失败";
                        }
                        ToastUtil.displayShortToast(errorText);
                    }
                });
    }

    /**
     * 给任何Http的Observable加上在Service中运行的线程调度器
     */
    public static <T> ObservableTransformer<T, T> getSchedulerstransFormerToService() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    //需要额外的添加其他的参数进去，所以把原有的参数和额外的参数通过这个方法一起添加进去.
    public static Map addParams(Map<String, String> data) {
        //添加统一的参数的地方
        return data;
    }

}
