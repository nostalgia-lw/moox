package com.moox.system.dao;

import com.moox.system.entity.Role;
import com.moox.system.entity.User;
import com.moox.system.entity.UserRole;
import com.moox.system.support.dao.BaseDao;

import java.util.List;

public interface UserRoleDao extends BaseDao<UserRole> {
    /**
     * 查询根据部门和角色查询人员
     * @param orgIds
     * @param rolekey
     * @return
     */
    List<User> findUserIdByOrganId(String orgIds, String rolekey);
    
    /**
     * 根据用户ID查询角色集合
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(Long userId);
    /**
     * 根据角色ID查询用户集合
     * @param roleId
     * @return
     */
    List<User> findUsersByRoleKey(String roleKey);
}
