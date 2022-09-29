package com.boot.service;

import java.util.List;

import com.boot.domain.Board;

public interface BoardService {
	
	List<Board> getBoardList(); //목록 보기
	
	Board getBoard(Long seq);   //상세 보기
	
}
