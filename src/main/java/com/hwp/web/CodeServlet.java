package com.hwp.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/code.jpg",initParams ={@WebInitParam(name = "kaptcha.textproducer.char.length",value = "4"),
        @WebInitParam(name = "kaptcha.image.width",value = "120"),
        @WebInitParam(name = "kaptcha.image.height",value = "34"),
        @WebInitParam(name = "kaptcha.textproducer.font.color",value = "black"),
        @WebInitParam(name = "kaptcha.background.clear.from",value = "gray"),
        @WebInitParam(name = "kaptcha.background.clear.to",value = "white"),
        @WebInitParam(name = "kaptcha.noise.color",value = "white"),
        @WebInitParam(name = "kaptcha.session.key",value = "checkCode"),
        @WebInitParam(name = "kaptcha.textproducer.font.size",value = "28"),
        @WebInitParam(name = "kaptcha.textproducer.char.string",value = "abcdefghijklmnopqrstuvwxyz"),
        @WebInitParam(name = "kaptcha.textproducer.font.names",value = "Monospaced"),
        @WebInitParam(name = "kaptcha.obscurificator.impl",value = "com.google.code.kaptcha.impl.ShadowGimpy")})
public class CodeServlet extends KaptchaServlet {
}
