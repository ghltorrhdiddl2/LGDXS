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
	    <div class="panel-heading">Board Contents Page</div>
	    <div class="panel-body">
	    	<table class="table table-bordered table-hover">
	    	<% Board vo = (Board)request.getAttribute("vo"); %>
	    		 <tr>
	    		 	<td>제목</td>
	    		 	<td><%=vo.getTitle()%></td>
	    		 </tr>
	    		 <tr>
	    		 	<td>내용</td>
	    		 	<td><%=vo.getContents().replaceAll("\n", "<br>")%></td>
	    		 </tr>
	    		 <tr>
	    		 	<td>작성자</td>
	    		 	<td><%=vo.getWriter()%></td>
	    		 </tr>
	    		 <tr>
	    		 	<td colspan="2" align="center">
	    		 		<a href="boardList.do"><button type="button" class="btn btn-info">돌아가기</button></a>
	    		 		<a href="boardDelete.do?idx=<%= vo.getIdx() %>"><button type="button" class="btn btn-danger">삭제</button></a>
	    		 		<a href="boardUpdate.do?idx=<%= vo.getIdx() %>"><button type="button" class="btn btn-warning">수정</button></a>
	    		 	</td>
	    		 </tr>
	    	</table>
	    </div>
	    <div class="panel-footer">LG DX - 고양이</div>
 	</div>
</div>
</body>
</html>