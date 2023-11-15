<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../layouts/header.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사용자 프로필</title>
</head>
<body>
    <div>
        <h1>사용자 프로필</h1>
        <c:choose>
            <c:when test="${not empty userInfo}">
                <p>환영합니다, ${userInfo.response.name}님</p>
                <p>이메일: ${userInfo.response.email}</p>
                <!-- 기타 사용자 정보 표시 -->
            </c:when>
            <c:otherwise>
                <p>사용자 정보를 불러오는 데 실패했습니다.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>