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
	<h1>게시물 상세!</h1>
	<hr>
	<table border="2">
		<tr>
			<th>게시판번호</th>
			<td>${b.boardNo }</td>
		</tr>
		<tr>
			<th>게시판제목</th>
			<td>${b.boardTitle }</td>
		</tr>
		<tr>
			<th>게시판작성자</th>
			<td>${b.boardWriter }</td>
		</tr>
		<tr>
			<th>게시판날짜</th>
			<td>${b.boardDate }</td>
			</tr>
		<tr>
			<th>게시판내용</th>
			<td>${b.boardContent }</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
			<c:forEach items="${b.fileList }" var="b">
			<%--   <td>${b.fileNo}</td>
			<td>${b.filename }</td>
			 --%>
			 ${b.filename }
			</c:forEach>
			</td>
		</tr>
	</table>
</body>
</html>