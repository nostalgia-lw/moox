package com.moox.system.dao;

import com.moox.system.entity.User;
import com.moox.system.support.dao.BaseDao;

import java.util.List;

/**
 * 用户数据访问对象
 *
 * @author tanghom <tanghom@qq.com> 2015-11-18
 */
public interface UserDao extends BaseDao<User> {

    /**
     * 根据登录名查询用户
     *
     * @param loginName
     * @return
     */
    User getUserByloginName(String loginName);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findList();

    /**
     * 通过角色的key查找用户
     * */
    List<User> findUserByRoleKey(String key);
}
