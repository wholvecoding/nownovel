package com.wolvescoding.nownovel.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterRespDto {

    //@Schema(description = "用户ID")
    private Long uid;

    //@Schema(description = "用户token")
    private String token;
}
