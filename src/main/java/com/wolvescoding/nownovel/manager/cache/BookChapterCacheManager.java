package com.wolvescoding.nownovel.manager.cache;

import com.wolvescoding.nownovel.core.constant.CacheConsts;
import com.wolvescoding.nownovel.dao.entity.BookChapter;
import com.wolvescoding.nownovel.dao.mapper.BookChapterMapper;
import com.wolvescoding.nownovel.dto.resp.BookChapterRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookChapterCacheManager {
    private final BookChapterMapper bookChapterMapper;

    /**
     * 查询小说章节信息，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
            value = CacheConsts.BOOK_CHAPTER_CACHE_NAME)
    public BookChapterRespDto getChapter(Long chapterId) {
        BookChapter bookChapter = bookChapterMapper.selectById(chapterId);
        return BookChapterRespDto.builder()
                .id(chapterId)
                .bookId(bookChapter.getBookId())
                .chapterNum(bookChapter.getChapterNum())
                .chapterName(bookChapter.getChapterName())
                .chapterWordCount(bookChapter.getWordCount())
                .chapterUpdateTime(bookChapter.getUpdateTime())
                .isVip(bookChapter.getIsVip())
                .build();
    }

    @CacheEvict(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
            value = CacheConsts.BOOK_CHAPTER_CACHE_NAME)
    public void evictBookChapterCache(Long chapterId) {
        // 调用此方法自动清除小说章节信息的缓存
    }

}
