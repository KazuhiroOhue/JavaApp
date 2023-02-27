<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="icon" type="image/png" href="images/tako.png">
<title>読書記録の編集</title>
</head>
<body>
	<form action="EditRRecord" method="GET">
	読書した日：<br>
	 <input type="date" name="eReadingDate" value="${eReadingDate}" required="required"> 
	 <br>
	読んだ本：<br>
	<select name="eBookName">
	    <c:forEach var="bookTitles" items="${bookTitles}">
	    	<option value="${bookTitles}"${bookTitles == eBookName ? 'selected' : ''}>${bookTitles}</option>
	    </c:forEach>
    </select><br> 
	ページ数：
	<input type="number" name="eReadingPages" value="${eReadingPages}" min="1" max="9999" required="required"><br>
	メモ：<br>
		<textarea name="eReadingMemos" maxlength="30"
			placeholder="30文字以内でお願いします！">${eReadingMemos}</textarea><br>
			
		<input type="submit" name="action" value="変更する">
	</form>		
	<form action="DeleteRRecord" method="GET">
		<input type="submit" name="action" value="削除する">
	</form>
	<form action="EditRRecord" method="POST">
		<input type="submit" name="action" value="やめる">
	</form>
</body>
</html>