package com.example.order.config;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.order.model.member.Member;
import com.example.order.model.member.RoleType;
import com.example.order.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PricipalOAuthUserService extends DefaultOAuth2UserService {

	private final MemberRepository memberRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("userRequest.getClientRegistration(): {}", userRequest.getClientRegistration());
		log.info("userRequest.getClientRegistration(): {}", userRequest.getClientRegistration());

		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info("oAuth2User.getAttributes(): {}", oAuth2User.getAttributes());

		// 서비스 이름
		String provider = userRequest.getClientRegistration().getRegistrationId();

		String name = null;
		String email = null;
		switch (provider) {
		case "google":
			// 이름
			name = oAuth2User.getAttribute("name");
			// 아이디
			email = oAuth2User.getAttribute("email");
			break;
		case "kakao":
			Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
			name = ((Map<String, String>) kakaoAccount.get("profile")).get("nickname");
			email = (String) kakaoAccount.get("email");
			break;
		}

		log.info("email: {}", email);
		log.info("name: {}", name);
		log.info("provider: {}", provider);

		Member member = Member.builder().member_id(email).password("1234").name(name).role(RoleType.ROLE_USER)
				.provider(provider).build();

		// 처음 로그인 하는 사람은 회원가입
		Member findMember = memberRepository.findMemberById(email);
		if (findMember == null) {
			memberRepository.saveMember(member);
		}

		return new PrincipalDetails(member, oAuth2User.getAttributes());
	}
}
