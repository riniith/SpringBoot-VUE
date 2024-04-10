package com.mms.entity;

/**
 * 实体类：用户
 * 
 * @author Administrator
 *
 */
public class User {
	// 帐号，用户名，唯一
	private String username;
	// 密码
	private String password;
	// 状态：true表示正常状态（启用）；false表示禁用状态
	private boolean enabled;
	// 角色: NORMAL | MANAGER | USERMANAGER
	private UserRole role;
	// 直属领导
	private String manager;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public UserRole getRole() {
		return role;
	}
	
	public void setRole(UserRole role) {
		this.role = role;
	}
	
	public String getManager() {
		return manager;
	}
	
	public void setManager(String manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + ", role=" + role
				+ ", manager=" + manager + "]";
	}
	
}
