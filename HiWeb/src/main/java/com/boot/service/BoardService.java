package com.boot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.boot.domain.Board;
import com.boot.domain.Search;

public interface BoardService {
	
	//List<Board> getBoardList(); //목록 보기
	
	//Page<Board> getBoardList(Board board);
	
	Page<Board> getBoardList(Search search);
	
	Board getBoard(Long seq);   //상세 보기
	
	void insertBoard(Board board);  //글쓰기
	
	void updateBoard(Board board);  //글수정
	
	void deleteBoard(Board board);  //글 삭제
	
	void updateCount(Long seq);   //조회수
	
}
