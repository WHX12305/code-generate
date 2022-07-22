package cn.kys.generate.process;

import cn.kys.generate.configration.GenerateProperties;
import cn.kys.generate.model.Table;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

/**
 * <p>
 * 常量类生成
 * </p>
 *
 * @author whx
 * @since 2022/7/13 上午11:51
 */
public class ConstantsGenerateProcess extends GenerateProcess{
    @Override
    public void generate(Table table) throws Exception {
        Template template = getTemplate(GenerateProperties.getTemplatePath() + constant.replace(".", "/"), "TemplateConstants.java");
        File file = new File(GenerateProperties.getOutPath() + constant.replace(".", "/"));
        if(!file.exists()){
            file.mkdirs();
        }

        // 创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
        Writer out = new FileWriter(GenerateProperties.getOutPath()
                + constant.replace(".", "/")
                + table.getuName() + "Constants.java");
        // 调用模板对象的process方法输出文件。
        template.process(table, out);
        // 关闭流。
        out.close();
    }
}
