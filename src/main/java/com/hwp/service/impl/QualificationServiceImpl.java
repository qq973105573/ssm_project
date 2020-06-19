package com.hwp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwp.entity.Qualification;
import com.hwp.entity.QualificationCondition;
import com.hwp.mapper.QualificationMapper;
import com.hwp.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QualificationServiceImpl extends BaseServiceImpl<Qualification> implements QualificationService {

    @Autowired
    QualificationMapper qualificationMapper;
    @Override
    public PageInfo selectPage(int pageNum, int size, QualificationCondition condition){
        PageHelper.startPage(pageNum,size);
        List<Qualification> qualifications = qualificationMapper.selectPage(condition);
        return new PageInfo(qualifications);
    }
}
