package com.hwp.service;

import com.github.pagehelper.PageInfo;
import com.hwp.entity.WorkOrder;

import java.util.Map;

public interface WorkOrderService extends BaseService<WorkOrder> {



    PageInfo selectPage(int pageNum, int size, Map<String, Object> map);


    Map<String,Object> selectDetail(long oid);
}
