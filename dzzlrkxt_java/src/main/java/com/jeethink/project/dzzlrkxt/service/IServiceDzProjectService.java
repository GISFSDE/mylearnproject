package com.jeethink.project.dzzlrkxt.service;

import com.jeethink.project.dzzlrkxt.domain.DzProject;
import com.jeethink.project.dzzlrkxt.domain.vo.ProjectPageVo;

import java.util.List;

/**
 * @ClassName IServiceDzProjectService
 * @Description
 * @Author lxl
 * @Time 2023/6/19 10:36
 * @Version 1.0
 */

public interface IServiceDzProjectService {
    int deleteByPrimaryKey(Long id);

    int insert(DzProject record);

    int insertProject(DzProject record);

    DzProject selectByPrimaryKey(Long id);

    ProjectPageVo selectProjectList(DzProject project, int pageNum, int pageSize);
    List<Long> selectProjectListIds(DzProject project);

    int updateByPrimaryKeySelective(DzProject record);

    int updateByPrimaryKey(DzProject record);
}
