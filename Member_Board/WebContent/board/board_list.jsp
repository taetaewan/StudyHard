<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = (String) session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board list jsp</title>
</head>
<body>
	<div align="center">
		<h3>[자유 게시판]</h3>
		<table>
			<tr align="right">
				<td>
					<%
						if (id != null && id.equals("admin")) {
					%> <input type="button"
					value="회원 관리" onclick="location.href='memberListAction.me'">
					<%
						}
					%> 
					<input type="button" value="로그아웃" onclick="location.href='memberLogout.me'"> 
					<input type="button" value="글쓰기" onclick="location.href='boardWrite.bo'">
					<input type="button" value="회원 정보 수정" onclick="fnModify('<%=id%>')">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>