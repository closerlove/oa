package com.qf.oa.controller;

import com.qf.oa.service.IDocService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @Author cx
 * @Time 2018/11/8 17:27
 * @Version 1.0
 */
@Controller
@RequestMapping("/doc")
public class DocumentController {

    @Autowired
    private IDocService docService;


    //导出员工信息
    @RequestMapping("/export")
    public String export(HttpServletResponse response){
        HSSFWorkbook wkb = docService.export();
        //输出Excel文件
        OutputStream output= null;
        try {
            output = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = "员工信息.xls";
        response.reset();
        try {
            response.setHeader("Content-disposition", "attachment; filename="+ new String( fileName.getBytes("gb2312"), "ISO8859-1" ));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/msexcel");
        try {
            wkb.write(output);
        } catch (IOException e) {
            e.printStackTrace( );
        }finally {
            try {
                 output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       return "redirect:/emp/getEmpList";
    }


    //导入员工信息
    @RequestMapping("/upload")
    //接收文件表单
    public String upload(@RequestParam("upfile") MultipartFile file){
        //判断文件是否正确
        if (file.isEmpty()) {
            try {
                throw new Exception("文件不存在！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //调用serice解析文件
         docService.UploadExcel(file);
        return "redirect:/emp/getEmpList";

    }

    //导出部门信息
    @RequestMapping("/exportdep")
    public String exportDep(HttpServletResponse response){
        HSSFWorkbook wkb = docService.exportdep();
        //输出Excel文件
        OutputStream output= null;
        try {
            output = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = "部门信息.xls";
        response.reset();
        try {
            response.setHeader("Content-disposition", "attachment; filename="+ new String( fileName.getBytes("gb2312"), "ISO8859-1" ));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/msexcel");
        try {
            wkb.write(output);
        } catch (IOException c) {
            c.printStackTrace();
        }finally {
            try {
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/emp/getEmpList";
    }
}
