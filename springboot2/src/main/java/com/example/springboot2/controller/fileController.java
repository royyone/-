package com.example.springboot2.controller;

import com.example.springboot2.ApiResult;
import com.example.springboot2.temp.pptxUpdate;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.colors.Indexed;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.functions.Index;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/fileController")
public class fileController {
    private String res[][] = new String[10100][20];
    // 常量
    @PostMapping("/fileHandle")
    public String Summary(@RequestParam("excelFile") MultipartFile input) throws IOException {
        // 处理excel文件
        excelHandle(input);
        // 声明pdf生成类
        final String TEMP_PATH = "public/Demo2.pdf";
        String DEST;

        for(int i=1; i<7; i++) { // res.length(); 遍历每条信息
            DEST = "public/certificate/certificate_" + i + ".pdf";
            PdfDocument pdfDocument = new PdfDocument(new PdfReader(TEMP_PATH), new PdfWriter(DEST));
            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, false);
            DeviceRgb color = new DeviceRgb(31,78,121);
            PdfFont font = PdfFontFactory.createFont("public/simsun.ttc,0");
            for(int j=1; j<=3; j++) {
                PdfFormField receptionistName = form.getFormFields().get("Text"+j);
                receptionistName.setValue(res[i][j]).setColor(color).setFont(font).setFontSize(18);
                // 清除表单域
//        receptionistName.setJustification(PdfFormField.ALIGN_CENTER); // 设置居中对齐
            }
            // 奖项
            color = new DeviceRgb(128,0,0);
            font = PdfFontFactory.createFont("public/simkai.ttf");
            PdfFormField receptionistName = form.getFormFields().get("Text"+4);
            receptionistName.setValue(res[i][4]).setColor(color).setFont(font).setJustification(PdfFormField.ALIGN_CENTER).setFontSize(54);
            form.flattenFields();
            pdfDocument.close();
        }
        return "win!!!";
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
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
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
