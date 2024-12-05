package com.wolvescoding.nownovel.core.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举类。
 *
 * 错误码为字符串类型，共 5 位，分成两个部分：错误产生来源+四位数字编号。
 * 错误产生来源分为 U/S/T， U 表示错误来源于用户，比如参数错误，用户安装版本过低，用户支付
 * 超时等问题； S 表示错误来源于当前系统，往往是业务逻辑出错，或程序健壮性差等问题； T 表示错误来源
 * 于第三方服务，比如 CDN 服务出错，消息投递超时等问题；四位数字编号从 0001 到 9999，大类之间的
 * 步长间距预留 100。
 *
 * 错误码分为一级宏观错误码、二级宏观错误码、三级宏观错误码。
 * 在无法更加具体确定的错误场景中，可以直接使用一级宏观错误码。
 *
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    OK("00000", "成功"),
    USER_ERROR("U0001","用户端错误"),
    USER_REGISTER_ERROR("U0100","用户注册错误"),

    USER_NO_AGREE_PRIVATE_ERROR("U0101","用户未同意隐私隐条款"),
    USER_REGISTER_AREA_LIMIT_ERROR("U0102","用户注册区域受限"),
    USER_VERIFY_CODE_ERROR("u0103","验证码错误" ),
    USER_NAME_EXIST("u0104","用户名已经存在" ),
    USER_NOT_EXIST("U0105","用户不存在" ),
    USER_PASSWORD_WRONG("U0106","用户密码错误" ),
    USER_COMMENT_REPEAT("U0107", "每一个用户只能对一本书评论一次"),
    USER_LOGIN_EXPIRED("u0108",  "用户登录信息失效"),
    USER_REQUEST_PARAMETER_ERROR("Uo400","用户请求参数错误" ),
    AUTHOR_ACCOUNT_NOT_EXISTS("U0401","作家账号不存在" ),
    SYSTEM_ERROR("S0001","系统执行出错"),
    SYSTEM_TIMEOUT_ERROR("S0100","系统执行超时"),
   THIRD_SERVER_ERROR("T0001","调用第三方服务出错"),
    MIDDLE_SERVER_ERROR("T0100","中间件服务出错"),
    AUTHOR_BOOK_NAME_EXIST("UA001","该作者书籍不存在" ),
    USER_UN_AUTH("UA002","你不具备作者的权限" );
    private String code;
    private String msg;




}
