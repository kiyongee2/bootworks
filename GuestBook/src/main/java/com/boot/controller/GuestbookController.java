package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.GuestbookDto;
import com.boot.dto.PageRequestDto;
import com.boot.service.GuestbookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/guestbook")
@Controller
public class GuestbookController {
	
	private final GuestbookService service;
	
	//목록 보기
	@GetMapping("/list")
	public String list(PageRequestDto pageRequestDto, Model model) {
		model.addAttribute("result", service.getList(pageRequestDto));
		return "/guestbook/list";
	}
	
	//글쓰기 폼 요청
	@GetMapping("/register")
	public void register() {}
	
	//글쓰기 처리
	@PostMapping("/register")
	public String register(GuestbookDto dto, RedirectAttributes
			redirectAttributes) {
		Long gno = service.register(dto);
		redirectAttributes.addFlashAttribute("msg", gno); //글 번호
		return "redirect:list";
	}
	
	//글 상세보기
	@GetMapping("/read")
	public void read(Long gno, Model model,
			@ModelAttribute("requestDto") PageRequestDto requestDto) {
		GuestbookDto dto = service.read(gno); //게시글 1개
		model.addAttribute("dto", dto);
	}
}








