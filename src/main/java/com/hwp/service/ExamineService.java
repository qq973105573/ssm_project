package com.hwp.service;

import com.github.pagehelper.PageInfo;
import com.hwp.entity.Examine;

import java.util.Map;

public interface ExamineService extends BaseService<Examine>{


    PageInfo<Examine> selectPage(int pageNum, int size, Map<String, Object> map);
}
