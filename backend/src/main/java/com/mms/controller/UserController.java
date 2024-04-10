package com.mms.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mms.entity.PasswordModel;
import com.mms.entity.User;
import com.mms.service.UserService;

@CrossOrigin // 支持跨域请求
@RestController // 告诉SpringBoot: 这是一个控制器组件
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		User authenticatedUser = this.userService.login(user.getUsername(), user.getPassword());
		if (authenticatedUser == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else {
			return ResponseEntity.ok(authenticatedUser);
		}
	}
	
	@GetMapping("/user")
	public List<User> list() {
		return this.userService.list();
	}
	
	@PutMapping("/user/pwd")
	public ResponseEntity<?> updatePwd(@RequestBody PasswordModel passwordModel) {
		boolean success = this.userService.updatePassword(passwordModel.getUsername(), passwordModel.getOldPassword(), passwordModel.getNewPassword());
		if (success) {
			return ResponseEntity.ok("");
		} 
		return ResponseEntity.badRequest().body("旧密码不正确");
	}
	
	@PutMapping("/user/enable/{username}")
	public void enable(@PathVariable String username) {
		this.userService.enableUser(username);
	}
	
	@PutMapping("/user/disable/{username}")
	public void disable(@PathVariable String username) {
		this.userService.disableUser(username);
	}
	
	@PostMapping("/user")
	public void create(@RequestBody User user) {
		this.userService.createUser(user);
	}
	
	@GetMapping("/user/check/{username}")
	public boolean check(@PathVariable String username) {
		return this.userService.check(username);
	}
	
}
