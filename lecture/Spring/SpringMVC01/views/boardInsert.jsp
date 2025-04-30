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
	  <h2>Spring MVC 01</h2>
	  <div class="panel panel-warning">
	    <div class="panel-heading">Board Insert Page</div>
	    <div class="panel-body">
	    <!-- 
	    	Server로 데이터를 보내기 위한 세가지 조건
	    	1.name = 보낼 데이터를 구분하기 위한 이름
	    	2.action = 데이터를 보낼 곳
	    	3.submit = 데이터를 보내는 시점
	    	
	    	Server로 요청하는 방식
	    	1.GET (Query String)
	    	 - 요청할 때 전달할 데이터를 url뒤에 붙여서 보내는 방식
	    	 - boardList.do?title=오늘퀘사디아먹음&writer=심수지&contents=꽤맛있었음
	    	 - 모든 요청을 할 때 따로 변경하지 않는 한 기본 요청 방식은 GET방식
	    	 - 장점 : 속도 빠름, url 공유 가능
	    	 - 단점 : 보안에 취약, 보낼 데이터의 제한이 있음
	    	 
	    	2.POST
	    	 - 요청할 떄 전달할 데이터를 패킷 바디 안에 보내는 방식
	    	 - 보내는 데이터를 외부에서 확인 불가
	    	 - 장점 : 보안 좋음, 많은 양의 데이터를 보낼 수 있음
	    	 - 단점 : 상대적으로 느림, 데이터 공유 불가
	     -->
	    	<form action="boardInsert.do" method="post">
	    	<table class="table table-bordered table-hover">
	    		 <tr>
	    		 	<td>제목</td>
	    		 	<td><input name="title" type="text" class="form-control" placeholder="제목을 입력하세요."></td>
	    		 </tr>
	    		 <tr>
	    		 	<td>내용</td>
	    		 	<td><textarea name="contents" rows="7" class="form-control" cols="" placeholder="내용을 입력하세요."></textarea></td>>
	    		 </tr>
	    		 <tr>
	    		 	<td>작성자</td>
	    		 	<td><input name="writer" type="text" class="form-control" placeholder="작성자를 입력하세요."></td>
	    		 </tr>
	    		 <tr>
	    		 	<td colspan="2" align="center">
	    		 		<a href="boardList.do"><button type="button" class="btn btn-info">돌아가기</button></a>
	    		 		<button type="reset" class="btn btn-danger">취소</button>
	    		 		<button type="submit" class="btn btn-warning">작성</button>
	    		 	</td>
	    		 </tr>
	    	</table>
	    	</form>
	    </div>
	    <div class="panel-footer">LG DX - 고양이</div>
 	</div>
</div>
</body>
</html>