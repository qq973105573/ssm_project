package com.hwp.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwp.entity.AppVersion;
import com.hwp.entity.Result;
import com.hwp.service.AppVersionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AppVersionServiceImpl extends  BaseServiceImpl<AppVersion> implements AppVersionService {

    /*@Autowired
    AppVersionMapper mapper;

    public AppVersion selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }*/
    @Override
    public PageInfo selectPage(int pageNum, int size){
        PageHelper.startPage(pageNum,size);//开启分页拦截
        AppVersion appVersion = new AppVersion();
        appVersion.setDelFlag("0");
        List<AppVersion> appVersions = mapper.select(appVersion);
        return new PageInfo(appVersions);
    }
}
