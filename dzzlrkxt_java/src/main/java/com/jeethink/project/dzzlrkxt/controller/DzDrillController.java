package com.jeethink.project.dzzlrkxt.controller;

import com.jeethink.project.dzzlrkxt.domain.DzDrill;
import com.jeethink.project.dzzlrkxt.domain.vo.DrillPageVo;
import com.jeethink.project.dzzlrkxt.service.IServiceDzDrillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (DzDrill)表控制层
 *
 * @author makejava
 * @since 2023-06-28 15:15:00
 */
@RestController
@RequestMapping("dzDrill")
public class DzDrillController {
    /**
     * 服务对象
     */
    @Resource
    private IServiceDzDrillService dzDrillService;

    /**
     * 分页查询
     *
     * @param dzDrill 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public DrillPageVo queryByPage(DzDrill dzDrill) {
        return dzDrillService.queryByPage(dzDrill,1,10);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<DzDrill> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.dzDrillService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param dzDrill 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<DzDrill> add(DzDrill dzDrill) {
        return ResponseEntity.ok(this.dzDrillService.insert(dzDrill));
    }

    /**
     * 编辑数据
     *
     * @param dzDrill 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<DzDrill> edit(DzDrill dzDrill) {
        return ResponseEntity.ok(this.dzDrillService.update(dzDrill));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.dzDrillService.deleteById(id));
    }

}

