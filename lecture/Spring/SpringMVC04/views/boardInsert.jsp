<%@page import="com.lgdx.entity.Member"%>
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
	
	<jsp:include page="header.jsp"></jsp:include>
	
	  <h2>Spring MVC 03</h2>
	  <div class="panel panel-warning">
	    <div class="panel-heading">Board Insert Page</div>
	    <div class="panel-body">
	    <!-- 
	    	Server로 데이터를 보내기위한 세가지 조건
	    	1.name = 보낼데이터를 구분하기위한 이름
	    	2.action = 데이터를 보낼 곳
	    	3.submit = 데이터를 보내는 시점
	    	
	    	Server로 요청하는 방식
	    	1.GET (Query String)
	    	 - 요청할때 전달할 데이터를 url뒤에 붙여서 보내는 방식
	    	 - boardList.do?title=오늘쌀국수먹음&writer=박병관&contents=정말맛있었음
	    	 - 모든 요청을할때 따로 변경하지 않는한 기본 요청방식은 GET방식이다
	    	 - 장점 : 속도가 빠르다, url 공유가 가능하다
	    	 - 단점 : 보안에 취약하다, 보낼 데이터의 제한이 있다
	    	 
	    	2.POST
	    	 - 요청할때 전달할 데이터를 패킷 바디 안에 보내는 방식
	    	 - 보내는 데이터를 외부에서 확인이 불가능하다
	    	 - 장점 : 보안에 강력하다, 많은 양의 데이터를 보낼 수 있다
	    	 - 단점 : 상대적으로 느리다, 데이터를 공유할 수 없다
	    	 
	    	
	     -->
	     
	     <% Member info = (Member)session.getAttribute("info"); %>
	     
	    	<form action="boardInsert.do" method="post" enctype="multipart/form-data">
	    	
	    	<input type="hidden" name="id" value="<%= info.getId() %>">
	    	
	    	
	    	<table class="table table-bordered table-hover">
	    		<tr>
	    			<td>제목</td>
	    			<td><input name="title" type="text" class="form-control" placeholder="제목을 입력하세요."></td>
	    		</tr>
	    		<tr>
	    			<td>파일</td>
	    			<td><input name="filename" type="file" class="form-control" placeholder="파일을 추가하세요."></td>
	    		</tr>
	    		<tr>
	    			<td>내용</td>
	    			<td>
	    			<textarea name="contents" rows="7" class="form-control" cols="" placeholder="내용을 입력하세요."></textarea></td>
	    		</tr>
	    		<tr>
	    			<td>작성자</td>
	    			<td><input readonly="readonly" value="<%= info.getNick() %>" name="writer" type="text" class="form-control" placeholder="작성자를 입력하세요."></td>
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
	    <div class="panel-footer">LG DX - 박병관</div>
	  </div>
	</div>
</body>
</html>









