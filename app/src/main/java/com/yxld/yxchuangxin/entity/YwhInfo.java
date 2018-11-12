package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * Created by Administrator on 2018/11/12.
 */

public class YwhInfo extends BaseEntity {

    /**
     * data : {"gongshiData":{"id":2,"title":"发起成立业委会申请","content":"发起成立业委会申请","fileurl":"1233","starttime":"2018-11-08 15:23:06.0","endtime":"2018-11-10 17:01:00.0","startmanid":1,"startman":"杜坤","gongshiType":7,"manNumber":null,"projectId":346,"piCi":1,"faPiao":"2018-11-10 17:01:00.0"},"flow":{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,"flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":2,"phaseName":"开始成立","isChengli":-1}}
     */

    private DataBean data;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * gongshiData : {"id":2,"title":"发起成立业委会申请","content":"发起成立业委会申请","fileurl":"1233","starttime":"2018-11-08 15:23:06.0","endtime":"2018-11-10 17:01:00.0","startmanid":1,"startman":"杜坤","gongshiType":7,"manNumber":null,"projectId":346,"piCi":1,"faPiao":"2018-11-10 17:01:00.0"}
         * flow : {"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,"flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":2,"phaseName":"开始成立","isChengli":-1}
         */

        private GongshiDataBean gongshiData;
        private FlowBean flow;

        public GongshiDataBean getGongshiData() {
            return gongshiData;
        }

        public void setGongshiData(GongshiDataBean gongshiData) {
            this.gongshiData = gongshiData;
        }

        public FlowBean getFlow() {
            return flow;
        }

        public void setFlow(FlowBean flow) {
            this.flow = flow;
        }

        public static class GongshiDataBean {
            /**
             * id : 2
             * title : 发起成立业委会申请
             * content : 发起成立业委会申请
             * fileurl : 1233
             * starttime : 2018-11-08 15:23:06.0
             * endtime : 2018-11-10 17:01:00.0
             * startmanid : 1
             * startman : 杜坤
             * gongshiType : 7
             * manNumber : null
             * projectId : 346
             * piCi : 1
             * faPiao : 2018-11-10 17:01:00.0
             */

            private int id;
            private String title;
            private String content;
            private String fileurl;
            private String starttime;
            private String endtime;
            private int startmanid;
            private String startman;
            private int gongshiType;
            private Object manNumber;
            private int projectId;
            private int piCi;
            private String faPiao;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getFileurl() {
                return fileurl;
            }

            public void setFileurl(String fileurl) {
                this.fileurl = fileurl;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public int getStartmanid() {
                return startmanid;
            }

            public void setStartmanid(int startmanid) {
                this.startmanid = startmanid;
            }

            public String getStartman() {
                return startman;
            }

            public void setStartman(String startman) {
                this.startman = startman;
            }

            public int getGongshiType() {
                return gongshiType;
            }

            public void setGongshiType(int gongshiType) {
                this.gongshiType = gongshiType;
            }

            public Object getManNumber() {
                return manNumber;
            }

            public void setManNumber(Object manNumber) {
                this.manNumber = manNumber;
            }

            public int getProjectId() {
                return projectId;
            }

            public void setProjectId(int projectId) {
                this.projectId = projectId;
            }

            public int getPiCi() {
                return piCi;
            }

            public void setPiCi(int piCi) {
                this.piCi = piCi;
            }

            public String getFaPiao() {
                return faPiao;
            }

            public void setFaPiao(String faPiao) {
                this.faPiao = faPiao;
            }
        }

        public static class FlowBean {
            /**
             * id : null
             * flowName : null
             * flowCommitTime : null
             * flowEndTime : null
             * flowStatus : null
             * flowType : null
             * flowOperator : null
             * flowOperatorId : null
             * projectId : null
             * pici : null
             * phaseState : 2
             * phaseName : 开始成立
             * isChengli : -1
             */

            private Object id;
            private Object flowName;
            private Object flowCommitTime;
            private Object flowEndTime;
            private Object flowStatus;
            private Object flowType;
            private Object flowOperator;
            private Object flowOperatorId;
            private Object projectId;
            private Object pici;
            private int phaseState;
            private String phaseName;
            private int isChengli;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getFlowName() {
                return flowName;
            }

            public void setFlowName(Object flowName) {
                this.flowName = flowName;
            }

            public Object getFlowCommitTime() {
                return flowCommitTime;
            }

            public void setFlowCommitTime(Object flowCommitTime) {
                this.flowCommitTime = flowCommitTime;
            }

            public Object getFlowEndTime() {
                return flowEndTime;
            }

            public void setFlowEndTime(Object flowEndTime) {
                this.flowEndTime = flowEndTime;
            }

            public Object getFlowStatus() {
                return flowStatus;
            }

            public void setFlowStatus(Object flowStatus) {
                this.flowStatus = flowStatus;
            }

            public Object getFlowType() {
                return flowType;
            }

            public void setFlowType(Object flowType) {
                this.flowType = flowType;
            }

            public Object getFlowOperator() {
                return flowOperator;
            }

            public void setFlowOperator(Object flowOperator) {
                this.flowOperator = flowOperator;
            }

            public Object getFlowOperatorId() {
                return flowOperatorId;
            }

            public void setFlowOperatorId(Object flowOperatorId) {
                this.flowOperatorId = flowOperatorId;
            }

            public Object getProjectId() {
                return projectId;
            }

            public void setProjectId(Object projectId) {
                this.projectId = projectId;
            }

            public Object getPici() {
                return pici;
            }

            public void setPici(Object pici) {
                this.pici = pici;
            }

            public int getPhaseState() {
                return phaseState;
            }

            public void setPhaseState(int phaseState) {
                this.phaseState = phaseState;
            }

            public String getPhaseName() {
                return phaseName;
            }

            public void setPhaseName(String phaseName) {
                this.phaseName = phaseName;
            }

            public int getIsChengli() {
                return isChengli;
            }

            public void setIsChengli(int isChengli) {
                this.isChengli = isChengli;
            }
        }
    }
}
