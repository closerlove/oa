package com.qf.oa.dao;

import com.qf.oa.entity.Department;

import java.util.List;

public interface DepartmentMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> queryAll();

    public int addDep(Department dep);

    Integer deleteById(Integer did);

    List<Department> getDepNumber();
}