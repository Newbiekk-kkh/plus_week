package com.example.demo.domain.user.controller;

import com.example.demo.constants.GlobalConstants;
import com.example.demo.domain.user.dto.LoginRequestDto;
import com.example.demo.domain.user.dto.UserRequestDto;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.util.Authentication;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.domain.user.enums.Role.ADMIN;
import static com.example.demo.domain.user.enums.Role.USER;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void signupWithEmail(@RequestBody UserRequestDto userRequestDto) {
        userService.signupWithEmail(userRequestDto);
    }

    @PostMapping("/login")
    public void loginWithEmail(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        Authentication authentication = userService.loginUser(loginRequestDto);
        HttpSession session = request.getSession();

        if(authentication.getRole().equals(USER)) {
            session.setAttribute(GlobalConstants.USER_AUTH, authentication);
        }

        if(authentication.getRole().equals(ADMIN)) {
            session.setAttribute(GlobalConstants.ADMIN_AUTH, authentication);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
