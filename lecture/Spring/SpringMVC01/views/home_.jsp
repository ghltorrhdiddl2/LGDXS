<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.lgdx.controller.Team"%>
<%-- Team 클래스 import --%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	나가세여
	<!-- JSP (Java Server Page) : 동적인 HTML 문서를 만들어낸다. -->
	<!-- home.jsp * home_jsp.java * home_jsp.class * home.html -->
	<!-- JSP에서 Java 코드를 사용하는 방법 -->
	<%
	// 스크립트릿 - JSP에서 Java 코드를 사용할 수 있는 영역(태그)
	// 눈에 보이는건 html에 관련된것만 보임. 코드나 다른건 안보임
	int num1 = 10;
	%>
	<%-- JSP 주석 
		JSP 주석도 눈에 보이지 않는다.
	--%>
	<%-- Java의 변수나 메소드의 값을 JSP 화면에 출력하는 방법 --%>
	num1의 값은?
	<%=num1%>
	입니다.
	<%-- age의 값이 20 이상이면 성인입니다 를 출력
		미만이라면 미성년자 입니다를 출력하시오.
		 --%>
	<%
	int age = 20;
	String prin = "";
	%>
	<br>
	<%
	String result = age >= 20 ? "성인입니다." : "미성년자 입니다.";
	%>
	<%=result%>
	<br>
	<%
	if (age >= 20) {
		prin = "성인입니다.";
	} else {
		prin = "미짜입니다";
	}
	%>
	<%=prin%>
	<br>
	<table border="1px solid black">
		<tr>
			<%
			int num = 2;
			int len = 10;
			for (int i = 1; i < len; i++) {
			%>
			<td><%=num%> * <%=i%> = <%=num * i%></td>
			<%
			}
			%>
		</tr>
	</table>
	<%
	String[] list = new String[9];
	list[0] = "박찬호";
	list[1] = "김도영";
	list[2] = "위즈덤";
	list[3] = "최형우";
	list[4] = "나성범";
	list[5] = "김성빈";
	list[6] = "이성우";
	list[7] = "김태균";
	list[8] = "최원준";
	%>
	<!--  반복문을 활용해서 아래에 선수를 출력해주세요
	 		EX) 1번타자 박찬호
	 			2번타자 김도영
	 			3번타자 위즈덤 -->
	<ul>
		<%
		for (int i = 0; i < list.length; i++) {
		%>
		<li><%=i + 1%>번타자 <%=list[i]%></li>
		<%
		}
		%>
	</ul>
	<%
	Team t1 = new Team("이민우", "1", 26, 'M', "남자");
	Team t2 = new Team("주후상", "2", 27, 'M', "남자");
	Team t3 = new Team("박원지", "3", 25, 'M', "여자");
	Team t4 = new Team("나유진", "4", 24, 'M', "여자");
	Team t5 = new Team("김도은", "5", 23, 'M', "여자");
	Team t6 = new Team("박준호", "6", 26, 'M', "남자");
	Team t7 = new Team("심수지", "7", 25, 'M', "여자");
	ArrayList<Team> teams = new ArrayList<Team>();
	%>
	<%
	teams.add(t1);
	teams.add(t2);
	teams.add(t3);
	teams.add(t4);
	teams.add(t5);
	teams.add(t6);
	teams.add(t7);
	t1.getAge();
	%>
	<table border="1px solid black">
		<tr>
			<td>이름</td>
			<td>전화번호</td>
			<td>나이</td>
			<td>성별</td>
			<td>닉네임</td>
			<%
			for (Team t : teams) {
			%>
		
		<tr>
			<td><%=t.getName()%></td>
			<td><%=t.getPhone()%></td>
			<td><%=t.getAge()%></td>
			<td><%=t.getGender()%></td>
			<td><%=t.getNick()%></td>
		</tr>
		<%
		}
		%>
		</tr>
	</table>
</body>
</html>

