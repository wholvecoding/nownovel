package com.wolvescoding.nownovel.controller.front;

import com.wolvescoding.nownovel.core.common.resp.RestResp;
import com.wolvescoding.nownovel.dto.resp.ImgVerifyCodeRespDto;
import com.wolvescoding.nownovel.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import  com.wolvescoding.nownovel.core.constant.ApiRouterConsts;
@RequiredArgsConstructor
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_RESOURCE_URL_PREFIX)

public class ResourceController {
    private final ResourceService resourceService;
    @GetMapping("img_verify_code")
    public RestResp<ImgVerifyCodeRespDto> getImgVerifyCode() throws Exception {
        return resourceService.getImgVerifyCode();

    }
}
