package com.yxld.yxchuangxin.entity;

import java.util.List;

/**
 * 作者：Android on 2017/7/27
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class XMsxt extends BaseBack{

    /**
     * total : 3
     * data : [{"devmac":"03bfc0b72325ea66","shebeixuliehao":"121212121212","loginName":"admin","loginpsw":"123456","shebeiName":"中远录像机","sxt":[{"id":1,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":1,"tongdaohao":1,"tongdaoname":"中远的摄像头","watchtime":60},{"id":2,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":2,"tongdaohao":2,"tongdaoname":"中远的1摄像头","watchtime":60},{"id":3,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":3,"tongdaohao":3,"tongdaoname":"中远的2摄像头","watchtime":60},{"id":4,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":4,"tongdaohao":4,"tongdaoname":"中远的3摄像头","watchtime":60},{"id":5,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":5,"tongdaohao":5,"tongdaoname":"中远的4摄像头","watchtime":60},{"id":6,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":6,"tongdaohao":6,"tongdaoname":"中远的5摄像头","watchtime":60},{"id":7,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":7,"tongdaohao":7,"tongdaoname":"中远的6摄像头","watchtime":60},{"id":8,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":8,"tongdaohao":8,"tongdaoname":"中远的7摄像头","watchtime":60},{"id":9,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":9,"tongdaohao":9,"tongdaoname":"中远的8摄像头","watchtime":60},{"id":10,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":10,"tongdaohao":10,"tongdaoname":"中远的9摄像头","watchtime":60},{"id":11,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":11,"tongdaohao":11,"tongdaoname":"中远的10摄像头","watchtime":60},{"id":12,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":12,"tongdaohao":12,"tongdaoname":"中远的11摄像头","watchtime":60}]},{"devmac":"03bfc0b72325ea66","shebeixuliehao":"121212121212","loginName":"admin","loginpsw":"123456","shebeiName":"中远录像机","sxt":[{"id":1,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":1,"tongdaohao":1,"tongdaoname":"中远的摄像头","watchtime":60},{"id":2,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":2,"tongdaohao":2,"tongdaoname":"中远的1摄像头","watchtime":60},{"id":3,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":3,"tongdaohao":3,"tongdaoname":"中远的2摄像头","watchtime":60},{"id":4,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":4,"tongdaohao":4,"tongdaoname":"中远的3摄像头","watchtime":60},{"id":5,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":5,"tongdaohao":5,"tongdaoname":"中远的4摄像头","watchtime":60},{"id":6,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":6,"tongdaohao":6,"tongdaoname":"中远的5摄像头","watchtime":60},{"id":7,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":7,"tongdaohao":7,"tongdaoname":"中远的6摄像头","watchtime":60},{"id":8,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":8,"tongdaohao":8,"tongdaoname":"中远的7摄像头","watchtime":60},{"id":9,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":9,"tongdaohao":9,"tongdaoname":"中远的8摄像头","watchtime":60},{"id":10,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":10,"tongdaohao":10,"tongdaoname":"中远的9摄像头","watchtime":60},{"id":11,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":11,"tongdaohao":11,"tongdaoname":"中远的10摄像头","watchtime":60},{"id":12,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":12,"tongdaohao":12,"tongdaoname":"中远的11摄像头","watchtime":60}]}]
     * success : true
     * rows : 1
     * error : false
     * status : 1
     */

    private String total;
    private boolean success;
    private String rows;
    private String error;
    private String status;
    private List<DataBean> data;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * devmac : 03bfc0b72325ea66
         * shebeixuliehao : 121212121212
         * loginName : admin
         * loginpsw : 123456
         * shebeiName : 中远录像机
         * sxt : [{"id":1,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":1,"tongdaohao":1,"tongdaoname":"中远的摄像头","watchtime":60},{"id":2,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":2,"tongdaohao":2,"tongdaoname":"中远的1摄像头","watchtime":60},{"id":3,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":3,"tongdaohao":3,"tongdaoname":"中远的2摄像头","watchtime":60},{"id":4,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":4,"tongdaohao":4,"tongdaoname":"中远的3摄像头","watchtime":60},{"id":5,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":5,"tongdaohao":5,"tongdaoname":"中远的4摄像头","watchtime":60},{"id":6,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":6,"tongdaohao":6,"tongdaoname":"中远的5摄像头","watchtime":60},{"id":7,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":7,"tongdaohao":7,"tongdaoname":"中远的6摄像头","watchtime":60},{"id":8,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":8,"tongdaohao":8,"tongdaoname":"中远的7摄像头","watchtime":60},{"id":9,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":9,"tongdaohao":9,"tongdaoname":"中远的8摄像头","watchtime":60},{"id":10,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":10,"tongdaohao":10,"tongdaoname":"中远的9摄像头","watchtime":60},{"id":11,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":11,"tongdaohao":11,"tongdaoname":"中远的10摄像头","watchtime":60},{"id":12,"intervaltime":100,"shebeixuliehao":"03bfc0b72325ea66","sxtid":12,"tongdaohao":12,"tongdaoname":"中远的11摄像头","watchtime":60}]
         */

        private String devmac;
        private String shebeixuliehao;
        private String loginName;
        private String loginpsw;
        private String shebeiName;
        private List<SxtBean> sxt;

        public String getDevmac() {
            return devmac;
        }

        public void setDevmac(String devmac) {
            this.devmac = devmac;
        }

        public String getShebeixuliehao() {
            return shebeixuliehao;
        }

        public void setShebeixuliehao(String shebeixuliehao) {
            this.shebeixuliehao = shebeixuliehao;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getLoginpsw() {
            return loginpsw;
        }

        public void setLoginpsw(String loginpsw) {
            this.loginpsw = loginpsw;
        }

        public String getShebeiName() {
            return shebeiName;
        }

        public void setShebeiName(String shebeiName) {
            this.shebeiName = shebeiName;
        }

        public List<SxtBean> getSxt() {
            return sxt;
        }

        public void setSxt(List<SxtBean> sxt) {
            this.sxt = sxt;
        }

        public static class SxtBean {
            /**
             * id : 1
             * intervaltime : 100
             * shebeixuliehao : 03bfc0b72325ea66
             * sxtid : 1
             * tongdaohao : 1
             * tongdaoname : 中远的摄像头
             * watchtime : 60
             */

            private int id;
            private int intervaltime;
            private String shebeixuliehao;
            private int sxtid;
            private int tongdaohao;
            private String tongdaoname;
            private int watchtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIntervaltime() {
                return intervaltime;
            }

            public void setIntervaltime(int intervaltime) {
                this.intervaltime = intervaltime;
            }

            public String getShebeixuliehao() {
                return shebeixuliehao;
            }

            public void setShebeixuliehao(String shebeixuliehao) {
                this.shebeixuliehao = shebeixuliehao;
            }

            public int getSxtid() {
                return sxtid;
            }

            public void setSxtid(int sxtid) {
                this.sxtid = sxtid;
            }

            public int getTongdaohao() {
                return tongdaohao;
            }

            public void setTongdaohao(int tongdaohao) {
                this.tongdaohao = tongdaohao;
            }

            public String getTongdaoname() {
                return tongdaoname;
            }

            public void setTongdaoname(String tongdaoname) {
                this.tongdaoname = tongdaoname;
            }

            public int getWatchtime() {
                return watchtime;
            }

            public void setWatchtime(int watchtime) {
                this.watchtime = watchtime;
            }
        }
    }
}
