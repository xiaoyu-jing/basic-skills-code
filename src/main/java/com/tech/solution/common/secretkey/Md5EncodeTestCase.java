package com.tech.solution.common.secretkey;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * @author jing1560
 * @data 2024/3/4
 * @discription MD5加密
 */
public class Md5EncodeTestCase {

    public static void main(String[] args){
        String path = "/Users/jing1560/IdeaProjects/StudyProject/basic-skills-code/src/main/java/com/tech/solution/common/secretkey/Md5EncodeTestCase.java";
        File file = new File(path);
        String md5OfFile = getMD5OfFile(file);
        System.out.println("文件MD5值为：" + md5OfFile);
    }

    /**
     * MD5编码
     * @param file
     * @return
     */
    private static String getMD5OfFile(File file) {
        String result = "";
        if(file == null){
            return result;
        }
        try {
            FileInputStream ins = new FileInputStream(file);
            MappedByteBuffer byteBuffer = ins.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            ins.close();
            BigInteger bi = new BigInteger(1, md5.digest());
            result = bi.toString(16);
            if(result.length() < 32){
                result = "0" + result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
