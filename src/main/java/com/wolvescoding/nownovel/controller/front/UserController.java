package com.wolvescoding.nownovel.controller.front;

import com.wolvescoding.nownovel.core.auth.UserHolder;
import com.wolvescoding.nownovel.core.common.req.PageReqDto;
import com.wolvescoding.nownovel.core.common.resp.PageRespDto;
import com.wolvescoding.nownovel.core.common.resp.RestResp;
import com.wolvescoding.nownovel.core.constant.ApiRouterConsts;
import com.wolvescoding.nownovel.dto.req.UserCommentReqDto;
import com.wolvescoding.nownovel.dto.req.UserInfoUpReqDto;
import com.wolvescoding.nownovel.dto.req.UserLoginReqDto;
import com.wolvescoding.nownovel.dto.req.UserRegisterReqDto;
import com.wolvescoding.nownovel.dto.resp.UserInfoRespDto;
import com.wolvescoding.nownovel.dto.resp.UserLoginRespDto;
import com.wolvescoding.nownovel.dto.resp.UserRegisterRespDto;
import com.wolvescoding.nownovel.dto.resp.UsercommentRespDto;
import com.wolvescoding.nownovel.service.BookService;
import com.wolvescoding.nownovel.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_USER_URL_PREFIX)
@RequiredArgsConstructor
@Tag(name="UserController",description = "用户相关操作")
public class UserController {
    private final UserService userService;
    private final BookService bookService;
   @Operation(summary ="用户注册接口")
    @PostMapping("/register")
    public RestResp<UserRegisterRespDto> register(@RequestBody UserRegisterReqDto req) {
        return userService.register(req);
    }
  @Operation(summary ="用户登录接口")
    @PostMapping("/login")
    public RestResp<UserLoginRespDto> login( @RequestBody UserLoginReqDto req) {
        return userService.login(req);
    }
     /**
     * 发表评论接口
     */
     @GetMapping
     @Operation(summary ="用户信息查询接口")
     public RestResp<UserInfoRespDto> getUserInfo(){

         return userService.getUserInfo(UserHolder.getUserId());
     }
     @PutMapping
     @Operation(summary ="用户信息修改接口")
     public RestResp<Void> updateUserInfo(@RequestBody UserInfoUpReqDto dto){
         return userService.updataUserInfo(dto);
     }
    @PostMapping("/feedback")
    @Operation(summary ="用户反馈接口")
     public RestResp<Void> submitFeedback(@RequestBody String content){
         return userService.saveFeedback(UserHolder.getUserId(), content);
     }
     @Operation(summary ="删除反馈接口")
     @DeleteMapping("/feedback/{id}")
     public RestResp<Void> deleteFeedback(@PathVariable("id") Long id){
         return userService.deleteFeedback(UserHolder.getUserId(), id);
     }

  @Operation(summary ="发表评论")
    @PostMapping("comment")
    public RestResp<Void> comment( @RequestBody UserCommentReqDto dto) {
        dto.setUserId(UserHolder.getUserId());
        return bookService.saveComment(dto);
    }
    /**
     * 修改评论接口
     */
    @PutMapping("comment/{id}")
    @Operation(summary ="修改评论")
    public RestResp<Void> updateComment(@PathVariable Long id, String content) {
        return bookService.updateComment(UserHolder.getUserId(), id, content);
    }
    /**
     * 删除评论接口
     */
    @DeleteMapping("comment/{id}")//这个id式评论的id
    @Operation(summary ="删除评论")
    public RestResp<Void> deleteComment(@PathVariable Long id) {
        return bookService.deleteComment(UserHolder.getUserId(), id);
    }


    @GetMapping("bookshelf_status")
    @Operation(summary ="获取书架状态")
    public RestResp<Integer> getBookshelfStatus(String bookId){
        return userService.getBookshelfStatus(UserHolder.getUserId(), bookId);
    }
    @GetMapping("comments")
    @Operation(summary ="获取评论列表")
    public RestResp<PageRespDto<UsercommentRespDto>> listComments(PageReqDto pageReqDto){
        System.out.println("ss"+UserHolder.getUserId());

        return bookService.listComments(UserHolder.getUserId(),pageReqDto);
    }


}
