package com.moox.system.service;

import com.moox.system.entity.Role;
import com.moox.system.entity.User;

import java.util.List;

public interface UserRoleService {
    
    /**
     * 查询user
     * @param orgIds 部门ids
     * @param rolekey 角色KEY
     * @return ""
     */
    List<User> findUserIdByOrganId(String orgIds, String rolekey);
    
    /**
     * 根据用户Id查询角色集合
     * @param userId 用户Id
     * @return 角色集合
     */
    List<Role> findRolesByUserId(Long userId);
    
    /**
     * 根据角色Id查询用户集合
     * @param roleId 角色ID
     * @return 用户集合
     */
	public List<User> findUsersByRoleKey(String roleKey);

}
