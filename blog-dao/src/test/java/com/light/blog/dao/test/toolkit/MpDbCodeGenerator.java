package com.light.blog.dao.test.toolkit;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *      基于mp 生成entity、mapper
 * </p>
 *
 * @author light
 * @since 2019/8/19
 */
public class MpDbCodeGenerator {

    @Data
    public static class DbCodeGenConfig {

        String url;
        String driver;
        String user;
        String pwd;

        String packageRoot ; //package根路径
        String entityPackage ;//entity 文件生成包路径
        String mapperPackage ;  //mapper 文件生成包路径

        String xmlPath;
        String[] targetTables; //针对哪些表生成,null表示生成所有

        //基类
        String baseEntity;
        String baseMapper;

        String author;
    }

    public static void exec(DbCodeGenConfig config) {

        String url = config.getUrl();
        String driver = config.getDriver();
        String user = config.getUser();
        String pwd = config.getPwd();
        String outputDir = config.getPackageRoot();
        String entityPackage = config.getEntityPackage();
        String mapperPackage = config.getMapperPackage();
        String xmlPath = config.getXmlPath();
        String[] targetTables = config.getTargetTables();

        String baseEntity = config.getBaseEntity();
        String baseMapper = config.getBaseMapper();

        String author = config.getAuthor();


        AutoGenerator mpg = new AutoGenerator();

        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setFileOverride(false); //不覆盖已经存在的文件

        //xml
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);

        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driver);
        dsc.setUsername(user);
        dsc.setPassword(pwd);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("");
        pc.setModuleName("");
        pc.setEntity(entityPackage);
        pc.setMapper(mapperPackage);
        pc.setController(entityPackage);
        pc.setService(mapperPackage);
        pc.setServiceImpl(mapperPackage);

        mpg.setPackageInfo(pc);


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        //为了防止Mp创建 Service、Controller （Mp这个codegen是真的很烂）
        cfg.setFileCreate(
                new IFileCreate() {
                    @Override
                    public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                        if (fileType == FileType.SERVICE || fileType == FileType.CONTROLLER || fileType == FileType.SERVICE_IMPL) {
                            return false;
                        }
                        return true;
                    }
                }
        );

        List<FileOutConfig> focList = new ArrayList<>();
        File xmlDir = new File(xmlPath);
        if (!xmlDir.exists()) {
            xmlDir.mkdirs();
        }
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {

                // 自定义输入文件名称
                return xmlPath + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);  //表名 下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);    //列名 下划线转驼峰
        strategy.setSuperEntityClass(baseEntity);
        strategy.setSuperMapperClass(baseMapper);
        strategy.setEntityLombokModel(true);    //使用lombok
        strategy.setInclude(targetTables);

        /*
            放在 BaseEntity的字段
         */
//        strategy.setSuperEntityColumns("id", "is_deleted", "create_time", "create_user_id", "update_time", "update_user_id");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
