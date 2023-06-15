package com.example.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final AuthenticationFailureHandler authenticationFailureHandler;
	private final PricipalOAuthUserService pricipalOAuthUserService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.
			// Cross-Site request forgery 보호 기능
			csrf().disable()
			// iframe으로 접근이 안되도록 설정을 비활성화
			.headers().frameOptions().disable()
			.and()
				// URL별 접근제어 정의
				.authorizeRequests()
				// permitAll() : antMatchers에서 설정한 리소스의 접근을 인증절차 없이 허용
				.antMatchers("/",
							 "/member/join", 
							 "/member/login",
							 "/member/login-faild",
							 "/member/logout").permitAll()
				.antMatchers("/css/*", "/favicon.ico").permitAll()
				.antMatchers("/admin/**").hasAnyRole("ADMIN")
				.anyRequest().authenticated()
			.and()
				// 폼 로그인 방식을 사용
				.formLogin()
				// username 파라미터의 기본값은 username이며 다른 이름을 사용할 때는 지정을 해야 함.
				.usernameParameter("member_id")
				// 사용자가 만든 로그인 페이지를 쓰겠다.
				// 설정하지 않으면 기본 URL이 "/login" 이기 때문에 스프링의 기본 로그인 페이지가 호출된다.
				.loginPage("/member/login")
				// 로그인 인증 처리 URL
				.loginProcessingUrl("/member/login")
				// 인증에 성공했을 때 이동할 URL
				.defaultSuccessUrl("/member/login-success")
				// 로그인에 실패했을 때 이동할 URL
//				.failureUrl("/member/login-faild")
				.failureHandler(authenticationFailureHandler)
			.and()
				.logout()
				// 로그아웃 URL 지정
				.logoutUrl("/member/logout")
				// 로그아웃 성공 후 리다이렉트 할 주소
				.logoutSuccessUrl("/")
				// 세션을 삭제한다.
				.invalidateHttpSession(true)
				// 쿠키를 삭제한다.
				.deleteCookies("JSESSIONID")
			.and()
				.oauth2Login()
				.userInfoEndpoint()
				.userService(pricipalOAuthUserService);
			
		return http.build();
			
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
}

















