package com.wolvescoding.nownovel.core.auth;

import com.wolvescoding.nownovel.core.common.constant.ErrorCodeEnum;
import com.wolvescoding.nownovel.core.common.exception.BusinessException;
import com.wolvescoding.nownovel.core.constant.ApiRouterConsts;
import com.wolvescoding.nownovel.core.util.JwtUtils;
import com.wolvescoding.nownovel.dto.AuthorInfoDto;
import com.wolvescoding.nownovel.manager.cache.AuthorInfoCacheManager;
import com.wolvescoding.nownovel.manager.cache.UserInfoCacheManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class AuthorAuthStrategy  implements AuthStrategy {
    private final JwtUtils jwtUtils;
    private final UserInfoCacheManager userInfoCacheManager;
    private final AuthorInfoCacheManager authorInfoCacheManager;
// 排除的URI列表
    private static final List<String> EXCLUDE_URI = List.of(
            ApiRouterConsts.API_AUTHOR_URL_PREFIX + "/register",
            ApiRouterConsts.API_AUTHOR_URL_PREFIX + "/status"
    );

    @Override
    public void auth(String token, String RequestUri) {
        Long userId = authSSO(jwtUtils, userInfoCacheManager, token);
        // 如果请求的URI在排除列表中，则直接返回
        if (EXCLUDE_URI.contains(RequestUri)) {
            return;
        }
        AuthorInfoDto authorInfoDto = authorInfoCacheManager.getAuthor(userId);
        if (Objects.isNull(authorInfoDto)) {
            throw new BusinessException(ErrorCodeEnum.AUTHOR_ACCOUNT_NOT_EXISTS);
        }
        UserHolder.setAuthorId(authorInfoDto.getId());
    }
}
