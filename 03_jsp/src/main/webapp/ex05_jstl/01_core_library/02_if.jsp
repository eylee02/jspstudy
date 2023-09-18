<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		<c:if>
		1. if문이다.
		2. else문이 없다. (if문을 2개 작성해야함)
		3. 형식
			<c:if test="조건식">
				실행문
			</c:if>
	 --%>
	 
	 <c:set var="age" value="29"/>
	 <c:if test="${age >= 20}">
	 		<div>성인</div>
	 </c:if>
	 <c:if test="${age < 20}">
	 		<div>미성년자</div>
	 </c:if>
	 
	 <c:set var="score" value="85"/>
	 <c:if test="${score >= 90}">
	 		<div>A</div>
	 </c:if>
	 <c:if test="${score < 90 and score >= 80}">
	 		<div>B</div>
	 </c:if>
	 
	 
	 
	 


</body>
</html>