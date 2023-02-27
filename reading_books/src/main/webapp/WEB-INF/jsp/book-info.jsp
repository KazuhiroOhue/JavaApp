<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="icon" type="image/png" href="images/tako.png">
<title>${readBook.bookName}</title>
</head>
<body>
	<img src="images/${readBook.imagePath}" height="100">
	<h3>${readBook.bookName}</h3>
	<p>${readBook.purchaseDate}購入</p>
	<p>ジャンル：${readBook.bookCategory}</p>
	<p>${readBook.totalPages}ページ</p>
	<br>
	<p>${readBook.bookmemos}</p>
	<br>
	<form action="EditBookInfo" method="GET">
		<input type="submit" name="action" value="編集する">
	</form>
	<form action="BookInfo" method="POST">
		<input type="submit" value="一覧にもどる">
	</form>
</body>
</html>