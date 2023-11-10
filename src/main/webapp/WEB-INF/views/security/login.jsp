<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../layouts/header.jsp"%>


<div style="width: 500px" class="mx-auto">
	<h1 class="my-6">로그인</h1>

	<p class="text-end"></p>
	<hr class="hr-solid">

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
			<label for="userpassword"> 비밀번호:</label> <input type="password"
				name="userpassword" id="userpassword" class="form-control" />
		</div>


		<!--  로그인 유지 remember-me -->
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


<form id="userlogoutForm" action="/security/logout" method="post"></form>

<%@include file="../layouts/footer.jsp"%>
