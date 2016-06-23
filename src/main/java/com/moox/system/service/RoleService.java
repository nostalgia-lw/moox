package com.moox.system.service;

import com.moox.system.entity.Role;
import com.moox.system.support.tag.pagination.Pagination;

import java.util.List;

/**
 * 角色管理逻辑层
 * @author tanghom <tanghom@qq.com> 2015-11-18
 *
 */
public interface RoleService {

	/**
	 * 添加配置
	 * @param config Role
	 */
	public void add(Role role, Long[] userIds);
	/**
	 * 根据ID删除
	 * @return
	 */
	public Boolean deleteById(Long id);
	/**
	 * 删除
	 * @return
	 */
	public Boolean delete(Role role);
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Role getById(Long id);
	/**
	 * 更新角色
	 * @return
	 */
	public void update(Role role, Long[] userIds);
	/**
	 * 查询所有角色
	 * @param curIndex 当前页码
	 * @param name 名称搜索
	 * @param key key搜索
	 * @return
	 */
	public Pagination<Role> queryPageRoles(Long curIndex, String name, String key);
	
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> queryAll();
	
	/**
	 * 根据角色ID查询角色
	 * @param ids
	 * @return
	 */
	
	public List<Role> queryRolesByIds(Long ids[]);
	
	/**
	 * 根据角色KEY查询
	 * @param key
	 * @return
	 */
	public Role getRoleByKey(String key);
	
	/**
	 * 根据角色name查询
	 * @param name
	 * @return
	 */
	public Role getRoleByName(String name);
	
	
	/**
	 * 修改角色菜单
	 * @param config Role
	 */
	public void addRoleRes(Long roleId, Long resId[]);

}
