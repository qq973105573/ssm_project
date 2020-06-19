package com.hwp.mapper;

import com.hwp.entity.WorkOrder;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface WorkOrderMapper extends Mapper<WorkOrder> {

    @SelectProvider(type=WorkOrderSqlProvider.class,method="selectPage")
    List<WorkOrder> selectPage(Map<String, Object> map);
    @Select("SELECT   " +
            "      wo.id,   " +
            "      wo.`code`,   " +
            "      wo.create_user_id,   " +
            "      wo.transport_user_id,   " +
            "      wo.recipient_user_id,   " +
            "      wo.`status`,   " +
            "      wo.create_date,   " +
            "      wo.update_date,   " +
            "      wo.del_flag,   " +
            "      wo.create_by,   " +
            "      cu.NAME create_user_name,   " +
            "      cu.phone create_user_phone,   " +
            "      cof.NAME create_office_name,   " +
            "      tu.NAME transport_user_name,   " +
            "      tu.phone transport_user_phone,   " +
            "      tof.NAME transport_office_name,   " +
            "      ru.NAME recipient_user_name,   " +
            "      ru.phone recipient_user_phone,   " +
            "      rof.NAME recipient_office_name    " +
            "FROM   " +
            "      work_order wo   " +
            "      LEFT JOIN sys_user cu ON wo.create_user_id = cu.id   " +
            "      LEFT JOIN sys_user tu ON wo.transport_user_id = tu.id   " +
            "      LEFT JOIN sys_user ru ON wo.recipient_user_id = ru.id   " +
            "      LEFT JOIN sys_office cof ON cu.office_id = cof.id   " +
            "      LEFT JOIN sys_office tof ON tu.office_id = tof.id   " +
            "      LEFT JOIN sys_office rof ON ru.office_id = rof.id    " +
            "WHERE   " +
            "      wo.del_flag = 0    " +
            "      AND wo.id = #{id}    ")
    WorkOrder selectByOid(long id);
}