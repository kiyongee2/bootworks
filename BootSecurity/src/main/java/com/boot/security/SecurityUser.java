package com.boot.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.boot.domain.Member;

public class SecurityUser extends User{

	private static final long serialVersionUID = 11L;

	//User 클래스의 생성자를 호출할때 검색 결과로 얻은 Member 객체를 전달함
	public SecurityUser(Member member) {
		//사용자 정보 - id, password, role(목록)
		//{noop} 제거함
		super(member.getId(), member.getPassword(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}

}
