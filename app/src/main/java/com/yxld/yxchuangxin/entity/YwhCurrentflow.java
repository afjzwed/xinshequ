package com.yxld.yxchuangxin.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * Created by William on 2018/11/11.
 */

public class YwhCurrentflow extends BaseEntity {


    /**
     * success : true
     * code : 200
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

        private FlowBean flow;
        private List<FlowsBean> flows;

        public FlowBean getFlow() {
            return flow;
        }

        public void setFlow(FlowBean flow) {
            this.flow = flow;
        }


        public List<FlowsBean> getFlows() {
            return flows;
        }

        public void setFlows(List<FlowsBean> flows) {
            this.flows = flows;
        }

        public static class FlowBean {


            /**
             * phaseState : 1
             * phaseName : 成立筹备组
             * isChengli : null
             * gongshi : {"id":2,"title":"发起成立业委会申请","content":"发起成立业委会申请","fileurl":"http://p9zwbgynz.bkt.clouddn
             * .com/QQ%E5%9B%BE%E7%89%8720181112195210.gif","starttime":"2018-11-08 15:23:06","endtime":"2018-11-12
             * 15:00:00","startmanid":1,"startman":"杜坤","gongshiType":7,"manNumber":null,"projectId":346,"piCi":1,
             * "faPiao":"2018-11-12 19:53:19"}
             * confirmPeople : null
             * proprietorAduitVo : null
             * voteVo : null
             * files : null
             * beianPeoples : null
             * beianInfo : null
             */

            private int phaseState;
            private String phaseName;
            private int isChengli;
            private GongshiBean gongshi;
            private List<ConfirmPeopleBean> confirmPeople;//业委会人员
            private ProprietorAduitVoBean proprietorAduitVo;
            private VoteVoBean voteVo;//投票方式
            private List<FilesBean> files;//附件集合
            private Object beianPeoples;//备案人员列表集合 YwhBeianPeople类：备案人员
            private BeianInfoBean beianInfo;//YwhBeian类 备案基本信息

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

            public GongshiBean getGongshi() {
                return gongshi;
            }

            public void setGongshi(GongshiBean gongshi) {
                this.gongshi = gongshi;
            }

            public List<ConfirmPeopleBean> getConfirmPeople() {
                return confirmPeople;
            }

            public void setConfirmPeople(List<ConfirmPeopleBean> confirmPeople) {
                this.confirmPeople = confirmPeople;
            }

            public ProprietorAduitVoBean getProprietorAduitVo() {
                return proprietorAduitVo;
            }

            public void setProprietorAduitVo(ProprietorAduitVoBean proprietorAduitVo) {
                this.proprietorAduitVo = proprietorAduitVo;
            }

            public VoteVoBean getVoteVo() {
                return voteVo;
            }

            public void setVoteVo(VoteVoBean voteVo) {
                this.voteVo = voteVo;
            }

            public List<FilesBean> getFiles() {
                return files;
            }

            public void setFiles(List<FilesBean> files) {
                this.files = files;
            }

            public Object getBeianPeoples() {
                return beianPeoples;
            }

            public void setBeianPeoples(Object beianPeoples) {
                this.beianPeoples = beianPeoples;
            }

            public BeianInfoBean getBeianInfo() {
                return beianInfo;
            }

            public void setBeianInfo(BeianInfoBean beianInfo) {
                this.beianInfo = beianInfo;
            }

            public static class ProprietorAduitVoBean {

                /**
                 * aduitname : 筹备工作开始,请及时领取票权
                 * aduitstate : 1
                 * aduitStateContext : 待审核
                 * aduitTime : 2018-11-12 15:00:00
                 * aduitOpinion : null
                 */

                private String aduitname;
                private int aduitstate;
                private String aduitStateContext;
                private String aduitTime;
                private String aduitOpinion;

                public String getAduitname() {
                    return aduitname;
                }

                public void setAduitname(String aduitname) {
                    this.aduitname = aduitname;
                }

                public int getAduitstate() {
                    return aduitstate;
                }

                public void setAduitstate(int aduitstate) {
                    this.aduitstate = aduitstate;
                }

                public String getAduitStateContext() {
                    return aduitStateContext;
                }

                public void setAduitStateContext(String aduitStateContext) {
                    this.aduitStateContext = aduitStateContext;
                }

                public String getAduitTime() {
                    return aduitTime;
                }

                public void setAduitTime(String aduitTime) {
                    this.aduitTime = aduitTime;
                }

