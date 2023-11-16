<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../layouts/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>LoginTest</title>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<style type="text/css">
html, div, body, h3 {
	margin: 0;
	padding: 0;
}

h3 {
	display: inline-block;
	padding: 0.6em;
}
</style>
</head>
<body>



	<!-- 로그인 폼 -->
	<div style="width: 500px" class="mx-auto">
		<h1 class="my-6">로그인</h1>

		<p class="text-end"></p>
		<hr class="hr-solid">

		<!-- 로그인 폼 코드 -->
		<c:if test="${param.error == 'true' }">
			<div class="error">사용자 ID 또는 비밀번호가 일치하지 않습니다.</div>
		</c:if>

		<c:if test="${param.error == 'login_required' }">
			<div class="error">로그인이 필요한 서비스입니다.</div>
		</c:if>

		<form action="/security/login" method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<div class="form-group">
				<label for="userid"> 사용자 ID:</label> <input type="text"
					name="userid" id="userid" class="form-control" />
			</div>

			<div class="form-group">
				<label for="password"> 비밀번호:</label> <input type="password"
					name="password" id="password" class="form-control" />
			</div>

			<!-- 로그인 유지 remember-me -->
			<div class="form-group form-check">
				<label class="form-check-label"> <input
					class="form-check-input" type="checkbox" name="remember-me" /> 로그인
					유지
				</label>
			</div>

			<button type="submit" class="btn btn-primary btn-block">
				<i class="fa-solid fa-right-to-bracket"></i> 로그인
			</button>

			<button type="button" onclick="location.href='signup'"
				class="btn btn-primary btn-block">
				<i class="fa-solid fa-right-to-bracket"></i> 회원가입
			</button>
		</form>
	</div>
	<!-- 네이버 로그인 버튼 -->
	<div id="naver_id_login" style="text-align: center; margin-top: 20px;">
		<a href="naverLogin"> <img width="223"
			src="${pageContext.request.contextPath}/resources/icons/naver_Bn_Green.png" />
		</a>
	</div>
	
	<!-- 
	<div id="naver_id_login" style="text-align: center">
		<a href="${url}"><img width="223"
			src="${pageContext.request.contextPath}/resources/icons/naver_Bn_Green.png" /></a>
	</div>
	<br>
	 -->
	
	

	<!-- 로그아웃 폼 -->
	<form id="userlogoutForm" action="/security/logout" method="post"></form>
	<%@include file="../layouts/footer.jsp"%>
</body>
</html>
