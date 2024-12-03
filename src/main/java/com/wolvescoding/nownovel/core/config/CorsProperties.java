package com.wolvescoding.nownovel.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 跨域配置属性
 */

@ConfigurationProperties(prefix = "nownovel.cors")
@Data
public class CorsProperties {
    private List<String> allowedOrigins;

}
