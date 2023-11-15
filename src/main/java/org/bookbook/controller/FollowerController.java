package org.bookbook.controller;


import java.security.Principal;

import org.bookbook.domain.FollowerVO;
import org.bookbook.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/security")
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @PostMapping("/follow")
    public String follow(@ModelAttribute FollowerVO follower) {
        followerService.follow(follower);
        return "redirect:/security/follow"; // 변경됨
    }

    @PostMapping("/unfollow")
    public String unfollow(@ModelAttribute FollowerVO follower) {
        followerService.unfollow(follower.getFollowId());
        return "redirect:/security/follow"; // 변경됨
    }

    @GetMapping("/{userId}")
    public String listFollowers(@PathVariable String userId, Model model) {
        model.addAttribute("followers", followerService.getFollowers(userId));
        return "security/follow"; // 변경됨
    }
    
    @PostMapping("/toggleFollow")
    @ResponseBody
    public ResponseEntity<?> toggleFollow(@RequestParam("userId") String userId, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        String currentUserId = principal.getName(); // 현재 로그인한 사용자의 ID를 얻음

        followerService.toggleFollow(currentUserId, userId); // 팔로우 상태 토글
        return ResponseEntity.ok().build();
    }
}