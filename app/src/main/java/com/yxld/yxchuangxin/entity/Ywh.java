package com.yxld.yxchuangxin.entity;

import java.util.List;

/**
 * Created by William on 2018/11/11.
 */

public class Ywh {


    /**
     * success : true
     * code : 200
     * msg : 操作成功
     * error : null
     * data : {"gongshiData":{"id":2,"title":"发起成立业委会申请","content":"发起成立业委会申请","fileurl":"1233",
     * "starttime":"2018-11-08 15:23:06.0","endtime":"2018-11-10 17:01:00.0","startmanid":1,"startman":"杜坤",
     * "gongshiType":7,"manNumber":null,"projectId":346,"piCi":1,"faPiao":"2018-11-10 17:01:00.0"},
     * "flows":[{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
     * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
     * "phaseName":"开始成立"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
     * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
     * "phaseName":"成立筹备组"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
     * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
     * "phaseName":"筹备组工作"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
     * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
     * "phaseName":"候选人确认"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
     * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
     * "phaseName":"业主大会"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
     * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
     * "phaseName":"备案阶段"}],"currentFlow":{"id":1,"currentPhaseName":"开始成立","currentPhaseStatus":-1,"projectId":346,
     * "pici":1}}
     * rows : {"gongshiData":{"id":2,"title":"发起成立业委会申请","content":"发起成立业委会申请","fileurl":"1233",
     * "starttime":"2018-11-08 15:23:06.0","endtime":"2018-11-10 17:01:00.0","startmanid":1,"startman":"杜坤",
     * "gongshiType":7,"manNumber":null,"projectId":346,"piCi":1,"faPiao":"2018-11-10 17:01:00.0"},
     * "flows":[{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
     * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
     * "phaseName":"开始成立"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
     * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
     * "phaseName":"成立筹备组"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
     * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
     * "phaseName":"筹备组工作"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
     * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
     * "phaseName":"候选人确认"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
     * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
     * "phaseName":"业主大会"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
     * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
     * "phaseName":"备案阶段"}],"currentFlow":{"id":1,"currentPhaseName":"开始成立","currentPhaseStatus":-1,"projectId":346,
     * "pici":1}}
     * total : null
     * token : null
     */

    private boolean success;
    private int code;
    private String msg;
    private Object error;
    private DataBean data;
    private RowsBean rows;
    private Object total;
    private Object token;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public RowsBean getRows() {
        return rows;
    }

