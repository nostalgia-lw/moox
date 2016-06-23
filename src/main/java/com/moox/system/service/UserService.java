package com.moox.system.service;

import com.moox.system.entity.User;
import com.moox.system.support.tag.pagination.Pagination;

import java.util.List;

public interface UserService {

	/**
	 * 保存用户
	 * @param user
	 * @param roleId
	 */
	public void save(User user, Long roleId[]);
	/**
	 * 更新保存
	 * @param user
	 * @param roleId
	 */
	public void update(User user, Long roleId[]);
	/**
	 * 根据登录ID查询用户
	 * @param loginName
	 * @return
	 */
	public User getUserByloginName(String loginName);
	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
	public User getUserById(Long id);
	/**
	 * 根据iD删除用户
	 * @param id
	 */
	public void deleteById(Long id);
	
	/**
	 * 查询所有配置
	 * @return
	 */
	public Pagination<User> queryPageUsers(Long curIndex, String name, String loginName, Long orgId);
	
	/**
	 * 查询所有正常的用户信息集合
	 * @return
	 */
	public List<User> findList();
	
	/**
	 * 根据部门Id查询用户信息集合
	 * @param organId 部门Id
	 * @return
	 */
	public List<User> getOrganId(Long organId);
	
	/**
	 * 根据角色Key查询用户信息集合
	 * @param roleKey 角色Key
	 * @return
	 */
	List<User> getUserByRole(String roleKey);
	   
    /**
     * 根据部门Id查询用户信息集合(但不包含自己)
     * @param organId 部门Id
     * @param valText 姓名  账号  电话
     * @param currentId 当前商务经理的Id
     * @return
     */
    public Pagination<User> findByOrgId(String valText, Long organId, Long currentId, Long curIndex);
    
    /**
     * 更新用户
     * @param user
     */
    void modify(User user);
    
    /**
     * 查询在线用户
     * @param curIndex
     * @return
     */
    public Pagination<User> findByIdsSingleOnline(String name, String loginName, Long orgId, Long curIndex, String Ids);
    
    /**
     * 根据多个角色查询用户信息集合
     * @param roleKeys
     * @return
     */
    public List<User> findUserMoreRole(String... roleKeys);
    
}
