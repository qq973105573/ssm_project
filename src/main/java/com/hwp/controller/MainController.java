package com.hwp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("navbar")
    public String navbar(){
        return "/publice/navbar";
    }

    @RequestMapping("sidebar")
    public String sidebar(){
        return "/publice/sidebar";
    }


}
