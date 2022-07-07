package cn.kys.generate.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 表信息
 * </p>
 *
 * @author whx
 * @since 2022/7/5 下午6:00
 */
public class Table {

    /**
     * 作者
     */
    private String author;

    /**
     * 时间
     */
    private String dateTime;

    /**
     * 基础包路径
     */
    private String basePackage;

    /**
     * 原始表名
     */
    private String originName;

    /**
     * 名称
     */
    private String name;

    /**
     * 首字母大写的名称
     */
    private String uName;

    /**
     * 列信息
     */
    private List<Column> columns;

    /**
     * 注释
     */
    private String desc;

    /**
     * 列路径集合
     */
    private Set<String> columnPackage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
        List<String> collect = columns.stream().map(Column::getTypePackage).toList();
        columnPackage = new HashSet<>(collect);
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<String> getColumnPackage() {
        return columnPackage;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }
}
