package com.yxld.yxchuangxin.data.api;

/**
 * Created by hu on 2017/5/16.
 */

public interface API {
    long CONNECT_TIMEOUT = 30 * 1000;
    long IO_TIMEOUT = 60 * 1000;
    //加载图片
    String PIC = "http://img0.hnchxwl.com/";
    String IP_Camera = "http://api1.cloudlinks.cn";
    //欣周边地址
//    String Periphery = "http://pay.iot.xin/cxwy_consumer_terminal";

    //
//    String IP_PRODUCT = "http://192.168.8.132:8080/wygl";
//    String BASE_URL_DEVOLOP = "http://192.168.8.132:8080/wygl/";
//    String IP_PRODUCT = "http://wy.iot.xin";
//    String BASE_URL_DEVOLOP = "http://wy.iot.xin/";
    //    String IP_PRODUCT = "http://192.168.8.113:8080/wygl";
//    String BASE_URL_DEVOLOP = "http://192.168.8.113:8080/wygl/";
//    String IP_PRODUCT = "http://119.23.162.25";
//    String BASE_URL_DEVOLOP = "http://119.23.162.25/";
    String IP_PRODUCT = "http://192.168.8.222:8080";
    String BASE_URL_DEVOLOP = "http://192.168.8.222:8080/";
    String BASE_URL = IP_PRODUCT + "/";
    String Periphery = IP_PRODUCT + "/cxwy_consumer_terminal";//周边相关
    String daozai= "http://dz.hnchxwl.com";//道闸
//    String daozai= "http://192.168.8.124:8080";//道闸
    /**
     * 两个协议用https的链接
     * 派安缴费协议
     * 登陆平台协议
     * 停车位使用协议
     */
    String HTTPS = "https://wy.iot.xin/";
    String URL_XIEYI = HTTPS + "paianjiaofeixieyi.html";
    String PINGTAI_XIEYI = HTTPS + "userxieyi.jsp";
    String PAY_CAR_XIEYI = daozai+"/cxwy_daozha/protocol.html";
    String URL_SATISFICING = HTTPS + "manyidu.jsp?";


    /**
     * 判断手机号码是否注册找回密码 20170220校验
     */
    String URL_GET_EXIST_SHOUJI = "mall/androidUser_findXmByPhoneNum";
    /**
     * 找回密码 20170220校验
     */
    String URL_GET_FIND_PWD = "mall/androidUser_findPwd";
    /**
     * 根据手机号码查询项目列表 20170220校验
     */
    String URL_GET_LOGIN_PHONE = "mall/androidUser_findXmByPhoneNum";

    /**
     * 登录接口 20170220校验
     * shouji=%1$s&pwd=%2$s&macAddr=%3$s&id=%4$s&app_version=%5$s&mobile_type=%6$s&mobile_brand=%7$s&mobile_version
     * =%8$s"
     */
    String URL_GET_ALL_LOGIN = "mall/androidUser_findUser";

    /**
     * 退出登录接口 20170221 校验新增
     */
    String URL_GET_ALL_OUTLOGIN = "mall/androidUser_logout";
    /**
     * 修改密码 0221校验     --已增加uuid验证
     */
    String URL_GET_ALL_UPDATE_PWD = "mall/androidUser_changePassWord";


    /**
     * 获取商品列表信息通过关键字 0221校验  --已增加uuid验证
     * rows=5&page=1&keys=%E7%8C%AA&sort=shangpinClassicTwo&order=asc&appxiaoqu=
     */
    String URL_GET_ALL_SHOPLIST_BY_KEY = "mall/androidProduct_searchByKeys";

    /***
     * 获取商品详情根据商品ID 0221校验  --已增加uuid验证
     */
    String URL_GETPRODUCT_BYGOODID = "mall/androidProduct_findGoodsById";

    /**
     * 添加商品至购物车 0221 校验     --0222已增加uuid验证
     */
    String URL_ADD_INFO_TO_CART = "mall/cart_addCart";

    /**
     * 根据用户ID查找购物车信息 0221校验  --0222已增加uuid验证
     */
    String URL_GET_INFO__CART_FROM_USERID = "mall/cart_findByUerId";

    /**
     * 根据购物车Id和购物车数量和购物车商品id,修改购物车信息 0221校验  --0222已增加uuid验证
     * ?cart.cartId=%1$s&cart.cartNum=%2$s&cart.cartShangpNum=%3$s&uuid=%4$s
     */
    String URL_UPDATE_INFO__CART_FROM_ID = "mall/cart_mergeCart";

