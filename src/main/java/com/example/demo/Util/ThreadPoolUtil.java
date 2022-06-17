package com.example.demo.Util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolUtil {
    
    @Bean
    ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
        t.setCorePoolSize(10);
        t.setMaxPoolSize(10);
        t.setQueueCapacity(100);
        return t;
    }
}
