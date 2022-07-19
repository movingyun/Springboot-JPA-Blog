package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repositoy.UserRepository;

@Service // 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 = IoC를 해준다.
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	//변경된 계정으로 세션을 새로 넣어주기 위해서 사용
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	// 회원가입
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

	@Transactional
	public void 회원수정(User user) {
		User originUser = userRepository.findById(user.getId())
				.orElseThrow(()->{
					return new IllegalArgumentException("회원찾기 실패");
				});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		originUser.setPassword(encPassword);
		originUser.setEmail(user.getEmail());
		
		//변경된 계정으로 세션 새로 등록
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 된다.
		//영속화된 originUser 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌
	}

}