    /**
     * 根据购物车Id删除购物车信息 0221校验    --0222已增加uuid验证
     * deleteCartId=%1$s&uuid=%2$s
     */
    String URL_DELETE_INFO__CART_FROM_ID = "mall/cart_deleteCart?";

    /**
     * 提交订单 0221校验       --0222已增加uuid验证
     */
    String URL_ADD_ORDER = "mall/androidOrder_addOrder?";

    /**
     * 根据业主id和订单状态获取订单列表 0221校验   --0222已增加uuid验证
     */
    String URL_GET_ORDER_LIST_FROM_USER = "mall/androidOrder_findOrderByUserName?";

    /**
     * 根据订单ID和订单状态修改订单状态 0221校验  --0628已增加uuid验证
     */
    String URL_UPDATE_ORDER_STATE_FROM_ID = "mall/androidOrder_mergeOrder?";

    /**
     * 根据订单ID修改用户退款流程（待发货状态下） 0221校验   --0222已增加uuid验证
     */
    String URL_TUIKUAN_ORDER_STATE_FROM_ID = "mall/androidOrder_TuikuanOrder?";

    /**
     * 根据订单ID查询订单详情 0221校验    -0222已增加uuid验证
     */
    String URL_GET_ORDER_DESTAIL_FROM_ID = "mall/androidOrder_findSaleByOrder";

    /**
     * 根据订单ID查询库存信息，判断是否可以付款 0221校验   -0222已增加uuid验证
     * ?ord.dingdanId=%1$s&uuid=%2$s
     */
    String URL_GET_ORDER_KUNCUN_FROM_ID = "mall/androidOrder_findOrderKucunByordid";

    /**
     * 用户评价商品 0221校验   -0222已增加uuid验证
     * todo 10 27 修改
     */
    String URL_PRAISE_GOODS_FROM_USER = "mall2app/appraise/add.mvc";

    /**
     * 获取商品评价列表 根据商品ID 0221 校验   -0222已增加uuid验证
     * ?rows=%1$s&page=%2$s&comment.pingjiaShangpNum=%3$s&comment.pingjiaLevel=%4$s&uuid=%5$s
     */
    String URL_PRAISE_LIST_FROM_GOOD = "mall/comment_findComment";


    /**
     * 获取首页推荐商品集合rows=%1$s&page=%2$s&product.shangpinClassicShow=%3$s
     * 0221 校验  -0222已增加uuid验证
     */
    String URL_INDEX_GOODS_LIST = "mall/androidProduct_findHomeProduct?";

    /**
     * 获取首页轮播图 0221 校验   -0222已增加uuid验证
     */
    String URL_GET_MAIN_LUNBO = "mall/androidPeizhi_findAppMainLunBoTu";

    /**
     * 获取商城轮播和图标 0221 校验  -0222已增加uuid验证
     * /mall/androidPeizhi_findAppScLunBoTu?uuid=%1$s&xmid=%2$s"
     */
    String URL_GET_SC_LUNBO_TUBIAO = "mall/androidPeizhi_findAppScLunBoTu?";

    /**
     * 获取商品详情网页 0221 校验
     */
    String URL_GOODS_DESTAIL_WEB = "pages/mall/productDetail.jsp?productId=";

    /**
     * 新增收获地址 0221校验   -0222已增加uuid验证
     */
    String URL_ADD_ADDRESS = "mall/add_saveAdd?";

    /**
     * 修改收获地址 0221校验  -0222已增加uuid验证
     */
    String URL_UPDATE_ADDRESS = "mall/add_mergeAdd?";

    /**
     * 根据用户查询收获地址列表 0221校验  -0222已增加uuid验证
     * uuid=%1$s
     */
    String URL_GET_ADDRESS_LIST_FROM_USER = "mall/add_findAdd";

    /**
     * 根据地址Id删除地址 0221校验  -0222已增加uuid验证
     * add.addId=%1$s&uuid=%2$s
     */
    String URL_DELETE_ADDRESS_FROM_ID = "mall/add_deleteAdd?";

    /**
     * 根据地址id设置默认地址 0221校验  -0222已增加uuid验证
     */
    String URL_DEFULE_ADDRESS_FROM_ID = "mall/add_setDefaultAdd?add.addId=%1$s&uuid=%2$s";

    /**
     * 优惠券 0221校验   -0222已增加uuid验证
     */
    String URL_GET_YHQ = "mall/androidUsedaijinquan_findAllUserDaijinquan?";

    /**
     * 不可用优惠券 0221校验   -0222已增加uuid验证
     */
    String URL_GET_NOYHQ = "mall/androidUsedaijinquan_findAllUserDaijinquanGuoqiShiyong?";

