package com.moox.system.controller;

import com.moox.system.entity.Role;
import com.moox.system.entity.User;
import com.moox.system.service.RoleService;
import com.moox.system.service.UserService;
import com.moox.system.support.log.SystemLog;
import com.moox.system.support.tag.pagination.Pagination;
import com.moox.system.util.CommonKey;
import com.moox.system.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角色控制层
 * 
 * @author tanghom <tanghom@qq.com> 2015-11-18
 * 
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	/**
	 * 角色管理
	 * 
	 * @param model
	 * @param name
	 *            角色名称
	 * @param key
	 *            角色key
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model, String name, String key, Long pageNo) {
		Pagination<Role> pageRoles = roleService.queryPageRoles(pageNo, name, key);
		model.addAttribute("page", pageRoles);
		return CommonKey.WEB_PATH + "/system/role/list";
	}

	/**
	 * 新增角色页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		List<User> users = userService.findList();
		model.addAttribute("users", users);
		model.addAttribute("action", "add.shtml");
		return CommonKey.WEB_PATH + "/system/role/edit";
	}

	/**
	 * 保存角色
	 * 
	 * @param role
	 *            角色对象
	 * @return 结果
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@SystemLog(methods = "角色管理-新增角色")
	// 凡需要处理业务逻辑的.都需要记录操作日志
	public String add(Role role, @RequestParam(value="userIds[]",defaultValue="")Long[] userIds, HttpServletRequest request) {
		Role checkRoleKey = roleService.getRoleByKey(role.getKey());
		if (null != checkRoleKey) {
			return CommonUtils.msgCallback(false, "新增角色失败,角色Key重复", "", null);
		}
		Role checkRoleName = roleService.getRoleByName(role.getName());
		if (null != checkRoleName) {
			return CommonUtils.msgCallback(false, "新增角色失败,角色名称重复", "", null);
		}

		try {
			roleService.add(role,userIds);
			return CommonUtils.msgCallback(true, "新增角色成功", "role/list.shtml", null);
		} catch (Exception e) {
			return CommonUtils.msgCallback(false, "新增角色失败", "", null);
		}
	}

	/**
	 * 编辑角色页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, Model model) {
		Role role = roleService.getById(id);
		List<User> users = userService.findList();
		model.addAttribute("users", users);
		model.addAttribute("roleUsers", role.getUsers());
		model.addAttribute("action", "edit.shtml");
		model.addAttribute("role", role);
		return CommonKey.WEB_PATH + "/system/role/edit";
	}

	/**
	 * 保存角色
	 * 
	 * @param role
	 *            角色对象
	 * @return 结果
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(Role role, @RequestParam(value="userIds[]",defaultValue="")Long[] userIds, HttpServletRequest request) {
		try {
			Role checkRoleKey = roleService.getRoleByKey(role.getKey());
			if (null != checkRoleKey && checkRoleKey.getId() != role.getId()) {
				return CommonUtils.msgCallback(false, "修改角色失败,角色Key重复", "", null);
			}
			Role checkRoleName = roleService.getRoleByName(role.getKey());
			if (null != checkRoleName && checkRoleKey.getId() != role.getId()) {
				return CommonUtils.msgCallback(false, "修改角色失败,角色名称重复", "", null);
			}
			roleService.update(role,userIds);
			return CommonUtils.msgCallback(true, "修改角色成功", "role/list.shtml", null);
		} catch (Exception e) {
			return CommonUtils.msgCallback(false, "修改角色失败", "", null);
		}
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 *            角色ID
	 * @return 结果
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Long id) {
		Boolean result = roleService.deleteById(id);
		if (result) {
			return CommonUtils.msgCallback(true, "删除角色成功", "", null);
		} else {
			return CommonUtils.msgCallback(false, "删除角色失败", "", null);
		}
	}

	/**
	 * 授权用户页面
	 *
	 * @author tanghom
	 * @param model
	 * @return
	 */
	@RequestMapping("/permissions")
	public String permissions(Long roleId, Model model) {
		Role role = roleService.getById(roleId);
		List<User> users = userService.findList();
		model.addAttribute("users", users);
		model.addAttribute("role", role);
		model.addAttribute("roleUsers", role.getUsers());
		return CommonKey.WEB_PATH + "/system/role/permissions";
	}

}
