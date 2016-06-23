package com.moox.system.service.impl;

import com.moox.system.dao.LoginLogDao;
import com.moox.system.entity.LoginLog;
import com.moox.system.entity.User;
import com.moox.system.service.LoginLogService;
import com.moox.system.support.tag.pagination.Pagination;
import com.moox.system.util.CommonUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginLogServiceImpl implements LoginLogService {
	@Autowired
	private LoginLogDao loginLogDao;

	public void add() {
		User loginer = CommonUtils.getUserSession();
		LoginLog log = new LoginLog();
		log.setLoginer(loginer);
		log.setLoginTime(new Date());
		String ip = null;
		try {
			ip = SecurityUtils.getSubject().getSession().getHost();
		} catch (Exception e) {
			ip = "无法获取登录用户Ip";
		}
		log.setIp(ip);
		loginLogDao.save(log);
	}


	@Override
	public Pagination<LoginLog> queryPageLogs(Long uid,Long curIndex) {
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuffer sb = new StringBuffer("from LoginLog where 1=1");
		if(null!=uid){
			sb.append(" and loginer.id=:uid");
			params.put("uid", uid);
		}
		Pagination<LoginLog> page = new Pagination<LoginLog>();
		page.setCurIndex(curIndex);
		page = loginLogDao.findPagination(sb.toString(), page.getCurIndex(), page.getPageSize(), params);
		return page;
	}

}
