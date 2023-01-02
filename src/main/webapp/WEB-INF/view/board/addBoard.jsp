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
		.msg {color : #FF0000;}
	</style>
	<!-- J쿼리 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			$('#addBoardBtn').click(function(){
				// 제목 유효성 체크
				if($('#boardTitle').val() == '') {
					$('#boardTitleMsg').text('제목을 입력해주세요');
					$('#boardTitle').focus();
					return;
				} else {
					$('#boardTitleMsg').text('');
				}
				// 내용 유효성 체크
				if($('#boardContent').val() == '') {
					$('#boardContentMsg').text('내용을 입력해주세요');
					$('#boardContent').focus();
					return;
				} else {
					$('#boardContentMsg').text('');
				}
				
				$('#addBoardForm').submit();
			});
		})
	</script>
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
		<div class="container mt-5 col text-center" style="position:relative; right:150px">
			<img src="${pageContext.request.contextPath}/img/cloud3.jpg" style="width:400px">
		</div>
		
		<!-- 게시글 작성 -->
		<div class="col container mt-4" style="position:relative; right:150px">
			<div class="shadow p-5">
			<h1 class="text-center mt-5"><span style="color:#4ABFD3;">Goodee</span> <span style="color:#8D8D8D">Gallery</span></h1>
			<br>
			<p class="text-center">- 게시글 작성 -</p>
				<form action="${pageContext.request.contextPath}/board/addBoard" method="post" id="addBoardForm">
					<table class="table table-hover">
						<tr>
							<td>
								<input type="text" name="boardTitle" placeholder="제목을 입력해주세요" id="boardTitle" style="width:400px">
								
							</td>
							<td>작성자 : <input type="text" name="memberId" value="${loginMember.memberId}" readonly="readonly" style="width:100px"></td>
						</tr>
						<tr>
							<td colspan="2"><span id="boardTitleMsg" class="msg"></span></td>
						</tr>
						<tr>
							<td colspan="2"><span id="boardContentMsg" class="msg"></span></td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea rows="10" cols="80" name="boardContent" placeholder="내용을 입력해주세요" id="boardContent"></textarea>
							</td>
						</tr>
					</table>
					<div>
						<a class="btn btn-sm btn-outline-dark" href="${pageContext.request.contextPath}/board/boardList">취소</a>
						<button class="btn btn-sm btn-outline-dark" type="button" id="addBoardBtn">등록</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>