    /**
     * 获取版本信息 0222 校验
     */
    String URL_APP_GETVERSION = "mall/androidApp_findNewVersion";

    /*
     * 获取所有报修项目列表 0222 校验
     */ String URL_GET_ALL_COMPLAINT = "daily/androidComm_findAllXm.action";

    /*
     * 提交私有报修数据到服务器 0221 校验  -0223已增加uuid验证
     */ String URL_GET_ALL_PRIVATE_SUBMIT = "daily/androidBaoxiu_savebaoxiu.action?";

    /**
     * 查询全部报修 0221校验   -0223已增加uuid验证
     */
    String URL_REPAIR_ALL = "daily/androidBaoxiu_findbaoxiu.action";

    /**
     * 查询其他报修 0221校验   -0223已增加uuid验证
     */
    String URL_REPAIR_OTHER = "daily/androidBaoxiu_findbaox.action";

    /**
     * 根据删除业主成员  0221 校验    -0223已增加uuid验证
     */
    String URL_delete_chengyuan = "daily/androidHousehold_deletecy";

    /**
     * 根据保存业主成员  0221 校验   -0223已增加uuid验证
     */
    String URL_add_chengyuan = "daily/androidHousehold_saveyezhu";

    /**
     * 根据业主id获取业主成员列表    -0223已增加uuid验证
     * 0221 校验
     */
    String URL_findall_chengyuan = "daily/androidHousehold_findrz";

    /**
     * 业主缴费明细查询： 0221 校验   -0223已增加uuid验证
     */
    String URL_DETAIL = "jfgl/jf_yzWyMonthList.action";

    /**
     * 业主房屋查询： 0221 校验  -0223已增加uuid验证
     */
    String URL_HOUSE = "daily/fangwu_fbyyz.action";

    /**
     * 物业费添加 0221 校验   -0223已增加uuid验证
     */
    String URL_WUYE_ADD = "jfgl/jf_addwyfei.action";


    /**
     * 获取业主开门二维码   /业主姓名/业主电话/业主角色/楼盘ID/楼栋/单元120.25.78.92   0221 校验
     */
    String URL_GET_YEZHUOPENCODE = "door/androidDoor_getYezhuCodes";
    /**
     * 获取门禁列表
     */
    String URL_GET_MENJINLIST = "xdoor/device/getXdoorApp.mvc";

    /**
     * 获取门禁访客密码
     */
    String URL_POST_MENJINMIMA = "xdoor/device/appShare.mvc";
    /**
     * 获取已经分享是门禁可以呼叫的成员
     */
    String URL_POST_MENJIN_SHAREMEMBER = "xdoor/device/findMubiao.mvc";

    /**
     * 设置门禁可以呼叫的成员
     */
    String URL_POST_MENJIN_SAVE = "xdoor/device/mubiaoSave.mvc";
    /**
     * 获取业主访客二维码  coed/getcodes/{bName}/{bPhone}/{bRole}/{name}/{phone}/{role}/{building}/{buildingHouse}/{buildingUnit}
     * 0221 校验
     */
    String URL_GET_FANGKEOPENCODE = "door/androidDoor_getFangKeCodes";


    /**
     * 获取首页通知活动标题 0221校验
     */
    String URL_GET_NEWMAINTONGZHI = "tongzhi/tongzhi_findTitle.action";


    /**
     * 判断业主是否已经做了满意度调查接口 0221 校验   -0223已增加uuid验证
     */
    String URL_GET_MANYIDUTIAOCHAEXIST = "shouquan/manyidu_finduserid";
    /**
     * 七牛token 0221校验    -0223已增加uuid验证
     */
    String URL_GET_QINIU_TOKEN = "qiniu/qiniu_getQiniuToken.action";

    /**
     * 摄像头登录
     */
    String URL_GET_CAMERA = IP_Camera + "/Users/ThirdLogin.ashx?";

    /**
     * 摄像头添加    -0223已增加uuid验证
     */
    String URL_GET_CAMERA_ADD = "security/save";

    String url_camera_add_fenxiang = "security/addFenxiang";     //添加居家安防通知对象   uuid, yezhuIds, sb_ipc_id

    String url_get_jiashu = "security/findYezhuJiashu";         //拿到家属的所有对象       uuid,loudong,danyuan,fanghao

    String url_get_fenxiang_duixiang = "security/getFenxiang";              //拿到分享通知对象 uuid,sb_ipc_id


    /*
       * 获取全部摄像头列表  -0223已增加uuid验证
       */ String URL_GET_CAMERA_All = "security/findAllShebeiByYzid";


