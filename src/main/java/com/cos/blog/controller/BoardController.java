package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping({"","/"})//아무것도 안적을 때, /일때 일로온다.
	public String index() {
		return "index";
	}
}
