package com.hwp.service;

import com.github.pagehelper.PageInfo;
import com.hwp.entity.Statute;

import java.util.Map;

public interface StatuteService extends BaseService<Statute> {
    PageInfo<Statute> selectPage(int pageNum, int pageSize, Integer type);
}
