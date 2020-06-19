package com.hwp.mapper;

import org.springframework.util.StringUtils;

import java.util.Map;

public class ExamineSqlProvider {

    public String selectPage(Map<String,Object> map){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " +
                "  ex.id, " +
                "  ex.examine_user_id, " +
                "  ex.score, " +
                "  ex.score_info, " +
                "  ex.type, " +
                "  ex.create_date, " +
                "  ex.update_date, " +
                "  ex.del_flag, " +
                "  ex.create_by, " +
                "  su.`name` user_name, " +
                "  so.`name` office_name  " +
                "FROM " +
                "  examine ex, " +
                "  sys_user su, " +
                "  sys_office so  " +
                "WHERE " +
                "  ex.del_flag = 0  ");
        if (map.containsKey("type")&&!StringUtils.isEmpty(map.get("type"))){
            sb.append(" AND ex.type = #{type} ");
        }
        if(map.containsKey("name")&&!StringUtils.isEmpty(map.get("name"))){
            sb.append(" AND su.`name` LIKE concat( '%', #{name}, '%' )  ");
        }

        if(map.containsKey("officeId")&&!StringUtils.isEmpty(map.get("officeId"))){
            sb.append(" AND so.id = #{officeId} ");
        }

        sb.append(" AND ex.examine_user_id = su.id " +
                " AND su.office_id = so.id ");
        return sb.toString();
    }
}
