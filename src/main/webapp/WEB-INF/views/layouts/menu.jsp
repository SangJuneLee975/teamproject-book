<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<nav
	class="navbar navbar-expand-md bg-primary navbar-dark sticky-top mb-5">


	<a class="navbar-brand" href="/"> <i class="fa-solid fa-house"></i>
		HOME
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#collapsibleNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="collapsibleNavbar">




		<!-- 우측 메뉴 -->
		<ul class="navbar-nav ml-auto">

			<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal.username" var="username" />

				<%-- 로그인 된 상태 --%>


				<li class="nav-item"><a class="nav-link"
					href="/security/update"> <i class="fa-solid fa-user-plus"></i>
						회원정보수정
				</a></li>

				<li class="nav-item"><a class="nav-link"
					href="/security/profile"> <img
						src="/security/avatar/sm/${username}" class="avatar-sm" />
						${username}
				</a></li>


				<li class="nav-item"><a class="nav-link logout-link" href="#">
						<i class="fa-solid fa-right-from-bracket"></i> 로그아웃
				</a></li>
			</sec:authorize>
			<sec:authorize access="isAnonymous()">

				<%-- 로그아웃 된 상태 --%>
				<li class="nav-item"><a class="nav-link" href="/security/login">
						</i> 로그인
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/security/signup"> <i class="fa-solid fa-user-plus"></i>
						회원가입
				</a></li>
			</sec:authorize>
		</ul>
	</div>
</nav>

<form id="logoutForm" action="/security/logout" method="post">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>

<script>
	$(document).ready(function() {
		$('.logout-link').click(function(e) {
			e.preventDefault();
			$('#logoutForm').submit();
		});
	});
</script>

