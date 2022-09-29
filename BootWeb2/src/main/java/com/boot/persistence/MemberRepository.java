package com.boot.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.boot.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	//ID 중복 확인 - 입력한 아이디가 있으면 1개, 없으면 0개
	@Query("SELECT COUNT(*) FROM Member m WHERE m.id = :id")
	public int checkID(String id);
}
