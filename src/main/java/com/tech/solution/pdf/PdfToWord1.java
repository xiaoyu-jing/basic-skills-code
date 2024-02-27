/*
package com.tech.solution.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

*/
/**
 * @author jing1560
 * @data 2023/10/10
 *//*

public class PdfToWord1 {

    public static void main(String[] args) throws IOException {
        //PDF文件路径
        String pdfFilePath = "/Users/jing1560/Desktop/七年级道德与法治山西人教上学期阶段评估一.pdf";
        //Word文件路径
        String wordFilePath = "/Users/jing1560/Desktop/七年级道德与法治山西人教上学期阶段评估一.docx";

        FileInputStream inputStream = new FileInputStream(new File(pdfFilePath));
        PDDocument document = PDDocument.load(inputStream);
//        List<PDPage> pages = document.getDocumentCatalog().getAllPages();
        XWPFDocument doc = new XWPFDocument();
        int pageIndex = 0;
        while (document.getDocumentCatalog().getPages().get(pageIndex) != null){
            PDPage page = document.getDocumentCatalog().getPages().get(pageIndex);
            XWPFParagraph paragraph = doc.createParagraph();
            //PDFRenderer pdfRenderer = new PDFRenderer(document);
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
            contentStream.beginText();
//            contentStream.setFont(page.getFont(), 12);
            contentStream.newLineAtOffset(100, 700);
//            contentStream.showText(page.getDisplayText());
            contentStream.endText();
            contentStream.close();
            contentStream = null;
            if(pageIndex == 5){
                break;
            }
            pageIndex++;
        }
 */
/*       for (PDPage page : pages) {
            XWPFParagraph paragraph = doc.createParagraph();
            //PDFRenderer pdfRenderer = new PDFRenderer(document);
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
            contentStream.beginText();
//            contentStream.setFont(page.getFont(), 12);
            contentStream.newLineAtOffset(100, 700);
//            contentStream.showText(page.getDisplayText());
            contentStream.endText();
            contentStream.close();
            contentStream = null;
        }*//*

        FileOutputStream out = new FileOutputStream(wordFilePath);
        doc.write(out);
        out.close();
        System.out.println("PDF转换为Word完成!");
    }
}
*/
