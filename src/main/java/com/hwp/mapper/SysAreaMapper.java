package com.hwp.mapper;

import com.hwp.entity.SysArea;
import org.apache.ibatis.annotations.*;
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

    /**
     * 根据父节点id和父节点的parent_ids来更新子节点的parent_ids
     * @param sysArea
     * @return
     */
    @Update("update sys_area set parent_ids=REPLACE(parent_ids,#{oldParentIds},#{parentIds}) " +
            "where parent_ids like CONCAT('%,',#{id},',%')")
    int updateParentIds(SysArea sysArea);

    @InsertProvider(type = SysAreaSqlProvider.class,method = "insertBatch")
    int insertBatch(@Param("sysAreas") List<SysArea> sysAreas);
}