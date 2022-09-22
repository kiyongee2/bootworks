package com.boot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Override
	public String hello(String name) { //문자열 리턴
		
		return "hello~ " + name;
	}

	@Override
	public BoardVO getBoard() { //VO 객체 리턴하기
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("테스트 제목...");
		board.setWriter("테스터");
		board.setContent("테스트 내용입니다.");
		board.setCreateDate(new Date());
		board.setCnt(0);
		return board;
	}

	@Override
	public List<BoardVO> getBoardList() { //컬렉션 리턴하기
		List<BoardVO> boardList = new ArrayList<>();
		
		for(int i=1; i<=10; i++) {
			BoardVO board = new BoardVO();
			board.setSeq(i);
			board.setTitle("제목" + i);
			board.setWriter("테스터");
			board.setContent(i + "번 테스트 내용입니다.");
			board.setCreateDate(new Date());
			board.setCnt(0);
			
			boardList.add(board);
		}
		return boardList;
	}
}
