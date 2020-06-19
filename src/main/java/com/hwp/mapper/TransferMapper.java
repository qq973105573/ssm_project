package com.hwp.mapper;

import com.hwp.entity.Transfer;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TransferMapper extends Mapper<Transfer> {
    /**
     * 根据id查找id查找详单
     * @param oid id
     * @return
     */

    @Select("SELECT  " +
            "    tr.id,  " +
            "    tr.content,  " +
            "    tr.oprate_user_id,  " +
            "    tr.created_at,  " +
            "    tr.work_order_id,  " +
            "    tr.create_date,  " +
            "    tr.update_date,  " +
            "    tr.del_flag,  " +
            "    tr.create_by,  " +
            "    su.NAME,   " +
            "    su.phone   " +
            "FROM  " +
            "    transfer tr,  " +
            "    sys_user su   " +
            "WHERE  " +
            "    tr.del_flag = 0   " +
            "    AND tr.work_order_id = #{oid}   " +
            "    AND tr.oprate_user_id = su.id "+
            "ORDER BY " +
            "    tr.create_date DESC ")
    List<Transfer> selectByOid(long oid);
}