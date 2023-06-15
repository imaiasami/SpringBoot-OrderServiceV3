package com.example.order.model.member;

import lombok.Data;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Data
public class JoinMemberForm {
	@Size(min = 4, max = 20)
	private String member_id;
	@Size(min = 4, max = 20)
	private String password;
	@NotBlank
	private String name;
	private GenderType gender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth;
	private String email;
	private RoleType role;

	public static Member toMember(JoinMemberForm joinMemberForm) {
		Member member = new Member();
		member.setMember_id(joinMemberForm.getMember_id());
		member.setPassword(joinMemberForm.getPassword());
		member.setName(joinMemberForm.getName());
		member.setGender(joinMemberForm.getGender());
		member.setBirth(joinMemberForm.getBirth());
		member.setEmail(joinMemberForm.getEmail());
		return member;
	}
}
