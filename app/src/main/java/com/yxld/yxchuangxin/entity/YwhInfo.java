package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

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
        private YwhCurrentflow.DataBean.FlowBean flow;

        public YwhCurrentflow.DataBean.FlowBean getFlow() {
            return flow;
        }

        public void setFlow(YwhCurrentflow.DataBean.FlowBean flow) {
            this.flow = flow;
        }
    }
}
