package com.boot.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.boot.exception.BoardNotFoundException;

@Controller
public class ExceptionController {
	
	@GetMapping("/boardError")
	public String boardError() {
		//throw new 클래스이름 : 강제로 예외 발생 시킴
		throw new BoardNotFoundException("검색된 게시글 없습니다.");
	}
	
	@GetMapping("/illegalArgumentError")
	public String illegalArgumentError() {
		throw new IllegalArgumentException("부적절한 인자가 전달되었습니다.");
	}
	
	@GetMapping("/sqlError")
	public String sqlError() throws SQLException {
		throw new SQLException("SQL 구문에 오류가 있습니다.");
	}
}
