package com.qf.oa.service.impl;

import com.qf.oa.dao.RescMapper;
import com.qf.oa.entity.Resc;
import com.qf.oa.service.IRescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Author cx
 * @Time 2018/11/3 14:16
 * @Version 1.0
 */
@Service
public class RescServiceImpl implements IRescService {

    @Autowired
    private RescMapper rescMapper;

    @Override
    public List<Resc> getRescList() {
        return rescMapper.queryAll();
    }

    @Override
    public Integer addOrUpdate(Resc resc) {
        if (resc.getId() != null) {
            return rescMapper.updateByPrimaryKeySelective(resc);
        } else {
            return rescMapper.insert(resc);
        }
    }

    @Override
    public Integer delete(Integer id) {
        return rescMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Resc> queryByRid(Integer rid) {
        return rescMapper.queryByRid(rid);
    }
}
