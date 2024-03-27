package com.example.springboot2.controller;


import cn.hutool.core.io.FileUtil;
import com.example.springboot2.Dao.awardDao;
import com.example.springboot2.Dao.gameDao;

import com.example.springboot2.Result;
import com.example.springboot2.pojo.Certificate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.colors.DeviceRgb;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import lombok.Data;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;

//@CrossOrigin(origins = "http://localhost:8080")
//@CrossOrigin
@RestController
@RequestMapping("/fileController")
@Data
public class fileController {
//    @Value("${ip:localhost}")
//    String ip;
//    @Value("${server.port:8081}")
//    String port;
    private String res[][] = new String[10100][20];
    // todo 不同环境下修改路径
    // 本地
//    private final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "public/certificateFiles";
//    private final String TEMP_PATH1 = System.getProperty("user.dir") + File.separator + "public/template/demo.pdf";
//    private final String TEMP_PATH2 = System.getProperty("user.dir") + File.separator + "public/template/demo0319.pdf";
//    private String DEST;
//
//    private  String Simsun = System.getProperty("user.dir") + File.separator + "public/template/simsun.ttc,0";
//    private String Simkai = System.getProperty("user.dir") + File.separator + "public/template/simkai.ttf";
//    private String Simhei = System.getProperty("user.dir") + File.separator + "public/template/simhei.ttf";
//    private String mubanPath = System.getProperty("user.dir") + File.separator + "public/template/获奖信息模板.xlsx";
    // linux;
    private final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "public/certificateFiles";
    private final String TEMP_PATH1 = System.getProperty("user.dir") + File.separator + "public/template/demo.pdf";
    private final String TEMP_PATH2 = System.getProperty("user.dir") + File.separator + "public/template/demo0319.pdf";
    private String DEST;
    private  String Simsun = System.getProperty("user.dir") + File.separator + "public/template/simsun.ttc,0";
    private String Simkai = System.getProperty("user.dir") + File.separator + "public/template/simkai.ttf";
    private String Simhei = System.getProperty("user.dir") + File.separator + "public/template/simhei.ttf";
    private String mubanPath = System.getProperty("user.dir") + File.separator + "public/template/获奖信息模板.xlsx";
    // 处理excel文件，添加获奖信息
    @PostMapping("/fileHandle")
    public Result Summary(@RequestPart("excelFile") MultipartFile excelFile,
                          @RequestPart("certificate") String jsonCertificate) throws IOException {
        // 字符串转对象
        ObjectMapper objectMapper = new ObjectMapper();
        Certificate certificate = objectMapper.readValue(jsonCertificate, Certificate.class);
        // 检查编号为id的比赛是否已经导入过数据
//        System.out.println(gameDao.selectById(certificate.getGame_id()));
        if(gameDao.selectById(certificate.getGame_id()).getGame_status().equals(1)) {
            return Result.error("该比赛已经导入过数据，无法再次导入");
        }
        // 处理excel文件
        excelHandle(excelFile, certificate);
        return Result.success();
    }
    //未盖章奖状
    @PostMapping("/certificateCreate1")
    public Result Create1(@RequestBody Certificate certificate) throws IOException, SQLException{

        certificate.setStatus(1);
//        System.out.println(certificate.toString());
        return this.Create(TEMP_PATH1, certificate);
//        return Result.success();
    }
    // 盖章奖状
    @PostMapping("/certificateCreate2")
    public Result Create2(@RequestBody Certificate certificate) throws IOException, SQLException{

//        String TEMP_PATH = "public/比赛名称test.pdf";
        certificate.setStatus(2);
//        System.out.println(certificate.toString());
        return this.Create(TEMP_PATH2, certificate);
    }
    public Result Create(String TEMP_PATH, Certificate certificate) throws IOException, SQLException {
//        System.out.println(status);


        // todo 不同环境下奖状名字在这里改
        DEST = System.getProperty("user.dir") + File.separator + "public/certificateFiles/" + certificate.getGame_name() + certificate.getStu_name() + certificate.getAward_id() + ".pdf";
        String temp = certificate.getGame_name() + certificate.getStu_name() + certificate.getAward_id() + ".pdf";
        certificate.setFile_path(temp);
        PdfDocument pdfDocument = new PdfDocument(new PdfReader(TEMP_PATH), new PdfWriter(DEST));
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, false);
        DeviceRgb color = new DeviceRgb(31,78,121);
        PdfFont font = PdfFontFactory.createFont(Simsun);
//        for(int j=1; j<=3; j++) {

        // 学院
        PdfFormField receptionistName = form.getFormFields().get("Text"+1);
        receptionistName.setValue(certificate.getCollege_name()).setColor(color).setFont(font).setFontSize(18);
        receptionistName.setJustification(PdfFormField.ALIGN_LEFT); // 设置左对齐

        // 姓名
        receptionistName = form.getFormFields().get("Text"+2);
        receptionistName.setValue(certificate.getStu_name()).setColor(color).setFont(font).setFontSize(18);
        receptionistName.setJustification(PdfFormField.ALIGN_LEFT); // 设置左对齐

