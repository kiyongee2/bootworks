package com.boot.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
public class Member {
	@Id  //기본키(PK)임을 설정
	private String id;
	private String password;
	private String name;
	
	@Enumerated(EnumType.STRING) //문자열 타입으로 저장
	private Role role;        //권한 자료형 참조
	
	private boolean enabled;
}
