package com.qf.oa.service;

import com.qf.oa.entity.Department;

import java.util.List;

/**
 * @Author cx
 * @Time 2018/11/1 17:47
 * @Version 1.0
 */
public interface IDepService {

   public List<Department> queryAll();

   public int addDep(Department dep);

   public Integer deleteById(Integer did);

   public List<Department> getDepNumber();
}
