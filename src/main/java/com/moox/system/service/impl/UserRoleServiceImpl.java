package com.moox.system.service.impl;

import com.moox.system.dao.UserRoleDao;
import com.moox.system.entity.Role;
import com.moox.system.entity.User;
import com.moox.system.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService{
    
    @Resource
    private UserRoleDao userRoleDao;

    @Override
    public List<User> findUserIdByOrganId(String orgIds, String rolekey) {
        return userRoleDao.findUserIdByOrganId(orgIds, rolekey);
    }

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return userRoleDao.findRolesByUserId(userId);
    }

    
    
	@Override
	public List<User> findUsersByRoleKey(String roleKey) {
		return userRoleDao.findUsersByRoleKey(roleKey);
	}

}
