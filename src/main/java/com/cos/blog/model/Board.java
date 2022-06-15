package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더패턴
@Entity //Board클래스가 MySQL에 자동으로 테이블이 생성이 된다.
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //대용량 데이터 쓸 때 사용
	private String content; //섬머노트 라이브러리 사용 - <html>태그가 섞여서 디자인됨
	
	@ColumnDefault("0")
	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER) //객체관계 생성 //Many = Board, User = One -> 한명의 유저는 여러개의 게시물 작성 
	@JoinColumn(name="userId") //userId라는 컬럼으로 만들어
	private User user; //DB는 오브젝트를 저장할수없어서 FK사용. But 자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //하나의 게시글엔 여러개의 답변이 달린다.
	//mappedBy 연과관계의 주인이 아니다(난 FK가 아니다) DB에 컬럼을 추가하지 않는다.
	//내가 FK가 아니라 Reply의 board가 FK야!
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
