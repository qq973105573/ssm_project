package com.hwp.mapper;

import com.hwp.entity.Examine;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ExamineMapper extends Mapper<Examine> {
    /**
     *
     * @param map  {officeId:'',
     *                name:'',
     *               type:''}
     * @return
     */

    @SelectProvider(type = ExamineSqlProvider.class,method = "selectPage")
    List<Examine> selectPage(Map<String,Object> map);
}