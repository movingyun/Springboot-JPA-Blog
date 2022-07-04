package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

@Configuration //bean등록(Ioc관리)
@EnableWebSecurity //시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증을 미리 체크하겠음.
public class SecurtiyConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean //Ioc가 된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 대신 로그인해줄 때 password를 가로채기하는데
	//해당 password가 뭘로 해시가 되어 회원가입이 되었는지 알려주어야한다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //csrf토큰 비활성화(테스트 시 걸어두는게 좋음)
			.authorizeRequests() //request가 들어오면
				.antMatchers("/auth/**","/js/**","/css/**","/image/**") 
				.permitAll()
				.anyRequest() //이거 빼고는
				.authenticated() //다 인증이 되어야 함.
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc") //해당주소로 요청오는 로그인을 가로채서 대신 로그인해준다.
				.defaultSuccessUrl("/");  // 성공하면 /로 이동해준다.
	}
}
