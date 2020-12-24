package com.santeamo.demo.utils;

import java.io.UnsupportedEncodingException;

/**
 * FileName StringHelper.java
 *
 * Description
 *
 * @author SanTeAmo
 * @date 2020/12/18 13:31
 * @version V1.0
 **/
public class StringHelper {

    public static void codeLengthInfo(String s) throws UnsupportedEncodingException {
        System.out.printf("字符串：%s, 长度:%d\n", s, s.length());
        System.out.printf("默认编码：%s, 字节数:%d%n", System.getProperty("file.encoding"), s.getBytes().length);
        System.out.printf("编码：%s, 字节数:%d%n", "utf8", s.getBytes("utf8").length);
        System.out.printf("编码：%s, 字节数:%d%n", "utf16", s.getBytes("utf16").length);
        System.out.printf("编码：%s, 字节数:%d%n", "gb2312", s.getBytes("gb2312").length);
        System.out.printf("编码：%s, 字节数:%d%n", "gbk", s.getBytes("gbk").length);
    }

}
