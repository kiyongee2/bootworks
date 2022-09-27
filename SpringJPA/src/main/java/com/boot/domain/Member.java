package com.boot.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//순환 참조를 하므로 boardList 객체를 제외함
@ToString(exclude="boardList")
@Setter
@Getter
@Entity
public class Member {
	@Id
	@Column(name="MEMBER_ID")  //기본키 이름 설정(외래키에서 참조)
	private String id;
	private String password;
	private String name;
	private String role;
	
	//일다다 관계 매핑
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER)
	private List<Board> boardList = new ArrayList<>();
}
