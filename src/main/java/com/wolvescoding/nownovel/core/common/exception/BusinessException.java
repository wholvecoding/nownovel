package com.wolvescoding.nownovel.core.common.exception;

import com.wolvescoding.nownovel.core.common.constant.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {
    private final ErrorCodeEnum errorCodeEnum;
    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        /* super的构造函数
           protected RuntimeException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
         */
        super(errorCodeEnum.getMsg(),null,false, false);
        this.errorCodeEnum = errorCodeEnum;
    }


}
