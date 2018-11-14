package com.qf.oa.service.impl;

import com.qf.oa.Utils.ExcelUtils;
import com.qf.oa.dao.DepartmentMapper;
import com.qf.oa.dao.EmployeeMapper;
import com.qf.oa.entity.Department;
import com.qf.oa.entity.Employee;
import com.qf.oa.service.IDocService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author cx
 * @Time 2018/11/8 17:30
 * @Version 1.0
 */
@Service
public class DocServiceImpl implements IDocService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public HSSFWorkbook export() {
        List<Employee> empList = employeeMapper.getEmpList();
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wkb = new HSSFWorkbook();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet();


        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        HSSFCreationHelper creationHelper = hssfWorkbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        //建立新的sheet对象（excel的表单）
        sheet=wkb.createSheet("员工信息表");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("员工信息一览表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,6));
        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("编号");
        row2.createCell(1).setCellValue("姓名");
        row2.createCell(2).setCellValue("邮箱");
        row2.createCell(3).setCellValue("性别");
        row2.createCell(4).setCellValue("生日");
        row2.createCell(5).setCellValue("所属部门");

        int i=2;
        //遍历查询到的员工信息，并将其添加进单元格
        for(Employee e:empList){
            HSSFRow row3=sheet.createRow(i);
            row3.createCell(0).setCellValue(e.getId());
            row3.createCell(1).setCellValue(e.getName());
            row3.createCell(2).setCellValue(e.getEmail());
            row3.createCell(3).setCellValue(e.getSex()==0?"女":"男");
            row3.createCell(4).setCellValue(e.getBirthday());
            row3.createCell(5).setCellValue(e.getDname());
            i++;
        }
        return wkb;
    }


    @Override
    public String UploadExcel(MultipartFile file) {

        InputStream in = null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<Object>> listob = null;
        try {
            listob = new ExcelUtils().getBankListByExcel(in, file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }


        //初始化时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //定义一个接收字符的变量
        String sex = null;
        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            Employee vo = new Employee();
            Employee j = null;

            try {
                //查询id，判断该id信息是否在数据库存在，若查到则存到该变量里面
                j = employeeMapper.selectByPrimaryKey(Integer.valueOf(String.valueOf(lo.get(0))));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                System.out.println("没有新增");
            }
            //读取id
            vo.setId(Integer.valueOf(String.valueOf(lo.get(0))));
            //读取用户名
            vo.setName(String.valueOf(lo.get(1)));
            //读取密码
            vo.setPassword(String.valueOf(lo.get(2)));
            //读取email
            vo.setEmail(String.valueOf(String.valueOf(lo.get(3))));
            //获得性别，通过if将sex转化为int类型
            sex = String.valueOf(lo.get(4));
            if (sex.equals("男")) {
                vo.setSex(1);
            } else if (sex.equals("女")) {
                vo.setSex(0);
            }
                try {
                    //设置时间  转化格式
                    vo.setBirthday(sdf.parse(String.valueOf(lo.get(5))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //设置部门名称
                vo.setDname(String.valueOf(String.valueOf(lo.get(6))));

                if (j == null) {//如果i为空，则表示该用户数据库中没有，则新增
                    employeeMapper.insertExcel(vo);
                } else {
                    //i不为空，该用户已存在数据库，更新用户信息
                    employeeMapper.updateExcel(vo);
                }
            }
            return "文件导入成功！";
        }

    @Override
    public HSSFWorkbook exportdep() {
        List<Department> departments = departmentMapper.queryAll();

        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wkb = new HSSFWorkbook();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet();
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        HSSFCreationHelper creationHelper = hssfWorkbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        //建立新的sheet对象（excel的表单）
        sheet=wkb.createSheet("部门信息表");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("部门信息一览表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("编号");
        row2.createCell(1).setCellValue("部门名称");
        row2.createCell(2).setCellValue("父部门名称");
        row2.createCell(3).setCellValue("部门描述");
        row2.createCell(4).setCellValue("成立时间");

        int i=2;
        //遍历查询到的员工信息，并将其添加进单元格
        for(Department d:departments){
            HSSFRow row3=sheet.createRow(i);
            row3.createCell(0).setCellValue(d.getId());
            row3.createCell(1).setCellValue(d.getDname());
            row3.createCell(2).setCellValue(d.getPname());
            row3.createCell(3).setCellValue(d.getDinfo());
            row3.createCell(4).setCellValue(d.getCreatetime());

            i++;
        }
        return wkb;
    }


}
