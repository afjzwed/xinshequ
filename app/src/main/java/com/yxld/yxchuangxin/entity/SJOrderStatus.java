package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 订单状态Entity
 * @author BOOM
 *
 */
@SuppressWarnings("serial")
public class SJOrderStatus extends BaseEntity implements Serializable {

    /**
     * 状态码
     */
	private String success;
	private int total;

	private List<SJOrderStatus> data;

	/*private Integer orderStatusId;
	private String orderStatusOrderNumber;//订单编号（序列号）
	private Integer orderStatusStatus;//订单状态（1-待付款；2-待发货；3-待收货；4-待评价）
	private String orderStatusChangeTime;//状态改变时间
	private Integer orderStatusOperatorId;//操作员编号
	private String orderStatusOperatorName;//操作人姓名
	private Integer orderStatusOperatorPhone;//操作员电话*/

	private int orderStatusId;
	private String orderStatusOrderNumber;//订单编号（序列号）
	private int orderStatusStatus;//订单状态（1-待付款；2-待发货；3-待收货；4-待评价）
	private String orderStatusChangeTime;//状态改变时间
	private int orderStatusOperatorId;//操作员编号
	private String orderStatusOperatorName;//操作人姓名
	private String orderStatusOperatorPhone;//操作员电话


	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<SJOrderStatus> getData() {
		return data;
	}

	public void setData(List<SJOrderStatus> data) {
		this.data = data;
	}

	public int getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public String getOrderStatusOrderNumber() {
		return orderStatusOrderNumber;
	}

	public void setOrderStatusOrderNumber(String orderStatusOrderNumber) {
		this.orderStatusOrderNumber = orderStatusOrderNumber;
	}

	public int getOrderStatusStatus() {
		return orderStatusStatus;
	}

	public void setOrderStatusStatus(int orderStatusStatus) {
		this.orderStatusStatus = orderStatusStatus;
	}

	public String getOrderStatusChangeTime() {
		return orderStatusChangeTime;
	}

	public void setOrderStatusChangeTime(String orderStatusChangeTime) {
		this.orderStatusChangeTime = orderStatusChangeTime;
	}

	public int getOrderStatusOperatorId() {
		return orderStatusOperatorId;
	}

	public void setOrderStatusOperatorId(int orderStatusOperatorId) {
		this.orderStatusOperatorId = orderStatusOperatorId;
	}

	public String getOrderStatusOperatorName() {
		return orderStatusOperatorName;
	}

	public void setOrderStatusOperatorName(String orderStatusOperatorName) {
		this.orderStatusOperatorName = orderStatusOperatorName;
	}

	public String getOrderStatusOperatorPhone() {
		return orderStatusOperatorPhone;
	}

	public void setOrderStatusOperatorPhone(String orderStatusOperatorPhone) {
		this.orderStatusOperatorPhone = orderStatusOperatorPhone;
	}

	@Override
	public String toString() {
		return "SJOrderStatus{" +
				"success='" + success + '\'' +
				", total=" + total +
				", data=" + data +
				", orderStatusId=" + orderStatusId +
				", orderStatusOrderNumber='" + orderStatusOrderNumber + '\'' +
				", orderStatusStatus=" + orderStatusStatus +
				", orderStatusChangeTime='" + orderStatusChangeTime + '\'' +
				", orderStatusOperatorId=" + orderStatusOperatorId +
				", orderStatusOperatorName='" + orderStatusOperatorName + '\'' +
				", orderStatusOperatorPhone=" + orderStatusOperatorPhone +
				'}';
	}
}
