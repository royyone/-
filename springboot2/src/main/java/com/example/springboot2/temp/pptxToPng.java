package com.example.springboot2.temp;

import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.text.DocumentException;
import com.liumapp.workable.converter.WorkableConverter;
import com.liumapp.workable.converter.core.ConvertPattern;
import com.liumapp.workable.converter.exceptions.ConvertFailedException;
import com.liumapp.workable.converter.factory.CommonConverterManager;
import com.liumapp.workable.converter.factory.ConvertPatternManager;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class pptxToPng {
    public static void main(String[] args) throws IOException, DocumentException {
        String pptxFilePath = "public\\test3.pptx";
        String pdfFilePath = "public\\pdfprint092900.pdf";
//        word2PDF(pptxFilePath, pdfFilePath);
        String tmp1 = "src\\main\\resources\\demo092900.png";
        String tmp2 = "src\\main\\resources\\pdfprint092901.pdf";
        try {
            // 创建Document对象
            Document document = new Document();
            // 创建PdfWriter对象
            PdfWriter.getInstance(document, new FileOutputStream(tmp2));
            // 打开Document
            document.open();

            // 添加图片
            Image image = Image.getInstance(tmp1);
            image.scalePercent(50f);
            document.add(image);
            // 关闭Document
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public static void convertPptxToPdf(String pptxFilePath, String pdfFilePath) throws IOException {
//        // Load the PPTX file using Apache POI
//        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(pptxFilePath));
//
//        // Create a PDF document using iText
//        PdfWriter writer = new PdfWriter(new FileOutputStream(pdfFilePath));
//        Document document = new Document(writer);
//
//        // Iterate through each slide in the PPTX file
//        for (XSLFSlide slide : ppt.getSlides()) {
//            // Convert the slide to an image
//            BufferedImage bufferedImage = slide.getThumbnail(1.0f, 1.0f);
//            Image image = new Image(ImageDataFactory.create(bufferedImage, Color.WHITE));
//
//            // Add the image to the PDF document
//            document.add(image);
//        }
//
//        // Close the document and save the PDF file
//        document.close();
//        ppt.close();
//    }
public static void word2PDF(String inputFile, String pdfFile) {
    File file = new File(inputFile);


    FileInputStream is = null;
    FileOutputStream os = null;
    try {
        WorkableConverter converter = new WorkableConverter();
        ConvertPattern pattern = ConvertPatternManager.getInstance();
        is = new FileInputStream(file);
        os = new FileOutputStream(pdfFile);
        // 处理网络文件
        pattern.streamToStream(is, os);
        pattern.setSrcFilePrefix(DefaultDocumentFormatRegistry.DOCX);
        pattern.setDestFilePrefix(DefaultDocumentFormatRegistry.PDF);
        converter.setConverterType(CommonConverterManager.getInstance());
        converter.convert(pattern.getParameter());
    } catch (ConvertFailedException e) {
//        log.error("文件:{}，转换失败：{}", inputFile, e.getMessage());
    } catch (FileNotFoundException e) {
//        log.error("文件:{}不存在或liceroffice未安装", inputFile);
    } finally {
        try {
            is.close();
            os.close();
        } catch (IOException e) {
//            log.("文件流关闭失败");
        }
    }
}

}

