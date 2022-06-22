package com.cos.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repositoy.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입(DI)
	private UserRepository userRepositoy;
	
	//{id} 주소로 파라미터를 전달 받을 수 있음.
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//id로 조회를 하자.
		//있으면 userRepositoy.findById(id)로 찾아오고
		//없으면 override된 함수로 빈 객체를 만들어
		User user = userRepositoy.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다.");
			}
		});
		return user;
	}
	
	
	// http://localhost:8000/blog/dummy/join(요청)
	// http의 body에 username, password, email 데이터를 가지고(요청)
	@PostMapping("/dummy/join")
	public String join(User user) { //입력값을 Object(user)로 받을 수 있음
		System.out.println("username : " + user.getUsername() );
		System.out.println("password : " + user.getPassword() );
		System.out.println("email : " + user.getEmail() );
		System.out.println("role : " + user.getRole() );
		user.setRole(RoleType.USER);
		userRepositoy.save(user);
		
		return "회원가입이 완료되었습니다.";
	}
}
