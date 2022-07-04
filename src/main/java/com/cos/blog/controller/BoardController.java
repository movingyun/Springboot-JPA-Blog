package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"","/"})//아무것도 안적을 때, /일때 일로온다.
	public String index(Model model) { 
		model.addAttribute("boards",boardService.글목록());
		return "index";
	}
	
	//글쓰기
	//USER권한 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
}
