package com.santeamo.demo.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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
        return postJsonMsg(restTemplate, url, msg);
    }

    public static String postJsonMsg(RestTemplate restTemplate, String url, String msg) {
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

    public static RestTemplate getRestTemplate(ResponseErrorHandler responseErrorHandler) {
        RestTemplate restTemplate = getRestTemplate();
        restTemplate.setErrorHandler(responseErrorHandler);
        return restTemplate;
    }

    public static class MyResponseErrorHandler extends DefaultResponseErrorHandler {
        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            int rawStatusCode = response.getRawStatusCode();
            if (rawStatusCode / 100 != 5) {
                super.handleError(response);
            }
        }
    }

}
