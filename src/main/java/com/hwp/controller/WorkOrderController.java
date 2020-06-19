package com.hwp.controller;

import com.github.pagehelper.PageInfo;
import com.hwp.entity.Result;
import com.hwp.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("manager/work")
public class WorkOrderController {

    @Autowired
    WorkOrderService workOrderService;


    /**
     * 跳转到index页面
     * 默认走ajax响应，如果要走视图解析器讲返回值设置为ModelAndView
     *
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/work/admin/index");
    }

    @RequestMapping("toDetail")
    public ModelAndView toDetail(){
        return new ModelAndView("/work/work-detail");
    }


    @RequestMapping("selectPage/{pageNum}/{size}")
    public Result selectPage(@PathVariable("pageNum")int pageNum, @PathVariable("size")int size, @RequestBody Map<String,Object> map){
        PageInfo pageInfo = workOrderService.selectPage(pageNum,size,map);
        return new Result(true,null,pageInfo);
    }

    @RequestMapping("selectDetail/{id}")
    public Result selectDetail(@PathVariable("id") long id){
        return new Result(true,null,workOrderService.selectDetail(id));
    }
}
