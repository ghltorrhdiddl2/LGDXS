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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	<jsp:include page="header.jsp"></jsp:include>
		<h2>Spring MVC 03</h2>
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
					// Model이라는 저장소는 request안에 포함되어 있다
					ArrayList<Board> list = (ArrayList<Board>) request.getAttribute("list");
					%>

					<%
					for (int i = 0; i < list.size(); i++) {
					%>
					<tr>
						<td><%=i + 1%></td>
						<td><a
							href="boardContents.do?idx=<%=list.get(i).getIdx()%>"><%=list.get(i).getTitle()%></a></td>
						<td><%=list.get(i).getWriter()%></td>
						<td><%=list.get(i).getCount()%></td>
						<td><%=list.get(i).getIndate()%></td>
					</tr>
					<%
					}
					%>
				</table>
				
				<% Member info = (Member)session.getAttribute("info"); //세션가져옴%>
				<% if(info != null) {  //로그인 되었을때만 글쓰기 버튼 활성화%>
					<a class="btn btn-primary" href="boardInsert.do">글쓰기</a>
				<% }else{ %>
					<a class="btn btn-primary" href="javascript:loginInfo()">글쓰기</a>
				<% } %>
			</div>
			<div class="panel-footer">LG DX - 최강고양이</div>
		</div>
	</div>
	<script type="text/javascript">
		function loginInfo(){
			let result = confirm("로그인을 해야 글쓰기가 가능합니다. 로그인 하시겠습니까?");
			if(result){
				location.href="login.do";
			}
		}
	</script>
</body>
</html>
