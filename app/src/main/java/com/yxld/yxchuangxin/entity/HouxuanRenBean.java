package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by William on 2018/11/14.
 */

public class HouxuanRenBean extends BaseEntity {

    /**
     * success : true
     * code : 200
     * error : null
     * data : [{"id":1149,"name":"李涛","unit":"1","building":"1","roomNumber":"2317","area":"1","yezhuId":null,
     * "fwId":1099},{"id":3406,"name":"李涛","unit":"2","building":"29","roomNumber":"307","area":"1","yezhuId":null,
     * "fwId":2670},{"id":3401,"name":"向磊","unit":"888","building":"888","roomNumber":"888","area":"1",
     * "yezhuId":null,"fwId":3557},{"id":3401,"name":"向磊","unit":"1","building":"1","roomNumber":"1901","area":"1",
     * "yezhuId":null,"fwId":1033}]
     * rows : [{"id":1149,"name":"李涛","unit":"1","building":"1","roomNumber":"2317","area":"1","yezhuId":null,
     * "fwId":1099},{"id":3406,"name":"李涛","unit":"2","building":"29","roomNumber":"307","area":"1","yezhuId":null,
     * "fwId":2670},{"id":3401,"name":"向磊","unit":"888","building":"888","roomNumber":"888","area":"1",
     * "yezhuId":null,"fwId":3557},{"id":3401,"name":"向磊","unit":"1","building":"1","roomNumber":"1901","area":"1",
     * "yezhuId":null,"fwId":1033}]
     * total : null
     * token : null
     */

    private boolean success;
    private int code;
    private Object error;
    private Object total;
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

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
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
         * id : 1149
         * name : 李涛
         * unit : 1
         * building : 1
         * roomNumber : 2317
         * area : 1
         * yezhuId : null
         * fwId : 1099
         */

        private int id;//业主ID
        private String name;//人名
        private String unit;//单元
        private String building;//楼栋
        private String roomNumber;//房号
        private String area;//区/期
        private int yezhuId;
        private int fwId;//被推荐人 的房屋id

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

        public int getYezhuId() {
            return yezhuId;
        }

        public void setYezhuId(int yezhuId) {
            this.yezhuId = yezhuId;
        }

        public int getFwId() {
            return fwId;
        }

        public void setFwId(int fwId) {
            this.fwId = fwId;
        }
    }

    public static class RowsBean {
        /**
         * id : 1149
         * name : 李涛
         * unit : 1
         * building : 1
         * roomNumber : 2317
         * area : 1
         * yezhuId : null
         * fwId : 1099
         */

        private int id;
        private String name;
        private String unit;
        private String building;
        private String roomNumber;
        private String area;
        private Object yezhuId;
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

        public Object getYezhuId() {
            return yezhuId;
        }

        public void setYezhuId(Object yezhuId) {
            this.yezhuId = yezhuId;
        }

        public int getFwId() {
            return fwId;
        }

        public void setFwId(int fwId) {
            this.fwId = fwId;
        }
    }
}
