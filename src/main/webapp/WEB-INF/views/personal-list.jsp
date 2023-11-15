<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
$('#follow-btn').on('click', function() {
	follow(true);
});

$('#unfollow-btn').on('click', function() {
	follow(false);
});

function follow(check) {
	if(check) {
		$.ajax({
			type: "POST",
			url: "/myapp/follow/${user.id}",
			headers: {
				"Content-Type": "application/json",
				"X-HTTP-Method-Override": "POST"
			},
			success: function(result) {
				console.log("result : " + result);
				if(result === "FollowOK"){
					$(".follow").html('<button class="followBtn" id="unfollow-btn">언팔로우</button>');
					location.href="/myapp/post/${user.id}";
				}
			}
		});
	} else {
		$.ajax({
			type: "POST",
			url: "/myapp/unfollow/${user.id}",
			headers: {
				"Content-Type": "application/json",
				"X-HTTP-Method-Override": "POST"
			},
			success: function(result) {
				console.log("result : " + result);
				if(result === "UnFollowOK"){
					$(".follow").html('<button class="followBtn" id="follow-btn">팔로우</button>');
					location.href="/myapp/post/${user.id}";
				}
			}
		});
	}
}
</head>
</html>
