package org.bookbook.service;

import java.io.IOException;
import java.util.List;

import org.bookbook.domain.AuthVO;
import org.bookbook.domain.ChangePasswordVO;
import org.bookbook.domain.FollowerVO;
import org.bookbook.domain.UserVO;
import org.bookbook.mapper.FollowerMapper;
import org.bookbook.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UserServiceImpl implements UserService {

	public static final String AVATAR_UPLOAD_DIR = "c:/upload/avatar";

	@Autowired
	UserMapper mapper;
	
	 @Autowired
    private FollowerMapper followerMapper;

	@Autowired
	private PasswordEncoder pwEncoder;

	// 
	@Override
	public UserVO get(String userid) {
		return mapper.read(userid);
	}

	
	//회원 가입
	@Override
	public void register(UserVO userid) throws IOException {

		// 1. 비밀번호 암호화
		String encPassword = pwEncoder.encode(userid.getPassword());
		userid.setPassword(encPassword);

		// 2. users 테이블에 저장
		mapper.insert(userid);

		// 3. users_auth에 저장
		AuthVO auth = new AuthVO(userid.getUserid(), "ROLE_USER");
		mapper.insertAuth(auth);

	}

	
	// 비밀번호 바꾸기
	@Override
	public boolean changePassword(ChangePasswordVO vo) {
		UserVO user = mapper.read(vo.getUserid());

		log.info("입력된 orgPassword: " + vo.getOrgPassword());
		log.info("저장된 비밀번호: " + user.getPassword());
		// 데이터베이스에 저장되어있는 패스워드
		if (!pwEncoder.matches(vo.getOrgPassword(), user.getPassword())) {
			// 비번 오류
			log.info("비밀번호 불일치.");
			return false;
		}

		String encPassword = pwEncoder.encode(vo.getNewPassword());
		vo.setEncPassword(encPassword);
		mapper.changePassword(vo);

		return true;
	}


	  @Override
	    public List<UserVO> getAllUsers() {
	        return mapper.getAllUsers();
	    }
	  
	   @Override
	    public List<UserVO> getAllUsersWithFollowStatus(String currentUserId) {
		   List<UserVO> users = mapper.getAllUsers();
		    users.forEach(user -> {
		        FollowerVO follow = followerMapper.findFollowByUserIds(currentUserId, user.getUserid());
		        user.setFollowed(follow != null);
		    });
		    return users;
	    }


	  
}
