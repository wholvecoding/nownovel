package com.wolvescoding.nownovel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wolvescoding.nownovel.core.common.constant.CommonConsts;
import com.wolvescoding.nownovel.core.common.constant.ErrorCodeEnum;
import com.wolvescoding.nownovel.core.common.exception.BusinessException;
import com.wolvescoding.nownovel.core.common.resp.RestResp;
import com.wolvescoding.nownovel.core.constant.DatabaseConsts;
import com.wolvescoding.nownovel.core.constant.SystemConfigConsts;
import com.wolvescoding.nownovel.core.util.JwtUtils;
import com.wolvescoding.nownovel.dao.entity.UserBookshelf;
import com.wolvescoding.nownovel.dao.entity.UserFeedback;
import com.wolvescoding.nownovel.dao.entity.UserInfo;
import com.wolvescoding.nownovel.dao.mapper.UserBookshelfMapper;
import com.wolvescoding.nownovel.dao.mapper.UserFeedbackMapper;
import com.wolvescoding.nownovel.dao.mapper.UserInfoMapper;
import com.wolvescoding.nownovel.dto.req.UserInfoUpReqDto;
import com.wolvescoding.nownovel.dto.req.UserLoginReqDto;
import com.wolvescoding.nownovel.dto.req.UserRegisterReqDto;
import com.wolvescoding.nownovel.dto.resp.UserInfoRespDto;
import com.wolvescoding.nownovel.dto.resp.UserLoginRespDto;
import com.wolvescoding.nownovel.dto.resp.UserRegisterRespDto;
import com.wolvescoding.nownovel.manager.VerifyCodeManager;
import com.wolvescoding.nownovel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final VerifyCodeManager verifyCodeManager;
    private final UserInfoMapper userInfoMapper;
    private final JwtUtils jwtUtils;
    private final UserFeedbackMapper userFeedbackMapper;
    private final UserBookshelfMapper userBookshelfMapper;
    @Override
    public RestResp<UserRegisterRespDto> register(UserRegisterReqDto dto) {
        // 验证码校验
        if(!verifyCodeManager.imgVerifyCodeOk(dto.getSessionId(), dto.getVelCode())){
            throw new BusinessException(ErrorCodeEnum.USER_VERIFY_CODE_ERROR);
        }
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        //
        queryWrapper.eq(DatabaseConsts.UserInfoTable.COLUMN_USERNAME, dto.getUsername()).last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
       // 用户名已存在
        if(userInfoMapper.selectCount(queryWrapper) > 0){
            throw new BusinessException(ErrorCodeEnum.USER_NAME_EXIST);
        }
        // 创建用户
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(dto.getUsername());
        userInfo.setPassword(
                DigestUtils.md5DigestAsHex(dto.getPassword().getBytes(StandardCharsets.UTF_8)));
        userInfo.setNickName(dto.getUsername());
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setUpdateTime(LocalDateTime.now());
        userInfo.setSalt("0");
        userInfoMapper.insert(userInfo);
        verifyCodeManager.removeImgVerifyCode(dto.getSessionId());
        // 生成token
        return RestResp.ok(UserRegisterRespDto.builder().token(jwtUtils.generateToken(userInfo.getId(), SystemConfigConsts.NOVEL_FRONT_KEY))
                .uid(userInfo.getId()).build());

    }
    @Override
    public RestResp<UserLoginRespDto> login(UserLoginReqDto dto) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.UserInfoTable.COLUMN_USERNAME, dto.getUsername()).last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        if(Objects.isNull(userInfo)){
            throw new BusinessException(ErrorCodeEnum.USER_NOT_EXIST);
        }
        if(!Objects.equals(userInfo.getPassword(),   DigestUtils.md5DigestAsHex(dto.getPassword().getBytes(StandardCharsets.UTF_8)))){
            throw new BusinessException(ErrorCodeEnum.USER_PASSWORD_WRONG);
        }
        return RestResp.ok(UserLoginRespDto.builder().token(jwtUtils.generateToken(userInfo.getId(), SystemConfigConsts.NOVEL_FRONT_KEY))
                .uid(userInfo.getId())
                .nickName(userInfo.getNickName())
                .build());
        }

    @Override
    public RestResp<UserInfoRespDto> getUserInfo(Long userId) {
            UserInfo userInfo = userInfoMapper.selectById(userId);
        return RestResp.ok(UserInfoRespDto.builder()
                .nickName(userInfo.getNickName())
                .userSex(userInfo.getUserSex())
                .userPhoto(userInfo.getUserPhoto())
                .build());
    }

    @Override
    public RestResp<Void> updataUserInfo(UserInfoUpReqDto dto) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(dto.getUserId());
        userInfo.setNickName(dto.getNickName());
        userInfo.setUserPhoto(dto.getUserPhoto());
        userInfo.setUserSex(dto.getUserSex());
        userInfoMapper.updateById(userInfo);
        return RestResp.ok();
    }

    @Override
    public RestResp<Void> saveFeedback(Long userId, String content) {
        UserFeedback userFeedback = new UserFeedback();
        userFeedback.setUserId(userId);
        userFeedback.setContent(content);
        userFeedback.setCreateTime(LocalDateTime.now());
        userFeedbackMapper.insert(userFeedback);
        return RestResp.ok();
    }

    @Override
    public RestResp<Void> deleteFeedback(Long userId, Long id) {
        QueryWrapper<UserFeedback> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.CommonColumnEnum.ID.getName(), id)
                .eq(DatabaseConsts.UserFeedBackTable.COLUMN_USER_ID, userId);
        userFeedbackMapper.delete(queryWrapper);
        return RestResp.ok();
    }

    @Override
    public RestResp<Integer> getBookshelfStatus(Long userId, String bookId) {
        QueryWrapper<UserBookshelf> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.UserBookshelfTable.COLUMN_USER_ID, userId)
                .eq(DatabaseConsts.UserBookshelfTable.COLUMN_BOOK_ID, bookId);
        return RestResp.ok(
                userBookshelfMapper.selectCount(queryWrapper) > 0
                        ? CommonConsts.YES
                        : CommonConsts.NO
        );
    }


}
