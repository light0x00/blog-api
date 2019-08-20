package com.light.blog.dao.test.toolkit;


import com.light.blog.dao.MyBaseMapper;

/**
 * <p>
 * 生成entity\mapper\xml
 * </p>
 *
 * @author light
 * @since 2018-11-12
 */
public class CodeGenerator {

    public static void main(String[] args) {

        /*
            ----------------------------- dao层代码生成配置 -----------------------------
         */
        String url = "jdbc:mysql://localhost:3306/blog?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String driver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String pwd = "root";

         /*
            ----------------------------- end -----------------------------
         */

        MpDbCodeGenerator.DbCodeGenConfig config = new MpDbCodeGenerator.DbCodeGenConfig();

        config.setUrl(url);
        config.setDriver(driver);
        config.setUser(user);
        config.setPwd(pwd);

        config.setAuthor("light");
        config.setPackageRoot(System.getProperty("user.dir") + "/blog-dao/src/main/java/");
        config.setEntityPackage("com.light.blog.dao.entities");
        config.setMapperPackage("com.light.blog.dao.mapper");
        config.setXmlPath(System.getProperty("user.dir") + "/blog-dao/src/main/resources/mapper/");
        config.setTargetTables(null);
        config.setBaseMapper(MyBaseMapper.class.getName());

        MpDbCodeGenerator.exec(config);


    }

}

