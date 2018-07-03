package com.imooc.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.UsersExample;
import com.imooc.pojo.UsersExample.Criteria;
import com.imooc.service.UsersService;
import com.imooc.utils.PagedResult;
import tk.mybatis.mapper.entity.Example;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersMapper userMapper;

	@Autowired
	private Sid sid;
	
	@Override
	public PagedResult queryUsers(Users user, Integer page, Integer pageSize) {

		String username = "";
		String nickname = "";
		if (user != null) {
			username = user.getUsername();
			nickname = user.getNickname();
		}
		
		PageHelper.startPage(page, pageSize);

		UsersExample userExample = new UsersExample();
		Criteria userCriteria = userExample.createCriteria();
		if (StringUtils.isNotBlank(username)) {
			userCriteria.andUsernameLike("%" + username + "%");
		}
		if (StringUtils.isNotBlank(nickname)) {
			userCriteria.andNicknameLike("%" + nickname + "%");
		}

		List<Users> userList = userMapper.selectByExample(userExample);

		PageInfo<Users> pageList = new PageInfo<Users>(userList);

		PagedResult grid = new PagedResult();
		grid.setTotal(pageList.getPages());
		grid.setRows(userList);
		grid.setPage(page);
		grid.setRecords(pageList.getTotal());

		return grid;
	}

	@Override
	public boolean queryUsernameIsExist(String username) {
		Users users = new Users();
		users.setUsername(username);
		Users result = userMapper.selectOne(users);


		return result == null ? false : true;
	}

	@Override
	public void saveUser(Users user) {
		String userId = sid.nextShort();
		user.setId(userId);
		userMapper.insert(user);
	}

	@Override
	public Users queryUserForLogin(String username, String password) {
		Example userExample = new Example(Users.class);
		tk.mybatis.mapper.entity.Example.Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("username", username);
		criteria.andEqualTo("password", password);
		Users result = userMapper.selectOneByExample(userExample);

		return result;
	}


}
