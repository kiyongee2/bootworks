package com.boot.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.domain.Member;

//Member 엔티티를 사용하여 삽입, 조회, 삭제, 수정 등을 처리
public interface MemberRepository extends JpaRepository<Member, String>{

}
