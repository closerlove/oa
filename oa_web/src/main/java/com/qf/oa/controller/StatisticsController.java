package com.qf.oa.controller;

import com.qf.oa.entity.Department;
import com.qf.oa.entity.Employee;
import com.qf.oa.entity.PieChart;
import com.qf.oa.entity.Product;
import com.qf.oa.service.IDepService;
import com.qf.oa.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cx
 * @Time 2018/11/8 8:51
 * @Version 1.0
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private IDepService depService;

    @Autowired
    private IEmpService empService;

    /**
     * 根据部门查询，柱状图
     * @return
     */
    @RequestMapping("/getDepNumber")
    @ResponseBody
    public List<Product> getDepNumber(){
        //查询数据
        List<Department> depNumber = depService.getDepNumber();
        //初始化封装数据的集合
        List<Product> list=new ArrayList<>();
        //遍历查询到的数据
        for (Department d:depNumber) {

            Product product=new Product();
            //把部门名称封装到name属性
            product.setName(d.getDname());
            //先判断人数为不为null，如果为null，则吧它设置为0，如果不为0，则把他封装带对象里面
            if(d.getCount()==null || d.getCount().equals("")){
                product.setNum(0);
            }else{
                product.setNum(d.getCount());
            }
            //将对象添加到集合中
            list.add(product);
        }
        //将数据发送至前端
        return list;
    }


    @RequestMapping("/getDepNumber2")
    @ResponseBody
    public List<PieChart> getDepNumber2(){
        //查询数据
        List<Department> depNumber = depService.getDepNumber();
        //初始化封装数据的对象
        List<PieChart> list=new ArrayList<>();
        //遍历数据，将它封装至集合中
        for (Department d:depNumber) {
           PieChart p=new PieChart();
           p.setName(d.getDname());
           if(d.getCount()==null || d.getCount().equals("")){
               p.setValue(0);
           }else{
               p.setValue(d.getCount());
           }
            list.add(p);
        }
        //将数据发送至前端
        return list;
    }

    /**
     * 根据性别查询，柱状图
     * @return
     */
    @RequestMapping("/queryBySexCount")
    @ResponseBody
    public List<Product> queryBySexCount(){
        //获取数据库查询的性别与人数数据
       List<Employee> emplist= empService.queryBySexCount();
       //初始化封装数据的集合
        List<Product> products=new ArrayList<>();
        //遍历数据
        for (Employee employee:emplist) {
            //初始化对象
            Product product=new Product();
            //判断性别，并把它封装到product对象中
            if(employee.getSex()==0){
               product.setName("女");
            }else{
                product.setName("男");
            }
            //把参数封装
            product.setNum(employee.getCount());
            //添加到集合中
            products.add(product);
        }
        return products;
    }

    /**
     * 根据性别查询，饼状图
     *
     */
    @RequestMapping("/queryBySexCount2")
    @ResponseBody
    public List<PieChart> queryBySexCount2(){
        //获取数据库查询的性别与人数数据
        List<Employee> emplist= empService.queryBySexCount();
        //初始化封装数据的对象
        List<PieChart> list=new ArrayList<>();
        //遍历数据，将它封装至集合中
        for (Employee e:emplist) {
            PieChart p=new PieChart();
            if(e.getSex()==0){
                p.setName("女");
            }else{
                p.setName("男");
            }
            p.setValue(e.getCount());
            list.add(p);
        }
        //将数据发送至前端
        return list;
    }
}
