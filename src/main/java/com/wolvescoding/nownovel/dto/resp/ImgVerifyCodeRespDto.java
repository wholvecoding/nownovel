package com.wolvescoding.nownovel.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data//@Data注解会自动生成getter和setter方法，@Data注解相当于@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode这5
@Builder//@Builder注解会生成builder方法，用于创建对象
public class ImgVerifyCodeRespDto {
    private String sessionId;
    private String img;
}
