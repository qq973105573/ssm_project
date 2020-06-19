package com.hwp.controller;

import com.hwp.entity.Result;
import com.hwp.service.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manager/office")
public class SysOfficeController {
    @Autowired
    SysOfficeService sysOfficeService;
    @RequestMapping("list")
    public Result list(){
        return new Result(true,"查询成功", sysOfficeService.selectAll());
    }

}
