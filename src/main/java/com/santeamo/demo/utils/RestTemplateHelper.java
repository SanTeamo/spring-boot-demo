package com.santeamo.demo.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * FileName RestTemplateHelper.java
 *
 * Description
 *
 * @author SanTeAmo
 * @date 2020/12/18 10:48
 * @version V1.0
 **/
public class RestTemplateHelper {

    public static String postJsonMsg(String url, String msg) {
        RestTemplate restTemplate = getRestTemplate();
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        String contentType = "application/json;charset=utf-8";
        headers.setContentType(MediaType.parseMediaType(contentType));
        HttpEntity<String> httpEntity = new HttpEntity<>(msg, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        return responseEntity.getBody();
    }

    public static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        HttpMessageConverter<?> httpMessageConverter;
        for (int i = 0; i < messageConverters.size(); i++) {
            httpMessageConverter = messageConverters.get(i);
            if (httpMessageConverter.getClass().equals(StringHttpMessageConverter.class)) {
                messageConverters.set(i, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            }
        }
        return restTemplate;
    }

}
