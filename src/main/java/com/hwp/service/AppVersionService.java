package com.hwp.service;

import com.github.pagehelper.PageInfo;
import com.hwp.entity.AppVersion;
import com.hwp.entity.Result;

public interface AppVersionService extends BaseService<AppVersion> {
    /*@Autowired
        AppVersionMapper mapper;

        public AppVersion selectByPrimaryKey(Long id) {
            return mapper.selectByPrimaryKey(id);
        }*/
    PageInfo selectPage(int pageNum, int size);



//    AppVersion selectByPrimaryKey(Long id);
}
