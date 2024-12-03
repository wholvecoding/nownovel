package com.wolvescoding.nownovel.core.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolvescoding.nownovel.core.auth.AuthStrategy;
import com.wolvescoding.nownovel.core.auth.UserHolder;
import com.wolvescoding.nownovel.core.common.constant.ErrorCodeEnum;
import com.wolvescoding.nownovel.core.common.exception.BusinessException;
import com.wolvescoding.nownovel.core.common.resp.RestResp;
import com.wolvescoding.nownovel.core.constant.ApiRouterConsts;
import com.wolvescoding.nownovel.core.constant.SystemConfigConsts;
import com.wolvescoding.nownovel.core.util.JwtUtils;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

//@RequiredArgsConstructor
//public class AuthInterceptor implements HandlerInterceptor {
//    private final JwtUtils  jwtUtils;
//    private final ObjectMapper objectMapper;
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
//        String token = request.getHeader(SystemConfigConsts.HTTP_AUTH_HEADER_NAME);//获取token
//        if(!Objects.isNull(token)){
//            String requestURI = request.getRequestURI();
//            if (requestURI.contains(ApiRouterConsts.API_FRONT_URL_PREFIX)) {
//                Long userId = jwtUtils.paraseToken(token, SystemConfigConsts.NOVEL_FRONT_KEY);
//                if(!Objects.isNull(userId)){
//                    return HandlerInterceptor.super.preHandle(request, response, handler);
//                }
//            } else if(requestURI.contains(ApiRouterConsts.API_AUTHOR_URL_PREFIX)){
//
//            } else if(requestURI.contains(ApiRouterConsts.API_ADMIN_URL_PREFIX)){
//
//            }
//        }
//        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.getWriter().write(objectMapper.writeValueAsString(RestResp.fail(ErrorCodeEnum.USER_LOGIN_EXPIRED)));
//        return false;
//    }
//
//}

/**专门用来处理http请求的拦截器
 *
 */
@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final Map<String, AuthStrategy> authStrategy;
    private final ObjectMapper objectMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //获取JWTtoken
        String token = request.getHeader(SystemConfigConsts.HTTP_AUTH_HEADER_NAME);//获取token
        //获取请求的uri
        String requestUri = request.getRequestURI();
        //获取系统名称
        String subUri = requestUri.substring(ApiRouterConsts.API_URL_PREFIX.length() + 1);
        String systemName = subUri.substring(0,subUri.indexOf("/"));
        String authStrategyName = String.format("%sAuthStrategy",systemName);
        try {
            authStrategy.get(authStrategyName).auth(token, requestUri);
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        catch(BusinessException exception){
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(RestResp.fail(exception.getErrorCodeEnum())));
            return false;
        }
    }

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    @SuppressWarnings("NullableProblems")
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // 清理当前线程保存的用户数据
        UserHolder.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}