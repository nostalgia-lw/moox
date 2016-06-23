package com.moox.system.controller;

import com.moox.system.entity.Role;
import com.moox.system.entity.User;
import com.moox.system.service.OrganizationService;
import com.moox.system.service.RoleService;
import com.moox.system.service.UserService;
import com.moox.system.support.log.SystemLog;
import com.moox.system.support.tag.pagination.Pagination;
import com.moox.system.util.CommonKey;
import com.moox.system.util.CommonUtils;
import com.moox.system.util.JsonUtil;
import com.moox.system.util.Md5Util;
import com.moox.system.util.tree.TreeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * 用户管理控制层
 * 
 * @author tanghom
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private OrganizationService organizationService;

	/**
	 * 查询用户分页
	 * 
	 * @param model
	 * @param name
	 * @param loginName
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model, String name, String loginName, Long orgId, Long pageNo) {
		Pagination<User> pageUsers = userService.queryPageUsers(pageNo, name, loginName, orgId);
		model.addAttribute("page", pageUsers);
		List<TreeObject> orgTree = organizationService.findToSelectTree();
		model.addAttribute("organizations", orgTree);
		return CommonKey.WEB_PATH + "/system/user/list";
	}

	/**
	 * 新增用户页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		List<Role> roles = roleService.queryAll();
		model.addAttribute("roles", roles);
		List<TreeObject> orgTree = organizationService.findToSelectTree();
		model.addAttribute("organizations", orgTree);
		model.addAttribute("action", "add.shtml");
		return CommonKey.WEB_PATH + "/system/user/edit";
	}

	/**
	 * 保存用户
	 *
	 *            用户对象
	 * @return 结果
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@SystemLog(methods = "新增用户")
	public String add(User user, Long roleId[]) {
		if (null == user.getName()) {
			return CommonUtils.msgCallback(false, "新增用户失败，请输入员工名称", "", null);
		}
		if (null == user.getLoginPwd()) {
			return CommonUtils.msgCallback(false, "新增用户失败，请输入密码", "", null);
		}
		if (null == roleId) {
			return CommonUtils.msgCallback(false, "新增用户失败，请选择角色", "", null);
		}
		User checkUser = userService.getUserByloginName(user.getLoginName());
		if (null != checkUser) {
			return CommonUtils.msgCallback(false, "新增用户失败，登录名重复", "", null);
		}
		
		try {
			userService.save(user, roleId);
			return CommonUtils.msgCallback(true, "新增用户成功", "", null);
		} catch (Exception e) {
			return CommonUtils.msgCallback(false, "新增用户失败，请联系管理员重试", "", null);
		}
	}

	/**
	 * 编辑用户页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, Model model) {
		User u = userService.getUserById(id);
		model.addAttribute("user", u);
		List<Role> roles = roleService.queryAll();
		model.addAttribute("roles", roles);
		List<TreeObject> orgTree = organizationService.findToSelectTree();
		model.addAttribute("organizations", orgTree);
		model.addAttribute("action", "edit.shtml");
		return CommonKey.WEB_PATH + "/system/user/edit";
	}

	/**
	 * 保存用户
	 * 
	 * @param user
	 *            用户对象
	 * @param roleId
	 *            roleId
	 * @return 结果
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(User user, Long roleId[]) {
		User checkUser = userService.getUserByloginName(user.getLoginName());
		if (null != checkUser && !checkUser.getId().equals(user.getId())) {
			return CommonUtils.msgCallback(true, "编辑用户失败，登录名重复", "", null);
		}
		try {

			userService.update(user, roleId);
			return CommonUtils.msgCallback(true, "修改用户成功", "", null);
		} catch (Exception e) {
			return CommonUtils.msgCallback(false, "修改用户失败，请联系管理员重试", "", null);
		}
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 *            用户ID
	 * @return 结果
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Long id) {
		try {
			userService.deleteById(id);
			return CommonUtils.msgCallback(true, "删除用户成功", "", null);

		} catch (Exception e) {
			return CommonUtils.msgCallback(false, "删除用户失败", "", null);
		}
	}

	/**
	 * 用户下拉查询
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user_select",produces = "application/json;charset=UTF-8" )
	public String userSelect() {
		List<User> userList = userService.findList();
		String userJson = JsonUtil.toJSONStringWithoutForeignKey(userList);
		return userJson;
	}

	/**
	 * ajax查询用户详细信息
	 *
	 * @return 弹出框中显示
	 */
	@RequestMapping(value = "/ajax_user_info", method = RequestMethod.GET)
	public String ajaxUserInfo(Long id, Model model) {
		User u = userService.getUserById(id);
		model.addAttribute("user", u);
		return CommonKey.WEB_PATH + "/system/user/ajax_user_info";
	}

	/**
	 * 编辑基本资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/setprofile", method = RequestMethod.POST)
	@ResponseBody
	public String setprofile(User user) {
		try {
			User loginer = CommonUtils.getUserSession();
			User us = userService.getUserById(loginer.getId());
			us.setDescription(user.getDescription());
			us.setName(user.getName());
			us.setPhone(user.getPhone());
			us.setSex(user.getSex());
			us.setSeatNumber(user.getSeatNumber());
			us.setOrganization(user.getOrganization());
			us.setTheAgentId(user.getTheAgentId());
			userService.modify(us);
			return CommonUtils.msgCallback(true, "修改成功", "","");
		} catch (Exception e) {
			return CommonUtils.msgCallback(false, "修改失败，请重试！", "", null);
		}
	}
	
	/**
	 * 编辑基本资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user_seting", method = RequestMethod.GET)
	public String userSeting(Model model) {
		User u = CommonUtils.getUserSession();
		User user = userService.getUserById(u.getId());
		model.addAttribute("user", user);
		List<TreeObject> orgTree = organizationService.findToSelectTree();
		model.addAttribute("organizations", orgTree);
		List<User> users=userService.findList();
		model.addAttribute("users", users);
		return CommonKey.WEB_PATH + "/system/user/user_seting";
	}

	/**
	 * 编辑头像
	 * 
	 * @return
	 */
	@RequestMapping(value = "/setavator", method = RequestMethod.POST)
	@ResponseBody
	public String setavator(HttpServletRequest request) {
		try {
			User u = CommonUtils.getUserSession();
			String avatorUploadPath = "/upload/avator/";
			String getImagePath = request.getSession().getServletContext().getRealPath(avatorUploadPath);
			File image = new File(getImagePath);
			if (!image.exists()) {
				image.mkdir();
			}
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile uploadFile = multipartRequest.getFile("avatorFile");
			String fileNewName = u.getId()+".jpg";
			BufferedImage srcBufferImage = ImageIO.read(uploadFile.getInputStream());
			FileOutputStream out = new FileOutputStream(getImagePath + "/" + fileNewName);
			ImageIO.write(srcBufferImage, "jpeg", out);
			User user = userService.getUserById(u.getId());
			String avator = avatorUploadPath +  fileNewName;
			user.setAvator(avator);
			userService.modify(user);
			return CommonUtils.msgCallback(true, "上传成功", "",avator);
		} catch (Exception e) {
			return CommonUtils.msgCallback(false, "上传失败，请重试！", "", null);
		}
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/setpwd", method = RequestMethod.POST)
	@ResponseBody
	public String setpwd(String nowPwd,String newPwd,String confirmPwd,Model model) {
		try {
			
		User u = CommonUtils.getUserSession();
		User user = userService.getUserById(u.getId());
		if(!user.getLoginPwd().equals(Md5Util.encrypt(nowPwd))){
			return CommonUtils.msgCallback(false, "当前密码错误！", "", null);
		}else if(!newPwd.equals(confirmPwd)){
			return CommonUtils.msgCallback(false, "请确认新密码是否正确！", "", null);
		}else{
			user.setLoginPwd(Md5Util.encrypt(newPwd));
			userService.modify(user);
		}
		return CommonUtils.msgCallback(true, "修改成功，请重新登录！", "", null);
		} catch (Exception e) {
			return CommonUtils.msgCallback(false, "修改失败，请重试！", "", null);
		}
	}
}
