package com.santeamo.demo.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * FileName Base64Helper.java
 * <p>
 * Description
 *
 * @author SanTeAmo
 * @version V1.0
 * @date 2020/12/18 14:30
 **/
public class Base64Helper {

    public static String decodeString(String encodeString) {
        return decodeString(encodeString, UTF_8);
    }

    public static String decodeString(String encodeString, Charset charset) {
        if (StringUtils.isBlank(encodeString)) {
            return encodeString;
        }
        encodeString = encodeString.trim();
        byte[] decodeMsgBytes = Base64.getDecoder().decode(encodeString);
        return new String(decodeMsgBytes, charset);
    }

    public static String decodeString(String encodeString, String charsetName) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(encodeString)) {
            return encodeString;
        }
        encodeString = encodeString.trim();
        byte[] decodeMsgBytes = Base64.getDecoder().decode(encodeString);
        return new String(decodeMsgBytes, charsetName);
    }

    public static String encodeString(String str) {
        return encodeString(str, UTF_8);
    }

    public static String encodeString(String str, Charset charset) {
        byte[] bytes = str.getBytes(charset);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String encodeString(String str, String charsetName) throws UnsupportedEncodingException {
        byte[] bytes = str.getBytes(charsetName);
        return Base64.getEncoder().encodeToString(bytes);
    }
}
