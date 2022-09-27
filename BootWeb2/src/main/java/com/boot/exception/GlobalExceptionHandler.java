package com.boot.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //예외 처리 전용 컨트롤러
public class GlobalExceptionHandler {
	
	//해당 메서드가 들어가는 예외 타입을 처리
	@ExceptionHandler(BoardException.class) //Board 관련 오류 처리
	public String handleCustomException(BoardException exception,
			Model model) {
		model.addAttribute("exception", exception);
		return "/errors/boardError";
	}
	
	@ExceptionHandler(Exception.class) //일반적인 코드 및 구문 오류 처리
	public String handleException(Exception exception,
			Model model) {
		model.addAttribute("exception", exception);
		return "/errors/globalError";
	}
	
	
}
