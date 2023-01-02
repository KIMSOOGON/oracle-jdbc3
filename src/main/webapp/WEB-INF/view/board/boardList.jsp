<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			// 페이지 당 출력항목갯수 변경 시 form 바로 submit하게끔 설정
			$('#rowPerPage').change(function() {
				$('#pageForm').submit();
			})
		})
	</script>
	<link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css">
	<style>
		select {
			font-size:13px;
		}
		a {
			font-size:13px;
		}
		.font {
			font-size:13px;
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
		<div class="container mt-4 col-3" style="position:relative; top:100px; right:100px">
			<img src="${pageContext.request.contextPath}/img/cloud1.jpg" style="width:400px">
		</div>
		
		<div class="container col-9 shadow pb-4 mt-3" style="position:relative; right:200px">
			<!-- 타이틀 -->
			<h1 class="text-center mt-5"><span style="color:#4ABFD3;">Goodee</span> <span style="color:#8D8D8D">Gallery</span></h1>
			<br>
			<br>
			<!-- 검색창 구현 -->
			<div class="text-center">
				<form action="${pageContext.request.contextPath}/board/boardList" method="get">
					<select name="search">
					<c:if test="${empty search || search eq 'board_title'}">
						<option value="board_title" selected="selected">제목</option>
						<option value="board_content">내용</option>
						<option value="member_id">작성자</option>
					</c:if>
					<c:if test="${search eq 'board_content'}">
						<option value="board_title">제목</option>
						<option value="board_content" selected="selected">내용</option>
						<option value="member_id">작성자</option>
					</c:if>
					<c:if test="${search eq 'member_id'}">
						<option value="board_title">제목</option>
						<option value="board_content">내용</option>
						<option value="member_id" selected="selected">작성자</option>
					</c:if>
					</select>
					<input type="hidden" name="rowPerPage" value="${rowPerPage}">
					<input style="background-color:#FFFFF9; width:300px" class="border border-success border-3 font" type="text" name="word" placeholder="검색">
					<button type="submit" class="btn btn-sm btn-light"><img src="${pageContext.request.contextPath}/img/search.png" style="width:25px;"></button>
				</form>
				<c:if test="${word != null}">
					<div>'${word}' 검색결과, 총 '${ttlCntBoard}'개의 검색결과가 있습니다.</div>
				</c:if>
			</div>
		
			
			<a style="float:right" class="btn btn-sm btn-outline-dark" href="${pageContext.request.contextPath}/board/addBoard">글쓰기<img src="${pageContext.request.contextPath}/img/write.png" style="width:25px;"></a>
			<!-- n개씩 보기(정렬) -->
			<form id="pageForm" method="get" action="${pageContext.request.contextPath}/board/boardList">
				<c:if test="${word != null}">
					<input type="hidden" name="word" value="${word}">
					<input type="hidden" name="search" value="${search}">
				</c:if>
				<select name="rowPerPage" id="rowPerPage">
					<c:if test="${rowPerPage == 10}">
						<option value="10" selected="selected">10</option>
						<option value="20">20</option>
						<option value="30">30</option>
					</c:if>
					<c:if test="${rowPerPage == 20}">
						<option value="10">10</option>
						<option value="20" selected="selected">20</option>
						<option value="30">30</option>
					</c:if>
					<c:if test="${rowPerPage == 30}">
						<option value="10">10</option>
						<option value="20">20</option>
						<option value="30" selected="selected">30</option>
					</c:if>
				</select><span class="font">개씩 보기</span>
			</form>
			<br>
			<!-- 게시글목록 -->
			<table class="table table-hover bg-light font">
				<tr class="text-center" style="background-color:#C7D3ED">
					<th style="width:50px">번호</th>
					<th style="width:200px">제목</th>
					<th style="width:50px">글쓴이</th>
					<th style="width:70px">작성일</th>
				</tr>
				<c:forEach var="b" items="${boardList}">
					<tr>
						<td class="text-center">${b.boardNo}</td>
						<td>
							<a class="btn font" href="${pageContext.request.contextPath}/board/boardOne?boardNo=${b.boardNo}">
								${b.boardTitle}
							</a>
						</td>
						<td class="text-center">${b.memberId}</td>
						<td class="text-center">${b.createdate}</td>
					</tr>
				</c:forEach>
			</table>
		
			<!-- 페이징처리 -->
			<div class="container text-center border border-dark" style="width:350px">
				<!-- 검색값이 없을 때 -->
				<c:if test="${word == null}">
					<a class="btn btn-sm" href="${pageContext.request.contextPath}/board/boardList?rowPerPage=${rowPerPage}&currentPage=1&rowPerPage=${rowPerPage}">◀◀</a>
					<c:if test="${currentPage > 1}"><!-- 이전 -->
						<a class="btn btn-sm" href="${pageContext.request.contextPath}/board/boardList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}&rowPerPage=${rowPerPage}">이전</a>
					</c:if>		
					<span>${currentPage} / ${lastPage}</span>
					<c:if test="${currentPage < lastPage}"><!-- 다음 -->
						<a class="btn btn-sm" href="${pageContext.request.contextPath}/board/boardList?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}&rowPerPage=${rowPerPage}">다음</a>
					</c:if>
					<a class="btn btn-sm" href="${pageContext.request.contextPath}/board/boardList?rowPerPage=${rowPerPage}&currentPage=${lastPage}&rowPerPage=${rowPerPage}">▶▶</a>
				</c:if>
				<!-- 검색값이 있을 때 search와 word도 함께넘겨주도록 -->
				<c:if test="${word != null}">
					<a class="btn btn-sm" href="${pageContext.request.contextPath}/board/boardList?rowPerPage=${rowPerPage}&currentPage=1&rowPerPage=${rowPerPage}&word=${word}&search=${search}">◀◀</a>
					<c:if test="${currentPage > 1}"><!-- 이전 -->
						<a class="btn btn-sm" href="${pageContext.request.contextPath}/board/boardList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}&rowPerPage=${rowPerPage}&word=${word}&search=${search}">이전</a>
					</c:if>		
					<span>${currentPage} / ${lastPage}</span>
					<c:if test="${currentPage < lastPage}"><!-- 다음 -->
						<a class="btn btn-sm" href="${pageContext.request.contextPath}/board/boardList?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}&rowPerPage=${rowPerPage}&word=${word}&search=${search}">다음</a>
					</c:if>
					<a class="btn btn-sm" href="${pageContext.request.contextPath}/board/boardList?rowPerPage=${rowPerPage}&currentPage=${lastPage}&rowPerPage=${rowPerPage}&word=${word}&search=${search}">▶▶</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>