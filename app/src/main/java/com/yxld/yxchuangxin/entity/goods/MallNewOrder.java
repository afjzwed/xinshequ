package com.yxld.yxchuangxin.entity.goods;

import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * @author xlei
 * @Date 2017/10/24.
 */

public class MallNewOrder extends BaseEntity implements Parcelable {

    private List<MallNewOrder> orders;
    private List<MallNewOrderDetails> orderDetails;
    private int state;
    private int total;

    private int id; /* 订单id */
    private int xiangmuId; /* 项目id */
    private int gongsiId; /* 公司id */
    private String xiangmuMing; /* 项目名 */
    private String bianhao; /* 订单编号 */
    private int zhuangtai; /* 订单状态：1待支付、2待发货、3待收货、4待评价、5已完成、6退货中、7退款中、8已退款、9已取消、10退货完成 */
    private int shifouYejian; /* 是否夜间订单 -1夜间，1日间 */
    private String shouhuorenMing; /* 收货人姓名 */
    private String shouhuoDizhi; /* 收货地址 */
    private String shouhuoDianhua; /* 收货电话 */
    private double zongjine; /* 订单总金额 */
    private int dianziquan; /* 电子券抵扣 */
    private double shijiJine; /* 实付金额 */
    private String payjiaoyihao; /* 第三方支付交易号 */
    private int fukuanFangshi; /* 付款方式:1支付宝，2微信，3银联 */
    private int peisongFangshi; /* 配送方式：1商城配送，2自提 */
    private int anpaiPeisong; /* 是否安排配送人 -1否，1是 */
    private int jiedanMoshi; /* 接单模式：1派单，2抢单 */
    private int paidanren; /* 订单派单人(后台分配配送员的管理员) */
    private int peisongrenId; /* 订单配送人id */
    private String peisongrenMing; /* 订单配送人名 */
    private double peisongfei; /* 订单配送费 */
    private int peisongfeiLaiyuan; /* 配送费来源：1自提，2商家，3买家 */
    private int tuikuanfangshi; /* 退款方式 -1部分，1全额 */
    private double tuikuanJine; /* 退款金额 */
    private int tuikuanYuanyin; /* 退款原因：1质量问题，2服务，3无理由 */
    private String xiadanShijian; /* 下单时间 */
    private String fukuanShijian; /* 付款时间 */
    private String paisongShijian; /* 派单时间 */
    private String quhuoShijian; /* 取货时间 */
    private String shouhuoShijian; /* 收货时间 */
    private String pingjiaShijian; /* 评价时间 */
    private String tuikuanShenqingShijian; /* 申请退款时间 */
    private String tuihuanShijian; /* 退还时间 */
    private String tuikuanYunxuShijian; /* 允许退款时间 */
    private String quxiaoShijian; /* 取消时间 */
    private String quxiaoYuanyin; /* 取消原因 */
    private String wanchengShijian; /* 完成订单时间 */
    private String tuikuanpicihao; /* 订单退款批次号 */
    private String tuikuanbiaozhi; /* 是否需要退款: 无需退款 需要退款 已退款至支付宝/微信/银联 */
    private int yezhuId; /* 业主id */
    private String yezhuZhanghao; /* 用户账号 */
    private int yezhuShanchu; /* 业主在APP中是否删除标记,-1是未删除，1为删除 */
    private int dajianorder; /* 大件配送，0为正常配送订单，1为大件配送订单 */
    private String beizhu; /*业主备注*/
    private int shangpinNum; /*订单商品总件数*/
    private String peisongrenTel; /*配送人电话*/
    private int isShouhou;/*有没有申请过售后：1是已申请，2是未申请*/
    // TODO: 2018/3/22  二维码支付添加字段
    private int orderType;
    private double duijiangdikou;
    private double orderMoney;


