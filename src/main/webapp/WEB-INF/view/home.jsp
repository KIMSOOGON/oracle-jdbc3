<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		}
		#login {
			position : relative;
			top : 40px;
		}
	</style>
	<link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css">
</head>
<body style="background-color:rgb(233,233,233)" data-aos="fade-up" data-aos-delay="500" data-aos-duration="1000">
	<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
	<script>AOS.init();</script>
	
	<c:if test="${loginMember != null}">
		<!-- 메뉴 partial jsp 구성 -->
		<div>
			<jsp:include page="../../inc/menu.jsp"></jsp:include>
		</div>
	</c:if>
	
	<div id="cloud" class="row">
		<!-- 구름사진 -->
		<div class="container mt-5 col">
			<img src="${pageContext.request.contextPath}/img/cloud.jpg" style="width:400px">
		</div>
		
		<!-- 홈화면 -->
		<div id="home" class="col container mt-4">
			<div class="container shadow py-5 mx-4" style="width:400px; height:300px">
			
				<h1 class="text-center"><span style="color:#4ABFD3;">Goodee</span> <span style="color:#8D8D8D">Academy</span></h1>
				<!-- 로그인 상태일 경우, 로그아웃 및 글등록 버튼 표기 -->
				<div id="login" class="container">
					<c:if test="${loginMember != null}">
					<div>
						${loginMember.memberId}(${loginMember.memberName})님 환영합니다
					</div>
					<br>
						<a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/member/logout">로그아웃</a>&nbsp;
						<a class="btn btn-sm btn-outline-dark" href="${pageContext.request.contextPath}/member/memberOne">회원정보</a>&nbsp;
						<a class="btn btn-sm btn-outline-dark" href="${pageContext.request.contextPath}/board/boardList">게시판</a>
					</c:if>
					<!-- 로그아웃인 상태, 로그인 버튼 표기 (글등록은 로그인상태에서만 가능) -->
					<c:if test="${loginMember == null}">
						<a class="btn btn-sm btn-outline-dark" href="${pageContext.request.contextPath}/member/login">로그인</a>&nbsp;
						<a class="btn btn-sm btn-outline-dark" href="${pageContext.request.contextPath}/member/addMember">회원가입</a>
					</c:if>
				</div>
				<br>
				<br>
			</div>
		</div>
	</div>
</body>
</html>