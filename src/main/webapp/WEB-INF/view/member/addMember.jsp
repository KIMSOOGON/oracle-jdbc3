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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#signinBtn').click(function(){			
				// 아이디 유효성 체크
				if($('#memberId').val().length < 2) {
					$('#memberIdMsg').text('아이디는 2자 이상 입력해주세요');
					$('#memberId').focus();
					return;
				} else {
					$('#memberIdMsg').text('');
				}
				// 패스워드 유효성 체크
				if($('#memberPw').val().length < 4) {
					$('#memberPwMsg').text('패스워드는 4자 이상 입력해주세요');
					$('#memberPw').focus();
					return;
				} else {
					$('#memberPwMsg').text('');
				}
				// 패스워드 확인
				if($('#memberPwCk').val() != $('#memberPw').val()) {
					$('#memberPwCkMsg').text('패스워드가 일치하지 않습니다');
					$('#memberPwCk').focus();
					return;
				} else {
					$('#memberPwCkMsg').text('');
				}
				// 이름 유효성 체크
				if($('#memberName').val() == '') {
					$('#memberNameMsg').text('필수입력 정보입니다');
					$('#memberName').focus();
					return;
				} else {
					$('#memberNameMsg').text('');
				}
				
				$('#signinForm').submit();
			});
		});
	</script>
	<link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css">
</head>
<body style="background-color:rgb(233,233,233)" data-aos="fade-up" data-aos-delay="500" data-aos-duration="1000">
	<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
	<script>AOS.init();</script>
	<div id="cloud" class="row">
		<div class="container mt-5 col">
			<img src="${pageContext.request.contextPath}/img/cloud.jpg" style="width:400px">
		</div>
		<div id="home" class="col container mt-4">
			<div class="container shadow py-4 px-3" style="width:350px">
				<span style="float:left"><a class="btn btn-sm" href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/img/home.png" style="width:30px"></a></span>
				<h3 class="text-center">회원가입</h3>
				<br>
				<form action="${pageContext.request.contextPath}/member/addMember" method="post" id="signinForm">
					<table class="table table-hover">
						<tr>
							<td>
								<input type="text" name="memberId" id="memberId" placeholder="아이디" class="container">
								<span id="memberIdMsg" class="msg"></span>
								<c:if test="${msg != null}">
									<span class="msg">중복된 아이디가 존재합니다</span>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" name="memberPw" id="memberPw" placeholder="패스워드" class="container">
								<span id="memberPwMsg" class="msg"></span>
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" name="memberPwCk" id="memberPwCk" placeholder="패스워드 확인" class="container">
								<span id="memberPwCkMsg" class="msg"></span>
							</td>
						</tr>
						<tr>
							<td>
								<input type="text" name="memberName" id="memberName" placeholder="이름" class="container">
								<span id="memberNameMsg" class="msg"></span>
							</td>
						</tr>
					</table>
					<div class="text-center">
						<button class="btn btn-sm btn-outline-dark" type="button" id="signinBtn">회원가입 </button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>