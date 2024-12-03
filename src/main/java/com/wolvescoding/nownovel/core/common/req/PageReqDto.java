package com.wolvescoding.nownovel.core.common.req;

import lombok.Data;

@Data
public class PageReqDto {
    private int pageNum = 1;
    private int pageSize = 10;

    private boolean fetchall = false;
}
