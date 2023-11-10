package org.bookbook.mapper;

import org.bookbook.domain.AuthVO;
import org.bookbook.domain.ChangePasswordVO;
import org.bookbook.domain.UserVO;

public interface UserMapper {
	public UserVO read(String userid);
	
	public void insert(UserVO userid);
	
	public void insertAuth(AuthVO auth);
	
	public void changePassword(ChangePasswordVO vo); 

	//public boolean changePassword(ChangePasswordVO vo); 
	
}
