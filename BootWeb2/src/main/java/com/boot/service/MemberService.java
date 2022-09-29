package com.boot.service;

import com.boot.domain.Member;

public interface MemberService {
	//로그인 처리
	Member getMember(Member member);
	
	//회원 가입
	void signup(Member member);
	
	//ID 중복 확인
	int checkID(String id);
	
	//회원 정보
	Member getOne(String id);
	
	//회원 탈퇴
	void delete(Member member);
	
	//회원 수정
	void update(Member member);
}
