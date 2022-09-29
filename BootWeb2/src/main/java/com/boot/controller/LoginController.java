package com.boot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

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
			//에러 처리
			int error = 1;
			model.addAttribute("error", error);
			return "login";
		}
	}
	
	//로그아웃 처리
	/*@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); //모든 세션 삭제
		return "redirect:";  //경로가 공백이면 '/' 경로와 같음
	}*/
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:";  //경로가 공백이면 '/' 경로와 같음
	}
	
	//회원 가입 페이지 요청
	@GetMapping("/signup")
	public String signupView() {
		return "signup";  //signup.html
	}
	
	//회원 가입 처리
	@PostMapping("/signup")
	public String signup(Member member) {
		service.signup(member);
		return "redirect:login";
	}
	
	//ID 중복 확인
	@GetMapping("/checkID")
	@ResponseBody //데이터 보내는 어노테이션
	public int checkID(String id) {
		int result = service.checkID(id);
		return result;
	}
	
	//회원 정보 처리
	@GetMapping("/memberView")
	public String view(String id, Model model) {
		Member member = service.getOne(id);
		model.addAttribute("member", member); //member 객체를 모델로 보냄
		return "memberView";  //memberView.html로 이동
	}
	
	//회원 탈퇴
	@GetMapping("/deleteMember")
	public String delete(Member member) {
		service.delete(member);
		return "redirect:";  //index.html로 이동
	}
	
	//회원 수정
	@PostMapping("/updateMember")
	public String update(Member member) {
		service.update(member);
		return "redirect:";  //index.html로 이동
	}
}
 





