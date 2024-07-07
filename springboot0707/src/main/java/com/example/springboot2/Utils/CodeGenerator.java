/**
 * function:
 * author: suhsbeba
 * date: 2024/7/7 15:07
 */
package com.example.springboot2.Utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/_project",
                        "root", "root")
                .globalConfig(builder -> {
                    builder.author("suhsbeba") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("com.example") // 设置父包名
                                .moduleName("springboot2") // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.xml,System.getProperty("user.dir")+"/src/main/resources/mapper")) // 设置mapperXml生成路径
                                .mapper("Dao") // 设置 Mapper 接口包名
                                .controller("controller")//控制层包名
                                .entity("entity")//实体类包名
                                .xml("mappers") // 设置 Mapper XML 文件包名
                                .service("service") // 设置 Service 接口包名
                                .serviceImpl("service.impl") // 设置 Service 实现类包名
                )
//                .strategyConfig(builder ->
//                        builder.addInclude("t_simple") // 设置需要生成的表名
//                                .addInclude("aus")
//                                .addTablePrefix("t_", "c_") // 设置过滤表前缀
//                )
                .strategyConfig(builder -> {
                            //设置要生成的表名
                            builder.addInclude("award_advisor", "award_message", "award_student", "competition_message", "student_message",
                                            "teacher_message")
                                    .entityBuilder()
                                    .enableLombok()
                                    .enableChainModel()
                                    .naming(NamingStrategy.underline_to_camel)//数据表映射实体命名策略：默认下划线转驼峰underline_to_camel
                                    .columnNaming(NamingStrategy.underline_to_camel)//表字段映射实体属性命名规则：默认null，不指定按照naming执行
                                    .idType(IdType.AUTO)//添加全局主键类型
                                    .formatFileName("%s")//格式化实体名称，%s取消首字母I,
                                    .mapperBuilder()
                                    .enableMapperAnnotation()//开启mapper注解
                                    .enableBaseResultMap()//启用xml文件中的BaseResultMap 生成
                                    .enableBaseColumnList()//启用xml文件中的BaseColumnList
                                    .formatMapperFileName("%sMapper")//格式化Dao类名称
                                    .formatXmlFileName("%sMapper")//格式化xml文件名称
                                    .serviceBuilder()
                                    .formatServiceFileName("%sService")//格式化 service 接口文件名称
                                    .formatServiceImplFileName("%sServiceImpl")//格式化 service 接口文件名称
                                    .controllerBuilder()
                                    .enableRestStyle();
                        })
                .execute();
        System.out.println("代码生成完毕");
    }
}
