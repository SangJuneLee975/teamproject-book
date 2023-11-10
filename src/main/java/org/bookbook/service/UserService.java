package org.bookbook.service;

import java.io.IOException;

import org.bookbook.domain.ChangePasswordVO;
import org.bookbook.domain.UserVO;


public interface UserService {
	public UserVO get(String userid);

	public void register(UserVO userid) throws IOException;

	public boolean changePassword(ChangePasswordVO vo); 
	 //


}
