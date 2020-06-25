package com.hwp.service.impl;

import com.hwp.entity.SysUser;
import com.hwp.mapper.SysUserMapper;
import com.hwp.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
}
