package org.bookbook.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.bookbook.auth.Naver.NaverLoginBO;
import org.bookbook.domain.UserVO;
import org.bookbook.exception.DateConversionUtil;
import org.bookbook.service.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/security")
@Log4j
public class SecurityController {

    @Autowired
    private NaverLoginBO naverLoginBO;

    private String apiResult = null;

    @Autowired
    UserService service;

    @GetMapping("/login")
    public void login() {
        log.info("login page");
    }

    @GetMapping("/signup")
    public void signup(@ModelAttribute("user") UserVO user) {

    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("user") UserVO user, Errors errors,
            @RequestParam("birth.year") String year, @RequestParam("birth.month") String month,
            @RequestParam("birth.day") String day) throws IOException {

        // 1. 비밀번호, 비밀번호확인 일치여부
        if (!user.getPassword().equals(user.getPasswordcheck())) {
            // 에러 추가
            errors.rejectValue("passwordcheck", "비밀번호 불일치", "비밀번호 확인이 일치하지 않습니다.");
        }

        // 2. 아이디 중복
        if (!errors.hasFieldErrors("userid")) { // username 유효성 통과한 경우에
            // DB에서 userid을 검사
            if (service.get(user.getUserid()) != null) { // 중복일때
                errors.rejectValue("userid", "ID 중복", "이미 사용중인 ID입니다.");
            }
        }

        if (errors.hasErrors()) {
            return "security/signup";
        }

        try {
            user.setBirth(DateConversionUtil.convertToDate(year, month, day));
        } catch (ParseException e) {
            e.printStackTrace(); // 날짜 변환 실패 시 예외 처리
        }

        String email = user.getEmail().replace(",", "@");
        if (email.contains("@type")) {
            email = email.replace("@type", ""); // @type을 빈 문자열로 대체하여 제거
            user.setEmail(email); // @type이 제거된 이메일로 설정
        }

        user.setEmail(email); // 변경된 이메일을 다시 UserVO에 설정

        // DB 저장
        service.register(user);

        return "redirect:/";
    }

    @GetMapping("/profile")
    public void profile() {
    }

    @GetMapping("/naverLogin")
    public String naverLogin(Model model, HttpSession session) {
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
        model.addAttribute("url", naverAuthUrl);
        return "naverLogin";
    }

    @GetMapping("/callback")
    public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
            throws IOException, ParseException, org.json.simple.parser.ParseException {
        OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);

        apiResult = naverLoginBO.getUserProfile(oauthToken); // String형식의 json데이터

        // String형식인 apiResult를 json형태로 바꿈
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(apiResult);
        JSONObject jsonObj = (JSONObject) obj;
        model.addAttribute("userInfo", apiResult);

        // 데이터 파싱
        JSONObject response_obj = (JSONObject) jsonObj.get("response");
        String id = (String) response_obj.get("id");
        String nickname = (String) response_obj.get("nickname");
        String name = (String) response_obj.get("name");
        String email = (String) response_obj.get("email");
        String gender = (String) response_obj.get("gender");
        String birthday = (String) response_obj.get("birthday");

        session.setAttribute("userId", id);
        session.setAttribute("userNickname", nickname);
        session.setAttribute("userName", name);
        session.setAttribute("userEmail", email);
        session.setAttribute("userGender", gender);
        session.setAttribute("userBirthday", birthday);

        model.addAttribute("result", apiResult);

        return "redirect:/profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) throws IOException {
        session.invalidate();
        return "redirect:/";
    }
}
