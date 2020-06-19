package com.hwp.mapper;

import com.hwp.entity.Detail;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DetailMapper extends Mapper<Detail> {

    /**
     * 根据工单id查找详单
     * @param oid id
     * @return
     */
    @Select("SELECT    " +
            "    dt.id,    " +
            "    dt.work_order_id,    " +
            "    dt.waste_type_id,    " +
            "    dt.waste_id,      " +
            "    dt.component,      " +
            "    dt.weight,      " +
            "    dt.morphological,      " +
            "    dt.packaging,      " +
            "    dt.plate_number,      " +
            "    dt.create_date,      " +
            "    dt.update_date,      " +
            "    dt.del_flag,      " +
            "    dt.create_by,      " +
            "    wt.CODE waste_type_code,      " +
            "    wt.NAME waste_type_name,      " +
            "    wa.CODE waste_code       " +
            "FROM      " +
            "    detail dt,      " +
            "    waste_type wt,      " +
            "    waste wa       " +
            "WHERE      " +
            "    dt.del_flag = 0       " +
            "    AND dt.work_order_id = #{oid}       " +
            "    AND dt.waste_type_id = wt.id       " +
            "    AND dt.waste_id = wa.id")
    List<Detail> selectByOid(long oid);
}