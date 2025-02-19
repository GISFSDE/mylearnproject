package com.jeethink.project.dzzlrkxt.mapper;

import com.jeethink.project.dzzlrkxt.domain.DzDrill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (DzDrill)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-28 15:15:00
 */
public interface DzDrillMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param drillId 主键
     * @return 实例对象
     */
    DzDrill queryById(Long drillId);

    /**
     * 查询指定行数据
     *
     * @param dzDrill 查询条件
     * @return 对象列表
     */
    List<DzDrill> queryAllByLimit(DzDrill dzDrill);

    /**
     * 统计总行数
     *
     * @param dzDrill 查询条件
     * @return 总行数
     */
    long count(DzDrill dzDrill);

    /**
     * 新增数据
     *
     * @param dzDrill 实例对象
     * @return 影响行数
     */
    int insert(DzDrill dzDrill);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DzDrill> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DzDrill> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DzDrill> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DzDrill> entities);

    /**
     * 修改数据
     *
     * @param dzDrill 实例对象
     * @return 影响行数
     */
    int update(DzDrill dzDrill);

    /**
     * 通过主键删除数据
     *
     * @param drillId 主键
     * @return 影响行数
     */
    int deleteById(Long drillId);

    String[] queryDrillType();

    List<DzDrill> queryByIds(@Param("drillIds")Long[] drillIds,String drillType);
}

