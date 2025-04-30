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
<style>
	/* form */
body{
    background:#efefef;
}
.form-body{
    background:#fff;
    padding:20px;
}
.login-form{
    background:rgba(255,255,255,0.8);
	padding:20px;
	border-top:3px solid#3e4043;
}
.innter-form{
	padding-top:20px;
}
.final-login li{
	width:50%;
}

.nav-tabs {
    border-bottom: none !important;
}

.nav-tabs>li{
	color:#222 !important;
}
.nav-tabs>li.active>a, .nav-tabs>li.active>a:hover, .nav-tabs>li.active>a:focus {
    color: #fff;
    background-color: #d14d42;
    border: none !important;
    border-bottom-color: transparent;
	border-radius:none !important;
}
.nav-tabs>li>a {
    margin-right: 2px;
    line-height: 1.428571429;
    border: none !important;
    border-radius:none !important;
	text-transform:uppercase;
	font-size:16px;
}

.social-login{
	text-align:center;
	font-size:12px;
}
.social-login p{
	margin:15px 0;
}
.social-login ul{
	margin:0;
	padding:0;
	list-style-type:none;
}
.social-login ul li{
	width:33%;
	float:left;
    clear:fix;
}
.social-login ul li a{
	font-size:13px;
	color:#fff;
	text-decoration:none;
	padding:10px 0;
	display:block;
}
.social-login ul li:nth-child(1) a{
	background:#3b5998;
}
.social-login ul li:nth-child(2) a{
	background:#e74c3d;
}
.social-login ul li:nth-child(3) a{
	background:#3698d9;
}
.sa-innate-form input[type=text], input[type=password], input[type=file], textarea, select, email{
    font-size:13px;
	padding:10px;
	border:1px solid#ccc;
	outline:none;
	width:100%;
	margin:8px 0;
	
}
.sa-innate-form input[type=submit]{
    border:1px solid#e64b3b;
	background:#e64b3b;
	color:#fff;
	padding:10px 25px;
	font-size:14px;
	margin-top:5px;
	}
	.sa-innate-form input[type=submit]:hover{
	border:1px solid#db3b2b;
	background:#db3b2b;
	color:#fff;
	}
	
	.sa-innate-form button{
	border:1px solid#e64b3b;
	background:#e64b3b;
	color:#fff;
	padding:10px 25px;
	font-size:14px;
	margin-top:5px;
	}
	.sa-innate-form button:hover{
	border:1px solid#db3b2b;
	background:#db3b2b;
	color:#fff;
	}
    .sa-innate-form p{
        font-size:13px;
        padding-top:10px;
    }
</style>

</head>
<body>
	<div class="container">
	<jsp:include page="header.jsp"></jsp:include>
	  <h2>Spring MVC 01</h2>
	  <div class="panel panel-warning">
	    <div class="panel-heading">Board Insert Page</div>
	    <div class="panel-body">
			<div class="row">
<div class="col-md-4 col-md-offset-4">
<div class="form-body">
    <ul class="nav nav-tabs final-login">
        <li class="active"><a data-toggle="tab" href="#sectionA">Update</a></li>
    </ul>
    <div class="tab-content">
        <div id="sectionA" class="tab-pane fade in active">
        
        <% Member info = (Member)session.getAttribute("info"); //세션가져오기%>
        <!-- 로그인 입력 폼 -->
        <div class="innter-form">
            <form action="update.do" class="sa-innate-form" method="post">
            <label>ID</label>
            <input onclick="alert('아이디는 수정 불가능합니다');" type="text" name="id" value="<%= info.getId() %>" readonly="readonly">
            <label>Password</label>
            <input type="password" name="pw" value="<%= info.getPw() %>">
            <label>NickName</label>
            <input type="text" name="nick" value="<%= info.getNick() %>">
            <button type="submit">Update</button>
            </form>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    </div>
    </div>
    </div>
	    </div>
	    <div class="panel-footer">LG DX - 최강고양이</div>
	  </div>
	</div>
</body>
</html>