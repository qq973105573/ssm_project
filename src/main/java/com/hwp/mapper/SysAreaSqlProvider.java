package com.hwp.mapper;

import com.hwp.entity.SysArea;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.List;
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
                WHERE("sub.del_flag=0");
                WHERE("sub.parent_id=parent.id");
                //根据传入父id查询所有子级区域
                if(params.containsKey("aid")&& !StringUtils.isEmpty(params.get("aid"))){
                    WHERE("sub.parent_ids LIKE concat( '%,', #{aid}, ',%' ) ");
                    //根据传入区域名 模糊查询 区域
                }else if(params.containsKey("areaName")&&!StringUtils.isEmpty(params.get("areaName"))){
                    WHERE("parent.name like concat('%',#{areaName},'%')");
                }
            }
        }.toString();
    }

    public String insertBatch(@Param("sysAreas") List<SysArea> sysAreas){
        return new SQL(){
            {
                INSERT_INTO("sys_area");
                INTO_COLUMNS("`parent_id`, `parent_ids`, `code`, `name`, `type`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `icon`");
                for (int i = 0; i < sysAreas.size(); i++) {
                    INTO_VALUES("#{sysAreas["+i+"].parentId},"+
                            "#{sysAreas["+i+"].parentIds},"+
                            "#{sysAreas["+i+"].code},"+
                            "#{sysAreas["+i+"].name},"+
                            "#{sysAreas["+i+"].type},"+
                            "#{sysAreas["+i+"].createBy},"+
                            "#{sysAreas["+i+"].createDate},"+
                            "#{sysAreas["+i+"].updateBy},"+
                            "#{sysAreas["+i+"].updateDate},"+
                            "#{sysAreas["+i+"].remarks},"+
                            "#{sysAreas["+i+"].delFlag},"+
                            "#{sysAreas["+i+"].icon}");
                    ADD_ROW();//动态处理拼接一个插入记录
                }
            }
        }.toString();
    }
}
