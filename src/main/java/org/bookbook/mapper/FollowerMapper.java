package org.bookbook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bookbook.domain.FollowerVO;


public interface FollowerMapper {
	void insert(FollowerVO follower);

	void delete(int followId);

	List<FollowerVO> findFollowersByUserId(String userId);

	List<FollowerVO> findFollowingsByUserId(String userId);


	// 두 사용자 간의 팔로우 관계를 찾는 메소드
	FollowerVO findFollowByUserIds(@Param("followerId") String followerId, @Param("followingId") String followingId);
}