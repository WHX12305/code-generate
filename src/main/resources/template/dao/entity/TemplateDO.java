package ${basePackage}.dao.entity;
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
public class ${uName}DO{

<#list columns as column>
    <#if column.desc != "">
    /**
     * ${column.desc}
     */</#if>
    private ${column.type} ${column.name};
</#list>
<#list columns as column>

    public ${column.type} get${column.uName}() {
        return ${column.name};
    }

    public void set${column.uName}(${column.type} ${column.name}) {
        this.${column.name} = ${column.name};
    }
</#list>
}