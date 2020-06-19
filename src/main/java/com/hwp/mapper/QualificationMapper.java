package com.hwp.mapper;

import com.hwp.entity.Qualification;
import com.hwp.entity.QualificationCondition;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface QualificationMapper extends Mapper<Qualification> {

    @SelectProvider(type = QualificationSqlProvider.class,method = "selectPage")
    List<Qualification> selectPage(QualificationCondition condition);
}