<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<%@ include file="../layouts/header.jsp" %>


<sec:authentication property="principal.user" var="user" />
<%-- 개별 페이지 --%>
<h1>Profile</h1>
<div class="d-flex my-3 align-items-center">
<!-- 
	<div>
		<img src="/security/avatar/lg/${member.username }"/>
	</div>
	 -->
	<div class="ml-4">
		<div>
			사용자 ID: ${user.userid }
		</div>
		<div>
			username: ${user.username }
		</div>
		<div>
			가입일: 
			<fmt:formatDate value="${user.regDate}"
				pattern="yyyy-MM-dd HH:mm"/> 			
		</div>
	</div>	
</div>

<%@ include file="../layouts/footer.jsp" %>
