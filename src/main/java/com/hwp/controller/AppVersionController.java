package com.hwp.controller;

import com.github.pagehelper.PageInfo;
import com.hwp.entity.AppVersion;
import com.hwp.entity.Result;
import com.hwp.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;

//http://localhost:8080/ssm/manager/app/index
//必须起应用名，否则会导致manager请求跑到tomcat自带的manager项目上
@RestController
@RequestMapping("manager/app")
public class AppVersionController{

    @Autowired
    AppVersionService appVersionService;

    @RequestMapping("selectOne")
    @ResponseBody
    public AppVersion selectOne(long id) {
        return appVersionService.selectByPrimaryKey(id);
    }

    /**
     * 跳转到index页面
     * 默认走ajax响应，如果要走视图解析器讲返回值设置为ModelAndView
     *
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/app/index");
    }

    /**
     * 返回列表
     */
    @RequestMapping("findAll")
    public Result findAll(){
        return new Result(true,null,appVersionService.selectAll());
    }

    @RequestMapping("selectPage/{pageNum}/{size}")
    public Result selectPage(@PathVariable("pageNum")int pageNum,@PathVariable("size")int size){
        PageInfo pageInfo = appVersionService.selectPage(pageNum,size);
        return new Result(true,null,pageInfo);
    }

    /**
     * 跳转到update页
     * @return
     */
    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(){
        return new ModelAndView("/app/update");  // 走视图解析器
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody AppVersion app){
        app.setUpdateDate(new Date());//设置更新时间
        appVersionService.updateByPrimaryKeySelective(app);
        return new Result(true,"更新成功",null);
    }

    @RequestMapping("insert")
    public Result insert(@RequestBody AppVersion app){
        app.setCreateDate(new Date());
        app.setUpdateDate(new Date());
        app.setDelFlag("0");
        appVersionService.insertSelective(app);
        return new Result(true,"添加成功",null);
    }
    @RequestMapping("toDelete/{id}")
    public Result toDelete(@PathVariable("id")long id){
        AppVersion appVersion = new AppVersion();
        appVersion.setId(id);
        appVersion.setUpdateDate(new Date());
        appVersion.setDelFlag("1");
        appVersionService.updateByPrimaryKeySelective(appVersion);
        return new Result(true,"删除成功",null);
    }
}
