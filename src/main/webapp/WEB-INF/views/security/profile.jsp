<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<%@ include file="../layouts/header.jsp"%>
 <!--
<sec:authentication property="principal.user" var="user" />
 -->
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<script>
	var currentUserId = "${user.userid}";
</script>

<div class="wrap">
	<div class="greenContainer">
		<div>
			<div class="grade">프로필 사진(dicebear) 넣을 공간</div>

		</div>
		<div class="modify">i</div>
	</div>

	<!-- 사용자 정보 불러오기 -->
	<div class="d-flex my-3 align-items-center">
		<div class="ml-4">
			<div>ID: ${user.userid}</div>
			<div>이름 : ${user.username}</div>
			<div>
				가입일:
				<fmt:formatDate value="${user.regDate}" pattern="yyyy-MM-dd HH:mm" />
			</div>
			<br> 팔로우 기능, 책 평가 및 리뷰 확인 기능 , 알림 기능 구현, <br> 닉네임 또는 사용자 이름
			표시, 프로필 사진 표시, 회원정보 수정 기능 구현, SNS서평 공유 <br>
		</div>
	</div>



</div>




<div class="listContainer">

	<!-- 사용자 프로필 및 정보 -->
	<div class="user-profile">
		<!-- 프로필 정보 -->
		<!-- ... -->
	</div>

	<!-- 팔로우 모달창 표시 버튼 -->
	<button id="showFollowModal" class="btn btn-primary">팔로우 목록 보기</button>

	<!-- 팔로우 모달창 -->
	<link rel="stylesheet" href="/resources/css/modal.css" />
	<div id="followModal" class="modal_wrapper" style="display: none;">
		<div class="modal-content">
			<span class="close" onclick="closeFollowModal()">&times;</span>
			<h2>팔로우</h2>
			<div id="userList">
				<!-- 사용자 목록 표시 -->
			</div>
		</div>
	</div>



	<a href="#" class="item">
		<div class="icon">ii</div>
		<div class="text">책 평가 및 리뷰 확인 기능</div>
		<div class="right"></div>
	</a> <a href="#" class="item">
		<div class="icon">ii</div>
		<div class="text">알림 기능 구현</div>
		<div class="right"></div>
	</a>
	<div class="right"></div>
</div>




</div>

<link href="/resources/css/mypage.css" rel="stylesheet" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	//팔로우 모달창 표시
	$('#showFollowModal').click(function() {
		$('#followModal').show();
		loadUsers();
	});

	// 모달창 닫기 함수
	function closeFollowModal() {
		$('#followModal').hide();
	}

	// 사용자 목록 로드
	function loadUsers() {
		console.log("Sending AJAX request");
		$.ajax({
			url : '/api/usersWithFollowStatus',
			method : 'GET',
			success : function(users) {
				 var userList = $('#userList');
	                userList.empty();
	                var table = $('<table>').addClass('table');
	                var thead = $('<thead>').append('<tr><th>ID</th><th>이름</th><th></th></tr>');
	                var tbody = $('<tbody>');
	                
	                users.forEach(function(user) {
	                    var tr = $('<tr>').append('<td>' + user.userid + '</td>').append('<td>' + user.username + '</td>');
	                    
	                    // 
	                    if (user.userid !== currentUserId) {
	                        var followText = user.followed ? '언팔로우' : '팔로우';
	                        tr.append('<td><button data-userid="' + user.userid + '" class="btn btn-outline-primary">' + followText + '</button></td>');
	                    } else {
	                        tr.append('<td></td>');
	                    }
	                    tbody.append(tr);
	                });
	                
	                table.append(thead).append(tbody);
	                userList.append(table);
	            },
	            error: function(error) {
	                console.log('Error fetching user list:', error);
	            }
	        });
	    }

	// 팔로우 상태 토글
	function toggleFollow(userId) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		console.log("CSRF Token: " + token);
		console.log("CSRF Header: " + header);
		$.ajax({
			url : '/security/toggleFollow',
			method : 'POST',
			data : {
				userId : userId
			},
			beforeSend : function(xhr) {
				if (header) {
					xhr.setRequestHeader(header, token);
				} else {
					console.error('CSRF header is undefined');
				}
			},
			success: function(response) {
			    console.log('Follow toggle success:', response);
			    var button = $('button[data-userid="' + userId + '"]');
			    button.text(button.text() === '팔로우' ? '언팔로우' : '팔로우');
			},
			error : function(error) {
				console.log('팔로우 변경 실패:', error);
			}
		});
	}

	
	 // 버튼 클릭 이벤트 처리
    $('#userList').on('click', 'button', function() {
        var userId = $(this).data('userid');
        toggleFollow(userId);
    });
	 
	//  모달창 닫기
	window.onclick = function(event) {
		if (event.target == document.getElementById('followModal')) {
			closeFollowModal();
		}
	};
</script>

<%@ include file="../layouts/footer.jsp"%>
