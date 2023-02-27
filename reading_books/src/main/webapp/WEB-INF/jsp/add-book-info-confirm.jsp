<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="icon" type="image/png" href="images/tako.png">
<title>登録内容の確認</title>
</head>
<body>
<h2>この内容でよろしいでしょうか？</h2>
	<p>タイトル：${aBookName}</p>
	<p>購入日：${aPurchaseDate}	</p>
	<p>ジャンル：${aBookCategory}</p>
	<p>ページ数：${aTotalPages}</p>
	<p>メモ：</p>
	<p>${aBookMemos}</p>
	<form action="AddBookInfo" method="GET">
		<input type="submit" name="action" value="この内容で確定">
	</form>
	<form action="AddBookInfo" method="POST">
		<input type="submit" name="action" value="戻る">
	</form>
</body>
</html>