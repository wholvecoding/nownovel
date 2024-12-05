package com.wolvescoding.nownovel.manager;
//import  com.wolvescoding.nownovel.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.wolvescoding.nownovel.core.constant.CacheConsts;
import com.wolvescoding.nownovel.dao.entity.BookInfo;
import com.wolvescoding.nownovel.dao.entity.HomeBook;
import com.wolvescoding.nownovel.dao.mapper.BookInfoMapper;
import com.wolvescoding.nownovel.dao.mapper.HomeBookMapper;
import com.wolvescoding.nownovel.dto.resp.HomeBookRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 首页推荐小说的缓存管理类
 */
@Component
@RequiredArgsConstructor
public class HomebBookCacheManager {
    private final HomeBookMapper homeBookMapper;

    private final BookInfoMapper bookInfoMapper;
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER, value = CacheConsts.HOME_BOOK_CACHE_NAME)
    public List<HomeBookRespDto> listHomeBooks(){
        // 从数据库中查询出所有的首页推荐小说
        List<HomeBook> homeBooks = homeBookMapper.selectList(null);
        if(!CollectionUtils.isEmpty(homeBooks)){
            // 拿到所有的小说id
            List<Long> bookIds =homeBooks.stream()
                    .map(HomeBook::getBookId)
                    .toList();
            QueryWrapper<BookInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("id",bookIds);
            List<BookInfo> bookInfos = bookInfoMapper.selectList(queryWrapper);
            if(!CollectionUtils.isEmpty(bookInfos)){
                Map<Long, BookInfo> bookInfoMap = bookInfos.stream()
                        .collect(Collectors.toMap(BookInfo::getId, Function.identity()));
                return homeBooks.stream().map(v ->{
                    BookInfo bookInfo = bookInfoMap.get(v.getBookId());
                    HomeBookRespDto homeBookRespDto = new HomeBookRespDto();
                    homeBookRespDto.setBookId(v.getBookId());
                    homeBookRespDto.setBookName(bookInfo.getBookName());
                    homeBookRespDto.setPicUrl(bookInfo.getPicUrl());
                    homeBookRespDto.setBookDesc(bookInfo.getBookDesc());
                    return homeBookRespDto;
                }).toList();
            }
        }
        return Collections.emptyList();
    }

}
