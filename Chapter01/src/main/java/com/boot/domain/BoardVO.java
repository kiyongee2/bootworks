package com.boot.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private int seq;          //글번호
	private String title;     //글제목
	private String writer;    //글쓴이
	private String content;   //글내용
	private Date createDate;  //작성일
	private int cnt;          //조회수
}
