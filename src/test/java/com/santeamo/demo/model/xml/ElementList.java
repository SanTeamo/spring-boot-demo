package com.santeamo.demo.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

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
@XmlRootElement(name = "ElementList")
public class ElementList {

    @XmlElement(name = "OneElement")
    private List<OneElement> oneElementList;

    public List<OneElement> getOneElementList() {
        return oneElementList;
    }

    public void setOneElementList(List<OneElement> oneElementList) {
        this.oneElementList = oneElementList;
    }

    @Override
    public String toString() {
        return "ElementList{" +
                "oneElementList=" + oneElementList +
                '}';
    }
}
