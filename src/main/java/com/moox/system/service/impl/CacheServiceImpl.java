package com.moox.system.service.impl;

import com.moox.system.service.CacheService;
import com.moox.system.service.OrganizationService;
import com.moox.system.util.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CacheServiceImpl implements CacheService {

	@Resource
	private OrganizationService organizationService;
	@Override
	public void updateCache() {
		//部门用户树形缓存
		EhcacheUtils.initCacheManager();
		EhcacheUtils.initCache("cache");
		String orgUserJson  = organizationService.findOrgUserTree();
		EhcacheUtils.put(CommonKey.CACHE_ORG_USER_JSON, orgUserJson);
		String orgJson  = organizationService.findTreeList();
		EhcacheUtils.put(CommonKey.CACHE_ORG_JSON, orgJson);
	}
}
