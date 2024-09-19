package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.dto.ReqRes;
import com.example.c0823l1_be.dto.UserDto;
import com.example.c0823l1_be.entity.User;
import com.example.c0823l1_be.security.ChangePasswordRequest;
import com.example.c0823l1_be.service.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());

    }


    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody UserDto reqres){
        return ResponseEntity.ok(userService.updateUserInfo(userId, reqres));
    }

    @PutMapping("/adminstaff/update")
    public ResponseEntity<ReqRes> updateUserByStaff( @RequestBody UserDto reqres){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(userService.updateUserInfoByStaff(username, reqres));
    }


    @PutMapping("/adminstaff/update/password")
    public ResponseEntity<ReqRes> updateUserPassword(@RequestBody ChangePasswordRequest reqres){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(userService.updateUserPassword(username, reqres));
    }

    @GetMapping("/adminstaff/profile")
    public ResponseEntity<ReqRes> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ReqRes response = userService.getMyInfo(username);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUser(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }



}
