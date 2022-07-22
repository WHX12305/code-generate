package ${basePackage}.service;
import ${basePackage}.model.dto.${uName}DTO;

import java.util.List;

public interface I${uName}Service {

    /**
     * 根据id查询单条
     */
    ${uName}DTO selectOneById(@Param("id") Long id);

    /**
     * 根据id列表查询多条
     */
    List<${uName}DTO> selectByIds(List<Long> ids);

    /**
     * 条件查询多条
     */
    List<${uName}DTO> selectByCondition(${uName}DTO ${name});

    /**
     * 新增一条
     */
    ${uName}DTO insert(${uName}DTO ${name});

    /**
     * 根据主键id更新
     */
    void updateById(${uName}DTO ${name});
}