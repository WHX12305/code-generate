package cn.kys.generate.builder;

import cn.hutool.core.util.IdUtil;
import cn.kys.generate.configration.GenerateProperties;
import cn.kys.generate.model.Column;
import cn.kys.generate.model.Table;
import cn.kys.generate.process.*;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 代码构建器
 * </p>
 *
 * @author whx
 * @since 2022/7/6 上午11:48
 */
public class CodeBuilder implements Serializable {

    List<Generate> generates = Arrays.asList(
            new EntityGenerateProcess(),
            new MapperGenerateProcess(),
            new MapperXMLGenerateProcess(),
            new DTOGenerateProcess(),
            new ConstantsGenerateProcess(),
            new DaoQueryGenerateProcess());
    /**
     * 待构建表信息
     */
    private List<Table> tables;

    public void build() {
        try {
            prepare();
            generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建tables
     */
    private void prepare() throws Exception{
        tables = new ArrayList<>();
        List<String> tableNames = GenerateProperties.getTableNames();
        Connection conn = DriverManager.getConnection(
                GenerateProperties.getUrl(),
                GenerateProperties.getUsername(),
                GenerateProperties.getPassword());
        DatabaseMetaData metaData = conn.getMetaData();
        String databaseType = metaData.getDatabaseProductName();
        if (databaseType.equals("MySQL")) {
            //获取所有表结构
            ResultSet tableResultSet = metaData.getTables(null, "%", "%", new String[]{"TABLE"});
            while (tableResultSet.next()) {
                //获取表名
                String tableName = tableResultSet.getString("TABLE_NAME");
                //匹配表名
                if (tableNames.contains(tableName) || tableNames.get(0).equals("all")) {

                    String modelName = hump(tableName);
                    Table table = new Table();
                    table.setOriginName(tableName);
                    table.setName(modelName);
                    table.setuName(firstUpper(modelName));
                    table.setBasePackage(GenerateProperties.getBasePackage());
                    table.setAuthor(GenerateProperties.getAuthor());
                    table.setDeleteName(GenerateProperties.getDeleteName());
                    table.setDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(
                            "SELECT * FROM information_schema.`TABLES` WHERE TABLE_SCHEMA = '"
                                    + tableResultSet.getString("TABLE_CAT") +
                                    "' AND TABLE_NAME = '"
                                    + tableName + "'");
                    rs.next();
                    table.setDesc(rs.getString("TABLE_COMMENT"));

                    //获取索引列
                    rs = stmt.executeQuery("SHOW INDEX FROM " + tableName);
                    Set<String> indexCol = new HashSet<>();
                    while (rs.next()){
                        String columnNames = rs.getString("Column_name");
                        String keyName = rs.getString("Key_name");
                        if (!Objects.equals(keyName, "PRIMARY")){
                            indexCol.addAll(Arrays.asList(columnNames.split(",")));
                        }
                    }

                    ResultSet columnSet = metaData.getColumns(null, null, tableName, "%");
                    List<Column> columns = new ArrayList<>();
                    while (columnSet.next()) {
                        //列的描述
                        String remarks = columnSet.getString("REMARKS");
                        //获取列名
                        String columnName = columnSet.getString("COLUMN_NAME");

                        Column column = new Column();
                        if (columnName.equals("id")){
                            column.setPrimary(Boolean.TRUE);
                        }else {
                            column.setPrimary(Boolean.FALSE);
                        }
                        if (indexCol.contains(columnName)){
                            column.setIndex(Boolean.TRUE);
                        }else {
                            column.setIndex(Boolean.FALSE);
                        }

                        column.setOriginName(columnName);
                        column.setName(hump(columnName));
                        column.setuName(firstUpper(hump(columnName)));
                        //获取类型，并转成JavaType
                        String javaType = getType(columnSet.getInt("DATA_TYPE"));
                        column.setType(javaType.split("\\.")[2]);
                        column.setTypePackage(javaType);
                        column.setDesc(remarks);
                        columns.add(column);
                    }
                    table.setColumns(columns);
                    table.setSerialNo(IdUtil.getSnowflakeNextIdStr());
                    tables.add(table);

                    rs.close();
                    stmt.close();
                }
            }
        }
        conn.close();
    }

    private void generate() throws Exception{
        for (Generate generate : generates) {
            for (Table table : tables) {
                generate.generate(table);
            }
        }
    }



    /**
     * 转驼峰
     */
    private String hump(String tableName) {
        tableName = tableName.replace(GenerateProperties.getTableSuffix(), "");
        String[] s = tableName.split("_");
        StringBuilder sb = new StringBuilder(s[0]);
        for (int i = 1; i < s.length; i++) {
            sb.append(firstUpper(s[i]));
        }
        return sb.toString();
    }

    /**
     * 首字母大写
     */
    private String firstUpper(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1);
    }

    /**
     * 获取数据库对应java的type
     */
    private String getType(int value) {
        return switch (value) {
            case -6 -> "java.lang.Integer";
            case 5 -> "java.lang.Integer";
            case 4 -> "java.lang.Long";
            case -5 -> "java.lang.Long";
            case 6 -> "java.lang.Float";
            case 8 -> "java.lang.Double";
            case 1 -> "java.lang.String";
            case 12 -> "java.lang.String";
            case -1 -> "java.lang.String";
            case 91 -> "java.util.Date";
            case 92 -> "java.time.LocalDateTime";
            case 93 -> "java.time.LocalDateTime";
            case 16 -> "java.lang.Boolean";
            case -7 -> "java.lang.Integer";
            default -> "java.lang.String";
        };
    }
}
