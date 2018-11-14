package com.qf.oa.service.impl;

import com.qf.oa.dao.RoleMapper;
import com.qf.oa.entity.Employee;
import com.qf.oa.entity.Role;
import com.qf.oa.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author cx
 * @Time 2018/11/3 9:08
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoleList() {
        return roleMapper.queryAllRole();
    }

    @Override
    public Integer deleteRole(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer addOrUpdate(Role role) {
        if(role.getId()!=null){
            return roleMapper.updateByPrimaryKeySelective(role);
        }
        return roleMapper.insert(role);
    }

    @Override
    public List<Role> getAjxjList() {
        return roleMapper.getRoleAjxjList();
    }

    @Override
    public Integer updateRoles(Integer eid, Integer[] rid) {
        //先清空用户的角色
        roleMapper.deleteEmpAndRolesInfo(eid);
        //添加角色
        roleMapper.insertEmpAndRolesInfo(eid,rid);
        return null;
    }

    @Override
    public List<Employee> getRoleByEid(Integer eid) {
        return roleMapper.getRoleByEid(eid);
    }

    @Override
    public List<Role> getAjxjList2(Integer eid) {
        return roleMapper.getAjxjList2(eid);
    }

    @Override
    public Integer updateResc(Integer rid, Integer[] reids) {
        //先删除后添加
        roleMapper.deleteResc(rid);

        roleMapper.insertRescRidInfo(rid,reids);

        return 1;
    }
}