    /*
     * 摄像头修改密码         ?sb.sb_ipc_id=%1$s&sb.sb_ipc_pwd=%2$s&uuid=%3$s
    */ String URL_GET_CAMERA_UPDATE = "security/updateInfo";


    /*
     * 删除摄像头  -0223已增加uuid验证
     */ String URL_GET_CAMERA_DEL = "security/delete";

    /**
     * 获取业主 电子券
     */
    String USR_GET_YEZHU_DIANZHIQUAN = "dianzq/dianzq_androidVoucher.action";

    /**
     * 获取订单中业务最多可用的电子券
     */
    String USER_GET_YEZHU_DIANZIQUAN = "dianrule/dianrule_getVoucherUseNumByOrderPrice";

    /**
     * 获取业主 电子券记录
     */
    String USR_GET_YEZHU_DIANZHIQUAN_JILU = "dianzq/dianzq_androidIOSStreamList.action";

    /**
     * 获取业主 各个订单状态数量
     */
    String USR_GET_YEZHU_DINGDAN_NUM = "mall/androidOrder_findOrderTotalByZhuangTai";


    /**********************************    欣周边接口 *******************************/

    /**
     * 欣周边 获取投诉详情 ?complainId=%1$s&uuId=%2$s
     */
    String URL_GET_ALL_COMPLAIN_DETAIL = Periphery + "/app/complaininfo";

    /**
     * 欣周边 获取订单列表 orderStatus=%1$s&page=%2$s&rows=%3$s&uuId=%4$s
     */
    String URL_GET_ALL_ORDER_LIST = Periphery + "/app/orderlist";

    /**
     * 欣周边 获取订单动态跟踪orderNumber=%1$s&uuId=%2$s
     */
    String URL_GET_ALL_ORDER_DYNAMIC = Periphery + "/app/dynamictrace";

    /**
     * 欣周边 获取订单发表评价
     */
    String URL_GET_ALL_ORDER_COMMENT = Periphery + "/app/evaluate?";

    /**
     * 欣周边 订单投诉列表 page=%1$s&rows=%2$s&uuId=%3$s
     */
    String URL_GET_ALL_ORDER_COMPLAIN = Periphery + "/app/complainlist";
    /**
     * 欣周边 商家首页
     */
    String URL_GET_BUSINESS_FIRSTINFO = Periphery + "/app/businessfirstinfo";

    /**
     * 欣周边 商家评价列表
     */
    String URL_GET_RIM_SHOP_COMMENTLIST = Periphery + "/app/pingjialist";

    /**
     * 欣周边 订单获取打折信息
     */
    String URL_GET_RIM_ACTIVITY_DISCOUNT = Periphery + "/app/showActivityDiscount";
    /**
     * 欣周边 取消订单
     */
    String URL_RIM_ORDER_CANCEL = Periphery + "/app/cancelorder";

    /**
     * 欣周边 确认送达orderNumber=%1$s&uuId=%2$s
     */
    String URL_GET_ALL_CONFIRM_ORDER = Periphery + "/app/confirmorder";

    /**
     * 欣周边 订单投诉
     */
    String URL_GET_ADD_ORDER_COMPLAIN = Periphery + "/app/complain";

    /**
     * 欣周边 删除订单
     */
    String URL_DELETE_RIMORDER = Periphery + "/app/deleteOrder";

    /**
     * 欣周边 查询商品库存或下架
     */
    String URL_ORDER_BUYCHECK = Periphery + "/app/orderPayByCheck";
    /**
     * 欣周边 添加商品到购物车(POST)
     */
    String URL_ADD_SHOPCAR = Periphery + "/app/addShopCart";

    /**
     * 欣周边 查询购物车列表(POST)
     */
    String URL_SHOP_CAR_LIST = Periphery + "/app/shopCartList";

    /**
     * 欣周边 批量删除购物车商品接口(POST)
     */
    String URL_DELETE_SHOP_CAR = Periphery + "/app/deleteProductByShopCart";

    /**
     * 欣周边 批量删除购物车商品接口(POST)
     */
    String URL_UPDATA_SHOP_CAR = Periphery + "/app/updateShopCart";
    /**
     * 欣周边 确认修改订单接口(POST)
     */
    String URL_UPDATA_ORDER = Periphery + "/app/updateOrder";
    /**
     * 欣周边 将订单详情信息添加至购物车列表中(POST)
     */
    String URL_DETAILS_TO_CAR = Periphery + "/app/orderDetailsToShopCart";
    /**
     * 欣周边 单个订单的评价列表
     */
    String URL_RIM_ORDER_COMMENT = Periphery + "/app/evaluateByUser";

