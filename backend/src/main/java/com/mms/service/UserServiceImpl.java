package com.mms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.mms.dao.UserMapper;
import com.mms.entity.User;

/**
 * UserService接口的实现类
 * 
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl implements UserService {

	//service调用dao，所以需要创建mapper实例
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(String username, String password) {
		// 对明文密码进行加密
		String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
		// 根据username和md5Password去数据库查询匹配的用户
		User user = this.userMapper.selectByUsernameAndPassword(username, md5Password);
		// 返回结果
		if (user != null) {
			user.setPassword(""); // 洗掉密码
		}
		return user;
	}

	@Override
	public void logout(String username) {
		throw new RuntimeException("logout还未实现，不能调用");
	}

	@Override
	public void createUser(User newUser) {
		// 补填信息，这些信息不需要人工在界面上填写
		newUser.setPassword(DigestUtils.md5DigestAsHex("kaimen".getBytes()));
		newUser.setEnabled(true);
		// 保存到数据库
		this.userMapper.insert(newUser);
	}

	@Override
	public void disableUser(String username) {
		// 更新用户表的enabled字段为0
		this.userMapper.updateEnabledByUsername(username, false);
	}

	@Override
	public void enableUser(String username) {
		this.userMapper.updateEnabledByUsername(username, true);
	}

	@Override
	public boolean updatePassword(String username, String oldPassword, String newPassword) {
		// 首先，得到旧密码的md5密文
		String md5OldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
		// 查询数据库，验证旧密码对不对
		User user = this.userMapper.selectByUsernameAndPassword(username, md5OldPassword);
		// 如果不对，直接返回false
		if (null == user) {
			return false;
		}
		// 得到新密码的md5密文
		String md5NewPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
		// 更新数据库
		this.userMapper.updatePasswordByUsername(username, md5NewPassword);
		return true;
	}

	@Override
	public List<User> list() {
		return this.userMapper.selectAll();
	}
	
	@Override
	public boolean check(String username) {
		User user = this.userMapper.selectByPrimaryKey(username);
		return user == null;
	}
}
