package com.boot.service;

import java.util.List;

import com.boot.domain.BoardVO;

public interface BoardService {
	
	String hello(String name);  //문자열 리턴
	
	BoardVO getBoard();  //VO 객체 리턴
	
	List<BoardVO> getBoardList(); //컬렉션(리스트) 리턴
}