    /**2017-02-21 整理废弃功能接口start*************************************************************************/

    /**
     * 获取商品一级分类URL
     */
    String URL_GET_ALL_ATTENDANCE = "mall/androidClassify_findClassifyOne";

    /**
     * 根据一级分类获取二级分类
     */
    String URL_GET_ALL_SECONDCLASS = "mall/androidClassify_findByclassifyOne?classify.classifyId=%1$s";

    /**
     * 获取商品列表信息通过商品分类Id  未用上
     * rows=10&page=1&classify.classifyId=&sort=shangpinClassicTwo&order=asc&appxiaoqu=
     */
    String URL_GET_ALL_SHOPLIST_BY_ID = "mall/androidProduct_findByProduct?";
    /**
     * 修改昵称
     */
    String URL_GET_ALL_UPDATE_NAME = "mall/androidUser_mergeUserName?";

    /**
     * 修改身份证
     */
    String URL_GET_ALL_UPDATE_CARD = "mall/androidUser_mergeUser?user.userId=%1$s&user.userIdCard=%2$s";
    /**
     * 充值接口
     */
    String URL_CHONGZHI = "mall/androidUser_yonghuRecharge.action";

    /**
     * 查询所有余额 （1202 存在）
     */
    String URL_YUE = "mall/androidUser_findUserInfoByid?";
    /*
     * 提交私有楼盘数据到服务器
     */ String URL_GET_ALL_PRIVARE_LOUPAN = "daily/androidComm_findLoudong.action?id=%1$s";

    /*
     * 提交私有楼栋数据到服务器
     */ String URL_GET_ALL_PRIVARE_DANYUAN = "daily/androidComm_findDanyuan.action?id=%1$s";

    /*
     * 提交物业管理投诉数据到服务器
     */ String URL_GET_ALL_COMPAINT_WUYE_SUBMIT = "daily/androidTousu_tousu.action?";

    /**
     * 获取报修项目
     */
    String URL_REPAIR_XIANGMU = "daily/androidWxpoject_findxm.action";
    /**
     * 物业费缴费记录
     */
    String URL_PAYMENT_RECORDS_WUYE = "jfgl/jf_ywyfeilist.action?fwId=%1$s";
    /**
     * 水费缴费记录
     */
    String URL_PAYMENT_RECORDS_WATER = "sdushu/androidSdushu_findAllYezhuNameShui.action?";

    /**
     * 电费缴费记录
     */
    String URL_PAYMENT_RECORDS_ELECTRICITY = "sdushu/androidSdushu_findAllYezhuNameDian.action?";


    /**
     * 物业付款
     */
    String URL_WUYE_PAY = "sdushu/androidSdushu_jiaoFeiyong?";


    /**
     * 获取用户所有缴费记录
     */
    String URL_ALL_PAYMENT_RECORDS = "wuye/androidWuye_findAllYezhuYJCost.action";

    /**
     * 获取用户所有欠费记录
     */
    String URL_ALL_QIANFEI_RECORDS = "wuye/androidWuye_findAllYezhuDJAndQFCost.action";

    /**
     * 充值记录
     */
    String URL_GET_RECHARGE = "mall/androidUser_findYonghuBalancereCord.action?";
    /**
     * 消费记录
     */
    String URL_GET_EXPENDITURE = "mall/androidUser_findYonghuBalancereCord.action?";

    /**
     * 根据订单ID 进行余额支付
     */
    // http://192.168.0.114:8080/wygl/mall/order_payYue?ord.dingdanId=51
    String URL_YU_E_PAY_ORDER_STATE_FROM_ID = "mall/androidOrder_payYue?ord.dingdanId=%1$s";

    /**
     * 收藏商品 0221 校验
     */
    String URL_COLLECT_GOODS_FROM_ID = "mall/collection_addCollection?";

    /**
     * 删除收藏商品 0221 校验
     */
    String URL_DELETE_COLLECT_GOODS_FROM_ID = "mall/collection_deleteCollection?collection" + "" + "" + "" + "" + "" +
            ".collectionId=%1$s&collection.collectionShangpId=%2$s&collection.collectionUserId=%3$s";

    /***2017-02-21 整理废弃功能接口end***********************************************************************/

    //欣周边商家

    /**
     * 商家列表
     */
    String URL_BUSINESS_LIST = Periphery   //?page=%1$s&rows=%2$s&type=%3$s&uuId=%4$s
            + "/app/businesslist";

