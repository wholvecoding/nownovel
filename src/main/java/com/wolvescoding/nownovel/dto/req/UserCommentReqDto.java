package com.wolvescoding.nownovel.dto.req;

import lombok.Data;

@Data
public class UserCommentReqDto {
    private String CommentContent;
    private Long BookId;
    private Long UserId;
}
