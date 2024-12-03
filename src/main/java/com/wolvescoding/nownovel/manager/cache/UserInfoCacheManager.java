package com.wolvescoding.nownovel.manager.cache;

import com.wolvescoding.nownovel.core.constant.CacheConsts;
import com.wolvescoding.nownovel.dao.entity.UserInfo;
import com.wolvescoding.nownovel.dao.mapper.UserInfoMapper;
import com.wolvescoding.nownovel.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserInfoCacheManager {
    private final UserInfoMapper userInfoMapper;

    /**
     * 查询用户信息，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
            value = CacheConsts.USER_INFO_CACHE_NAME) // 所有的缓存信息存储在USER_INFO_CACHE_NAME中
    public UserInfoDto getUser(Long userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        if (Objects.isNull(userInfo)) {
            return null;
        }
        return UserInfoDto.builder()
                .id(userInfo.getId())
                .status(userInfo.getStatus()).build();
    }
}
