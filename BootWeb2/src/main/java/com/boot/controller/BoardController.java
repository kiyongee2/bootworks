package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.boot.domain.Board;
import com.boot.domain.Member;
import com.boot.service.BoardService;

//'member'를 세션에 저장
@SessionAttributes("member")  
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	//인증 상태 유지하기
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	//목록 보기
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = service.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "getBoardList";   //getBoardList.jsp
	}
	
	//새글 등록 폼 페이지 요청
	@GetMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member) {
		//로그인한 사용자만 글쓰기 가능
		if(member.getId() == null) {
			return "redirect:login";
		}else {
			return "insertBoard";  // /board/inserBoard.jsp
		}
	}
	
	//새글 등록 처리
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		service.insetBoard(board);
		return "redirect:getBoardList";
	}
	
	//글 상세 보기
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		service.updateCount(seq);  //조회수 증가
		Board board = service.getBoard(seq); //상세 처리
		
		model.addAttribute("board", board); //model - "board" 보냄
		return "getBoard";
	}
	
	//글 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		//게시글 1개 객체 삭제
		service.deleteBoard(board);
		return "redirect:getBoardList";
	}
	
	//글 수정
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		service.updateBoard(board);
		return "redirect:getBoardList";
	}
}






