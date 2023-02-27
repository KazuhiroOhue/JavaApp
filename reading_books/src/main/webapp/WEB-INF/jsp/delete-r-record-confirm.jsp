<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="icon" type="image/png" href="images/tako.png">
<title>削除してもよろしいでしょうか？</title>
</head>
<body>
<h2>削除してもよろしいでしょうか？</h2>
	<p>読書した日：
		<c:choose>
			<c:when test="${empty eReadingDate}">不明</c:when>
			<c:otherwise>${eReadingDate}</c:otherwise>
		</c:choose>
	</p>
	<p>本の名前：${eBookName}</p>
	<p>読んだページ数：${eReadingPages}</p>
	<p>メモ：</p>
	<p>${eReadingMemos}</p>
	<form action="DeleteRRecord" method="GET">
		<input type="submit" name="action" value="削除してOK">
	</form>
	<form action="DeleteRRecord" method="POST">
		<input type="submit" name="action" value="やめる">
	</form>
</body>
</html>