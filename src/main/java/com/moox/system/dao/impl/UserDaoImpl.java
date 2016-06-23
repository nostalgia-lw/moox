package com.moox.system.dao.impl;

import com.moox.system.dao.UserDao;
import com.moox.system.entity.User;
import com.moox.system.support.dao.impl.BaseDaoImpl;
import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public User getUserByloginName(String loginName) {
        StringBuffer hql = new StringBuffer("from User u where u.loginName = ?");
        User user = super.get(hql.toString(), loginName);
        return user;
    }

    @Override
    public List<User> findList() {
        String hql = "from User u where u.locked = " + User.NORMAL;
        return super.findList(hql);
    }

    @Override
    public List<User> findUserByRoleKey(String key) {
        String sql = " SELECT " +
                " ur.user_id as id, u.`name` as `name` " +
                " FROM " +
                " ( " +
                " SELECT " +
                "  id " +
                " FROM " +
                "  s_role r " +
                " WHERE " +
                "  r.role_key = :key " +
                " ) r " +
                " inner join " +
                " s_user_role ur " +
                " ON " +
                " r.id = ur.role_id " +
                " inner JOIN " +
                " s_user as u " +
                " ON " +
                " ur.user_id = u.id";

        Query query = getSession().createSQLQuery(sql)
                .addScalar("id", StandardBasicTypes.LONG)
                .addScalar("name", StandardBasicTypes.STRING)
                .setResultTransformer(new AliasToBeanResultTransformer(User.class));

        query.setParameter("key", key);

        return query.list();
    }
}
