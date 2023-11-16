package org.bookbook.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.bookbook.domain.UserVO;
import org.bookbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/api")
@Log4j
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	@ResponseBody
	public ResponseEntity<List<UserVO>> getAllUsers() {
		try {
			List<UserVO> users = userService.getAllUsers();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			log.error("Error fetching users", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}