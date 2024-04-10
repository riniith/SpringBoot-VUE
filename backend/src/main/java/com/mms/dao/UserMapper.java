package com.mms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mms.entity.User;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM user WHERE username = #{username}")
	User selectByPrimaryKey(String username);
	
	/**
	 * 查询指定用户的下属
	 * @param username
	 * @return
	 */
	@Select("SELECT username FROM `user` WHERE manager = #{username}")
	List<String> selectUsernameByManager(String username);
	
	/**
	 * 根据帐号和md5密码查询匹配的用户
	 * @param username
	 * @param md5Password
	 * @return 如果查到了返回用户对象，否则返回null
	 */
	User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String md5Password);

	/**
	 * 插入新用户
	 * @param newUser
	 */
	void insert(User newUser);
	
	/**
	 * 根据帐号更新用户状态
	 * @param username
	 * @param enabled
	 */
	void updateEnabledByUsername(@Param("username") String username, @Param("enabled") boolean enabled);
	
	/**
	 * 更新指定帐号的密码
	 * @param username
	 * @param newPassword
	 */
	void updatePasswordByUsername(@Param("username") String username, @Param("password") String newPassword);

	@Select("SELECT username, enabled, role, manager FROM `user`")
	List<User> selectAll();
	
}
