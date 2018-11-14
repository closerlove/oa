package com.qf.oa.controller;

import com.qf.oa.entity.Employee;
import com.qf.oa.entity.Role;
import com.qf.oa.service.IRoleService;
import com.qf.ssm.controller.BaseController;
import com.qf.ssm.interceptor.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author cx
 * @Time 2018/11/3 9:05
 * @Version 1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/getRoleList")
    public String getRoleList(Model model, Page page){
        List<Role> list = roleService.getRoleList();
        model.addAttribute("list",list);
        return "roleList";
    }

    @RequiresPermissions("/role/addOrUpdate")
    @RequestMapping("/addOrUpdate")
    public String addRole(Role role){
       roleService.addOrUpdate(role);
       return "redirect:/role/getRoleList";
    }

    @RequiresPermissions("/role/deleteRole")
    @RequestMapping("/deleteRole")
    public String deleteRole(Integer id){
        Integer i=roleService.deleteRole(id);
        return "redirect:/role/getRoleList";
    }

    @RequestMapping("/ajxjlist")
    @ResponseBody
    public List<Role> ajxjList(){
        List<Role> list = roleService.getAjxjList();
        return list;
    }

    @RequestMapping("/ajxjlist2")
    @ResponseBody
    public List<Role> ajxjList2(Integer eid){
        List<Role> list = roleService.getAjxjList2(eid);
        return list;
    }

    /**
     * 更新角色的权限
     * @return
     */
    @RequiresPermissions("/role/updateRoles")
    @RequestMapping("/updateRoles")
    public String updateRoles(Integer eid,Integer rid[]){
        roleService.updateRoles(eid,rid);
        return "redirect:/emp/getEmpList";
    }

    @RequestMapping("/getRoleByEid")
    @ResponseBody
    public List<Employee> getRoleByEid(Integer eid){
        List<Employee> list= roleService.getRoleByEid(eid);
        return list;
    }

    @RequestMapping("/updateResc")
    @ResponseBody
    public Integer updateResc(Integer rid,Integer reids[]){
        roleService.updateResc(rid, reids);
       return 1;
    }

}