        // 指导老师
        receptionistName = form.getFormFields().get("Text"+3);
        receptionistName.setValue(certificate.getAdviser()).setColor(color).setFont(font).setFontSize(18);
        receptionistName.setJustification(PdfFormField.ALIGN_LEFT); // 设置左对齐

        //  奖项
        color = new DeviceRgb(128,0,0);
        font = PdfFontFactory.createFont(Simkai);
        receptionistName = form.getFormFields().get("Text"+4);
        receptionistName.setValue(certificate.getAward()).setColor(color).setFont(font).setJustification(PdfFormField.ALIGN_CENTER).setFontSize(54);
        receptionistName.setJustification(PdfFormField.ALIGN_CENTER); // 设置居中对齐

//        // todo 比赛名称填充
        String temp_game = "在" + certificate.getGame_name() + "中荣获";
        color = new DeviceRgb(31,78,121);
        font = PdfFontFactory.createFont(Simhei);

        receptionistName = form.getFormFields().get("Text"+5);
        receptionistName.setValue(temp_game).setColor(color).setFont(font).setJustification(PdfFormField.ALIGN_CENTER).setFontSize(20);
        receptionistName.setJustification(PdfFormField.ALIGN_CENTER); // 设置居中对齐

        // todo 右下时间填充
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月", Locale.CHINA);
        String temp_date = dateFormat.format(certificate.getGame_date()).toUpperCase();
//        temp_date = this.transDate(temp_date);
        color = new DeviceRgb(31,78,121);
        font = PdfFontFactory.createFont(Simsun);

        receptionistName = form.getFormFields().get("Text"+6);
        receptionistName.setValue(temp_date).setColor(color).setFont(font).setJustification(PdfFormField.ALIGN_CENTER).setFontSize(18);
        receptionistName.setJustification(PdfFormField.ALIGN_CENTER); // 设置居中对齐

        form.flattenFields();
        pdfDocument.close();
        // 在数据库中修改文件地址
        Integer result = awardDao.updateAward(certificate);

        return Result.success();
    }
    // todo 将右下角数字日期转换为中文日期的函数
//    private String transDate(String tempDate) {
//        String[] digits = {"〇", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
//        String res = new String();
//        for(int i = 0; i < tempDate.length(); i++) {
//            if(Character.isDigit(tempDate.charAt(i))) {
//                res+=digits[Integer.parseInt(tempDate.charAt(i))];
//            }
//        }
//    }

    public void excelHandle (MultipartFile input,
                             Certificate certificate){
        try {

            XSSFWorkbook workbook = new XSSFWorkbook(input.getInputStream());
            // 获取第一张表
            Sheet sheet = workbook.getSheetAt(0);
            // sheet.getPhysicalNumberOfRows()获取总的行数
            // 循环读取每一行
            for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
                // 循环读取每一个格
                Row row = sheet.getRow(i);
                // row.getPhysicalNumberOfCells()获取总的列数
                for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                    // 获取数据，但是我们获取的cell类型
                    //代码上的内容自己根据实际需要自己调整就可以，这里只是展示一个样式···~
                    Cell cell = row.getCell(index);
                    // 转换为字符串类型
                    cell.setCellType(CellType.STRING);
                    // 获取得到字符串
                    res[i][index] = cell.getStringCellValue();
                    res[i][index] = res[i][index].replace("、", " ");
                    System.out.println(res[i][index]);

                }
                System.out.println(certificate.toString());
                certificate.setCollege_name(res[i][1]);
                certificate.setStu_name(res[i][2]);
                certificate.setAdviser(res[i][3]);
                certificate.setAward(res[i][4]);
                Integer result = awardDao.insertAward(certificate);
                if(result!=1) {
                    System.out.println("插入出错！\n" +
                            res[i][1]+" "+res[i][2]+" "+res[i][3]+" "+res[i][4]);
                }
                else {
                    System.out.println("插入数据成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // todo 下载链接

    @GetMapping("/downloadFile/{filename}")
    public void downloadFile(@PathVariable String filename, HttpServletResponse response) throws IOException {
        response.addHeader("Content-Disposition", "attachment; finalname=" + URLEncoder.encode(filename, "UTF-8"));
//        System.out.println(filename);
        load(filename, response);
    }
    // 下载excel模板
    @GetMapping("/downloadTemplate")
    public void downloadFile(HttpServletResponse response) throws IOException {
        // xsl 文件
        //response.setContentType("application/vnd.ms-excel");
        // xlsx 文件
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.addHeader("Content-Disposition", "attachment; finalname=" + URLEncoder.encode("获奖信息模板.xlsx", "UTF-8"));

        byte[] bytes = FileUtil.readBytes(mubanPath);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes); // 字节数组
        outputStream.flush();
        outputStream.close();
    }
    @GetMapping("/preview/{filename}")
    public void preview(@PathVariable String filename, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
//        response.addHeader("Content-Disposition", "inline");
        load(filename, response);
    }
    public void load(String filename, HttpServletResponse response) throws IOException {
        String filePath = ROOT_PATH + File.separator + filename;
        byte[] bytes = FileUtil.readBytes(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes); // 字节数组
        outputStream.flush();
        outputStream.close();
    }

}

