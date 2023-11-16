<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page session="false"%>

<html>
<head>
<!-- CSRF 토큰과 헤더 -->
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<sec:authentication property="principal.user" var="user" />

	<!-- 팔로우 모달창 -->
	<div id="followModal" class="modal" style="display: block;">
		<div class="modal-content">
			<span class="close" onclick="closeFollowModal()">&times;</span>
			<h2>사용자 목록</h2>
			<div id="userList">
				<!-- 사용자 목록이 여기에 동적으로 로드됩니다 -->
			</div>
		</div>
	</div>

	<script>
		function closeFollowModal() {
			window.location.href = "/security/profile"; // 프로필 페이지로 리다이렉트
		}

		$(document).ready(function() {
			console.log("Page loaded");
			loadUsers();
		});

		function loadUsers() {
			console.log("Sending AJAX request");
			$.ajax({
				url : '/api/users',
				method : 'GET',
				success : function(data) {
					console.log("Response received", data);
					var userList = document.getElementById('userList');
					userList.innerHTML = '';
					data.forEach(function(user) {
						var userDiv = document.createElement('div');
						userDiv.id = 'user-' + user.userid;
						userDiv.innerHTML = user.username
								+ ' <button onclick="toggleFollow(\''
								+ user.userid + '\')">팔로우</button>';
						userList.appendChild(userDiv);
					});
				},
				error : function(error) {
					console.log('Error fetching user list:', error);
				}
			});
		}

		function toggleFollow(userId) {
			var token = $("meta[name='_csrf']").attr("content");
		    var header = $("meta[name='_csrf_header']").attr("content");
		    
			$.ajax({
				url : '/security/toggleFollow',
				method : 'POST',
				data : {
					userId : userId
				},
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				success : function(response) {
					console.log('Follow toggle success:', response);
					var userDiv = document.getElementById('user-' + userId);
					var button = userDiv.querySelector('button');
					button.innerText = button.innerText === '팔로우' ? '언팔로우'
							: '팔로우';
				},
				error : function(error) {
					console.log('Error toggling follow status:', error);
				}
			});
		}
	</script>
</body>
</html>
