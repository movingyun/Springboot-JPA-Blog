package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repositoy.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입(DI)
	private UserRepository userRepositoy;
	
	//삭제하기
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepositoy.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패했습니다.";
		}
		return "삭제되었습니다.";
	}
	
	
	//업데이트
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) {
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getPassword());
		
		User originUser = userRepositoy.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		originUser.setPassword(requestUser.getPassword());
		originUser.setEmail(requestUser.getEmail());
//		userRepositoy.save(originUser);
		return null;
	}
	
	
	//전체 유저 가져오기
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepositoy.findAll();
	}
	
	//유저 가져와서 페이징하기
	//한페이지당 2건의 데이터를 리턴
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2,sort = "id",direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepositoy.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	//{id} 주소로 파라미터를 전달 받을 수 있음.
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//id로 조회를 하자.
		//있으면 userRepositoy.findById(id)로 찾아오고
		//없으면 override된 함수로 해당유저 없다고 메세지 날려주자
		User user = userRepositoy.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException(id+"번 유저는 없습니다.");
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
