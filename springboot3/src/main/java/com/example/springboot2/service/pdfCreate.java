/**
 * function:
 * author: suhsbeba
 * date: 2024/7/18 14:47
 */
package com.example.springboot2.service;

import com.example.springboot2.Dao.awardDao;
import com.example.springboot2.Utils.Result;
import com.example.springboot2.pojo.Certificate;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfWidgetAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class pdfCreate {
    private final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "public/certificateFiles";
    private final String TEMP_PATH = System.getProperty("user.dir") + File.separator + "public/template/template";
    private final String TEMP_PATH1 = System.getProperty("user.dir") + File.separator + "public/template/template1.pdf";
    private final String TEMP_PATH2 = System.getProperty("user.dir") + File.separator + "public/template/demo0319.pdf";
    private final String IMG_PATH = System.getProperty("user.dir") + File.separator + "public/template/seal.png";
    private String DEST;

    private  String Simsun = System.getProperty("user.dir") + File.separator + "public/template/simsun.ttc,0";
    private String Simkai = System.getProperty("user.dir") + File.separator + "public/template/simkai.ttf";
    private String Simhei = System.getProperty("user.dir") + File.separator + "public/template/simhei.ttf";
    public Result sealAdd(PdfDocument pdfDocument) throws IOException, SQLException {
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, false);

        // 印章
        //获取具体表单域
        PdfFormField field = form.getField("image");
        //获取控件对象
        List<PdfWidgetAnnotation> widgets = field.getWidgets();
        PdfWidgetAnnotation widget = widgets.get(0);
        // 域左下角顶点坐标
        float x1 = widget.getRectangle().getAsNumber(0).floatValue();
        float y1 = widget.getRectangle().getAsNumber(1).floatValue();
        // 域右上角顶点坐标
        float x2 = widget.getRectangle().getAsNumber(2).floatValue();
        float y2 = widget.getRectangle().getAsNumber(3).floatValue();
        // 域宽高
        float fieldWidth = x2 - x1;
        float fieldHeight = y2 - y1;
        // 创建图片对象
        Image img = new Image(ImageDataFactory.create(IMG_PATH));
        // 图片自适应域大小
        Image image = img.scaleToFit(fieldWidth, fieldHeight);
//        float scaleWidth
        img.setFixedPosition(x1, y1);
        //获取文档对象，将图片添加到文档
        Document document = new Document(pdfDocument);
        document.add(image);

        return Result.success();
    }
    public Result Create(Certificate certificate, Integer flag) throws IOException, SQLException {
        DEST = System.getProperty("user.dir") + File.separator + "public/certificateFiles/" + certificate.getGame_name() + certificate.getStu_name() + certificate.getAward_id() + ".pdf";
        String temp = certificate.getGame_name() + certificate.getStu_name() + certificate.getAward_id() + ".pdf";
        certificate.setFile_path(temp);
        PdfDocument pdfDocument = new PdfDocument(new PdfReader(TEMP_PATH+certificate.getTemplate_type()+".pdf"), new PdfWriter(DEST));
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, false);

        DeviceRgb color = new DeviceRgb(31,78,121);
        PdfFont font = PdfFontFactory.createFont(Simsun);
        PdfFormField receptionistName;
        /**
         * 设置学院 college_name
         * template1
         * */
        if(certificate.getTemplate_type().equals("1")) {

            receptionistName = form.getFormFields().get("college_name");
            receptionistName.setValue(certificate.getCollege_name()).setColor(color).setFont(font).setFontSize(16);
            receptionistName.setJustification(PdfFormField.ALIGN_LEFT); // 设置左对齐
        }
        /**
         * 设置队伍名 team_name
         * template2
         * template4
         * */
        if(certificate.getTemplate_type().equals("2")
        || certificate.getTemplate_type().equals("4")) {

            receptionistName = form.getFormFields().get("team_name");
            receptionistName.setValue(certificate.getAward_team()).setColor(color).setFont(font).setFontSize(16);
            receptionistName.setJustification(PdfFormField.ALIGN_LEFT); // 设置左对齐
        }
        /**
         * 设置学校名 school_name
         * template4
         * */
        if(certificate.getTemplate_type().equals("4")) {

            receptionistName = form.getFormFields().get("school_name");
            receptionistName.setValue(certificate.getSchool_name()).setColor(color).setFont(font).setFontSize(16);
            receptionistName.setJustification(PdfFormField.ALIGN_LEFT); // 设置左对齐
        }
        /**
         * 设置作品名 work_name
         * template3
         * */
        if(certificate.getTemplate_type().equals("3")) {

            receptionistName = form.getFormFields().get("work_name");
            receptionistName.setValue(certificate.getWork_name()).setColor(color).setFont(font).setFontSize(16);
            receptionistName.setJustification(PdfFormField.ALIGN_LEFT); // 设置左对齐
        }

        /**
         * 设置姓名 stu_name
         * template1
         * template2
         * template3
         * template4
         * */
        if(certificate.getTemplate_type().equals("1")
                || certificate.getTemplate_type().equals("2")
                || certificate.getTemplate_type().equals("3")
                || certificate.getTemplate_type().equals("4")) {
            // 姓名
            receptionistName = form.getFormFields().get("stu_name");
            receptionistName.setValue(certificate.getStu_name()).setColor(color).setFont(font).setFontSize(16);
            receptionistName.setJustification(PdfFormField.ALIGN_LEFT); // 设置左对齐
        }
        /**
         * 设置指导老师 advisor
         * template1
         * template2
         * template3
         * template4
         * */
        if(certificate.getTemplate_type().equals("1")
                || certificate.getTemplate_type().equals("2")
                || certificate.getTemplate_type().equals("3")
                || certificate.getTemplate_type().equals("4")) {
            if(certificate.getAdviser() != null && !certificate.getAdviser().equals("无")) {
                // 指导老师
                receptionistName = form.getFormFields().get("advisor");
                receptionistName.setValue("指导老师: "+certificate.getAdviser()).setColor(color).setFont(font).setFontSize(16);
                receptionistName.setJustification(PdfFormField.ALIGN_LEFT); // 设置左对齐
            }
        }
        /**
         * 设置奖项 award
         * template1
         * template2
         * template3
         * template4
         * */
        if(certificate.getTemplate_type().equals("1")
                || certificate.getTemplate_type().equals("2")
                || certificate.getTemplate_type().equals("3")
                || certificate.getTemplate_type().equals("4")) {
            //  奖项
            color = new DeviceRgb(128,0,0);
            font = PdfFontFactory.createFont(Simkai);
            receptionistName = form.getFormFields().get("award");
            receptionistName.setValue(certificate.getAward()).setColor(color).setFont(font).setJustification(PdfFormField.ALIGN_CENTER).setFontSize(54);
            receptionistName.setJustification(PdfFormField.ALIGN_CENTER); // 设置居中对齐
        }


        /**
         * 设置比赛名称 game_name
         * template1
         * template2
         * template3
         * template4
         * */
        if(certificate.getTemplate_type().equals("1")
                || certificate.getTemplate_type().equals("2")
                || certificate.getTemplate_type().equals("3")
                || certificate.getTemplate_type().equals("4")) {
            String temp_game = "在" + certificate.getGame_name() + "中荣获";
            color = new DeviceRgb(31,78,121);
            font = PdfFontFactory.createFont(Simhei);

            receptionistName = form.getFormFields().get("game_name");
            receptionistName.setValue(temp_game).setColor(color).setFont(font).setJustification(PdfFormField.ALIGN_CENTER).setFontSize(20);
            receptionistName.setJustification(PdfFormField.ALIGN_CENTER); // 设置居中对齐
        }
