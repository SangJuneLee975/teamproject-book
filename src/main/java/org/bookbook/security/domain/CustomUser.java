package org.bookbook.security.domain;

import java.util.Collection;

import org.bookbook.domain.UserVO;
import org.bookbook.security.CustomUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
public class CustomUser extends User {
	

	private static final long serialVersionUID = 1L;
	
	//
	private UserVO user;
	
	public CustomUser(String username, String password, 
					Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public CustomUser(UserVO vo) {
		super(vo.getUserid(), vo.getPassword(), vo.getAuthorities());
		this.user = vo;
		
	}
	
	
}
