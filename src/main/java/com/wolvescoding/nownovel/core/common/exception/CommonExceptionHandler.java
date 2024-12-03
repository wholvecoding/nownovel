package com.wolvescoding.nownovel.core.common.exception;

import com.wolvescoding.nownovel.core.common.constant.ErrorCodeEnum;
import com.wolvescoding.nownovel.core.common.resp.RestResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.net.BindException;
import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {
    /*
     * 数据校验异常
     */
    @ExceptionHandler(BindException.class)
    public RestResp<Void> handlerBindException(BindException e) {
        log.error(e.getMessage(), e);
        return RestResp.fail(ErrorCodeEnum.USER_REQUEST_PARAMETER_ERROR);
    }
    /*
     * 业务异常
     */
    @ExceptionHandler(Exception.class)
    public RestResp<Void> handleException(Exception e) {
        if (e instanceof NullPointerException) {
            log.error("空指针异常：", e);

        } else if (e instanceof SQLException) {
            log.error("数据库异常：", e);

        } else if (e instanceof IOException) {
            log.error("IO异常：", e);
        } else {
            log.error("系统异常：", e);
        }
        return RestResp.error();
    }

    /*
       * 系统异常
     */    @ExceptionHandler(BusinessException.class)
    public RestResp<Void> handlerBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return RestResp.fail(e.getErrorCodeEnum());
    }
}
