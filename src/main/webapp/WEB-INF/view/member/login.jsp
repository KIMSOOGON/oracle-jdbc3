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
		.msg {color : #FF0000;}
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
	<!-- J쿼리 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#loginBtn').click(function(){
				// 아이디 유효성 체크
				if($('#memberId').val() == '') {
					$('#memberIdMsg').text('아이디를 입력해주세요');
					$('#memberId').focus();
					return;
				} else {
					$('#memberIdMsg').text('');
				}
				// 패스워드 유효성 체크
				if($('#memberPw').val() == '') {
					$('#memberPwMsg').text('패스워드를 입력해주세요');
					$('#memberPw').focus();
					return;
				} else {
					$('#memberPwMsg').text('');
				}
				
				$('#loginForm').submit();
			});
		});
	</script>
	<link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css">
</head>
<body style="background-color:rgb(233,233,233)" data-aos="fade-up" data-aos-delay="500" data-aos-duration="1000">
	<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
	<script>AOS.init();</script>
	<div id="cloud" class="row">
	
		<!-- 구름사진 -->
		<div class="container mt-5 col text-center">
			<img src="${pageContext.request.contextPath}/img/cloud.jpg" style="width:400px">
		</div>
		
		<!-- 로그인화면 -->
		<div id="home" class="col container mt-4">
			<div class="container shadow py-4 mx-4" style="width:350px">
			
				<!-- 홈버튼 -->
				<span style="float:left"><a class="btn btn-sm" href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/img/home.png" style="width:30px"></a></span>
				<h3 class="text-center">로그인</h3>
				<br>
				
				<!-- 로그인폼 -->				
				<form id="loginForm" action="${pageContext.request.contextPath}/member/login" method="post">
					<table class="table table-hover">
						<tr>
							<td>
								<input type="text" name="memberId" id="memberId" class="container" placeholder="아이디">
								<span id="memberIdMsg" class="msg"></span>
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" name="memberPw" id="memberPw" class="container" placeholder="패스워드">
								<span id="memberPwMsg" class="msg"></span>
							</td>
						</tr>
					</table>
					<div>
						<c:if test="${checkLogin == false}">
							<span class="msg">아이디나 패스워드를 잘못입력했습니다</span>
						</c:if>
					</div>
					<div class="text-center">
						<button class="btn btn-sm btn-outline-dark" type="button" id="loginBtn">Log-In</button>
					</div>
				</form>
				<br>
				<div>
				<span style="float:right">
					<a class="btn btn-sm btn-outline-dark" href="${pageContext.request.contextPath}/member/addMember">회원가입</a>
				</span>
				</div>
				<br>
			</div>
		</div>
	</div>
</body>
</html>