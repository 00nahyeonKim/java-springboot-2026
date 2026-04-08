package com.pknu26.openapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

//  application.properties에 신규로 추가한 설정 그룹
@ConfigurationProperties(prefix = "redtable")
public class PublicDataProperties {
    
}
