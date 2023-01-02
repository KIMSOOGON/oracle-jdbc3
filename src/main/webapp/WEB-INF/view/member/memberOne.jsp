<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
	<style>
		#cloud {
			position : relative;
			top : 20px;
			left : 20px;
		}
		#home {
			position : relative;
			top : 130px;
			right : 20px;
		}
	</style>
	<link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css">
</head>
<body style="background-color:rgb(233,233,233)" data-aos="fade-up" data-aos-delay="500" data-aos-duration="1000">
	<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
	<script>AOS.init();</script>
		<!-- 메뉴 partial jsp 구성 -->
	<div>
		<jsp:include page="../../../inc/menu.jsp"></jsp:include>
	</div>
	
	<div id="cloud" class="row">
	
		<!-- 구름사진 -->
		<div class="container mt-5 col text-center">
			<img src="${pageContext.request.contextPath}/img/cloud.jpg" style="width:400px">
		</div>
		
		<!-- 회원정보 -->	
		<div id="home" class="col container mt-4">
			<div class="container shadow py-4 mx-4" style="width:350px">
				<!-- 홈버튼 -->
				<span style="float:left"><a class="btn btn-sm" href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/img/home.png" style="width:30px"></a></span>
				
				<!-- 내 정보 -->						
				<h3 class="text-center">${loginMember.memberId}님 <img src="${pageContext.request.contextPath}/img/user1.png" style="width:35px"></h3>
				<br>
				<br>
				<div>
					이름 : ${loginMember.memberName}
				</div>
				<br>
				<div>
					가입날짜 : ${loginMember.createdate}
				</div>
				<br>
				<br>
				<div>
					<a class="btn btn-sm btn-outline-dark" href="${pageContext.request.contextPath}/member/modifyMember">회원정보수정</a><img src="${pageContext.request.contextPath}/img/pw.png" style="width:25px">
					<a style="float:right" class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/member/removeMember">회원탈퇴</a>
				</div>
				<br>
				<br>
			</div>
		</div>
	</div>
</body>
</html>