package cn.loveyx815.blog.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
  * 运行此方法重新生成mybatis相关的文件，慎用
  * 运行之后会覆盖com.dao 以及com.entity的类，但是对应的mapper/*.xml中的数据库操作
  */
public class MainGenerator {

    /**
      * MyBatis反向生成工程
      * @param args
      */
         public static void main(String[] args) throws InvalidConfigurationException {
        try {
            System.out.println("MyBatis反向生成");
            List<String> warnings = new ArrayList<>();
            boolean overwrite = true;
            //配置文件
            File configFile = new
                    File("./src/main/resources/generatorConfig.xml");
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            System.out.println("执行完毕");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
         }
}
