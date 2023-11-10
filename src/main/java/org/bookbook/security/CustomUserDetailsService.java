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
	public UserDetails loadUserByUsername(String username) 
		throws UsernameNotFoundException {

		  log.warn("Load User By username: " + username);
		    UserVO vo = mapper.read(username);
		    
		log.warn("user vo: " + vo);
		
		if(vo == null) {
			log.warn(username + "은 없는 id입니다.");
			throw new UsernameNotFoundException(username + "은 없는 id입니다.");
			
			
		}
		
		log.warn("user vo: " + vo);
		log.warn("User details: " + vo.toString());
		   // 사용자 ID 값 로그에 출력
	    log.info("Received User ID: " + username);
	    



		return new CustomUser(vo);
		
	}
	
}



