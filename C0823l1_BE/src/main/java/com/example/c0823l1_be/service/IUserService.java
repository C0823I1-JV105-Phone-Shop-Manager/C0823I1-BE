package com.example.c0823l1_be.service;

import com.example.c0823l1_be.dto.ReqRes;
import com.example.c0823l1_be.entity.User;

public interface IUserService {
    public ReqRes register(ReqRes registrationRequest);

    public ReqRes login(ReqRes loginRequest);

    public ReqRes refreshToken(ReqRes refreshTokenReqiest);


        public ReqRes logout(ReqRes logoutRequest);

    public ReqRes getAllUsers();

    public ReqRes getUsersById(Integer id);

    public ReqRes deleteUser(Integer userId);

    public ReqRes updateUser(Integer userId, User updatedUser);

    public ReqRes getMyInfo(String email);







    }
