package com.boot.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//순환 참조를 하므로 member 객체를 제외함
@ToString(exclude="member")
@Getter
@Setter
@Entity   //DB 테이블을 만드는 어노테이션
public class Board {
	@Id @GeneratedValue
	private Long seq;          //글번호
	
	private String title;     //글제목
	//private String writer;    //글쓴이, Member name을 사용해야해서 주석
	
	private String content;   //글내용
	
	@Column(updatable=false, 
			columnDefinition="timestamp DEFAULT CURRENT_TIMESTAMP")
	private Date createDate;  //작성일
	
	@Column(updatable=false, 
			columnDefinition="bigint DEFAULT 0")
	private Long cnt = 0L; //조회수 - 초기화
	
	//연관 관계(다대일) 매핑
	@ManyToOne
	@JoinColumn(name="MEMBER_ID") //외래키
	private Member member;
	
}







