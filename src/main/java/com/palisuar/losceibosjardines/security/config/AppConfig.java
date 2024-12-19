package com.palisuar.losceibosjardines.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
    @Value("${app.base-url}")
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }
}
