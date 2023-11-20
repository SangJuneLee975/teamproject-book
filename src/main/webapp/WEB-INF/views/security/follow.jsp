<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
    prefix="sec"%>
<%@ page session="false"%>

<sec:authentication property="principal" var="userId" />

<!-- 사용자 목록 보여주기 -->
<div class="user-list">
    <c:forEach var="follower" items="${followers}">
        <div class="follower">
            <span>${follower.username}</span>
            <!--  버튼  -->
        </div>
    </c:forEach>
</div>