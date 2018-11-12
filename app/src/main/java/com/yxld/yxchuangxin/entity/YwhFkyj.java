package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by William on 2018/11/11.
 */

public class YwhFkyj extends BaseEntity {

    /**
     * success : true
     * code : 200
     * error : null
     * data : [{"id":5,"resultmanid":3407,"subtime":"2018-11-11 17:22:21.0","resultmanname":"吴芳","expect":"3",
     * "building":"29","unit":"2","roomNumber":"130","resultdesc":"不同意","callbackman":null,"callbackmanid":null,
     * "resulttime":null,"resultcontent":null,"resultphone":"15084950979","projectId":346,"resultType":4,
     * "gongshiId":1,"pici":1},{"id":6,"resultmanid":3407,"subtime":"2018-11-11 17:22:21.0","resultmanname":"吴芳",
     * "expect":"4","building":"29","unit":"2","roomNumber":"666","resultdesc":"某某不能胜任","callbackman":null,
     * "callbackmanid":null,"resulttime":null,"resultcontent":null,"resultphone":"15084950979","projectId":346,
     * "resultType":4,"gongshiId":1,"pici":1},{"id":7,"resultmanid":3407,"subtime":"2018-11-11 17:22:21.0",
     * "resultmanname":"吴芳","expect":"5","building":"29","unit":"2","roomNumber":"112","resultdesc":"先让他试试吧",
     * "callbackman":null,"callbackmanid":null,"resulttime":null,"resultcontent":null,"resultphone":"15084950979",
     * "projectId":346,"resultType":4,"gongshiId":1,"pici":1},{"id":8,"resultmanid":3407,"subtime":"2018-11-11
     * 17:22:21.0","resultmanname":"吴芳","expect":"6","building":"29","unit":"2","roomNumber":"333",
     * "resultdesc":"某某工作能力极强","callbackman":null,"callbackmanid":null,"resulttime":null,"resultcontent":null,
     * "resultphone":"15084950979","projectId":346,"resultType":4,"gongshiId":1,"pici":1},{"id":9,"resultmanid":3407,
     * "subtime":"2018-11-11 17:22:21.0","resultmanname":"吴芳","expect":"7","building":"29","unit":"2",
     * "roomNumber":"001","resultdesc":"小明更能胜任这份工作","callbackman":null,"callbackmanid":null,"resulttime":null,
     * "resultcontent":null,"resultphone":"15084950979","projectId":346,"resultType":4,"gongshiId":1,"pici":1},
     * {"id":10,"resultmanid":3407,"subtime":"2018-11-11 17:22:21.0","resultmanname":"吴芳","expect":"8",
     * "building":"29","unit":"2","roomNumber":"100","resultdesc":"对名单没什么异议","callbackman":null,"callbackmanid":null,
     * "resulttime":null,"resultcontent":null,"resultphone":"15084950979","projectId":346,"resultType":4,
     * "gongshiId":1,"pici":1}]
     * rows : [{"id":5,"resultmanid":3407,"subtime":"2018-11-11 17:22:21.0","resultmanname":"吴芳","expect":"3",
     * "building":"29","unit":"2","roomNumber":"130","resultdesc":"不同意","callbackman":null,"callbackmanid":null,
     * "resulttime":null,"resultcontent":null,"resultphone":"15084950979","projectId":346,"resultType":4,
     * "gongshiId":1,"pici":1},{"id":6,"resultmanid":3407,"subtime":"2018-11-11 17:22:21.0","resultmanname":"吴芳",
     * "expect":"4","building":"29","unit":"2","roomNumber":"666","resultdesc":"某某不能胜任","callbackman":null,
     * "callbackmanid":null,"resulttime":null,"resultcontent":null,"resultphone":"15084950979","projectId":346,
     * "resultType":4,"gongshiId":1,"pici":1},{"id":7,"resultmanid":3407,"subtime":"2018-11-11 17:22:21.0",
     * "resultmanname":"吴芳","expect":"5","building":"29","unit":"2","roomNumber":"112","resultdesc":"先让他试试吧",
     * "callbackman":null,"callbackmanid":null,"resulttime":null,"resultcontent":null,"resultphone":"15084950979",
     * "projectId":346,"resultType":4,"gongshiId":1,"pici":1},{"id":8,"resultmanid":3407,"subtime":"2018-11-11
     * 17:22:21.0","resultmanname":"吴芳","expect":"6","building":"29","unit":"2","roomNumber":"333",
     * "resultdesc":"某某工作能力极强","callbackman":null,"callbackmanid":null,"resulttime":null,"resultcontent":null,
     * "resultphone":"15084950979","projectId":346,"resultType":4,"gongshiId":1,"pici":1},{"id":9,"resultmanid":3407,
     * "subtime":"2018-11-11 17:22:21.0","resultmanname":"吴芳","expect":"7","building":"29","unit":"2",
     * "roomNumber":"001","resultdesc":"小明更能胜任这份工作","callbackman":null,"callbackmanid":null,"resulttime":null,
     * "resultcontent":null,"resultphone":"15084950979","projectId":346,"resultType":4,"gongshiId":1,"pici":1},
     * {"id":10,"resultmanid":3407,"subtime":"2018-11-11 17:22:21.0","resultmanname":"吴芳","expect":"8",
     * "building":"29","unit":"2","roomNumber":"100","resultdesc":"对名单没什么异议","callbackman":null,"callbackmanid":null,
     * "resulttime":null,"resultcontent":null,"resultphone":"15084950979","projectId":346,"resultType":4,
     * "gongshiId":1,"pici":1}]
     * total : 9
     * token : null
     */

