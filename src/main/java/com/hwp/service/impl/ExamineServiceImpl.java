package com.hwp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwp.entity.Examine;
import com.hwp.mapper.ExamineMapper;
import com.hwp.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ExamineServiceImpl extends BaseServiceImpl<Examine> implements ExamineService {
    @Autowired
    ExamineMapper examineMapper;
    @Override
    public PageInfo<Examine> selectPage(int pageNum, int size, Map<String, Object> map){
        PageHelper.startPage(pageNum,size);
        List<Examine> examines = examineMapper.selectPage(map);
        return new PageInfo<Examine>(examines);
    }
}