    public void setRows(RowsBean rows) {
        this.rows = rows;
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

    public static class DataBean {
        /**
         * gongshiData : {"id":2,"title":"发起成立业委会申请","content":"发起成立业委会申请","fileurl":"1233","starttime":"2018-11-08
         * 15:23:06.0","endtime":"2018-11-10 17:01:00.0","startmanid":1,"startman":"杜坤","gongshiType":7,
         * "manNumber":null,"projectId":346,"piCi":1,"faPiao":"2018-11-10 17:01:00.0"}
         * flows : [{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
         * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
         * "phaseName":"开始成立"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
         * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
         * "phaseName":"成立筹备组"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,
         * "flowStatus":null,"flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,
         * "phaseState":-1,"phaseName":"筹备组工作"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,
         * "flowStatus":null,"flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,
         * "phaseState":-1,"phaseName":"候选人确认"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,
         * "flowStatus":null,"flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,
         * "phaseState":-1,"phaseName":"业主大会"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,
         * "flowStatus":null,"flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,
         * "phaseState":-1,"phaseName":"备案阶段"}]
         * currentFlow : {"id":1,"currentPhaseName":"开始成立","currentPhaseStatus":-1,"projectId":346,"pici":1}
         */

        private GongshiDataBean gongshiData;
        private CurrentFlowBean currentFlow;
        private List<FlowsBean> flows;

        public GongshiDataBean getGongshiData() {
            return gongshiData;
        }

        public void setGongshiData(GongshiDataBean gongshiData) {
            this.gongshiData = gongshiData;
        }

        public CurrentFlowBean getCurrentFlow() {
            return currentFlow;
        }

        public void setCurrentFlow(CurrentFlowBean currentFlow) {
            this.currentFlow = currentFlow;
        }

        public List<FlowsBean> getFlows() {
            return flows;
        }

        public void setFlows(List<FlowsBean> flows) {
            this.flows = flows;
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

        public static class CurrentFlowBean {
            /**
             * id : 1
             * currentPhaseName : 开始成立
             * currentPhaseStatus : -1
             * projectId : 346
             * pici : 1
             */

            private int id;
            private String currentPhaseName;
            private int currentPhaseStatus;
            private int projectId;
            private int pici;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCurrentPhaseName() {
                return currentPhaseName;
            }

            public void setCurrentPhaseName(String currentPhaseName) {
                this.currentPhaseName = currentPhaseName;
            }

            public int getCurrentPhaseStatus() {
                return currentPhaseStatus;
            }

            public void setCurrentPhaseStatus(int currentPhaseStatus) {
                this.currentPhaseStatus = currentPhaseStatus;
            }

            public int getProjectId() {
                return projectId;
            }

            public void setProjectId(int projectId) {
                this.projectId = projectId;
            }

            public int getPici() {
                return pici;
            }

            public void setPici(int pici) {
                this.pici = pici;
            }
        }

        public static class FlowsBean {
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
             * phaseState : -1
             * phaseName : 开始成立
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
        }
    }

    public static class RowsBean {
        /**
         * gongshiData : {"id":2,"title":"发起成立业委会申请","content":"发起成立业委会申请","fileurl":"1233","starttime":"2018-11-08
         * 15:23:06.0","endtime":"2018-11-10 17:01:00.0","startmanid":1,"startman":"杜坤","gongshiType":7,
         * "manNumber":null,"projectId":346,"piCi":1,"faPiao":"2018-11-10 17:01:00.0"}
         * flows : [{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
         * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
         * "phaseName":"开始成立"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,"flowStatus":null,
         * "flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,"phaseState":-1,
         * "phaseName":"成立筹备组"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,
         * "flowStatus":null,"flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,
         * "phaseState":-1,"phaseName":"筹备组工作"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,
         * "flowStatus":null,"flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,
         * "phaseState":-1,"phaseName":"候选人确认"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,
         * "flowStatus":null,"flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,
         * "phaseState":-1,"phaseName":"业主大会"},{"id":null,"flowName":null,"flowCommitTime":null,"flowEndTime":null,
         * "flowStatus":null,"flowType":null,"flowOperator":null,"flowOperatorId":null,"projectId":null,"pici":null,
         * "phaseState":-1,"phaseName":"备案阶段"}]
         * currentFlow : {"id":1,"currentPhaseName":"开始成立","currentPhaseStatus":-1,"projectId":346,"pici":1}
         */

        private GongshiDataBeanX gongshiData;
        private CurrentFlowBeanX currentFlow;
        private List<FlowsBeanX> flows;

        public GongshiDataBeanX getGongshiData() {
            return gongshiData;
        }

        public void setGongshiData(GongshiDataBeanX gongshiData) {
            this.gongshiData = gongshiData;
        }

        public CurrentFlowBeanX getCurrentFlow() {
            return currentFlow;
        }

        public void setCurrentFlow(CurrentFlowBeanX currentFlow) {
            this.currentFlow = currentFlow;
        }

        public List<FlowsBeanX> getFlows() {
            return flows;
        }

        public void setFlows(List<FlowsBeanX> flows) {
            this.flows = flows;
        }

        public static class GongshiDataBeanX {
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

        public static class CurrentFlowBeanX {
            /**
             * id : 1
             * currentPhaseName : 开始成立
             * currentPhaseStatus : -1
             * projectId : 346
             * pici : 1
             */

            private int id;
            private String currentPhaseName;
            private int currentPhaseStatus;
            private int projectId;
            private int pici;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCurrentPhaseName() {
                return currentPhaseName;
            }

            public void setCurrentPhaseName(String currentPhaseName) {
                this.currentPhaseName = currentPhaseName;
            }

            public int getCurrentPhaseStatus() {
                return currentPhaseStatus;
            }

            public void setCurrentPhaseStatus(int currentPhaseStatus) {
                this.currentPhaseStatus = currentPhaseStatus;
            }

            public int getProjectId() {
                return projectId;
            }

            public void setProjectId(int projectId) {
                this.projectId = projectId;
            }

            public int getPici() {
                return pici;
            }

            public void setPici(int pici) {
                this.pici = pici;
            }
        }

        public static class FlowsBeanX {
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
             * phaseState : -1
             * phaseName : 开始成立
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
        }
    }
}
