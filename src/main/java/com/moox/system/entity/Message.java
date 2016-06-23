package com.moox.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 站内信实体
 * 
 * @author tanghom
 * 
 */
@Entity
@Table(name = "t_message")
public class Message implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 67031895431202132L;
	/**
	 * ID
	 */
	@Column(name = "id", unique = true, nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 创建时间
	 */
	@Column(name = "send_time", columnDefinition = "datetime")
	private Date sendTime;
	/**
	 * 创建人
	 */
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;
	/**
	 * 接收人
	 */
	@ManyToOne
	@JoinColumn(name = "to_user_id")
	private User toUser;
	/**
	 * 内容
	 */
	@Column(name = "content", columnDefinition = "TEXT", length = 65535)
	private String content;
	/**
	 * 查看时间
	 */
	@Column(name = "read_time", columnDefinition = "datetime")
	private Date readTime;
	/**
	 * 记录状态 0 正常 1删除
	 */
	@Column(name = "status")
	private Integer status = 0;
	/**
	 * 类别 0 系统信息 1站内短信息
	 */
	@Column(name = "type")
	private Integer type = 0;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

}
