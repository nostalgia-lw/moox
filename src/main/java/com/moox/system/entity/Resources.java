package com.moox.system.entity;

import com.moox.system.support.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单、资源数据库实体对象
 * @author tanghom <tanghom@qq.com> 2015-11-18
 *
 */
@Entity
@Table(name = "s_resources")
public class Resources extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4310566413928103902L;

	/**
	 * 名称
	 */
	@Column(name = "name", length = 50)
	private String name;
	/**
	 * 唯一标识
	 */
	@Column(name = "res_key", length = 50)
	private String key;
	/**
	 * 地址
	 */
	@Column(name = "url", length = 200)
	private String url;
	/**
	 * 上级ID
	 */
	@Column(name = "pid")
	private Long pid;

	/**
	 * 类型（目录，菜单，按钮/操作）
	 */
	@Column(name = "type", length = 4)
	private Integer type;
	/**
	 * 图标
	 */
	@Column(name = "icon", length = 50)
	private String icon;
	/**
	 * 是否隐藏
	 */
	@Column(name = "is_hide")
	private Integer isHide;
	/**
	 * 描述
	 */
	@Column(name = "description", length = 255)
	private String description;

	/**
	 * 排序
	 */
	@Column(name = "sort", length = 4)
	private Integer sort;

	/**
	 * 关联角色
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "s_role_resources", joinColumns = { @JoinColumn(name = "resources_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private List<Role> roles = new ArrayList<Role>();// 角色集合

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getIsHide() {
		return isHide;
	}

	public void setIsHide(Integer isHide) {
		this.isHide = isHide;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
