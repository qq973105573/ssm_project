package com.hwp.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

public class SysAreaSqlProvider {

    public String selectPage(Map<String,Object> params){
        return new SQL(){
            {
                SELECT("sub.id, " +
                        "   sub.parent_id, " +
                        "   sub.parent_ids, " +
                        "   sub.`code`, " +
                        "   sub.`name`, " +
                        "   sub.type, " +
                        "   sub.create_by, " +
                        "   sub.create_date, " +
                        "   sub.update_by, " +
                        "   sub.update_date, " +
                        "   sub.remarks, " +
                        "   sub.del_flag, " +
                        "   sub.icon, " +
                        "   parent.NAME parent_name");
                FROM(" sys_area sub,sys_area parent");
                WHERE("sub.parent_ids like CONCAT('%,',parent.id,',%')");
                WHERE("sub.parent_id=parent.id");
                if(params.containsKey("aid")&& !StringUtils.isEmpty(params.get("aid"))){
                    WHERE("parent.id=#{aid}");
                }else if(params.containsKey("areaName")&&!StringUtils.isEmpty(params.get("areaName"))){
                    WHERE("parent.name like concat('%',#{areaName},'%')");
                }
            }
        }.toString();
    }
}
