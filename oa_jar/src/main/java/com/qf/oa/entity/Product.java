package com.qf.oa.entity;

/**
 * @Author cx
 * @Time 2018/11/8 8:49
 * @Version 1.0
 */

/**
 * 用来 封装图表所需要的数据
 */
public class Product {

    private String name;  //类别名称

    private int num; //数量

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
