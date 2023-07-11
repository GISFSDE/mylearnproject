package com.jeethink.project.dzzlrkxt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeethink.project.dzzlrkxt.domain.DzProject;
import com.jeethink.project.dzzlrkxt.domain.vo.ProjectPageVo;
import com.jeethink.project.dzzlrkxt.mapper.DzProjectMapper;
import com.jeethink.project.dzzlrkxt.service.IServiceDzProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lxl
 */
@Service
public class IServiceDzProjectServiceImp implements IServiceDzProjectService {

    @Resource
    DzProjectMapper dzProjectMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dzProjectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DzProject dzProject) {
        return 0;
    }

    @Override
    public int insertProject(DzProject dzProject) {
        return dzProjectMapper.insertSelective(dzProject);
    }

    @Override
    public DzProject selectByPrimaryKey(Long id) {
        return dzProjectMapper.selectByPrimaryKey(id);
    }



    @Override
    public ProjectPageVo selectProjectList(DzProject project, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DzProject> dzProjects = dzProjectMapper.selectProjectList(project);
        PageInfo<DzProject> pageInfo = new PageInfo<DzProject>(dzProjects);
        ProjectPageVo projectPageVo = new ProjectPageVo();
        projectPageVo.setData(dzProjects);
        projectPageVo.setPageInfo(pageInfo);
        return projectPageVo;
    }

    @Override
    public List<Long> selectProjectListIds(DzProject project) {
        List<Long> ids = new ArrayList<>();
        List<DzProject> dzProjects = dzProjectMapper.selectProjectList(project);
        for (DzProject dzProject : dzProjects) {
            ids.add(dzProject.getProjectId());
        }

        return ids;
    }

    @Override
    public int updateByPrimaryKeySelective(DzProject dzProject) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(DzProject dzProject) {
        return 0;
    }
}