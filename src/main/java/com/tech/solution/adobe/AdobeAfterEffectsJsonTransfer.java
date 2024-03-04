package com.tech.solution.adobe;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author jing1560
 * @data 2023/12/10
 */
public class AdobeAfterEffectsJsonTransfer {

    public static void main(String[] args){
        try {
            //String oldFilePath = "/Users/jing1560/Desktop/LOCAL_ACCELERATE.json";
            String oldFilePath = "/Users/jing1560/Desktop/123.json";
            //创建File对象
            //File file = new File(oldFilePath);

            Path path = Paths.get(oldFilePath);
            byte[] fileBytes = Files.readAllBytes(path);
            String fileContext = new String(fileBytes);

            Gson gson = new Gson();
            Object o = gson.fromJson(fileContext, Object.class);
            System.out.println(o);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