    /**
     * 商家信息
     */
    String URL_BUSINESS_INFO = Periphery  //?businessNumber=%1$s&uuId=%2$s
            + "/app/businessinfo";

    /**
     * 查询商品分类信息
     */
    String URL_CLASSIFYINFO = Periphery           //?businessNumber=%1$s&classifyId=%2$s&uuId=%3$s
            + "/app/classifyinfo";
    /**
     * 查询商品信息
     */
    String URL_PRODUCTINFO = Periphery   //?businessNumber=%1$s&classifyId=%2$s&page=%3$s&rows=%4$s&uuId=%5$s
            + "/app/productinfo";
    /**
     * 订单状态查询
     */
    String URL_ORDERINFO = Periphery    //?orderNumber=%1$s&uuId=%2$s
            + "/app/orderinfo";

    /**
     * 预约订单
     */
    String URL_APPOINTMENT = Periphery + "/app/appointment";
    /**
     * 修改订单
     */
    String URL_UPDATEORDER = Periphery + "/app/updateorder?";

    /**
     * 订单状态查询
     */
    String URL_CONFIRMORDER = Periphery + "/app/confirmorder?orderNumber=%1$s&uuId=%2$s";

    //公共安防摄像头Token获取
    String URL_COMMON_Token = "https://open.ys7.com/api/lapp/token/get";

    //公共安防摄像头显示隐藏  uuid
    String URL_COMMON = "vcr/vcr_findAllVcrXsq";

    //**************新增的h5的接口******************//
    //获取通知        //luopan(post)
    String URL_FINDTONGZHI = "tongzhi/tongzhi_findhuodongxx.action";
    //获取活动        //luopan(post)
    String URL_HUODONG = "tongzhi/tongzhi_findTongzhilx.action";
    //投诉建议提交
    /**
     * (post)
     * 参数：
     * 投诉：tousufanwei  tousujianyitype（值为1）  yezhuid  tousuXiangmuId  tsjyNeirong
     * 建议：tsjyNeirong  tousujianyitype（值为2）  yezhuid  tousuXiangmuId
     */
    String URL_SAVETOUSU = "daily/tousu_appsavetousu.action";
    //投诉建议列表
    /**
     * method:GET
     * 参数：yezhuid
     */
    String URL_FINDTOUSULIST = "daily/tousu_findtousuList.action";

    //满意度调查结果提交(POST)
    /**
     * 参数：
     * abc:{userId,xiangmuId,tableId,question（问题数组，每个问题包含id和value两个参数，id代表问题id，value代表答案id）}
     */
    String URL_MANYIDU = "shouquan/manyidu_ManyiSave";


    //商城建议和投诉 (method:POST)
    //参数：tousujianyitype（值为1建议）  malluserid  tousuXiangmuId  tsjyNeirong
    //参数：fwtstype  tousujianyitype（值为0投诉 ） malluserid  orderid  tousuXiangmuId  tsjyNeirong
    String URL_MALL_TOUSUJIANYI_SAVA = "mall/tousujianyi_sava.action";

    /**
     * 房屋出租列表
     * uuid
     * method   post
     */
    String URL_CHUZU_CHUZUXIANSHI = "chuzu/chuzu_chuzuxianshi.action";

    /**
     * 修改房子出租状态
     */
    String URL_CHUZU_UPDATEZHUANG = "chuzu/chuzu_updatezhuang.action";
    /**
     * 获取车辆列表
     * method   get
     * yezhuId  xmId
     * 120.25.78.92
     */
    String URL_CAR_LIST = daozai+"/cxwy_daozha/config/fmcars";
    /**
     * 车辆解锁
     * method   post
     * lpid  serialNo  parkNo  mediaNo isLock
     */
    String URL_CAR_JIESUO = daozai+"/cxwy_daozha/config/jsche";
    /**
     * 车辆充值
     * method   post
     * lpid  parkNo  mediaNo  month  cardTimr  money  isnew（值为1） rechargeType  rechargeSerialNum  rechargeResult
     */
    String URL_CAR_RECHARGE = daozai+"/cxwy_daozha/config/recharge";

    /**
     * 车辆充值
     */
    String URL_PRE_CHELAINGJIAOFEI = daozai+"/cxwy_daozha/config/reccw";
    /**
     * 车辆缴费记录
     * method   post
     * rows  page  mediaNo  isnew（值为1）
     */
    String URL_CAR_RELIST = daozai+"/cxwy_daozha/config/relist";

