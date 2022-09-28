package com.boot.service;

import com.boot.domain.Member;

public interface MemberService {
	//로그인 처리
	Member getMember(Member member);
	
	//회원 가입
	void signup(Member member);
}
