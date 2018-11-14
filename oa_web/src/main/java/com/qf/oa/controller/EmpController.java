package com.qf.oa.controller;

import com.qf.oa.entity.Employee;
import com.qf.oa.entity.ResultInfo;
import com.qf.oa.service.IEmpService;
import com.qf.ssm.controller.BaseController;
import com.qf.ssm.interceptor.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cx
 * @Time 2018/11/2 11:44
 * @Version 1.0
 */
@Controller
@RequestMapping("/emp")
public class EmpController extends BaseController {

    @Autowired
    private IEmpService empService;


    @RequestMapping("/getEmpList")
    public String getEmpList(Model model, Page page){
        List<Employee> empList = empService.getEmpList();
        model.addAttribute("emp",empList);
        return "empList";
    }

    @RequiresPermissions("/emp/addOrUpdate")
    @RequestMapping("/addOrUpdate")
    public String addOrUpdate(Employee employee){
        empService.addOrUpdate(employee);
        return "redirect:/emp/getEmpList";
    }

    @RequiresPermissions("/emp/delete")
    @RequestMapping("/delete")
    public String deleteEmp(Integer id){
        System.out.println(id);
        empService.deleteEmp(id);
        return "redirect:/emp/getEmpList";
    }

    /**
     * 查询职工的信息
     * @return
     */
    @RequestMapping("/queryinfo")
    @ResponseBody
    public ResultInfo queryInfo(String keyword, @SessionAttribute("user") Employee employee){
        List<Employee> list= empService.queryInfo(keyword,employee.getId());
        ResultInfo resultInfo=new ResultInfo();
        List<ResultInfo.Info> infos=new ArrayList<>();
        for(Employee e:list){
            ResultInfo.Info info=new ResultInfo.Info();
            info.setValue(e.getName()+"("+e.getEmail()+")");//显示在页面上的内容 姓名+邮箱
            info.setData(e.getEmail());//传输的内容
            infos.add(info);
        }
        resultInfo.setSuggestions(infos);
        return resultInfo;
    }
}
