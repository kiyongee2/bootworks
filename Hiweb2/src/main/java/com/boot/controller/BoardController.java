package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.domain.Board;
import com.boot.service.BoardService;

@RequestMapping("/board/")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	//게시글 목록 보기 
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		return "board/getBoardList";  //templates/board/getBoarList.html
	}
}
