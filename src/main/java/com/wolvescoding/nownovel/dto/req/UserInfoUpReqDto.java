package com.wolvescoding.nownovel.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.checkerframework.checker.units.qual.Length;
@Data
public class UserInfoUpReqDto {
    private Long userId;

    @Schema(description = "昵称")
   // @Length(min = 2,max = 10)
    private String nickName;

    @Schema(description = "头像地址")
    //@Pattern(regexp="^/[^\s]{10,}\\.(png|PNG|jpg|JPG|jpeg|JPEG|gif|GIF|bpm|BPM)$")
    private String userPhoto;

    @Schema(description = "性别")
    //@Min(value = 0)
    //@Max(value = 1)
    private Integer userSex;
}
