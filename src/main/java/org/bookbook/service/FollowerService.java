package org.bookbook.service;

import java.util.List;

import org.bookbook.domain.FollowerVO;
import org.springframework.stereotype.Service;

public interface FollowerService {

	void follow(FollowerVO follower);

	void unfollow(int followId); // 팔로우 ID를 기반으로 언팔로우 기능을 제공합니다.

	List<FollowerVO> getFollowers(String userId); // 특정 사용자의 팔로워 목록을 가져옵니다.

	List<FollowerVO> getFollowings(String userId); // 특정 사용자가 팔로우하는 목록을 가져옵니다.
	
	 void toggleFollow(String followerId, String followingId);  // 팔로우 상태를 토글하는 메소드
}