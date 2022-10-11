package com.boot.service;

import java.util.List;

import com.boot.domain.Board;

public interface BoardService {
	
	List<Board> getBoardList();   //글 목록 보기
	
	Board getBoard(Long seq);    //글 상세 보기
	
	void insertBoard(Board board);  //글쓰기
	
	void updateCount(Long seq);  //조회수
	
	void updateBoard(Board board); //글 수정
	
	void deleteBoard(Board board); //글 삭제
}
