package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BootWeb1Application extends SpringBootServletInitializer{

	//메인 실행
	public static void main(String[] args) {
		SpringApplication.run(BootWeb1Application.class, args);
	}

	//war 배포를 위한 추가 코드(메소드 재정의)
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BootWeb1Application.class);
	}

}
