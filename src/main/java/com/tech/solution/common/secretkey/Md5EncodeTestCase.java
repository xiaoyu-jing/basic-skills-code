package com.tech.solution.common.secretkey;

import java.io.*;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.Date;

/**
 * @author jing1560
 * @data 2024/3/4
 * @discription MD5加密
 */
public class Md5EncodeTestCase {

    public static void main(String[] args){
        String path = Md5EncodeTestCase.class.getResource("").getPath() + "/Md5EncodeTestCase.class";
        File file = new File(path);
        String md5OfFile = getMD5OfFile(file);
        System.out.println("文件MD5值为：" + md5OfFile);

        String md5FilePath = writeMD5ToFile(file, md5OfFile, String.valueOf(new Date().getTime()));
        System.out.println("新文件的路径为：" + md5FilePath);
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

    /**
     * 将MD5值写入文件中
     * @param file
     * @param md5OfFile
     * @param date
     * @return
     */
    private static String writeMD5ToFile(File file, String md5OfFile, String date){
        String filePath = file.getParent() + "/md5-" + date + ".txt";
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(filePath));
            pw.print(md5OfFile);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*File localMd5File = new File(filePath);  //路径转换，filePath会出现类似这样： /target/temp\md5-20171211213712.txt
        return localMd5File.getAbsolutePath();*/
        return filePath;
    }
}
