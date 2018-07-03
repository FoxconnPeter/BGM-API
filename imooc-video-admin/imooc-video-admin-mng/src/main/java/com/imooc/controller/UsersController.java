package com.imooc.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.imooc.bean.AdminUser;
import com.imooc.pojo.Users;
import com.imooc.service.UsersService;
import com.imooc.utils.IMoocJSONResult;
import com.imooc.utils.PagedResult;

@Controller
@RequestMapping("users")
@Api(description = "用户登录")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/showList")
	public String showList() {
		return "users/usersList";
	}
	
	@PostMapping("/list")
	@ResponseBody
	public PagedResult list(Users user , Integer page) {
		
		PagedResult result = usersService.queryUsers(user, page == null ? 1 : page, 10);
		return result;
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}

//
//	@ApiOperation(value = "管理员用户登录",notes = "管理员用户登录")
//	@PostMapping("/login")
//	@ResponseBody
//	public IMoocJSONResult userLogin(
//
//			@ApiParam(value = "用户名",required = true) String username, @ApiParam(value = "密码",required = true)String password,
//			HttpServletRequest request, HttpServletResponse response) {
//
//
//		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
//			return IMoocJSONResult.errorMap("用户名和密码不能为空");
//		}
//		Users usersResult = usersService.queryUserForLogin(username,password);
//		if (usersResult!=null) {
//			String token = UUID.randomUUID().toString();
//			AdminUser user = new AdminUser(username, password, token);
//			request.getSession().setAttribute("sessionUser", user);
//			return IMoocJSONResult.ok();
//		}
//		return IMoocJSONResult.errorMsg("用户名或密码错误，请重试...");
//	}


	@PostMapping("login")
	@ResponseBody
	public IMoocJSONResult userLogin(String username, String password,
									 HttpServletRequest request, HttpServletResponse response) {

		// TODO 模拟登陆
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return IMoocJSONResult.errorMap("用户名和密码不能为空");
		} else if (username.equals("lee") && password.equals("lee")) {

			String token = UUID.randomUUID().toString();
			AdminUser user = new AdminUser(username, password, token);
			request.getSession().setAttribute("sessionUser", user);
			return IMoocJSONResult.ok();
		}

		return IMoocJSONResult.errorMsg("登陆失败，请重试...");
	}

	@ApiOperation(value = "管理员用户登出",notes = "管理员用户登出")
	@PostMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("sessionUser");
		return "login";
	}


	@ApiOperation(value = "管理员用户登录",notes = "管理员用户登录")
	@PostMapping("/register")
	@ResponseBody
	public IMoocJSONResult userRegister(@RequestBody Users user) throws Exception {
		//判断用户密码不能为空
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
			return IMoocJSONResult.errorMsg("账户密码不能为空");
		}
		//判断用户名是否存在
		boolean usernameIsExist = usersService.queryUsernameIsExist(user.getUsername());

		//保存用户
		if (!usernameIsExist) {
			user.setNickname(user.getUsername());
			user.setPassword(user.getPassword());
			user.setFansCounts(0);
			user.setReceiveLikeCounts(0);
			user.setFollowCounts(0);
			usersService.saveUser(user);
		} else {
			return IMoocJSONResult.errorMsg("用户名已存在，请更换");
		}
		user.setPassword("");
		return IMoocJSONResult.ok(user);
	}
	
	
	
	
}
