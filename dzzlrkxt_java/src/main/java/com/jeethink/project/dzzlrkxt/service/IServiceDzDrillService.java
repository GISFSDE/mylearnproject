package com.jeethink.project.dzzlrkxt.service;

import com.jeethink.project.dzzlrkxt.domain.DzDrill;
import com.jeethink.project.dzzlrkxt.domain.vo.DrillPageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (DzDrill)表服务接口
 *
 * @author makejava
 * @since 2023-06-28 15:15:22
 */
public interface IServiceDzDrillService {

    /**
     * 通过ID查询单条数据
     *
     * @param drillId 主键
     * @return 实例对象
     */
    DzDrill queryById(Long drillId);
    DrillPageVo queryByIds(Long[] drillIds,String drillType, int pageNum, int pageSize);

    /**
     * 分页查询
     *
     * @param dzDrill 筛选条件
     * @return 查询结果
     */
    DrillPageVo queryByPage(DzDrill dzDrill, int pageNum, int pageSize);
    Long querySumNum(Long ProjectId);
    int insertBatch(@Param("entities") List<DzDrill> entities);
    /**
     * 新增数据
     *
     * @param dzDrill 实例对象
     * @return 实例对象
     */
    DzDrill insert(DzDrill dzDrill);

    /**
     * 修改数据
     *
     * @param dzDrill 实例对象
     * @return 实例对象
     */
    DzDrill update(DzDrill dzDrill);

    /**
     * 通过主键删除数据
     *
     * @param drillId 主键
     * @return 是否成功
     */
    boolean deleteById(Long drillId);

    String[] queryDrillType();
}
