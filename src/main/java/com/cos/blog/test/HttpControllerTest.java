package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//데이터를 응답하는 컨트롤러
@RestController
public class HttpControllerTest {
	
	//브라우저에 url로 요청은 무조건 get요청만 가능하다!! -> Talend를 사용해서 확인
	//http://localhost:8080/http/get
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get요청"+m.getId()+","+m.getUsername();
	}
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post요청"+m.getId()+","+m.getUsername();
	}
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put요청"+m.getId()+","+m.getUsername();
	}
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete요청";
	}
}
