package com.wolvescoding.nownovel.controller.front;

import com.wolvescoding.nownovel.core.common.resp.RestResp;
import com.wolvescoding.nownovel.dto.resp.HomeBookRespDto;
import com.wolvescoding.nownovel.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wolvescoding.nownovel.core.constant.ApiRouterConsts;
import java.util.List;

@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_HOME_URL_PREFIX)
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;
    @GetMapping("books")
    public RestResp<List<HomeBookRespDto>> listHomeBooks() {
        return homeService.listHomeBooks();
    }



}
