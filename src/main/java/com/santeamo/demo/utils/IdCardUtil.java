package com.santeamo.demo.utils;

/**
 * FileName IdCardUtil.java
 * <p>
 * Description
 *
 * @author SanTeAmo
 * @version V1.0
 * @date 2020/12/18 12:18
 **/
public class IdCardUtil {

    /**
     * Description 15位身份证转18位
     *
     * @param idCardNumber 15位身份证号
     * @return {@link String}
     * @date 2020/12/18
     * @author SanTeAmo
     */
    public static String getEighteenIdCard(String idCardNumber) throws Exception {
        if (idCardNumber != null && idCardNumber.length() == 15) {
            StringBuilder sb = new StringBuilder();
            sb.append(idCardNumber, 0, 6).append("19").append(idCardNumber.substring(6));
            sb.append(getVerifyCode(sb.toString()));
            return sb.toString();
        } else {
            return idCardNumber;
        }
    }

    /**
     * Description 获取身份证的校验码
     *
     * @param idCardNumber
     * @return {@link char}
     * @date 2020/12/18
     * @author SanTeAmo
     */
    public static char getVerifyCode(String idCardNumber) throws Exception {
        if (idCardNumber == null || idCardNumber.length() < 17) {
            throw new Exception("不合法的身份证号码");
        }
        char[] Ai = idCardNumber.toCharArray();
        int[] Wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] verifyCode = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3',
                '2'};
        int S = 0;
        int Y;
        for (int i = 0; i < Wi.length; i++) {
            S += (Ai[i] - '0') * Wi[i];
        }
        Y = S % 11;
        return verifyCode[Y];
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getEighteenIdCard("330324360802695"));
    }

}
