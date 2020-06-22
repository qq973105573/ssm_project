package com.hwp.controller;

import com.github.pagehelper.PageInfo;
import com.hwp.entity.Qualification;
import com.hwp.entity.QualificationCondition;
import com.hwp.entity.Result;
import com.hwp.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@RestController
@RequestMapping("manager/qualification")
public class QualificationController {

    @Autowired
    QualificationService qualificationService;


    /**
     * 跳转到index页面
     * 默认走ajax响应，如果要走视图解析器讲返回值设置为ModelAndView
     *
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/qualification/index");
    }



    @RequestMapping("selectPage/{pageNum}/{size}")
    public Result selectPage(@PathVariable("pageNum")int pageNum, @PathVariable("size")int size, @RequestBody QualificationCondition condition){
        PageInfo pageInfo = qualificationService.selectPage(pageNum,size,condition);
        return new Result(true,null,pageInfo);
    }
    /**
     * 跳转到update页
     * @return
     */
    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(){
        return new ModelAndView("/qualification/update");  // 走视图解析器
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody Qualification qualification){
        qualification.setUpdateDate(new Date());//设置更新时间
        qualificationService.updateByPrimaryKeySelective(qualification);
        return new Result(true,"更新成功",null);
    }

}
