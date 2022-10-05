package com.boot.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.boot.config.SecurityUser;
import com.boot.domain.Board;
import com.boot.domain.Search;
import com.boot.dto.FileDto;
import com.boot.service.BoardService;

@RequestMapping("/board/")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	//게시글 목록
	@GetMapping("/getBoardList")
	public String getBoardList(Search search, Model model) {
		//초기값 null 처리
		if(search.getSearchCondition()==null) {
			search.setSearchCondition("TITLE");  //제목으로 설정
		}
		if(search.getSearchKeyword()==null) {
			search.setSearchKeyword("");  //문자 초기화
		}
		//List<Board> boardList = service.getBoardList();
		Page<Board> boardList = service.getBoardList(search);
		model.addAttribute("boardList", boardList);
		return "board/getBoardList"; //board/getBoardList.html
	}
	
	//상세 보기
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		service.updateCount(seq);  //조회수
		Board board = service.getBoard(seq); //상세 보기
		model.addAttribute("board", board);
		return "board/getBoard"; //getBoard.html
	}
	
	//글쓰기 폼 요청
	@GetMapping("/insertBoard")
	public void insertBoard() {}
	
	//글쓰기 처리
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @RequestParam MultipartFile[] uploadFile, Model model,
			@AuthenticationPrincipal SecurityUser principal) throws IllegalStateException, IOException {
		//파일 업로드
		//MultipartFile[]를 파라미터로 객체 사용
		for(MultipartFile file : uploadFile) {
			if(!file.isEmpty()) {
				//FileDto 객체 생성
				FileDto dto = new FileDto(UUID.randomUUID().toString(),
						file.getOriginalFilename(), file.getContentType());
				
				//파일 생성 - File 클래스의 객체는 논리적인 파일 이름임
				File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
				//실제 물리적인 파일로 전달해서 저장
				file.transferTo(newFileName);
			}
		}
		//글쓰기
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
	
	//글 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		service.deleteBoard(board);
		return "redirect:getBoardList";
	}
}









