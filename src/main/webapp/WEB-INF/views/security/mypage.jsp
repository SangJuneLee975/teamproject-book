<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 팔로우 모달 -->
<div id="followModal" class="modal" style="display:none;">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>팔로우 목록</h2>
        <div id="userList">
            <!-- 사용자 목록이 여기에 표시됩니다 -->
        </div>
    </div>
</div>

<script>
    function openModal() {
        document.getElementById('followModal').style.display = 'block';
        loadUsers(); // 사용자 목록을 로드하는 함수
    }

    function closeModal() {
        document.getElementById('followModal').style.display = 'none';
    }

    function loadUsers() {
        // 서버로부터 사용자 목록을 받아와 표시하는 AJAX 요청을 여기에 작성
    }
</script>