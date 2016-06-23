package com.moox.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 登录操作日志实体
 * @author tanghom <tanghom@qq.com> 2015-11-18
 *
 */
@Entity
@Table(name = "s_login_log")
public class LoginLog implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7558325120712729770L;
	
	 /**
     * ID
     */
    @Column(name = "id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	/**
	 * 用户
	 */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "loginer")
	private User loginer;
	/**
	 * IP
	 */
	@Column(name = "ip", length = 50)
	private String ip;

	/**
	 * login_time
	 */
	@Column(name = "login_time", columnDefinition = "datetime")
	private Date loginTime = new Date();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public User getLoginer() {
		return loginer;
	}
	public void setLoginer(User loginer) {
		this.loginer = loginer;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getOperTime() {
		return loginTime;
	}
	public void setOperTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
}