    protected MallNewOrder(Parcel in) {
        orders = in.createTypedArrayList(MallNewOrder.CREATOR);
        orderDetails = in.createTypedArrayList(MallNewOrderDetails.CREATOR);
        state = in.readInt();
        total = in.readInt();
        id = in.readInt();
        xiangmuId = in.readInt();
        gongsiId = in.readInt();
        xiangmuMing = in.readString();
        bianhao = in.readString();
        zhuangtai = in.readInt();
        shifouYejian = in.readInt();
        shouhuorenMing = in.readString();
        shouhuoDizhi = in.readString();
        shouhuoDianhua = in.readString();
        zongjine = in.readDouble();
        dianziquan = in.readInt();
        shijiJine = in.readDouble();
        payjiaoyihao = in.readString();
        fukuanFangshi = in.readInt();
        peisongFangshi = in.readInt();
        anpaiPeisong = in.readInt();
        jiedanMoshi = in.readInt();
        paidanren = in.readInt();
        peisongrenId = in.readInt();
        peisongrenMing = in.readString();
        peisongfei = in.readDouble();
        peisongfeiLaiyuan = in.readInt();
        tuikuanfangshi = in.readInt();
        tuikuanJine = in.readDouble();
        tuikuanYuanyin = in.readInt();
        xiadanShijian = in.readString();
        fukuanShijian = in.readString();
        paisongShijian = in.readString();
        quhuoShijian = in.readString();
        shouhuoShijian = in.readString();
        pingjiaShijian = in.readString();
        tuikuanShenqingShijian = in.readString();
        tuihuanShijian = in.readString();
        tuikuanYunxuShijian = in.readString();
        quxiaoShijian = in.readString();
        quxiaoYuanyin = in.readString();
        wanchengShijian = in.readString();
        tuikuanpicihao = in.readString();
        tuikuanbiaozhi = in.readString();
        yezhuId = in.readInt();
        yezhuZhanghao = in.readString();
        yezhuShanchu = in.readInt();
        dajianorder = in.readInt();
        beizhu = in.readString();
        shangpinNum = in.readInt();
        peisongrenTel = in.readString();
        isShouhou = in.readInt();
        orderType = in.readInt();
        duijiangdikou = in.readDouble();
        orderMoney = in.readDouble();
    }

    public static final Creator<MallNewOrder> CREATOR = new Creator<MallNewOrder>() {
        @Override
        public MallNewOrder createFromParcel(Parcel in) {
            return new MallNewOrder(in);
        }

        @Override
        public MallNewOrder[] newArray(int size) {
            return new MallNewOrder[size];
        }
    };

