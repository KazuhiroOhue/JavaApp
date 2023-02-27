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
<title>新しい本の登録</title>
</head>
<body>
	<form action="AddBookInfo" method="GET">
		タイトル：<br>
		 <input type="text" name="aBookName" class="bookName"
			value="${aBookName}" maxlength="50" required="required"><br>
		購入日：<br>
		 <input type="date" id="today" name="aPurchaseDate"
			value="${aPurchaseDate}"  min="1900-01-01" max="2100-12-31" required="required"> <br> 
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
		ジャンル：<br> 
		<select name="aBookCategory" required="required">
			<option value="" selected disabled>（選択してください）</option>
			<c:forEach var="category" items="${category}">
	    		<option value="${category}"${category == aBookCategory ? 'selected' : ''}>${category}</option>
	    	</c:forEach>
		</select> <br> ページ数：<br> <input type="number" name="aTotalPages"
			value="${aTotalPages}" min="1" max="9999"> 本についてのメモ：<br>
		<textarea name="aBookMemos" maxlength="100"
			placeholder="100文字以内でお願いします！">${aBookMemos}</textarea>
		<br> <input type="submit" name="action" value="登録する">
	</form>
	<form action=AddBookInfo method="POST">
		<input type="submit" name="action" value="やめる">
	</form>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/js/today.js"></script>
</body>
</html>