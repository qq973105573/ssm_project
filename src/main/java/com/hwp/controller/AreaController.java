package com.hwp.controller;


import com.github.pagehelper.PageInfo;
import com.hwp.entity.Result;
import com.hwp.entity.SysArea;
import com.hwp.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("manager/area")
public class AreaController {

    @Autowired
    AreaService areaService;

    @RequestMapping("")
    public ModelAndView index(){
        return new ModelAndView("/area/area");
    }

    @RequestMapping("toSelect")
    public ModelAndView toSelect(){
        return new ModelAndView("/area/select");
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(){
        return new ModelAndView("/area/save");
    }

    @RequestMapping("awesome")
    public ModelAndView awesome(){
        return new ModelAndView("/modules/font-awesome");
    }

    @RequestMapping(value = "selectPage/{pageNum}/{pageSize}")
    public Result selectPage(@RequestBody Map<String,Object> params, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize ){
        PageInfo<SysArea> pageInfo = areaService.selectPage(pageNum, pageSize,params);
        return new Result(true,"查询成功",pageInfo);//Result 统一结果响应
    }
    @RequestMapping("selectAll")
    public List<SysArea> selectAll(){
        return areaService.selectAll();
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody SysArea area){
        area.setUpdateDate(new Date());
        areaService.updateByPrimaryKeySelective(area);
        return new Result(true,"更新成功",null);
    }

    @RequestMapping("download")
    public void download(HttpServletResponse resp) throws IOException {
        resp.setHeader("Content-Disposition","attachment;;filename="+new String("SysArea.xlsx".getBytes(),"iso8859-1"));
        areaService.download(resp.getOutputStream());
    }

    @RequestMapping("upload")
    public Result upload(MultipartFile file) throws IOException {
        areaService.upload(file.getInputStream());
        return new Result(true,"导入成功",null);
    }
//    @RequestMapping(value = "doUpdate",method = RequestMethod.PUT)
//    public Result doUpdate(@RequestBody Statute statute){
//        //设置更新时间
//        statute.setUpdateDate(new Date());
//        AreaService.updateByPrimaryKeySelective(statute);
//        return  new Result(true,"更新成功",null);
//    }
//
//    @RequestMapping("toDelete/{id}")
//    public Result toDelete(@PathVariable("id")long id){
//        Statute statute = new Statute();
//        statute.setId(id);
//        statute.setUpdateDate(new Date());
//        statute.setDelFlag("0");
//        AreaService.updateByPrimaryKeySelective(statute);
//        return new Result(true,"删除成功",null);
//    }
//    @RequestMapping("insert")
//    public Result insert(@RequestBody Statute statute){
//        statute.setCreateDate(new Date());
//        statute.setUpdateDate(new Date());
//        statute.setDelFlag("0");
//        AreaService.insertSelective(statute);
//        return new Result(true,"添加成功",null);
//    }


}
