package com.cos.blog.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/**
// 그냥 주소가 /이면 index.jsp 허용
// static 이하에 있는 /js/**,/css/**,/image/** 허용
@Controller
public class UserController {

	@GetMapping("/auth/joinForm")
	public String joinForm() {

		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}

	@GetMapping("/user/updateForm")
	public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {
		return "user/updateForm";
	}

	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoCallback(String code) {

		// POST방식으로 key=value데이터를 요청(카카오쪽으로)
		// POST방식으로 데이터를 보내려면 RestTemplate라는 라이브러리 사용
		// Retrofit2(안드로이드에서 사용), OkHttp(예전에 사용)

		RestTemplate rt = new RestTemplate();

		// Http 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// Http Body 오브젝트 생성(바디에 담아서 날릴거 작성)
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "e99501c29dc96fc3dd715b383757d655");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기(아래서 한번에 보내야 함)
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http요청하기 - Post방식으로 - 그리고 response변수의 응답 받음
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", // 보내는 주소
				HttpMethod.POST, // 보내는 형식
				kakaoTokenRequest, // http body, http header
				String.class // 응답을 받을 타입
		);

		// JSON데이터 받기
		// Gson, Json Simple, ObjectMapper
		ObjectMapper obectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = obectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("카카오 엑세스 토큰:"+oauthToken.getAccess_token());
		
		
		return response.getBody();
	}
}
