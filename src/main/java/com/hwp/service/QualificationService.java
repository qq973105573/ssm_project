package com.hwp.service;

import com.github.pagehelper.PageInfo;
import com.hwp.entity.Qualification;
import com.hwp.entity.QualificationCondition;

public interface QualificationService extends BaseService<Qualification> {


    PageInfo selectPage(int pageNum, int size, QualificationCondition condition);

}
