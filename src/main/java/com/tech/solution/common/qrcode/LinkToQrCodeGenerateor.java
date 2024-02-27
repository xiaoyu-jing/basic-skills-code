package com.tech.solution.common.qrcode;

/**
 * @author egbert.jxy
 * @date 2023/9/26
 * @describe  图片链接转二维码
 */
public class LinkToQrCodeGenerateor {

    /**
     * 文件后缀
     */
    private static final String format = "png";

    public static void main(String[] args){
        //二维码的内容
        String qrCodeContent = "https://i2.hdslb.com/bfs/archive/08adf9bdf8d8f720c4e3489cee422c5744e77df7.jpg";

        //二维码要生成的路径
        String qrCodeImagePath = ImageToQrCodeGenerateor.class.getResource("/").getPath();
        //生成qrCode_JXY.png的二维码图片(去除白边)
        String qrCodeUrl = QRCodeUtil.createImage(qrCodeContent, 300, 300, "qrCode_JXY", "png", qrCodeImagePath);
        System.out.println("生成的二维码地址：" + qrCodeUrl);

    }

}

