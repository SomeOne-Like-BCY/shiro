package com.bcy.util.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcy.entity.Permission;
import com.bcy.entity.Role;
import com.bcy.entity.User;
import com.bcy.service.UserService;

public class MyShiroRealm extends AuthorizingRealm{

	@Autowired
    private UserService userService;

    /*
    *
    * 认证
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /*获取输入的用户名及密码*/
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        /*调用service层方法返回用户信息*/
        User user = userService.getUserByUserName(username);
        /*如果用户信息为Null,则抛出用户不存在异常*/
        if(user == null){
            throw new UnknownAccountException("用户名为空-----------------");
        }
        /*如果用户状态为1,则抛出账户锁定异常*/
        //if(user.getStatus().equals(ConstantValue.USER_STATUS_LOCKED)){
        if(user.getStatus().equals(1)){
            throw new LockedAccountException("账号被锁定-----------------------");
        }
        /*比较密码是否一致,如果不一致,则抛出密码错误异常*/
        //final String md5Password = MD5.getMD5(password, user.getSalt());
        final String md5Password = new SimpleHash("MD5", "123456", "bcy521",1).toString();
        if(!md5Password.equals(user.getPassword())){
            throw new IncorrectCredentialsException("密码错误---------------------");
        }
        /*如果一切顺利,则返回该信息*/
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user,
                md5Password,
                //ByteSource.Util.bytes(user.getSalt()),
               /*user.getSalt(),*/
                this.getName());
        return info;
    }

    /*
    *
    * 授权
    *
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //从principals获取主身份信息
        //将getPrimaryPrincipal方法返回值转为真实身份类型
        //在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型
        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(user != null){
            //根据身份信息获取角色信息并且设置到PrincipalCollection中
            List<Role> roleList = userService.getRoleByUserId(user.getId());
            Set<String> roles = new HashSet<String>();
            if(roleList != null){
                for(Role role : roleList){
                    if(role.getRoleName() != null && !role.getRoleName().equals("")){
                        roles.add(role.getRoleName());
                    }
                }
                info.setRoles(roles);
            }
            //根据身份信息获取权限信息并且设置到PrincipalCollection中
            List<Permission> permissionList = userService.getPermissionByUserId(user.getId());
            if(permissionList != null){
                Set<String> permissions = new HashSet<String>();
                for(Permission tbPermission : permissionList){
                    if(tbPermission.getPerCode() != null && !tbPermission.getPerCode().trim().equals("")){
                        permissions.add(tbPermission.getPerCode());
                    }
                }
                info.setStringPermissions(permissions);
            }
            else{
                throw new UnauthorizedException();
            }
        }
        return info;
    }

}
