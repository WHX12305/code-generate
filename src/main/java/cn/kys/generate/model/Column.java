package cn.kys.generate.model;

/**
 * <p>
 * 列信息
 * </p>
 *
 * @author whx
 * @since 2022/7/5 下午6:01
 */
public class Column {

    /**
     * 原始列名
     */
    private String originName;

    /**
     * 列名
     */
    private String name;

    /**
     * 首字母大写列名
     */
    private String uName;

    /**
     * 类型
     */
    private String type;

    /**
     * 类型路径
     */
    private String typePackage;

    /**
     * 备注
     */
    private String desc;

    /**
     * 是否为主键
     */
    private Boolean isPrimary;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypePackage() {
        return typePackage;
    }

    public void setTypePackage(String typePackage) {
        this.typePackage = typePackage;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }
}
