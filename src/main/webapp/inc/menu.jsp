<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
	<!-- include창 -->
	<div class="container bg-white p-3">
		<a class="btn btn-sm btn-outline-white" style="float:left" href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/img/home.png" style="width:30px"><span style="color:#4ABFD3;">&nbsp;Goodee</span> <span style="color:#8D8D8D">Academy</span></a>&nbsp;||
		<a class="btn btn-sm btn-outline-dark" href="${pageContext.request.contextPath}/member/logout">LOG-OUT</a>&nbsp;
		<a class="btn btn-sm btn-outline-dark" href="${pageContext.request.contextPath}/member/memberOne">내정보</a>&nbsp;
		<a class="btn btn-sm btn-outline-info" href="${pageContext.request.contextPath}/board/boardList"><span style="color:#4ABFD3;">Goodee</span> <span style="color:#8D8D8D">Gallery</span></a>&nbsp;
	</div>
</body>
</html>