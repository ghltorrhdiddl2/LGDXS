<%@page import="com.lgdx.entity.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="boardList.do">LG DX School</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="boardList.do">Home</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Page 1-1</a></li>
          <li><a href="#">Page 1-2</a></li>
          <li><a href="#">Page 1-3</a></li>
        </ul>
      </li>
      <li><a href="#">Page 2</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    
      <!-- 로그인 정보에 따라서 로그아웃, 로그인정보 출력하기 -->
      <% Member info = (Member)session.getAttribute("info"); %>
      
      <% if(info != null){ %>
      	<li><a><%= info.getNick() %> 님 Welcome~</a></li>
      	<li><a href="update.do"><span class="glyphicon glyphicon-refresh"></span> Update</a></li>
      	<li><a href="logout.do"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      <% }else{ %>
      	<li><a href="login.do"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      <% } %>
      
      
    </ul>
  </div>
</nav>










