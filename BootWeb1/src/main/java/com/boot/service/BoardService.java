package com.boot.service;

import java.util.List;

import com.boot.domain.Board;

public interface BoardService {
	
	//게시글 목록
	List<Board> getBoardList();
}
