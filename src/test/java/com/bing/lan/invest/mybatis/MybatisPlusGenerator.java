package com.bing.lan.invest.mybatis;

import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.bing.lan.invest.base.BaseEntity;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MybatisPlusGenerator {

    private static final String url = "jdbc:mysql://localhost:3306/invest?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8";

    public static void main(String[] args) {

        // String projectPath = new File("").getCanonicalPath();
        String projectPath = System.getProperty("user.dir");

        FastAutoGenerator.create(url, "root", "admin")
                .globalConfig(builder -> {

                    builder.author("oopcoder") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(projectPath + "\\src\\main\\java"); // 指定输出目录

                }).packageConfig(builder -> {

                    builder.parent("com.bing.lan.invest") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .entity("domain.entity")// 修改entity默认包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    projectPath + "\\src\\main\\resources\\mapper")); // 修改mapperXml默认生成路径

                }).strategyConfig(builder -> {

                    builder.addInclude("invest_account") // 设置需要生成的表名
                            .addInclude("invest_fund")
                            .addInclude("invest_turnover")
                            .addInclude("invest_account_fund_detail")
                            .addInclude("invest_ssj_trans")
                            .addTablePrefix("invest_", "c_"); // 设置过滤表前缀

                    builder.entityBuilder()
                            // .enableFileOverride()
                            .enableLombok()
                            //.addSuperEntityColumns("id", "update_time", "create_time")
                            .superClass(BaseEntity.class);

                    builder.mapperBuilder()
                            // .enableFileOverride()
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation();

                    builder.serviceBuilder()
                            // .enableFileOverride()
                            .convertServiceFileName(entityName -> entityName + "Service");

                    builder.controllerBuilder()
                            // .enableFileOverride()
                            .enableRestStyle();

                }).injectionConfig(builder -> {
                    Map<String, String> map = new HashMap<>();
                    Map<String, Object> customMap = new HashMap<>();

                    // 生成自定义的dto文件
                    customMap.put("dtoSuffix", "Dto");// 配置一些属性，可以在模板文件中使用这些属性
                    customMap.put("dtoPackage", "com.bing.lan.invest.domain.dto");

                    builder.customFile(fileBuilder -> {
                        fileBuilder.fileName("Dto.java")
                                .templatePath("/templates/dto.java.ftl")
                                // .enableFileOverride()
                                // 配置子包路径，查看/com/baomidou/mybatisplus/generator/engine/AbstractTemplateEngine.java:74  outputCustomFile
                                .packageName("domain/dto");
                    });

                    // 生成自定义的dao文件
                    customMap.put("daoSuffix", "Dao");
                    customMap.put("daoPackage", "com.bing.lan.invest.dao");

                    builder.customFile(fileBuilder -> {
                        fileBuilder.fileName("Dao.java")
                                .templatePath("/templates/dao.java.ftl")
                                // .enableFileOverride()
                                .packageName("dao");
                    });

                    builder.customFile(map);
                    builder.customMap(customMap);

                }).templateConfig(builder -> {

                    // 修改默认的模板
                    builder.entity("/templates/entity.java")
                            .controller("/templates/controller.java")
                            .mapper("/templates/mapper.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java");

                })
                .templateEngine(new MyFreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    public static class MyFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

        protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo,
                Map<String, Object> objectMap) {

            String entityName = tableInfo.getEntityName();
            String otherPath = getPathInfo(OutputFile.other);
            customFile.forEach((key, value) -> {
                // System.out.println("key: " + key + " , value: " + value);
                String fileName = String.format((otherPath + File.separator + entityName + "%s"), key);
                System.out.println("生成自定义文件名: " + fileName);
                outputFile(new File(fileName), objectMap, value, getConfigBuilder().getInjectionConfig().isFileOverride());
            });
        }
    }
}
