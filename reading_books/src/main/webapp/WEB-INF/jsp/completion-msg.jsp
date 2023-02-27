<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="icon" type="image/png" href="images/tako.png">
<title>完了しました！</title>
</head>
<body>
	<p><c:out value="${msg}" />	</p>
	<c:if test="${action == 'returnRRList'}">
		<form action="RRecordList" method="GET">
			<input type="submit" value="読書記録一覧に戻る">
		</form>
	</c:if>
	<form action="Main" method="POST">
		<input type="submit" value="メインページに戻る">
	</form>
</body>
</html>