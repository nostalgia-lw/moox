package com.moox.system.entity;

import com.moox.system.support.entity.BaseEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据库实体对象
 * @author tanghom <tanghom@qq.com> 2015-11-18
 *
 */
@Entity
@Table(name = "s_user")
public class User extends BaseEntity {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7558325120712729770L;
	/** 0、正常 */
	public static final int NORMAL = 0;
	/** 1、锁定 */
	public static final int LOCKED = 1;
	/** 2、离职 */
	public static final int TURNOVER = 2;
	
	/**
	 * 登录帐号
	 */
	@Column(name = "login_name", length = 128)
	private String loginName;
	/**
	 * 登录密码
	 */
	@Column(name = "login_pwd", length = 128)
	private String loginPwd;
	/**
	 * 姓名
	 */
	@Column(name = "name", length = 128)
	private String name;
	/**
	 * 姓名拼音
	 */
	@Column(name = "pinyin", length = 128)
	private String pinyin;
	/**
	 * 性别
	 */
	@Column(name = "sex", length = 32)
	private String sex;
	
	/**
	 * 岗位级别
	 */
	@Column(name = "position_level")
	private Long position_level=1L;

	/**
	 * 用户所属部门
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	private Organization organization;
	/**
	 * 关联角色
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "s_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@OrderBy("id")  
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
	private List<Role> roles = new ArrayList<Role>();// 角色集合
	/**
	 * 电话
	 */
	@Column(name = "phone")
	private String phone;
	/**
	 * 头像地址
	 */
	@Column(name = "avator", length = 255)
	private String avator;
	/**
	 * 个人描述
	 */
	@Column(name = "description", length = 255)
	private String description;
	/**
	 * 帐号是否被锁定:0、正常；1、锁定；2、离职；
	 * 默认：0、正常
	 */
	@Column(name = "locked")
	private Integer locked = 0;
	
	@Column(name = "seat_number", length = 32)
	private String seatNumber;

	@Column(name = "the_agent_id")
	private Long theAgentId;
	
	public String getPinyin() {
		return pinyin;
	}
	
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getPosition_level() {
		return position_level;
	}

	public void setPosition_level(Long position_level) {
		this.position_level = position_level;
	}

	public String getAvator() {
		return avator;
	}

	public void setAvator(String avator) {
		this.avator = avator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Long getTheAgentId() {
		return theAgentId;
	}

	public void setTheAgentId(Long theAgentId) {
		this.theAgentId = theAgentId;
	}
}
