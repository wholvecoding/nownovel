package com.wolvescoding.nownovel.manager;

import com.wolvescoding.nownovel.core.common.util.ImgVerifyCodeUtils;
import com.wolvescoding.nownovel.core.constant.CacheConsts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class VerifyCodeManager {
    private final StringRedisTemplate stringRedisTemplate;
    // 生成验证码 redis
    public String genVerifyCode(String sessionId) throws IOException {
        String  verifyCode = ImgVerifyCodeUtils.getVerifyCode(4);
        String img = ImgVerifyCodeUtils.genVerifyCodeImg(verifyCode);
        stringRedisTemplate.opsForValue().set(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY+sessionId, verifyCode, Duration.ofMinutes(4));
        return img;

    }
    // 验证验证码 redis
    public boolean imgVerifyCodeOk(String sessionId, String code) {
        return Objects.equals(stringRedisTemplate.opsForValue().get(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY + sessionId), code);
    }
    public void removeImgVerifyCode(String sessionId) {
        stringRedisTemplate.delete(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY + sessionId);
    }

}
