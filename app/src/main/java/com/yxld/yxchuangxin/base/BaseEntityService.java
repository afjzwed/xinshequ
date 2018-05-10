package com.yxld.yxchuangxin.base;

/**
 * @ClassName: BaseEntity 
 * @Description: 公共实体�?
 * @author wwx
 * @date 2015�?1�?6�?下午1:38:22 
 *
 */
public class BaseEntityService extends BaseEntity{
	/**
	 * 状态码
	 */
	public int success;
	/**
	 * 详细信息
	 */

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
}
