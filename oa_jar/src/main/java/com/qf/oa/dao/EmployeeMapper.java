package com.qf.oa.dao;

import com.qf.oa.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getEmpList();

    Employee login(@Param("email") String email, @Param("password") String password);

    Employee queryByEmail(String email);

    List<Employee> queryInfo(@Param("keyword") String keyword, @Param("eid") Integer id);

    List<Employee> queryBySexCount();

    void insertExcel(Employee employee);

    void updateExcel(Employee vo);
}