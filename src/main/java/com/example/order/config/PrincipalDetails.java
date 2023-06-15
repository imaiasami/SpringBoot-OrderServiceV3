package com.example.order.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.order.model.member.Member;

public class PrincipalDetails implements UserDetails, OAuth2User {

	private Member member;
	private Map<String, Object> attributes;

	public PrincipalDetails(Member member) {
		this.member = member;
	}

	public PrincipalDetails(Member member, Map<String, Object> attributes) {
		this.member = member;
		this.attributes = attributes;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return member.getName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 사용자의 권한 정보를 리턴
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new SimpleGrantedAuthority(member.getRole().name()));
		return collect;
	}

	
	@Override
	public String getUsername() {
		return member.getMember_id();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return member.getPassword();
	}

}
