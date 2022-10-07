package com.boot.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PageRequestDto { //페이지 처리를 위한 클래스
	
	private int page;  //페이지 번호
	private int size;  //페이지당 개수
	
	public PageRequestDto() { //생성할 때 기본값 설정
		this.page = 1;
		this.size = 10;	
	}
	
	//Pageable 타입의 객체를 생성하는 메서드
	public Pageable getPageable(Sort sort) { //page는 0번부터 시작.
		return PageRequest.of(page - 1, size, sort);
	}
}






