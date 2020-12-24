package com.santeamo.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * FileName XmlUtil.java
 *
 * Description xml转换工具类
 *
 **/
public class XmlUtil {

    public static final String RESPONSE_NAMESPACE_START = "<string xmlns=\"http://tempuri.org/\">";
    public static final String RESPONSE_NAMESPACE_END = "</string>";
    public static final String RESPONSE_KEY_NAME = "response";

    /**
     * 根据map获取xml
     * @param map
     * @param rootName
     * @return
     */
    public static String getMessage(Map<String, Object> map, String rootName) {
        return getMessage(map, rootName, "UTF-8");
    }

    /**
     * 根据map获取xml
     * @param map
     * @param rootName 节点名
     * @param charaset
     * @return
     */
    public static String getMessage(Map<String, Object> map, String rootName, String charaset) {
        Document doc = DocumentHelper.createDocument();
        doc.setXMLEncoding(charaset);
        doc.addElement(rootName);
        getMessage(doc.getRootElement(), map);
        return doc.getRootElement().asXML();
    }

    /**
     * @param root 根节点
     * @param map 节点信息
     */
    public static void getMessage(Element root, Map<String, Object> map) {
        addElementByMap(root, map);
    }

    /**
     * @param parent 父节点
     * @param map 当前节点信息
     */
    private static void addElementByMap(Element parent, Map<String, Object> map) {
        if (map == null) {
            parent.setText("");
            return;
        }

        for (String key : map.keySet()) {
            addElementByString(parent, key, map.get(key));
        }
    }

    /**
     * @param parent 父节点
     * @param listNode 当前节点名
     * @param value 当前节点的LIST信息
     */
    @SuppressWarnings("unchecked")
    private static void addElementByList(Element parent, String listNode, List value) {
        int size = value.size();
        if (size == 0) {
            Element node = parent.addElement(listNode);
            node.setText("");
            return;
        }
        for (int i = 0; i < size; i++) {
            Element node = parent.addElement(listNode);
            Object nodeValue = value.get(i);
            // List 中只能有String和Map类型，再有List类型就没有意义了
            // 所以这里只分析了两种类型
            if (nodeValue instanceof String) {
                node.setText((String) nodeValue);
            } else if (nodeValue instanceof Map) {
                addElementByMap(node, (Map) nodeValue);
            }
        }
    }

    /**
     * @param parent 父节点
     * @param nodePath 节点名信息 如：head/toll/value
     * @param value 节点值
     */
    @SuppressWarnings("unchecked")
    private static void addElementByString(Element parent, String nodePath, Object value) {
        String[] nodes = nodePath.split("/");
        int size = nodes.length;
        for (int i = 0; i < size - 1; i++) {
            if (parent.element(nodes[i]) == null) {
                parent = parent.addElement(nodes[i]);
            } else {
                parent = parent.element(nodes[i]);
            }
        }
        //list的情况比较特殊
        if (value instanceof List) {
            addElementByList(parent, nodes[size - 1], (List) value);
        } else {
            if (parent.element(nodes[size - 1]) == null) {
                parent = parent.addElement(nodes[size - 1]);
            } else {
                parent = parent.element(nodes[size - 1]);
            }
            if (value instanceof String) {
                parent.setText((String)value);
            } else if (value instanceof Map) {
                addElementByMap(parent, (Map) value);
            } else if (value == null) {
                parent.setText("NULL");
            } else {
                parent.setText(value.toString());
            }
        }
    }

    /**
     * 获取response
     * @param xmlStr
     * @return
     * @throws DocumentException
     */
    public static Map<String, Object> getTcmResponseMap(String xmlStr) throws DocumentException {

        xmlStr = xmlStr.substring(xmlStr.indexOf(RESPONSE_NAMESPACE_START) + RESPONSE_NAMESPACE_START.length(),
                xmlStr.indexOf(RESPONSE_NAMESPACE_END));

        return (Map<String, Object>) getElements(xmlStr).get(RESPONSE_KEY_NAME);
    }

    /**
     * 获取responseJson
     * @param xmlStr
     * @return
     * @throws DocumentException
     */
    public static String getResponseJson(String xmlStr) throws DocumentException {

        xmlStr = xmlStr.substring(xmlStr.indexOf(RESPONSE_NAMESPACE_START) + RESPONSE_NAMESPACE_START.length(),
                xmlStr.indexOf(RESPONSE_NAMESPACE_END));

        return xmlStr;
    }

    /**
     * xml 转 map
     * @param xmlStr
     * @return
     * @throws DocumentException
     */
    public static Map<String, Object> getElements(String xmlStr) throws DocumentException {
        return getElements(DocumentHelper.parseText(xmlStr).getRootElement());
    }

