<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="stylesheet" href="css/pie_chart.css" type="text/css">
	<link rel="icon" type="image/png" href="images/tako.png">
<title>メインメニュー</title>
<script src="/reading_books/js/sample.js"></script>
</head>
<body>
	<c:if test="${not empty msg}">
	<p><c:out value="${msg}" /></p>
	</c:if>
	<h2>メインメニュー</h2>
	<form action="AddBookInfo" method="GET">
		<input type="submit" name="action" value="新しい本の登録">
	</form>
	<c:choose>
		<c:when test="${fn:length(bookList) == 0}">
		ここでは、持っている本を登録して、読書記録をつけることができます。<br>
		"本を登録する"ボタンをクリックしてみて下さい！<br>
		</c:when>
		<c:when test="${fn:length(bookList) != 0}">
			<form action="RRecordList" method="GET">
				<input type="submit" value="読書記録一覧へ">
			</form>
			<table border="1">
				<caption>本棚の中</caption>
				<tr>
					<th>タイトル</th>
					<th>ジャンル</th>
					<th></th>
					<th>総ページ数</th>
					<th>読んだページ数</th>
					<th>達成率</th>
				</tr>
				<c:forEach var="b" items="${bookList}">
					<tr>
						<td><a href="BookInfo?bookId=${b.bookId}">${b.bookName}</a></td>

						<td><a>${b.bookCategory}</a></td>
						<td><img src="images/${b.imagePath}" width="100"></td>
						<td><a>${b.totalPages}ページ</a></td>
						<td><a>${b.readPages}ページ</a></td>
						<td><a>
							<c:choose>
							  <c:when test="${b.percentage >= 100.0}">
							    <div class="pie animate" style="--p:100;--c:rgb(255, 0, 0);--b:10px"> 100%</div>
							  </c:when>
							  <c:otherwise>
							    <div class="pie animate" style="--p:${b.percentage};--c:rgb(255, 128, 0);--b:10px"> ${b.percentage}%</div>
							  </c:otherwise>
							</c:choose>
						</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>

	<form action="Login" method="GET">
		<input type="submit" value="ログアウトする">
	</form>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/js/no-browser-back.js"></script>
</body>
</html>