<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css">
	<style>
		body {
			font-size:14px;
		}
	</style>
</head>
<body style="background-color:rgb(233,233,233)" data-aos="fade-up" data-aos-delay="500" data-aos-duration="1000">
	<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
	<script>AOS.init();</script>
	<!-- 메뉴 partial jsp 구성 -->
	<div>
		<jsp:include page="../../../inc/menu.jsp"></jsp:include>
	</div>
	
	<div class="row">
	
		<!-- 구름사진 -->
		<div class="container mt-5 col text-center" style="position:relative; right:100px">
			<img src="${pageContext.request.contextPath}/img/cloud1.jpg" style="width:400px">
		</div>
		
		<!-- 게시글 상세 -->
		<div class="col container mt-4" style="position:relative; right:100px">
			<div class="shadow p-5">
				<h1 class="text-center mt-5"><span style="color:#4ABFD3;">Goodee</span> <span style="color:#8D8D8D">Gallery</span></h1>
				<br><br>
				<div>
					<div><!-- 제목 -->
						${boardOne.boardTitle}
					</div>
					<br>
					<hr>
					<br>
					<p class="container bg-light"><!-- 내용 -->
						${boardOne.boardContent}
					</p>
					<br>
					<hr>
					<br>
					<div><!-- 아이디 -->
						${boardOne.memberId}
						<!-- 생성날짜 -->
						<span style="float:right">${boardOne.createdate}</span>
					</div>				
				</div>
				<br><br>
				<div>
					<!-- 본인이 작성한 글일 경우, 수정 및 삭제 가능 -->
					<c:if test="${loginMember.memberId == boardOne.memberId}">
						<form action="${pageContext.request.contextPath}/board/removeBoard" method="post">
							<input type="hidden" name="boardNo" value="${boardOne.boardNo}">
							<a class="btn btn-sm btn-outline-success" href="${pageContext.request.contextPath}/board/modifyBoard?boardNo=${boardOne.boardNo}">수정</a>
							<button class="btn btn-sm btn-outline-danger" type="submit">삭제</button>
						</form>
					</c:if>
					<a class="btn btn-sm btn-outline-dark" style="float:right" href="${pageContext.request.contextPath}/board/boardList">목록보기</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>