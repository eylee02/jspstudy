<%@page import="java.util.Optional"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<% request.setCharacterEncoding("UTF-8");
	 Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
	 String title = opt.orElse("환영합니다.");
	 String contextPath = request.getContextPath();   // /jsp 를 contextPath로 변수선언 후 사용
%>
<title><%=title%></title>
<script src="<%=contextPath%>/resource/js/lib/jquery-3.7.1.min.js"></script>
<script src="<%=contextPath%>/resource/js/header.js?dt=<%=System.currentTimeMillis()%>"></script>   <%-- 마우스 반응에 따른 css설정 js파일 첨부--%>
<link rel="stylesheet" href="<%=contextPath%>/resource/css/header.css?dt=<%=System.currentTimeMillis()%>">   <%-- css 파일을 style로 첨부하는방법 --%>
<link rel="stylesheet" href="<%=contextPath%>/resource/css/main.css?dt=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" href="<%=contextPath%>/resource/css/footer.css?dt=<%=System.currentTimeMillis()%>">
</head>
<body>

	<nav class="gnb">
		<div class="logo"></div>        <%-- header.js 파일에 이미지경로 작성되어있음 --%>
		<ul>
			<li><a href="main1.jsp">main1</a></li>
			<li><a href="main2.jsp">main2</a></li>
			<li><a href="main3.jsp">main3</a></li>
		</ul>
	</nav>
	
	