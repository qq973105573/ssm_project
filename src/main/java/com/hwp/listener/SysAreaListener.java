package com.hwp.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hwp.entity.SysArea;
import com.hwp.mapper.SysAreaMapper;

import java.util.ArrayList;
import java.util.List;

public class SysAreaListener extends AnalysisEventListener<SysArea> {
    private SysAreaMapper sysAreaMapper;
    private List<SysArea> sysAreas = new ArrayList<>();//创建缓存集合
    public SysAreaListener(SysAreaMapper sysAreaMapper){
        this.sysAreaMapper = sysAreaMapper;
    }

    /**
     * 每当读取到一行记录，就会封装成一个java对象，并且传递过来调用invoke方法
     * @param sysArea 读取到的一行excel记录自动封装出来的java对象
     * @param analysisContext easyExcel容器对象
     */
    @Override
    public void invoke(SysArea sysArea, AnalysisContext analysisContext){
        sysAreas.add(sysArea);
        if(sysAreas.size()==10){
            sysAreaMapper.insertBatch(sysAreas);
            sysAreas.clear();
        }
    }

    /**
     * 当所有行都读取完成之后，会自动调用当前方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if(sysAreas.size()>0){
            sysAreaMapper.insertBatch(sysAreas);
            sysAreas.clear();
        }

    }
}
