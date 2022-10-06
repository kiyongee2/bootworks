package com.boot.service;

import com.boot.domain.Member;

public interface MemberService {
	
	void signup(Member member);  //회원 가입
	
	Member view(String userid);  //상세 보기
}
