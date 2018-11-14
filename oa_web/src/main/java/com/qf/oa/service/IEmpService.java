package com.qf.oa.service;

import com.qf.oa.entity.Employee;

import java.util.List;

/**
 * @Author cx
 * @Time 2018/11/2 11:45
 * @Version 1.0
 */
public interface IEmpService {

    List<Employee> getEmpList();

    Integer addOrUpdate(Employee employee);

    Integer deleteEmp(Integer id);

    Employee login(String email, String password);

    Employee queryByEmail(String email);

    List<Employee> queryInfo(String keyword, Integer id);

    List<Employee> queryBySexCount();
}
