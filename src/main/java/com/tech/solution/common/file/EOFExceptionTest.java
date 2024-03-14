package com.tech.solution.common.file;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author jing1560
 * @data 2024/3/14
 * EOFException详解：https://www.yisu.com/ask/3720438.html
 */
public class EOFExceptionTest {

    public static void main(String[] args){
        try {
            // 创建一个输入流
            FileInputStream fis = new FileInputStream("src/main/java/com/tech/solution/common/file/file.txt");
            // 读取数据
            int data;
            while ((data = fis.read()) != -1) {
                System.out.println((char) data);
            }
            // 关闭输入流
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            System.out.println("文件已经读取完毕或输入流已经关闭");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
