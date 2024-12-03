package com.wolvescoding.nownovel.service;

import com.wolvescoding.nownovel.core.common.resp.RestResp;
import com.wolvescoding.nownovel.dto.resp.HomeBookRespDto;

import java.util.List;

public interface HomeService {
    RestResp<List<HomeBookRespDto>> listHomeBooks();
}
