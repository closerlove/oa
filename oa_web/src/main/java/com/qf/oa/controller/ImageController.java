package com.qf.oa.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @Author cx
 * @Time 2018/11/2 16:48
 * @Version 1.0
 */
@Controller
@RequestMapping("/img")
public class ImageController {

    @Value("${uploadpath}")
    private String path;


    @RequestMapping("/uploadImg")
    @ResponseBody
    public String uploadImg(MultipartFile file){
        InputStream is=null;
        OutputStream os=null;
        try {
            //输入流
            is = file.getInputStream();
            //文件路径+文件名
            String pathLoad=path+ UUID.randomUUID().toString();
            os=new FileOutputStream(pathLoad);
            //上传文件
            IOUtils.copy(is,os);
            //返回一个json格式的文件路径
            return "{\"uploadpath\":\"" + pathLoad + "\"}";
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    @RequestMapping("/getImg")
    public void getImg(String path, HttpServletResponse response){
        InputStream in = null;
        OutputStream out = null;
        try {
            //用户需要显示的图片
            in = new FileInputStream(path);

            out = response.getOutputStream();

            IOUtils.copy(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
