package com.wolvescoding.nownovel.dto.resp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserLoginRespDto {
    private String token;
    private Long uid;
    private String nickName;
}
