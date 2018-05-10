package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 欣周边 投诉建议Entity
 * @author BOOM
 *
 */
@SuppressWarnings("serial")
public class SJComplain extends BaseEntity implements Serializable{

	private List<SJComplain> data;

	/**
	 * 状态码
	 */
	public int success;


	private int total;

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	private Integer complainId;
	private String complainCompanyName;//	所属公司名称
	private Integer complainCompanyId;//	公司ID
	private String complainProjectName;//	所属项目名称
	private Integer complainProjectId;//	项目Id
	private String complainBusinessNumber;//	商家编号
	private String complainBusinessName;//	商家名称
	private String complainOrderNumber;//	订单编号
	private Integer complainPersonId;//		投诉人编号
	private String complainPersonName;//	投诉人姓名
	private String complainPersonPhone;//	投诉人电话
	private String complainContent;//		投诉内容
	private String complainTime;//			投诉时间
	private String complainDisposeTime;//	处理时间
	private Integer complainDisposeId;//	处理人编号
	private String complainDisposePerson;//	处理人姓名
	private Integer complainStatus;//		投诉状态（1-待处理；2-处理中；3-已处理）
	private String complainReplyContent;//	投诉回复内容（处理结果）
	private Integer complainType;   // 1-商品；2-服务

	public Integer getComplainId() {
		return complainId;
	}

	public void setComplainId(Integer complainId) {
		this.complainId = complainId;
	}

	public String getComplainCompanyName() {
		return complainCompanyName;
	}

	public void setComplainCompanyName(String complainCompanyName) {
		this.complainCompanyName = complainCompanyName;
	}

	public Integer getComplainCompanyId() {
		return complainCompanyId;
	}

	public void setComplainCompanyId(Integer complainCompanyId) {
		this.complainCompanyId = complainCompanyId;
	}

	public String getComplainProjectName() {
		return complainProjectName;
	}

	public void setComplainProjectName(String complainProjectName) {
		this.complainProjectName = complainProjectName;
	}

	public Integer getComplainProjectId() {
		return complainProjectId;
	}

	public void setComplainProjectId(Integer complainProjectId) {
		this.complainProjectId = complainProjectId;
	}

	public String getComplainBusinessNumber() {
		return complainBusinessNumber;
	}

	public void setComplainBusinessNumber(String complainBusinessNumber) {
		this.complainBusinessNumber = complainBusinessNumber;
	}

	public String getComplainBusinessName() {
		return complainBusinessName;
	}

	public void setComplainBusinessName(String complainBusinessName) {
		this.complainBusinessName = complainBusinessName;
	}

	public String getComplainOrderNumber() {
		return complainOrderNumber;
	}

	public void setComplainOrderNumber(String complainOrderNumber) {
		this.complainOrderNumber = complainOrderNumber;
	}

	public Integer getComplainPersonId() {
		return complainPersonId;
	}

	public void setComplainPersonId(Integer complainPersonId) {
		this.complainPersonId = complainPersonId;
	}

	public String getComplainPersonName() {
		return complainPersonName;
	}

	public void setComplainPersonName(String complainPersonName) {
		this.complainPersonName = complainPersonName;
	}

	public String getComplainPersonPhone() {
		return complainPersonPhone;
	}

	public void setComplainPersonPhone(String complainPersonPhone) {
		this.complainPersonPhone = complainPersonPhone;
	}

	public String getComplainContent() {
		return complainContent;
	}

	public void setComplainContent(String complainContent) {
		this.complainContent = complainContent;
	}

	public String getComplainTime() {
		return complainTime;
	}

	public void setComplainTime(String complainTime) {
		this.complainTime = complainTime;
	}

	public String getComplainDisposeTime() {
		return complainDisposeTime;
	}

	public void setComplainDisposeTime(String complainDisposeTime) {
		this.complainDisposeTime = complainDisposeTime;
	}

	public Integer getComplainDisposeId() {
		return complainDisposeId;
	}

	public void setComplainDisposeId(Integer complainDisposeId) {
		this.complainDisposeId = complainDisposeId;
	}

	public String getComplainDisposePerson() {
		return complainDisposePerson;
	}

	public void setComplainDisposePerson(String complainDisposePerson) {
		this.complainDisposePerson = complainDisposePerson;
	}

	public Integer getComplainStatus() {
		return complainStatus;
	}

	public void setComplainStatus(Integer complainStatus) {
		this.complainStatus = complainStatus;
	}

	public String getComplainReplyContent() {
		return complainReplyContent;
	}

	public void setComplainReplyContent(String complainReplyContent) {
		this.complainReplyContent = complainReplyContent;
	}

	public Integer getComplainType() {
		return complainType;
	}

	public void setComplainType(Integer complainType) {
		this.complainType = complainType;
	}

	public List<SJComplain> getData() {
		return data;
	}

	public void setData(List<SJComplain> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "SJComplain{" +
				"data=" + data +
				", success=" + success +
				", msg='" + msg + '\'' +
				", total=" + total +
				", complainId=" + complainId +
				", complainCompanyName='" + complainCompanyName + '\'' +
				", complainCompanyId=" + complainCompanyId +
				", complainProjectName='" + complainProjectName + '\'' +
				", complainProjectId=" + complainProjectId +
				", complainBusinessNumber='" + complainBusinessNumber + '\'' +
				", complainBusinessName='" + complainBusinessName + '\'' +
				", complainOrderNumber='" + complainOrderNumber + '\'' +
				", complainPersonId=" + complainPersonId +
				", complainPersonName='" + complainPersonName + '\'' +
				", complainPersonPhone='" + complainPersonPhone + '\'' +
				", complainContent='" + complainContent + '\'' +
				", complainTime='" + complainTime + '\'' +
				", complainDisposeTime='" + complainDisposeTime + '\'' +
				", complainDisposeId=" + complainDisposeId +
				", complainDisposePerson='" + complainDisposePerson + '\'' +
				", complainStatus=" + complainStatus +
				", complainReplyContent='" + complainReplyContent + '\'' +
				", complainType=" + complainType +
				"} " + super.toString();
	}
}
