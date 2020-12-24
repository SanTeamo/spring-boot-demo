package com.santeamo.demo.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * FileName DateUtils.java
 *
 * Description
 *
 * @author SanTeAmo
 * @date 2020/11/19 10:04
 * @version V1.0
 **/
public class DateUtils {
    public static int getAge(Date patBirth) {
        if (patBirth == null) {
            return 0;
        }
        LocalDate nowTime = LocalDate.now();
        LocalDate birthLoalDate =  patBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (birthLoalDate.isAfter(nowTime)) {
            throw new IllegalArgumentException("出生日期在当前时间之后，不合法！");
        }
        Period until = birthLoalDate.until(nowTime);
        return until.getYears();
    }
}
