<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="icon" type="image/png" href="images/tako.png">
	<title>ログイン画面</title>
</head>
<body>
	<c:if test="${error_msg != null}">
		<div class="flush_error">
			<c:out value="${error_msg}"/>
		</div>
	</c:if>
	
	<form action="Login" method="post">
		<h2>ログインしてください</h2>
		名前<input type="text" name="userName" required="required"><br>
		パスワード<input type="password" name="password" required="required"><br>
		<input type="submit" name="action" value="ログインする">
	</form>
	<br>
	<form action="Register" method="GET">
		<h3>アカウントがない方はこちら</h3>
		<input type="submit" name="action" value="アカウント作成">
	</form>
</body>
</html>