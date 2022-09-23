<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<div id="container">
		<h2>글쓰기</h2>
		<form action="/insertBoard" method="post">
			<table class="tbl_reg">
				<tbody>
					<tr>
						<td>제목</td>
						<td><input type="text" name="title"></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td><input type="text" name="writer"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td>
							<textarea rows="10" cols="50"
							   name="content"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit">새글 등록</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>