//        if(certificate.getTemplate_type().equals("2")
//                || certificate.getTemplate_type().equals("3")) {
//            String temp_game = certificate.getGame_name();
//            color = new DeviceRgb(31,78,121);
//            font = PdfFontFactory.createFont(Simhei);
//
//            receptionistName = form.getFormFields().get("game_name");
//            receptionistName.setValue(temp_game).setColor(color).setFont(font).setJustification(PdfFormField.ALIGN_CENTER).setFontSize(20);
//            receptionistName.setJustification(PdfFormField.ALIGN_CENTER); // 设置居中对齐
//        }
        /**
         * 设置比赛日期 date
         * template1
         * template2
         * template3
         * template4
         * */
        if(certificate.getTemplate_type().equals("1")
                || certificate.getTemplate_type().equals("2")
                || certificate.getTemplate_type().equals("3")
                || certificate.getTemplate_type().equals("4")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月", Locale.CHINA);
            String temp_date = dateFormat.format(certificate.getGame_date()).toUpperCase();
            color = new DeviceRgb(31,78,121);
            font = PdfFontFactory.createFont(Simsun);

            receptionistName = form.getFormFields().get("date");
            receptionistName.setValue(temp_date).setColor(color).setFont(font).setJustification(PdfFormField.ALIGN_CENTER).setFontSize(18);
            receptionistName.setJustification(PdfFormField.ALIGN_CENTER); // 设置居中对齐
        }
        if(flag == 0) {
            form.flattenFields();
            pdfDocument.close();
            // 在数据库中修改文件地址
            Integer result = awardDao.updateAward(certificate);
            return Result.success();
        }
        else {
            sealAdd(pdfDocument);
            form.flattenFields();
            pdfDocument.close();
            // 在数据库中修改文件地址
            Integer result = awardDao.updateAward(certificate);
            return Result.success();
        }
    }
}
