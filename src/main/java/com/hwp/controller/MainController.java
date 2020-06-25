package com.hwp.controller;

import com.hwp.entity.Result;
import com.hwp.entity.SysUser;
import com.hwp.service.SysUserService;
import com.hwp.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    SysUserService sysUserService;

    @RequestMapping("navbar")
    public String navbar(){
        return "/publice/navbar";
    }

    @RequestMapping("sidebar")
    public String sidebar(){
        return "/publice/sidebar";
    }

    @RequestMapping("doLogin")
    @ResponseBody
    public Result login(@RequestBody Map<String,String> params,HttpSession session){
        //校验 验证码是否正确
        if(params.containsKey("vcode")&& !StringUtils.isEmpty(params.get("vcode"))){
            String vcode =  params.get("vcode");
            if(params.containsKey("name")&& !StringUtils.isEmpty(params.get("name"))){
                if(params.containsKey("password")&& !StringUtils.isEmpty(params.get("password"))){
                    SysUser sysUser = new SysUser();
                    String name = params.get("name");
                    String password = params.get("password");
                    sysUser.setUsername(name);
                    sysUser.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(password)+name));
                    SysUser loginUser = sysUserService.selectOne(sysUser);
                    if(loginUser!=null){//正确登录
                        //保持用户登录状态
                        session.setAttribute("loginUser",loginUser);
//                            return new Result(true,"登录成功",null);
                        loginUser.setPassword(null);
                        return new Result(true,"登录成功",loginUser);
                    }else{
                        return new Result(false,"用户名或密码错误",null);
                    }
                }
            }

        }else{
            return new Result(false,"验证码错误",null);
        }
        return new Result(false,"登录失败，请重试",null);
    }
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";  //不再走视图解析器
    }
    @RequestMapping("toIndex")
    public String toIndex(){
        return "/index";
    }
}
