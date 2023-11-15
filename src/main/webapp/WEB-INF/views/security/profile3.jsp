<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
</head>
<body>
    <h1>User Profile</h1>
    
    <table>
        <tr>
            <th>아이디</th>
            <td>${sessionScope.userId}</td>
        </tr>
        <tr>
            <th>닉네임</th>
            <td>${sessionScope.userNickname}</td>
        </tr>
        <tr>
            <th>이름</th>
            <td>${sessionScope.userName}</td>
        </tr>
        <tr>
            <th>이메일</th>
            <td>${sessionScope.userEmail}</td>
        </tr>
        <tr>
            <th>성별</th>
            <td>${sessionScope.userGender}</td>
        </tr>
        <tr>
            <th>생년월일</th>
            <td>${sessionScope.userBirthday}</td>
        </tr>
    </table>
    
    <a href="/security/logout">로그아웃</a>
</body>
</html>
