<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table.type05 {
	border-collapse: separate;
	border-spacing: 1px;
	text-align: left;
	line-height: 1.5;
	border-top: 1px solid #ccc;
	margin: 200px 100px;
}

table.type05 th {
	width: 150px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	background: #efefef;
}

table.type05 td {
	width: 350px;
	padding: 10px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
}
</style>
</head>
<body>
	<h1>공지사항 작성</h1>
	<hr>
	<form action="/writeNotice.do">
		<table class="type05">
			<tr>
				<th scope="row">작성자</th>
				<td><input type="text" name="noticeWriter" readonly value="${sessionScope.m.memberId}"></td>
			</tr>
			<tr>
				<th scope="row">제목</th>
				<td><input type="text" name="noticeTitle"></td>
			</tr>
			<tr>
				<th scope="row">내용</th>
				<td><textarea name="noticeContent" cols="40" rows="8"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="등록"
			style="font-size: 50px; margin-left: 300px;">
	</form>
</body>
</html>