    /**
     * 授权放行列表（业主）
     * method:GET
     * 参数：userid
     */
    String URL_GET_SHOUQUAN_LIST_YEZHU = "shouquan/cxwy_selectall.action";
    /**
     * 授权放行列表（租客）
     * method:post
     * 参数：yezhuId  xiangmuId  fwId  obj（物品信息数组）
     */
//    String URL_SHOUQUAN_CXWY_TIJIAO= "shouquan/cxwy_tijiao.action";
    String URL_SHOUQUAN_CXWY_TIJIAO = "shouquan/cxwy_tijiaoxg.action";
    /**
     * 租户授权申请提交
     * method:GET
     * 参数：userid
     */
    String URL_GET_SHOUQUAN_LIST_ZUKE = "shouquan/cxwy_tenant.action";

    /**
     * 授权申请详情
     * method:POST
     * 参数：pcId
     */
    String URL_GET_SHOUQUAN_DETAIL = "shouquan/cxwy_zuhuleibiao";

    /**
     * 业主授权放行
     * method:POST
     * 参数：pcId
     */
    String URL_SHOUQUAN_COMFIRM = "shouquan/cxwy_queren";

    /**
     * 商城投诉建议
     */
    String URL_TOUSUJIANYI_SAVA = "mall/tousujianyi_sava.action";

    String URL_DINGDANTOUSU_WEICHULI = "mall/tousujianyi_findyonghustat.action";
    String URL_DINGDANTOUSU_CHULIZHONG = "mall/tousujianyi_findyonghuclzstat.action";
    String URL_DINGDANTOUSU_YICHULI = "mall/tousujianyi_findyonghuyclstat.action";

    //    String URL_SURE_CHELAINGJIAOFEI = "http://dz.hnchxwl.com/cxwy_daozha/config/reccw";


    //雄迈摄像头
    String URL_XIONGMAI_SXT = "http://192.168.8.120:8080/sxt/getAllSXT";

    //**************新增的h5的接口******************//

    //**********************派安***********************//
    String PAIAN_SEARCH_ALLHOST = "paian/xinshequ_searchAllHost";
    String PAIAN_BUCHEFANG = "paian/xinshequ_buchefang";

    String PAIAN_TIMINGDISARMTIMESETTING = "paian/xinshequ_timingdisarmtimesetting";
    String PAIAN_FINDSHEBEILIST = "paian/xinshequ_findShebeiList";

    String PAIAN_SELECTDINGSHIBUCHEFANG = "paian/xinshequ_selectdingshibuchefang";

    //获取续费内容金额
    String PAIAN_GETMONEY = "paian/cxwyPaianJiaofei_getMoney";
    //获取续费支付订单号
    String PAIAN_JIAOFEIORDER = "paian/cxwyPaianJiaofei_saveJiaoFeiOrder";
    //撤销布撤防
    String PAIAN_ZONEINTERVA = "paian/xinshequ_zoneinterval";

    //获取报警列表
    String PAIAN_ALARMHISTORY = "paian/xinshequ_alarmhistory";
    //设置鸣笛
    String PAIAN_LEIXIN = "paian/xinshequ_leixin";

    String PAIAN_FIND_SHARE = "paian/xinshequ_findFenxiang";

    String PAIAN_SAVE_SHARE = "paian/xinshequ_saveNumber";

    String PAIAN_UPDATE_FANGQU = "paian/xinshequ_updateFangqu";

    String PAIAN_FIND_YEZHU_JIASHU = "paian/xinshequ_findYezhuJiashu";
    //**********************派安***********************//


