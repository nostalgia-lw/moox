package com.moox.system.shiro;

import com.moox.system.entity.Resources;
import com.moox.system.service.ResourcesService;
import org.apache.log4j.Logger;
import org.apache.shiro.config.Ini;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 产生责任链，确定每个url的访问权限
 * 
 * @author tanghom <tanghom@qq.com> 2015-11-18
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {
	private static final Logger LOG = Logger.getLogger(UserRealm.class);
	@Autowired
	private ResourcesService resourcesService;
	
	// 静态资源访问权限
	private String filterChainDefinitions = null;
	public Ini.Section getObject() throws Exception {
		LOG.info(">>>>>>>>>>>>>加载所有资源URL，添加到Shiro权限列表>>>>>>>>>>>>>>>>>>>>");
		Ini ini = new Ini();
		// 加载默认的url
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		// 循环Resource的url,逐个添加到section中。section就是filterChainDefinitionMap,
		// 里面的键就是链接URL,值就是存在什么条件才能访问该链接
		List<Resources> lists = resourcesService.queryAllResources();
		for (Resources resources : lists) {
			// 构成permission字符串
			String permission = "perms[" + resources.getKey() + "]";
			section.put(resources.getUrl() + "", permission);
		}
		return section;
	}

	/**
	 * 通过filterChainDefinitions对默认的url过滤定义
	 * 
	 * @param filterChainDefinitions
	 *            默认的url过滤定义
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	public Class<?> getObjectType() {
		return this.getClass();
	}

	public boolean isSingleton() {
		return false;
	}
}
