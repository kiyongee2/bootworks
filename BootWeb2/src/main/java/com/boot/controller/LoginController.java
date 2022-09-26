package com.boot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.boot.domain.Member;
import com.boot.service.MemberService;

//'member' 세션 유지
@SessionAttributes("member") 
@Controller
public class LoginController {
	
	@Autowired
	private MemberService service;
	
	//로그인 페이지 요청
	@GetMapping("/login")
	public String loginView() {
		return "login";  //login.jsp
	}
	
	//로그인 인증 처리
	@PostMapping("/login")
	public String login(Member member, Model model) {
		Member findMember = service.getMember(member); //db에 저장된 객체
		//findMember의 비밀번호와 로그인 폼에 입력한 비밀번호와 비교
		if(findMember != null 
				&& findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("member", findMember); //세션 발급(view에 보내줌)
			return "redirect:getBoardList";
		}else {
			return "redirect:login";
		}
	}
	
	//로그아웃 처리
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); //모든 세션 삭제
		return "redirect:";  //경로가 공백이면 '/' 경로와 같음
	}
}






