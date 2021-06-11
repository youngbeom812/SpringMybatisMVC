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
	<h1>[${sessionScope.m.memberName }]님의쿠폰 목록</h1>
	<hr>
	<table border="2">
		<tr>
			<th>번호</th><th>이름</th><th>내용</th><th>발급일</th><th>종료일자</th><th>사용가능여부</th>
		</tr>
		<c:forEach items="${list }" var="coupon" >
		<tr>
			<td>${coupon.couponNo }</td>
			<td>${coupon.couponName }</td>
			<td>${coupon.couponContent }</td>
			<td>${coupon.startDate }</td>
			<td>${coupon.endDate }</td>
			<td>${coupon.status }</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>