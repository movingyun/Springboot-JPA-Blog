package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	@GetMapping({"","/"})//아무것도 안적을 때, /일때 일로온다.
	public String index() { //컨트롤러에서 세션을 어떻게 찾지?
		return "index";
	}
	
	//글쓰기
	//USER권한 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
}
