package com.wolvescoding.nownovel.service;

import com.wolvescoding.nownovel.core.common.resp.RestResp;
import com.wolvescoding.nownovel.dto.req.UserCommentReqDto;
import com.wolvescoding.nownovel.dto.resp.BookCommentRespDto;

public interface BookService {
    public RestResp<Void> saveComment(UserCommentReqDto dto);
    public RestResp<Void> updateComment(Long userId, Long id, String content);
    public RestResp<Void> deleteComment(Long userId, Long id);
    public RestResp<BookCommentRespDto> listNewestComments(Long bookId);
}
