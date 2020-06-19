package com.hwp.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

public class WorkOrderSqlProvider {

    /**
     * 用sql语句构建器创建动态sql
     * @param map
     * @return
     */

    public String selectPage(final Map<String, Object> map){
        return new SQL(){
            {
                SELECT("wo.id, " +
                        "    wo.`code`, " +
                        "    wo.create_user_id, " +
                        "    wo.transport_user_id, " +
                        "    wo.recipient_user_id, " +
                        "    wo.`status`, " +
                        "    wo.create_date, " +
                        "    wo.update_date, " +
                        "    wo.del_flag, " +
                        "    wo.create_by, " +
                        "    cu.`name` create_user_name, " +
                        "    scoff.NAME create_office_name, " +
                        "    tu.NAME transport_user_name, " +
                        "    ru.NAME recipient_user_name ");
                FROM("work_order wo");
                LEFT_OUTER_JOIN("sys_user cu ON wo.create_user_id = cu.id");
                LEFT_OUTER_JOIN("sys_user tu ON wo.transport_user_id = tu.id");
                LEFT_OUTER_JOIN("sys_user ru ON wo.recipient_user_id = ru.id");
                LEFT_OUTER_JOIN("sys_office scoff ON cu.office_id = scoff.id");
                LEFT_OUTER_JOIN("sys_office stoff ON tu.office_id = stoff.id");
                LEFT_OUTER_JOIN("sys_office sroff ON ru.office_id = sroff.id");
                WHERE("wo.del_flag=0");
                if(map.containsKey("officeId")&& !StringUtils.isEmpty(map.get("officeId"))){
                    WHERE("(scoff.id=#{officeId} or stoff.id=#{officeId} or sroff.id=#{officeId})");
                }
                if(map.containsKey("status")&& !StringUtils.isEmpty(map.get("status"))){
                    WHERE("wo.status=#{status}");
                }
                if(map.containsKey("startDate")&& !StringUtils.isEmpty(map.get("startDate"))){
                    WHERE("wo.create_date>=#{startDate}");
                }
                if(map.containsKey("endDate")&& !StringUtils.isEmpty(map.get("endDate"))){
                    WHERE("wo.create_date>=#{endDate}");
                }
            }
        }.toString();
    }
}
