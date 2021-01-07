package com.santeamo.demo.utils;

import com.santeamo.demo.model.xml.XmlJavaBean;
import org.junit.Test;

/**
 * FileName XmlUtilTest.java
 *
 * Description
 *
 * @author SanTeAmo
 * @date 2020/12/25 11:22
 * @version V1.0
 **/
public class XmlUtilTest {

    @Test
    public void converyToJavaBean() {
        String xmlStr = "<RootEle>" +
                "<BasicEleA>Success</BasicEleA>" +
                "<BasicEleB>1</BasicEleB>" +
                "<ElementA>A</ElementA>" +
                "<ElementB>B</ElementB>" +
                "<ElementList>" +
                "<OneElement>" +
                "<EleOne>AA</EleOne>" +
                "<EleTwo>AB</EleTwo>" +
                "</OneElement>" +
                "<OneElement>" +
                "<EleOne>BA</EleOne>" +
                "<EleTwo>BB</EleTwo>" +
                "</OneElement>" +
                "</ElementList>" +
                "</RootEle>";
        XmlJavaBean xmlJavaBean = XmlUtil.converyToJavaBean(xmlStr, XmlJavaBean.class);
        System.out.println(xmlJavaBean);
    }
}