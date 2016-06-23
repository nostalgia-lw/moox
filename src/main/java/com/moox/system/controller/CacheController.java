package com.moox.system.controller;

import com.moox.system.service.CacheService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/cache")
public class CacheController {

	@Resource
	private CacheService cacheService;
	/**
	 * 更新缓存
	 * @return
     */
	@RequestMapping(value = "/update",produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String update() {
		//更新缓存
		cacheService.updateCache();
		return "更新成功";
	}
}
