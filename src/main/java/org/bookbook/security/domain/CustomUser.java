package org.bookbook.security.domain;

import java.util.Collection;

import org.bookbook.domain.UserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserVO user;
	
	public CustomUser(String userid, String userpassword, 
					Collection<? extends GrantedAuthority> authorities) {
		super(userid, userpassword, authorities);
	}

	public CustomUser(UserVO vo) {
		super(vo.getUserid(), vo.getPassword(), vo.getAuthorities());
		this.user = vo;
	}
	
}
