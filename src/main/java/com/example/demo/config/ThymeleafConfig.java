package com.example.demo.config;

import com.querydsl.core.annotations.Config;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;

@Config
public class ThymeleafConfig {

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
