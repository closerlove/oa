package com.qf.oa.controller;

import com.qf.oa.entity.Resc;
import com.qf.oa.service.IRescService;
import com.qf.ssm.controller.BaseController;
import com.qf.ssm.interceptor.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 * @Author cx
 * @Time 2018/11/3 14:17
 * @Version 1.0
 */

@Controller
@RequestMapping("/resc")
public class RescController extends BaseController {

    @Autowired
    private IRescService rescService;

    @RequestMapping("/getRescList")
    public String getRescList(Model model, Page page){
        List<Resc> list=rescService.getRescList();
        model.addAttribute("list",list);
        return "rescList";
    }


    @RequiresPermissions("/resc/addOrUpdate")
    @RequestMapping("/addOrUpdate")
    public String addOrUpdate(Resc resc){
        Integer i=rescService.addOrUpdate(resc);
        return "redirect:/resc/getRescList";
    }

    @RequiresPermissions("/resc/delete")
    @RequestMapping("/delete")
    public String deleteResc(Integer id){
        Integer i=rescService.delete(id);
        return "redirect:/resc/getRescList";
    }


    @RequestMapping("/listajax")
    @ResponseBody
    public List<Resc> listajax(){
        List<Resc> list=rescService.getRescList();
        return list;
    }

    @RequestMapping("/listajax2")
    @ResponseBody
    public List<Resc> listajax2(Integer rid){
        List<Resc> list=rescService.queryByRid(rid);
        return list;
    }


}
