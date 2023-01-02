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
			$('#removeMemberBtn').click(function(){
				// 패스워드 일치여부 확인
				if($('#memberPw').val() != $('#myPw').val()){
					$('#memberPwMsg').text('패스워드가 일치하지 않습니다');
					$('#memberPw').focus();
					return;
				} 
				$('#removeMemberForm').submit();
			});
		});
	</script>
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
		<div class="container mt-5 col text-center">
			<img src="${pageContext.request.contextPath}/img/cloud.jpg" style="width:400px">
		</div>
		<div id="home" class="col container mt-4">
			<div class="container shadow py-4 mx-4" style="width:700px">
				<!-- 홈버튼 -->
				<span style="float:left"><a class="btn btn-sm" href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/img/home.png" style="width:30px"></a></span>
				
				<h3 class="text-center">회원탈퇴</h3>
				<br>
				<div>
					<ul>
						<li>사용하고 계신 아이디는 탈퇴할 경우 <span style="font-weight:bold; color:red;">재사용 및 복구가 불가능</span>합니다.</li>
						<br>
						<li>탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제됩니다.</li>
						<br>
						<li>모든 내용을 확인하셨으며 탈퇴를 진행하고자 하시면 비밀번호를 입력해주세요.</li>
					</ul>
				</div>
				<br>
				<form action="${pageContext.request.contextPath}/member/removeMember" method="post" id="removeMemberForm">
					<div class="text-center">
					<input type="password" name="memberPw" id="memberPw" placeholder="패스워드를 입력하세요">
					<span id="memberPwMsg" class="msg"></span>
					<input type="hidden" name="myPw" id="myPw" value="${loginMember.memberPw}">
					</div>
					<div>
						<span style="float:left"><a class="btn btn-sm btn-outline-dark" href="${pageContext.request.contextPath}/member/memberOne">취소</a></span>
						<span style="float:right"><button class="btn btn-sm btn-outline-danger" type="button" id="removeMemberBtn">탈퇴</button></span>
					</div>
				</form>
				<br>
			</div>
		</div>
	</div>
</body>
</html>