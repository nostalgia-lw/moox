package com.moox.system.controller;

import com.moox.system.service.LoginLogService;
import com.moox.system.service.UserService;
import com.moox.system.util.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帐号登录登出控制器
 * 
 * @author tanghom <tanghom@qq.com> 2015-11-18
 * 
 */
@Controller
public class LoginController {
	@Resource
	private LoginLogService logService;
	@Autowired
	private UserService userService;
	/**
	 * 登录入口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login.shtml", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		request.removeAttribute("error");
		return "/login";
	}

	/**
	 * 登录
	 * 
	 * @param loginname
	 *            登录名
	 * @param password
	 *            密码
	 * @param request
	 *            HttpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/login.shtml", method = RequestMethod.POST)
	public String login(String loginname, String password, HttpServletRequest request) {
		try {
			if(StringUtils.isNullOrEmpty(loginname)||StringUtils.isNullOrEmpty(password)){
				request.setAttribute("error", "请输入用户名和密码！");
				return "/login";
			}
			Subject user = SecurityUtils.getSubject();
			String pwd = Md5Util.encrypt(password);
			UsernamePasswordToken token = new UsernamePasswordToken(loginname, pwd);
			try {
				user.login(token);
			} catch (LockedAccountException lae) {
				token.clear();
				request.setAttribute("error", "用户已经被锁定不能登录，请与管理员联系！");
				return "/login";
			} catch (IncorrectCredentialsException e) {
				token.clear();
				request.setAttribute("error", "账号：" + loginname + " 登录失败,密码错误!");
				return "/login";
			} catch (AuthenticationException e) {
				token.clear();
				request.setAttribute("error", "用户或密码不正确！");
				return "/login";
			}
			request.removeAttribute("error");
		} catch (Exception e) {
			request.setAttribute("error", "登录异常，请联系管理员！");
			return "/login";
		}
		logService.add();
		return "redirect:main.shtml";
	}
}
