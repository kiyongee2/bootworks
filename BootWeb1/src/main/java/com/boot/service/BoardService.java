package com.boot.service;

import java.util.List;

import com.boot.domain.Board;

public interface BoardService {
	
	//게시글 목록
	List<Board> getBoardList();
	
	//새글 등록
	void insetBoard(Board board);
	
	//글 상세 보기
	Board getBoard(Long seq);
	
	//게시글 삭제
	void deleteBoard(Board board);
	
	//게시글 수정
	void updateBoard(Board board);
}
