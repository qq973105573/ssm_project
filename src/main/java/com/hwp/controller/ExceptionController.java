package com.hwp.controller;

import com.hwp.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    @ResponseBody
    public Result doException(Exception e){
        return new Result(false,e.getMessage(),e);
    }
}
