package com.boot.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boot.domain.Member;
import com.boot.persistence.MemberRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepo;
	
	//아이디 - user를 Member 엔티티의 id로 사용하기 위해 재정의
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// MemberRepository로 회원 정보(id)를 조회하여
		//UserDetails 객체로 반환함
		Optional<Member> optional = memberRepo.findById(username);
		if(!optional.isPresent()) {
			throw new UsernameNotFoundException(username);
		}else {
			Member member = optional.get();
			return new SecurityUser(member);
		}
	}
}
