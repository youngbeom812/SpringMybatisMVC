<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<h1>수정하기</h1>
<hr>
<form action="/updateNotice.do" method="post">
<table class="type05">
  <tr>
    <th scope="row">번호</th>
    <td><input type="text" readonly name="noticeNo" value="${n.noticeNo }"></td>
  </tr>
  <tr>
    <th scope="row">제목</th>
    <td><input type="text" name="noticeTitle" value="${n.noticeTitle }"></td>
  </tr>
  <tr>
    <th scope="row">내용</th>
    <td rowspan="2"><input type="text" name="noticeContent" value="${n.noticeContent }"></td>
  </tr>
  <tr>
  </tr>
  <tr>
    <th scope="row">작성자</th>
    <td><input type="text" readonly name="noticeWriter" value="${n.noticeWriter }"></td>
  </tr>
  <tr>
    <th scope="row">작성일</th>
    <td><input type="text" readonly name="noticeDate" value="${n.noticeDate }"></td>
  </tr>
</table>
	<input type="submit" value="정보수정">
</form>
</body>
</html>