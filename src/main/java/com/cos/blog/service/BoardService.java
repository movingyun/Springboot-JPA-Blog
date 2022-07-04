package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repositoy.BoardRepository;


@Service //스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 = IoC를 해준다.
public class BoardService {
	
	@Autowired
	private BoardRepository BoardRepository;
	
	@Transactional
	public void 글쓰기(Board board, User user) {//title content 받아옴
		board.setCount(0);
		board.setUser(user);
		BoardRepository.save(board);
	}
}