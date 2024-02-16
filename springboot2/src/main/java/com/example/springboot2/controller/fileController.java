package com.example.springboot2.controller;


import cn.hutool.core.io.FileUtil;
import com.example.springboot2.Dao.awardDao;
import com.example.springboot2.Result;
import com.example.springboot2.SQL;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/fileController")
public class fileController {
    @Value("${ip:localhost}")
    String ip;
    @Value("${server.port:8081}")
    String port;
    private String res[][] = new String[10100][20];
    private final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "public/certificate";
    // 处理excel文件，添加获奖信息
    @PostMapping("/fileHandle")
    public Result Summary(@RequestPart("excelFile") MultipartFile excelFile,
                          @RequestPart("certificate") String jsonCertificate) throws IOException {
        // 字符串转对象
        ObjectMapper objectMapper = new ObjectMapper();
        Certificate certificate = objectMapper.readValue(jsonCertificate, Certificate.class);
        // 处理excel文件
        excelHandle(excelFile, certificate);
        return Result.success();
    }
    //未盖章奖状
    @PostMapping("/certificateCreate1")
    public Result Create1(@RequestBody Certificate certificate) throws IOException, SQLException{
        String TEMP_PATH = "public/Demo3.pdf";
        certificate.setStatus(1);
        System.out.println(certificate.toString());
        return this.Create(TEMP_PATH, certificate);
//        return Result.success();
    }
    // 盖章奖状
    @PostMapping("/certificateCreate2")
    public Result Create2(@RequestBody Certificate certificate) throws IOException, SQLException{
        String TEMP_PATH = "public/Demo2.pdf";
        certificate.setStatus(2);
        System.out.println(certificate.toString());
        return this.Create(TEMP_PATH, certificate);
    }
    public Result Create(String TEMP_PATH, Certificate certificate) throws IOException, SQLException {
//        System.out.println(status);

        String DEST;
        // 奖状名字在这里改
        DEST = "public/certificate/certificate_"+ certificate.getAward_id() + ".pdf";
        String temp = "certificate_" + certificate.getAward_id() + ".pdf";
        certificate.setFile_path(temp);
        PdfDocument pdfDocument = new PdfDocument(new PdfReader(TEMP_PATH), new PdfWriter(DEST));
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, false);
        DeviceRgb color = new DeviceRgb(31,78,121);
        PdfFont font = PdfFontFactory.createFont("public/simsun.ttc,0");
//        for(int j=1; j<=3; j++) {
        // 学校
        PdfFormField receptionistName = form.getFormFields().get("Text"+1);
        receptionistName.setValue(certificate.getCollege_Name()).setColor(color).setFont(font).setFontSize(18);
        // 姓名
        receptionistName = form.getFormFields().get("Text"+2);
        receptionistName.setValue(certificate.getStu_name()).setColor(color).setFont(font).setFontSize(18);
        // 指导老师
        receptionistName = form.getFormFields().get("Text"+3);
        receptionistName.setValue(certificate.getAdviser()).setColor(color).setFont(font).setFontSize(18);
            // 清除表单域
        receptionistName.setJustification(PdfFormField.ALIGN_CENTER); // 设置居中对齐
//        }
//         奖项
        color = new DeviceRgb(128,0,0);
        font = PdfFontFactory.createFont("public/simkai.ttf");
        receptionistName = form.getFormFields().get("Text"+4);
        receptionistName.setValue(certificate.getAward()).setColor(color).setFont(font).setJustification(PdfFormField.ALIGN_CENTER).setFontSize(54);
        form.flattenFields();
        pdfDocument.close();
        // 在数据库中修改文件地址
        Integer result = awardDao.updateAward(certificate);
//
//        for(int i=1; i<7; i++) { // res.length(); 遍历每条信息
//            DEST = "public/certificate/certificate_" + i + ".pdf";
//            PdfDocument pdfDocument = new PdfDocument(new PdfReader(TEMP_PATH), new PdfWriter(DEST));
//            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, false);
//            DeviceRgb color = new DeviceRgb(31,78,121);
//            PdfFont font = PdfFontFactory.createFont("public/simsun.ttc,0");
//            for(int j=1; j<=3; j++) {
//                PdfFormField receptionistName = form.getFormFields().get("Text"+j);
//                receptionistName.setValue(res[i][j]).setColor(color).setFont(font).setFontSize(18);
//                // 清除表单域
////        receptionistName.setJustification(PdfFormField.ALIGN_CENTER); // 设置居中对齐
//            }
//            // 奖项
//            color = new DeviceRgb(128,0,0);
//            font = PdfFontFactory.createFont("public/simkai.ttf");
//            PdfFormField receptionistName = form.getFormFields().get("Text"+4);
//            receptionistName.setValue(res[i][4]).setColor(color).setFont(font).setJustification(PdfFormField.ALIGN_CENTER).setFontSize(54);
//            form.flattenFields();
//            pdfDocument.close();
//        }
        return Result.success();
    }
    public void excelHandle (MultipartFile input,
                             Certificate certificate){
        try {

            XSSFWorkbook workbook = new XSSFWorkbook(input.getInputStream());
            // 获取第一张表
            Sheet sheet = workbook.getSheetAt(0);
            // sheet.getPhysicalNumberOfRows()获取总的行数
            // 循环读取每一行
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
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
                certificate.setSchool_name(res[i][1]);
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
                // 插入数据
//                String sql="INSERT INTO award (school_name, stu_name, adviser, award, status) VALUES (?, ?, ?, ?, ?)";
//                System.out.println("INSERT INTO result (school_name, stu_name, adviser, award) VALUES " +
//                        "("+res[i][0]+", "+res[i][1]+", "+res[i][2]+", "+res[i][3]+")");
//                Connection conn = DriverManager.getConnection(SQL.url, SQL.user, SQL.password);
//                PreparedStatement statement = conn.prepareStatement(sql);
//                statement.setString(1, );  // 设置第一个占位符的值
//                statement.setString(2, );  // 设置第二个占位符的值
//                statement.setString(3, );  // 设置第三个占位符的值
//                statement.setString(4, );  // 设置第四个占位符的值
//                statement.setInt(5, 0);  // 设置第四个占位符的值
//                int rowsInserted = statement.executeUpdate();  // 执行插入操作并获取受影响的行数
//                if (rowsInserted > 0) {
//                    System.out.println("插入成功！");  // 可选：输出插入成功的消息
//                }

//                statement.close();  // 关闭语句
//                conn.close();  // 关闭连接
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // todo 下载链接

    @GetMapping("/downloadFile/{filename}")
    public Result downloadFile(@PathVariable String filename, HttpServletResponse response) throws IOException {
        response.addHeader("Content-Disposition", "attachment; finalname=" + URLEncoder.encode(filename, "UTF-8"));
        return load(filename, response);
    }
    @GetMapping("/preview/{filename}")
    public Result preview(@PathVariable String filename, HttpServletResponse response) throws IOException {
        return load(filename, response);
    }
    public Result load(String filename, HttpServletResponse response) throws IOException {
        String filePath = ROOT_PATH + File.separator + filename;
        byte[] bytes = FileUtil.readBytes(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes); // 字节数组
        outputStream.flush();
        outputStream.close();
        return Result.success();
    }
//    @PostMapping("/test")
//    public String dd() {
//        return ROOT_PATH;
//    }

}

