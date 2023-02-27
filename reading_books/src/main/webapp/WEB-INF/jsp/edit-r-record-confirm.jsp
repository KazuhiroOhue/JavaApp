<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="icon" type="image/png" href="images/tako.png">
<title>変更内容の確認</title>
</head>
<body>
	<h2>この内容でよろしいでしょうか？</h2>
	<p>読書した日：${eReadingDate}</p>
	<p>本の名前：${eBookName}</p>
	<p>読んだページ数：${eReadingPages}ページ</p>
	<p>メモ：</p>
	<p>${eReadingMemos}</p>
	<form action="EditRRecord" method="GET">
		<input type="submit" name="action" value="この内容で確定">
	</form>
	<form action="EditRRecord" method="POST">
		<input type="submit" name="action" value="編集画面に戻る">
	</form>
</body>
</html>