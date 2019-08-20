package com.light.blog.web.test.other;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

/**
 * @auther: light
 * @since: 2018/10/23 10:36
 * <p></p>
 */
public class MD5Test {

    public static void main(String[] args) {
//        String token = "abc";
//
//        String s = DigestUtils.md5Hex("www.baidu.com"+token);
//        System.out.println(s);
//        MessageDigest digest = DigestUtils.getDigest(s);
//        System.out.println(digest);


        String md5 = md5WithSalut("maximili");
        System.out.println(md5);
        boolean r = compareWithSault("maximili", md5);
        System.out.println(r);


    }

    /*
        下面这种加盐的原理 是
            计算md5时 随机生成一个字符 这个字符与 被加密字符作为一个整体 加密
            然后把盐字符 放到加密结果里

     */
    public static boolean compareWithSault(String str, String md5) {
        char sault = md5.toCharArray()[0];
        System.out.println(sault);
        String r = DigestUtils.md5Hex(str + sault);
        return r.replaceAll("^.", String.valueOf(sault)).equals(md5);

    }

    public static String md5WithSalut(String str) {
        char salut = (char) new Random().nextInt(128);

        String r = DigestUtils.md5Hex(str + salut);

        return r.replaceAll("^.", String.valueOf(salut));
    }

    /**
     * MD5方法
     *
     * @param text 明文
     * @param key  盐
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        String encodeStr = DigestUtils.md5Hex(text + key);
        System.out.println("MD5加密后的字符串为:encodeStr=" + encodeStr);
        return encodeStr;
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param key  密钥
     * @param md5  密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String key, String md5) throws Exception {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        if (md5Text.equalsIgnoreCase(md5)) {
            System.out.println("MD5验证通过");
            return true;
        }

        return false;
    }


}
