package com.hwp.mapper;

import com.alibaba.druid.util.StringUtils;
import com.hwp.entity.QualificationCondition;

public class QualificationSqlProvider {
    /**
     * 动态条件生成查询sql
     * @param condition
     * @return
     */

    public String selectPage(QualificationCondition condition){
        StringBuilder sb = new StringBuilder();
        sb.append("select qu.id, qu.upload_user_id, qu.type, qu.address, qu.`check`, qu.description, qu.check_user_id, qu.create_date, qu.update_date, qu.del_flag, qu.create_by," +
                "uu.name upload_user_name,cu.name check_user_name from qualification qu " +
                "left JOIN sys_user uu " +
                "on " +
                "qu.upload_user_id=uu.id " +
                "left join sys_user cu " +
                "on " +
                "qu.check_user_id=cu.id " +
                "where " +
                "qu.del_flag=0 ");
        if(!StringUtils.isEmpty(condition.getStartDate())){
            sb.append("and qu.create_date >= #{startDate} ");
        }
        if(!StringUtils.isEmpty(condition.getEndDate())){
            sb.append("and qu.create_date <= #{endDate} ");
        }
        if(condition.getType() != null){
            sb.append("and qu.type = #{type} ");
        }
        if(condition.getCheck() != null){
            sb.append("and qu.`check` = #{check} ");
        }
        return sb.toString();
    }

}
