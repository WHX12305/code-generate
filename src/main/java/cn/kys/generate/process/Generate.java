package cn.kys.generate.process;

import cn.kys.generate.model.Table;
import freemarker.template.Template;

import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 * 生成器
 * </p>
 *
 * @author whx
 * @since 2022/7/6 下午5:17
 */
public interface Generate {

    /**
     * 生成文件
     * @param table 数据实体
     */
    void generate(Table table) throws Exception;
}
