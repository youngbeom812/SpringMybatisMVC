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
	<h1>[${sessionScope.m.memberName }]님의 쿠폰리스트!</h1>
	<hr>
	<table border="1">
		<tr>
			<th>번호</th><th>이름</th><th>내용</th><th>발급날짜</th><th>종료날짜</th><th>사용가능여부</th>
		</tr>
		<c:forEach items="${list }" var="c" varStatus="i">
			<tr>
				<td>${i.count }</td>
				<td>${c.couponName }</td>
				<td>${c.couponContent }</td>
				<td>${c.startDate }</td>
				<td>${c.endDate }</td>
				<td>${c.status }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>