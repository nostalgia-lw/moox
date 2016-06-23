package com.moox.system.service;

import com.moox.system.entity.Organization;
import com.moox.system.util.tree.TreeObject;

import java.util.List;

/**
 * 组织机构
 * @author liulei
 *
 */
public interface OrganizationService {
    /**
     * 根据父级节点Id查询改父级节点Id下的节点id(包含父级)
     * @param parentsId 父级Id
     * @return Objec[]
     * @author liulei
     */
    List GetParentsIdQueryChildren(Long parentsId);
    /**
     * 根据父级节点Id查询改父级节点Id下的节点id(不包含父级)
     * @param parentsId 父级Id
     * @return Objec[]
     * @author liulei
     */
    List<Organization> GetParentsIdQueryChildrenParentsExsit(Long parentsId);

    /**
     * 根据子节点部门查询当前节点部门的顶级部门
     * @param childId 子节点Id
     * @return 顶级部门ID
     */
    public int GetChildrenQueryParentsId(Long childId);
    /**
     * 保存部门
     *
     * @param organization
     *            部门实例
     * @return 是否成功
     * @author liulei
     */
    public boolean add(Organization organization);

    /**
     * 查询部门树
     *
     * @return tree
     * @author liulei
     */
    public String findTreeList();
    /**
     * 查询部门用户tree
     * @return
     */
    public String findOrgUserTree();

    /**
     * 修改部门信息
     * @return 是否成功
     * @param organization
     *            部门实例
     */
    public boolean modify(Organization organization);

    /**
     * 根据部门Id查询部门信息
     *
     * @param id
     *            部门ID
     * @return 部门实体
     * @author liulei
     */
    public Organization findById(Long id);
    /**
     * 删除一个菜单信息
     * @param organization 菜单I
     * @return 是否成功
     */
    public boolean delete(Organization organization);

    /**
     * 查询所有部门
     *
     * @return list
     * @author 汤宏
     */
    public List<TreeObject> findToSelectTree();
    /**
     * 根据ids查询部门（in）
     * @param ids id字符串
     * @return
     */
    public List<Organization> findByIds(String ids);
    /**
     * 查询子节点所在的事业部
     * @param childId 子节点Id
     * @return 事业部Id
     */
    public int GetChildrenQueryBusinessDepartment(Long childId);

    /**
     * 查询所有事业部
     * @return
     */
    public List<Organization> findDivision();

    /**
     * 根据部门Id查询所属子级部门信息
     * @param id
     * @return
     */
    List<Organization> findByOrganId(Long id);


}
