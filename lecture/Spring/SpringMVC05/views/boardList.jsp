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
	  <h2>Spring MVC 05</h2>
	  <div class="panel panel-warning">
	    <div class="panel-heading">Board List Page</div>
	    <div class="panel-body">
	    	<table class="table table-bordered table-hover">
	    		<tr class="warning">
	    			<td>번호</td>
	    			<td>제목</td>
	    			<td>작성자</td>
	    			<td>조회수</td>
	    			<td>날짜</td>
	    		</tr>
	    		<% 
	    		//Model이라는 저장소는 request안에 포함되어 있다
	    		ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list"); //다운캐스팅
	    		for(Board b:list){%>
	    			<tr>
	    				<td><%=b.getIdx()%></td>
	    				<td><a href = "boardContents.do?idx=<%= b.getIdx() %>"><%= b.getTitle()%></a></td>
	    				<td><%=b.getWriter()%></td>
	    				<td><%=b.getCount()%></td>
	    				<td><%=b.getIndate()%></td>
	    			</tr>
	    		<% } %>	
	    	</table>
	    	
	    	<a class="btn btn-primary" href="boardInsert.do">글쓰기</a>
	    	
	    </div>
	    <div class="panel-footer">LG DX - 고양이</div>
 	</div>
</div>
</body>
</html>