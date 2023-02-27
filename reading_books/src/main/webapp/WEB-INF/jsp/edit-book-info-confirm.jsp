<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<p>タイトル：${eBookName}</p>
	<p>購入日：${ePurchaseDate}</p>
	<p>ジャンル：${eBookCategory}</p>
	<p>ページ数：${eTotalPages}</p>
	<p>メモ：</p>
	<p>${eBookMemos}</p>
	<form action="EditBookInfo" method="GET">
		<input type="submit" name="action" value="この内容で確定">
	</form>
	<form action="EditBookInfo" method="POST">
		<input type="submit" name="action" value="編集画面に戻る">
	</form>
</body>
</html>