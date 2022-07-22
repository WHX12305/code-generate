package cn.kys.generate.process;

import cn.kys.generate.configration.GenerateProperties;
import cn.kys.generate.model.Table;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

/**
 * <p>
 *
 * </p>
 *
 * @author whx
 * @since 2022/7/22 下午4:46
 */
public class DaoQueryGenerateProcess extends GenerateProcess{
    @Override
    public void generate(Table table) throws Exception {
        Template template = getTemplate(GenerateProperties.getTemplatePath() + daoQuery.replace(".", "/"), "TemplateDaoQuery.java");
        File file = new File(GenerateProperties.getOutPath() + daoQuery.replace(".", "/"));
        if(!file.exists()){
            file.mkdirs();
        }

        // 创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
        Writer out = new FileWriter(GenerateProperties.getOutPath()
                + daoQuery.replace(".", "/")
                + table.getuName() + "DaoQuery.java");
        // 调用模板对象的process方法输出文件。
        template.process(table, out);
        // 关闭流。
        out.close();
    }
}
