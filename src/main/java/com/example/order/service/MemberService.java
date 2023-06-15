package com.example.order.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.order.model.member.Member;
import com.example.order.model.member.RoleType;
import com.example.order.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	// 회원정보 검색
	public Member findMemberById(String member_id) {
		return memberRepository.findMemberById(member_id);
	}

	// 회원가입
	public void saveMember(Member member) {
		// 패스워드 암호화, 단방향 암호화 방식, 암호화 된 값은 동일하다. 그걸 비교한다.
		String rawPassword = member.getPassword();
		String encPassowrd = passwordEncoder.encode(rawPassword);
		member.setPassword(encPassowrd);
		// 기본 Role 부여
		member.setRole(RoleType.ROLE_USER);
		memberRepository.saveMember(member);
	}
}
