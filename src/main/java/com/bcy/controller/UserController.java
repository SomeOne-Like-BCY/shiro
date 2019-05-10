package com.bcy.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcy.entity.User;
import com.bcy.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	// 登录界面
	@RequestMapping("login")
	public String login() {
		System.out.println("去到登录页面");
		return "login";
	}
	@RequestMapping("error")
	public String error() {
		System.out.println("登录失败");
		return "error";
	}

	// 登录验证
	@RequestMapping("loginCheck")
	public String loginCheck(User tbUser, String rememberMe, Map map,String username ,String password) {
		password = new SimpleHash("MD5", password, "bcy521",1).toString();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		if (rememberMe == null) {
			token.setRememberMe(false);
		} else {
			token.setRememberMe(true);
		}
		try {
			subject.login(token);
			return "forward:toIndex";
		} catch (Exception e) {
			map.put("msg", "用户名或密码错误");
			return "login";
		}
	}

	// 如果登录成功，则跳转到此处，由此处获取信息，并发送至前台页面
	@RequestMapping("toIndex")
	public String toIndex(Map map) {
		/* 获取TbUserRealm中的Subject */
		final Subject subject = SecurityUtils.getSubject();
		User tbUser = (User) subject.getPrincipal();
		if (tbUser == null) {
			return "redirect:/login/login";
		}
		/* 将用户信息放到map中 */
		map.put("tbUser", tbUser);
		/* 查询菜单信息，并将菜单信息放置到map中 */
		//Map<Permission, List<Permission>> menu = userService.getRoleByUserId(tbUser.getId());
		//map.put("menu", menu);
		return "index";
	}

	/* 注册 */
	/* 注册前用户名查重 */
	@RequestMapping("isUserExist")
	@ResponseBody
	public String isUserExist(String username) {
		User tbUser = userService.getUserByUserName(username);
		if (tbUser == null) {
			return "2";
		}
		return "1";
	}

	@RequestMapping("register")
	public String register(String username, String password) {
		/* 用户注册 */
		User tbUser = new User();
		tbUser.setUsername(username);
		tbUser.setPassword(password);
		userService.addUser(tbUser);
		/* 用户注册后,登录到首页展示页面 */
		return "forward:loginCheck";
	}

	/* 用户退出登录 */
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		/* 清除缓存 */
		session.invalidate();
		return "redirect:/login/login";
	}
	
	/* 测试权限 */
	@RequestMapping("admin1")
	public String admin1() {
		/* 清除缓存 */
		System.out.println("测试权限");
		return "bcy";
	}

	
	
	
	
}
