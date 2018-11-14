package com.qf.oa.service;

import com.qf.oa.entity.Employee;
import com.qf.oa.entity.Role;

import java.util.List;

/**
 * @Author cx
 * @Time 2018/11/3 9:07
 * @Version 1.0
 */
public interface IRoleService {

    List<Role> getRoleList();

    Integer deleteRole(Integer id);

    Integer addOrUpdate(Role role);

    List<Role> getAjxjList();

    Integer updateRoles(Integer eid, Integer[] rid);

    List<Employee> getRoleByEid(Integer eid);

    List<Role> getAjxjList2(Integer eid);

    Integer updateResc(Integer rid, Integer[] reids);
}
