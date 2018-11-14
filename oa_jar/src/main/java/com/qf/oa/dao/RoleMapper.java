package com.qf.oa.dao;

import com.qf.oa.entity.Employee;
import com.qf.oa.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Role record);

    List<Role> queryAllRole();

    List<Role> getRoleAjxjList();

    Integer deleteEmpAndRolesInfo(Integer eid);

    Integer insertEmpAndRolesInfo(@Param("eid") Integer eid, @Param("rid") Integer[] rid);

    List<Employee> getRoleByEid(Integer eid);

    List<Role> getAjxjList2(Integer eid);

    Integer deleteResc(Integer rid);

    Integer insertRescRidInfo(@Param("rid") Integer rid, @Param("reids") Integer[] reids);
}