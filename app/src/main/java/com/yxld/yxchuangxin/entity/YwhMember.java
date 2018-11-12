package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by William on 2018/11/12.
 */

public class YwhMember extends BaseEntity {

    /**
     * success : true
     * code : 200
     * error : null
     * data : [{"id":6,"cfname":"julia","idcard":"430902201811072606","phone":"15084950970","expect":"two expect",
     * "building":"3 building","unit":"3 unit","roomNumber":"A104","driscipt":"interesting","isdelel":1,
     * "addtime":"2018-11-07 15:48:16.0","type":3,"projectid":346,"workUnit":"asia","otherWorks":"job",
     * "workPosition":"lead","pici":1},{"id":8,"cfname":"marry","idcard":"430902201811072606","phone":"15084950970",
     * "expect":"two expect","building":"3 building","unit":"3 unit","roomNumber":"A104","driscipt":"interesting",
     * "isdelel":1,"addtime":"2018-11-07 15:37:34.0","type":3,"projectid":346,"workUnit":"asia","otherWorks":"job",
     * "workPosition":"lead","pici":1}]
     * rows : [{"id":6,"cfname":"julia","idcard":"430902201811072606","phone":"15084950970","expect":"two expect",
     * "building":"3 building","unit":"3 unit","roomNumber":"A104","driscipt":"interesting","isdelel":1,
     * "addtime":"2018-11-07 15:48:16.0","type":3,"projectid":346,"workUnit":"asia","otherWorks":"job",
     * "workPosition":"lead","pici":1},{"id":8,"cfname":"marry","idcard":"430902201811072606","phone":"15084950970",
     * "expect":"two expect","building":"3 building","unit":"3 unit","roomNumber":"A104","driscipt":"interesting",
     * "isdelel":1,"addtime":"2018-11-07 15:37:34.0","type":3,"projectid":346,"workUnit":"asia","otherWorks":"job",
     * "workPosition":"lead","pici":1}]
     * total : 2
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
         * id : 6
         * cfname : julia
         * idcard : 430902201811072606
         * phone : 15084950970
         * expect : two expect
         * building : 3 building
         * unit : 3 unit
         * roomNumber : A104
         * driscipt : interesting
         * isdelel : 1
         * addtime : 2018-11-07 15:48:16.0
         * type : 3
         * projectid : 346
         * workUnit : asia
         * otherWorks : job
         * workPosition : lead
         * pici : 1
         */

        private int id;
        private String cfname;
        private String idcard;
        private String phone;
        private String expect;
        private String building;
        private String unit;
        private String roomNumber;
        private String driscipt;
        private int isdelel;
        private String addtime;
        private int type;//类型1:筹备组人员 2:业委会候选人 3:业委会人员4:业委会主任5:业委会副主任
        private int projectid;
        private String workUnit;
        private String otherWorks;
        private String workPosition;
        private int pici;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCfname() {
            return cfname;
        }

        public void setCfname(String cfname) {
            this.cfname = cfname;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getExpect() {
            return expect == null ? "" : expect;
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

        public String getDriscipt() {
            return driscipt;
        }

        public void setDriscipt(String driscipt) {
            this.driscipt = driscipt;
        }

        public int getIsdelel() {
            return isdelel;
        }

        public void setIsdelel(int isdelel) {
            this.isdelel = isdelel;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getProjectid() {
            return projectid;
        }

        public void setProjectid(int projectid) {
            this.projectid = projectid;
        }

        public String getWorkUnit() {
            return workUnit;
        }

        public void setWorkUnit(String workUnit) {
            this.workUnit = workUnit;
        }

        public String getOtherWorks() {
            return otherWorks==null?"":otherWorks;
        }

        public void setOtherWorks(String otherWorks) {
            this.otherWorks = otherWorks;
        }

        public String getWorkPosition() {
            return workPosition;
        }

        public void setWorkPosition(String workPosition) {
            this.workPosition = workPosition;
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
         * id : 6
         * cfname : julia
         * idcard : 430902201811072606
         * phone : 15084950970
         * expect : two expect
         * building : 3 building
         * unit : 3 unit
         * roomNumber : A104
         * driscipt : interesting
         * isdelel : 1
         * addtime : 2018-11-07 15:48:16.0
         * type : 3
         * projectid : 346
         * workUnit : asia
         * otherWorks : job
         * workPosition : lead
         * pici : 1
         */

        private int id;
        private String cfname;
        private String idcard;
        private String phone;
        private String expect;
        private String building;
        private String unit;
        private String roomNumber;
        private String driscipt;
        private int isdelel;
        private String addtime;
        private int type;
        private int projectid;
        private String workUnit;
        private String otherWorks;
        private String workPosition;
        private int pici;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCfname() {
            return cfname;
        }

        public void setCfname(String cfname) {
            this.cfname = cfname;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getDriscipt() {
            return driscipt;
        }

        public void setDriscipt(String driscipt) {
            this.driscipt = driscipt;
        }

        public int getIsdelel() {
            return isdelel;
        }

        public void setIsdelel(int isdelel) {
            this.isdelel = isdelel;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getProjectid() {
            return projectid;
        }

        public void setProjectid(int projectid) {
            this.projectid = projectid;
        }

        public String getWorkUnit() {
            return workUnit;
        }

        public void setWorkUnit(String workUnit) {
            this.workUnit = workUnit;
        }

        public String getOtherWorks() {
            return otherWorks;
        }

        public void setOtherWorks(String otherWorks) {
            this.otherWorks = otherWorks;
        }

        public String getWorkPosition() {
            return workPosition;
        }

        public void setWorkPosition(String workPosition) {
            this.workPosition = workPosition;
        }

        public int getPici() {
            return pici;
        }

        public void setPici(int pici) {
            this.pici = pici;
        }
    }
}
