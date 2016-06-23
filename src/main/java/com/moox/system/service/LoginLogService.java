package com.moox.system.service;

import com.moox.system.entity.LoginLog;
import com.moox.system.support.tag.pagination.Pagination;

/**
 * 操作日志逻辑层
 * 
 * @author tanghom <tanghom@qq.com> 2015-11-18
 * 
 */
public interface LoginLogService {
	/**
	 * 添加日志
	 * 
	 * @param log
	 *            Log
	 */
	public void add();

	/**
	 * 查询所有日志
	 * 
	 * @return
	 */
	public Pagination<LoginLog> queryPageLogs(Long uid, Long curIndex);
}
