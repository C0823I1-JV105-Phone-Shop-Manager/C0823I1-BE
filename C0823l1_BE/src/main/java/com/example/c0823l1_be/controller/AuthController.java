package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.dto.ReqRes;
import com.example.c0823l1_be.entity.User;
import com.example.c0823l1_be.service.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg){
        return ResponseEntity.ok(userService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(userService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(userService.refreshToken(req));
    }
    @PostMapping("/auth/logout")
    public ResponseEntity<ReqRes> logout(@RequestBody ReqRes req){
        return ResponseEntity.ok(userService.logout(req));
    }

}
