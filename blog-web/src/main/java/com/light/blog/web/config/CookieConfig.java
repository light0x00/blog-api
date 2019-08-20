package com.light.blog.web.config;

import lombok.Data;

@Data
public class CookieConfig {

    String domain;
    String path;
    String tokenKey;
    int maxAge;

}