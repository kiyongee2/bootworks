package com.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private SecurityUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		//사용자 정의 userDetailsService 객체 사용함
		security.userDetailsService(userDetailsService);
		
		security.csrf().disable();  //csrf 비활성화
		//로그인 후 게시글 목록 페이지로 이동(인증)
		security.formLogin().loginPage("/system/login")
				.defaultSuccessUrl("/board/getBoardList", true);
		
		//로그아웃 처리
		security.logout().logoutUrl("/system/logout")
		        .invalidateHttpSession(true)  //세션 삭제
		        .logoutSuccessUrl("/");
		
		//권한
		security.authorizeRequests()
		        .antMatchers("/", "/system/**").permitAll()
		        .antMatchers("/board/**").authenticated()
				.antMatchers("/admin/**").hasRole("ADMIN");
		//접근 권한 없음
		security.exceptionHandling()
		        .accessDeniedPage("/system/accessDenied");
	}
	
	//비밀번호 암호화 객체 반환해줌
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}
