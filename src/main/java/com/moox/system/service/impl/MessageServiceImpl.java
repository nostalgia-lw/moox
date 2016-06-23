package com.moox.system.service.impl;

import com.moox.system.dao.MessageDao;
import com.moox.system.entity.Message;
import com.moox.system.entity.User;
import com.moox.system.service.MessageService;
import com.moox.system.support.tag.pagination.Pagination;
import com.moox.system.util.CommonUtils;
import com.moox.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 站内信逻辑层实现
 * @author tanghom
 *
 */
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;
	@Override
	public void save(Message message) {
		User loginer = CommonUtils.getUserSession();
		message.setSender(loginer);
		message.setSendTime(new Date());
		messageDao.save(message);
	}
	@Override
	public Pagination<Message> queryPage(String keyword, Long toUserId, Long senderId , Integer status, Integer type, Long curIndex) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer("from Message a where 1=1");
		if(senderId!=null){
			sb.append(" and a.sender.id=:senderId");
			params.put("senderId", senderId);
		}
		if(toUserId!=null){
			sb.append(" and a.toUser.id=:toUserId");
			params.put("toUserId", toUserId);
		}
		if(status!=null){
			sb.append(" and a.status=:status");
			params.put("status", status);
		}
		if(type!=null){
			sb.append(" and a.type=:type");
			params.put("type", type);
		}
		if(!StringUtils.isNullOrEmpty(keyword)){
			sb.append(" and (");
			sb.append(" content like :keyword");//商机名称
			sb.append(" or toUser.name like :keyword");//商机描述内容
			sb.append(" or sender.name like :keyword");//客户编号
			sb.append(" )");
			params.put("keyword", "%"+keyword+"%");
		}
		String countString = "select count(a.id) "+sb.toString();
		Pagination<Message> pageMessage = new Pagination<Message>();
		pageMessage.setCurIndex(curIndex);
		pageMessage = messageDao.findPagination(sb.append(" order by a.sendTime desc").toString(),countString, pageMessage.getCurIndex(), pageMessage.getPageSize(), params);
		
		return pageMessage;
	}
	@Override
	public Integer countMsg(Long uid, Integer type) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer("from Message a where 1=1 and a.status = 0 and a.readTime is null");
		sb.append(" and a.toUser.id=:toUserId");
		params.put("toUserId", uid);
		if(type!=null){
			sb.append(" and a.type=:type");
			params.put("type", type);
		}
		String countString = "select count(a.id) "+sb.toString();
		Integer count =((Integer)messageDao.count(countString, params)).intValue();
		return count;
		
	}
	@Override
	public void update(Message message) {
		messageDao.update(message);
		
	}
	@Override
	public Message getById(Long id) {
		return messageDao.getById(id);
	}
    @Override
    public void add(Message message) {
       messageDao.save(message);
    }
    @Override
    public void delete(Message message) {
        messageDao.delete(message);
        
    }
	@Override
	public void deleteAll(String ids, Long uid) {
		String hql = "delete from t_message where id in("+ids+") and to_user_id ='"+uid+"'";
		messageDao.executeSqlUpdate(hql);
	}
	@Override
	public void readAll(String ids, Long uid) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String hql = "update t_message set read_time ='"+fmt.format(new Date())+"' where id in("+ids+") and to_user_id ='"+uid+"' and read_time is null";
		messageDao.executeSqlUpdate(hql);
		
	}
}
