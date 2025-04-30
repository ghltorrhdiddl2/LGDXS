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
	
	  <h2>Spring MVC 04</h2>
	  <div class="panel panel-warning">
	    <div class="panel-heading">Board Contents Page</div>
	    <div class="panel-body">
	    <% Board vo = (Board)request.getAttribute("vo"); %>
	    	<table class="table table-bordered table-hover">
	    		<tr>
	    			<td>제목</td>
	    			<td><%= vo.getTitle() %></td>
	    		</tr>
	    		<tr>
	    			<td>내용</td>
	    			<td>
	    				<% if(vo.getFilename()!=null){ %>
	    					<img style="max-width: 500px;" alt="" src="resources/upload/<%=vo.getFilename()%>">
	    					<br>
	    				<% } %>
	    			<%= vo.getContents().replaceAll("\n", "<br>") %></td>
	    		</tr>
	    		<tr>
	    			<td>작성자</td>
	    			<td><%= vo.getWriter() %></td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center">
	    				<a href="boardList.do"><button type="button" class="btn btn-info">돌아가기</button></a>
	    				<!-- 
	    					BoardController에 boardDelete.do 요청시 
	    					해당 idx와 일치하는 게시글을 삭제하고
	    					boardList.jsp 페이지로 이동하시오
	    					힌트. 상세보기를 잘 살펴보면 매우 흡사하다
	    				 -->
	    				<% Member info = (Member)session.getAttribute("info"); %>
	    				
	    				<% if(info != null){ %>
	    					<% if(info.getId().equals(vo.getId())){ %>
	    						<a href="boardDelete.do?idx=<%= vo.getIdx() %>"><button type="button" class="btn btn-danger">삭제</button></a>
	    						<a href="boardUpdate.do?idx=<%= vo.getIdx() %>"><button type="button" class="btn btn-warning">수정</button></a>
	    					<% }else{ %>
	    						<button disabled="disabled" type="button" class="btn btn-danger">삭제</button>
	    						<button disabled="disabled" type="button" class="btn btn-warning">수정</button>
	    					<% } %>
	    				
	    				<% } %>
	    				 
	    				
	    			</td>
	    		</tr>
	    	</table>
	    	
	    </div>
	    <div class="panel-footer">LG DX - 박병관</div>
	  </div>
	</div>
</body>
</html>









