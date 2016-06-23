package com.moox.system.controller;


import com.moox.system.entity.Log;
import com.moox.system.service.LogService;
import com.moox.system.support.tag.pagination.Pagination;
import com.moox.system.util.CommonKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/config")
public class LogController {
	
	@Autowired
	private LogService logService;
	
	@RequestMapping("/add")
	@ResponseBody
    public String add(Model model) {
      
        return "ok";
    }
	@RequestMapping("/list")
	public String list(Model model,String name) {
		Pagination<Log> pageLogs = logService.queryPageLogs(1L, name);
		model.addAttribute("page",pageLogs);
		return CommonKey.WEB_PATH+"/system/log/list";
	}
}
