package com.bcy.service;

import java.util.List;

import com.bcy.entity.Permission;
import com.bcy.entity.Role;
import com.bcy.entity.User;

public interface UserService {

	User getUserByUserName(String username);

	List<Role> getRoleByUserId(Integer id);

	List<Permission> getPermissionByUserId(Integer id);

	int addUser(User user);

	void deleteUser(Integer id);
	

}
