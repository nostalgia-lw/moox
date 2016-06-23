package com.moox.system.dao;

import com.moox.system.entity.Role;
import com.moox.system.support.dao.BaseDao;

import java.util.List;

/**
 * 角色数据访问对象
 * 
 * @author tanghom <tanghom@qq.com> 2015-11-18
 *
 */
public interface RoleDao extends BaseDao<Role> {

	/**
	 * 根据Key查询角色
	 * @param key
	 * @return
	 */
	public Role getRoleByKey(String key);
	/**
	 * 根据name查询角色
	 * @param name
	 * @return
	 */
	
	public Role getRoleByName(String name);
	/**
	 * 根据角色ID集合查询角色集合
	 * @param ids
	 * @return
	 */
	public List<Role> queryRolesById(Long[] ids);
}
