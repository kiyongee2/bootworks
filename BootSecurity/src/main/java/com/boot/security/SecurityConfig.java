package com.boot.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//시큐리티 설정 파일을 의미하며 시큐리티를 사용하는 데 필요한 수많은 객체를 생성함
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private SecurityUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		
		security.userDetailsService(userDetailsService);
		//인증과 권한 설정
		/*security.authorizeRequests().antMatchers("/").permitAll();
		//로그인 인증 통과
		security.authorizeRequests().antMatchers("/member/**").authenticated();*/
		
		//AuthorizedUrl 빌터 패턴 사용
		security.authorizeRequests()
			    .antMatchers("/").permitAll()
		        .antMatchers("/member/**").authenticated()
		        .antMatchers("/manager/**").hasRole("MANAGER")
		        .antMatchers("/admin/**").hasRole("ADMIN");
		
		security.csrf().disable();
		//시큐리티 제공 로그인 폼
		//security.formLogin();
		security.formLogin()
		        .loginPage("/login")
		        .defaultSuccessUrl("/loginSuccess", true);
		
		//접근 권한 없음 페이지 처리
		security.exceptionHandling().accessDeniedPage("/accessDenied");
		
		//로그아웃 처리
		security.logout().invalidateHttpSession(true)
		        .logoutSuccessUrl("/login");
	} //configure() 끝
	
	//비밀번호 암호화 - PasswordEncoder 객체를 리턴하는 passwordEncoder() 추가
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	/*@Autowired
	private DataSource dataSource;*/
	
	/*@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		//db에 저장된 사용자로 인증 처리
		//query1 : 인증 - 사용자가 입력한 아이디로 사용자 정보 조회
		//id를 username 변수에, password를 password에 저장
		String query1 = "select id username, concat('{noop}', password) password,"
				+ "true enabled from member where id=?";
		//query2 : 사용자가 입력한 아이디로 권한 정보 조회
		String query2 = "select id, role from member where id=?";
		
		//jdbcAuthentication() 사용
		auth.jdbcAuthentication()
		    .dataSource(dataSource)
		    .usersByUsernameQuery(query1)        //인증
		    .authoritiesByUsernameQuery(query2); //권한
		
		//메모리 사용자 인증
		/*auth.inMemoryAuthentication()
		    .withUser("manager")   //직접 만든 아이디 설정
		    .password("{noop}manager123") //{noop} 암호화 하지 않을때 사용
		    .roles("MANAGER");
		
		auth.inMemoryAuthentication()
		.withUser("admin")   //직접 만든 아이디 설정
		.password("{noop}admin123")
		.roles("ADMIN");*/
	
	//}

}
