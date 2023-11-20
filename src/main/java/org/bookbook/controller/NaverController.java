package org.bookbook.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.bookbook.auth.naver.NaverLoginBO;
import org.bookbook.security.CustomAuthenticationToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.model.OAuth2AccessToken;

@Controller
public class NaverController {
	
	   private String apiResult = null;

	  @Autowired
	    private NaverLoginBO naverLoginBO;

	  @GetMapping("/naver-login-url")
	  @ResponseBody
	  public String naverLogin(Model model, HttpSession session) {
	      return  naverLoginBO.getAuthorizationUrl(session);
	  }

	  @GetMapping("/callback")
	  public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
	          throws IOException, ParseException, org.json.simple.parser.ParseException {

	      OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
	      if (oauthToken != null) {
	          String apiResult = naverLoginBO.getUserProfile(oauthToken);
	          JSONParser parser = new JSONParser();
	          JSONObject jsonObj = (JSONObject) parser.parse(apiResult);
	          JSONObject response_obj = (JSONObject) jsonObj.get("response");

	          // 사용자 정보 세션에 저장
	          session.setAttribute("userId", response_obj.get("id"));
	          session.setAttribute("userNickname", response_obj.get("nickname"));
	          session.setAttribute("userName", response_obj.get("name"));
	          session.setAttribute("userEmail", response_obj.get("email"));
	          session.setAttribute("userGender", response_obj.get("gender"));
	          session.setAttribute("userBirthday", response_obj.get("birthday"));
	          session.setAttribute("oauthToken", oauthToken);

	          return "redirect:/profile"; // 사용자를 프로필 페이지로 리디렉션
	      } else {
	          return "error"; // 에러 페이지로 리디렉션
	      }
	  }
}
