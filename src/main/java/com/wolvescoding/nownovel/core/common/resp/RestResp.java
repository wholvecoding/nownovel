package com.wolvescoding.nownovel.core.common.resp;

import com.wolvescoding.nownovel.core.common.constant.ErrorCodeEnum;
import lombok.Getter;

import java.util.Objects;

/*
 * Http Rest 响应工具及数据格式封装

 */
@Getter
public class RestResp <T> {
    private String code;
    private String message;
    private T data;

    private RestResp() {
        this.code = ErrorCodeEnum.OK.getCode();
        this.message = ErrorCodeEnum.OK.getMsg();
    }

    private RestResp(ErrorCodeEnum errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
    }

    private RestResp(T data) {
        this();
        this.data = data;
    }
    public static RestResp<Void> ok(){
        return new RestResp<>();
    }
    public static <T> RestResp<T> ok(T data){
        return new RestResp<>(data);
    }
    public static RestResp<Void> fail(ErrorCodeEnum errorCode){
        return new RestResp<>(errorCode);
    }
    public static RestResp<Void> error(){
        return new RestResp<>(ErrorCodeEnum.SYSTEM_ERROR);
    }
    public boolean isOk(){
        return Objects.equals(this.code, ErrorCodeEnum.OK.getCode());
    }
}
