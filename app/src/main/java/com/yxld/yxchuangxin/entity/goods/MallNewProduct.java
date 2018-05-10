package com.yxld.yxchuangxin.entity.goods;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * @author xlei
 * @Date 2017/10/24.
 */

public class MallNewProduct  extends BaseEntity{
    //商品id
    private int id;
    // @ExcelField(title="商品名")
    private String shangpinMing;
    //@ExcelField(title="规格")
    private String guige;
    //  @ExcelField(title="条形码")
    private String tiaoxingma;
    private int fenlei1;
    //   @ExcelField(title="一级分类名称")
    private String fenlei1Name;
    private int fenlei2;
    //  @ExcelField(title="二级分类名称")
    private String fenlei2Name;
    //  @ExcelField(title="进货价")
    private Double jinhuojia;
    // @ExcelField(title="售价")
    private Double shoujia;
    // @ExcelField(title="高销量")
    private int gaoxiaoliang;
    // @ExcelField(title="高人气")
    private int gaorenqi;
    // @ExcelField(title="活动展示")
    private int huodongZhanshi;
    //  @ExcelField(title="是否大件")
    private int shangpinDajian;
    //  @ExcelField(title="排序")
    private int paixu;
    // @ExcelField(title="夜间销售")
    private int yejianXiaoshou;
    // @ExcelField(title="前端显示")
    private int xianshi;
    // @ExcelField(title="上架")
    private int shangjia;
    //  @ExcelField(title="拆分")
    private int chaifen;
    //	@ExcelField(title="主图")
    private String zhutu;
    //	@ExcelField(title="详情")
    private String xiangqing;
    private int xiangmuId;
    private int gongsiId;
    //	@ExcelField(title="订单流水号")
    // @ExcelField(title="夜间库存")
    private int yejianKucun;
    private String wuziBianhao;
    //  @ExcelField(title="添加时间")
    private String tianjiaShijian;
    //库存
    private int kuncun;

    //销量
    private int xiaoliang;

    private int selectCount;

    public int getSelectCount() {
        return selectCount;
    }

