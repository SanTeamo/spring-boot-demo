package com.santeamo.demo.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement(name = "OneElement")
public class OneElement {

    @XmlElement(name = "EleOne")
    private String eleOne;

    @XmlElement(name = "EleTwo")
    private String eleTwo;

    public String getEleOne() {
        return eleOne;
    }

    public void setEleOne(String eleOne) {
        this.eleOne = eleOne;
    }

    public String getEleTwo() {
        return eleTwo;
    }

    public void setEleTwo(String eleTwo) {
        this.eleTwo = eleTwo;
    }

    @Override
    public String toString() {
        return "OneElement{" +
                "eleOne='" + eleOne + '\'' +
                ", eleTwo='" + eleTwo + '\'' +
                '}';
    }
}
