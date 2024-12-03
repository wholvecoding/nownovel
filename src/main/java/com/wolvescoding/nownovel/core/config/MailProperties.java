package com.wolvescoding.nownovel.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.mail")
public record MailProperties(String nickname, String username) {
}
