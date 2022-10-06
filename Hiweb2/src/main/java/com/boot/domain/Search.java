package com.boot.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Search { //파라미터 전달 - DTO
	private String searchCondition;  //조건(제목, 내용)
	private String searchKeyword;    //검색어
}
