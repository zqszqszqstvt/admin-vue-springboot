package com.afa.adminback.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

//代码生成器
//by 阿发
//@since 2024-03-01
public class Generator {

    public static void main(String[] args) {
        generate();
    }
    private static void generate() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/warehouse?serverTimezone=GMT%2b8", "root", "123456789")
                .globalConfig(builder -> {
                    builder.author("阿发") // 设置作者
                            //.enableSwagger() // 开启 swagger2 模式  使用的是swagger3 所以注释掉了
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\learn\\myproject\\admin-vue-springboot\\adminBack\\src\\main\\java\\"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.afa.adminback") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\learn\\myproject\\admin-vue-springboot\\adminBack\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "sys_") // 设置过滤表前缀
                            .entityBuilder().enableFileOverride() // 覆盖已生成的实体类文件
                            .entityBuilder().enableLombok()
                            .mapperBuilder().enableMapperAnnotation().build()
                            .mapperBuilder().enableFileOverride() //覆盖生成的mapper文件
                            .controllerBuilder().enableHyphenStyle().enableRestStyle()
                            .controllerBuilder().enableFileOverride(); // 覆盖已生成的控制器文件
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
