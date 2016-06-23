package com.moox.system.dao.impl;

import com.moox.system.dao.OrganizationDao;
import com.moox.system.entity.Organization;
import com.moox.system.support.dao.impl.BaseDaoImpl;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizationDaoImpl extends BaseDaoImpl<Organization> implements OrganizationDao  {

    @Override
    public List GetParentsIdQueryChildren(Long parentsId) {
        String sql="SELECT * FROM s_organization o WHERE FIND_IN_SET(o.id,queryChildOrgan("+parentsId+"))";
        return getSession().createSQLQuery(sql).list();
    }

    @Override
    public List GetParentsIdQueryChildrenParentsExsit(Long parentsId) {
        String sql="SELECT * FROM s_organization o WHERE FIND_IN_SET(o.id,queryChildOrgan("+parentsId+")) and o.id <> "+parentsId;
        Query query = getSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public Integer GetChildrenQueryParentsId(Long childId) {
        String sql="select a.id from s_organization a , f_GetChildrenQueryParentsId('"+childId+"') b where a.id = b.id ";
        return  Integer.parseInt(getSession().createSQLQuery(sql).uniqueResult().toString());
    }

    @Override
    public void add(Organization organization) {
        getSession().save(organization);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Organization> findTreeList() {
        String hql=" from Organization o where o.isHide > 0  ORDER BY o.sort asc";
        return getSession().createQuery(hql).list();
    }

    @Override
    public Organization findById(Long Id) {
        return (Organization) getSession().get(Organization.class, Id);
    }

    @Override
    public void delete(Organization organization) {
        getSession().delete(organization);;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Organization> findAll() {
        String hql=" from Organization o order by o.sort asc";
        return getSession().createQuery(hql).list();
    }

    @Override
    public Integer GetChildrenQueryBusinessDepartment(Long childId) {

        String sql="SELECT o.id FROM s_organization o WHERE FIND_IN_SET(o.id,queryParentOrgan("+childId+")) AND o.pid in(SELECT id FROM s_organization WHERE pid=0)";
        Object i=getSession().createSQLQuery(sql).uniqueResult();
        if(i==null){
            return 0;
        }
        return Integer.parseInt(getSession().createSQLQuery(sql).uniqueResult().toString());
    }

    @Override
    public List findBySql(String sql) {
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

}
