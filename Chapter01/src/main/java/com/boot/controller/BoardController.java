package com.boot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.domain.BoardVO;

@RestController //jsp를 만들지 않고 문자열, 객체를 반환
public class BoardController {
	
	public BoardController() {
		System.out.println("==> BoardController 생성");
	}
	
	//문자열 리턴하기
	@GetMapping("/hello")
	public String hello() {
		return "안녕~ SpringBoot!!";
	}
	
	//문자열 리턴, 파라미터에 값 설정
	@GetMapping("/hello2")
	public String hello2(String name) {
		return "안녕~ " + name;
	}
	
	//VO 객체 리턴
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("테스트 제목...");
		board.setWriter("테스터");
		board.setContent("테스트 내용입니다.");
		board.setCreateDate(new Date());
		board.setCnt(0);
		
		return board;
	}
	
	//컬렉션(리스트) 리턴
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList(){
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








