package org.bookbook.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;

@Data
public class MemberVO {
	private String userid;
	private String userpw;
	private String userName;

	private Date regDate;

	private String email;
	private String tel;
	private List<AuthVO> authList;

	private String snsId;
	
	public Collection<SimpleGrantedAuthority> getAuthorities() {
	    if (this.authList == null) {
	        return new ArrayList<>();
	    }
	    return this.authList.stream()
	            .map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
	            .collect(Collectors.toList());
	}
	
}