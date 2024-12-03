package com.wolvescoding.nownovel.controller.front;

import com.wolvescoding.nownovel.core.common.resp.RestResp;
import com.wolvescoding.nownovel.core.constant.ApiRouterConsts;
import com.wolvescoding.nownovel.dto.resp.BookCommentRespDto;
import com.wolvescoding.nownovel.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRouterConsts.API_FRONT_BOOK_URL_PREFIX)
public class BookController {
    private final BookService bookService;
    @GetMapping("comment/newest_list")
    public RestResp<BookCommentRespDto> listNewestComments( Long bookId) {
        return bookService.listNewestComments(bookId);
    }
}