    //**********************商城改版***********************//
    String url_shangcheng_fenlei = "mall2app/index/classifybyone.mvc";//查询所有一级分类
    String url_shangcheng_shouhou = "mall2app/shouhou/add.mvc";  // 提交售后申请
    String url_my_evaluate = "mall2app/appraise/list.mvc";//我的评价列表
    String url_goods_kinds = "mall2app/goods/kinds.mvc";   //查询新品、热销商品、店长推荐商品
    String url_goods_list = "mall2app/goods/list.mvc"; //商品分类
    String url_mall_order = "mall2app/order/list.mvc";  //订单列表
    String url_goods_info = "mall2app/goods/info.mvc"; //商品详情
    String url_goods_appraise = "mall2app/appraise/appraiseList.mvc";  //查询商品评价及回复
    String url_shop_cart_add = "mall2app/shopcar/add.mvc";                   //加入购物车
    String url_get_shop_cart_list = "mall2app/shopcar/list.mvc";            //根据用户id查询购物车列表
    String url_get_shop_cart_count = "mall2app/shopcar/count.mvc";          //根据用户id查询购物车商品种类总数
    String url_shop_cart_edit = "mall2app/shopcar/update.mvc";               //编辑购物车数量
    String url_shop_cart_delete = "mall2app/shopcar/delete.mvc";               //删除购物车商品
    String url_goods_dianzijuan = "mall2app/electicket/param.mvc";               //获取电子卷
    String url_goods_order_add = "mall2app/order/add.mvc";               //结算购物车
    String url_goods_order_cancel = "mall2app/order/cancel.mvc";               //取消订单
    String url_goods_order_hidden = "mall2app/order/hiddenorder.mvc";               //删除订单
    String url_goods_order_suggest = "mall2app/suggest/add_order.mvc";               //订单投诉
    String url_goods_order_count = "mall2app/order/counts.mvc";               //我的界面订单数量
    String url_goods_order_suggest_list = "mall2app/suggest/detail.mvc";               //查看订单投诉
    String url_goods_banner = "mall2app/index/banner.mvc";               //根据商品名模糊查询商品信息
    String url_isNight = "mall2app/index/isnight.mvc";//是否夜间销售时间
    String url_goods_dianzijuan_list = "mall2app/electicket/list.mvc";//我的电子卷
    String url_mall_suggest_list = "mall2app/suggest/list.mvc";//查询商城建议列表
    String url_mall_commit_suggest = "mall2app/suggest/add.mvc";//提交商城建议列表
    String url_goods_get_order = "mall2app/order/kucun.mvc";//(待支付)立即支付判断库存 接口判断库存，返回订单号，进入支付页面（不需要判断）
    String url_goods_is_xiajia = "mall2app/order/isxiajia.mvc";//(待支付)立即支付判断是否下架 接口判断库存，返回订单号，进入支付页面
    String url_get_dianzijuan_guizei = "mall2app/electicket/use.mvc";//获取电子券规则
    String url_get_pos_order = "pos/localorder/getorder.mvc";//获取二维码支付扫码支付订单详情
    String url_confirm_order = "pos/localorder/xsqtopay.mvc";//二维码支付提交订单接口

    //**********************商城改版***********************//

    // TODO: 2018/5/11 东升物业新增闪屏广告
    String URL_DONGSHENG_AD = "mall2app/qidong/didong_info.mvc";

    // TODO: 2018/5/11 维修流程
    String URL_WEIXIU_LIUCHENG = "baoxiu_/find.mvc";

    //**********************业委会***********************//
    String URL_YWH_CURRENTFLOW = "http://192.168.8.106:8083/supervise/app/conference/currentflow";

    String URL_YWH_GETFKYJLIST = "http://192.168.8.128:8888/supervise/app/conference/ylist";

    String URL_YWH_COMMITFKYJ = "http://192.168.8.128:8888/supervise/app/conference/yadd";

    String URL_YWH_COMMITFKYJ2 = "http://192.168.8.128:8888/app/obtain/feedback/submit";

    String URL_YWH_GETMEMBERSHOWLIST = "http://192.168.8.128:8888/supervise/app/conference/plist";

    String URL_YWH_GETSURVEYLIST = "http://192.168.8.223:8080/supervise/app/research/slist";

    //**********************业委会***********************//

    String ywh_pic = "http://http://p9zwbgynz.bkt.clouddn.com";//lt七牛访问地址
    String url_ywh_lcxx = "http://192.168.8.106:8083/supervise/app/conference/flowlist";
    String url_ywh_tjcbz = "http://192.168.8.223:8080/supervise/app/prepare/recommend";//推荐筹备组成员理由
    String url_ywh_tjcbz_list = "http://192.168.8.223:8080/supervise/app/prepare/cblist";//推荐筹备组成员列表
    String url_ywh_gsmd = "http://192.168.8.223:8080/supervise/app/prepare/recommend/notice";//筹备组成员公示名单列表//暂时不需要
    String url_ywh_tjyj = "http://192.168.8.223:8080/supervise/app/prepare/padd";//提交反馈信息
    String url_ywh_lhlb = "http://192.168.8.223:8080/supervise/app/prepare/pdetail";//查看提交的反馈意见
    String url_ywh_house = "http://192.168.8.106:8083//supervise/app/preparework/ohList";//业主房屋列表
    String url_ywh_smrz = "http://192.168.8.106:8083/supervise/app/preparework/pradd";//个人实名认证添加
    String url_ywh_smrz_detail = "http://192.168.8.106:8083/supervise/app/preparework/preal";//个人实名认证详情
    String url_ywh_smrz_change = "http://192.168.8.106:8083/supervise/app/preparework/prupdate";//个人实名认证修改
}
