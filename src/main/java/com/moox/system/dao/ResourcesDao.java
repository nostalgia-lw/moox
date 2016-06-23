package com.moox.system.dao;

import com.moox.system.entity.Resources;
import com.moox.system.support.dao.BaseDao;

import java.util.List;

/**
 * 资源数据访问对象
 * 
 * @author tanghom <tanghom@qq.com> 2015-11-18
 * 
 */
public interface ResourcesDao extends BaseDao<Resources> {

	/**
	 * 查询可用的资源路径
	 * 
	 * @return
	 */
	public List<Resources> queryAllResources();
	
	/**
	 * 根据ID查询资源
	 * @param ids
	 * @return
	 */
	public List<Resources> queryResourcesById(Long ids[]);
	
	/**
	 * 根据Key查询
	 * @param key
	 * @return
	 */
	public Resources getResourcesByKey(String key);
	/**
	 * 根据name查询
	 * @param name
	 * @return
	 */
	
	public Resources getResourcesByName(String value);

}
