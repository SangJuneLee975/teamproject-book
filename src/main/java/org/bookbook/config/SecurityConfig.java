package org.bookbook.config;

import javax.sql.DataSource;

import org.bookbook.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import lombok.extern.log4j.Log4j;

@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);

		http.addFilterBefore(filter, CsrfFilter.class);

		http.csrf().ignoringAntMatchers("/api/**");

		http.authorizeRequests().antMatchers("/security/profile").authenticated();

		http.formLogin().loginPage("/security/login?error=login_required") // 로그인 안하고 접근한 경우 리다이렉트
				.loginProcessingUrl("/security/login").defaultSuccessUrl("/") // 로그인 성공시 다음 화면 넘어줄 URL
				.failureUrl("/security/login?error=true"); // el : param.error

		http.logout() // 로그아웃 설정 시작
				.logoutUrl("/security/logout") // 로그아웃을 수행할 때 POST 요청을 보낼 URL을 설정
				.invalidateHttpSession(true) // 로그아웃 시 사용자 세션을 무효화할지 여부를 설정
				.deleteCookies("remember-me", "JSESSION-ID") // 로그아웃 시 삭제할 쿠키 목록을 지정
				.logoutSuccessUrl("/"); // 로그아웃 이후 이동할 페이지

		http.rememberMe() // remember-me 기능 설정
				.key("bookbook") // "remember-me" 기능에서 사용할 키를 설정
				.tokenRepository(persistentTokenRepository()) // "remember-me" 토큰의 저장과 검색을 담당하는 리포지토리를 설정
				.tokenValiditySeconds(3 * 24 * 60 * 60); // "remember-me" 토큰의 유효성 기간을 설정. 3일 동안 유효한 토큰을 설정

	}

	@Autowired
	private DataSource dataSource;

	@Bean
	public UserDetailsService customUserService() {
		return new CustomUserDetailsService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());

	}

	// 서버에 토큰 저장 series 값과 token 값을 따로 만들어서 저장
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl(); // 이 클래스는 JDBC를 사용하여 "remember-me" 토큰을 저장하고 관리
		repo.setDataSource(dataSource);

		return repo;
	}

}
