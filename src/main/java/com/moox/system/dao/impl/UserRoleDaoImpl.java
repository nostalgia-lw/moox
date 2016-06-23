package com.moox.system.dao.impl;

import com.moox.system.dao.UserRoleDao;
import com.moox.system.entity.Role;
import com.moox.system.entity.User;
import com.moox.system.entity.UserRole;
import com.moox.system.support.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole> implements UserRoleDao {

    @SuppressWarnings("rawtypes")
    @Override
    public List<User> findUserIdByOrganId(String orgIds, String rolekey) {
        String sql="select u.id from s_user_role r,s_role s,s_user u where r.role_id=s.id and r.user_id=u.id and s.role_key='"+rolekey+"' and u.org_id in ("+orgIds+")";
        List list=(List) getSession().createSQLQuery(sql).list();
        List<User> user=new ArrayList<User>();
        if(list!=null){
           for (int i = 0; i < list.size(); i++) {
               User u=new User();
               u.setId(Long.parseLong(list.get(i).toString()));
               user.add(u);
           }
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> findRolesByUserId(Long userId) {
    	String hql="select s from Role s,UserRole r where s.id=r.role.id and r.user.id="+userId+"";
    	return ((List<Role>) getSession().createQuery(hql).list());
    }

    @SuppressWarnings("unchecked")
	@Override
	public List<User> findUsersByRoleKey(String roleKey) {
		String hql="select u from User u,UserRole r where u.id=r.user.id and r.role.key='"+roleKey+"'";
		return ((List<User>) getSession().createQuery(hql).list());
	} 

}
