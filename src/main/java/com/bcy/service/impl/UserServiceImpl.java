package com.bcy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import com.bcy.entity.Permission;
import com.bcy.entity.Role;
import com.bcy.entity.User;
import com.bcy.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	/*@Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;*/

    // 登录验证:根据用户名查询用户信息
    @Override
    public User getUserByUserName(String username) {
        /*User tbUser = userDao.getUserByUserName(username);
        return tbUser;*/
    	User user = new User();
    	user.setId(1);
    	user.setUsername("admin");
    	user.setStatus("0");
    	user.setSalt("bcy521");
    	String simpleHash = new SimpleHash("MD5", "123456", "bcy521",1).toString();
    	user.setPassword(simpleHash);
    	return user;
    }

    // 授权验证第一步:根据用户id查询用户角色
    @Override
    public List<Role> getRoleByUserId(Integer id) {
        /*List<Role> tbRoleList = roleDao.getTbRoleByUserId(id);
        return tbRoleList;*/
    	List<Role> roles = new ArrayList<>();
    	Role role = new Role();
    	role.setUserId(1);
    	role.setRoleId(101);
    	role.setRoleName("admins");
    	roles.add(role);
    	return roles;
    }

    // 授权验证:根据用户id查询用户权限信息
    @Override
    public List<Permission> getPermissionByUserId(Integer userId) {
        /*List<Permission> permissionList = permissionDao.getTbPermissionByUserId(userId);
        return permissionList;*/
    	
    	List<Permission> permissions= new ArrayList<>();
    	Permission permission = new Permission();
    	permission.setUserId(1);
    	permission.setPermissionId(123);
    	permission.setPerCode("add");
    	permissions.add(permission);
    	return permissions;
    }

   /* // 查询菜单信息
    @Override
    public Map<TbPermission, List<TbPermission>> getMenuByUserId(Integer userId){
        List<TbPermission> permissionList = tbPermissionMapper.getTopTbPermissionByUserId(userId);
        Map<TbPermission, List<TbPermission>> map = new HashMap<TbPermission, List<TbPermission>>();
        for(TbPermission tbPermission : permissionList){
            if(tbPermission.getParentId() == null){
                // 当用户id是确定的时候，根据父菜单id查询子菜单id
                List<TbPermission> list = tbPermissionMapper.getTbPermissionByParentIdAndUserId(tbPermission.getId(),userId);
                if(list != null){
                    map.put(tbPermission,list);
                }
            }
        }
        return map;
    }*/

    // 注册用户
    @Override
    public int addUser(User user) {
       /* // 获取salt
        String salt=new SecureRandomNumberGenerator().nextBytes().toHex();
        user.setSalt(salt);
        // 进行加密
        String md5Pwd = MD5.getMD5(tbUser.getPassword(), salt);
        tbUser.setPassword(md5Pwd);
        // 设置状态
        //tbUser.setStatus(ConstantValue.USER_STATUS_UNLOCKED);
        user.setStatus(0+"");
        userDao.addUser(user);
        返回主键
        Integer id =user.getId();
        return id;*/
    	return 0;
    }

 /*   // 查询所有用户信息
    @Override
    public PageUtils getAllUser(Integer pageNo) {
        PageHelper.startPage(pageNo, ConstantValue.PAGE_SIZE);
        List<TbUser> userList = tbUserMapper.getAllUser();
        PageUtils pageInfo = new PageUtils();
        pageInfo.setRows(userList);
        PageInfo<TbUser> pageInfoList = new PageInfo<TbUser>(userList);
        pageInfo.setTotalPages(pageInfoList.getPages());
        pageInfo.setCurrentPage(pageNo);
        pageInfo.setPageSize(pageInfoList.getPageSize());
        return pageInfo;
    }*/

    // 删除用户信息及其关联角色信息
    @Override
    public void deleteUser(Integer id) {
        /*userDao.deleteUser(id);
        userDao.deleteUserRole(id);*/
    }
}
