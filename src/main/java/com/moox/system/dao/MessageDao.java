package com.moox.system.dao;

import com.moox.system.entity.Message;
import com.moox.system.support.dao.BaseDao;

import java.util.Map;

/**
 * 站内信数据访问对象
 * @author tanghom <tanghom@qq.com> 2015-12-25
 *
 */
public interface MessageDao extends BaseDao<Message> {
	
	/**
	 * 查询总数
	 * @param countString
	 * @param params
	 * @return
	 */
	public Integer count(String countString, Map<String, Object> params);
}
