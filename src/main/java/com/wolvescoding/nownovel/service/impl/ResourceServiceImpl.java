package com.wolvescoding.nownovel.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.wolvescoding.nownovel.core.common.resp.RestResp;
import com.wolvescoding.nownovel.dto.resp.ImgVerifyCodeRespDto;
import com.wolvescoding.nownovel.manager.VerifyCodeManager;
import com.wolvescoding.nownovel.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ResourceServiceImpl implements ResourceService {
    private final VerifyCodeManager verifyCodeManager;
    @Override
    public RestResp<ImgVerifyCodeRespDto> getImgVerifyCode() throws IOException {
        String sessionId = IdWorker.get32UUID();
        return RestResp.ok(ImgVerifyCodeRespDto.builder().
                sessionId(sessionId).img(verifyCodeManager.genVerifyCode(sessionId))
                .build());
    }
}
