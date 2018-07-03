package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.utils.PagedResult;

public interface UsersService {

	public PagedResult queryUsers(Users user, Integer page, Integer pageSize);
	/**
	 * @Description: 判断用户名是否存在
	 */
	public boolean queryUsernameIsExist(String username);

	/**
	 * @Description: 保存用户(用户注册)
	 */
	public void saveUser(Users user);

	/**
	 * @Description: 用户登录，根据用户名和密码查询用户
	 */
	public Users queryUserForLogin(String username, String password);


}
