package com.moox.system.service;

import com.moox.system.entity.Log;
import com.moox.system.support.tag.pagination.Pagination;

/**
 * 操作日志逻辑层
 * 
 * @author tanghom <tanghom@qq.com> 2015-11-18
 * 
 */
public interface LogService {
	/**
	 * 添加日志
	 * 
	 * @param log
	 *            Log
	 */
	public void add(String account, String methods, String description);
	
	/**
	 * 保存日志
	 * @param account 帐号
	 * @param methods 操作
	 * @param tableName 操作数据库表
	 * @param tableId 操作数据Id
	 * @param description 操作结果
	 */
	public void save(String account, String methods, String tableName, Long tableId, String description);

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	public Log getLogById(Long id);

	/**
	 * 更新日志
	 * 
	 * @return
	 */
	public void update(Log log);

	/**
	 * 查询所有日志
	 * 
	 * @return
	 */
	public Pagination<Log> queryPageLogs(Long curIndex, String name);
}
