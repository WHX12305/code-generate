package cn.kys.generate.process;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 生成器父类
 * </p>
 *
 * @author whx
 * @since 2022/7/6 下午5:22
 */
public abstract class GenerateProcess implements Generate{
    protected final static String controller = ".controller.";
    protected final static String service = ".service.";
    protected final static String serviceImpl = ".service.impl.";
    protected final static String entity = ".dao.entity.";
    protected final static String daoQuery = ".dao.query.";
    protected final static String mapper = ".dao.mapper.";
    protected final static String dto = ".model.dto.";
    protected final static String dtoQuery = ".model.dto.query.";
    protected final static String enums = ".model.enums.";
    /**
     * 获取freemark模板
     */
    protected Template getTemplate(String templateFilePath, String templateFileName) throws IOException {
        // 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 第二步：设置模板文件所在的路径。
        configuration.setDirectoryForTemplateLoading(new File(templateFilePath));
        // 第三步：设置模板文件使用的字符集。一般就是utf-8.
        configuration.setDefaultEncoding("utf-8");
        // 第四步：加载一个模板，创建一个模板对象。
        return configuration.getTemplate(templateFileName);
    }
}
