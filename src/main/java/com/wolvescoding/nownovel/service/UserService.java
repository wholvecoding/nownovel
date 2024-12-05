package com.wolvescoding.nownovel.service;

import com.wolvescoding.nownovel.core.common.resp.RestResp;
import com.wolvescoding.nownovel.dto.req.UserInfoUpReqDto;
import com.wolvescoding.nownovel.dto.req.UserLoginReqDto;
import com.wolvescoding.nownovel.dto.req.UserRegisterReqDto;
import com.wolvescoding.nownovel.dto.resp.UserInfoRespDto;
import com.wolvescoding.nownovel.dto.resp.UserLoginRespDto;
import com.wolvescoding.nownovel.dto.resp.UserRegisterRespDto;

public interface UserService {
    public RestResp<UserRegisterRespDto> register(UserRegisterReqDto user);
    public RestResp<UserLoginRespDto> login(UserLoginReqDto user);

    RestResp<UserInfoRespDto> getUserInfo(Long userId);

    RestResp<Void> updataUserInfo(UserInfoUpReqDto dto);

    RestResp<Void> saveFeedback(Long userId, String content);

    RestResp<Void> deleteFeedback(Long userId, Long id);

    RestResp<Integer> getBookshelfStatus(Long userId, String bookId);
}
