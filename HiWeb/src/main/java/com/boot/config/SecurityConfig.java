package com.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private SecurityUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		//사용자 정의 userDetailsService 객체 사용함
		security.userDetailsService(userDetailsService);
		
		//인증과 권한
		security.authorizeRequests()
		        .antMatchers("/").permitAll();
		
		security.csrf().disable();  //csrf 비활성화
		//로그인 후 게시글 목록 페이지로 이동
		security.formLogin().loginPage("/system/login")
				.defaultSuccessUrl("/board/getBoardList", true);
		
		//로그아웃 처리
		security.logout().logoutUrl("/system/logout")
		        .invalidateHttpSession(true)
		        .logoutSuccessUrl("/");
		
	}
	
}
