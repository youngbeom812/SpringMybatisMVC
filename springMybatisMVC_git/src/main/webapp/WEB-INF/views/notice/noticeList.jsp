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
	 <h1>공지사항목록띠</h1>
	<hr>
	<table border="2">
		<tr>
			<th>번호</th><th>제목</th><th>내용</th><th>작성자</th><th>날짜</th>
		</tr>
		<c:forEach items="${list }" var="notice">
		<tr>
			<td>${notice.noticeNo }</a></td>
			<td><a href="noticeView.do?noticeNo=${notice.noticeNo }">${notice.noticeTitle }</a></td>
			<td>${notice.noticeContent }</td>
			<td>${notice.noticeWriter }</td>
			<td>${notice.noticeDate }</td>
			</tr>	
		</c:forEach>
	</table> 
		<a href="writeNoticeFrm.do">공지사항작성</a>
</body>
</html>