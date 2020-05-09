package com.santeamo.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Title:
 * @ClassName: com.santeamo.demo.configuration.MyWebMvcConfig.java
 * @Description: MVC配置
 *
 * @author: santeamo
 * @date:  2019-08-30 上午 9:29
 * @version V1.0
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