                public String getAduitOpinion() {
                    return aduitOpinion;
                }

                public void setAduitOpinion(String aduitOpinion) {
                    this.aduitOpinion = aduitOpinion;
                }
            }

            public static class ConfirmPeopleBean implements Parcelable {

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
                 * addtime : 2018-09-05 09:13:10
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
                private int type;//类型1:筹备组人员 2:业委会候选人 3:业委会人员4:业委会主任
                private int projectid;
                private String workUnit;
                private Object otherWorks;
                private String workPosition;
                private int pici;

                protected ConfirmPeopleBean(Parcel in) {
                    id = in.readInt();
                    cfname = in.readString();
                    idcard = in.readString();
                    phone = in.readString();
                    expect = in.readString();
                    building = in.readString();
                    unit = in.readString();
                    roomNumber = in.readString();
                    driscipt = in.readString();
                    isdelel = in.readInt();
                    addtime = in.readString();
                    type = in.readInt();
                    projectid = in.readInt();
                    workUnit = in.readString();
                    workPosition = in.readString();
                    pici = in.readInt();
                }

                public static final Creator<ConfirmPeopleBean> CREATOR = new Creator<ConfirmPeopleBean>() {
                    @Override
                    public ConfirmPeopleBean createFromParcel(Parcel in) {
                        return new ConfirmPeopleBean(in);
                    }

                    @Override
                    public ConfirmPeopleBean[] newArray(int size) {
                        return new ConfirmPeopleBean[size];
                    }
                };

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(id);
                    dest.writeString(cfname);
                    dest.writeString(idcard);
                    dest.writeString(phone);
                    dest.writeString(expect);
                    dest.writeString(building);
                    dest.writeString(unit);
                    dest.writeString(roomNumber);
                    dest.writeString(driscipt);
                    dest.writeInt(isdelel);
                    dest.writeString(addtime);
                    dest.writeInt(type);
                    dest.writeInt(projectid);
                    dest.writeString(workUnit);
                    dest.writeString(workPosition);
                    dest.writeInt(pici);
                }
            }

            public static class GongshiBean  implements Parcelable {
                /**
                 * id : 2
                 * title : 发起成立业委会申请
                 * content : 发起成立业委会申请
                 * fileurl : http://p9zwbgynz.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720181112195210.gif
                 * starttime : 2018-11-08 15:23:06
                 * endtime : 2018-11-12 15:00:00
                 * startmanid : 1
                 * startman : 杜坤
                 * gongshiType : 7
                 * manNumber : null
                 * projectId : 346
                 * piCi : 1
                 * faPiao : 2018-11-12 19:53:19
                 */

                private int id;
                private String title;
                private String content;
                private String fileurl;
                private String starttime;
                private String endtime;//候选人阶段结束时间
                private int startmanid;
                private String startman;
                private int gongshiType;//公示类型 1:筹备组成员公示 2:关于业主大会筹备文件公示的通知 3:候选人名单公示 4:业主大会结果公示 5成立业主委员会的通知,
                // 6启动候选人推荐7.已正式发起业委会成立阶段/已启动推荐组成员推荐程序 8.业主大会线上投票(app无用)
                private Object manNumber;
                private int projectId;
                private int piCi;
                private String faPiao;

                protected GongshiBean(Parcel in) {
                    id = in.readInt();
                    title = in.readString();
                    content = in.readString();
                    fileurl = in.readString();
                    starttime = in.readString();
                    endtime = in.readString();
                    startmanid = in.readInt();
                    startman = in.readString();
                    gongshiType = in.readInt();
                    projectId = in.readInt();
                    piCi = in.readInt();
                    faPiao = in.readString();
                }

                public static final Creator<GongshiBean> CREATOR = new Creator<GongshiBean>() {
                    @Override
                    public GongshiBean createFromParcel(Parcel in) {
                        return new GongshiBean(in);
                    }

                    @Override
                    public GongshiBean[] newArray(int size) {
                        return new GongshiBean[size];
                    }
                };

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(id);
                    dest.writeString(title);
                    dest.writeString(content);
                    dest.writeString(fileurl);
                    dest.writeString(starttime);
                    dest.writeString(endtime);
                    dest.writeInt(startmanid);
                    dest.writeString(startman);
                    dest.writeInt(gongshiType);
                    dest.writeInt(projectId);
                    dest.writeInt(piCi);
                    dest.writeString(faPiao);
                }
            }

            public static class VoteVoBean {

                private int voteType;/*1:线上 2 线下*/

                private String content;

                private String startTime;

                private String endTime;

                public int getVoteType() {
                    return voteType;
                }

                public void setVoteType(int voteType) {
                    this.voteType = voteType;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
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

                public int getIsVote() {
                    return isVote;
                }

                public void setIsVote(int isVote) {
                    this.isVote = isVote;
                }

                private int isVote;/*-1否 1是*/


            }

            public static class BeianInfoBean {

                /**
                 * id : 6
                 * isSuccess : 1
                 * beianTime : 2018-11-12 19:41:47.0
                 * projectId : 346
                 * operatorId : 2
                 * operatorName : 大大
                 * bieanstate : 1
                 * beianmingc : 抠脚大汉
                 * pici : 1
                 */

                private int id;
                private int isSuccess;
                private String beianTime;
                private int projectId;
                private int operatorId;
                private String operatorName;
                private int bieanstate;
                private String beianmingc;
                private int pici;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getIsSuccess() {
                    return isSuccess;
                }

                public void setIsSuccess(int isSuccess) {
                    this.isSuccess = isSuccess;
                }

                public String getBeianTime() {
                    return beianTime;
                }

                public void setBeianTime(String beianTime) {
                    this.beianTime = beianTime;
                }

                public int getProjectId() {
                    return projectId;
                }

                public void setProjectId(int projectId) {
                    this.projectId = projectId;
                }

                public int getOperatorId() {
                    return operatorId;
                }

                public void setOperatorId(int operatorId) {
                    this.operatorId = operatorId;
                }

                public String getOperatorName() {
                    return operatorName;
                }

                public void setOperatorName(String operatorName) {
                    this.operatorName = operatorName;
                }

                public int getBieanstate() {
                    return bieanstate;
                }

                public void setBieanstate(int bieanstate) {
                    this.bieanstate = bieanstate;
                }

                public String getBeianmingc() {
                    return beianmingc;
                }

                public void setBeianmingc(String beianmingc) {
                    this.beianmingc = beianmingc;
                }

                public int getPici() {
                    return pici;
                }

                public void setPici(int pici) {
                    this.pici = pici;
                }
            }

            public static class FilesBean  implements Parcelable {
                /**
                 * id : 24
                 * filename : 1000.jpg
                 * uploadtime : 2018-11-13 16:15:34.0
                 * filestate : 1
                 * url : gj/upload/file/1542096826013
                 * ywhType : 6
                 * projectId : 346
                 * pici : 1
                 */

                private int id;
                private String filename;
                private String uploadtime;
                private int filestate;
                private String url;
                private int ywhType;
                private int projectId;
                private int pici;

                protected FilesBean(Parcel in) {
                    id = in.readInt();
                    filename = in.readString();
                    uploadtime = in.readString();
                    filestate = in.readInt();
                    url = in.readString();
                    ywhType = in.readInt();
                    projectId = in.readInt();
                    pici = in.readInt();
                }

                public static final Creator<FilesBean> CREATOR = new Creator<FilesBean>() {
                    @Override
                    public FilesBean createFromParcel(Parcel in) {
                        return new FilesBean(in);
                    }

                    @Override
                    public FilesBean[] newArray(int size) {
                        return new FilesBean[size];
                    }
                };

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getFilename() {
                    return filename;
                }

                public void setFilename(String filename) {
                    this.filename = filename;
                }

                public String getUploadtime() {
                    return uploadtime;
                }

                public void setUploadtime(String uploadtime) {
                    this.uploadtime = uploadtime;
                }

                public int getFilestate() {
                    return filestate;
                }

                public void setFilestate(int filestate) {
                    this.filestate = filestate;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getYwhType() {
                    return ywhType;
                }

                public void setYwhType(int ywhType) {
                    this.ywhType = ywhType;
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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(id);
                    dest.writeString(filename);
                    dest.writeString(uploadtime);
                    dest.writeInt(filestate);
                    dest.writeString(url);
                    dest.writeInt(ywhType);
                    dest.writeInt(projectId);
                    dest.writeInt(pici);
                }
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
            private int phaseState;//-1:未开始,1:进行中,2已完成
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

    @Override
    public String toString() {
        return "YwhCurrentflow{" +
                "success=" + success +
                ", code=" + code +
                ", error=" + error +
                ", data=" + data +
                ", rows=" + rows +
                ", total=" + total +
                ", token=" + token +
                '}';
    }
}
