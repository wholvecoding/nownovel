package com.wolvescoding.nownovel.core.auth;

import com.wolvescoding.nownovel.core.util.JwtUtils;
import com.wolvescoding.nownovel.manager.cache.UserInfoCacheManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FrontAuthStrategy implements AuthStrategy {
    private final JwtUtils jwtUtils;
    private final UserInfoCacheManager userInfoCacheManager;
    @Override
    public void auth(String token, String RequestUri){
        authSSO(jwtUtils,userInfoCacheManager,token);
    }
}
