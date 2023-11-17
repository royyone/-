package com.example.springboot2.temp;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        // 常量
        final String time = "2024";
        final String item = "年浙江省第二十届大学生程序设计竞赛中荣获";
        final String itext = "特发此证，以资奖励！";
        final String tissue = "浙江省大学生科技竞赛委员会";
        final String TIME = "二〇二三年四月";
        // 声明pdf生成类
        pptxUpdate pptxtopdf = new pptxUpdate();

        // 声明获奖信息
        String str[][] = new String[10000][10];
        // 获取excel路径, 获取获奖信息
        String excel_path = "src\\main\\resource\\result.xlsx";
        readExcel.ReadExcel(excel_path, "", str);
        for (int i = 1; i < 10; i++) {
            pptxtopdf.setValue("学校：" + str[i][1], "学生：" + str[i][2],
                    "指导老师：" + str[i][3], time, item,
                    str[i][4], itext, tissue, TIME);
            String pptPath = "src\\main\\resource\\pptxPrint\\pptxtest" + i + ".pptx";
            String pdfPath = "src\\main\\resource\\pdfPrint\\pdfprint" + i + ".pdf";
            pptxtopdf.setPptx(pptPath);
            pptxtopdf.getPdf(pptPath, pdfPath);
            //System.out.print(str[i][j] + " ");
            //System.out.println();
        }
    }


}