    public List<MallNewOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<MallNewOrder> orders) {
        this.orders = orders;
    }

    public List<MallNewOrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<MallNewOrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getXiangmuId() {
        return xiangmuId;
    }

    public void setXiangmuId(int xiangmuId) {
        this.xiangmuId = xiangmuId;
    }

    public int getGongsiId() {
        return gongsiId;
    }

    public void setGongsiId(int gongsiId) {
        this.gongsiId = gongsiId;
    }

    public String getXiangmuMing() {
        return xiangmuMing;
    }

    public void setXiangmuMing(String xiangmuMing) {
        this.xiangmuMing = xiangmuMing;
    }

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
    }

    public int getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(int zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public int getShifouYejian() {
        return shifouYejian;
    }

    public void setShifouYejian(int shifouYejian) {
        this.shifouYejian = shifouYejian;
    }

    public String getShouhuorenMing() {
        return shouhuorenMing;
    }

    public void setShouhuorenMing(String shouhuorenMing) {
        this.shouhuorenMing = shouhuorenMing;
    }

    public String getShouhuoDizhi() {
        return shouhuoDizhi;
    }

    public void setShouhuoDizhi(String shouhuoDizhi) {
        this.shouhuoDizhi = shouhuoDizhi;
    }

    public String getShouhuoDianhua() {
        return shouhuoDianhua;
    }

    public void setShouhuoDianhua(String shouhuoDianhua) {
        this.shouhuoDianhua = shouhuoDianhua;
    }

    public double getZongjine() {
        return zongjine;
    }

    public void setZongjine(double zongjine) {
        this.zongjine = zongjine;
    }

    public int getDianziquan() {
        return dianziquan;
    }

    public void setDianziquan(int dianziquan) {
        this.dianziquan = dianziquan;
    }

    public double getShijiJine() {
        return shijiJine;
    }

    public void setShijiJine(double shijiJine) {
        this.shijiJine = shijiJine;
    }

    public String getPayjiaoyihao() {
        return payjiaoyihao;
    }

    public void setPayjiaoyihao(String payjiaoyihao) {
        this.payjiaoyihao = payjiaoyihao;
    }

    public int getFukuanFangshi() {
        return fukuanFangshi;
    }

    public void setFukuanFangshi(int fukuanFangshi) {
        this.fukuanFangshi = fukuanFangshi;
    }

    public int getPeisongFangshi() {
        return peisongFangshi;
    }

    public void setPeisongFangshi(int peisongFangshi) {
        this.peisongFangshi = peisongFangshi;
    }

    public int getAnpaiPeisong() {
        return anpaiPeisong;
    }

    public void setAnpaiPeisong(int anpaiPeisong) {
        this.anpaiPeisong = anpaiPeisong;
    }

    public int getJiedanMoshi() {
        return jiedanMoshi;
    }

    public void setJiedanMoshi(int jiedanMoshi) {
        this.jiedanMoshi = jiedanMoshi;
    }

    public int getPaidanren() {
        return paidanren;
    }

    public void setPaidanren(int paidanren) {
        this.paidanren = paidanren;
    }

    public int getPeisongrenId() {
        return peisongrenId;
    }

    public void setPeisongrenId(int peisongrenId) {
        this.peisongrenId = peisongrenId;
    }

    public String getPeisongrenMing() {
        return peisongrenMing;
    }

    public void setPeisongrenMing(String peisongrenMing) {
        this.peisongrenMing = peisongrenMing;
    }

    public double getPeisongfei() {
        return peisongfei;
    }

    public void setPeisongfei(double peisongfei) {
        this.peisongfei = peisongfei;
    }

    public int getPeisongfeiLaiyuan() {
        return peisongfeiLaiyuan;
    }

    public void setPeisongfeiLaiyuan(int peisongfeiLaiyuan) {
        this.peisongfeiLaiyuan = peisongfeiLaiyuan;
    }

    public int getTuikuanfangshi() {
        return tuikuanfangshi;
    }

    public void setTuikuanfangshi(int tuikuanfangshi) {
        this.tuikuanfangshi = tuikuanfangshi;
    }

    public double getTuikuanJine() {
        return tuikuanJine;
    }

    public void setTuikuanJine(double tuikuanJine) {
        this.tuikuanJine = tuikuanJine;
    }

    public int getTuikuanYuanyin() {
        return tuikuanYuanyin;
    }

    public void setTuikuanYuanyin(int tuikuanYuanyin) {
        this.tuikuanYuanyin = tuikuanYuanyin;
    }

    public String getXiadanShijian() {
        return xiadanShijian;
    }

    public void setXiadanShijian(String xiadanShijian) {
        this.xiadanShijian = xiadanShijian;
    }

    public String getFukuanShijian() {
        return fukuanShijian;
    }

    public void setFukuanShijian(String fukuanShijian) {
        this.fukuanShijian = fukuanShijian;
    }

    public String getPaisongShijian() {
        return paisongShijian;
    }

    public void setPaisongShijian(String paisongShijian) {
        this.paisongShijian = paisongShijian;
    }

    public String getQuhuoShijian() {
        return quhuoShijian;
    }

    public void setQuhuoShijian(String quhuoShijian) {
        this.quhuoShijian = quhuoShijian;
    }

    public String getShouhuoShijian() {
        return shouhuoShijian;
    }

    public void setShouhuoShijian(String shouhuoShijian) {
        this.shouhuoShijian = shouhuoShijian;
    }

    public String getPingjiaShijian() {
        return pingjiaShijian;
    }

    public void setPingjiaShijian(String pingjiaShijian) {
        this.pingjiaShijian = pingjiaShijian;
    }

    public String getTuikuanShenqingShijian() {
        return tuikuanShenqingShijian;
    }

    public void setTuikuanShenqingShijian(String tuikuanShenqingShijian) {
        this.tuikuanShenqingShijian = tuikuanShenqingShijian;
    }

    public String getTuihuanShijian() {
        return tuihuanShijian;
    }

    public void setTuihuanShijian(String tuihuanShijian) {
        this.tuihuanShijian = tuihuanShijian;
    }

    public String getTuikuanYunxuShijian() {
        return tuikuanYunxuShijian;
    }

    public void setTuikuanYunxuShijian(String tuikuanYunxuShijian) {
        this.tuikuanYunxuShijian = tuikuanYunxuShijian;
    }

    public String getQuxiaoShijian() {
        return quxiaoShijian;
    }

    public void setQuxiaoShijian(String quxiaoShijian) {
        this.quxiaoShijian = quxiaoShijian;
    }

    public String getQuxiaoYuanyin() {
        return quxiaoYuanyin;
    }

    public void setQuxiaoYuanyin(String quxiaoYuanyin) {
        this.quxiaoYuanyin = quxiaoYuanyin;
    }

    public String getWanchengShijian() {
        return wanchengShijian;
    }

    public void setWanchengShijian(String wanchengShijian) {
        this.wanchengShijian = wanchengShijian;
    }

    public String getTuikuanpicihao() {
        return tuikuanpicihao;
    }

    public void setTuikuanpicihao(String tuikuanpicihao) {
        this.tuikuanpicihao = tuikuanpicihao;
    }

    public String getTuikuanbiaozhi() {
        return tuikuanbiaozhi;
    }

    public void setTuikuanbiaozhi(String tuikuanbiaozhi) {
        this.tuikuanbiaozhi = tuikuanbiaozhi;
    }

    public int getYezhuId() {
        return yezhuId;
    }

    public void setYezhuId(int yezhuId) {
        this.yezhuId = yezhuId;
    }

    public String getYezhuZhanghao() {
        return yezhuZhanghao;
    }

    public void setYezhuZhanghao(String yezhuZhanghao) {
        this.yezhuZhanghao = yezhuZhanghao;
    }

    public int getYezhuShanchu() {
        return yezhuShanchu;
    }

    public void setYezhuShanchu(int yezhuShanchu) {
        this.yezhuShanchu = yezhuShanchu;
    }

    public int getDajianorder() {
        return dajianorder;
    }

    public void setDajianorder(int dajianorder) {
        this.dajianorder = dajianorder;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public int getShangpinNum() {
        return shangpinNum;
    }

    public void setShangpinNum(int shangpinNum) {
        this.shangpinNum = shangpinNum;
    }

    public int getIsShouhou() {
        return isShouhou;
    }

    public void setIsShouhou(int isShouhou) {
        this.isShouhou = isShouhou;
    }

    public String getPeisongrenTel() {
        return peisongrenTel;
    }

    public void setPeisongrenTel(String peisongrenTel) {
        this.peisongrenTel = peisongrenTel;
    }

    public double getDuijiangdikou() {
        return duijiangdikou;
    }

    public void setDuijiangdikou(double duijiangdikou) {
        this.duijiangdikou = duijiangdikou;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
    }

    //订单状态 1待支付、2待发货、3待收货、4待评价、5已完成、6退货中、7退款中、8已退款、9已取消、
    public String getZhuangTaiString(int zhuangtai) {
        String s = "";
        switch (zhuangtai) {
            case 1:
                s = "待支付";
                break;
            case 2:
                s = "待发货";
                break;
            case 3:
                s = "待收货";
                break;
            case 4:
                s = "待评价";
                break;
            case 5:
                s = "已完成";
                break;
            case 6:
                s = "退货中";
                break;
            case 7:
                s = "退款中";
                break;
            case 8:
                s = "已退款";
                break;
            case 9:
                s = "已取消";
                break;
            default:
        }
        return s;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(orders);
        dest.writeTypedList(orderDetails);
        dest.writeInt(state);
        dest.writeInt(total);
        dest.writeInt(id);
        dest.writeInt(xiangmuId);
        dest.writeInt(gongsiId);
        dest.writeString(xiangmuMing);
        dest.writeString(bianhao);
        dest.writeInt(zhuangtai);
        dest.writeInt(shifouYejian);
        dest.writeString(shouhuorenMing);
        dest.writeString(shouhuoDizhi);
        dest.writeString(shouhuoDianhua);
        dest.writeDouble(zongjine);
        dest.writeInt(dianziquan);
        dest.writeDouble(shijiJine);
        dest.writeString(payjiaoyihao);
        dest.writeInt(fukuanFangshi);
        dest.writeInt(peisongFangshi);
        dest.writeInt(anpaiPeisong);
        dest.writeInt(jiedanMoshi);
        dest.writeInt(paidanren);
        dest.writeInt(peisongrenId);
        dest.writeString(peisongrenMing);
        dest.writeDouble(peisongfei);
        dest.writeInt(peisongfeiLaiyuan);
        dest.writeInt(tuikuanfangshi);
        dest.writeDouble(tuikuanJine);
        dest.writeInt(tuikuanYuanyin);
        dest.writeString(xiadanShijian);
        dest.writeString(fukuanShijian);
        dest.writeString(paisongShijian);
        dest.writeString(quhuoShijian);
        dest.writeString(shouhuoShijian);
        dest.writeString(pingjiaShijian);
        dest.writeString(tuikuanShenqingShijian);
        dest.writeString(tuihuanShijian);
        dest.writeString(tuikuanYunxuShijian);
        dest.writeString(quxiaoShijian);
        dest.writeString(quxiaoYuanyin);
        dest.writeString(wanchengShijian);
        dest.writeString(tuikuanpicihao);
        dest.writeString(tuikuanbiaozhi);
        dest.writeInt(yezhuId);
        dest.writeString(yezhuZhanghao);
        dest.writeInt(yezhuShanchu);
        dest.writeInt(dajianorder);
        dest.writeString(beizhu);
        dest.writeInt(shangpinNum);
        dest.writeString(peisongrenTel);
        dest.writeInt(isShouhou);
        dest.writeInt(orderType);
        dest.writeDouble(duijiangdikou);
        dest.writeDouble(orderMoney);
    }
}
