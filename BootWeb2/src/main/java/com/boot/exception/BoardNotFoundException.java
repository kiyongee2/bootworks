package com.boot.exception;

public class BoardNotFoundException extends BoardException{

	private static final long serialVersionUID = 2L;

	//객체가 생성될때 에러 메시지 출력
	public BoardNotFoundException(String message) {
		super(message);
	}

}
