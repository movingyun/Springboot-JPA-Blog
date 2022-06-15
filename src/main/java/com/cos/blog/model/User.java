package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity //User클래스가 MySQL에 자동으로 테이블이 생성이 된다.
public class User {
	
	@Id//primary key 주기
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라감.(=mySQL에서는 auto_increment를 사용하겠다!)
	private int id; //auto_increment
	
	@Column(nullable = false,length = 30) //Column어노테이션으로 컬럼 속성 준다(Notnull, 길이 = 30)
	private String username; //아이디
	
	@Column(nullable = false,length = 100) //Notnull, 길이 = 100
	private String password;
	
	@Column(nullable = false,length = 50) //Notnull, 길이 = 50
	private String email;
	
	@ColumnDefault(" 'user' ") //기본값 지정
	private String role; //Enum(값의 도메인을 정해둠)을 쓰는게 좋다. //admin,user,manager
	
	@CreationTimestamp //시간이 자동 입력
	private Timestamp createDate;
}
