package com.tech.solution.common.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author egbert.jxy
 * @date 2022/6/19
 * @describe  文字转二维码
 */
public class TextToQrCodeGenerateor {

    /**
     * 黑
     */
    private static final int BLACK = 0xFF000000;

    /**
     * 白
     */
    private static final int WHITE = 0xFFFFFFFF;

    /**
     * 文件后缀
     */
    private static final String format = "png";

    /**
     * 字符串生成二维码方法
     *
     * @param content  字符串
     * @return 二维码
     */
    public static File buildQuickMark(String content) {
        String filePath = TextToQrCodeGenerateor.class.getResource("/").getPath();
        File file = new File(filePath + System.nanoTime() + format);

        try {
            BitMatrix byteMatrix = new MultiFormatWriter().encode(new String(content.getBytes(), StandardCharsets.ISO_8859_1),
                    BarcodeFormat.QR_CODE, 200, 200);
            BufferedImage image = toBufferedImage(byteMatrix);

            if(!ImageIO.write(image, format, file)){
                throw new IOException("生成二维码异常" + file);
            }
            return file;


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成二维码异常" + e);
        }
        return null;
    }

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < width; x++){
            for(int y=0; y < height; y++){
                image.setRGB(x, y, matrix.get(x,y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void main(String[] args){
        String str = "Hello JXY";
        File file = TextToQrCodeGenerateor.buildQuickMark(str);
        System.out.println(file);
    }

}

