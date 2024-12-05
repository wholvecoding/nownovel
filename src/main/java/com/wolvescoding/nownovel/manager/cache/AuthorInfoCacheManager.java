package com.wolvescoding.nownovel.manager.cache;

import com.wolvescoding.nownovel.dao.entity.AuthorInfo;
import com.wolvescoding.nownovel.dao.mapper.AuthorInfoMapper;
import com.wolvescoding.nownovel.dto.AuthorInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthorInfoCacheManager {
    private final AuthorInfoMapper AuthorInfoMapper;
    public AuthorInfoDto getAuthor(Long authorId){
        AuthorInfo authorInfo = AuthorInfoMapper.selectById(authorId);
        if(Objects.isNull(authorInfo)){
            return null;
        }
        return AuthorInfoDto.builder()
                .id(authorInfo.getId())
                .penName(authorInfo.getPenName())
                .status(authorInfo.getStatus()).build();
    }
}
