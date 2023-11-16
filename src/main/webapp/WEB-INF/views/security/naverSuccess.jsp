<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
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
    <script type="text/javascript">
        $(document).ready(function() {
            // userInfo 객체에서 사용자 정보 파싱 및 표시
            var userInfo = ${userInfo};
            if (userInfo) {
                var userName = userInfo.response.name;
                var userEmail = userInfo.response.email;

                $("#name").html("환영합니다. " + userName + "님");
                $("#email").html(userEmail);

                // 사용자 정보를 가져온 후, 리다이렉트 수행
                window.location.href = "/";
            } else {
                $("#name").html("사용자 정보를 불러오는 데 실패했습니다.");
            }
        });
    </script>
</head>
<body>
    <div style="background-color: #15a181; width: 100%; height: 50px; text-align: center; color: white;">
        <h3>SIST Naver_Login Success</h3>
    </div>
    <c:choose>
        <c:when test="${not empty userInfo}">
            <!-- userInfo에서 사용자 정보 파싱 및 표시 -->
            <h2 style="text-align: center" id="name">환영합니다. ${userInfo.response.name}님</h2>
            <h4 style="text-align: center" id="email">${userInfo.response.email}</h4>
        </c:when>
        <c:otherwise>
            <p>사용자 정보를 불러오는 데 실패했습니다.</p>
        </c:otherwise>
    </c:choose>
    <br>
</body>
</html>