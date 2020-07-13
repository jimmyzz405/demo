package com.restful.demo.utils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

public class MyGenerator {

    @Test
    public void codeGenerator() {
        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setAuthor("Jimmy zZ");//Mapper,Service类注解中显示创建人信息
//        gc.setBaseColumnList(true); //在Mapper.xml文件中是否生成公用SQL代码段
//        gc.setBaseResultMap(true);  //在Mapper.xml文件中是否生成公用返回集合ResultMap
//        gc.setOpen(false);  //文件生成完毕后，是否需要打开所在路径
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://121.43.101.40:3306/rest?serverTimezone=UTC&useUnicode=true&useSSL=false&characterEncoding=utf8");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("1043cf308967dc8a");
//        mpg.setDataSource(dsc);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setParent("com.restful.demo");  //父级公用包名，就是自动生成的文件放在项目路径下的那个包中
//        mpg.setPackageInfo(pc);
//
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义Mapper.xml文件存放的路径
//                return projectPath + "/src/main/resources/mapper/"
//                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//        mpg.setTemplate(new TemplateConfig().setXml(null));
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);  //Entity文件名称命名规范
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel); //Entity字段名称
//        strategy.setEntityLombokModel(true); //是否使用lombok完成Entity实体标注Getting Setting ToString 方法
//        //strategy.setRestControllerStyle(true); //Controller注解使用是否RestController标注,否则是否开启使用Controller标注
//        strategy.entityTableFieldAnnotationEnable(true); //是否在Entity属性上通过注解完成对数据库字段的映射
//        strategy.setControllerMappingHyphenStyle(true);  //Controller注解名称，不使用驼峰，使用连字符
////        strategy.setTablePrefix("sys_");  //表前缀，添加该表示，则生成的实体，不会有表前缀，比如sys_dept 生成就是Dept
//        //strategy.setFieldPrefix("sys_");  //字段前缀
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        mpg.execute();
    }
}
