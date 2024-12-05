
package com.wolvescoding.nownovel.dao.mapper;

import com.wolvescoding.nownovel.dao.entity.BookInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 小说信息 Mapper 接口
 * </p>
 *
 * @author wolvescoding
 * @since 2024/12/03
 */
public interface BookInfoMapper extends BaseMapper<BookInfo> {
        void addVisitCount(@Param("bookId") Long bookId);
}
