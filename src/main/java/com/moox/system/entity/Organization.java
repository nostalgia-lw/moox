package com.moox.system.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 组织架构数据库实体对象
 * @author tanghom <tanghom@qq.com> 2015-11-18
 *
 */
@Entity
@Table(name = "s_organization")
public class Organization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4310566413928103902L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;// ID
	/**
	 * 名称
	 */
	@Column(name = "name", length = 50)
	private String name;
	/**
	 * 上级ID
	 */
	@Column(name = "pid")
	private Long pid=0L;

	/**
	 * 是否隐藏
	 */
	@Column(name = "is_hide")
	private Boolean isHide;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Boolean getIsHide() {
		return isHide;
	}

	public void setIsHide(Boolean isHide) {
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
