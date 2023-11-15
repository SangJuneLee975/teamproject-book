package org.bookbook.controller;

import java.util.List;

import org.bookbook.domain.UserVO;
import org.bookbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

  
    @GetMapping("/users")
    @ResponseBody
    public List<UserVO> getAllUsers() {
        return userService.getAllUsers(); // 모든 사용자 목록을 반환
    }
}