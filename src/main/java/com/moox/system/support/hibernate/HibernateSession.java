package com.moox.system.support.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;

/**
 * 业务抽象类
 * 
 * @author tanghom <tanghom@qq.com> 2015-11-18
 * 
 */
public abstract class HibernateSession {
	/**
	 * Hibernate会话工厂实例
	 */
	private SessionFactory sessionFactory;

	/**
	 * 设置Hibernate会话工厂实例
	 * 
	 * @param sessionFactory
	 *            Hibernate会话工厂实例
	 */
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 获取Hibernate会话
	 * 
	 * @return Hibernate会话
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
