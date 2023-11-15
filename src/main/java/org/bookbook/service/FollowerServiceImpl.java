package org.bookbook.service;

import org.bookbook.domain.FollowerVO;
import org.bookbook.mapper.FollowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FollowerServiceImpl implements FollowerService {
	
	@Autowired
	private FollowerMapper followerMapper;

	@Override
	public void follow(FollowerVO follower) {
		followerMapper.insert(follower);
	}

	@Override
	public void unfollow(int followId) {
		followerMapper.delete(followId);
	}

	@Override
	public List<FollowerVO> getFollowers(String userId) {
		return followerMapper.findFollowersByUserId(userId);
	}

	@Override
	public List<FollowerVO> getFollowings(String userId) {
		return followerMapper.findFollowingsByUserId(userId);
	}
	
	 @Override
	    public void toggleFollow(String currentUserId, String targetUserId) {
	        // 팔로우 상태 확인
	        FollowerVO existingFollow = followerMapper.findFollowByUserIds(currentUserId, targetUserId);

	        if (existingFollow != null) {
	            // 이미 팔로우 상태인 경우, 언팔로우
	            followerMapper.delete(existingFollow.getFollowId());
	        } else {
	            // 팔로우 상태가 아닌 경우, 팔로우
	            FollowerVO newFollow = new FollowerVO();
	            newFollow.setFollowerId(currentUserId);
	            newFollow.setFollowingId(targetUserId);
	            followerMapper.insert(newFollow);
	        }
	    }
}