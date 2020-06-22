package com.hwp.service;


import com.github.pagehelper.PageInfo;
import com.hwp.entity.SysArea;

import java.util.Map;


public interface AreaService extends BaseService<SysArea> {
    PageInfo<SysArea> selectPage(int pageNum, int pageSize, Map<String, Object> params);


    /*@Autowired
        AppVersionMapper mapper;

        public AppVersion selectByPrimaryKey(Long id) {
            return mapper.selectByPrimaryKey(id);
        }*/
//    PageInfo selectPage(int pageNum, int size);
//    AppVersion selectByPrimaryKey(Long id);
}
