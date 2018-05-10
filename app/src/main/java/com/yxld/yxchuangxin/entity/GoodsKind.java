package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Android on 2017/10/24
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class GoodsKind extends BaseEntity {

    /**
     * success : true
     * rows : {"rexiaoLists":[{"rexiaoId":1,"rexiaoShangpinId":910,"shangpinMing":"商品小米3","shoujia":2,"zhutu":"upload/img/classify/3.JPEG","tiaoxingma":"20178746546","guige":"台","kucun":null},{"rexiaoId":2,"rexiaoShangpinId":911,"shangpinMing":"小米手机4","shoujia":2,"zhutu":"upload/img/classify/3.JPEG,upload/img/classify/1.JPEG,upload/img/classify/3.JPEG,upload/img/classify/2.JPEG,upload/img/classify/4.JPEG","tiaoxingma":"23565541548","guige":"个","kucun":null}],"dianzhangtuijianLists":[{"tuijianId":1,"tuijianHuodongtupian":"upload/img/classify/6.jpg","tuijianShangpinId":18,"shangpinMing":"乐虎500ml","shoujia":111,"tiaoxingma":"2222","guige":"瓶","kucun":null},{"tuijianId":5,"tuijianHuodongtupian":"upload/img/classify/1.JPEG,upload/img/classify/5.jpg,upload/img/classify/6.jpg,upload/img/classify/7.jpg","tuijianShangpinId":910,"shangpinMing":"商品小米3","shoujia":2,"tiaoxingma":"20178746546","guige":"台","kucun":null},{"tuijianId":3,"tuijianHuodongtupian":"upload/img/classify/6.jpg","tuijianShangpinId":911,"shangpinMing":"小米手机4","shoujia":2,"tiaoxingma":"23565541548","guige":"个","kucun":null},{"tuijianId":4,"tuijianHuodongtupian":"upload/img/classify/6.jpg","tuijianShangpinId":908,"shangpinMing":"苹果10","shoujia":3,"tiaoxingma":"115878445457","guige":" 台","kucun":null},{"tuijianId":2,"tuijianHuodongtupian":"upload/img/classify/1.JPEG,upload/img/classify/5.jpg,upload/img/classify/6.jpg,upload/img/classify/7.jpg","tuijianShangpinId":66,"shangpinMing":"哇哈哈300ml","shoujia":6,"tiaoxingma":"6666","guige":"板","kucun":null},{"tuijianId":6,"tuijianHuodongtupian":"upload/img/classify/1.JPEG,upload/img/classify/5.jpg,upload/img/classify/6.jpg,upload/img/classify/7.jpg","tuijianShangpinId":909,"shangpinMing":"商品测试2","shoujia":2,"tiaoxingma":"201700012157","guige":"个","kucun":null}],"bannerLists":[{"lunboId":1,"lunboHuodongtupian":"20170805205655.jpg","lunboShangpinId":18,"shangpinMing":"乐虎500ml","shoujia":111,"tiaoxingma":"2222","guige":"瓶","kucun":null},{"lunboId":2,"lunboHuodongtupian":"upload/img/classify/3.JPEG","lunboShangpinId":909,"shangpinMing":"商品测试2","shoujia":2,"tiaoxingma":"201700012157","guige":"个","kucun":null},{"lunboId":3,"lunboHuodongtupian":"upload/img/classify/3.JPEG","lunboShangpinId":908,"shangpinMing":"苹果10","shoujia":3,"tiaoxingma":"115878445457","guige":" 台","kucun":null}],"xinpinLists":[{"xinpinId":1,"xinpinShangpinId":908,"shangpinMing":"苹果10","shoujia":3,"zhutu":"upload/img/classify/1.JPEG,upload/img/classify/5.jpg,upload/img/classify/6.jpg,upload/img/classify/7.jpg,upload/img/classify/6.jpg","tiaoxingma":"115878445457","guige":" 台","kucun":null},{"xinpinId":2,"xinpinShangpinId":19,"shangpinMing":"五香瓜子300g","shoujia":5,"zhutu":"upload/img/classify/5.jpg,upload/img/classify/5.jpg,upload/img/classify/6.jpg,upload/img/classify/6.jpg","tiaoxingma":"1111","guige":"包","kucun":null},{"xinpinId":3,"xinpinShangpinId":66,"shangpinMing":"哇哈哈300ml","shoujia":6,"zhutu":"upload/img/classify/1.JPEG,upload/img/classify/2.JPEG,upload/img/classify/2.JPEG,upload/img/classify/4.JPEG,upload/img/classify/5.jpg","tiaoxingma":"6666","guige":"板","kucun":null}]}
     * total : null
     */

    private boolean success;
    private RowsBean rows;
    private Object total;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public RowsBean getRows() {
        return rows;
    }

    public void setRows(RowsBean rows) {
        this.rows = rows;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public static class RowsBean {
        private ArrayList<RexiaoListsBean> rexiaoLists;
        private List<DianzhangtuijianListsBean> dianzhangtuijianLists;
        private List<BannerListsBean> bannerLists;
        private List<XinpinListsBean> xinpinLists;

        public ArrayList<RexiaoListsBean> getRexiaoLists() {
            return rexiaoLists;
        }

        public void setRexiaoLists(ArrayList<RexiaoListsBean> rexiaoLists) {
            this.rexiaoLists = rexiaoLists;
        }

        public List<DianzhangtuijianListsBean> getDianzhangtuijianLists() {
            return dianzhangtuijianLists;
        }

        public void setDianzhangtuijianLists(List<DianzhangtuijianListsBean> dianzhangtuijianLists) {
            this.dianzhangtuijianLists = dianzhangtuijianLists;
        }

        public List<BannerListsBean> getBannerLists() {
            return bannerLists;
        }

        public void setBannerLists(List<BannerListsBean> bannerLists) {
            this.bannerLists = bannerLists;
        }

        public List<XinpinListsBean> getXinpinLists() {
            return xinpinLists;
        }

        public void setXinpinLists(List<XinpinListsBean> xinpinLists) {
            this.xinpinLists = xinpinLists;
        }

        public static class RexiaoListsBean {
            /**
             * rexiaoId : 1
             * rexiaoShangpinId : 910
             * shangpinMing : 商品小米3
             * shoujia : 2
             * zhutu : upload/img/classify/3.JPEG
             * tiaoxingma : 20178746546
             * guige : 台
             * kucun : null
             */

            private int rexiaoId;
            private int rexiaoShangpinId;
            private String shangpinMing;
            private double shoujia;
            private String zhutu;
            private String tiaoxingma;
            private String guige;
            private Object kucun;
            private int selectCount;
            private String rexiaoHuodongtupian;

            public String getRexiaoHuodongtupian() {
                return rexiaoHuodongtupian;
            }

            public void setRexiaoHuodongtupian(String rexiaoHuodongtupian) {
                this.rexiaoHuodongtupian = rexiaoHuodongtupian;
            }

            public int getSelectCount() {
                return selectCount;
            }

            public void setSelectCount(int selectCount) {
                this.selectCount = selectCount;
            }

            public int getRexiaoId() {
                return rexiaoId;
            }

            public void setRexiaoId(int rexiaoId) {
                this.rexiaoId = rexiaoId;
            }

            public int getRexiaoShangpinId() {
                return rexiaoShangpinId;
            }

            public void setRexiaoShangpinId(int rexiaoShangpinId) {
                this.rexiaoShangpinId = rexiaoShangpinId;
            }

            public String getShangpinMing() {
                return shangpinMing;
            }

            public void setShangpinMing(String shangpinMing) {
                this.shangpinMing = shangpinMing;
            }

            public double getShoujia() {
                return shoujia;
            }

            public void setShoujia(double shoujia) {
                this.shoujia = shoujia;
            }

            public String getZhutu() {
                return zhutu;
            }

            public void setZhutu(String zhutu) {
                this.zhutu = zhutu;
            }

            public String getTiaoxingma() {
                return tiaoxingma;
            }

            public void setTiaoxingma(String tiaoxingma) {
                this.tiaoxingma = tiaoxingma;
            }

            public String getGuige() {
                return guige;
            }

            public void setGuige(String guige) {
                this.guige = guige;
            }

            public Object getKucun() {
                return kucun;
            }

            public void setKucun(Object kucun) {
                this.kucun = kucun;
            }
        }

        public static class DianzhangtuijianListsBean {
            /**
             * tuijianId : 1
             * zhutu : upload/img/classify/6.jpg
             * tuijianShangpinId : 18
             * shangpinMing : 乐虎500ml
             * shoujia : 111
             * tiaoxingma : 2222
             * guige : 瓶
             * kucun : null
             */

            private int tuijianId;
            private String zhutu;
            private int tuijianShangpinId;
            private String shangpinMing;
            private double shoujia;
            private String tiaoxingma;
            private String guige;
            private int kucun;
            private int selectCount;
            private int isYejianShangpin;

            public int getIsYejianShangpin() {
                return isYejianShangpin;
            }

            public void setIsYejianShangpin(int isYejianShangpin) {
                this.isYejianShangpin = isYejianShangpin;
            }

            public int getIsDajian() {
                return isDajian;
            }

            public void setIsDajian(int isDajian) {
                this.isDajian = isDajian;
            }

            private int isDajian;

            public int getSelectCount() {
                return selectCount;
            }

            public void setSelectCount(int selectCount) {
                this.selectCount = selectCount;
            }

            public int getTuijianId() {
                return tuijianId;
            }

            public void setTuijianId(int tuijianId) {
                this.tuijianId = tuijianId;
            }

            public String getZhutu() {
                return zhutu;
            }

            public void setZhutu(String zhutu) {
                this.zhutu = zhutu;
            }

            public int getTuijianShangpinId() {
                return tuijianShangpinId;
            }

            public void setTuijianShangpinId(int tuijianShangpinId) {
                this.tuijianShangpinId = tuijianShangpinId;
            }

            public String getShangpinMing() {
                return shangpinMing;
            }

            public void setShangpinMing(String shangpinMing) {
                this.shangpinMing = shangpinMing;
            }

            public double getShoujia() {
                return shoujia;
            }

            public void setShoujia(double shoujia) {
                this.shoujia = shoujia;
            }

            public String getTiaoxingma() {
                return tiaoxingma;
            }

            public void setTiaoxingma(String tiaoxingma) {
                this.tiaoxingma = tiaoxingma;
            }

            public String getGuige() {
                return guige;
            }

            public void setGuige(String guige) {
                this.guige = guige;
            }

            public int getKucun() {
                return kucun;
            }

            public void setKucun(int kucun) {
                this.kucun = kucun;
            }
        }

        public static class BannerListsBean {
            /**
             * lunboId : 1
             * lunboHuodongtupian : 20170805205655.jpg
             * lunboShangpinId : 18
             * shangpinMing : 乐虎500ml
             * shoujia : 111
             * tiaoxingma : 2222
             * guige : 瓶
             * kucun : null
             */

            private int lunboId;
            private String lunboHuodongtupian;
            private int lunboShangpinId;
            private String shangpinMing;
            private double shoujia;
            private String tiaoxingma;
            private String guige;
            private Object kucun;
            private int selectCount;

            public int getSelectCount() {
                return selectCount;
            }

            public void setSelectCount(int selectCount) {
                this.selectCount = selectCount;
            }

            public int getLunboId() {
                return lunboId;
            }

            public void setLunboId(int lunboId) {
                this.lunboId = lunboId;
            }

            public String getLunboHuodongtupian() {
                return lunboHuodongtupian;
            }

            public void setLunboHuodongtupian(String lunboHuodongtupian) {
                this.lunboHuodongtupian = lunboHuodongtupian;
            }

            public int getLunboShangpinId() {
                return lunboShangpinId;
            }

            public void setLunboShangpinId(int lunboShangpinId) {
                this.lunboShangpinId = lunboShangpinId;
            }

            public String getShangpinMing() {
                return shangpinMing;
            }

            public void setShangpinMing(String shangpinMing) {
                this.shangpinMing = shangpinMing;
            }

            public double getShoujia() {
                return shoujia;
            }

            public void setShoujia(double shoujia) {
                this.shoujia = shoujia;
            }

            public String getTiaoxingma() {
                return tiaoxingma;
            }

            public void setTiaoxingma(String tiaoxingma) {
                this.tiaoxingma = tiaoxingma;
            }

            public String getGuige() {
                return guige;
            }

            public void setGuige(String guige) {
                this.guige = guige;
            }

            public Object getKucun() {
                return kucun;
            }

            public void setKucun(Object kucun) {
                this.kucun = kucun;
            }
        }

        public static class XinpinListsBean {
            /**
             * xinpinId : 1
             * xinpinShangpinId : 908
             * shangpinMing : 苹果10
             * shoujia : 3
             * zhutu : upload/img/classify/1.JPEG,upload/img/classify/5.jpg,upload/img/classify/6.jpg,upload/img/classify/7.jpg,upload/img/classify/6.jpg
             * tiaoxingma : 115878445457
             * guige :  台
             * kucun : null
             */

            private int xinpinId;
            private int xinpinShangpinId;
            private String shangpinMing;
            private double shoujia;
            private String zhutu;
            private String tiaoxingma;
            private String guige;
            private int kucun;
            private int selectCount;
            private int isYejianShangpin;

            public int getIsYejianShangpin() {
                return isYejianShangpin;
            }

            public void setIsYejianShangpin(int isYejianShangpin) {
                this.isYejianShangpin = isYejianShangpin;
            }

            public int getIsDajian() {
                return isDajian;
            }

            public void setIsDajian(int isDajian) {
                this.isDajian = isDajian;
            }

            private int isDajian;

            public int getSelectCount() {
                return selectCount;
            }

            public void setSelectCount(int selectCount) {
                this.selectCount = selectCount;
            }

            public int getXinpinId() {
                return xinpinId;
            }

            public void setXinpinId(int xinpinId) {
                this.xinpinId = xinpinId;
            }

            public int getXinpinShangpinId() {
                return xinpinShangpinId;
            }

            public void setXinpinShangpinId(int xinpinShangpinId) {
                this.xinpinShangpinId = xinpinShangpinId;
            }

            public String getShangpinMing() {
                return shangpinMing;
            }

            public void setShangpinMing(String shangpinMing) {
                this.shangpinMing = shangpinMing;
            }

            public double getShoujia() {
                return shoujia;
            }

            public void setShoujia(double shoujia) {
                this.shoujia = shoujia;
            }

            public String getZhutu() {
                return zhutu;
            }

            public void setZhutu(String zhutu) {
                this.zhutu = zhutu;
            }

            public String getTiaoxingma() {
                return tiaoxingma;
            }

            public void setTiaoxingma(String tiaoxingma) {
                this.tiaoxingma = tiaoxingma;
            }

            public String getGuige() {
                return guige;
            }

            public void setGuige(String guige) {
                this.guige = guige;
            }

            public int getKucun() {
                return kucun;
            }

            public void setKucun(int kucun) {
                this.kucun = kucun;
            }
        }
    }
}
