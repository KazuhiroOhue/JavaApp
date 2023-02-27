<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="icon" type="image/png" href="images/tako.png">
<title>編集中</title>
</head>
<body>
	<form action="EditBookInfo" method="GET">
		タイトル：<br> <input type="text" name="eBookName" class="bookName"
			value="${eBookName}" maxlength="50" required="required"><br>
		購入日：<br> <input type="date" name="ePurchaseDate"
			value="${ePurchaseDate}"> <br>
			
			<%
			List<String> category = new ArrayList<>();
			category.add("学習参考書");
			category.add("専門書");
			category.add("文学・文芸");
			category.add("ビジネス");
			category.add("趣味");
			category.add("マンガ");
			category.add("その他");
			request.setAttribute("category",category);
			%>
			
			 ジャンル：${eBookCategory}<br>
		<select name="eBookCategory">
			<c:forEach var="category" items="${category}">
	    		<option value="${category}"${category == eBookCategory ? 'selected' : ''}>${category}</option>
	    	</c:forEach>
		</select> <br> ページ数：<br> <input type="number" name="eTotalPages"
			value="${eTotalPages}"> 本についてのメモ：<br>
		<textarea name="eBookMemos" maxlength="100"
			placeholder="100文字以内でお願いします！">${eBookMemos}</textarea>
		<br> <input type="submit" name="action" value="変更する">
	</form>
	<form action=EditBookInfo method="POST">
		<input type="submit" name="action" value="やめる">
	</form>
</body>
</html>