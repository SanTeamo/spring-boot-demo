package com.santeamo.demo.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * FileName XmlBasicJavaBean.java
 *
 * Description
 *
 * @author SanTeAmo
 * @date 2020/12/25 11:58
 * @version V1.0
 **/
@XmlAccessorType(XmlAccessType.NONE)
public class XmlBasicJavaBean {

    @XmlElement(name = "BasicEleA")
    private String basicEleA;

    @XmlElement(name = "BasicEleB")
    private Integer basicEleB;

    public String getBasicEleA() {
        return basicEleA;
    }

    public void setBasicEleA(String basicEleA) {
        this.basicEleA = basicEleA;
    }

    public Integer getBasicEleB() {
        return basicEleB;
    }

    public void setBasicEleB(Integer basicEleB) {
        this.basicEleB = basicEleB;
    }

    @Override
    public String toString() {
        return "XmlBasicJavaBean{" +
                "basicEleA='" + basicEleA + '\'' +
                ", basicEleB=" + basicEleB +
                '}';
    }
}
