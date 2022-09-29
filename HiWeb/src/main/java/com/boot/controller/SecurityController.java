package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {  //LoginController 역할

	//로그인
	@GetMapping("/system/login")
	public void login() {}  //system/login.html 작성
	
	//로그아웃
	@GetMapping("/system/logout")
	public void logout() {}
}
