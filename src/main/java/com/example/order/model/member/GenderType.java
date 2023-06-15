package com.example.order.model.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderType {
	MALE("남성"), FEMALE("여성");

	private final String description;

}
