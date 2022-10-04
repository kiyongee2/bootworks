package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.boot.domain.Member;
import com.boot.service.MemberService;

@Controller
public class SecurityController {  //LoginController 역할
	
	@Autowired
	private MemberService memberService;

	//로그인
	@GetMapping("/system/login")
	public void login() {}  //system/login.html 작성
	
	//로그아웃
	@GetMapping("/system/logout")
	public void logout() {}
	
	//회원 가입 폼 요청
	@GetMapping("/system/signup")
	public void signup() {}
	
	//회원 가입 처리
	@PostMapping("/system/signup")
	public String signup(Member member) {
		memberService.signup(member);
		return "redirect:login";
	}
	
	//관리자 페이지 요청
	@GetMapping("/admin/adminPage")
	public void admin() {}
	
	//권한 없음 페이지 요청
	@GetMapping("/system/accessDenied")
	public void accessDenied() {}
}
