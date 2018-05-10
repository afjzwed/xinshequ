package com.yxld.yxchuangxin.entity;
////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//         佛祖保佑       永无BUG     永不修改                  //
////////////////////////////////////////////////////////////////////

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by 89876 on 2017/5/13 0013.
 * 个人主页：http://yishangfei.me
 * Github:https://github.com/yishangfei
 * <p>
 */
public class CxwyCommon extends BaseEntity {

    /**
     * code : 0
     * data : [{"cvoList":[{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":20,"tongdaohao":1,"tongdaoname":"东城摄像头","watchTime":0},{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":21,"tongdaohao":2,"tongdaoname":"东城摄像头2","watchTime":0},{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":22,"tongdaohao":3,"tongdaoname":"东城摄像头3","watchTime":0},{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":23,"tongdaohao":4,"tongdaoname":"东城摄像头4","watchTime":0},{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":24,"tongdaohao":5,"tongdaoname":"东城摄像头5","watchTime":0},{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":25,"tongdaohao":6,"tongdaoname":"东城摄像头6","watchTime":0},{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":26,"tongdaohao":7,"tongdaoname":"东城摄像头7","watchTime":0},{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":27,"tongdaohao":8,"tongdaoname":"东城摄像头8","watchTime":0}],"shebeiName":"东城录像机","shebeiid":27,"shebeixuliehao":"757580121"},{"cvoList":[{"intervalTime":0,"shebeixuliehao":"744365348","sxtid":15,"tongdaohao":2,"tongdaoname":"摄像头9号","watchTime":0},{"intervalTime":0,"shebeixuliehao":"744365348","sxtid":18,"tongdaohao":1,"tongdaoname":"中远公馆三楼摄像头","watchTime":0}],"shebeiName":"录像机一号","shebeiid":744365360,"shebeixuliehao":"744365348"},{"cvoList":[],"shebeiName":"zt1号","shebeiid":744365363,"shebeixuliehao":"111111"},{"cvoList":[{"intervalTime":0,"shebeixuliehao":"777081029","sxtid":34,"tongdaohao":1,"tongdaoname":"走廊","watchTime":0},{"intervalTime":0,"shebeixuliehao":"777081029","sxtid":35,"tongdaohao":2,"tongdaoname":"财务室","watchTime":0},{"intervalTime":0,"shebeixuliehao":"777081029","sxtid":36,"tongdaohao":3,"tongdaoname":"地下车库出入口","watchTime":0},{"intervalTime":0,"shebeixuliehao":"777081029","sxtid":37,"tongdaohao":4,"tongdaoname":"地下车库出入口2","watchTime":0},{"intervalTime":0,"shebeixuliehao":"777081029","sxtid":38,"tongdaohao":5,"tongdaoname":"服务中心大门","watchTime":0},{"intervalTime":0,"shebeixuliehao":"777081029","sxtid":39,"tongdaohao":6,"tongdaoname":"后门岗亭","watchTime":0},{"intervalTime":0,"shebeixuliehao":"777081029","sxtid":40,"tongdaohao":7,"tongdaoname":"服务大厅","watchTime":0}],"shebeiName":"爱迪生","shebeiid":744365366,"shebeixuliehao":"777081029"}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cvoList : [{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":20,"tongdaohao":1,"tongdaoname":"东城摄像头","watchTime":0},{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":21,"tongdaohao":2,"tongdaoname":"东城摄像头2","watchTime":0},{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":22,"tongdaohao":3,"tongdaoname":"东城摄像头3","watchTime":0},{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":23,"tongdaohao":4,"tongdaoname":"东城摄像头4","watchTime":0},{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":24,"tongdaohao":5,"tongdaoname":"东城摄像头5","watchTime":0},{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":25,"tongdaohao":6,"tongdaoname":"东城摄像头6","watchTime":0},{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":26,"tongdaohao":7,"tongdaoname":"东城摄像头7","watchTime":0},{"intervalTime":0,"shebeixuliehao":"757580121","sxtid":27,"tongdaohao":8,"tongdaoname":"东城摄像头8","watchTime":0}]
         * shebeiName : 东城录像机
         * shebeiid : 27
         * shebeixuliehao : 757580121
         */

        private String shebeiName;
        private int shebeiid;
        private String shebeixuliehao;
        private List<CvoListBean> cvoList;

        public String getShebeiName() {
            return shebeiName;
        }

        public void setShebeiName(String shebeiName) {
            this.shebeiName = shebeiName;
        }

        public int getShebeiid() {
            return shebeiid;
        }

        public void setShebeiid(int shebeiid) {
            this.shebeiid = shebeiid;
        }

        public String getShebeixuliehao() {
            return shebeixuliehao;
        }

        public void setShebeixuliehao(String shebeixuliehao) {
            this.shebeixuliehao = shebeixuliehao;
        }

        public List<CvoListBean> getCvoList() {
            return cvoList;
        }

        public void setCvoList(List<CvoListBean> cvoList) {
            this.cvoList = cvoList;
        }

        public static class CvoListBean {
            /**
             * intervalTime : 0
             * shebeixuliehao : 757580121
             * sxtid : 20
             * tongdaohao : 1
             * tongdaoname : 东城摄像头
             * watchTime : 0
             */

            private int intervalTime;
            private String shebeixuliehao;
            private int sxtid;
            private int tongdaohao;
            private String tongdaoname;
            private int watchTime;

            public int getIntervalTime() {
                return intervalTime;
            }

            public void setIntervalTime(int intervalTime) {
                this.intervalTime = intervalTime;
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

            public int getWatchTime() {
                return watchTime;
            }

            public void setWatchTime(int watchTime) {
                this.watchTime = watchTime;
            }
        }
    }
}
