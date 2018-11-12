package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by William on 2018/11/12.
 */

public class OpinionSurveyEntity extends BaseEntity {

    /**
     * success : true
     * code : 200
     * error : null
     * data : [{"id":36,"subjectTitle":"㝟","subjectType":1,"addTime":"2018-11-11 16:42:10","userId":59,
     * "userName":"李涛的110101日个公司","subjectRemark":"法国红酒股份合计","subjectState":1,"startTime":"2018-11-06 00:00:00",
     * "endTime":"2018-11-23 00:00:00","provinceId":"43","cityId":"4301","districtId":"430102",
     * "streetId":"430102001","communityId":"430102001001","projectId":null,"period":null,"building":null,
     * "unit":null,"questionList":null,"answer":null,"samplePerson":null,"progress":null},{"id":35,
     * "subjectTitle":"问卷主题","subjectType":1,"addTime":"2018-11-11 15:58:33","userId":59,"userName":"李涛的110101日个公司",
     * "subjectRemark":"111111111111111111111","subjectState":1,"startTime":"2018-11-07 00:00:00",
     * "endTime":"2018-11-24 00:00:00","provinceId":"43","cityId":"4301","districtId":"430102",
     * "streetId":"430102001","communityId":"430102001001","projectId":null,"period":null,"building":null,
     * "unit":null,"questionList":null,"answer":null,"samplePerson":null,"progress":null},{"id":34,
     * "subjectTitle":"【社区月老牵线活动】之人员情况调查","subjectType":1,"addTime":"2018-11-10 18:46:52","userId":59,
     * "userName":"李涛的110101日个公司","subjectRemark":"为服务广大单身男女青年，早日寻找到医生伴侣而。。。。。。。。。。。。。。。。。。","subjectState":1,
     * "startTime":"2018-11-08 00:00:00","endTime":"2018-11-24 00:00:00","provinceId":"43","cityId":"4301",
     * "districtId":"430102","streetId":"430102001","communityId":"430102001001","projectId":null,"period":null,
     * "building":null,"unit":null,"questionList":null,"answer":null,"samplePerson":null,"progress":null},{"id":7,
     * "subjectTitle":"呀呀呀","subjectType":1,"addTime":"2018-11-01 13:43:03","userId":1,"userName":"wf",
     * "subjectRemark":null,"subjectState":1,"startTime":"2018-10-01 18:07:06","endTime":"2018-11-30 09:36:06",
     * "provinceId":"43","cityId":"4301","districtId":"430102","streetId":"430102001","communityId":"430102001001",
     * "projectId":346,"period":null,"building":null,"unit":null,"questionList":null,"answer":null,
     * "samplePerson":null,"progress":null},{"id":6,"subjectTitle":"假如生活欺骗了你","subjectType":1,"addTime":"2018-10-23
     * 11:02:19","userId":1,"userName":"wf","subjectRemark":"假如生活欺骗了你该怎么办","subjectState":1,"startTime":"2018-11-30
     * 09:36:06","endTime":"2018-11-30 09:36:06","provinceId":"43","cityId":"4301","districtId":"430102",
     * "streetId":"430102001","communityId":"430102001001","projectId":346,"period":null,"building":null,"unit":null,
     * "questionList":null,"answer":null,"samplePerson":null,"progress":null},{"id":5,"subjectTitle":"一屋不扫何以扫天下",
     * "subjectType":1,"addTime":"2018-10-23 10:58:25","userId":1,"userName":"wf","subjectRemark":"一屋不扫何以扫天下",
     * "subjectState":1,"startTime":"2018-10-24 09:36:03","endTime":"2018-11-30 09:36:06","provinceId":"43",
     * "cityId":"4301","districtId":"430102","streetId":"430102001","communityId":"430102001001","projectId":346,
     * "period":null,"building":null,"unit":null,"questionList":null,"answer":null,"samplePerson":null,"progress":null}]
     * rows : [{"id":36,"subjectTitle":"㝟","subjectType":1,"addTime":"2018-11-11 16:42:10","userId":59,
     * "userName":"李涛的110101日个公司","subjectRemark":"法国红酒股份合计","subjectState":1,"startTime":"2018-11-06 00:00:00",
     * "endTime":"2018-11-23 00:00:00","provinceId":"43","cityId":"4301","districtId":"430102",
     * "streetId":"430102001","communityId":"430102001001","projectId":null,"period":null,"building":null,
     * "unit":null,"questionList":null,"answer":null,"samplePerson":null,"progress":null}]
     * total : 10
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
         * id : 36
         * subjectTitle : 㝟
         * subjectType : 1
         * addTime : 2018-11-11 16:42:10
         * userId : 59
         * userName : 李涛的110101日个公司
         * subjectRemark : 法国红酒股份合计
         * subjectState : 1
         * startTime : 2018-11-06 00:00:00
         * endTime : 2018-11-23 00:00:00
         * provinceId : 43
         * cityId : 4301
         * districtId : 430102
         * streetId : 430102001
         * communityId : 430102001001
         * projectId : null
         * period : null
         * building : null
         * unit : null
         * questionList : null
         * answer : null
         * samplePerson : null
         * progress : null
         */

