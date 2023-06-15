package com.example.order.config;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.info("로그인 실패 핸들러 호출: {}", exception);
		String errorMessage = "";
		if (exception instanceof BadCredentialsException) {
			log.info("아이디 또는 비밀번호가 맞지 않습니다.");
			errorMessage = "아이디 또는 비밀번호가 맞지 않습니다.";
		}
		errorMessage = "알 수 없는 이유로 로그인에 실패 했습니다.";
		errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
		setDefaultFailureUrl("/member/login?error=true&exception=" + errorMessage);
		super.onAuthenticationFailure(request, response, exception);
	}

}















