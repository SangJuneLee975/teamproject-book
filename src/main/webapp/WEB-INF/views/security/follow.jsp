<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
  

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
        // 모달창 닫기
        window.location.href = "/security/profile"; // 프로필 페이지로 리다이렉트
    }

    // 페이지 로드 시 사용자 목록 로드
    $(document).ready(function() {
        loadUsers();
    });
    // 사용자 목록을 가져오는 함수
    function loadUsers() {
        $.ajax({
            url: '/api/users',     
            method: 'GET',
            success: function(data) {
                var userList = document.getElementById('userList');
                userList.innerHTML = '';
                data.forEach(function(user) {
                    var userDiv = document.createElement('div');
                    userDiv.id = 'user-' + user.userid; // 각 사용자 div에 고유한 ID 할당
                    userDiv.innerHTML = user.username + 
                        ' <button onclick="toggleFollow(\'' + user.userid + '\')">팔로우</button>';
                    userList.appendChild(userDiv);
                });
            },
            error: function(error) {
                console.log('Error fetching user list:', error);
            }
        });
    }
    // 팔로우 상태를 토글하는 함수
    function toggleFollow(userId) {
        $.ajax({
            url: '/security/toggleFollow',
            method: 'POST',
            data: { userId: userId },
            success: function(response) {
                var userDiv = document.getElementById('user-' + userId);
                var button = userDiv.querySelector('button');
                button.innerText = button.innerText === '팔로우' ? '언팔로우' : '팔로우';
            },
            error: function(error) {
                console.log('Error toggling follow status:', error);
            }
        });
    }
</script>

