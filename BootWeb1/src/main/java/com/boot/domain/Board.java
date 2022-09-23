package com.boot.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity   //DB 테이블을 만드는 어노테이션
public class Board {
	@Id @GeneratedValue
	private Long seq;          //글번호
	
	private String title;     //글제목
	
	@Column(updatable=false)   //수정(update)시에 수정 못하도록 설정
	private String writer;    //글쓴이
	private String content;   //글내용
	
	@Column(insertable=false, updatable=false, 
			columnDefinition="timestamp default current_timestamp")  //입력을 수동으로 하지 않도록 설정
	private Date createDate;  //작성일
	
	@Column(insertable=false, updatable=false,
			columnDefinition="bigint default 0")
	private Long cnt;          //조회수
}
