package com.qf.oa.service.impl;


import com.qf.oa.dao.DepartmentMapper;
import com.qf.oa.entity.Department;
import com.qf.oa.service.IDepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author cx
 * @Time 2018/11/1 17:48
 * @Version 1.0
 */
@Service
public class DepServiceImpl implements IDepService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> queryAll() {
        return departmentMapper.queryAll();
    }

    @Override
    public int addDep(Department dep) {
        return departmentMapper.addDep(dep);
    }

    @Override
    public Integer deleteById(Integer did) {
        return departmentMapper.deleteById(did);
    }

    @Override
    public List<Department> getDepNumber() {
        return departmentMapper.getDepNumber();
    }
}
