<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="icon" type="image/png" href="images/tako.png">
<title>アカウント登録</title>
</head>
<body>
	<c:if test="${error_msg != null}">
		<div class="flush_error">
			<c:out value="${error_msg}" />
		</div>
	</c:if>

	<form action="Register" method="post">
		<h2>登録する名前とパスワードを入力してください</h2>
		名前<input type="text" name="userName" required="required"><br>
		パスワード<input type="password" name="password" required="required"><br>
		<button>新規登録</button>
	</form>
	<br>
	<a href="Start">やめる</a>

</body>
</html>