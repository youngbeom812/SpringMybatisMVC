<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${sessionScope.m.memberId }의쪽지함</h1>
	<hr>
	<fieldset>
		<h3>받은쪽지함</h3>
		<table border="1">
			<tr>
				<th>번호</th>
				<th>보낸사람</th>
				<th>날짜</th>
				<th>확인여부</th>
			</tr>
			<c:forEach items="${getList }" var="dm" varStatus="i">
				<tr>
					<td>${i.count }</td>
					<td>${dm.sender }</td>
					<td>${dm.dmTime }</td>
					<td>${dm.readStatus }</td>
				</tr>
			</c:forEach>
		</table>
	</fieldset>
	<fieldset>
		<h3>보낸쪽지함</h3>
		<table border="1">
			<tr>
				<th>번호</th>
				<th>받는사람</th>
				<th>날짜</th>
				<th>확인여부</th>
			</tr>
			<c:forEach items="${sendList }" var="dm" varStatus="i">
				<tr>
					<td>${i.count }</td>
					<td>${dm.receiver }</td>
					<td>${dm.dmTime }</td>
					<td>${dm.readStatus }</td>
				</tr>
			</c:forEach>
		</table>
	</fieldset>
	<hr>
	<br>
	<h2>쪽지보내기</h2>
	<input type="hidden" name="sender" value="${sessionScope.m.memberId }">
	내용 : <input type="text" name="dmContent"><br>
	받는사람 : <input type="text" name="receiver">
	<button onclick="sendDm();">쪽지보내기</button>
	<script>
		function sendDm(){
			var sender = $("[name=sender]").val();
			var dmContent = $("[name=dmContent]").val();
			var receiver = $("[name=receiver]").val();
			$.ajax({
				url : "/sendDm.do",
				data : {sender:sender, dmContent:dmContent , receiver:receiver},
				type : "post",
				success : function(data){
					if(data == 1){
						alert('쪽지보내기 성공');
					}else{
						alert('쪽지보내기 실패');
					}
						location.reload();
						//location.href="/dmList.do?memberId="+sender;
				}
			});
		}
	</script>
</body>
</html>