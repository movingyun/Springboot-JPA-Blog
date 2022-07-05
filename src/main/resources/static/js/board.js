let index={
	init: function(){
		$("#btn-save").on("click",()=>{
			this.save();
		});
		$("#btn-delete").on("click",()=>{
			this.deleteById();
		});
	},	

	save:function(){
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}
		$.ajax({
			// 회원가입 수행 요청
			type : "POST",
			url:"/api/board",
			data:JSON.stringify(data), // java객체를 json형태로 변경
			contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지
			dataType:"json" 
		}).done(function(resp){
			// 요청이 성공하면 ㄱㄱ
			alert("글쓰기가 완료되었습니다.")
			location.href="/";
		}).fail(function(error){
			// 요청이 실패하면 ㄱㄱ
			alert(JSON.stringify(error));
		});
	},
	deleteById:function(){
		var id = $("#id").text();
		console.log(id);
		$.ajax({
			// 회원가입 수행 요청
			type : "DELETE",
			url:"/api/board/"+id,
			dataType:"json" 
		}).done(function(resp){
			// 요청이 성공하면 ㄱㄱ
			alert("삭제가 완료되었습니다.")
			location.href="/";
		}).fail(function(error){
			// 요청이 실패하면 ㄱㄱ
			alert(JSON.stringify(error));
		});
	},
	
}

index.init();