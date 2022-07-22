package ${basePackage}.dao.mapper;
import ${basePackage}.dao.entity.${uName}DO;
import ${basePackage}.dao.query.${uName}Query;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ${uName}Mapper {

    /**
     * 根据id查询单条
     */
    ${uName}DO selectOneById(@Param("id") Long id);

    /**
     * 根据id列表查询多条
     */
    List<${uName}DO> selectByIds(List<Long> ids);

    /**
     * 条件查询多条
     */
    List<${uName}DO> selectByCondition(${uName}DaoQuery query);

    /**
     * 新增一条
     */
    int insert(${uName}DO ${name});

    /**
     * 根据主键id更新
     */
    void updateById(${uName}DO ${name});
}