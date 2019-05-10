package com.bcy.entity;

public class Permission {

	private Integer permissionId;
	private String perCode;
	private Integer userId;
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	public String getPerCode() {
		return perCode;
	}
	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Permission(Integer permissionId, String perCode, Integer userId) {
		super();
		this.permissionId = permissionId;
		this.perCode = perCode;
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", perCode=" + perCode + ", userId=" + userId + "]";
	}
}
