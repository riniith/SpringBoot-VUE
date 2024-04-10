package com.mms.service;

import java.util.List;

import com.mms.entity.User;

/**
 * 用户模块的业务接口
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * 根据帐号和密码进行身份认证
	 * @param username 用户输入的帐号
	 * @param password 用户输入的密码
	 * @return 如果身份认证成功，返回一个含有帐号、角色信息的User对象；如果身份认证失败，返回null
	 */
	User login(String username, String password);
	
	/**
	 * 注销指定的用户
	 * @param username 要注销的用户的帐号
	 */
	void logout(String username);
	
	/**
	 * 创建用户
	 * @param newUser 包含了帐号、角色信息的新用户对象
	 */
	void createUser(User newUser);
	
	/**
	 * 禁用指定的用户
	 * @param username 要禁用的用户的帐号
	 */
	void disableUser(String username);
	
	/**
	 * 启用指定的用户
	 * @param username 要启用的用户的帐号
	 */
	void enableUser(String username);
	
	/**
	 * 更新密码
	 * @param username 用户的帐号
	 * @param oldPassword 用户输入的旧密码
	 * @param newPassword 用户输入的新密码
	 * @return 如果更新密码成功则返回true；如果旧密码不正确，返回false
	 */
	boolean updatePassword(String username, String oldPassword, String newPassword);
	
	/**
	 * 查询所有的用户
	 * @return
	 */
	List<User> list();

	/**
	 * 检查指定的用户名是否可用
	 * @param username
	 * @return
	 */
	boolean check(String username);
}
