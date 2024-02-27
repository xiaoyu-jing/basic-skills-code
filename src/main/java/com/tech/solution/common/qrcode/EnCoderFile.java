package com.tech.solution.common.qrcode;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * @author egbert.jxy
 * @date 2022/6/19
 * @describe
 */
public class EnCoderFile {

    public static void main(String[] args){
        //编码文件
        //encoderFile();

        //解码文件
        deCoderFile();

        //zip压缩
        //System.out.println(compress());

        //二维码文件生成
        //qrCodeGenerate();
    }

    /**
     * 对文件进行Base64编码，大字符串写入文件中
     */
    private static void encoderFile(){
        try {
            //资源文件
            //String filePath = "/Users/aliegbert/Desktop/代码度量.docx";
            //String filePath = "/Users/aliegbert/Desktop/代码度量.zip";
            //String filePath = "/Users/egbert.jxy/Downloads/20220713-退款类型错误.jpg";
            String filePath = "/Users/egbert.jxy/Desktop/DataTest/car.jpg";

            //资源文件处理及编码
            File file = new File(filePath);
            FileInputStream stream = new FileInputStream(file);
            byte[] fileBytes = new byte[(int)file.length()];
            stream.read(fileBytes);
            BASE64Encoder encoder = new BASE64Encoder();
            StringBuilder builder = new StringBuilder();
            builder.append(encoder.encode(fileBytes));

            System.out.println("-----------开始写数据------------");
            //编码文件写入
            String newFilePath = targetFilePath;
            bufferedWriter(newFilePath,builder.toString());

            System.out.println("-----------success------------");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }

    private static void bufferedWriter(String newFilePath, String content) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFilePath))){
            bufferedWriter.write(content);
        }
    }

    //private static final String targetFilePath = "/Users/aliegbert/Desktop/个人资料/write.txt";
    //private static final String targetFilePath = "/Users/aliegbert/Desktop/个人资料/writeZip.txt";
    //private static final String targetFilePath = "/Users/egbert.jxy/Desktop/桌面/个人资料/temp1.txt";
    private static final String targetFilePath = "/Users/egbert.jxy/Desktop/DataTest/car.txt";

    /**
     * 读取编码文件内容，进行解码，解码后的数据生成文件
     */
    private static void deCoderFile() {
        try {
            System.out.println("------------开始解码------------");
            //资源文件
            String filePath = targetFilePath;
            File file = new File(filePath);

            BASE64Decoder decoder = new BASE64Decoder();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer buffer = new StringBuffer();
            char[] c = new char[1024];
            while (reader.read(c) != -1) {
                buffer.append(c);
            }
            reader.close();
            byte[] b = decoder.decodeBuffer(buffer.toString());
            //BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("/Users/aliegbert/Desktop/个人资料/123.docx")));
            //BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("/Users/egbert.jxy/Desktop/桌面/个人资料/temp1.jpg")));
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("/Users/egbert.jxy/Desktop/DataTest/carCoderFile.jpg")));
            stream.write(b);
            stream.flush();
            stream.close();
            System.out.println("------------success------------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 压缩
    private static String compress() {
        try {
            //资源文件
            String filePath = targetFilePath;
            File file = new File(filePath);

            BASE64Decoder decoder = new BASE64Decoder();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer buffer = new StringBuffer();
            char[] c = new char[1024];
            while (reader.read(c) != -1) {
                buffer.append(c);
            }
            reader.close();
            String str = buffer.toString();
            if (str == null || str.length() == 0) {
                return str;
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
            gzip.close();
            return out.toString("ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void qrCodeGenerate(){
        try {
            System.out.println("------------开始生成二维码--------------");
            //资源文件
            String filePath = targetFilePath;
            File file = new File(filePath);

            BASE64Decoder decoder = new BASE64Decoder();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer buffer = new StringBuffer();
            char[] c = new char[1024];
            while (reader.read(c) != -1) {
                buffer.append(c);
            }
            reader.close();

            String[] strArray = stringToStringArray(buffer.toString(), 5000);
            for(int i = 0; i < strArray.length; i++){
                TextToQrCodeGenerateor.buildQuickMark(strArray[i]);
            }

            System.out.println("------------success--------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] stringToStringArray(String src, int length){
        if(StringUtils.isEmpty(src)){
            return null;
        }

        if(length <= 0){
            return null;
        }

        int n = (src.length() + length - 1) / length;
        String[] split = new String[n];
        for(int i = 0; i < n; i++){
            if(i < (n - 1)){
                split[i] = src.substring(i * length, (i + 1) * length);
            } else {
                split[i] = src.substring(i * length);
            }
        }
        return split;
    }

}
