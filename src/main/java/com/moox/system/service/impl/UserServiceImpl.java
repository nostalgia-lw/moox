package com.moox.system.service.impl;

import com.moox.system.dao.UserDao;
import com.moox.system.entity.Role;
import com.moox.system.entity.User;
import com.moox.system.service.UserService;
import com.moox.system.support.tag.pagination.Pagination;
import com.moox.system.util.CommonUtils;
import com.moox.system.util.Md5Util;
import com.moox.system.util.Pinyin4jUtil;
import com.moox.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public void save(User user, Long roleId[]) {
		String pinyin = null;
		if (StringUtils.isNullOrEmpty(user.getPinyin())) {
			pinyin = Pinyin4jUtil.converterToFirstSpell(user.getName());
		} else {
			pinyin = user.getPinyin();
		}
		user.setPinyin(pinyin);
		String pwd = Md5Util.encrypt(user.getLoginPwd());
		List<Role> roles = new ArrayList<Role>();
		if (null != roleId) {
			for (Long r : roleId) {
				Role role = new Role();
				role.setId(r);
				roles.add(role);
			}
		}
		user.setRoles(roles);
		user.setLoginPwd(pwd);
		User loger = CommonUtils.getUserSession();
		user.setCreaterId(loger.getId());
		user.setCreaterName(loger.getName());
		user.setCreateTime(new Date());
		userDao.save(user);
	}

	public User getUserByloginName(String loginName) {
		return userDao.getUserByloginName(loginName);
	}

	public User getUserById(Long id) {
		return userDao.getById(id);
	}

	@Override
	public void update(User user, Long roleId[]) {
		User u = userDao.getById(user.getId());
		List<Role> roles = new ArrayList<Role>();
		if (null != roleId) {
			for (Long r : roleId) {
				Role role = new Role();
				role.setId(r);
				roles.add(role);
			}
			u.setRoles(roles);
		}
		String pinyin = null;
		if (!StringUtils.isNullOrEmpty(user.getPinyin())) {
			pinyin = Pinyin4jUtil.converterToFirstSpell(user.getName());
		} else {
			pinyin = u.getPinyin();
		}
		if(!StringUtils.isNullOrEmpty(user.getLoginPwd())){
			String pwd = Md5Util.encrypt(user.getLoginPwd());
			u.setLoginPwd(pwd);
		}
		u.setPinyin(pinyin);
		u.setId(user.getId());
		u.setLoginName(user.getLoginName());
		u.setName(user.getName());
		u.setSex(user.getSex());
		u.setOrganization(user.getOrganization());
		User loger = CommonUtils.getUserSession();
		u.setUpdateTime(new Date());
		u.setUpdaterId(loger.getId());
		u.setUpdaterName(loger.getName());
		userDao.update(u);
	}

	@Override
	public Pagination<User> queryPageUsers(Long curIndex, String name, String loginName,Long orgId) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer("from User u where 1=1");
		if (!StringUtils.isNullOrEmpty(name)) {
			sb.append(" and u.name like :name");
			params.put("name", "%" + name + "%");
		}
		if (!StringUtils.isNullOrEmpty(loginName)) {
			sb.append(" and u.loginName like :loginName");
			params.put("loginName", "%" + loginName + "%");
		}
		if (null != orgId) {
			sb.append(" and u.organization.id =:orgId");
			params.put("orgId", orgId);
		}
		String countString = "select count(u.id) "+sb.toString();
		Pagination<User> page = new Pagination<User>();
		page.setCurIndex(curIndex);
		page = userDao.findPagination(sb.append(" order by u.id desc").toString(),countString, page.getCurIndex(), page.getPageSize(), params);
		return page;
	}

	@Override
	public void deleteById(Long id) {
		User user = userDao.getById(id);
		userDao.delete(user);
	}

	@Override
	public List<User> findList() {
		return userDao.findList();
	}

	@Override
	public List<User> getOrganId(Long organId) {
		String hql = "from User u where u.locked=0 and u.organization.id=?";
		return userDao.findList(hql, organId);
	}

	@Override
	public List<User> getUserByRole(String roleKey) {
		String hql = "SELECT u FROM User u,Role r,UserRole ur WHERE ur.user.id = u.id AND ur.role.id = r.id AND r.key=?";
		return userDao.findList(hql, roleKey);
	}

    @Override
    public Pagination<User> findByOrgId(String valText,Long organId, Long currentId,Long curIndex) {
        StringBuffer sb = new StringBuffer("from User u where 1=1 and u.organization.id="+organId+" and u.id not in ("+currentId+")");
        if(!StringUtils.isNullOrEmpty(valText)){
            sb.append(" and (u.loginName = '"+valText+"' or u.name like '%"+valText+"%' or u.phone='"+valText+"')");
        }
        String countString = "select count(u.id) "+sb.toString();
        Pagination<User> page = new Pagination<User>();
        page.setCurIndex(curIndex);
        page = userDao.findPagination(sb.append(" order by u.id desc").toString(),countString, page.getCurIndex(), page.getPageSize());
        return page;
    }

    @Override
    public void modify(User user) {
       userDao.update(user);
    }

    @Override
    public Pagination<User> findByIdsSingleOnline( String name, String loginName, Long orgId,Long curIndex,String Ids) {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer("from User u where 1=1 and u.id in ("+Ids+")");
        if (!StringUtils.isNullOrEmpty(name)) {
            sb.append(" and u.name like :name");
            params.put("name", "%" + name + "%");
        }
        if (!StringUtils.isNullOrEmpty(loginName)) {
            sb.append(" and u.loginName like :loginName");
            params.put("loginName", "%" + loginName + "%");
        }
        if (null != orgId) {
            sb.append(" and u.organization.id =:orgId");
            params.put("orgId", orgId);
        }
        String countString = "select count(u.id) "+sb.toString();
        Pagination<User> page = new Pagination<User>();
        page.setCurIndex(curIndex);
        page = userDao.findPagination(sb.append(" order by u.id desc").toString(),countString, page.getCurIndex(), page.getPageSize(),params);
        return page;
    }

	@Override
	public List<User> findUserMoreRole(String... roleKeys) {
		String hql = "SELECT DISTINCT u FROM User u,Role r,UserRole ur WHERE ur.user.id = u.id AND ur.role.id = r.id";
		if(roleKeys.length > 0){
			String where = " AND (";
			for (String key : roleKeys) {
				where += " r.key = '"+key+"' OR";
			}
			where = where.substring(0, where.length() -2) + ")";
			hql += where;
		}
		return userDao.findList(hql);
	}
}
