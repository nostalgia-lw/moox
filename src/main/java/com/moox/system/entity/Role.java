package com.moox.system.entity;

import com.moox.system.support.entity.BaseEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色数据库实体对象
 * @author tanghom <tanghom@qq.com> 2015-11-18
 *
 */
@Entity
@Table(name = "s_role")
public class Role extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 524697939983445494L;

	/**
	 * 名称
	 */
	@Column(name = "name", length = 50)
	private String name;
	/**
	 * 唯一标识
	 */
	@Column(name = "role_key", length = 50)
	private String key;
	/**
	 * 描述
	 */
	@Column(name = "description", length = 200)
	private String description;
	/**
	 * 关联用户
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "s_user_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<User> users = new ArrayList<User>();// 用户集合
	
	/**
	 * 关联资源
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "s_role_resources", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "resources_id") })
	@OrderBy("sort")  
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
	private List<Resources> resources = new ArrayList<Resources>();// 资源集合

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Resources> getResources() {
		return resources;
	}

	public void setResources(List<Resources> resources) {
		this.resources = resources;
	}

}
