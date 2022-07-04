package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration //bean등록(Ioc관리)
@EnableWebSecurity //시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증을 미리 체크하겠음.
public class SecrutiyConfig extends WebSecurityConfigurerAdapter{
	
	@Bean //Ioc가 된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //csrf토큰 비활성화(테스트 시 걸어두는게 좋음)
			.authorizeRequests() //request가 들어오면
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**") 
				.permitAll()
				.anyRequest() //이거 빼고는
				.authenticated() //다 인증이 되어야 함.
			.and()
				.formLogin()
				.loginPage("/auth/loginForm");
	}
}
