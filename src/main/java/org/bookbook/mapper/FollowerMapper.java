package org.bookbook.mapper;

import java.util.List;

import org.bookbook.domain.FollowerVO;

public interface FollowerMapper {
	void insert(FollowerVO follower); // 'follow' 메소드를 'insert'로 변경

	void delete(int followId); // 'unfollow' 메소드를 'delete'로 변경

	List<FollowerVO> findFollowersByUserId(String userId); // 메소드 이름 변경

	List<FollowerVO> findFollowingsByUserId(String userId); // 메소드 이름 변경
	
	  FollowerVO findFollowByUserIds(String followerId, String followingId); // 특정 두 사용자 간의 팔로우 관계를 찾는 메소드
}