        private int id;
        private String subjectTitle;
        private int subjectType;
        private String addTime;
        private int userId;
        private String userName;
        private String subjectRemark;
        private int subjectState;
        private String startTime;
        private String endTime;
        private String provinceId;
        private String cityId;
        private String districtId;
        private String streetId;
        private String communityId;
        private Object projectId;
        private Object period;
        private Object building;
        private Object unit;
        private Object questionList;
        private Object answer;
        private Object samplePerson;
        private Object progress;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubjectTitle() {
            return subjectTitle;
        }

        public void setSubjectTitle(String subjectTitle) {
            this.subjectTitle = subjectTitle;
        }

        public int getSubjectType() {
            return subjectType;
        }

        public void setSubjectType(int subjectType) {
            this.subjectType = subjectType;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getSubjectRemark() {
            return subjectRemark;
        }

        public void setSubjectRemark(String subjectRemark) {
            this.subjectRemark = subjectRemark;
        }

        public int getSubjectState() {
            return subjectState;
        }

        public void setSubjectState(int subjectState) {
            this.subjectState = subjectState;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getStreetId() {
            return streetId;
        }

        public void setStreetId(String streetId) {
            this.streetId = streetId;
        }

        public String getCommunityId() {
            return communityId;
        }

        public void setCommunityId(String communityId) {
            this.communityId = communityId;
        }

        public Object getProjectId() {
            return projectId;
        }

        public void setProjectId(Object projectId) {
            this.projectId = projectId;
        }

        public Object getPeriod() {
            return period;
        }

        public void setPeriod(Object period) {
            this.period = period;
        }

        public Object getBuilding() {
            return building;
        }

        public void setBuilding(Object building) {
            this.building = building;
        }

        public Object getUnit() {
            return unit;
        }

        public void setUnit(Object unit) {
            this.unit = unit;
        }

        public Object getQuestionList() {
            return questionList;
        }

        public void setQuestionList(Object questionList) {
            this.questionList = questionList;
        }

        public Object getAnswer() {
            return answer;
        }

        public void setAnswer(Object answer) {
            this.answer = answer;
        }

        public Object getSamplePerson() {
            return samplePerson;
        }

        public void setSamplePerson(Object samplePerson) {
            this.samplePerson = samplePerson;
        }

        public Object getProgress() {
            return progress;
        }

        public void setProgress(Object progress) {
            this.progress = progress;
        }
    }

    public static class RowsBean {
        /**
         * id : 36
         * subjectTitle : 㝟
         * subjectType : 1
         * addTime : 2018-11-11 16:42:10
         * userId : 59
         * userName : 李涛的110101日个公司
         * subjectRemark : 法国红酒股份合计
         * subjectState : 1
         * startTime : 2018-11-06 00:00:00
         * endTime : 2018-11-23 00:00:00
         * provinceId : 43
         * cityId : 4301
         * districtId : 430102
         * streetId : 430102001
         * communityId : 430102001001
         * projectId : null
         * period : null
         * building : null
         * unit : null
         * questionList : null
         * answer : null
         * samplePerson : null
         * progress : null
         */

        private int id;
        private String subjectTitle;
        private int subjectType;
        private String addTime;
        private int userId;
        private String userName;
        private String subjectRemark;
        private int subjectState;
        private String startTime;
        private String endTime;
        private String provinceId;
        private String cityId;
        private String districtId;
        private String streetId;
        private String communityId;
        private Object projectId;
        private Object period;
        private Object building;
        private Object unit;
        private Object questionList;
        private Object answer;
        private Object samplePerson;
        private Object progress;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubjectTitle() {
            return subjectTitle;
        }

        public void setSubjectTitle(String subjectTitle) {
            this.subjectTitle = subjectTitle;
        }

        public int getSubjectType() {
            return subjectType;
        }

        public void setSubjectType(int subjectType) {
            this.subjectType = subjectType;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getSubjectRemark() {
            return subjectRemark;
        }

        public void setSubjectRemark(String subjectRemark) {
            this.subjectRemark = subjectRemark;
        }

        public int getSubjectState() {
            return subjectState;
        }

        public void setSubjectState(int subjectState) {
            this.subjectState = subjectState;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getStreetId() {
            return streetId;
        }

        public void setStreetId(String streetId) {
            this.streetId = streetId;
        }

        public String getCommunityId() {
            return communityId;
        }

        public void setCommunityId(String communityId) {
            this.communityId = communityId;
        }

        public Object getProjectId() {
            return projectId;
        }

        public void setProjectId(Object projectId) {
            this.projectId = projectId;
        }

        public Object getPeriod() {
            return period;
        }

        public void setPeriod(Object period) {
            this.period = period;
        }

        public Object getBuilding() {
            return building;
        }

        public void setBuilding(Object building) {
            this.building = building;
        }

        public Object getUnit() {
            return unit;
        }

        public void setUnit(Object unit) {
            this.unit = unit;
        }

        public Object getQuestionList() {
            return questionList;
        }

        public void setQuestionList(Object questionList) {
            this.questionList = questionList;
        }

        public Object getAnswer() {
            return answer;
        }

        public void setAnswer(Object answer) {
            this.answer = answer;
        }

        public Object getSamplePerson() {
            return samplePerson;
        }

        public void setSamplePerson(Object samplePerson) {
            this.samplePerson = samplePerson;
        }

        public Object getProgress() {
            return progress;
        }

        public void setProgress(Object progress) {
            this.progress = progress;
        }
    }
}
