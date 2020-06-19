package com.hwp.service.impl;

import com.hwp.entity.SysOffice;
import com.hwp.service.SysOfficeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysOfficeServiceImpl extends BaseServiceImpl<SysOffice> implements SysOfficeService {
}
