package com.boot.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.boot.domain.Member;

public class SecurityUser extends User{

	private Member member;
	
	public SecurityUser(Member member) {
		//super(아이디, 비밀번호, 권한)
		//db에 비밀번호 암호화하여 저장하면 {noop} 제거함
		super(member.getId(), member.getPassword(),
			AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member = member;
	}
	
	//인증된 회원 정보를 html 화면에서 사용
	public Member getMember() {
		return member;
	}
}
