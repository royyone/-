package com.example.springboot2.controller;


import cn.hutool.core.io.FileUtil;
import com.example.springboot2.Result;
import com.example.springboot2.SQL;
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
    // 常量
    @PostMapping("/fileHandle")
    public String Summary(@RequestParam("excelFile") MultipartFile excelFile) throws IOException {
        // 处理excel文件
        excelHandle(excelFile);
        // 声明pdf生成类
        return "win!!!";
    }
    @PostMapping("/certificateCreate1")
    public Result Create1(@RequestBody Certificate certificate) throws IOException, SQLException{
        String TEMP_PATH = "public/Demo3.pdf";
        certificate.setStatus(1);
        return this.Create(TEMP_PATH, certificate);
    }
    @PostMapping("/certificateCreate2")
    public Result Create2(@RequestBody Certificate certificate) throws IOException, SQLException{
        String TEMP_PATH = "public/Demo2.pdf";
        certificate.setStatus(2);
        return this.Create(TEMP_PATH, certificate);
    }
    public Result Create(String TEMP_PATH, Certificate certificate) throws IOException, SQLException {
//        System.out.println(status);

        String DEST;
        DEST = "public/certificate/certificate_" + certificate.getId() + ".pdf";
        String temp = "certificate_" + certificate.getId() + ".pdf";
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
//        receptionistName.setJustification(PdfFormField.ALIGN_CENTER); // 设置居中对齐
//        }
        // 奖项
        color = new DeviceRgb(128,0,0);
        font = PdfFontFactory.createFont("public/simkai.ttf");
        receptionistName = form.getFormFields().get("Text"+4);
        receptionistName.setValue(certificate.getAward()).setColor(color).setFont(font).setJustification(PdfFormField.ALIGN_CENTER).setFontSize(54);
        form.flattenFields();
        pdfDocument.close();
        // 在数据库中修改文件地址
        Connection connection = DriverManager.getConnection(SQL.url, SQL.user, SQL.password);
        PreparedStatement statement = connection.prepareStatement("UPDATE result SET file_path = ? WHERE id = ?");
//        System.out.println(certificate.getFile_path());
//        System.out.println(certificate.getId());
        // 设置要更新的列的值
        statement.setString(1, certificate.getFile_path()); // 设置第一个问号的值为新值
        statement.setInt(2, certificate.getId()); // 设置第二个问号的值为指定的记录的ID
        statement.executeUpdate();

        statement = connection.prepareStatement("UPDATE result SET status = ? WHERE id = ?");
        statement.setInt(1, certificate.getStatus()); // 设置第1个问号的值为status
        statement.setInt(2, certificate.getId()); // 设置第二个问号的值为指定的记录的ID
        statement.executeUpdate();
        statement.close();
        connection.close();
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
    public void excelHandle (MultipartFile input){
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
                // 插入数据
                String sql="INSERT INTO result (school_name, stu_name, adviser, award, status) VALUES (?, ?, ?, ?, ?)";
                System.out.println("INSERT INTO result (school_name, stu_name, adviser, award) VALUES " +
                        "("+res[i][0]+", "+res[i][1]+", "+res[i][2]+", "+res[i][3]+")");
                Connection conn = DriverManager.getConnection(SQL.url, SQL.user, SQL.password);
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, res[i][1]);  // 设置第一个占位符的值
                statement.setString(2, res[i][2]);  // 设置第二个占位符的值
                statement.setString(3, res[i][3]);  // 设置第三个占位符的值
                statement.setString(4, res[i][4]);  // 设置第四个占位符的值
                statement.setInt(5, 0);  // 设置第四个占位符的值
                int rowsInserted = statement.executeUpdate();  // 执行插入操作并获取受影响的行数
                if (rowsInserted > 0) {
                    System.out.println("插入成功！");  // 可选：输出插入成功的消息
                }

                statement.close();  // 关闭语句
                conn.close();  // 关闭连接
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
@Data
class Certificate {
    private Integer id;
    private String match_name;
    private String school_name;
    private String college_Name;
    private String stu_name;
    private String team_name;
    private String award;
    private Integer status;
    private String file_path;
    private String adviser;
    private String isSelect;
}
