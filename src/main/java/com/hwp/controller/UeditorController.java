package com.hwp.controller;

import cn.baidu.ueditor.ActionEnter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UeditorController {
    @Value("${dir}")
    private String dir;
    @Value("${path}")
    private String path;
    /**
     * 富文本统一请求接口
     * 通过请求参数action的值来判断进行相应的处理逻辑
     * @return
     */
    @RequestMapping("ueditor")
    public String execute(String action, HttpServletRequest req, MultipartFile upfile){
        String jsonStr = null;
        //第一次请求，前端初始化ueditor对象的时候回自动发送action= config请求获取后台的配置json
        if("config".equals(action)){
            jsonStr= new ActionEnter(req, req.getServletContext().getRealPath("/")).exec();
        }else if("uploadimage".equals(action)){
            //文件上传
            //1.引入依赖 2.配置文件上传解析器multipartResolver 3.提交文件属性与方法参数名一致
            //功能实现
            //1.上传指定路径 2告诉服务器真实路径位置
            //dir文件存放路径
            File dirFile = new File(dir);

            if(!dirFile.exists()){
                dirFile.mkdirs();
            }
            //随机生成一个文件名
            String originalFilename = upfile.getOriginalFilename();
            String type = originalFilename.substring(originalFilename.lastIndexOf("."));
            String targetFileName = UUID.randomUUID().toString()+type;
            try {
                upfile.transferTo(new File(dirFile,targetFileName));
                Map<String, Object> map = resultMap("SUCCESS", originalFilename, upfile.getSize(), targetFileName, type, path + File.separator + targetFileName);
                jsonStr = new JSONObject(map).toString();
            } catch (IOException e) {
                e.printStackTrace();
                //失败相应
                jsonStr=new JSONObject(resultMap("FAILS",null,0,null,null,null)).toString();
            }

        }
        return jsonStr;
    }
    //{"state": "SUCCESS","original": "111.jpg","size": "124147","title": "1535961757878095151.jpg","type": ".jpg","url": "/1535961757878095151.jpg"}
    //封装统一响应json格式
    private Map<String,Object> resultMap(String state, String original, long size, String title,String type,  String url){
        Map<String ,Object> result = new HashMap<>();
        result.put("state",state);
        result.put("original",original);
        result.put("size",size);
        result.put("title",title);
        result.put("type",type);
        result.put("url", url);
        return result;
    }
}
