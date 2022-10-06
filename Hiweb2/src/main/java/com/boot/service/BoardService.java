package com.boot.service;

import java.util.List;

import com.boot.domain.Board;

public interface BoardService {
	
	List<Board> getBoardList();   //글 목록 보기
}
