package com.qf.oa.service.impl;

import com.qf.oa.dao.EmployeeMapper;
import com.qf.oa.entity.Employee;
import com.qf.oa.entity.Student;
import com.qf.oa.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author cx
 * @Time 2018/11/2 11:55
 * @Version 1.0
 */
@Service
public class EmpServiceImpl implements IEmpService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getEmpList() {
        return employeeMapper.getEmpList();
    }

    @Override
    public Integer addOrUpdate(Employee employee) {
        if(employee.getId()!=null){
            return employeeMapper.updateByPrimaryKeySelective(employee);
        }
        return employeeMapper.insert(employee);
    }

    @Override
    public Integer deleteEmp(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Employee login(String email, String password) {
        return employeeMapper.login(email,password);
    }

    @Override
    public Employee queryByEmail(String email) {
        return employeeMapper.queryByEmail(email);
    }

    @Override
    public List<Employee> queryInfo(String keyword, Integer id) {
        return employeeMapper.queryInfo(keyword,id);
    }

    @Override
    public List<Employee> queryBySexCount() {
        return employeeMapper.queryBySexCount();
    }
}
