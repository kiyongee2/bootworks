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
	private BoardService boardService;

	//게시글 목록 보기 
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		return "board/getBoardList";  //templates/board/getBoarList.html
	}
	
	//게시글 상세 보기
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		boardService.updateCount(seq);  //조회수 증가
		Board board = boardService.getBoard(seq);
		model.addAttribute("board", board);
		return "board/getBoard";
	}
	
	//글 쓰기 폼 페이지 요청
	@GetMapping("/insertBoard")
	public void insertBoard() {}
	
	//글쓰기 처리 요청
	@PostMapping("/insertBoard")
	public String insertBoard(Board board,
			@AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember()); //인증된 회원이 글쓰기 함
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
}








