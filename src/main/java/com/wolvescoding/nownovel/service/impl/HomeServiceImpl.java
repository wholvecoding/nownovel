package com.wolvescoding.nownovel.service.impl;

import com.wolvescoding.nownovel.core.common.resp.RestResp;
import com.wolvescoding.nownovel.dto.resp.HomeBookRespDto;
import com.wolvescoding.nownovel.manager.HomebBookCacheManager;
import com.wolvescoding.nownovel.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final HomebBookCacheManager homebBookCacheManager;



    @Override
    public RestResp<List<HomeBookRespDto>> listHomeBooks() {
        return RestResp.ok(homebBookCacheManager.listHomeBooks());
    }

}
