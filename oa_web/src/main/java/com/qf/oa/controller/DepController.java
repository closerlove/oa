package com.qf.oa.controller;

import com.qf.oa.entity.Department;
import com.qf.oa.service.IDepService;
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
 * @Time 2018/11/1 17:09
 * @Version 1.0
 */
@Controller
@RequestMapping("/dep")
public class DepController extends BaseController {

    @Autowired
    private IDepService depService;


    @RequestMapping("/getDepList")
    public String getDepList(Model model, Page page){
        List<Department> list = depService.queryAll();
        model.addAttribute("list",list);
        return "depList";
    }

    @RequestMapping("/listajax")
    @ResponseBody
    public List<Department> listajax(Model model){
        List<Department> list = depService.queryAll();
        return list;
    }

    @RequiresPermissions("/dep/addDep")
    @RequestMapping("/addDep")
    public String addDep(Department dep){
        depService.addDep(dep);
        return "redirect:/dep/getDepList";
    }

    @RequiresPermissions("/dep/delete")
    @RequestMapping("/delete")
    public String deleteDep(Integer did){
        depService.deleteById(did);
        return "redirect:/dep/getDepList";
    }
}
