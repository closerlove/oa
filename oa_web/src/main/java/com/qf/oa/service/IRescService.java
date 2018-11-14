package com.qf.oa.service;

import com.qf.oa.entity.Resc;

import java.util.List;

/*
 * @Author cx
 * @Time 2018/11/3 14:15
 * @Version 1.0
 */

public interface IRescService {

    List<Resc> getRescList();

    Integer addOrUpdate(Resc resc);

    Integer delete(Integer id);

    List<Resc> queryByRid(Integer rid);
}
