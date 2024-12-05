package com.wolvescoding.nownovel.manager.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wolvescoding.nownovel.core.constant.CacheConsts;
import com.wolvescoding.nownovel.core.constant.DatabaseConsts;
import com.wolvescoding.nownovel.dao.entity.BookCategory;
import com.wolvescoding.nownovel.dao.mapper.BookCategoryMapper;
import com.wolvescoding.nownovel.dto.resp.BookCategoryRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookCategoryCacheManager {

    private final BookCategoryMapper bookCategoryMapper;

    /**
     * 根据作品方向查询小说分类列表，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
            value = CacheConsts.BOOK_CATEGORY_LIST_CACHE_NAME)
    public List<BookCategoryRespDto> listCategory(Integer workDirection) {
        QueryWrapper<BookCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.BookCategoryTable.COLUMN_WORK_DIRECTION, workDirection);
        return bookCategoryMapper.selectList(queryWrapper).stream().map(v ->
                BookCategoryRespDto.builder()
                        .id(v.getId())
                        .name(v.getName())
                        .build()).toList();
    }

}