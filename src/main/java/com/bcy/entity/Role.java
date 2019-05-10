package com.bcy.entity;

public class Role {

	private String  roleName;
	private Integer roleId;
	private Integer UserId;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(String roleName, Integer roleId, Integer userId) {
		super();
		this.roleName = roleName;
		this.roleId = roleId;
		UserId = userId;
	}
	@Override
	public String toString() {
		return "Role [roleName=" + roleName + ", roleId=" + roleId + ", UserId=" + UserId + "]";
	}
}
