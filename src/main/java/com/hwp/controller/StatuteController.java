package com.hwp.controller;

import com.github.pagehelper.PageInfo;
import com.hwp.entity.Result;
import com.hwp.entity.Statute;
import com.hwp.service.StatuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;


@RestController
@RequestMapping("manager/statute")
public class StatuteController {

    @Autowired
    StatuteService statuteService;

    @RequestMapping("index")
    public ModelAndView index(){
        return new ModelAndView("/statute/index");
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(){
        return new ModelAndView("/statute/update");
    }

    @RequestMapping(value = "selectPage/{pageNum}/{pageSize}")
    public Result selectPage( @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize,Integer type ){
        PageInfo<Statute> pageInfo = statuteService.selectPage(pageNum, pageSize,type);
        return new Result(true,"查询成功",pageInfo);//Result 统一结果响应
    }

    @RequestMapping(value = "doUpdate",method = RequestMethod.PUT)
    public Result doUpdate(@RequestBody Statute statute){
        //设置更新时间
        statute.setUpdateDate(new Date());
        statuteService.updateByPrimaryKeySelective(statute);
        return  new Result(true,"更新成功",null);
    }

    @RequestMapping("toDelete/{id}")
    public Result toDelete(@PathVariable("id")long id){
        Statute statute = new Statute();
        statute.setId(id);
        statute.setUpdateDate(new Date());
        statute.setDelFlag("0");
        statuteService.updateByPrimaryKeySelective(statute);
        return new Result(true,"删除成功",null);
    }
    @RequestMapping("insert")
    public Result insert(@RequestBody Statute statute){
        statute.setCreateDate(new Date());
        statute.setUpdateDate(new Date());
        statute.setDelFlag("0");
        statuteService.insertSelective(statute);
        return new Result(true,"添加成功",null);
    }


}
