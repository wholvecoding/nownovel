package com.wolvescoding.nownovel;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.cache.CacheManager;

import java.util.Map;

@MapperScan("com.wolvescoding.nownovel.dao.mapper")
@EnableCaching
@Slf4j
@SpringBootApplication(scanBasePackages = "com.wolvescoding.nownovel")
public class NownovelApplication {

    public static void main(String[] args) {
        SpringApplication.run(NownovelApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext context) {
//        return args -> {
//            Map<String, CacheManager> beans = context.getBeansOfType(CacheManager.class);
//            log.info("加载了以下的缓存管理器：");
//            beans.forEach((k,v)->{
//                log.info("{}:{}", k, v.getClass().getName());
//                log.info("缓存:{}", v.getCacheNames());
//            });
//
//        };
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .securityMatcher(EndpointRequest.toAnyEndpoint())
//                .authorizeHttpRequests(requests -> requests.anyRequest().hasRole("ENDPOINT_ADMIN"));
//        http.httpBasic();
//        return http.build();
//    }
}
