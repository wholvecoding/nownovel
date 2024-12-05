package com.wolvescoding.nownovel.service;

import com.wolvescoding.nownovel.core.common.req.PageReqDto;
import com.wolvescoding.nownovel.core.common.resp.PageRespDto;
import com.wolvescoding.nownovel.core.common.resp.RestResp;
import com.wolvescoding.nownovel.dto.req.BookAddReqDto;
import com.wolvescoding.nownovel.dto.req.ChapterAddReqDto;
import com.wolvescoding.nownovel.dto.req.ChapterUpdateReqDto;
import com.wolvescoding.nownovel.dto.req.UserCommentReqDto;
import com.wolvescoding.nownovel.dto.resp.*;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface BookService {
    public RestResp<Void> saveComment(UserCommentReqDto dto);
    public RestResp<Void> updateComment(Long userId, Long id, String content);
    public RestResp<Void> deleteComment(Long userId, Long id);
    public RestResp<BookCommentRespDto> listNewestComments(Long bookId);

    RestResp<PageRespDto<BookChapterRespDto>> listBookChapters(Long bookId, PageReqDto dto);

    RestResp<PageRespDto<UsercommentRespDto>> listComments(Long userId, PageReqDto pageReqDto);

    RestResp<List<BookCategoryRespDto>> listCategory(Integer workDirection);

    RestResp<BookInfoRespDto> getBookById(Long bookId);

    RestResp<Void> addVisitCount(Long bookId);

    RestResp<Void> saveBook(BookAddReqDto dto);
   RestResp<Void> saveBookChapter(ChapterAddReqDto dto);

    RestResp<PageRespDto<BookInfoRespDto>> listAuthorBooks(PageReqDto dto);

    RestResp<BookChapterAboutRespDto> getLastChapterAbout(Long bookId);

    RestResp<List<BookInfoRespDto>> listRecBooks(Long bookId) throws NoSuchAlgorithmException;

    RestResp<List<BookChapterRespDto>> listChapters(Long bookId);

    RestResp<ChapterContentRespDto> getBookChapter(Long chapterId);

    @Transactional
    RestResp<Void> updateBookChapter(Long chapterId, ChapterUpdateReqDto dto);

    @Transactional

    RestResp<BookContentAboutRespDto> getBookContentAbout(Long chapterId);

    RestResp<Long> getPreChapterId(Long chapterId);

    RestResp<Long> getNextChapterId(Long chapterId);

    RestResp<List<BookRankRespDto>> listVisitRankBooks();

    RestResp<List<BookRankRespDto>> listNewestRankBooks();

    RestResp<List<BookRankRespDto>> listUpdateRankBooks();
    RestResp<Void> deleteBookChapter(Long chapterId);
}