    public void setSelectCount(int selectCount) {
        this.selectCount = selectCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShangpinMing() {
        return shangpinMing;
    }

    public void setShangpinMing(String shangpinMing) {
        this.shangpinMing = shangpinMing;
    }

    public String getGuige() {
        return guige;
    }

    public void setGuige(String guige) {
        this.guige = guige;
    }

    public String getTiaoxingma() {
        return tiaoxingma;
    }

    public void setTiaoxingma(String tiaoxingma) {
        this.tiaoxingma = tiaoxingma;
    }

    public int getFenlei1() {
        return fenlei1;
    }

    public void setFenlei1(int fenlei1) {
        this.fenlei1 = fenlei1;
    }

    public String getFenlei1Name() {
        return fenlei1Name;
    }

    public void setFenlei1Name(String fenlei1Name) {
        this.fenlei1Name = fenlei1Name;
    }

    public int getFenlei2() {
        return fenlei2;
    }

    public void setFenlei2(int fenlei2) {
        this.fenlei2 = fenlei2;
    }

    public String getFenlei2Name() {
        return fenlei2Name;
    }

    public void setFenlei2Name(String fenlei2Name) {
        this.fenlei2Name = fenlei2Name;
    }

    public Double getJinhuojia() {
        return jinhuojia;
    }

    public void setJinhuojia(Double jinhuojia) {
        this.jinhuojia = jinhuojia;
    }

    public Double getShoujia() {
        return shoujia;
    }

    public void setShoujia(Double shoujia) {
        this.shoujia = shoujia;
    }

    public int getGaoxiaoliang() {
        return gaoxiaoliang;
    }

    public void setGaoxiaoliang(int gaoxiaoliang) {
        this.gaoxiaoliang = gaoxiaoliang;
    }

    public int getGaorenqi() {
        return gaorenqi;
    }

    public void setGaorenqi(int gaorenqi) {
        this.gaorenqi = gaorenqi;
    }

    public int getHuodongZhanshi() {
        return huodongZhanshi;
    }

    public void setHuodongZhanshi(int huodongZhanshi) {
        this.huodongZhanshi = huodongZhanshi;
    }

    public int getShangpinDajian() {
        return shangpinDajian;
    }

    public void setShangpinDajian(int shangpinDajian) {
        this.shangpinDajian = shangpinDajian;
    }

    public int getPaixu() {
        return paixu;
    }

    public void setPaixu(int paixu) {
        this.paixu = paixu;
    }

    public int getYejianXiaoshou() {
        return yejianXiaoshou;
    }

    public void setYejianXiaoshou(int yejianXiaoshou) {
        this.yejianXiaoshou = yejianXiaoshou;
    }

    public int getXianshi() {
        return xianshi;
    }

    public void setXianshi(int xianshi) {
        this.xianshi = xianshi;
    }

    public int getShangjia() {
        return shangjia;
    }

    public void setShangjia(int shangjia) {
        this.shangjia = shangjia;
    }

    public int getChaifen() {
        return chaifen;
    }

    public void setChaifen(int chaifen) {
        this.chaifen = chaifen;
    }

    public String getZhutu() {
        return zhutu;
    }

    public void setZhutu(String zhutu) {
        this.zhutu = zhutu;
    }

    public String getXiangqing() {
        return xiangqing;
    }

    public void setXiangqing(String xiangqing) {
        this.xiangqing = xiangqing;
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

    public int getYejianKucun() {
        return yejianKucun;
    }

    public void setYejianKucun(int yejianKucun) {
        this.yejianKucun = yejianKucun;
    }

    public String getWuziBianhao() {
        return wuziBianhao;
    }

    public void setWuziBianhao(String wuziBianhao) {
        this.wuziBianhao = wuziBianhao;
    }

    public String getTianjiaShijian() {
        return tianjiaShijian;
    }

    public void setTianjiaShijian(String tianjiaShijian) {
        this.tianjiaShijian = tianjiaShijian;
    }

    public int getKuncun() {
        return kuncun;
    }

    public void setKuncun(int kuncun) {
        this.kuncun = kuncun;
    }

    public int getXiaoliang() {
        return xiaoliang;
    }

    public void setXiaoliang(int xiaoliang) {
        this.xiaoliang = xiaoliang;
    }

    @Override
    public String toString() {
        return "MallNewProduct{" +
                "id=" + id +
                ", shangpinMing='" + shangpinMing + '\'' +
                ", guige='" + guige + '\'' +
                ", tiaoxingma='" + tiaoxingma + '\'' +
                ", fenlei1=" + fenlei1 +
                ", fenlei1Name='" + fenlei1Name + '\'' +
                ", fenlei2=" + fenlei2 +
                ", fenlei2Name='" + fenlei2Name + '\'' +
                ", jinhuojia=" + jinhuojia +
                ", shoujia=" + shoujia +
                ", gaoxiaoliang=" + gaoxiaoliang +
                ", gaorenqi=" + gaorenqi +
                ", huodongZhanshi=" + huodongZhanshi +
                ", shangpinDajian=" + shangpinDajian +
                ", paixu=" + paixu +
                ", yejianXiaoshou=" + yejianXiaoshou +
                ", xianshi=" + xianshi +
                ", shangjia=" + shangjia +
                ", chaifen=" + chaifen +
                ", zhutu='" + zhutu + '\'' +
                ", xiangqing='" + xiangqing + '\'' +
                ", xiangmuId=" + xiangmuId +
                ", gongsiId=" + gongsiId +
                ", yejianKucun=" + yejianKucun +
                ", wuziBianhao='" + wuziBianhao + '\'' +
                ", tianjiaShijian='" + tianjiaShijian + '\'' +
                ", kuncun=" + kuncun +
                '}';
    }
}
