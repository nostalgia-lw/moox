package com.moox.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统操作日志实体
 * @author tanghom <tanghom@qq.com> 2015-11-18
 *
 */
@Entity
@Table(name = "s_log")
public class Log implements Serializable {

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
	 * 帐号
	 */
	@Column(name = "account", length = 50)
	private String account;
	/**
	 * IP
	 */
	@Column(name = "ip", length = 50)
	private String ip;

	/**
	 * 时间
	 */
	@Column(name = "oper_time", columnDefinition = "datetime")
	private Date operTime = new Date();
	/**
	 * 执行时间
	 */
	@Column(name = "action_time")
	private Long actionTime;
	/**
	 * 操作
	 */
	@Column(name = "methods", length = 255)
	private String methods;
	/**
	 * 操作表名
	 */
	@Column(name = "table_name")
	private String tableName;
	/**
	 * 操作表的ID
	 */
	@Column(name  = "table_Id")
	private Long tableId;
	/**
	 * 操作结果
	 */
	@Column(name = "description", length = 255)
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public String getMethods() {
		return methods;
	}
	public void setMethods(String methods) {
		this.methods = methods;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getActionTime() {
		return actionTime;
	}
	public void setActionTime(Long actionTime) {
		this.actionTime = actionTime;
	}
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public Long getTableId() {
        return tableId;
    }
    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }
	
}
