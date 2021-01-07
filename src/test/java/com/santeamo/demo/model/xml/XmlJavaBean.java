package com.santeamo.demo.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * FileName JavaBean.java
 *
 * Description
 *
 * @author SanTeAmo
 * @date 2020/12/25 11:27
 * @version V1.0
 **/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "RootEle")
public class XmlJavaBean extends XmlBasicJavaBean {

    @XmlElement(name = "ElementA")
    private String elementA;

    @XmlElement(name = "ElementB")
    private String elementB;

    @XmlElement(name = "ElementList")
    private ElementList elementList;

    public String getElementA() {
        return elementA;
    }

    public void setElementA(String elementA) {
        this.elementA = elementA;
    }

    public String getElementB() {
        return elementB;
    }

    public void setElementB(String elementB) {
        this.elementB = elementB;
    }

    public ElementList getElementList() {
        return elementList;
    }

    public void setElementList(ElementList elementList) {
        this.elementList = elementList;
    }

    @Override
    public String toString() {
        return "XmlJavaBean{" +
                super.toString() +
                ", elementA='" + elementA + '\'' +
                ", elementB='" + elementB + '\'' +
                ", elementList=" + elementList +
                '}';
    }
}
