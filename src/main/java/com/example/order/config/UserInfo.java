package com.example.order.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.order.model.member.Member;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserInfo implements UserDetails {
	
	private Member member;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 사용자의 권한 정보를 리턴
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new SimpleGrantedAuthority(member.getRole().name()));
		return collect;
	}

	@Override
	public String getPassword() {
		// 사용자의 패스워드 리턴
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		// 사용자의 아이디 리턴
		return member.getMember_id();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정 만료 여부
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠금 여부 리턴
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 접속 권한 만료 여부 리턴
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 계정 사용 가능 여부
		return true;
	}

}
