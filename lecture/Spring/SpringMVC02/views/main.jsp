<%@page import="com.lgdx.entity.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	  <h2>Spring MVC 02</h2>
	  <div class="panel panel-warning">
	    <div class="panel-heading">Board List Page</div>
	    <div class="panel-body" id="list">
	    	<table class="table table-bordered table-hover">
	    		<tr class="warning">
	    			<td>번호</td>
	    			<td>제목</td>
	    			<td>작성자</td>
	    			<td>조회수</td>
	    			<td>날짜</td>
	    		</tr>
	    		
	    		<tbody id="view"></tbody>
	    		
	    	</table>
	    	
	    	<a class="btn btn-primary" href="javascript:goForm()">글쓰기</a>
	    	
	    </div>
	    
	    <div class="panel-body" id="wform" style="display:none;">
	    	<form id="frm">
	    	<table class="table table-bordered table-hover">
	    		 <tr>
	    		 	<td>제목</td>
	    		 	<td><input name="title" type="text" class="form-control" placeholder="제목을 입력하세요."></td>
	    		 </tr>
	    		 <tr>
	    		 	<td>내용</td>
	    		 	<td><textarea name="contents" rows="7" class="form-control" cols="" placeholder="내용을 입력하세요."></textarea></td>
	    		 </tr>
	    		 <tr>
	    		 	<td>작성자</td>
	    		 	<td><input name="writer" type="text" class="form-control" placeholder="작성자를 입력하세요."></td>
	    		 </tr>
	    		 <tr>
	    		 	<td colspan="2" align="center">
	    		 		<a href="javascript:goList()"><button type="button" class="btn btn-info">돌아가기</button></a>
	    		 		<button id="fclear" type="reset" class="btn btn-danger">취소</button>
	    		 		<button onclick="goInsert()" type="button" class="btn btn-warning">작성</button>
	    		 	</td>
	    		 </tr>
	    	</table>
	    	</form>
	    </div>
	    
	    <div class="panel-footer">LG DX - 박병관</div>
	  </div>
	</div>
	
	<script type="text/javascript">
		
		loadList(); //함수호출
		//게시글 전체 불러오기 기능
		function loadList(){
			//비동기 통신 - ajax
			$.ajax({
				url : "boardList.do",
				type: "GET",
				dataType: "json",
				success : makeView,
				error : function(){
					alert("error...");
				}
			});
		}
		
		function makeView(data){
			console.log(data);
			
			let html="";
			for(let i=0;i<data.length;i++){
				html += "<tr>";
				html += "<td>" + data[i].idx + "</td>";
				
				html += "<td id='t"+ data[i].idx +"'>";
				html += "<a href='javascript:goContents("+ data[i].idx +")'>";
				html += data[i].title;
				html += "</a>";
				html += "</td>";
				
				html += "<td id='w"+ data[i].idx +"'>" + data[i].writer + "</td>";
				html += "<td>" + data[i].count + "</td>";
				html += "<td>" + data[i].indate + "</td>";
				
				html += "</tr>";
				
				//상세내용
				html += "<tr id='c"+ data[i].idx +"' style='display : none;'>";
				html += "<td>내용</td>";
				html += "<td colspan='4'>";
				html += "<textarea id='ta"+ data[i].idx +"' readonly rows='7' class='form-control'>";
				//html += data[i].contents;
				html += "</textarea>";
				
				//수정, 삭제 버튼
				html += "<br>";
				html += "<span id='ub"+data[i].idx+"'>";
				html += "<button onclick='goUpdateForm("+ data[i].idx +")' class='btn btn-danger'>수정</button>&nbsp&nbsp"; // nbsp : 띄어쓰기
				html += "</span>"; //span태그로 구역 나누기 용도
				html += "<button onclick='goDelete("+ data[i].idx +")' type='button' class='btn btn-warning'>삭제</button>";
				
				html += "</td>";
				html += "</tr>";
			}
			
			$("#view").html(html);
			goList();
		}
		
		//글쓰기 보여주기 기능
		function goForm(){
			$("#wform").css("display","block");
			$("#list").css("display","none");
		}
		//게시글 목록 다시 보여주기 기능
		function goList(){
			$("#wform").css("display","none");
			$("#list").css("display","block");
		}
		
		//게시글 작성 기능
		function goInsert(){
			// title=hello&contents=hi&writer=hodoo
			let fData = $("#frm").serialize();
			console.log(fData);
			
			$.ajax({
				url : "boardInsert.do",
				type: "post",
				data: fData,
				success : loadList,
				error : function(){
					alert("error...");
				}
			});
			
			$("#fclear").trigger("click");
		}
		
		//상세보기 기능
		function goContents(idx){
			let state = $("#c"+idx).css("display");
			//console.log(state);
			if(state=="none"){
				//클릭한 하나의 게시글 정보를 가져오기
				$.ajax({
					url : "boardContents.do",
					type: "get",
					data: {"idx":idx},
					dataType: "json",
					success : function(data){
						console.log(data);
						$("#ta"+idx).val(data.contents);
					},
					error : function(){alert("error...");}
				});
				
				$("#c"+idx).css("display","table-row");
			}
			else{
				$.ajax({
					url : "boardCount.do",
					type: "get",
					data: {"idx":idx},
					success : loadList,
					error : function(){alert("error...");}
				});
				$("#c"+idx).css("display","none");
			}	
		}
		
		//삭제기능
		function goDelete(idx){
			$.ajax({
				url : "boardDelete.do",
				type : "get",
				data : {"idx":idx},
				success : loadList,
				error : function(){alert("error...");}
				
			});
		}
		
		//수정기능
		function goUpdateForm(idx){
			$("#ta"+idx).attr("readonly",false);
			let title = $("#t"+idx).text();
			let newTitle = "<input id='nt"+idx+"' value='"+title+"' type='text' class='form-control'>"; //텍스트 → 입력창으로 바꿔서 수정 가능하게
			$("#t"+idx).html(newTitle);
			
			let writer = $("#w"+idx).text();
			let newWriter = "<input id='nw"+idx+"' value='"+writer+"' type='text' class='form-control'>"; //텍스트 → 입력창으로 바꿔서 수정 가능하게
			$("#w"+idx).html(newWriter);
			
			let newButton = "<button onclick='goUpdate("+idx+")' class='btn btn-primary'>수정하기</button>&nbsp;&nbsp";
			$("#ub"+idx).html(newButton);
		}
		
		//게시글 수정 기능
		function goUpdate(idx){
			let title = $("#nt"+idx).val();
			let writer = $("#nw"+idx).val();
			let contents = $("#ta"+idx).val();
			console.log(title+"/"+writer+"/"+contents);
			
			$.ajax({
				url : "boardUpdate.do",
				type: "post",
				data: {"idx":idx,"title":title,"writer":writer,"contents":contents},
				success : loadList,
				error : function(){alert("error...");}
			});
		}
		
	</script>
</body>
</html>
