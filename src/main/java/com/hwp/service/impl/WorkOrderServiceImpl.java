package com.hwp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwp.entity.Detail;
import com.hwp.entity.Transfer;
import com.hwp.entity.WorkOrder;
import com.hwp.mapper.DetailMapper;
import com.hwp.mapper.TransferMapper;
import com.hwp.mapper.WorkOrderMapper;
import com.hwp.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WorkOrderServiceImpl extends BaseServiceImpl<WorkOrder> implements WorkOrderService {

    @Autowired
    WorkOrderMapper workOrderMapper;

    @Autowired
    DetailMapper detailMapper;

    @Autowired
    TransferMapper transferMapper;
    @Override
    public PageInfo<WorkOrder> selectPage(int pageNum, int size, Map<String, Object> map){
        PageHelper.startPage(pageNum,size);
        List<WorkOrder> workOrders = workOrderMapper.selectPage(map);
        return new PageInfo<WorkOrder>(workOrders);
    }
    /**
     * 1.根据工单id查工单信息、用户、公司信息
     * 2.根据工单id查询详单信息
     * 3.根据工单id查询转运记录
     * @param oid
     * @return
     */
    @Override
    public Map<String,Object> selectDetail(long oid){
        HashMap<String, Object> map = new HashMap<>();
        WorkOrder workOrder = workOrderMapper.selectByOid(oid);
        List<Detail> details = detailMapper.selectByOid(oid);
        List<Transfer> transfers = transferMapper.selectByOid(oid);
        map.put("workOrder",workOrder);
        map.put("details",details);
        map.put("transfers",transfers);
        return map;
    }

}
