package com.cos.blog.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//Service(CRUD) 역할을 한다.
//자동으로 bean등록이 된다.(=@Repositoy 생략 가능)
//User테이블이 관리하는 레퍼지토린데 User테이블의 PK는 Integer형식이다.
public interface UserRepository extends JpaRepository<User, Integer> {

	//로그인 함수
	//JPA Naming 쿼리
	//SELECT * FROM user WHERE username=? AND password=?; 자동으로 만들어줌
	User findByUsernameAndPassword(String username, String password);
	
	//@Query(value = "SELECT * FROM user WHERE username=? AND password=?", nativeQuery = true)
	//User login(String username, String password);
}
