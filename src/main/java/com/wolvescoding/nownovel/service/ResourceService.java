package com.wolvescoding.nownovel.service;

import com.wolvescoding.nownovel.core.common.resp.RestResp;
import com.wolvescoding.nownovel.core.common.util.ImgVerifyCodeUtils;
import com.wolvescoding.nownovel.dto.resp.ImgVerifyCodeRespDto;

public interface ResourceService {
    public RestResp<ImgVerifyCodeRespDto> getImgVerifyCode() throws Exception ;
}
