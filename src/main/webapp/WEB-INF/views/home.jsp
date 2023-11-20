<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="layouts/header.jsp"%>


<h1> HOME 팀 프로젝트 회원가입 만들기 </h1>

<div>
    <c:if test="${not empty userId}">
        <p>환영합니다, ${userNickname}님!</p>
        <!-- 추가적인 사용자 정보 표시 -->
    </c:if>
</div>


<%@include file="layouts/footer.jsp"%>