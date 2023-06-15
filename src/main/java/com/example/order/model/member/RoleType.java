package com.example.order.model.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleType {
	ROLE_USER("사용자"), ROLE_ADMIN("관리자");

	private final String description;

}