    /**
     * element 转 map
     * @param element
     * @return
     */
    @SuppressWarnings("unchecked")
    private static Map<String, Object> getElements(Element element) {
        Map<String, Object> result = new HashMap<>(8);
        List<Element> children = element.elements();
        if (null == children) {
            return result;
        }
        for (Element e : children) {
            //如果当前节点的子节点为空时，则向map中添加节点
            if (e.elements().isEmpty()) {
                //如果返回的Map中已经包含了当前节点，分为两种情况
                //1.如果value类型是List，则向list中增加当前节点的text
                //2.如果value类型不是List，也就是单值，则将value的值塞入新增的List中，再将当前节点的text也塞入其中
                if (result.containsKey(e.getName())) {
                    if (result.get(e.getName()) instanceof List) {
                        ((List)result.get(e.getName())).add(!"NULL".equals(e.getText()) ? e.getText() : null);
                    } else {
                        List list = new ArrayList();
                        list.add(result.get(e.getName()));
                        list.add(!"NULL".equals(e.getText()) ? e.getText() : null);
                        result.put(e.getName(), list);
                    }
                } else {
                    result.put(e.getName(),
                            !"NULL".equals(e.getText()) && StringUtils.isNotBlank(e.getText()) ? e.getText() : null);
                }
            } else {
                //说明类似如上，不同点是取的当前节点的不是text，而是递归出来的Map
                if (result.containsKey(e.getName())) {
                    if (result.get(e.getName()) instanceof List) {
                        ((List)result.get(e.getName())).add(getElements(e));
                    } else {
                        List list = new ArrayList();
                        list.add(result.get(e.getName()));
                        list.add(getElements(e));
                        result.put(e.getName(), list);
                    }
                } else {
                    result.put(e.getName(), getElements(e));
                }
            }
        }
        return result;
    }

    /**
     * Json to xml string.
     *
     * @param json the json
     * @return the string
     */
    public static String jsonToXml(String json){
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            JSONObject jObj = JSON.parseObject(json);
            jsonToXmlstr(jObj,buffer);
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Json to xmlstr string.
     *
     * @param jObj   the j obj
     * @param buffer the buffer
     * @return the string
     */
    public static String jsonToXmlstr(JSONObject jObj, StringBuffer buffer ){
        Set<Map.Entry<String, Object>> se = jObj.entrySet();
        for(Iterator<Map.Entry<String, Object>> it = se.iterator(); it.hasNext(); )
        {
            Map.Entry<String, Object> en = it.next();
            if(en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONObject")){
                buffer.append("<"+en.getKey()+">");
                JSONObject jo = jObj.getJSONObject(en.getKey());
                jsonToXmlstr(jo,buffer);
                buffer.append("</"+en.getKey()+">");
            }else if(en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONArray")){
                JSONArray jarray = jObj.getJSONArray(en.getKey());
                for (int i = 0; i < jarray.size(); i++) {
                    buffer.append("<"+en.getKey()+">");
                    JSONObject jsonobject =  jarray.getJSONObject(i);
                    jsonToXmlstr(jsonobject,buffer);
                    buffer.append("</"+en.getKey()+">");
                }
            }else if(en.getValue().getClass().getName().equals("java.lang.String")){
                buffer.append("<"+en.getKey()+">"+en.getValue());
                buffer.append("</"+en.getKey()+">");
            }
        }
        return buffer.toString();
    }

    public static Map<String,Object> convertBeanToMap(Object bean) throws IntrospectionException,IllegalAccessException, InvocationTargetException {
    Class type = bean.getClass();
    Map<String,Object> returnMap = new HashMap<String, Object>();
    BeanInfo beanInfo = Introspector.getBeanInfo(type);
    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
    for (int i = 0; i < propertyDescriptors.length; i++) {
    PropertyDescriptor descriptor = propertyDescriptors[i];
    String propertyName = descriptor.getName();
    if (!propertyName.equals("class")) {
        Method readMethod = descriptor.getReadMethod();
            Object result = readMethod.invoke(bean, new Object[0]);
            if (result != null) {
                returnMap.put(propertyName, result);
            } else {
                returnMap.put(propertyName, "");
            }
        }
    }
        return returnMap;
    }
    /**
     * xml转换成JavaBean
     * @param xml
     * @param c
     * @return
     */
    public static <T> T converyToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }

    /**
     * 利用反射将map集合封装成bean对象
     *
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<?> clazz) throws Exception {
        Object obj = clazz.newInstance();
        if (map != null && !map.isEmpty() && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String propertyName = entry.getKey(); 	// 属性名
                Object value = entry.getValue();		// 属性值
                String setMethodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
                Field field = getClassField(clazz, propertyName);	//获取和map的key匹配的属性名称
                if (field == null){
                    continue;
                }
                Class<?> fieldTypeClass = field.getType();
                value = convertValType(value, fieldTypeClass);
                try {
                    clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        return (T) obj;
    }

    /**
     * 根据给定对象类匹配对象中的特定字段
     * @param clazz
     * @param fieldName
     * @return
     */
    private static Field getClassField(Class<?> clazz, String fieldName) {
        if (Object.class.getName().equals(clazz.getName())) {
            return null;
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        Class<?> superClass = clazz.getSuperclass();	//如果该类还有父类，将父类对象中的字段也取出
        if (superClass != null) {						//递归获取
            return getClassField(superClass, fieldName);
        }
        return null;
    }

    /**
     * 将map的value值转为实体类中字段类型匹配的方法
     * @param value
     * @param fieldTypeClass
     * @return
     */
    private static Object convertValType(Object value, Class<?> fieldTypeClass) {
        Object retVal = null;

        if (Long.class.getName().equals(fieldTypeClass.getName())
                || long.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Long.parseLong(value.toString());
        } else if (Integer.class.getName().equals(fieldTypeClass.getName())
                || int.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Integer.parseInt(value.toString());
        } else if (Float.class.getName().equals(fieldTypeClass.getName())
                || float.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Float.parseFloat(value.toString());
        } else if (Double.class.getName().equals(fieldTypeClass.getName())
                || double.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Double.parseDouble(value.toString());
        } else {
            retVal = value;
        }
        return retVal;
    }

}
