package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.config.SecurityUser;
import com.boot.domain.Board;
import com.boot.service.BoardService;

@RequestMapping("/board/")
@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	//게시글 목록
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = service.getBoardList();
		model.addAttribute("boardList", boardList);
		return "board/getBoardList"; //board/getBoardList.html
	}
	
	//상세 보기
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		Board board = service.getBoard(seq);
		model.addAttribute("board", board);
		return "board/getBoard"; //getBoard.html
	}
	
	//글쓰기 폼 요청
	@GetMapping("/insertBoard")
	public void insertBoard() {}
	
	//글쓰기 처리
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, 
			@AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		service.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	//글 수정
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		service.updateBoard(board);
		return "redirect:getBoardList";
	}
}









