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

    private List<ResultsBean> data;
    private int code;


    public List<ResultsBean> getData() {
        return data;
    }

    public void setData(List<ResultsBean> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ResultsBean {

        /**
         * id : 1149
         * name : 李涛
         * unit : 1
         * building : 1
         * roomNumber : 2317
         * area : 1
         * fwId : 1099
         */

        private int id;
        private String name;
        private String unit;
        private String building;
        private String roomNumber;
        private String area;
        private int fwId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getFwId() {
            return fwId;
        }

        public void setFwId(int fwId) {
            this.fwId = fwId;
        }
    }

}
