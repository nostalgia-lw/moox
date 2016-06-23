package com.moox.system.controller;

import com.moox.system.entity.Organization;
import com.moox.system.entity.User;
import com.moox.system.service.OrganizationService;
import com.moox.system.service.UserService;
import com.moox.system.util.*;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门业务控制器
 * 
 * @author liulei
 * 
 */
@Controller
@RequestMapping(value = "/organzition",produces = {"text/json;charset=UTF-8"})
public class OrganizationController {

    @Resource
    private OrganizationService organizationService;
    
    @Resource
    private UserService userService;

    /**
     * 保存一个部门信息
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("save")
    public String save(Organization organization, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(
                CommonKey.USER_SESSION);
        Organization org = new Organization();
        org.setName(organization.getName());
        org.setIsHide(true);
        if (organization.getPid() != null) {
            org.setPid(organization.getPid());
        }else{
            org.setPid(0L);
        }
        // 系统字段
        org.setDescription(organization.getDescription());
        org.setSort(organization.getSort());

        boolean result = organizationService.add(org);
        if (result == true) {
            return CommonUtils.msgCallback(true, "新增部门成功", "",
                    JsonUtil.toJSONString(org));
        } else {
            return CommonUtils.msgCallback(false, "新增部门失败,联系管理员", "", null);
        }
       
    }
    /**
     * 更新一个部门信息
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("modify")
    public String modify(Organization organization, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(
                CommonKey.USER_SESSION);
        Organization org = organizationService.findById(organization.getId());
        org.setName(organization.getName());
        // 系统字段
        org.setDescription(organization.getDescription());
        org.setSort(organization.getSort());
        boolean result = organizationService.modify(org);
        if (result == true) {
            return CommonUtils.msgCallback(true, "修改部门成功", "",
                    JsonUtil.toJSONString(org));
        } else {
            return CommonUtils.msgCallback(false, "修改部门失败,联系管理员", "", null);
        }
        
    }
    /**
     * 删除一个部门
     * 
     * @param id
     *            部门Id
     * @return 成功失败信息
     */
    @ResponseBody
    @RequestMapping("delete")
    public String delete(Long id,HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(
                CommonKey.USER_SESSION);
        Map<String, String> map=new HashMap<String, String>();
        List list=organizationService.GetParentsIdQueryChildren(id);
                try{
                    for(int i=0;i<list.size();i++){
                        Object [] obj=(Object[]) list.get(i);
                        Organization organization=organizationService.findById(Long.parseLong(obj[0].toString()));
                        organization.setIsHide(false);
                        // 系统字段
                        organizationService.modify(organization);
                    }
                    map.put("status","success");
                }catch(Exception e){
                    map.put("status","erro");
          }
           return new Gson().toJson(map);
    }
    /**
     * 查询部门index页面
     * @return index
     */
    @RequestMapping("index")
    public String organization_index(){
        return CommonKey.WEB_PATH+"/system/organization/tree";
    }
    /**
     * 查询部门json树
     * @return json树
     */
    @ResponseBody
    @RequestMapping(value = "/tree",produces = "application/json;charset=UTF-8" )
    public String organizationTree(){
        String json = (String) EhcacheUtils.get(CommonKey.CACHE_ORG_JSON);
        if(StringUtils.isNullOrEmpty(json)){
            json = organizationService.findTreeList();
            EhcacheUtils.put(CommonKey.CACHE_ORG_JSON, json);
        }
        return json;
    }
    
    /**
     * 查询部门json树
     * @return json树
     */
    @ResponseBody
    @RequestMapping(value = "/organization_tree",produces = "application/json;charset=UTF-8" )
    public String organizationUserTree(){
        String json = (String) EhcacheUtils.get(CommonKey.CACHE_ORG_USER_JSON);
        if(StringUtils.isNullOrEmpty(json)){
            json = organizationService.findOrgUserTree();
            EhcacheUtils.put(CommonKey.CACHE_ORG_USER_JSON, json);
        }
        return json;
    }
    
    
    /**
     * 获取json对象
     * @return
     */
    @ResponseBody
    @RequestMapping("jsonEdit")
    public String jsonEdit(Long id){
        Organization object=organizationService.findById(id);
        return JsonUtil.toJSONStringWithoutManyToOne(object);
    }
    /**
	 * 用户下拉查询
	 * 
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping("/organ_select")
//	public String userSelect() {
//		List<Organization> organList = organizationService.findTreeList();
//		String organJson = JsonUtil.toJSONStringWithoutForeignKey(organList);
//		return organJson;
//	}
    
}