package com.example.c0823l1_be.service;

import com.example.c0823l1_be.dto.ReqRes;
import com.example.c0823l1_be.dto.UserDto;
import com.example.c0823l1_be.entity.User;
import com.example.c0823l1_be.security.ChangePasswordRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IUserService {
    public ReqRes register(ReqRes registrationRequest);

    public ReqRes login( ReqRes loginRequest);

    public ReqRes refreshToken(ReqRes refreshTokenReqiest);


    public ReqRes logout(ReqRes logoutRequest);

    public ReqRes getAllUsers();

    public ReqRes getMyPassword(String username);

    public ReqRes updateUserInfoByStaff(String username, UserDto updatedUser);

    public ReqRes deleteUser(Integer userId);

    public ReqRes updateUserInfo(Integer userId, UserDto updatedUser);

    public ReqRes getMyInfo(String username);

    public ReqRes updateUserPassword(String username, ChangePasswordRequest updatePassword);









}
