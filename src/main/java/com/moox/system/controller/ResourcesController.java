package com.moox.system.controller;

import com.moox.system.entity.Resources;
import com.moox.system.entity.Role;
import com.moox.system.service.ResourcesService;
import com.moox.system.service.RoleService;
import com.moox.system.support.log.SystemLog;
import com.moox.system.support.tag.pagination.Pagination;
import com.moox.system.util.CommonKey;
import com.moox.system.util.CommonUtils;
import com.moox.system.util.JsonUtil;
import com.moox.system.util.tree.TreeObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 菜单资源控制层
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/resources")
public class ResourcesController {

	@Autowired
	private ResourcesService resourcesService;
	@Autowired
	private RoleService roleService;
//	@Autowired
//	private UserRealm userRealm;

	/**
	 * 查询菜单列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Long pid, Long pageNo, Model model) {
		pid = pid == null ? 0 : pid;
		String pname = "一级菜单";
		if (pid != 0) {
			Resources parent = resourcesService.getById(pid);
			pname = parent.getName();
		}
		model.addAttribute("pid", pid);
		model.addAttribute("pname", pname);
		Pagination<Resources> resources = resourcesService.queryPageResources(pageNo, "", pid);
		model.addAttribute("page", resources);
		List<TreeObject> parentTree = resourcesService.findToSelectTree();
		model.addAttribute("parentTree", parentTree);
		return CommonKey.WEB_PATH + "/system/resources/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Long pid, Model model) {
		List<TreeObject> parentTree = resourcesService.findToSelectTree();
		model.addAttribute("parentTree", parentTree);
		model.addAttribute("action", "add.shtml");
		model.addAttribute("pid", pid);
		return CommonKey.WEB_PATH + "/system/resources/edit";
	}

	/**
	 * 菜单新增
	 * 
	 * @param resources
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@SystemLog(methods = "菜单管理-新增菜单")
	// 凡需要处理业务逻辑的.都需要记录操作日志
	public String add(Resources resources) {
		Resources checkResourcesKey = resourcesService.getResourcesByKey(resources.getKey());
		if (null != checkResourcesKey) {
			return CommonUtils.msgCallback(false, "新增菜单失败,菜单Key重复", "", null);
		}
		Resources checkResourcesName = resourcesService.getResourcesByName(resources.getName());
		if (null != checkResourcesName) {
			return CommonUtils.msgCallback(false, "新增菜单失败,菜单名称重复", "", null);
		}
		try {
			resourcesService.save(resources);
			return CommonUtils.msgCallback(true, "新增菜单成功", "", null);
		} catch (Exception e) {
			return CommonUtils.msgCallback(false, "新增菜单失败", "", null);
		}
	}

	/**
	 * 编辑菜单页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, Model model) {
		Resources res = resourcesService.getById(id);
		model.addAttribute("action", "edit.shtml");
		model.addAttribute("res", res);
		model.addAttribute("pid", res.getPid());
		List<TreeObject> parentTree = resourcesService.findToSelectTree();
		model.addAttribute("parentTree", parentTree);
		return CommonKey.WEB_PATH + "/system/resources/edit";
	}

	/**
	 * 保存菜单
	 * 
	 * @param resource
	 *            菜单对象
	 * @return 结果
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(Resources resource) {
		try {
			Resources checkResourcesKey = resourcesService.getResourcesByKey(resource.getKey());
			if (null != checkResourcesKey && !checkResourcesKey.getId().equals(resource.getId())) {
				return CommonUtils.msgCallback(false, "新增菜单失败,菜单Key重复", "", null);
			}
			Resources checkResourcesName = resourcesService.getResourcesByName(resource.getName());
			if (null != checkResourcesName && !checkResourcesName.getId().equals(resource.getId()) ) {
				return CommonUtils.msgCallback(false, "新增菜单失败,菜单名称重复", "", null);
			}
			Resources res = resourcesService.getById(resource.getId());
			res.setName(resource.getName());
			res.setIcon(resource.getIcon());
			res.setDescription(resource.getDescription());
			res.setKey(resource.getKey());
			res.setPid(resource.getPid());
			res.setUrl(resource.getUrl());
			res.setIsHide(resource.getIsHide());
			res.setSort(resource.getSort());
			resourcesService.update(res);
			return CommonUtils.msgCallback(true, "修改菜单成功", "role/list.shtml", null);
		} catch (Exception e) {
			return CommonUtils.msgCallback(false, "修改菜单失败", "", null);
		}
	}

	/**
	 * 权限分配页面
	 * 
	 * @author tanghom
	 * @param model
	 * @return
	 */
	@RequestMapping("/permissions")
	public String permissions(Long roleId, Model model) {
		List<TreeObject> ns = resourcesService.queryResourcesTree();
		model.addAttribute("permissions", ns);
		Role role = roleService.getById(roleId);
		model.addAttribute("role", role);
		return CommonKey.WEB_PATH + "/system/resources/permissions";
	}

	/**
	 * 权限分配页面
	 * 
	 * @author tanghom
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/get_role_resources")
	@ResponseBody
	public String getRoleResources(Long roleId) {
		List<Resources> roleResources = resourcesService.queryResourcesByRoleId(roleId);
		return JsonUtil.toJSONStringWithoutForeignKey(roleResources);
	}

	/**
	 * @desc 角色分配权限
	 * @author tanghom 11:00:55
	 * @since 2015年11月19日
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/add_role_res")
	public String addRoleRes(Long roleId, Long resId[]) throws Exception {
		try {
			roleService.addRoleRes(roleId, resId);
			Subject user = SecurityUtils.getSubject();
			return CommonUtils.msgCallback(true, "修改成功", "", "");
		} catch (Exception e) {
			return CommonUtils.msgCallback(false, "修改失败", "", "");
		}
	}

	/**
	 * 修改排序
	 * 
	 * @param id
	 *            资源ID
	 * @param sort
	 *            排序数值
	 * @return
	 */
	@RequestMapping("/change_sort")
	@ResponseBody
	public String changeSort(Long id, Integer sort) {
		try {
			Resources resources = resourcesService.getById(id);
			resources.setSort(sort);
			resourcesService.update(resources);
			return CommonUtils.msgCallback(true, "修改成功", "", "");
		} catch (Exception e) {
			return CommonUtils.msgCallback(false, "修改失败", "", "");
		}
	}

	/**
	 * 修改禁用
	 * 
	 * @param id
	 *            资源ID
	 * @param isHide
	 *            是否禁用
	 * @return
	 */
	@RequestMapping("/change_hide")
	@ResponseBody
	public String changeHide(Long id, Integer isHide) {
		try {
			if (0 == isHide) {
				isHide = 1;
			} else {
				isHide = 0;
			}
			Resources resources = resourcesService.getById(id);
			resources.setIsHide(isHide);
			resourcesService.update(resources);
			return CommonUtils.msgCallback(true, "修改成功", "", "");
		} catch (Exception e) {
			return CommonUtils.msgCallback(false, "修改失败", "", "");
		}
	}

	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public String delete(Long id) {
		try {
			Resources resources = resourcesService.getById(id);
			resourcesService.delete(resources);
			return CommonUtils.msgCallback(true, "删除成功", "", "");
		} catch (Exception e) {
			return CommonUtils.msgCallback(false, "删除失败", "", "");

		}
	}
}
