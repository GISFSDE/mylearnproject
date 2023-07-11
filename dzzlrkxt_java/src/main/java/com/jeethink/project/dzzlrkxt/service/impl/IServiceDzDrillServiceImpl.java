package com.jeethink.project.dzzlrkxt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeethink.project.dzzlrkxt.domain.DzDrill;
import com.jeethink.project.dzzlrkxt.domain.vo.DrillPageVo;
import com.jeethink.project.dzzlrkxt.mapper.DzDrillMapper;
import com.jeethink.project.dzzlrkxt.service.IServiceDzDrillService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DzDrill)表服务实现类
 *
 * @author makejava
 * @since 2023-06-28 15:15:22
 */
@Service("dzDrillService")
public class IServiceDzDrillServiceImpl implements IServiceDzDrillService {
    @Resource
    private DzDrillMapper dzDrillMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param drillId 主键
     * @return 实例对象
     */
    @Override
    public DzDrill queryById(Long drillId) {
        return this.dzDrillMapper.queryById(drillId);
    }

    @Override
    public DrillPageVo queryByIds(Long[] drillIds,String drillType, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DzDrill> dzProjects = this.dzDrillMapper.queryByIds(drillIds,drillType);
        PageInfo<DzDrill> pageInfo = new PageInfo<DzDrill>(dzProjects);
        DrillPageVo drillPageVo = new DrillPageVo();
        drillPageVo.setData(dzProjects);
        drillPageVo.setPageInfo(pageInfo);
        return drillPageVo;
    }

    /**
     * 分页查询
     *
     * @param dzDrill 筛选条件
     * @return 查询结果
     */
    @Override
    public DrillPageVo queryByPage(DzDrill dzDrill, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DzDrill> dzProjects = dzDrillMapper.queryAllByLimit(dzDrill);
        PageInfo<DzDrill> pageInfo = new PageInfo<DzDrill>(dzProjects);
        DrillPageVo drillPageVo = new DrillPageVo();
        drillPageVo.setData(dzProjects);
        drillPageVo.setPageInfo(pageInfo);
        return drillPageVo;
    }

    @Override
    public Long querySumNum(Long projectId) {
        DzDrill dzDrill = new DzDrill();
        dzDrill.setProjectId(projectId);
        return dzDrillMapper.count(dzDrill);
    }


    @Override
    public int insertBatch(@Param("entities") List<DzDrill> entities) {
        for (DzDrill entity : entities) {
            String localtionX = entity.getLocaltionX();
            String localtionY = entity.getLocaltionY();
            entity.setLocaltion("POINT ("+localtionX+" "+localtionY+")");
        }
        return dzDrillMapper.insertBatch(entities);
    }

    /**
     * 新增数据
     *
     * @param dzDrill 实例对象
     * @return 实例对象
     */
    @Override
    public DzDrill insert(DzDrill dzDrill) {
        this.dzDrillMapper.insert(dzDrill);
        return dzDrill;
    }

    /**
     * 修改数据
     *
     * @param dzDrill 实例对象
     * @return 实例对象
     */
    @Override
    public DzDrill update(DzDrill dzDrill) {
        this.dzDrillMapper.update(dzDrill);
        return this.queryById(dzDrill.getDrillId());
    }

    /**
     * 通过主键删除数据
     *
     * @param drillId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long drillId) {
        return this.dzDrillMapper.deleteById(drillId) > 0;
    }

    @Override
    public String[] queryDrillType() {
        return this.dzDrillMapper.queryDrillType();
    }
}
