package com.tech.solution.common.secretkey;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author jing1560
 * @data 2024/3/4
 * @discription Base64 加解密
 */
public class Base64EncodeTestCase {

    private static final String text = "Base64 finally in Java 8";

    public static void main(String[] args){

        testEncode1();

    }

    private static void testEncode1(){
        //Base64 加密
        String encodeText = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println(encodeText);

        //Base64 解密
        String decodedText = new String(Base64.getDecoder().decode(encodeText),StandardCharsets.UTF_8);
        System.out.println(decodedText);
    }
}
