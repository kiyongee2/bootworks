package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.domain.BoardVO;
import com.boot.service.BoardService;

@RestController //jsp를 만들지 않고 문자열, 객체를 반환
public class BoardController {
	
	@Autowired
	private BoardService service;  //service 객체 선언

	//문자열 리턴, 파라미터에 값 설정
	@GetMapping("/hello")
	public String hello2(String name) {
		return service.hello(name);
	}
	
	//VO 객체 리턴
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		//getBoard() 사용
		BoardVO board = service.getBoard();
		return board;
	}
	
	//컬렉션(리스트) 리턴
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList(){
		List<BoardVO> boardList = service.getBoardList();
		return boardList;
	}
}








