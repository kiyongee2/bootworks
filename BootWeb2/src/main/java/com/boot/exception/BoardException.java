package com.boot.exception;

//사용자 정의 예외 만들기(상속 관계)
public class BoardException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BoardException(String message) { //생성자(message가 파라미터)
		super(message);
	}

}
