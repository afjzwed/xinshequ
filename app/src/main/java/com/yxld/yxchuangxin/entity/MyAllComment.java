package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * 作者：Android on 2017/10/24
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class MyAllComment extends BaseEntity {

    /**
     * success : true
     * data : [{"id":15,"dingdanBianhao":"cs123456789","pingjiaDengji":5,"shangpingId":11,"shangpinMing":"哇哈哈500ml","yezhuId":2218,"yezhuShouji":"18711001698","pingjiaShijian":"2017-08-29 12:12:12.0","pingjiaNeirong":"测试，很好喝，日期新鲜","zhuangtai":3,"huifuZhuangtai":-1,"huifu":null,"huifuShijian":null,"huifurenId":null,"huifurenMing":null,"xiangmuId":346,"gongsiId":1,"xiangmuMing":null,"shangpinTu":null},{"id":16,"dingdanBianhao":"cs123456789","pingjiaDengji":5,"shangpingId":11,"shangpinMing":"哇哈哈500ml","yezhuId":2218,"yezhuShouji":"18711001698","pingjiaShijian":"2017-08-29 12:12:12.0","pingjiaNeirong":"测试，很好喝，日期新鲜","zhuangtai":2,"huifuZhuangtai":-1,"huifu":null,"huifuShijian":null,"huifurenId":null,"huifurenMing":null,"xiangmuId":346,"gongsiId":1,"xiangmuMing":null,"shangpinTu":null},{"id":1,"dingdanBianhao":"cs115","pingjiaDengji":5,"shangpingId":66,"shangpinMing":"哇哈哈500ml","yezhuId":2218,"yezhuShouji":"18711001698","pingjiaShijian":"2017-08-29 00:00:00.0","pingjiaNeirong":"测试，很好喝，日期新鲜","zhuangtai":2,"huifuZhuangtai":1,"huifu":"感谢您的光临","huifuShijian":"2017-10-23 17:18:09.0","huifurenId":202,"huifurenMing":"刘诗_总部","xiangmuId":346,"gongsiId":1,"xiangmuMing":null,"shangpinTu":"upload/img/classify/1.JPEG"},{"id":13,"dingdanBianhao":"cs123456789","pingjiaDengji":5,"shangpingId":11,"shangpinMing":"哇哈哈500ml","yezhuId":2218,"yezhuShouji":"18711001698","pingjiaShijian":"2017-08-29 00:00:00.0","pingjiaNeirong":"测试，很好喝，日期新鲜测试，很好喝，日期新鲜测试，很好喝，日期新鲜测试，很好喝，日期新鲜测试，很好喝","zhuangtai":3,"huifuZhuangtai":1,"huifu":"感谢您的光临","huifuShijian":"2017-08-30 00:00:00.0","huifurenId":1,"huifurenMing":"管理员","xiangmuId":346,"gongsiId":1,"xiangmuMing":null,"shangpinTu":null},{"id":4,"dingdanBianhao":"cs111","pingjiaDengji":3,"shangpingId":19,"shangpinMing":"五香瓜子300g","yezhuId":2218,"yezhuShouji":"17645451355","pingjiaShijian":"2017-08-26 00:09:00.0","pingjiaNeirong":"测试，差评","zhuangtai":3,"huifuZhuangtai":1,"huifu":"","huifuShijian":null,"huifurenId":null,"huifurenMing":"","xiangmuId":346,"gongsiId":1,"xiangmuMing":null,"shangpinTu":"upload/img/classify/5.jpg"}]
     * total : 5
     * error : null
     */

    private boolean success;
    private int total;
    private Object error;
    private List<DataBean> data;
    private List<DataBean> rows;

    public List<DataBean> getRows() {
        return rows;
    }

    public void setRows(List<DataBean> rows) {
        this.rows = rows;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 15
         * dingdanBianhao : cs123456789
         * pingjiaDengji : 5
         * shangpingId : 11
         * shangpinMing : 哇哈哈500ml
         * yezhuId : 2218
         * yezhuShouji : 18711001698
         * pingjiaShijian : 2017-08-29 12:12:12.0
         * pingjiaNeirong : 测试，很好喝，日期新鲜
         * zhuangtai : 3
         * huifuZhuangtai : -1
         * huifu : null
         * huifuShijian : null
         * huifurenId : null
         * huifurenMing : null
         * xiangmuId : 346
         * gongsiId : 1
         * xiangmuMing : null
         * shangpinTu : null
         */

        private int id;
        private String dingdanBianhao;
        private int pingjiaDengji;
        private int shangpingId;
        private String shangpinMing;
        private int yezhuId;
        private String yezhuShouji;
        private String pingjiaShijian;
        private String pingjiaNeirong;
        private int zhuangtai;
        private int huifuZhuangtai;
        private String huifu;
        private Object huifuShijian;
        private Object huifurenId;
        private Object huifurenMing;
        private int xiangmuId;
        private int gongsiId;
        private Object xiangmuMing;
        private String shangpinTu;
        private double jiage;

        public double getJiage() {
            return jiage;
        }

        public void setJiage(double jiage) {
            this.jiage = jiage;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDingdanBianhao() {
            return dingdanBianhao;
        }

        public void setDingdanBianhao(String dingdanBianhao) {
            this.dingdanBianhao = dingdanBianhao;
        }

        public int getPingjiaDengji() {
            return pingjiaDengji;
        }

        public void setPingjiaDengji(int pingjiaDengji) {
            this.pingjiaDengji = pingjiaDengji;
        }

        public int getShangpingId() {
            return shangpingId;
        }

        public void setShangpingId(int shangpingId) {
            this.shangpingId = shangpingId;
        }

        public String getShangpinMing() {
            return shangpinMing;
        }

        public void setShangpinMing(String shangpinMing) {
            this.shangpinMing = shangpinMing;
        }

        public int getYezhuId() {
            return yezhuId;
        }

        public void setYezhuId(int yezhuId) {
            this.yezhuId = yezhuId;
        }

        public String getYezhuShouji() {
            return yezhuShouji;
        }

        public void setYezhuShouji(String yezhuShouji) {
            this.yezhuShouji = yezhuShouji;
        }

        public String getPingjiaShijian() {
            return pingjiaShijian;
        }

        public void setPingjiaShijian(String pingjiaShijian) {
            this.pingjiaShijian = pingjiaShijian;
        }

        public String getPingjiaNeirong() {
            return pingjiaNeirong;
        }

        public void setPingjiaNeirong(String pingjiaNeirong) {
            this.pingjiaNeirong = pingjiaNeirong;
        }

        public int getZhuangtai() {
            return zhuangtai;
        }

        public void setZhuangtai(int zhuangtai) {
            this.zhuangtai = zhuangtai;
        }

        public int getHuifuZhuangtai() {
            return huifuZhuangtai;
        }

        public void setHuifuZhuangtai(int huifuZhuangtai) {
            this.huifuZhuangtai = huifuZhuangtai;
        }

        public String getHuifu() {
            return huifu;
        }

        public void setHuifu(String huifu) {
            this.huifu = huifu;
        }

        public Object getHuifuShijian() {
            return huifuShijian;
        }

        public void setHuifuShijian(Object huifuShijian) {
            this.huifuShijian = huifuShijian;
        }

        public Object getHuifurenId() {
            return huifurenId;
        }

        public void setHuifurenId(Object huifurenId) {
            this.huifurenId = huifurenId;
        }

        public Object getHuifurenMing() {
            return huifurenMing;
        }

        public void setHuifurenMing(Object huifurenMing) {
            this.huifurenMing = huifurenMing;
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

        public Object getXiangmuMing() {
            return xiangmuMing;
        }

        public void setXiangmuMing(Object xiangmuMing) {
            this.xiangmuMing = xiangmuMing;
        }

        public String getShangpinTu() {
            return shangpinTu;
        }

        public void setShangpinTu(String shangpinTu) {
            this.shangpinTu = shangpinTu;
        }
    }
}
