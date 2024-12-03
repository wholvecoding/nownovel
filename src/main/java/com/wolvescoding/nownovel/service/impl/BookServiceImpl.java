package com.wolvescoding.nownovel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wolvescoding.nownovel.core.common.constant.ErrorCodeEnum;
import com.wolvescoding.nownovel.core.common.resp.RestResp;
import com.wolvescoding.nownovel.core.constant.DatabaseConsts;
import com.wolvescoding.nownovel.dao.entity.BookComment;
import com.wolvescoding.nownovel.dao.entity.UserInfo;
import com.wolvescoding.nownovel.dao.mapper.BookCommentMapper;
import com.wolvescoding.nownovel.dto.req.UserCommentReqDto;
import com.wolvescoding.nownovel.dto.resp.BookCommentRespDto;
import com.wolvescoding.nownovel.manager.dao.UserDaoManager;
import com.wolvescoding.nownovel.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookCommentMapper bookCommentMapper;
    private final UserDaoManager userDaoManager;



    @Override
    public RestResp<Void> saveComment(UserCommentReqDto dto) {
       QueryWrapper<BookComment> queryWrapper = new QueryWrapper<>();
       queryWrapper.eq(DatabaseConsts.BookCommentTable.COLUMN_USER_ID, dto.getUserId())
                .eq(DatabaseConsts.BookCommentTable.COLUMN_BOOK_ID, dto.getBookId());
        if(bookCommentMapper.selectCount(queryWrapper) > 0){
           return RestResp.fail(ErrorCodeEnum.USER_COMMENT_REPEAT);
       }
        BookComment bookComment = new BookComment();
        bookComment.setBookId(dto.getBookId());
        bookComment.setUserId(dto.getUserId());
        bookComment.setCommentContent(dto.getCommentContent());
        bookComment.setCreateTime(LocalDateTime.now());
        bookComment.setUpdateTime(LocalDateTime.now());
        return RestResp.ok();
    }
    @Override
    public RestResp<Void> updateComment(Long userId, Long id, String content) {
        QueryWrapper<BookComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.CommonColumnEnum.ID.getName(), id)
                .eq(DatabaseConsts.BookCommentTable.COLUMN_USER_ID, userId);
        BookComment bookComment = new BookComment();
        bookComment.setCommentContent(content);
        bookCommentMapper.update(bookComment, queryWrapper);
        return RestResp.ok();
    }
    @Override
    public RestResp<Void> deleteComment(Long userId, Long commentId) {
        QueryWrapper<BookComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.CommonColumnEnum.ID.getName(), commentId)
                .eq(DatabaseConsts.BookCommentTable.COLUMN_USER_ID, userId);
        bookCommentMapper.delete(queryWrapper);
        return RestResp.ok();
    }
    @Override
    public RestResp<BookCommentRespDto> listNewestComments(Long bookId) {
        QueryWrapper<BookComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.BookCommentTable.COLUMN_BOOK_ID, bookId);
        Long commentTotal = bookCommentMapper.selectCount(queryWrapper);
        BookCommentRespDto bookCommentRespDto = BookCommentRespDto.builder().commentTotal(commentTotal).build();
        if(commentTotal > 0){
            QueryWrapper<BookComment> commentQueryWrapper = new QueryWrapper<>();
            commentQueryWrapper.eq(DatabaseConsts.BookCommentTable.COLUMN_BOOK_ID, bookId)
                    .orderByDesc(DatabaseConsts.CommonColumnEnum.CREATE_TIME.getName())
                    .last(DatabaseConsts.SqlEnum.LIMIT_5.getSql());
            List<BookComment> bookComments = bookCommentMapper.selectList(commentQueryWrapper);

            List<Long> userIds = bookComments.stream().map(BookComment::getUserId).toList();
            List<UserInfo> userInfos = userDaoManager.listUsers(userIds);
            Map<Long, String> userInfoMap = userInfos.stream().collect(Collectors.toMap(UserInfo::getId, UserInfo::getUsername));
            List<BookCommentRespDto.CommentInfo> commentInfos = bookComments.stream()
                    .map(v -> BookCommentRespDto.CommentInfo.builder()
                            .id(v.getId())
                            .commentUserId(v.getUserId())
                            .commentUser(userInfoMap.get(v.getUserId()))
                            .commentContent(v.getCommentContent())
                            .commentTime(v.getCreateTime()).build()).toList();
            bookCommentRespDto.setComments(commentInfos);
        }
        else{
            bookCommentRespDto.setComments(Collections.emptyList());
        }
        return RestResp.ok(bookCommentRespDto);

    }

}
