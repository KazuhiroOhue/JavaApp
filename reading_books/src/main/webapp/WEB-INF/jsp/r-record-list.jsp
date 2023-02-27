<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="icon" type="image/png" href="images/tako.png">
<title>読書記録一覧</title>
</head>
<body>
	<h2>読書記録一覧</h2>
	<div style="height:400px; width:600px; overflow-y:scroll;">
		<table border="1">
			<tr>
				<th>読書した日</th>
				<th>読んだ本</th>
				<th>ページ数</th>
				<th>メモ</th>
			</tr>
			<c:forEach var="r" items="${rRecordList}">
				<tr>
					<td><a>${r.readingDate}</a></td>
					<td>
						<a href="EditRRecord?readingId=${r.readingId}">${r.bookName}</a>
					</td>
					<td><a>${r.readPages}ページ</a></td>
					<td><a>${r.readingMemos}</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<c:if test="${fn:length(rRecordList) == 0}" >
		<p>まだ記録はありません</p><br>
	</c:if>
		
	<form action="AddRRecord" method="GET">
		<input type="submit" name="action" value="読書記録の新規登録">
	</form>
	<form action="RRecordList" method="POST">
		<input type="submit" value="一覧にもどる">
	</form>
</body>
</html>