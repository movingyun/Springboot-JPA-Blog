<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">
	<!-- /auth/loginProc는 Spring Security가 가로채간다. -->
	<form action="/auth/loginProc" method="POST">
		<div class="form-group">
			<label for="username">Username :</label> <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">password :</label> <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>

		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=e99501c29dc96fc3dd715b383757d655
		&redirect_uri=http://localhost:8000/auth/kakao/callback
		&response_type=code">
		<img height="38px" src="/image/kakao_login_button.png">
		</a>
	</form>
</div>

<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp"%>