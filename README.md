# 나만의 블로그 만들기

## Lombok
 > 개발자의 단순 반복 작업을 덜어주기 위한 라이브러리로 데이터 접근을 위한 객체인 DTO의 캡슐화하는 작업들을 자동으로 해준다.<br>
 > **장점** : 반복적인 단순 작업을 안하도록 도와준다. ex) getter,setter생성<br>
 > **단점** : 개인 PC마다 Lombok이 모두 설치되어 있어야 한다.
```java
@Data   // getter, setter, toString 자동생성
@AllArgsConstructor   // 모든 인자를 가진 생성자 생성
@NoArgsConstructor  // 인자가 없는 생성자 생성
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
}
```
> **build패턴 사용**<br>
> DTO - Builder 어노테이션 적용
```
@Builder
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
}
```
>Controller - .builder().파라미터1(값1).파라미터2(값2).build()
```
@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().email("ydy1107@naver.com").password("ssafy").build();
		return "lombok test";
	}
 ```
 > 장점 : 생성자 파라미터의 순서를 지키지 않아도 된다.
