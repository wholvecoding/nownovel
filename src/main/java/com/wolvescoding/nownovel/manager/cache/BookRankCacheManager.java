package com.wolvescoding.nownovel.manager.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wolvescoding.nownovel.core.constant.CacheConsts;
import com.wolvescoding.nownovel.core.constant.DatabaseConsts;
import com.wolvescoding.nownovel.dao.entity.BookInfo;
import com.wolvescoding.nownovel.dao.mapper.BookInfoMapper;
import com.wolvescoding.nownovel.dto.resp.BookRankRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookRankCacheManager {
    private final BookInfoMapper bookInfoMapper;

    /**
     * 查询小说点击榜列表，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
            value = CacheConsts.BOOK_VISIT_RANK_CACHE_NAME)
    public List<BookRankRespDto> listVisitRankBooks() {
        QueryWrapper<BookInfo> bookInfoQueryWrapper = new QueryWrapper<>();
        bookInfoQueryWrapper.orderByDesc(DatabaseConsts.BookTable.COLUMN_VISIT_COUNT);

        return listRankBooks(bookInfoQueryWrapper);
    }

//    public List<BookRankRespDto> listVisitRankBooks() {
//        // 构建查询条件
//        QueryWrapper<BookInfo> bookInfoQueryWrapper = new QueryWrapper<>();
//        bookInfoQueryWrapper.orderByDesc(DatabaseConsts.BookTable.COLUMN_VISIT_COUNT);
//
//        // 打印查询条件，查看是否正确
//        System.out.println("查询条件: " + bookInfoQueryWrapper.getSqlSegment());
//
//        // 调用 listRankBooks 获取数据
//        List<BookRankRespDto> result = listRankBooks(bookInfoQueryWrapper);
//
//        // 打印查询结果
//        System.out.println("查询结果:");
//        result.forEach(book -> {
//            System.out.println("ID: " + book.getId());
//            System.out.println("书名: " + book.getBookName());
//            System.out.println("作者: " + book.getAuthorName());
//            System.out.println("分类: " + book.getCategoryName());
//            System.out.println("-----------");
//        });
//
//        return result;
//    }

    /**
     * 查询小说新书榜列表，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
            value = CacheConsts.BOOK_NEWEST_RANK_CACHE_NAME)
    public List<BookRankRespDto> listNewestRankBooks() {
        QueryWrapper<BookInfo> bookInfoQueryWrapper = new QueryWrapper<>();
        bookInfoQueryWrapper
                .gt(DatabaseConsts.BookTable.COLUMN_WORD_COUNT, 0)
                .orderByDesc(DatabaseConsts.CommonColumnEnum.CREATE_TIME.getName());
        return listRankBooks(bookInfoQueryWrapper);
    }

    /**
     * 查询小说更新榜列表，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
            value = CacheConsts.BOOK_UPDATE_RANK_CACHE_NAME)
    public List<BookRankRespDto> listUpdateRankBooks() {
        QueryWrapper<BookInfo> bookInfoQueryWrapper = new QueryWrapper<>();
        bookInfoQueryWrapper
                .gt(DatabaseConsts.BookTable.COLUMN_WORD_COUNT, 0)
                .orderByDesc(DatabaseConsts.CommonColumnEnum.UPDATE_TIME.getName());
        return listRankBooks(bookInfoQueryWrapper);
    }

    private List<BookRankRespDto> listRankBooks(QueryWrapper<BookInfo> bookInfoQueryWrapper) {
        bookInfoQueryWrapper
                .gt(DatabaseConsts.BookTable.COLUMN_WORD_COUNT, 0)
                .last(DatabaseConsts.SqlEnum.LIMIT_30.getSql());
        return bookInfoMapper.selectList(bookInfoQueryWrapper).stream().map(v -> {
            BookRankRespDto respDto = new BookRankRespDto();
            respDto.setId(v.getId());
            respDto.setCategoryId(v.getCategoryId());
            respDto.setCategoryName(v.getCategoryName());
            respDto.setBookName(v.getBookName());
            respDto.setAuthorName(v.getAuthorName());
            respDto.setPicUrl(v.getPicUrl());
            respDto.setBookDesc(v.getBookDesc());
            respDto.setLastChapterName(v.getLastChapterName());
            respDto.setLastChapterUpdateTime(v.getLastChapterUpdateTime());
            respDto.setWordCount(v.getWordCount());
            return respDto;
        }).toList();
    }


}
