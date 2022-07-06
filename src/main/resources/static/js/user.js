let index={
	init: function(){
		$("#btn-save").on("click",()=>{
			this.save();
		});
		$("#btn-update").on("click",()=>{
			this.update();
		});
	},	

	save:function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
// console.log(data);
		// ajax호출시 default가 비동기 호출
		// ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
		$.ajax({
			// 회원가입 수행 요청
			type : "POST",
			url:"/auth/joinProc",
			data:JSON.stringify(data), // java객체를 json형태로 변경
			contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지
			dataType:"json" // 요청을 서버로해서 응답이 왔을 때 생긴게 json이라면(기본적으로 모든것이 문자열) ->
							// JS오브젝트로 변경
		}).done(function(resp){
			// 요청이 성공하면 ㄱㄱ
			alert("회원가입이 완료되었습니다.")
			alert(resp)
			location.href="/";
		}).fail(function(error){
			// 요청이 실패하면 ㄱㄱ
			alert(JSON.stringify(error));
		});
	},
	update:function(){
		let data = {
			id : $("#id").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		$.ajax({
			// 회원가입 수행 요청
			type : "PUT",
			url:"/user",
			data:JSON.stringify(data), // java객체를 json형태로 변경
			contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지
			dataType:"json" // 요청을 서버로해서 응답이 왔을 때 생긴게 json이라면(기본적으로 모든것이 문자열) ->
							// JS오브젝트로 변경
		}).done(function(resp){
			// 요청이 성공하면 ㄱㄱ
			alert("회원수정이 완료되었습니다.")
			location.href="/";
		}).fail(function(error){
			// 요청이 실패하면 ㄱㄱ
			alert(JSON.stringify(error));
		});
	},
	
}

index.init();