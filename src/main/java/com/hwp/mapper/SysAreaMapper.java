package com.hwp.mapper;

import com.hwp.entity.SysArea;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysAreaMapper extends Mapper<SysArea> {

    /**
     * 根据父区域id查询所有区域
     * @param params
     * @return
     */
    @SelectProvider(type = SysAreaSqlProvider.class,method = "selectPage")
    List<SysArea> selectPage(Map<String,Object> params);
}