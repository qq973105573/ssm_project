package com.hwp.service;


import com.github.pagehelper.PageInfo;
import com.hwp.entity.SysArea;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;


public interface AreaService extends BaseService<SysArea> {
    PageInfo<SysArea> selectPage(int pageNum, int pageSize, Map<String, Object> params);
    void download(OutputStream outputStream);

    void upload(InputStream inputStream);


    /*@Autowired
        AppVersionMapper mapper;

        public AppVersion selectByPrimaryKey(Long id) {
            return mapper.selectByPrimaryKey(id);
        }*/
//    PageInfo selectPage(int pageNum, int size);
//    AppVersion selectByPrimaryKey(Long id);
}
