package com.yxld.yxchuangxin.entity;


/**
 * @ClassName: MemberEntity 
 * @Description: 会员实体 
 * @author wwx
 * @date 2016年3月18日 上午10:08:34 
 */
public class MemberEntity{
	/**	表ID	**/
	private Integer id;
	/****** 用户Id ***/
	private Integer memberId;
	/****** 用户名 ***/
	private String memberName;
	/****** 密码 ***/
	private String password;
	
	public MemberEntity(Integer id, Integer memberId, String memberName,
                        String password) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.memberName = memberName;
		this.password = password;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
	public String toString() {
		return "MemberEntity [id=" + id + ", memberId=" + memberId
				+ ", memberName=" + memberName + ", password=" + password + "]";
	}
	
}
