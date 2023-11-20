package org.bookbook.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.bookbook.auth.naver.NaverLoginBO;
import org.bookbook.domain.AuthVO;
import org.bookbook.domain.NaverUserVO;
import org.bookbook.domain.UserVO;
import org.bookbook.mapper.NaverUserMapper;
import org.bookbook.mapper.UserMapper;
import org.bookbook.security.domain.CustomUser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.scribejava.core.model.OAuth2AccessToken;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.warn("Load User By username: " + username);
		UserVO vo = userMapper.read(username);
		
		    if (username != null) {
		  //  	naveruserVO = naverUserMapper.read(username);
		    } else {
		        throw new UsernameNotFoundException("User not found with username: " + username);
		    }

		return new CustomUser(vo);
	}

//	private OAuth2AccessToken getOAuth2AccessTokenFromSession() {
//		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//		HttpSession session = attr.getRequest().getSession(false); // false 새로운 세션 생성 방지
//		return (OAuth2AccessToken) session.getAttribute("oauthToken");
//	}

//	private UserVO createUserFromNaverUserInfo(String naverId, NaverUserVO naverUserInfo) {
//		UserVO userVO = new UserVO();
//
//		// 네이버 사용자의 기본 정보 설정
//		userVO.setUserid(naverId);
//		userVO.setUsername(naverUserInfo.getName());
//		userVO.setNickname("Naver_" + naverId);
//		userVO.setEmail(naverUserInfo.getEmail());
//		userVO.setGender(naverUserInfo.getGender());
//		userVO.setPassword("default_password");
//		// 사용자 권한 설정
//		List<AuthVO> authList = new ArrayList<>();
//		authList.add(new AuthVO(naverId, "ROLE_USER"));
//		userVO.setAuthList(authList);
//
//		// DB에 사용자 정보 저장
//		userMapper.insert(userVO); // 사용자 기본 정보 저장
//		for (AuthVO auth : authList) {
//			userMapper.insertAuth(auth); // 사용자 권한 정보 저장
//		}
//
//		return userVO;
//	}

//	private NaverUserVO getNaverUserInfo(String naverId, OAuth2AccessToken oauthToken)
//			throws IOException, ParseException {
//		// 네이버 사용자 프로필 정보를 가져오는 로직
//		NaverLoginBO naverLoginBO = new NaverLoginBO();
//		String jsonProfile = naverLoginBO.getUserProfile(oauthToken);
//
//		// JSON 파싱 로직
//		JSONParser parser = new JSONParser();
//		JSONObject jsonObj = (JSONObject) parser.parse(jsonProfile);
//		JSONObject responseObj = (JSONObject) jsonObj.get("response");
//
//		NaverUserVO naverUser = new NaverUserVO();
//		naverUser.setId((String) responseObj.get("id"));
//		naverUser.setName((String) responseObj.get("name"));
//		naverUser.setEmail((String) responseObj.get("email"));
//		naverUser.setGender((String) responseObj.get("gender"));
//		naverUser.setBirthday((String) responseObj.get("birthday"));
//
//		return naverUser;
//	}

}