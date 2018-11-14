package com.qf.oa.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author cx
 * @Time 2018/11/8 17:29
 * @Version 1.0
 */
public interface IDocService {

    public HSSFWorkbook export();

    public String UploadExcel(MultipartFile file);

    HSSFWorkbook exportdep();
}
