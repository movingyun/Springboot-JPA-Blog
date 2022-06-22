package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더패턴
@Entity //User클래스가 MySQL에 자동으로 테이블이 생성이 된다.
//@DynamicInsert //insert시에 null인 필드를 제거해줌(role을 null로 안넣고 default값 넣어주려고 사용)
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
	
//	@ColumnDefault("user") enum을 사용하면서 주석처리
	//DB는 RoleType이 없어서 알려줘야댐
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum(값의 도메인을 정해둠)을 쓰는게 좋다. //admin,user
	
	@CreationTimestamp //시간이 자동 입력
	private Timestamp createDate;
}
