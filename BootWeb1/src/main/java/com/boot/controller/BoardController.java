package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.boot.domain.Board;
import com.boot.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;

	@GetMapping("/hello")
	public void hello() {  //hello.jsp
	}
	
	//목록 보기
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = service.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "getBoardList";   //getBoardList.jsp
	}
}
