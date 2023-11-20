package org.bookbook.security;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.bookbook.auth.naver.NaverLoginBO;
import org.bookbook.domain.NaverUserVO;
import org.bookbook.domain.UserVO;
import org.bookbook.mapper.NaverUserMapper;
import org.bookbook.mapper.UserMapper;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.scribejava.core.model.OAuth2AccessToken;

@Service
public class NaverLoginService {

    @Autowired
    private NaverLoginBO naverLoginBO;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NaverUserMapper naverUserMapper;

    public UserVO processNaverUserLogin(String code, String state, HttpSession session) throws IOException, ParseException {
        OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
        if (oauthToken != null) {
            NaverUserVO naverUserInfo = naverLoginBO.getNaverUserInfo(oauthToken);
            return createUserFromNaverUserInfo(naverUserInfo);
        }
        return null;
    }

    private UserVO createUserFromNaverUserInfo(NaverUserVO naverUserInfo) {
        // Check if user already exists
        String userId = naverUserMapper.getUserIdByNaverId(naverUserInfo.getId());
        if (userId == null) {
            // Create new user in database
            UserVO newUser = new UserVO();
            newUser.setUserid(naverUserInfo.getId());
            newUser.setUsername(naverUserInfo.getName());
            newUser.setNickname("Naver_" + naverUserInfo.getId());
            newUser.setEmail(naverUserInfo.getEmail());
            newUser.setGender(naverUserInfo.getGender());
            newUser.setPassword("default_password"); // 

            userMapper.insert(newUser);
            return newUser;
        } else {
          
            return userMapper.read(userId);
        }
    }
}