    private boolean success;
    private int code;
    private Object error;
    private int total;
    private Object token;
    private List<DataBean> data;
    private List<RowsBean> rows;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class DataBean {
        /**
         * id : 5
         * resultmanid : 3407
         * subtime : 2018-11-11 17:22:21.0
         * resultmanname : 吴芳
         * expect : 3
         * building : 29
         * unit : 2
         * roomNumber : 130
         * resultdesc : 不同意
         * callbackman : null
         * callbackmanid : null
         * resulttime : null
         * resultcontent : null
         * resultphone : 15084950979
         * projectId : 346
         * resultType : 4
         * gongshiId : 1
         * pici : 1
         */

        private int id;
        private int resultmanid;
        private String subtime;
        private String resultmanname;
        private String expect;
        private String building;
        private String unit;
        private String roomNumber;
        private String resultdesc;
        private Object callbackman;
        private Object callbackmanid;
        private Object resulttime;
        private String resultcontent;
        private String resultphone;
        private int projectId;
        private int resultType;
        private int gongshiId;
        private int pici;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getResultmanid() {
            return resultmanid;
        }

        public void setResultmanid(int resultmanid) {
            this.resultmanid = resultmanid;
        }

        public String getSubtime() {
            return subtime;
        }

        public void setSubtime(String subtime) {
            this.subtime = subtime;
        }

        public String getResultmanname() {
            return resultmanname;
        }

        public void setResultmanname(String resultmanname) {
            this.resultmanname = resultmanname;
        }

        public String getExpect() {
            return expect;
        }

        public void setExpect(String expect) {
            this.expect = expect;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public String getResultdesc() {
            return resultdesc;
        }

        public void setResultdesc(String resultdesc) {
            this.resultdesc = resultdesc;
        }

        public Object getCallbackman() {
            return callbackman;
        }

        public void setCallbackman(Object callbackman) {
            this.callbackman = callbackman;
        }

        public Object getCallbackmanid() {
            return callbackmanid;
        }

        public void setCallbackmanid(Object callbackmanid) {
            this.callbackmanid = callbackmanid;
        }

        public Object getResulttime() {
            return resulttime;
        }

        public void setResulttime(Object resulttime) {
            this.resulttime = resulttime;
        }

        public String getResultcontent() {
            return resultcontent;
        }

        public void setResultcontent(String resultcontent) {
            this.resultcontent = resultcontent;
        }

        public String getResultphone() {
            return resultphone;
        }

        public void setResultphone(String resultphone) {
            this.resultphone = resultphone;
        }

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public int getResultType() {
            return resultType;
        }

        public void setResultType(int resultType) {
            this.resultType = resultType;
        }

        public int getGongshiId() {
            return gongshiId;
        }

        public void setGongshiId(int gongshiId) {
            this.gongshiId = gongshiId;
        }

        public int getPici() {
            return pici;
        }

        public void setPici(int pici) {
            this.pici = pici;
        }
    }

    public static class RowsBean {
        /**
         * id : 5
         * resultmanid : 3407
         * subtime : 2018-11-11 17:22:21.0
         * resultmanname : 吴芳
         * expect : 3
         * building : 29
         * unit : 2
         * roomNumber : 130
         * resultdesc : 不同意
         * callbackman : null
         * callbackmanid : null
         * resulttime : null
         * resultcontent : null
         * resultphone : 15084950979
         * projectId : 346
         * resultType : 4
         * gongshiId : 1
         * pici : 1
         */

        private int id;
        private int resultmanid;
        private String subtime;
        private String resultmanname;
        private String expect;
        private String building;
        private String unit;
        private String roomNumber;
        private String resultdesc;
        private Object callbackman;
        private Object callbackmanid;
        private Object resulttime;
        private Object resultcontent;
        private String resultphone;
        private int projectId;
        private int resultType;
        private int gongshiId;
        private int pici;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getResultmanid() {
            return resultmanid;
        }

        public void setResultmanid(int resultmanid) {
            this.resultmanid = resultmanid;
        }

        public String getSubtime() {
            return subtime;
        }

        public void setSubtime(String subtime) {
            this.subtime = subtime;
        }

        public String getResultmanname() {
            return resultmanname;
        }

        public void setResultmanname(String resultmanname) {
            this.resultmanname = resultmanname;
        }

        public String getExpect() {
            return expect;
        }

        public void setExpect(String expect) {
            this.expect = expect;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public String getResultdesc() {
            return resultdesc;
        }

        public void setResultdesc(String resultdesc) {
            this.resultdesc = resultdesc;
        }

        public Object getCallbackman() {
            return callbackman;
        }

        public void setCallbackman(Object callbackman) {
            this.callbackman = callbackman;
        }

        public Object getCallbackmanid() {
            return callbackmanid;
        }

        public void setCallbackmanid(Object callbackmanid) {
            this.callbackmanid = callbackmanid;
        }

        public Object getResulttime() {
            return resulttime;
        }

        public void setResulttime(Object resulttime) {
            this.resulttime = resulttime;
        }

        public Object getResultcontent() {
            return resultcontent;
        }

        public void setResultcontent(Object resultcontent) {
            this.resultcontent = resultcontent;
        }

        public String getResultphone() {
            return resultphone;
        }

        public void setResultphone(String resultphone) {
            this.resultphone = resultphone;
        }

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public int getResultType() {
            return resultType;
        }

        public void setResultType(int resultType) {
            this.resultType = resultType;
        }

        public int getGongshiId() {
            return gongshiId;
        }

        public void setGongshiId(int gongshiId) {
            this.gongshiId = gongshiId;
        }

        public int getPici() {
            return pici;
        }

        public void setPici(int pici) {
            this.pici = pici;
        }
    }
}
