package com.hwp.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwp.entity.AppVersion;
import com.hwp.entity.SysArea;
import com.hwp.mapper.SysAreaMapper;
import com.hwp.service.AppVersionService;
import com.hwp.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class AreaServiceImpl extends  BaseServiceImpl<SysArea> implements AreaService {

    /*@Autowired
    AppVersionMapper mapper;

    public AppVersion selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }*/
//    @Override
//    public PageInfo selectPage(int pageNum, int size){
//        PageHelper.startPage(pageNum,size);//开启分页拦截
//        AppVersion appVersion = new AppVersion();
//        appVersion.setDelFlag("0");
//        List<AppVersion> appVersions = mapper.select(appVersion);
//        return new PageInfo(appVersions);
//    }

    @Autowired
    SysAreaMapper sysAreaMapper;

    @Override
    public PageInfo<SysArea> selectPage(int pageNum, int pageSize, Map<String, Object> params) {
        PageInfo<SysArea> pageInfo = null;
        PageHelper.startPage(pageNum, pageSize);
        //根据父区域id/父级区域名的查询
        List<SysArea> list = sysAreaMapper.selectPage(params);
        pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
