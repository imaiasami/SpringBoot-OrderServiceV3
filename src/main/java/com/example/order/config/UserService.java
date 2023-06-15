package com.example.order.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.order.model.member.Member;
import com.example.order.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * 로그인 요청이 들어오면 자동으로 UserDetailsService의 loadUserByUsername 메소드가 실행
 * 회원정보를 담고있는 UserDetails 타입의 값을 리턴해야 한다.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("username: {}", username);
		Member findMember = memberRepository.findMemberById(username);
		if (findMember != null) {
			return new PrincipalDetails(findMember);
		}
		throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
	}

	
}
