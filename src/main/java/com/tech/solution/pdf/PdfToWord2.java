package com.tech.solution.pdf;


import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;

import java.io.FileOutputStream;

/**
 * @author jing1560
 * @data 2023/10/10
 */
public class PdfToWord2 {

    public static void main(String[] args) {
        //PDF文件路径
        //String pdfFilePath = "/Users/jing1560/Desktop/七年级道德与法治山西人教上学期阶段评估一.pdf";
        String pdfFilePath = "/Users/jing1560/Desktop/学习文件/企业级分布式应用服务EDAS产品简介.pdf";
        pdf2doc(pdfFilePath);
    }

    //pdf转doc
    public static void pdf2doc(String pdfPath) {
        long old = System.currentTimeMillis();
        try {
            //新建一个word文档
            String wordPath = pdfPath.substring(0,pdfPath.lastIndexOf("."))+".docx";
            FileOutputStream os = new FileOutputStream(wordPath);
            //doc是将要被转化的word文档
            Document doc = new Document(pdfPath);
            //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(os, SaveFormat.DocX);
            os.close();
            //转化用时
            long now = System.currentTimeMillis();
            System.out.println("Pdf 转 Word 共耗时：" + ((now - old) / 1000.0) + "秒");
        } catch (Exception e) {
            System.out.println("Pdf 转 Word 失败...");
            e.printStackTrace();
        }
    }

}
