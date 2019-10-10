package com.zhuhong.inspection.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * @Author: jian.ye
 * @Date: 2019/10/8 18:18
 */
public class MD5 {

    /**
     * 将指定字符串进行MD5加密
     * @param str 待加密字符串
     * @return String
     * @Author: jian.ye
     * @Date: 2019/10/8 18:16
     */
    public static String getMD5(String str) {
        try {
            // 加密对象，指定加密方式
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // 准备要加密的数据
            byte[] b = str.getBytes();
            // 加密
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7' , '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                    'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                    'A', 'B', 'C', 'D', 'E','F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                    'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            StringBuffer sb = new StringBuffer();
            // 处理成十六进制的字符串(通常)
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
            // 打印加密后的字符串
            System.out.println(sb);
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 判断MD5加密字符串是否相等
     * @param str 待加密字符串
     * @param md5String 需比较的MD5字符串
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/8 18:19
     */
    public static boolean compareToMD5(String str, String md5String) {
        return md5String.equals(getMD5(str));
    }

    public static void main(String[] args) {
        String str = "123456";
        System.out.println(getMD5(str));
    }

}
