package com.moox.system.service.impl;

import com.moox.system.dao.LogDao;
import com.moox.system.entity.Log;
import com.moox.system.service.LogService;
import com.moox.system.support.tag.pagination.Pagination;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogDao logDao;

	public void add(String account, String methods, String description) {
		Log log = new Log();
		log.setAccount(account);
		log.setOperTime(new Date());
		log.setDescription(description);
		log.setMethods(methods);
		String ip = null;
		try {
			ip = SecurityUtils.getSubject().getSession().getHost();
		} catch (Exception e) {
			ip = "无法获取登录用户Ip";
		}
		log.setIp(ip);
		logDao.save(log);
	}

	public Log getLogById(Long id) {
		return logDao.getById(id);
	}

	@Override
	public void update(Log log) {
		logDao.update(log);
	}

	@Override
	public Pagination<Log> queryPageLogs(Long curIndex, String name) {
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuffer sb = new StringBuffer("from Log where 1=1");
		Pagination<Log> page = new Pagination<Log>();
		page.setCurIndex(curIndex);
		page = logDao.findPagination(sb.toString(), page.getCurIndex(), page.getPageSize(), params);
		return page;
	}

	@Override
	public void save(String account, String methods,String tableName,Long tableId,String description) {
		Log log = new Log();
		log.setAccount(account);
		log.setOperTime(new Date());
		log.setMethods(methods);
		log.setDescription(description);
		log.setTableName(tableName);
		log.setTableId(tableId);
		String ip = null;
		try {
			ip = SecurityUtils.getSubject().getSession().getHost();
		} catch (Exception e) {
			ip = "无法获取登录用户Ip";
		}
		log.setIp(ip);
		logDao.save(log);
	}

}
