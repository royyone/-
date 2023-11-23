package com.example.springboot2.controller;

import com.example.springboot2.ApiResult;
import com.example.springboot2.SQL;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
    private String res[][] = new String[10100][20];
    // 常量
    @PostMapping("/fileHandle")
    public String Summary(@RequestParam("excelFile") MultipartFile excelFile) throws IOException {
        // 处理excel文件
        excelHandle(excelFile);
        // 声明pdf生成类
        return "win!!!";
    }

    @PostMapping("/certificateCreate")
    public String Create(@RequestBody Certificate certificate) throws IOException, SQLException {
//        System.out.println(status);
        String TEMP_PATH = "public/Demo3.pdf";
        if(certificate.getStatus().equals(0)) {
            TEMP_PATH = "public/Demo2.pdf";
        }
        String DEST;
        DEST = "public/certificate/certificate_" + certificate.getId() + ".pdf";
        certificate.setFile_path(DEST);
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
        System.out.println(certificate.getFile_path());
        System.out.println(certificate.getId());
        // 设置要更新的列的值
        statement.setString(1, certificate.getFile_path()); // 设置第一个问号的值为新值
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
        String msg[] = {"奖状生成成功", "奖状生成失败"};
        return ApiResult.getApiResult(0, null, msg);
    }
    @GetMapping("/fileLoad")
    public String fileLoad() {
        int code = 1;
        String msg[] = new String[]{"路径查找失败", "路径查找成功"};
        Map<String, Map<String, String> > res = new HashMap<>();
        for(int i=0; i<2; i++) {
            Map<String, String> temp = new HashMap<>();
            temp.put("url", "public/ppttest1027/ppttest"+i+".pptx");
            // 查询数据库确定文件是否已盖章
            temp.put("fileName", "ppttest"+i+".pptx");
            temp.put("seal", "0");
            res.put(""+i, temp);
        }
        return ApiResult.getApiResult(code, res, msg);
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

    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response) {
        // 读取resource目下文件
        String templatePath =  "classpath:public/1.txt";
        String filename = " 1.txt ";

        File file = null;
        Logger log = null;
        try {
            file = ResourceUtils.getFile(templatePath);
        } catch (FileNotFoundException e) {
            log.warn("文件不存在 {}", filename);
            // todo, 可以在流中返回“文件不存在“，这样用户可以下载到文件，但是内容为”文件不存在”
            return;
        }

        if (file.isFile()) {
            byte[] fileNameBytes = filename.getBytes(StandardCharsets.UTF_8);
            filename = new String(fileNameBytes, 0, fileNameBytes.length, StandardCharsets.ISO_8859_1);

            response.reset();
            response.setContentType("application/force-download");
            response.setCharacterEncoding("utf-8");
            response.setContentLength((int) file.length());
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);

            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                byte[] buff = new byte[1024];
                OutputStream os = response.getOutputStream();
                int i;
                while ((i = bis.read(buff)) != -1) {
                    os.write(buff, 0, i);
                    os.flush();
                }
            } catch (IOException e) {
                log.error("下载出错 {}，错误原因 {}", filename, e.getMessage());
            }
        } else {
            log.warn("文件不存在 {}", filename);
            // todo, 可以在流中返回“文件不存在“，这样用户可以下载到文件，但是内容为”文件不存在”
        }
    }

}
class Certificate {
    Integer id;
    String match_name;
    String school_name;
    String college_Name;
    String stu_name;
    String team_name;
    String award;
    String status;
    String file_path;
    String adviser;
    String isSelect;

    public Integer getId() {
        return id;
    }

    public String getMatch_name() {
        return match_name;
    }

    public String getSchool_name() {
        return school_name;
    }

    public String getCollege_Name() {
        return college_Name;
    }

    public String getStu_name() {
        return stu_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public String getAward() {
        return award;
    }

    public String getStatus() {
        return status;
    }

    public String getFile_path() {
        return file_path;
    }

    public String getAdviser() {
        return adviser;
    }

    public String getIsSelect() {
        return isSelect;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMatch_name(String match_name) {
        this.match_name = match_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public void setCollege_Name(String college_Name) {
        this.college_Name = college_Name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }

    public void setIsSelect(String isSelect) {
        this.isSelect = isSelect;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", match_name='" + match_name + '\'' +
                ", school_name='" + school_name + '\'' +
                ", college_Name='" + college_Name + '\'' +
                ", stu_name='" + stu_name + '\'' +
                ", team_name='" + team_name + '\'' +
                ", award='" + award + '\'' +
                ", status='" + status + '\'' +
                ", file_path='" + file_path + '\'' +
                ", adviser='" + adviser + '\'' +
                ", isSelect='" + isSelect + '\'' +
                '}';
    }
}
