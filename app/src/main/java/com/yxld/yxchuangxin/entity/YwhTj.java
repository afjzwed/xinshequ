package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/11/13.
 */

public class YwhTj extends BaseEntity {

    /**
     * data : {"page":1,"rows":10,"total":6,"results":[{"id":1,"cfname":"张三","idcard":"43062119951223275x","phone":"15173009326","expect":"2","building":"3","unit":"四单元","roomNumber":"209","driscipt":"非常和善的一个人","isdelel":1,"addtime":"2018-09-05 09:13:10.0","type":1,"projectid":346,"workUnit":"长沙工商银行分行","otherWorks":null,"workPosition":"大堂经理","pici":1}]}
     */

    private DataBean data;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * page : 1
         * rows : 10
         * total : 6
         * results : [{"id":1,"cfname":"张三","idcard":"43062119951223275x","phone":"15173009326","expect":"2","building":"3","unit":"四单元","roomNumber":"209","driscipt":"非常和善的一个人","isdelel":1,"addtime":"2018-09-05 09:13:10.0","type":1,"projectid":346,"workUnit":"长沙工商银行分行","otherWorks":null,"workPosition":"大堂经理","pici":1}]
         */

        private int page;
        private int rows;
        private int total;
        private List<ResultsBean> results;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ResultsBean> getResults() {
            return results;
        }

        public void setResults(List<ResultsBean> results) {
            this.results = results;
        }

        public static class ResultsBean {
            /**
             * id : 1
             * cfname : 张三
             * idcard : 43062119951223275x
             * phone : 15173009326
             * expect : 2
             * building : 3
             * unit : 四单元
             * roomNumber : 209
             * driscipt : 非常和善的一个人
             * isdelel : 1
             * addtime : 2018-09-05 09:13:10.0
             * type : 1
             * projectid : 346
             * workUnit : 长沙工商银行分行
             * otherWorks : null
             * workPosition : 大堂经理
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
            private Object otherWorks;
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

            public Object getOtherWorks() {
                return otherWorks;
            }

            public void setOtherWorks(Object otherWorks) {
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
}
