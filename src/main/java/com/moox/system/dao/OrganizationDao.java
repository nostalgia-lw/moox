package com.moox.system.dao;

import com.moox.system.entity.Organization;
import com.moox.system.support.dao.BaseDao;

import java.util.List;

/**
 * 部门数据访问对象
 * 
 * @author tanghom <tanghom@qq.com> 2015-11-18
 * 
 */
public interface OrganizationDao extends BaseDao<Organization> {
    /**
     * 根据父级节点Id查询改父级节点Id下的节点id(包含父级)
     *
     * @param parentsId
     *            父级Id
     * @return 子节点Objec[]
     * @author liulei
     */
    List GetParentsIdQueryChildren(Long parentsId);

    /**
     * 根据父级节点Id查询改父级节点Id下的节点id(不包含父级)
     *
     * @param parentsId
     *            父级Id
     * @return 子节点Objec[]
     * @author liulei
     */
    List GetParentsIdQueryChildrenParentsExsit(Long parentsId);

    /**
     * 根据子节点部门查询当前节点部门的顶级部门
     *
     * @param childId
     *            子节点Id
     * @return 顶级部门ID
     * @author liulei
     */
    public Integer GetChildrenQueryParentsId(Long childId);

    /**
     * 跟进子节点部门查询子部门所在的事业部
     * @param childId 子接单iD
     * @return 事业部id
     */
    public Integer GetChildrenQueryBusinessDepartment(Long childId);

    /**
     * 保存部门
     *
     * @param organization
     *            部门实例
     * @author liulei
     */
    public void add(Organization organization);

    /**
     * 查询部门树
     *
     * @return tree
     * @author liulei
     */
    public List<Organization> findTreeList();


    /**
     * 根据部门Id查询部门信息
     *
     * @param Id
     *            部门ID
     * @return 部门实体
     * @author liulei
     */
    public Organization findById(Long Id);
    /**
     * 删除一个菜单信息
     * @param Id 菜单I
     * @return 是否成功
     */
    public void delete(Organization organization);

    /**
     * 查询部门
     *
     * @return list
     */
    public List<Organization> findAll();

    public List findBySql(String sql);

}
