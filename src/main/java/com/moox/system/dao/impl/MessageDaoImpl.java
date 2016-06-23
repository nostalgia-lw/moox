package com.moox.system.dao.impl;

import com.moox.system.dao.MessageDao;
import com.moox.system.entity.Message;
import com.moox.system.support.dao.impl.BaseDaoImpl;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 站内信数据访问对象实现类
 * @author tanghom <tanghom@qq.com> 2015-12-25
 *
 */
@Repository
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao {

	@Override
	public Integer count(String countString, Map<String, Object> params) {
		Query qry = getSession().createQuery(countString.toString());
		qry.setProperties(params);
		Integer count = ((Long) qry.iterate().next()).intValue();
		return count;
	}

}