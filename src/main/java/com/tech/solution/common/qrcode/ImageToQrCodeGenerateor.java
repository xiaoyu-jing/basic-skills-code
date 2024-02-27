package com.tech.solution.common.qrcode;

import com.github.binarywang.utils.qrcode.BufferedImageLuminanceSource;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author egbert.jxy
 * @date 2023/9/26
 * @describe  图片转二维码
 */
public class ImageToQrCodeGenerateor {

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
     * 图片生成二维码方法
     *
     * @param image  图片
     * @return 二维码
     */
    public static File buildQuickMark(BufferedImage image) {
        String filePath = ImageToQrCodeGenerateor.class.getResource("/").getPath();
        File file = new File(filePath + System.nanoTime() + format);

        try {
            // 将BufferedImage转换成BinaryBitmap
            //LuminanceSource source = new RGBLuminanceSource(image);
            LuminanceSource source = new BufferedImageLuminanceSource(image);

            //获取图片黑色元素
            //BitMatrix byteMatrix = new HybridBinarizer(source).getBlackMatrix();

            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            // 使用ZXing库从BinaryBitmap中提取信息
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(bitmap);

            // 获取提取到的信息
            String qrCodeText = result.getText();

            // 使用ZXing库生成二维码图片
            BitMatrix byteMatrix = new MultiFormatWriter().encode(new String(qrCodeText.getBytes(), StandardCharsets.ISO_8859_1),
                    BarcodeFormat.QR_CODE, 200, 200);
            BufferedImage imageBuffer = toBufferedImage(byteMatrix);

            if(!ImageIO.write(imageBuffer, format, file)){
                throw new IOException("生成二维码异常" + file);
            }
            return file;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成二维码异常" + e);
        }
        return null;
    }

    /**
     * 绘制黑白块的二维码
     * @param matrix
     * @return
     */
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
        // 加载图片
        try {
            String imagePath = "/Users/jing1560/Desktop/temp.jpeg";
//            String imagePath = "/Users/jing1560/Desktop/文字二维码.png";
            BufferedImage image = ImageIO.read(new File(imagePath));
            File file = ImageToQrCodeGenerateor.buildQuickMark(image);
            System.out.println(file);

           /* String imagePath = "/Users/jing1560/Desktop/temp.jpeg";
            InputStream stream = new BufferedInputStream(new FileInputStream(new File(imagePath)));

            Bitmap bitmap = BitmapFactory.decodeStream(stream);

            String decoded=scanQRImage(bitmap);*/

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

