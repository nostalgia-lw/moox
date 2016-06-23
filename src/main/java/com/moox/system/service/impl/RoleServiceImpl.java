package com.moox.system.service.impl;

import com.moox.system.dao.RoleDao;
import com.moox.system.entity.Resources;
import com.moox.system.entity.Role;
import com.moox.system.entity.User;
import com.moox.system.service.RoleService;
import com.moox.system.support.tag.pagination.Pagination;
import com.moox.system.util.CommonUtils;
import com.moox.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;

	public void add(Role role,Long[] userIds) {
		List<User> users = new ArrayList<User>();
			for (Long u : userIds) {
				User user = new User();
				user.setId(u);
				users.add(user);
			}
		role.setUsers(users);
		User loger = CommonUtils.getUserSession();
		role.setCreateTime(new Date());
		role.setCreaterId(loger.getId());
		role.setCreaterName(loger.getName());
		roleDao.save(role);
	}

	public Role getById(Long id) {
		return roleDao.getById(id);
	}

	@Override
	public void update(Role role,Long[] userIds) {
		Role r = roleDao.getById(role.getId());
		List<User> users = new ArrayList<User>();
		for (Long u : userIds) {
			User user = new User();
			user.setId(u);
			users.add(user);
		}
		r.setUsers(users);
		r.setKey(role.getKey());
		r.setName(role.getName());
		r.setDescription(role.getDescription());
		User loger = CommonUtils.getUserSession();
		r.setUpdaterId(loger.getId());
		r.setUpdaterName(loger.getName());
		r.setUpdateTime(new Date());
		roleDao.update(r);
	}

	@Override
	public Pagination<Role> queryPageRoles(Long curIndex, String name, String key) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer("from Role r where 1=1");
		if (!StringUtils.isNullOrEmpty(name)) {
			sb.append(" and r.name like :name");
			params.put("name", "%" + name + "%");
		}
		if (!StringUtils.isNullOrEmpty(key)) {
			sb.append(" and r.key like :key");
			params.put("key", "%" + key + "%");
		}

		Pagination<Role> page = new Pagination<Role>();
		page.setCurIndex(curIndex);
		page = roleDao.findPagination(sb.toString(), page.getCurIndex(), page.getPageSize(), params);
		return page;
	}

	@Override
	public Boolean deleteById(Long id) {
		Boolean result = false;
		try {
			Role role = roleDao.getById(id);
			roleDao.delete(role);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	@Override
	public Boolean delete(Role role) {
		try {
			roleDao.delete(role);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public List<Role> queryAll() {
		StringBuffer sb = new StringBuffer("from Role r where 1=1");
		return roleDao.findList(sb.toString());
	}

	@Override
	public List<Role> queryRolesByIds(Long[] ids) {
		List<Role> roles = roleDao.queryRolesById(ids);
		return roles;
	}

	@Override
	public Role getRoleByKey(String key) {
		Role role = roleDao.getRoleByKey(key);
		return role;
	}

	@Override
	public Role getRoleByName(String name) {
		Role role = roleDao.getRoleByName(name);
		return role;
	}

	@Override
	public void addRoleRes(Long roleId, Long[] resId) {
		// 角色关联多个资源id,先删除后保存
		Role role = roleDao.getById(roleId);
		List<Resources> resources = new ArrayList<Resources>();
		for (Long r : resId) {
			Resources res = new Resources();
			res.setId(r);
			resources.add(res);
		}
		role.setResources(resources);
		roleDao.update(role);
	}

}
