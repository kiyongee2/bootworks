package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SecurityController {
	
	@GetMapping("/")
	public String index() {
		log.info("index 요청입니다.");
		return "index";  //index.html
	}
	
	@GetMapping("/member")  //member.html로 이동
	public void forMember() {
		log.info("Member 요청입니다.");
	}
	
	@GetMapping("/manager")  //manager.html로 이동
	public void forManager() {
		log.info("Manager 요청입니다.");
	}
	
	@GetMapping("/admin")  //admin.html로 이동
	public void forAdmin() {
		log.info("Admin 요청입니다.");
	}
	
	@GetMapping("/login")
	public void login() {
		log.info("login 요청입니다.");
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		log.info("login 성공입니다.");
	}
	
	//접근 권한 없음
	@GetMapping("/accessDenied") //accessDenied.html로 이동
	public void accessDenied() {
		log.info("accessDenied 접근 거부");
	}

}
