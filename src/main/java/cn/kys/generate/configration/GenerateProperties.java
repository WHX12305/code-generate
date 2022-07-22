package cn.kys.generate.configration;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * <p>
 * 配置生成参数
 * </p>
 *
 * @author whx
 * @since 2022/7/5 下午6:30
 */
public class GenerateProperties {

    /**
     * 配置文件
     */
    private static Properties props = new Properties();

    /**
     * 作者
     */
    private static String author;

    /**
     * 包路径
     */
    private static String basePackage;

    /**
     * 是否启用swagger
     */
    private static Boolean enableSwagger;

    /**
     * 数据库链接url
     */
    private static String url;

    /**
     * 数据库用户名
     */
    private static String username;

    /**
     * 密码
     */
    private static String password;

    /**
     * 表前缀去除
     */
    private static String tableSuffix;

    /**
     * 逻辑删除字段名
     */
    private static String deleteName;

    /**
     * 需要生成的表‘,’分割
     */
    private static List<String> tableNames;

    /**
     * 项目路径
     */
    private static String outPath;

    /**
     * 模板路径
     */
    private static String templatePath;

    static {
        try {
            InputStream is = GenerateProperties.class.getClassLoader().getResourceAsStream("generate.properties");
            props.load(is);
            author = props.getProperty("author");
            basePackage = props.getProperty("basePackage");
            enableSwagger = Boolean.valueOf(props.getProperty("enableSwagger"));
            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");
            tableSuffix = props.getProperty("tableSuffix");
            deleteName = props.getProperty("deleteName");
            tableNames = Arrays.asList(props.getProperty("tableNames").split(","));
            outPath = GenerateProperties.class.getClassLoader().getResource("").getPath().replace("/target/classes/", "/src/main/resources/out/");
            templatePath = GenerateProperties.class.getClassLoader().getResource("").getPath().replace("/target/classes/", "/src/main/resources/template/");
            Class.forName(props.getProperty("driver"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getAuthor() {
        return author;
    }

    public static String getBasePackage() {
        return basePackage;
    }

    public static Boolean getEnableSwagger() {
        return enableSwagger;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getTableSuffix() {
        return tableSuffix;
    }

    public static List<String> getTableNames() {
        return tableNames;
    }

    public static String getOutPath() {
        return outPath;
    }

    public static String getTemplatePath() {
        return templatePath;
    }

    public static String getDeleteName() {
        return deleteName;
    }
}
