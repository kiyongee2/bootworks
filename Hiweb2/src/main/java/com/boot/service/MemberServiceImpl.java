package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.domain.Member;
import com.boot.domain.Role;
import com.boot.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder pwencoder;

	@Override
	public void signup(Member member) {
		//비밀번호 암호화
		String encPW = pwencoder.encode(member.getPassword());
		member.setPassword(encPW);
		member.setRole(Role.ROLE_MEMBER); //권한 설정
		member.setEnabled(true);   //계정 있음
		memberRepo.save(member);
	}

	@Override
	public Member view(String userid) {
		return memberRepo.findById(userid).get();
	}

}
