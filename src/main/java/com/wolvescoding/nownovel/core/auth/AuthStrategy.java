package com.wolvescoding.nownovel.core.auth;

import com.wolvescoding.nownovel.core.common.constant.ErrorCodeEnum;
import com.wolvescoding.nownovel.core.common.exception.BusinessException;
import com.wolvescoding.nownovel.core.constant.SystemConfigConsts;
import com.wolvescoding.nownovel.core.util.JwtUtils;
import com.wolvescoding.nownovel.dao.entity.UserInfo;
import com.wolvescoding.nownovel.dto.UserInfoDto;
import com.wolvescoding.nownovel.manager.cache.UserInfoCacheManager;
import org.springframework.util.StringUtils;

import java.util.Objects;

public interface AuthStrategy {
    /**
     * 认证鉴权
     * @param token // 登录的token
     * @param RequestUri // 请求的uri
     * @throws BusinessException // 鉴权失败抛出异常
     */
    void auth(String token, String RequestUri) throws BusinessException;

    /** 默认方法
     * 单点登录认证
     * @param jwtUtils
     * @param userInfoCacheManager
     * @param token
     * @return
     */
    default Long authSSO(JwtUtils jwtUtils, UserInfoCacheManager userInfoCacheManager, String token){
        if(!StringUtils.hasText(token)){
            throw new BusinessException(ErrorCodeEnum.USER_LOGIN_EXPIRED);
        }
        Long userId = jwtUtils.paraseToken(token, SystemConfigConsts.NOVEL_FRONT_KEY);
        if(Objects.isNull(userId)){
           throw new BusinessException(ErrorCodeEnum.USER_LOGIN_EXPIRED);
        }
        UserInfoDto userInfoDto = userInfoCacheManager.getUser(userId);
        if(Objects.isNull(userInfoDto)){
            throw new BusinessException(ErrorCodeEnum.USER_NOT_EXIST);
        }
        UserHolder.setUserId(userId);
        return userId;
    }
}
