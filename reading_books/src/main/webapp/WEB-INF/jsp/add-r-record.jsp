<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="icon" type="image/png" href="images/tako.png">
<title>読書記録の登録</title>
</head>
<body>
	<form action=AddRRecord method="GET">
	読書した日：<br>
	 <input type="date" id="today" name="aReadingDate" value="${aReadingDate}" min="1900-01-01" max="2100-12-31" required="required"> 
	 <br>
	読んだ本：<br>
	<select name="aBookName" required="required">
	<option value="" selected disabled>（選択してください）</option>
	    <c:forEach var="bookTitles" items="${bookTitles}">
	    	<option value="${bookTitles}"${bookTitles == aBookName ? 'selected' : ''}>${bookTitles}</option>
	    </c:forEach>
    </select><br> 
	ページ数：<br>
	<input type="number" name="aReadingPages" value="${aReadingPages}" min="1" max="9999" required="required"><br>
	メモ：<br>
	<textarea name="aReadingMemos" maxlength="30"
			placeholder="30文字以内でお願いします！">${aReadingMemos}</textarea><br>
		<input type="submit" name="action" value="登録する">
	</form>
	<form action=AddRRecord method="POST">
		<input type="submit" name="action" value="やめる">
	</form>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/js/today.js"></script>
</body>
</html>