package com.hwp.service.impl;


import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwp.entity.SysArea;
import com.hwp.listener.SysAreaListener;
import com.hwp.mapper.SysAreaMapper;
import com.hwp.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
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

    @Override
    public int updateByPrimaryKeySelective(SysArea sysArea){
        int result = 0;
        try {
            result = mapper.updateByPrimaryKeySelective(sysArea);

            String parentIds = sysArea.getParentIds();
            String oldParentIds = sysArea.getOldParentIds();
            if (!StringUtils.isEmpty(parentIds) && !StringUtils.isEmpty(oldParentIds)) {
                if (!parentIds.equals(oldParentIds)) {//需要更新子级区域的parentIds
                    result += sysAreaMapper.updateParentIds(sysArea);
                }
            }
        }catch (Exception e){
            throw new RuntimeException("更新失败，请重试");
        }
        return result;
    }

    /**
     *
     * @param outputStream   当前客户端的响应流
     */
    @Override
    public void download(OutputStream outputStream){
        EasyExcel.write(outputStream,SysArea.class).sheet().doWrite(sysAreaMapper.selectAll());
    }

    /**
     *
     * @param inputStream    当前客户端上传的文件流对象
     */
    @Override
    public void upload(InputStream inputStream){
        EasyExcel.read(inputStream,SysArea.class,new SysAreaListener(sysAreaMapper))
                .sheet()
                .doRead();
    }
}
