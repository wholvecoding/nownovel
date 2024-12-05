package com.wolvescoding.nownovel.core.interceptor;

import com.wolvescoding.nownovel.core.auth.UserHolder;
import com.wolvescoding.nownovel.core.constant.SystemConfigConsts;
import com.wolvescoding.nownovel.core.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class TokenParseInterceptor  implements HandlerInterceptor {
    private final JwtUtils jwtUtils;

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 获取登录 JWT
        String token = request.getHeader(SystemConfigConsts.HTTP_AUTH_HEADER_NAME);
        if (StringUtils.hasText(token)) {
            // 解析 token 并保存
            UserHolder.setUserId(jwtUtils.paraseToken(token, SystemConfigConsts.NOVEL_FRONT_KEY));
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * DispatcherServlet 完全处理完请求后调用，出现异常照常调用
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // 清理当前线程保存的用户数据
        UserHolder.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
