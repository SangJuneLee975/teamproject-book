package org.bookbook.security;

import org.bookbook.domain.UserVO;
import org.bookbook.mapper.UserMapper;
import org.bookbook.security.domain.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserMapper mapper;
	

	@Override
	public UserDetails loadUserByUsername(String userid) 
		throws UsernameNotFoundException {

		log.warn("Load User By Userid:===== " + userid);

		UserVO vo = mapper.read(userid);
		log.warn("user vo: " + vo);
		
		if(vo == null) {
			log.warn(userid + "은 없는 id입니다.");
			throw new UsernameNotFoundException(userid + "은 없는 id입니다.");
		}

		log.warn("user vo: " + vo);
		return new CustomUser(vo);
		
	}


}
