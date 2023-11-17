//package com.example.springboot2.temp;
///**
// * @author suhsbeba
// * @date 2023.09.24
// * @teach http://t.csdn.cn/2quSP
// */
//
//import org.apache.poi.xslf.usermodel.*;
//
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//
//import org.apache.poi.hslf.usermodel.*;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.pdf.*;
//
//import com.itextpdf.text.Image;
//
//
//public class pptxUpdate {
//    private final static String START_PATH = "public\\demo092400.pptx";
//    private final static String END_PATH = "public\\test3.pptx";
//    private final static String FONT_PATH = "";
//    private String school = "";
//    private String student = "";
//    private String teacher = "";
//    private String time = "";
//    private String item = "";
//    private String rank = "";
//    private String itext = "";
//    private String tissue = "";
//    private String TIME = "";
//
//    public void setValue(String school, String student, String teacher, String time, String item, String rank, String itext, String tissue, String TIME) {
//        this.school = school;
//        this.student = student;
//        this.teacher = teacher;
//        this.time = time;
//        this.item = item;
//        this.rank = rank;
//        this.itext = itext;
//        this.tissue = tissue;
//        this.TIME = TIME;
//    }
//
//    public void setPptx(String pptxPath) throws IOException {
//        XMLSlideShow ppt = null;
//        try {
//            // 通过输入流读取一个现有的ppt文件
//            ppt = new XMLSlideShow(new FileInputStream(START_PATH));
//            System.out.println("1");
//            // 遍历幻灯片
//            List<XSLFSlide> slides = ppt.getSlides();
//            for (XSLFSlide slide : slides) {
//                // 获取幻灯片中的所有图形（文本框、表格、图形...）
//                List<XSLFShape> shapes = slide.getShapes();
//                // 遍历图形
//                for (XSLFShape shape : shapes) {
//                    // 判断该图形类是否是文本框类
//                    if (shape instanceof XSLFTextShape) {
//                        // 将图像类强制装换成文本框类
//                        XSLFTextShape ts = (XSLFTextShape) shape;
//                        // 获取文本框内的文字
//                        //String str = ts.getText();
//                        //System.out.println(str);
//                        // 若想对文本框内的文字进行更改，还需要进行如下步骤
//                        List<XSLFTextParagraph> textParagraphs = ts.getTextParagraphs();
//                        for (XSLFTextParagraph tp : textParagraphs) {
//                            List<XSLFTextRun> textRuns = tp.getTextRuns();
//                            System.out.println(tp);
//                            for (XSLFTextRun r : textRuns) {
////                                System.out.println(r.getRawText());
//                                // 设置字体颜色
//                                //r.setFontColor(new Color(31, 78, 121));
//                                // 对匹配到的字符串进行更改
//                                if (r.getRawText().equals("学校：杭州师范大学")) r.setText(school);
//                                else if (r.getRawText().equals("学生：潘志程 戴泽耀 李书培")) r.setText(student);
//                                else if (r.getRawText().equals("指导老师：单振宇 虞剑波")) r.setText(teacher);
//                                else if (r.getRawText().equals("在")) ;
//                                else if (r.getRawText().equals("2023")) r.setText(time);
//                                else if (r.getRawText().equals("年浙江省第二十届大学生程序设计竞赛中荣获"))
//                                    r.setText(item);
//                                else if (r.getRawText().equals("参赛奖")) r.setText(rank);
//                                else if (r.getRawText().equals("特发此证，以资奖励！")) r.setText(itext);
//                                else if (r.getRawText().equals("二〇二三年四月")) r.setText(TIME);
//                                else if (r.getRawText().equals("浙江省大学生科技竞赛委员")) r.setText(tissue);
//                                // 将修改后的PPT文件回写到硬盘
//                                ppt.write(new FileOutputStream(pptxPath));
//
//
//                            }
//                        }
//
//                    }
//                }
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public boolean getPdf(String pptPath, String pdfPath) {
//
//
////        String pdfPath = pdfDir + "te." + "pdf";
//
//        Document document = null;
//
//        XMLSlideShow slideShow = null;
//
//
//        FileOutputStream fileOutputStream = null;
//
//        PdfWriter pdfWriter = null;
//
//
//        try {
//            //使用输入流pptx文件
//            slideShow = new XMLSlideShow(new FileInputStream(pptPath));
//            //获取幻灯片的尺寸
//            Dimension dimension = slideShow.getPageSize();
//            //新增pdf输出流，准备讲ppt写出
//            fileOutputStream = new FileOutputStream(pdfPath);
//            //创建一个写内容的容器
//            document = new Document();
//            //使用输出流写入
//            pdfWriter = PdfWriter.getInstance(document, fileOutputStream);
//            //使用之前必须打开<You have to open the document before you can write content.>
//            document.open();
//
//            PdfPTable pdfPTable = new PdfPTable(1);
//            //获取幻灯片
//            List<XSLFSlide> slideList = slideShow.getSlides();
//
//            for (int i = 0, row = slideList.size(); i < row; i++) {
//                //获取每一页幻灯片
//                XSLFSlide slide = slideList.get(i);
//
//                for (XSLFShape shape : slide.getShapes()) {
//                    //判断是否是文本
//                    if (shape instanceof XSLFTextShape) {
//                        // 设置字体, 解决中文乱码
//                        XSLFTextShape textShape = (XSLFTextShape) shape;
//                        for (XSLFTextParagraph textParagraph : textShape.getTextParagraphs()) {
//                            for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
//                                textRun.setFontFamily("宋体");
//                            }
//                        }
//                    }
//                }
//
//
//                //根据幻灯片尺寸创建图形对象
//
//                BufferedImage bufferedImage = new BufferedImage((int) dimension.getWidth(), (int) dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
////                System.out.println("2");
//                Graphics2D graphics2d = bufferedImage.createGraphics();
////                System.out.println("3");
//                graphics2d.setPaint(Color.white);
//                graphics2d.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 12));
////                System.out.println("4");
//                //把内容写入图形对象
//                System.out.println(graphics2d);
//                System.out.println(slide);
//                System.out.println("\n\novv\n\n\n");
//                slide.draw(graphics2d);
//                System.out.println("???!!!!");
//                graphics2d.dispose();
//
//                //封装到Image对象中
//                Image image = Image.getInstance(bufferedImage, null);
//                image.scalePercent(50f);
//
//                // 写入单元格
//                pdfPTable.addCell(new PdfPCell(image, true));
//                document.add(image);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            try {
//                if (document != null) {
//                    document.close();
//                }
//                if (fileOutputStream != null) {
//                    fileOutputStream.close();
//                }
//                if (pdfWriter != null) {
//                    pdfWriter.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return true;
//    }
//}
////
////
////    public static boolean pptToPdf(String pptPath, String pdfDir) {
////        String pdfPath = pdfDir + "te." + "pdf";
////        Document document = null;
////        HSLFSlideShow hslfSlideShow = null;
////        FileOutputStream fileOutputStream = null;
////        PdfWriter pdfWriter = null;
////
////        try {
////            //使用输入流ppt文件
////            hslfSlideShow = new HSLFSlideShow(new FileInputStream(pptPath));
////
////            // 获取ppt文件页面
////            Dimension dimension = hslfSlideShow.getPageSize();
////
////            fileOutputStream = new FileOutputStream(pdfPath);
////
////            document = new Document();
////
////            // pdfWriter实例
////            pdfWriter = PdfWriter.getInstance(document, fileOutputStream);
////
////            document.open();
////
////            PdfPTable pdfPTable = new PdfPTable(1);
////
////            List<HSLFSlide> hslfSlideList = hslfSlideShow.getSlides();
////
////            for (int i = 0; i < hslfSlideList.size(); i++) {
////                HSLFSlide hslfSlide = hslfSlideList.get(i);
////
////                for (HSLFShape shape : hslfSlide.getShapes()) {
////
////                    if (shape instanceof HSLFTextShape) {
////                        // 设置字体, 解决中文乱码
////                        HSLFTextShape textShape = (HSLFTextShape) shape;
////
////                        for (HSLFTextParagraph textParagraph : textShape.getTextParagraphs()) {
////                            for (HSLFTextRun textRun : textParagraph.getTextRuns()) {
////                                textRun.setFontFamily("宋体");
////                            }
////                        }
////                    }
////
////                }
////                BufferedImage bufferedImage = new BufferedImage((int) dimension.getWidth(), (int) dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
//////
//////                Graphics2D graphics2d = bufferedImage.createGraphics();
//////
//////                graphics2d.setPaint(Color.white);
//////                graphics2d.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 12));
//////
//////                hslfSlide.draw(graphics2d);
//////
//////                graphics2d.dispose();
//////
//////                Image image = Image.getInstance(bufferedImage, null);
//////                image.scalePercent(50f);
//////
//////                // 写入单元格
//////                pdfPTable.addCell(new PdfPCell(image, true));
//////                document.add(image);
////            }
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            return false;
////        } finally {
////            try {
////                if (document != null) {
////                    document.close();
////                }
////                if (fileOutputStream != null) {
////                    fileOutputStream.close();
////                }
////                if (pdfWriter != null) {
////                    pdfWriter.close();
////                }
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////        return true;
////    }
////
////
////}
////
package com.example.springboot2.temp;
/**
 * @author suhsbeba
 * @date 2023.09.24
 * @teach http://t.csdn.cn/2quSP
 */

import org.apache.poi.xslf.usermodel.*;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hslf.usermodel.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;

import com.itextpdf.text.Image;


public class pptxUpdate {
    private final static String START_PATH = "src\\main\\resource\\demo092400.pptx";
//    private final static String END_PATH = "src\\main\\resource\\test3.pptx";
//    private final static String FONT_PATH = "";
    private String school = "";
    private String student = "";
    private String teacher = "";
    private String time = "";
    private String item = "";
    private String rank = "";
    private String itext = "";
    private String tissue = "";
    private String TIME = "";

    public void setValue(String school, String student, String teacher, String time, String item, String rank, String itext, String tissue, String TIME) {
        this.school = school;
        this.student = student;
        this.teacher = teacher;
        this.time = time;
        this.item = item;
        this.rank = rank;
        this.itext = itext;
        this.tissue = tissue;
        this.TIME = TIME;
    }

    public void setPptx(String pptxPath) throws IOException {
        XMLSlideShow ppt = null;
        try {
            // 通过输入流读取一个现有的ppt文件
            ppt = new XMLSlideShow(new FileInputStream(START_PATH));
            System.out.println("1");
            // 遍历幻灯片
            List<XSLFSlide> slides = ppt.getSlides();
            for (XSLFSlide slide : slides) {
                // 获取幻灯片中的所有图形（文本框、表格、图形...）
                List<XSLFShape> shapes = slide.getShapes();
                // 遍历图形
                for (XSLFShape shape : shapes) {
                    // 判断该图形类是否是文本框类
                    if (shape instanceof XSLFTextShape) {
                        // 将图像类强制装换成文本框类
                        XSLFTextShape ts = (XSLFTextShape) shape;
                        // 获取文本框内的文字
                        //String str = ts.getText();
                        //System.out.println(str);
                        // 若想对文本框内的文字进行更改，还需要进行如下步骤
                        List<XSLFTextParagraph> textParagraphs = ts.getTextParagraphs();
                        for (XSLFTextParagraph tp : textParagraphs) {
                            List<XSLFTextRun> textRuns = tp.getTextRuns();
                            System.out.println(tp);
                            for (XSLFTextRun r : textRuns) {
//                                System.out.println(r.getRawText());
                                // 设置字体颜色
                                //r.setFontColor(new Color(31, 78, 121));
                                // 对匹配到的字符串进行更改
                                if (r.getRawText().equals("学校：杭州师范大学")) r.setText(school);
                                else if (r.getRawText().equals("学生：潘志程 戴泽耀 李书培")) r.setText(student);
                                else if (r.getRawText().equals("指导老师：单振宇 虞剑波")) r.setText(teacher);
                                else if (r.getRawText().equals("在")) ;
                                else if (r.getRawText().equals("2023")) r.setText(time);
                                else if (r.getRawText().equals("年浙江省第二十届大学生程序设计竞赛中荣获"))
                                    r.setText(item);
                                else if (r.getRawText().equals("参赛奖")) r.setText(rank);
                                else if (r.getRawText().equals("特发此证，以资奖励！")) r.setText(itext);
                                else if (r.getRawText().equals("二〇二三年四月")) r.setText(TIME);
                                else if (r.getRawText().equals("浙江省大学生科技竞赛委员")) r.setText(tissue);
                                // 将修改后的PPT文件回写到硬盘
                                ppt.write(new FileOutputStream(pptxPath));


                            }
                        }

                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getPdf(String pptPath, String pdfPath) {


//        String pdfPath = pdfDir + "te." + "pdf";

        Document document = null;

        XMLSlideShow slideShow = null;


        FileOutputStream fileOutputStream = null;

        PdfWriter pdfWriter = null;


        try {
            //使用输入流pptx文件
            slideShow = new XMLSlideShow(new FileInputStream(pptPath));
            //获取幻灯片的尺寸
            Dimension dimension = slideShow.getPageSize();
            //新增pdf输出流，准备讲ppt写出
            fileOutputStream = new FileOutputStream(pdfPath);
            //创建一个写内容的容器
            document = new Document();
            //使用输出流写入
            pdfWriter = PdfWriter.getInstance(document, fileOutputStream);
            //使用之前必须打开<You have to open the document before you can write content.>
            document.open();

            PdfPTable pdfPTable = new PdfPTable(1);
            //获取幻灯片
            List<XSLFSlide> slideList = slideShow.getSlides();

            for(int i = 0, row = slideList.size(); i < row; i++) {
                //获取每一页幻灯片
                XSLFSlide slide = slideList.get(i);

                for (XSLFShape shape : slide.getShapes()) {
                    //判断是否是文本
                    if (shape instanceof XSLFTextShape) {
                        // 设置字体, 解决中文乱码
                        XSLFTextShape textShape = (XSLFTextShape) shape;
                        for (XSLFTextParagraph textParagraph : textShape.getTextParagraphs()) {
                            for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
                                textRun.setFontFamily("宋体");
                            }
                        }
                    }
                }


                //根据幻灯片尺寸创建图形对象
                BufferedImage bufferedImage = new BufferedImage((int) dimension.getWidth(), (int) dimension.getHeight(), BufferedImage.TYPE_INT_RGB);

                Graphics2D graphics2d = bufferedImage.createGraphics();

                graphics2d.setPaint(Color.white);
                graphics2d.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 12));
                System.out.println(graphics2d);
                System.out.println(slide);
                System.out.println("\n\novv\n\n\n");
                //把内容写入图形对象
                slide.draw(graphics2d);

                graphics2d.dispose();

                //封装到Image对象中
                Image image = Image.getInstance(bufferedImage, null);
                image.scalePercent(50f);

                // 写入单元格
                pdfPTable.addCell(new PdfPCell(image, true));
                document.add(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (pdfWriter != null) {
                    pdfWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    public static boolean pptToPdf(String pptPath, String pdfDir) {
        String pdfPath = pdfDir + "te." + "pdf";
        Document document = null;
        HSLFSlideShow hslfSlideShow = null;
        FileOutputStream fileOutputStream = null;
        PdfWriter pdfWriter = null;

        try {
            //使用输入流ppt文件
            hslfSlideShow = new HSLFSlideShow(new FileInputStream(pptPath));

            // 获取ppt文件页面
            Dimension dimension = hslfSlideShow.getPageSize();

            fileOutputStream = new FileOutputStream(pdfPath);

            document = new Document();

            // pdfWriter实例
            pdfWriter = PdfWriter.getInstance(document, fileOutputStream);

            document.open();

            PdfPTable pdfPTable = new PdfPTable(1);

            List<HSLFSlide> hslfSlideList = hslfSlideShow.getSlides();

            for (int i = 0; i < hslfSlideList.size(); i++) {
                HSLFSlide hslfSlide = hslfSlideList.get(i);

                for (HSLFShape shape : hslfSlide.getShapes()) {

                    if (shape instanceof HSLFTextShape) {
                        // 设置字体, 解决中文乱码
                        HSLFTextShape textShape = (HSLFTextShape) shape;

                        for (HSLFTextParagraph textParagraph : textShape.getTextParagraphs()) {
                            for (HSLFTextRun textRun : textParagraph.getTextRuns()) {
                                textRun.setFontFamily("宋体");
                            }
                        }
                    }

                }
                BufferedImage bufferedImage = new BufferedImage((int) dimension.getWidth(), (int) dimension.getHeight(), BufferedImage.TYPE_INT_RGB);

                Graphics2D graphics2d = bufferedImage.createGraphics();

                graphics2d.setPaint(Color.white);
                graphics2d.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 12));

                hslfSlide.draw(graphics2d);

                graphics2d.dispose();

                Image image = Image.getInstance(bufferedImage, null);
                image.scalePercent(50f);

                // 写入单元格
                pdfPTable.addCell(new PdfPCell(image, true));
                document.add(image);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (pdfWriter != null) {
                    pdfWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


}

