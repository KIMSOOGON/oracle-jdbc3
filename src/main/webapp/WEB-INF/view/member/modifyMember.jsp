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
			$('#modifyMemberBtn').click(function(){
				// 기존 패스워드 유효성 체크
				if($('#memberPw').val() != $('#myPw').val()) {
					$('#memberPwMsg').text('기존 패스워드를 잘못입력하셨습니다');
					$('#memberPw').focus();
					return;
				} else {
					$('#memberPwMsg').text('');
				}
				// 패스워드 유효성 체크
				if($('#newMemberPw').val().length < 4) {
					$('#newMemberPwMsg').text('패스워드는 4자 이상 입력해주세요');
					$('#newMemberPw').focus();
					return;
				} else {
					$('#newMemberPwMsg').text('');
				}
				// 패스워드 확인
				if($('#newMemberPw').val() != $('#newMemberPwCk').val()) {
					$('#newMemberPwCkMsg').text('새 패스워드가 일치하지 않습니다');
					$('#newMemberPwCk').focus();
					return;
				} else {
					$('#newMemberPwCkMsg').text('');
				}
				// 이름 유효성 체크
				if($('#memberName').val() == '') {
					$('#memberNameMsg').text('필수입력 정보입니다');
					$('#memberName').focus();
					return;
				} else {
					$('#memberNameMsg').text('');
				}
				
				$('#modifyMemberForm').submit();
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
		<!-- 구름사진 -->
		<div class="container mt-5 col text-center">
			<img src="${pageContext.request.contextPath}/img/cloud.jpg" style="width:400px">
		</div>
		
		<!-- 내정보수정 화면 -->
		<div id="home" class="col container mt-4">
			<div class="container shadow py-4 mx-4" style="width:500px">
				<!-- 홈버튼 -->
				<div style="float:left"><a class="btn btn-sm" href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/img/home.png" style="width:30px"></a></div>
				<br>
				
				<div class="text-center">
					<h3>${loginMember.memberId}님의 회원정보 수정</h3>
					<p>
						-비밀번호 및 이름 변경-
					</p>
					<br>
				</div>
				
				<form id="modifyMemberForm" action="${pageContext.request.contextPath}/member/modifyMember" method="post">
					<div class="row">
						<table class="col-8">
							<tr>
								<td class="container mx-5">[My Password]</td>
							</tr>
							<tr>
								<td>
									<input type="password" name="memberPw" id="memberPw" placeholder="기존 패스워드" class="container mx-2 mb-3 w-75">
									<span id="memberPwMsg" class="msg"></span>
									<input type="hidden" name="myPw" id="myPw" value="${loginMember.memberPw}">
								</td>
							</tr>
							<tr>
								<td class="container mx-5 my-1">[New Password]</td>
							</tr>
							<tr>
								<td>
									<input type="password" name="newMemberPw" id="newMemberPw" placeholder="변경할 패스워드" class="container mx-2 w-75">
									<span id="newMemberPwMsg" class="msg"></span>
								</td>
							</tr>
							<tr>
								<td>
									<input type="password" name="newMemberPwCk" id="newMemberPwCk" placeholder="변경할 패스워드 확인" class="container mx-2 mb-3 w-75">
									<span id="newMemberPwCkMsg" class="msg"></span>
								</td>
							</tr>
							<tr>
								<td class="container mx-5 mt-4">[My Name]</td>
							</tr>
							<tr>
								<td>
									<input type="text" name="memberName" value="${loginMember.memberName}" id="memberName" placeholder="바꿀 이름" class="container mx-2 w-75">
									<span id="memberNameMsg" class="msg"></span>
								</td>
							</tr>
						</table>
						<div class="container col-4 mt-5 text-center">
							<br><br><br>
							<div>
								<a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/member/memberOne">취소</a>&nbsp;
								<button class="btn btn-sm btn-outline-primary" type="button" id="modifyMemberBtn">수정</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
		
</body>
</html>