package com.moox.system.controller;

import com.moox.system.entity.LoginLog;
import com.moox.system.entity.User;
import com.moox.system.service.LoginLogService;
import com.moox.system.support.tag.pagination.Pagination;
import com.moox.system.util.CommonKey;
import com.moox.system.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login_log")
public class LoginLogController {

	@Autowired
	private LoginLogService loginLogService;

	/**
	 * 我的登录日志
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/me")
	public String me( Long pageNo,Model model) {
		User loginer = CommonUtils.getUserSession();
		Pagination<LoginLog> pageLogs = loginLogService.queryPageLogs(loginer.getId(),pageNo);
		model.addAttribute("page", pageLogs);
		return CommonKey.WEB_PATH + "/system/login_log/list";
	}
}
