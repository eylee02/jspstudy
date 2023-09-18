<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="frm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="n" value="12345.6789"/>
	
	<div><frm:formatNumber value="${n}" pattern="#,##0"/></div>      <%-- 12,346 --%>
	<div><frm:formatNumber value="${n}" pattern="#,##0.00"/></div>   <%-- 12,345.68 --%>
	
	<div><frm:formatNumber value="0.5" type="percent"/></div>                       <%-- 50% --%>
	<div><frm:formatNumber value="${n}" type="currency" currencySymbol="$"/></div>  <%-- $12,346 --%>
	<div><frm:formatNumber value="${n}" type="currency" currencySymbol="|"/></div>  <%-- |12,346 --%>
	
	
</body>
</html>