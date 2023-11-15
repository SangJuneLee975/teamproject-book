<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<%@ include file="../layouts/header.jsp"%>

<sec:authentication property="principal.user" var="user" />



<div class="wrap">
	<div class="greenContainer">
		<div>
			<div class="grade">프로필 사진(dicebear) 넣을 공간</div>

		</div>
		<div class="modify">i</div>
	</div>

	<!-- 사용자 정보 섹션 -->
	<div class="d-flex my-3 align-items-center">
		<div class="ml-4">
			<div>사용자 ID: ${user.userid}</div>
			<div>username: ${user.username}</div>
			<div>
				가입일:
				<fmt:formatDate value="${user.regDate}" pattern="yyyy-MM-dd HH:mm" />
			</div>
			닉네임 또는 사용자 이름 표시, 프로필 사진 표시, 회원정보 수정 기능 구현, SNS서평 공유, (우선순위 낮음)

		</div>
	</div>



</div>

<div class="listContainer">
	<a class="nav-link" href="/security/follow">
		<div class="icon">ii</div>
		<div class="text">
			팔로우<span class="circle"></span>
		</div>
		<div class="right"></div>
	</a>


	<!-- 
		<%-- 로그아웃 된 상태 --%>
				<li class="nav-item">
					<a class="nav-link" href="/security/login"> 
					</i> 로그인
					</a>
				</li>	
	
	
   	<li class="nav-item">
					<a class="nav-link" href="/security/profile"> 
					 -->

	<a href="#" class="item">
		<div class="icon">ii</div>
		<div class="text">책 평가 및 리뷰 확인 기능</div>
		<div class="right">></div>
	</a> <a href="#" class="item">
		<div class="icon">ii</div>
		<div class="text">알림 기능 구현</div>
		<div class="right"></div>
	</a>
	<div class="right">></div>
</div>



<div class="listContainer">
	<a href="#" class="item">
		<div class="icon">ii</div>
		<div class="text">
			<span>Empty</span> <span class="smallLight"> <span></span> <span>Empty</span>
			</span>
		</div>
		<div class="right">
			<span class="blct">175 BLCT</span> >
		</div>
	</a> <a href="#" class="item">
		<div class="icon">ii</div>
		<div class="text">알림</div>
		<div class="right">></div>
	</a> <a href="#" class="item">
		<div class="icon">ii</div>
		<div class="text">설정</div>
		<div class="right">></div>
	</a>
</div>

<div class="infoContainer">
	<a href="#" class="item">
		<div>icon</div>
		<div>공지사항</div>
	</a> <a href="#" class="item">
		<div>icos</div>
		<div>이용안내</div>
	</a> <a href="#" class="item">
		<div>icon</div>
		<div>고객센터</div>
	</a>
</div>
</div>

<!-- 추가적인 사용자 정보 및 기능 -->
<!-- 기존 코드에서의 item, listContainer, infoContainer 섹션들을 여기에 삽입 -->

</div>

<link href="/resources/css/mypage.css" rel="stylesheet" type="text/css">


<%@ include file="../layouts/footer.jsp"%>
