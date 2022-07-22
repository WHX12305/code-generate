package ${basePackage}.query.entity;

<#list columnPackage as cp>
import ${cp};
</#list>
/**
 * <p>
 * ${name}, ${desc}
 * </p>
 *
 * @author ${author}
 * @since ${dateTime}
 */
public class ${uName}Query implements Serializable{

    private static final long serialVersionUID = ${serialNo}L;
<#list columns as column>
<#if column.index>
<#if column.desc != "">
    /**
     * ${column.desc}
     */</#if>
    private ${column.type} ${column.name};
</#if>
</#list>
<#list columns as column>
<#if column.index>
    <#if column.desc != "">
    /**
     * 获取${column.desc}
     */</#if>
    public ${column.type} get${column.uName}() {
        return ${column.name};
    }
    <#if column.desc != "">
    /**
     * 写入${column.desc}
     */</#if>
    public void set${column.uName}(${column.type} ${column.name}) {
        this.${column.name} = ${column.name};
    }
</#if>
</#list>
}