package com.hwp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwp.entity.Statute;
import com.hwp.mapper.StatuteMapper;
import com.hwp.service.StatuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StatuteServiceImpl extends BaseServiceImpl<Statute> implements StatuteService {

    @Autowired
    StatuteMapper statuteMapper;

    @Override
    public PageInfo<Statute> selectPage(int pageNum, int pageSize, Integer type){
        PageHelper.startPage(pageNum,pageSize);//开启分页拦截
        Statute statute = new Statute();
        statute.setType(type);
        List<Statute> statutes = statuteMapper.select(statute);
        return new PageInfo<Statute>(statutes);
    }
}
