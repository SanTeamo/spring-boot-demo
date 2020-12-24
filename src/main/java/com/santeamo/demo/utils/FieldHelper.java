package com.santeamo.demo.utils;

import java.lang.reflect.Field;

/**
 * FileName FieldHelper.java
 *
 * Description
 *
 * @author SanTeAmo
 * @date 2020/12/18 10:47
 * @version V1.0
 **/
public class FieldHelper {

    public static void fieldForEach(Object object) throws IllegalAccessException {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field: declaredFields) {
            field.setAccessible(true);
            System.out.println(field.getName() + " = " + field.get(object));
        }
    }

}
