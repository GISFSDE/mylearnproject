package com.jeethink.project.dzzlrkxt.domain.vo;/**
 * @ClassName ProjectPageVo
 * @Description
 * @Author lxl
 * @Time 2023/6/25 14:30
 * @Version 1.0
 */

import com.github.pagehelper.PageInfo;
import com.jeethink.project.dzzlrkxt.domain.DzProject;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ProjectPageVo
 * @Description TODO
 * @Author lxl
 * @Date 2023/6/25 14:30
 * @Version 1.0
 */
@Data
public class ProjectPageVo {
    List<DzProject> data;
    PageInfo<DzProject> pageInfo;
}