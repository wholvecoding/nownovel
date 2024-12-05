package com.wolvescoding.nownovel.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookCategoryRespDto {

    /**
     * 类别ID
     */
    @Schema(description = "类别ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 类别名
     */
    @Schema(description = "类别名")
    private String name;
}
