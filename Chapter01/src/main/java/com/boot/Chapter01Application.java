package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chapter01Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter01Application.class, args);
		
		/*SpringApplication application = 
				new SpringApplication(Chapter01Application.class);
		
		//자바 애플리케이션으로 실행하기
		//application.setWebApplicationType(WebApplicationType.NONE);
		
		//Spring Boot App으로 실행
		application.setWebApplicationType(WebApplicationType.SERVLET);
		application.run(args);*/
	}

}
