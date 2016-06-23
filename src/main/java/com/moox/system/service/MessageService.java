package com.moox.system.service;

import com.moox.system.entity.Message;
import com.moox.system.support.tag.pagination.Pagination;

/**
 * 站内信逻辑层
 * 
 * @author tanghom
 * 
 */
public interface MessageService {

	/**
	 * 保存消息
	 * @param message
	 */
	public void save(Message message);
	/**
	 * 保存消息
	 * @param message
	 */
	public void add(Message message);
	/**
	 * 更新消息
	 * @param message
	 */
	public void update(Message message);
	/**
	 * 删除信息
	 * @param message
	 */
	public void delete(Message message);
	/**
	 * 删除信息
	 * @param ids
	 * @param uid
	 */
	public void deleteAll(String ids, Long uid);
	/**
	 * 读取信息
	 * @param ids
	 * @param uid
	 */
	public void readAll(String ids, Long uid);
	
	public Message getById(Long id);
	/**
	 * 站内信分页
	 * @param keyword 关键字搜索
	 * @param toUserId 接收人
	 * @param senderId 发送人
	 * @param status 状态  0 正常 1删除
	 * @param type 类型（0系统消息，1站内信）
	 * @param curIndex 分页码
	 * @return
	 */
	public Pagination<Message> queryPage(String keyword, Long toUserId, Long senderId, Integer status, Integer type, Long curIndex);
	
	/**
	 * 查询用户未读消息
	 * @param uid 用户Id
	 * @param type 类型（0系统消息，1站内信）
	 * @return
	 */
	public Integer countMsg(Long uid, Integer type);
}
