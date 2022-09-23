<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 보기</title>
<link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<div id="container">
		<h2>글 상세보기</h2>
		<form action="/updateBoard" method="post">
			<!-- 수정시엔 기본키를 컨트롤러에 보내줌 -->
			<input type="hidden" name="seq" value='<c:out value="${board.seq}"/>'>
			<table class="tbl_reg">
				<tbody>
					<tr>
						<td>제목</td>
						<td><input type="text" name="title" value="${board.title}"></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td><input type="text" name="writer" value="${board.writer}"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td>
							<textarea rows="10" cols="50"
							   name="content"><c:out value="${board.content}"/></textarea>
						</td>
					</tr>
					<tr>
						<td>작성일</td>
						<td><fmt:formatDate value="${board.createDate}" 
						            pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
					<tr>
						<td>조회수</td>
						<td><input type="text" name="cnt" value="${board.cnt}"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit">수정</button>
							<a href="/deleteBoard?seq=<c:out value="${board.seq}"/>"
							   onclick="return confirm('정말로 삭제하시겠습니까?')">
								<button type="button">삭제</button>
							</a>
							<a href="/getBoardList">
								<button type="button">목록</button>
							</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>