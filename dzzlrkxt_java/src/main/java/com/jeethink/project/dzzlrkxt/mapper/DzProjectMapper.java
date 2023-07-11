package com.jeethink.project.dzzlrkxt.mapper;

import com.jeethink.project.dzzlrkxt.domain.DzProject;

import java.util.List;

/**
* @author PC
* @description 针对表【dz_project】的数据库操作Mapper
* @createDate 2023-06-19 10:32:51
* @Entity com.jeethink.project.dzzlrkxt.domain.DzProject
*/
public interface DzProjectMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DzProject project);

    int insertSelective(DzProject project);

    DzProject selectByPrimaryKey(Long id);
    List<DzProject> selectProjectList(DzProject project);

    int updateByPrimaryKeySelective(DzProject project);

    int updateByPrimaryKey(DzProject project);

}
