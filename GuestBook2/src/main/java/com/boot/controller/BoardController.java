package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.BoardDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/board/")
@Controller
public class BoardController {
	
	private final BoardService boardService;

	//게시글 목록 보기
	@GetMapping("/list")
	public String list(PageRequestDto pageRequestDto, Model model) {
		PageResultDto<BoardDto, Object[]> result = boardService.getList(pageRequestDto);
		model.addAttribute("result", result);
		return "board/list";
	}
	
	//게시글 상세 보기
	@GetMapping("/read")
	public void read(@ModelAttribute("requestDto") PageRequestDto requestDto
			, Long bno, Model model) {
		
		BoardDto boardDto = boardService.get(bno);
		model.addAttribute("dto", boardDto);
	}
	
	//글쓰기 폼 페이지 요청
	@GetMapping("/register")
	public void register() {}
	
	//글쓰기 처리
	@PostMapping("/register")
	public String register(BoardDto boardDto, 
			RedirectAttributes redirectAttributes) {
		
		Long bno = boardService.register(boardDto);
		redirectAttributes.addFlashAttribute("msg", bno);
		return "redirect:list";
	}
}




